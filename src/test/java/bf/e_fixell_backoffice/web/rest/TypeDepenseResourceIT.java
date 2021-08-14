package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.TypeDepense;
import bf.e_fixell_backoffice.repository.TypeDepenseRepository;

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
 * Integration tests for the {@link TypeDepenseResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeDepenseResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private TypeDepenseRepository typeDepenseRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeDepenseMockMvc;

    private TypeDepense typeDepense;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeDepense createEntity(EntityManager em) {
        TypeDepense typeDepense = new TypeDepense()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return typeDepense;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeDepense createUpdatedEntity(EntityManager em) {
        TypeDepense typeDepense = new TypeDepense()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        return typeDepense;
    }

    @BeforeEach
    public void initTest() {
        typeDepense = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeDepense() throws Exception {
        int databaseSizeBeforeCreate = typeDepenseRepository.findAll().size();
        // Create the TypeDepense
        restTypeDepenseMockMvc.perform(post("/api/type-depenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeDepense)))
            .andExpect(status().isCreated());

        // Validate the TypeDepense in the database
        List<TypeDepense> typeDepenseList = typeDepenseRepository.findAll();
        assertThat(typeDepenseList).hasSize(databaseSizeBeforeCreate + 1);
        TypeDepense testTypeDepense = typeDepenseList.get(typeDepenseList.size() - 1);
        assertThat(testTypeDepense.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTypeDepense.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createTypeDepenseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeDepenseRepository.findAll().size();

        // Create the TypeDepense with an existing ID
        typeDepense.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeDepenseMockMvc.perform(post("/api/type-depenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeDepense)))
            .andExpect(status().isBadRequest());

        // Validate the TypeDepense in the database
        List<TypeDepense> typeDepenseList = typeDepenseRepository.findAll();
        assertThat(typeDepenseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeDepenses() throws Exception {
        // Initialize the database
        typeDepenseRepository.saveAndFlush(typeDepense);

        // Get all the typeDepenseList
        restTypeDepenseMockMvc.perform(get("/api/type-depenses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeDepense.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getTypeDepense() throws Exception {
        // Initialize the database
        typeDepenseRepository.saveAndFlush(typeDepense);

        // Get the typeDepense
        restTypeDepenseMockMvc.perform(get("/api/type-depenses/{id}", typeDepense.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeDepense.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingTypeDepense() throws Exception {
        // Get the typeDepense
        restTypeDepenseMockMvc.perform(get("/api/type-depenses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeDepense() throws Exception {
        // Initialize the database
        typeDepenseRepository.saveAndFlush(typeDepense);

        int databaseSizeBeforeUpdate = typeDepenseRepository.findAll().size();

        // Update the typeDepense
        TypeDepense updatedTypeDepense = typeDepenseRepository.findById(typeDepense.getId()).get();
        // Disconnect from session so that the updates on updatedTypeDepense are not directly saved in db
        em.detach(updatedTypeDepense);
        updatedTypeDepense
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);

        restTypeDepenseMockMvc.perform(put("/api/type-depenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeDepense)))
            .andExpect(status().isOk());

        // Validate the TypeDepense in the database
        List<TypeDepense> typeDepenseList = typeDepenseRepository.findAll();
        assertThat(typeDepenseList).hasSize(databaseSizeBeforeUpdate);
        TypeDepense testTypeDepense = typeDepenseList.get(typeDepenseList.size() - 1);
        assertThat(testTypeDepense.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTypeDepense.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeDepense() throws Exception {
        int databaseSizeBeforeUpdate = typeDepenseRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeDepenseMockMvc.perform(put("/api/type-depenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeDepense)))
            .andExpect(status().isBadRequest());

        // Validate the TypeDepense in the database
        List<TypeDepense> typeDepenseList = typeDepenseRepository.findAll();
        assertThat(typeDepenseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeDepense() throws Exception {
        // Initialize the database
        typeDepenseRepository.saveAndFlush(typeDepense);

        int databaseSizeBeforeDelete = typeDepenseRepository.findAll().size();

        // Delete the typeDepense
        restTypeDepenseMockMvc.perform(delete("/api/type-depenses/{id}", typeDepense.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeDepense> typeDepenseList = typeDepenseRepository.findAll();
        assertThat(typeDepenseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
