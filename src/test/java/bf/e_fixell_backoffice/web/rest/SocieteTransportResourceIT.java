package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.SocieteTransport;
import bf.e_fixell_backoffice.repository.SocieteTransportRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SocieteTransportResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SocieteTransportResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    @Autowired
    private SocieteTransportRepository societeTransportRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSocieteTransportMockMvc;

    private SocieteTransport societeTransport;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SocieteTransport createEntity(EntityManager em) {
        SocieteTransport societeTransport = new SocieteTransport()
            .libelle(DEFAULT_LIBELLE)
            .adresse(DEFAULT_ADRESSE)
            .telephone(DEFAULT_TELEPHONE)
            .email(DEFAULT_EMAIL);
        return societeTransport;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SocieteTransport createUpdatedEntity(EntityManager em) {
        SocieteTransport societeTransport = new SocieteTransport()
            .libelle(UPDATED_LIBELLE)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .email(UPDATED_EMAIL);
        return societeTransport;
    }

    @BeforeEach
    public void initTest() {
        societeTransport = createEntity(em);
    }

    @Test
    @Transactional
    public void createSocieteTransport() throws Exception {
        int databaseSizeBeforeCreate = societeTransportRepository.findAll().size();
        // Create the SocieteTransport
        restSocieteTransportMockMvc.perform(post("/api/societe-transports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeTransport)))
            .andExpect(status().isCreated());

        // Validate the SocieteTransport in the database
        List<SocieteTransport> societeTransportList = societeTransportRepository.findAll();
        assertThat(societeTransportList).hasSize(databaseSizeBeforeCreate + 1);
        SocieteTransport testSocieteTransport = societeTransportList.get(societeTransportList.size() - 1);
        assertThat(testSocieteTransport.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testSocieteTransport.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testSocieteTransport.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testSocieteTransport.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void createSocieteTransportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = societeTransportRepository.findAll().size();

        // Create the SocieteTransport with an existing ID
        societeTransport.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSocieteTransportMockMvc.perform(post("/api/societe-transports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeTransport)))
            .andExpect(status().isBadRequest());

        // Validate the SocieteTransport in the database
        List<SocieteTransport> societeTransportList = societeTransportRepository.findAll();
        assertThat(societeTransportList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSocieteTransports() throws Exception {
        // Initialize the database
        societeTransportRepository.saveAndFlush(societeTransport);

        // Get all the societeTransportList
        restSocieteTransportMockMvc.perform(get("/api/societe-transports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(societeTransport.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }
    
    @Test
    @Transactional
    public void getSocieteTransport() throws Exception {
        // Initialize the database
        societeTransportRepository.saveAndFlush(societeTransport);

        // Get the societeTransport
        restSocieteTransportMockMvc.perform(get("/api/societe-transports/{id}", societeTransport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(societeTransport.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }
    @Test
    @Transactional
    public void getNonExistingSocieteTransport() throws Exception {
        // Get the societeTransport
        restSocieteTransportMockMvc.perform(get("/api/societe-transports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSocieteTransport() throws Exception {
        // Initialize the database
        societeTransportRepository.saveAndFlush(societeTransport);

        int databaseSizeBeforeUpdate = societeTransportRepository.findAll().size();

        // Update the societeTransport
        SocieteTransport updatedSocieteTransport = societeTransportRepository.findById(societeTransport.getId()).get();
        // Disconnect from session so that the updates on updatedSocieteTransport are not directly saved in db
        em.detach(updatedSocieteTransport);
        updatedSocieteTransport
            .libelle(UPDATED_LIBELLE)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .email(UPDATED_EMAIL);

        restSocieteTransportMockMvc.perform(put("/api/societe-transports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSocieteTransport)))
            .andExpect(status().isOk());

        // Validate the SocieteTransport in the database
        List<SocieteTransport> societeTransportList = societeTransportRepository.findAll();
        assertThat(societeTransportList).hasSize(databaseSizeBeforeUpdate);
        SocieteTransport testSocieteTransport = societeTransportList.get(societeTransportList.size() - 1);
        assertThat(testSocieteTransport.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testSocieteTransport.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testSocieteTransport.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testSocieteTransport.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void updateNonExistingSocieteTransport() throws Exception {
        int databaseSizeBeforeUpdate = societeTransportRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSocieteTransportMockMvc.perform(put("/api/societe-transports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeTransport)))
            .andExpect(status().isBadRequest());

        // Validate the SocieteTransport in the database
        List<SocieteTransport> societeTransportList = societeTransportRepository.findAll();
        assertThat(societeTransportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSocieteTransport() throws Exception {
        // Initialize the database
        societeTransportRepository.saveAndFlush(societeTransport);

        int databaseSizeBeforeDelete = societeTransportRepository.findAll().size();

        // Delete the societeTransport
        restSocieteTransportMockMvc.perform(delete("/api/societe-transports/{id}", societeTransport.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SocieteTransport> societeTransportList = societeTransportRepository.findAll();
        assertThat(societeTransportList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
