package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.Personnel;
import bf.e_fixell_backoffice.repository.PersonnelRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PersonnelResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PersonnelResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MATRICULE = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SALAIRE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SALAIRE = new BigDecimal(2);

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersonnelMockMvc;

    private Personnel personnel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Personnel createEntity(EntityManager em) {
        Personnel personnel = new Personnel()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .telephone(DEFAULT_TELEPHONE)
            .matricule(DEFAULT_MATRICULE)
            .salaire(DEFAULT_SALAIRE);
        return personnel;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Personnel createUpdatedEntity(EntityManager em) {
        Personnel personnel = new Personnel()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .telephone(UPDATED_TELEPHONE)
            .matricule(UPDATED_MATRICULE)
            .salaire(UPDATED_SALAIRE);
        return personnel;
    }

    @BeforeEach
    public void initTest() {
        personnel = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonnel() throws Exception {
        int databaseSizeBeforeCreate = personnelRepository.findAll().size();
        // Create the Personnel
        restPersonnelMockMvc.perform(post("/api/personnel")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnel)))
            .andExpect(status().isCreated());

        // Validate the Personnel in the database
        List<Personnel> personnelList = personnelRepository.findAll();
        assertThat(personnelList).hasSize(databaseSizeBeforeCreate + 1);
        Personnel testPersonnel = personnelList.get(personnelList.size() - 1);
        assertThat(testPersonnel.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testPersonnel.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testPersonnel.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testPersonnel.getMatricule()).isEqualTo(DEFAULT_MATRICULE);
        assertThat(testPersonnel.getSalaire()).isEqualTo(DEFAULT_SALAIRE);
    }

    @Test
    @Transactional
    public void createPersonnelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personnelRepository.findAll().size();

        // Create the Personnel with an existing ID
        personnel.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonnelMockMvc.perform(post("/api/personnel")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnel)))
            .andExpect(status().isBadRequest());

        // Validate the Personnel in the database
        List<Personnel> personnelList = personnelRepository.findAll();
        assertThat(personnelList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPersonnel() throws Exception {
        // Initialize the database
        personnelRepository.saveAndFlush(personnel);

        // Get all the personnelList
        restPersonnelMockMvc.perform(get("/api/personnel?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personnel.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].matricule").value(hasItem(DEFAULT_MATRICULE)))
            .andExpect(jsonPath("$.[*].salaire").value(hasItem(DEFAULT_SALAIRE.intValue())));
    }
    
    @Test
    @Transactional
    public void getPersonnel() throws Exception {
        // Initialize the database
        personnelRepository.saveAndFlush(personnel);

        // Get the personnel
        restPersonnelMockMvc.perform(get("/api/personnel/{id}", personnel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(personnel.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.matricule").value(DEFAULT_MATRICULE))
            .andExpect(jsonPath("$.salaire").value(DEFAULT_SALAIRE.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPersonnel() throws Exception {
        // Get the personnel
        restPersonnelMockMvc.perform(get("/api/personnel/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonnel() throws Exception {
        // Initialize the database
        personnelRepository.saveAndFlush(personnel);

        int databaseSizeBeforeUpdate = personnelRepository.findAll().size();

        // Update the personnel
        Personnel updatedPersonnel = personnelRepository.findById(personnel.getId()).get();
        // Disconnect from session so that the updates on updatedPersonnel are not directly saved in db
        em.detach(updatedPersonnel);
        updatedPersonnel
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .telephone(UPDATED_TELEPHONE)
            .matricule(UPDATED_MATRICULE)
            .salaire(UPDATED_SALAIRE);

        restPersonnelMockMvc.perform(put("/api/personnel")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPersonnel)))
            .andExpect(status().isOk());

        // Validate the Personnel in the database
        List<Personnel> personnelList = personnelRepository.findAll();
        assertThat(personnelList).hasSize(databaseSizeBeforeUpdate);
        Personnel testPersonnel = personnelList.get(personnelList.size() - 1);
        assertThat(testPersonnel.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testPersonnel.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testPersonnel.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testPersonnel.getMatricule()).isEqualTo(UPDATED_MATRICULE);
        assertThat(testPersonnel.getSalaire()).isEqualTo(UPDATED_SALAIRE);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonnel() throws Exception {
        int databaseSizeBeforeUpdate = personnelRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonnelMockMvc.perform(put("/api/personnel")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnel)))
            .andExpect(status().isBadRequest());

        // Validate the Personnel in the database
        List<Personnel> personnelList = personnelRepository.findAll();
        assertThat(personnelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersonnel() throws Exception {
        // Initialize the database
        personnelRepository.saveAndFlush(personnel);

        int databaseSizeBeforeDelete = personnelRepository.findAll().size();

        // Delete the personnel
        restPersonnelMockMvc.perform(delete("/api/personnel/{id}", personnel.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Personnel> personnelList = personnelRepository.findAll();
        assertThat(personnelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
