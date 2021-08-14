package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.Approvisionnement;
import bf.e_fixell_backoffice.repository.ApprovisionnementRepository;

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
 * Integration tests for the {@link ApprovisionnementResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ApprovisionnementResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final BigDecimal DEFAULT_MONTANT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT = new BigDecimal(2);

    @Autowired
    private ApprovisionnementRepository approvisionnementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restApprovisionnementMockMvc;

    private Approvisionnement approvisionnement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Approvisionnement createEntity(EntityManager em) {
        Approvisionnement approvisionnement = new Approvisionnement()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .date(DEFAULT_DATE)
            .montant(DEFAULT_MONTANT);
        return approvisionnement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Approvisionnement createUpdatedEntity(EntityManager em) {
        Approvisionnement approvisionnement = new Approvisionnement()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .date(UPDATED_DATE)
            .montant(UPDATED_MONTANT);
        return approvisionnement;
    }

    @BeforeEach
    public void initTest() {
        approvisionnement = createEntity(em);
    }

    @Test
    @Transactional
    public void createApprovisionnement() throws Exception {
        int databaseSizeBeforeCreate = approvisionnementRepository.findAll().size();
        // Create the Approvisionnement
        restApprovisionnementMockMvc.perform(post("/api/approvisionnements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(approvisionnement)))
            .andExpect(status().isCreated());

        // Validate the Approvisionnement in the database
        List<Approvisionnement> approvisionnementList = approvisionnementRepository.findAll();
        assertThat(approvisionnementList).hasSize(databaseSizeBeforeCreate + 1);
        Approvisionnement testApprovisionnement = approvisionnementList.get(approvisionnementList.size() - 1);
        assertThat(testApprovisionnement.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testApprovisionnement.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testApprovisionnement.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testApprovisionnement.getMontant()).isEqualTo(DEFAULT_MONTANT);
    }

    @Test
    @Transactional
    public void createApprovisionnementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = approvisionnementRepository.findAll().size();

        // Create the Approvisionnement with an existing ID
        approvisionnement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApprovisionnementMockMvc.perform(post("/api/approvisionnements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(approvisionnement)))
            .andExpect(status().isBadRequest());

        // Validate the Approvisionnement in the database
        List<Approvisionnement> approvisionnementList = approvisionnementRepository.findAll();
        assertThat(approvisionnementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllApprovisionnements() throws Exception {
        // Initialize the database
        approvisionnementRepository.saveAndFlush(approvisionnement);

        // Get all the approvisionnementList
        restApprovisionnementMockMvc.perform(get("/api/approvisionnements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(approvisionnement.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())));
    }
    
    @Test
    @Transactional
    public void getApprovisionnement() throws Exception {
        // Initialize the database
        approvisionnementRepository.saveAndFlush(approvisionnement);

        // Get the approvisionnement
        restApprovisionnementMockMvc.perform(get("/api/approvisionnements/{id}", approvisionnement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(approvisionnement.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingApprovisionnement() throws Exception {
        // Get the approvisionnement
        restApprovisionnementMockMvc.perform(get("/api/approvisionnements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApprovisionnement() throws Exception {
        // Initialize the database
        approvisionnementRepository.saveAndFlush(approvisionnement);

        int databaseSizeBeforeUpdate = approvisionnementRepository.findAll().size();

        // Update the approvisionnement
        Approvisionnement updatedApprovisionnement = approvisionnementRepository.findById(approvisionnement.getId()).get();
        // Disconnect from session so that the updates on updatedApprovisionnement are not directly saved in db
        em.detach(updatedApprovisionnement);
        updatedApprovisionnement
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .date(UPDATED_DATE)
            .montant(UPDATED_MONTANT);

        restApprovisionnementMockMvc.perform(put("/api/approvisionnements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedApprovisionnement)))
            .andExpect(status().isOk());

        // Validate the Approvisionnement in the database
        List<Approvisionnement> approvisionnementList = approvisionnementRepository.findAll();
        assertThat(approvisionnementList).hasSize(databaseSizeBeforeUpdate);
        Approvisionnement testApprovisionnement = approvisionnementList.get(approvisionnementList.size() - 1);
        assertThat(testApprovisionnement.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testApprovisionnement.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testApprovisionnement.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testApprovisionnement.getMontant()).isEqualTo(UPDATED_MONTANT);
    }

    @Test
    @Transactional
    public void updateNonExistingApprovisionnement() throws Exception {
        int databaseSizeBeforeUpdate = approvisionnementRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApprovisionnementMockMvc.perform(put("/api/approvisionnements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(approvisionnement)))
            .andExpect(status().isBadRequest());

        // Validate the Approvisionnement in the database
        List<Approvisionnement> approvisionnementList = approvisionnementRepository.findAll();
        assertThat(approvisionnementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApprovisionnement() throws Exception {
        // Initialize the database
        approvisionnementRepository.saveAndFlush(approvisionnement);

        int databaseSizeBeforeDelete = approvisionnementRepository.findAll().size();

        // Delete the approvisionnement
        restApprovisionnementMockMvc.perform(delete("/api/approvisionnements/{id}", approvisionnement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Approvisionnement> approvisionnementList = approvisionnementRepository.findAll();
        assertThat(approvisionnementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
