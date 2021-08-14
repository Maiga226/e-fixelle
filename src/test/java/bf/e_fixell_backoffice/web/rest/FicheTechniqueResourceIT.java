package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.FicheTechnique;
import bf.e_fixell_backoffice.repository.FicheTechniqueRepository;

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
 * Integration tests for the {@link FicheTechniqueResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FicheTechniqueResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private FicheTechniqueRepository ficheTechniqueRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFicheTechniqueMockMvc;

    private FicheTechnique ficheTechnique;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FicheTechnique createEntity(EntityManager em) {
        FicheTechnique ficheTechnique = new FicheTechnique()
            .libelle(DEFAULT_LIBELLE);
        return ficheTechnique;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FicheTechnique createUpdatedEntity(EntityManager em) {
        FicheTechnique ficheTechnique = new FicheTechnique()
            .libelle(UPDATED_LIBELLE);
        return ficheTechnique;
    }

    @BeforeEach
    public void initTest() {
        ficheTechnique = createEntity(em);
    }

    @Test
    @Transactional
    public void createFicheTechnique() throws Exception {
        int databaseSizeBeforeCreate = ficheTechniqueRepository.findAll().size();
        // Create the FicheTechnique
        restFicheTechniqueMockMvc.perform(post("/api/fiche-techniques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ficheTechnique)))
            .andExpect(status().isCreated());

        // Validate the FicheTechnique in the database
        List<FicheTechnique> ficheTechniqueList = ficheTechniqueRepository.findAll();
        assertThat(ficheTechniqueList).hasSize(databaseSizeBeforeCreate + 1);
        FicheTechnique testFicheTechnique = ficheTechniqueList.get(ficheTechniqueList.size() - 1);
        assertThat(testFicheTechnique.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createFicheTechniqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ficheTechniqueRepository.findAll().size();

        // Create the FicheTechnique with an existing ID
        ficheTechnique.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFicheTechniqueMockMvc.perform(post("/api/fiche-techniques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ficheTechnique)))
            .andExpect(status().isBadRequest());

        // Validate the FicheTechnique in the database
        List<FicheTechnique> ficheTechniqueList = ficheTechniqueRepository.findAll();
        assertThat(ficheTechniqueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFicheTechniques() throws Exception {
        // Initialize the database
        ficheTechniqueRepository.saveAndFlush(ficheTechnique);

        // Get all the ficheTechniqueList
        restFicheTechniqueMockMvc.perform(get("/api/fiche-techniques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ficheTechnique.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getFicheTechnique() throws Exception {
        // Initialize the database
        ficheTechniqueRepository.saveAndFlush(ficheTechnique);

        // Get the ficheTechnique
        restFicheTechniqueMockMvc.perform(get("/api/fiche-techniques/{id}", ficheTechnique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ficheTechnique.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingFicheTechnique() throws Exception {
        // Get the ficheTechnique
        restFicheTechniqueMockMvc.perform(get("/api/fiche-techniques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFicheTechnique() throws Exception {
        // Initialize the database
        ficheTechniqueRepository.saveAndFlush(ficheTechnique);

        int databaseSizeBeforeUpdate = ficheTechniqueRepository.findAll().size();

        // Update the ficheTechnique
        FicheTechnique updatedFicheTechnique = ficheTechniqueRepository.findById(ficheTechnique.getId()).get();
        // Disconnect from session so that the updates on updatedFicheTechnique are not directly saved in db
        em.detach(updatedFicheTechnique);
        updatedFicheTechnique
            .libelle(UPDATED_LIBELLE);

        restFicheTechniqueMockMvc.perform(put("/api/fiche-techniques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFicheTechnique)))
            .andExpect(status().isOk());

        // Validate the FicheTechnique in the database
        List<FicheTechnique> ficheTechniqueList = ficheTechniqueRepository.findAll();
        assertThat(ficheTechniqueList).hasSize(databaseSizeBeforeUpdate);
        FicheTechnique testFicheTechnique = ficheTechniqueList.get(ficheTechniqueList.size() - 1);
        assertThat(testFicheTechnique.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingFicheTechnique() throws Exception {
        int databaseSizeBeforeUpdate = ficheTechniqueRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheTechniqueMockMvc.perform(put("/api/fiche-techniques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ficheTechnique)))
            .andExpect(status().isBadRequest());

        // Validate the FicheTechnique in the database
        List<FicheTechnique> ficheTechniqueList = ficheTechniqueRepository.findAll();
        assertThat(ficheTechniqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFicheTechnique() throws Exception {
        // Initialize the database
        ficheTechniqueRepository.saveAndFlush(ficheTechnique);

        int databaseSizeBeforeDelete = ficheTechniqueRepository.findAll().size();

        // Delete the ficheTechnique
        restFicheTechniqueMockMvc.perform(delete("/api/fiche-techniques/{id}", ficheTechnique.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FicheTechnique> ficheTechniqueList = ficheTechniqueRepository.findAll();
        assertThat(ficheTechniqueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
