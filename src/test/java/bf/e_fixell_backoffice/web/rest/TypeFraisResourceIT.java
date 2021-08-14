package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.TypeFrais;
import bf.e_fixell_backoffice.repository.TypeFraisRepository;

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
 * Integration tests for the {@link TypeFraisResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeFraisResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private TypeFraisRepository typeFraisRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeFraisMockMvc;

    private TypeFrais typeFrais;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeFrais createEntity(EntityManager em) {
        TypeFrais typeFrais = new TypeFrais()
            .libelle(DEFAULT_LIBELLE);
        return typeFrais;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeFrais createUpdatedEntity(EntityManager em) {
        TypeFrais typeFrais = new TypeFrais()
            .libelle(UPDATED_LIBELLE);
        return typeFrais;
    }

    @BeforeEach
    public void initTest() {
        typeFrais = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeFrais() throws Exception {
        int databaseSizeBeforeCreate = typeFraisRepository.findAll().size();
        // Create the TypeFrais
        restTypeFraisMockMvc.perform(post("/api/type-frais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeFrais)))
            .andExpect(status().isCreated());

        // Validate the TypeFrais in the database
        List<TypeFrais> typeFraisList = typeFraisRepository.findAll();
        assertThat(typeFraisList).hasSize(databaseSizeBeforeCreate + 1);
        TypeFrais testTypeFrais = typeFraisList.get(typeFraisList.size() - 1);
        assertThat(testTypeFrais.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createTypeFraisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeFraisRepository.findAll().size();

        // Create the TypeFrais with an existing ID
        typeFrais.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeFraisMockMvc.perform(post("/api/type-frais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeFrais)))
            .andExpect(status().isBadRequest());

        // Validate the TypeFrais in the database
        List<TypeFrais> typeFraisList = typeFraisRepository.findAll();
        assertThat(typeFraisList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeFrais() throws Exception {
        // Initialize the database
        typeFraisRepository.saveAndFlush(typeFrais);

        // Get all the typeFraisList
        restTypeFraisMockMvc.perform(get("/api/type-frais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeFrais.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getTypeFrais() throws Exception {
        // Initialize the database
        typeFraisRepository.saveAndFlush(typeFrais);

        // Get the typeFrais
        restTypeFraisMockMvc.perform(get("/api/type-frais/{id}", typeFrais.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeFrais.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingTypeFrais() throws Exception {
        // Get the typeFrais
        restTypeFraisMockMvc.perform(get("/api/type-frais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeFrais() throws Exception {
        // Initialize the database
        typeFraisRepository.saveAndFlush(typeFrais);

        int databaseSizeBeforeUpdate = typeFraisRepository.findAll().size();

        // Update the typeFrais
        TypeFrais updatedTypeFrais = typeFraisRepository.findById(typeFrais.getId()).get();
        // Disconnect from session so that the updates on updatedTypeFrais are not directly saved in db
        em.detach(updatedTypeFrais);
        updatedTypeFrais
            .libelle(UPDATED_LIBELLE);

        restTypeFraisMockMvc.perform(put("/api/type-frais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeFrais)))
            .andExpect(status().isOk());

        // Validate the TypeFrais in the database
        List<TypeFrais> typeFraisList = typeFraisRepository.findAll();
        assertThat(typeFraisList).hasSize(databaseSizeBeforeUpdate);
        TypeFrais testTypeFrais = typeFraisList.get(typeFraisList.size() - 1);
        assertThat(testTypeFrais.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeFrais() throws Exception {
        int databaseSizeBeforeUpdate = typeFraisRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeFraisMockMvc.perform(put("/api/type-frais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeFrais)))
            .andExpect(status().isBadRequest());

        // Validate the TypeFrais in the database
        List<TypeFrais> typeFraisList = typeFraisRepository.findAll();
        assertThat(typeFraisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeFrais() throws Exception {
        // Initialize the database
        typeFraisRepository.saveAndFlush(typeFrais);

        int databaseSizeBeforeDelete = typeFraisRepository.findAll().size();

        // Delete the typeFrais
        restTypeFraisMockMvc.perform(delete("/api/type-frais/{id}", typeFrais.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeFrais> typeFraisList = typeFraisRepository.findAll();
        assertThat(typeFraisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
