package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.UniteOrganisation;
import bf.e_fixell_backoffice.repository.UniteOrganisationRepository;

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

import bf.e_fixell_backoffice.domain.enumeration.TypeUniteOrganisation;
/**
 * Integration tests for the {@link UniteOrganisationResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UniteOrganisationResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final TypeUniteOrganisation DEFAULT_TYPE = TypeUniteOrganisation.PAYS;
    private static final TypeUniteOrganisation UPDATED_TYPE = TypeUniteOrganisation.REGION;

    @Autowired
    private UniteOrganisationRepository uniteOrganisationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUniteOrganisationMockMvc;

    private UniteOrganisation uniteOrganisation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UniteOrganisation createEntity(EntityManager em) {
        UniteOrganisation uniteOrganisation = new UniteOrganisation()
            .libelle(DEFAULT_LIBELLE)
            .type(DEFAULT_TYPE);
        return uniteOrganisation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UniteOrganisation createUpdatedEntity(EntityManager em) {
        UniteOrganisation uniteOrganisation = new UniteOrganisation()
            .libelle(UPDATED_LIBELLE)
            .type(UPDATED_TYPE);
        return uniteOrganisation;
    }

    @BeforeEach
    public void initTest() {
        uniteOrganisation = createEntity(em);
    }

    @Test
    @Transactional
    public void createUniteOrganisation() throws Exception {
        int databaseSizeBeforeCreate = uniteOrganisationRepository.findAll().size();
        // Create the UniteOrganisation
        restUniteOrganisationMockMvc.perform(post("/api/unite-organisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uniteOrganisation)))
            .andExpect(status().isCreated());

        // Validate the UniteOrganisation in the database
        List<UniteOrganisation> uniteOrganisationList = uniteOrganisationRepository.findAll();
        assertThat(uniteOrganisationList).hasSize(databaseSizeBeforeCreate + 1);
        UniteOrganisation testUniteOrganisation = uniteOrganisationList.get(uniteOrganisationList.size() - 1);
        assertThat(testUniteOrganisation.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testUniteOrganisation.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createUniteOrganisationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uniteOrganisationRepository.findAll().size();

        // Create the UniteOrganisation with an existing ID
        uniteOrganisation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUniteOrganisationMockMvc.perform(post("/api/unite-organisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uniteOrganisation)))
            .andExpect(status().isBadRequest());

        // Validate the UniteOrganisation in the database
        List<UniteOrganisation> uniteOrganisationList = uniteOrganisationRepository.findAll();
        assertThat(uniteOrganisationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUniteOrganisations() throws Exception {
        // Initialize the database
        uniteOrganisationRepository.saveAndFlush(uniteOrganisation);

        // Get all the uniteOrganisationList
        restUniteOrganisationMockMvc.perform(get("/api/unite-organisations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uniteOrganisation.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getUniteOrganisation() throws Exception {
        // Initialize the database
        uniteOrganisationRepository.saveAndFlush(uniteOrganisation);

        // Get the uniteOrganisation
        restUniteOrganisationMockMvc.perform(get("/api/unite-organisations/{id}", uniteOrganisation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(uniteOrganisation.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingUniteOrganisation() throws Exception {
        // Get the uniteOrganisation
        restUniteOrganisationMockMvc.perform(get("/api/unite-organisations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUniteOrganisation() throws Exception {
        // Initialize the database
        uniteOrganisationRepository.saveAndFlush(uniteOrganisation);

        int databaseSizeBeforeUpdate = uniteOrganisationRepository.findAll().size();

        // Update the uniteOrganisation
        UniteOrganisation updatedUniteOrganisation = uniteOrganisationRepository.findById(uniteOrganisation.getId()).get();
        // Disconnect from session so that the updates on updatedUniteOrganisation are not directly saved in db
        em.detach(updatedUniteOrganisation);
        updatedUniteOrganisation
            .libelle(UPDATED_LIBELLE)
            .type(UPDATED_TYPE);

        restUniteOrganisationMockMvc.perform(put("/api/unite-organisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUniteOrganisation)))
            .andExpect(status().isOk());

        // Validate the UniteOrganisation in the database
        List<UniteOrganisation> uniteOrganisationList = uniteOrganisationRepository.findAll();
        assertThat(uniteOrganisationList).hasSize(databaseSizeBeforeUpdate);
        UniteOrganisation testUniteOrganisation = uniteOrganisationList.get(uniteOrganisationList.size() - 1);
        assertThat(testUniteOrganisation.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testUniteOrganisation.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingUniteOrganisation() throws Exception {
        int databaseSizeBeforeUpdate = uniteOrganisationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUniteOrganisationMockMvc.perform(put("/api/unite-organisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uniteOrganisation)))
            .andExpect(status().isBadRequest());

        // Validate the UniteOrganisation in the database
        List<UniteOrganisation> uniteOrganisationList = uniteOrganisationRepository.findAll();
        assertThat(uniteOrganisationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUniteOrganisation() throws Exception {
        // Initialize the database
        uniteOrganisationRepository.saveAndFlush(uniteOrganisation);

        int databaseSizeBeforeDelete = uniteOrganisationRepository.findAll().size();

        // Delete the uniteOrganisation
        restUniteOrganisationMockMvc.perform(delete("/api/unite-organisations/{id}", uniteOrganisation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UniteOrganisation> uniteOrganisationList = uniteOrganisationRepository.findAll();
        assertThat(uniteOrganisationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
