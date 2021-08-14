package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.Perte;
import bf.e_fixell_backoffice.repository.PerteRepository;

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
 * Integration tests for the {@link PerteResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PerteResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_QUANTITE = 1;
    private static final Integer UPDATED_QUANTITE = 2;

    private static final BigDecimal DEFAULT_MONTANT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT = new BigDecimal(2);

    @Autowired
    private PerteRepository perteRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPerteMockMvc;

    private Perte perte;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Perte createEntity(EntityManager em) {
        Perte perte = new Perte()
            .libelle(DEFAULT_LIBELLE)
            .date(DEFAULT_DATE)
            .quantite(DEFAULT_QUANTITE)
            .montant(DEFAULT_MONTANT);
        return perte;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Perte createUpdatedEntity(EntityManager em) {
        Perte perte = new Perte()
            .libelle(UPDATED_LIBELLE)
            .date(UPDATED_DATE)
            .quantite(UPDATED_QUANTITE)
            .montant(UPDATED_MONTANT);
        return perte;
    }

    @BeforeEach
    public void initTest() {
        perte = createEntity(em);
    }

    @Test
    @Transactional
    public void createPerte() throws Exception {
        int databaseSizeBeforeCreate = perteRepository.findAll().size();
        // Create the Perte
        restPerteMockMvc.perform(post("/api/pertes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(perte)))
            .andExpect(status().isCreated());

        // Validate the Perte in the database
        List<Perte> perteList = perteRepository.findAll();
        assertThat(perteList).hasSize(databaseSizeBeforeCreate + 1);
        Perte testPerte = perteList.get(perteList.size() - 1);
        assertThat(testPerte.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testPerte.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testPerte.getQuantite()).isEqualTo(DEFAULT_QUANTITE);
        assertThat(testPerte.getMontant()).isEqualTo(DEFAULT_MONTANT);
    }

    @Test
    @Transactional
    public void createPerteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = perteRepository.findAll().size();

        // Create the Perte with an existing ID
        perte.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPerteMockMvc.perform(post("/api/pertes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(perte)))
            .andExpect(status().isBadRequest());

        // Validate the Perte in the database
        List<Perte> perteList = perteRepository.findAll();
        assertThat(perteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPertes() throws Exception {
        // Initialize the database
        perteRepository.saveAndFlush(perte);

        // Get all the perteList
        restPerteMockMvc.perform(get("/api/pertes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(perte.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE)))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())));
    }
    
    @Test
    @Transactional
    public void getPerte() throws Exception {
        // Initialize the database
        perteRepository.saveAndFlush(perte);

        // Get the perte
        restPerteMockMvc.perform(get("/api/pertes/{id}", perte.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(perte.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.quantite").value(DEFAULT_QUANTITE))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPerte() throws Exception {
        // Get the perte
        restPerteMockMvc.perform(get("/api/pertes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePerte() throws Exception {
        // Initialize the database
        perteRepository.saveAndFlush(perte);

        int databaseSizeBeforeUpdate = perteRepository.findAll().size();

        // Update the perte
        Perte updatedPerte = perteRepository.findById(perte.getId()).get();
        // Disconnect from session so that the updates on updatedPerte are not directly saved in db
        em.detach(updatedPerte);
        updatedPerte
            .libelle(UPDATED_LIBELLE)
            .date(UPDATED_DATE)
            .quantite(UPDATED_QUANTITE)
            .montant(UPDATED_MONTANT);

        restPerteMockMvc.perform(put("/api/pertes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPerte)))
            .andExpect(status().isOk());

        // Validate the Perte in the database
        List<Perte> perteList = perteRepository.findAll();
        assertThat(perteList).hasSize(databaseSizeBeforeUpdate);
        Perte testPerte = perteList.get(perteList.size() - 1);
        assertThat(testPerte.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testPerte.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testPerte.getQuantite()).isEqualTo(UPDATED_QUANTITE);
        assertThat(testPerte.getMontant()).isEqualTo(UPDATED_MONTANT);
    }

    @Test
    @Transactional
    public void updateNonExistingPerte() throws Exception {
        int databaseSizeBeforeUpdate = perteRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPerteMockMvc.perform(put("/api/pertes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(perte)))
            .andExpect(status().isBadRequest());

        // Validate the Perte in the database
        List<Perte> perteList = perteRepository.findAll();
        assertThat(perteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePerte() throws Exception {
        // Initialize the database
        perteRepository.saveAndFlush(perte);

        int databaseSizeBeforeDelete = perteRepository.findAll().size();

        // Delete the perte
        restPerteMockMvc.perform(delete("/api/pertes/{id}", perte.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Perte> perteList = perteRepository.findAll();
        assertThat(perteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
