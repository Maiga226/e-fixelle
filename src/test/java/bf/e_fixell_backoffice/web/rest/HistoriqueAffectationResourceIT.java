package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.HistoriqueAffectation;
import bf.e_fixell_backoffice.repository.HistoriqueAffectationRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link HistoriqueAffectationResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class HistoriqueAffectationResourceIT {

    private static final Instant DEFAULT_DATE_DEBUT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_DEBUT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_FIN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_FIN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final BigDecimal DEFAULT_SALAIRE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SALAIRE = new BigDecimal(2);

    @Autowired
    private HistoriqueAffectationRepository historiqueAffectationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHistoriqueAffectationMockMvc;

    private HistoriqueAffectation historiqueAffectation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistoriqueAffectation createEntity(EntityManager em) {
        HistoriqueAffectation historiqueAffectation = new HistoriqueAffectation()
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN)
            .salaire(DEFAULT_SALAIRE);
        return historiqueAffectation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistoriqueAffectation createUpdatedEntity(EntityManager em) {
        HistoriqueAffectation historiqueAffectation = new HistoriqueAffectation()
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .salaire(UPDATED_SALAIRE);
        return historiqueAffectation;
    }

    @BeforeEach
    public void initTest() {
        historiqueAffectation = createEntity(em);
    }

    @Test
    @Transactional
    public void createHistoriqueAffectation() throws Exception {
        int databaseSizeBeforeCreate = historiqueAffectationRepository.findAll().size();
        // Create the HistoriqueAffectation
        restHistoriqueAffectationMockMvc.perform(post("/api/historique-affectations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historiqueAffectation)))
            .andExpect(status().isCreated());

        // Validate the HistoriqueAffectation in the database
        List<HistoriqueAffectation> historiqueAffectationList = historiqueAffectationRepository.findAll();
        assertThat(historiqueAffectationList).hasSize(databaseSizeBeforeCreate + 1);
        HistoriqueAffectation testHistoriqueAffectation = historiqueAffectationList.get(historiqueAffectationList.size() - 1);
        assertThat(testHistoriqueAffectation.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testHistoriqueAffectation.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
        assertThat(testHistoriqueAffectation.getSalaire()).isEqualTo(DEFAULT_SALAIRE);
    }

    @Test
    @Transactional
    public void createHistoriqueAffectationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = historiqueAffectationRepository.findAll().size();

        // Create the HistoriqueAffectation with an existing ID
        historiqueAffectation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHistoriqueAffectationMockMvc.perform(post("/api/historique-affectations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historiqueAffectation)))
            .andExpect(status().isBadRequest());

        // Validate the HistoriqueAffectation in the database
        List<HistoriqueAffectation> historiqueAffectationList = historiqueAffectationRepository.findAll();
        assertThat(historiqueAffectationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllHistoriqueAffectations() throws Exception {
        // Initialize the database
        historiqueAffectationRepository.saveAndFlush(historiqueAffectation);

        // Get all the historiqueAffectationList
        restHistoriqueAffectationMockMvc.perform(get("/api/historique-affectations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historiqueAffectation.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(DEFAULT_DATE_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(DEFAULT_DATE_FIN.toString())))
            .andExpect(jsonPath("$.[*].salaire").value(hasItem(DEFAULT_SALAIRE.intValue())));
    }
    
    @Test
    @Transactional
    public void getHistoriqueAffectation() throws Exception {
        // Initialize the database
        historiqueAffectationRepository.saveAndFlush(historiqueAffectation);

        // Get the historiqueAffectation
        restHistoriqueAffectationMockMvc.perform(get("/api/historique-affectations/{id}", historiqueAffectation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(historiqueAffectation.getId().intValue()))
            .andExpect(jsonPath("$.dateDebut").value(DEFAULT_DATE_DEBUT.toString()))
            .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()))
            .andExpect(jsonPath("$.salaire").value(DEFAULT_SALAIRE.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingHistoriqueAffectation() throws Exception {
        // Get the historiqueAffectation
        restHistoriqueAffectationMockMvc.perform(get("/api/historique-affectations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHistoriqueAffectation() throws Exception {
        // Initialize the database
        historiqueAffectationRepository.saveAndFlush(historiqueAffectation);

        int databaseSizeBeforeUpdate = historiqueAffectationRepository.findAll().size();

        // Update the historiqueAffectation
        HistoriqueAffectation updatedHistoriqueAffectation = historiqueAffectationRepository.findById(historiqueAffectation.getId()).get();
        // Disconnect from session so that the updates on updatedHistoriqueAffectation are not directly saved in db
        em.detach(updatedHistoriqueAffectation);
        updatedHistoriqueAffectation
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .salaire(UPDATED_SALAIRE);

        restHistoriqueAffectationMockMvc.perform(put("/api/historique-affectations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedHistoriqueAffectation)))
            .andExpect(status().isOk());

        // Validate the HistoriqueAffectation in the database
        List<HistoriqueAffectation> historiqueAffectationList = historiqueAffectationRepository.findAll();
        assertThat(historiqueAffectationList).hasSize(databaseSizeBeforeUpdate);
        HistoriqueAffectation testHistoriqueAffectation = historiqueAffectationList.get(historiqueAffectationList.size() - 1);
        assertThat(testHistoriqueAffectation.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testHistoriqueAffectation.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testHistoriqueAffectation.getSalaire()).isEqualTo(UPDATED_SALAIRE);
    }

    @Test
    @Transactional
    public void updateNonExistingHistoriqueAffectation() throws Exception {
        int databaseSizeBeforeUpdate = historiqueAffectationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistoriqueAffectationMockMvc.perform(put("/api/historique-affectations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historiqueAffectation)))
            .andExpect(status().isBadRequest());

        // Validate the HistoriqueAffectation in the database
        List<HistoriqueAffectation> historiqueAffectationList = historiqueAffectationRepository.findAll();
        assertThat(historiqueAffectationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHistoriqueAffectation() throws Exception {
        // Initialize the database
        historiqueAffectationRepository.saveAndFlush(historiqueAffectation);

        int databaseSizeBeforeDelete = historiqueAffectationRepository.findAll().size();

        // Delete the historiqueAffectation
        restHistoriqueAffectationMockMvc.perform(delete("/api/historique-affectations/{id}", historiqueAffectation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HistoriqueAffectation> historiqueAffectationList = historiqueAffectationRepository.findAll();
        assertThat(historiqueAffectationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
