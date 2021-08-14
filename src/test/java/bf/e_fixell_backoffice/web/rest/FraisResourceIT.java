package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.Frais;
import bf.e_fixell_backoffice.repository.FraisRepository;

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
 * Integration tests for the {@link FraisResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FraisResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_VALEUR = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALEUR = new BigDecimal(2);

    @Autowired
    private FraisRepository fraisRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFraisMockMvc;

    private Frais frais;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Frais createEntity(EntityManager em) {
        Frais frais = new Frais()
            .libelle(DEFAULT_LIBELLE)
            .valeur(DEFAULT_VALEUR);
        return frais;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Frais createUpdatedEntity(EntityManager em) {
        Frais frais = new Frais()
            .libelle(UPDATED_LIBELLE)
            .valeur(UPDATED_VALEUR);
        return frais;
    }

    @BeforeEach
    public void initTest() {
        frais = createEntity(em);
    }

    @Test
    @Transactional
    public void createFrais() throws Exception {
        int databaseSizeBeforeCreate = fraisRepository.findAll().size();
        // Create the Frais
        restFraisMockMvc.perform(post("/api/frais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(frais)))
            .andExpect(status().isCreated());

        // Validate the Frais in the database
        List<Frais> fraisList = fraisRepository.findAll();
        assertThat(fraisList).hasSize(databaseSizeBeforeCreate + 1);
        Frais testFrais = fraisList.get(fraisList.size() - 1);
        assertThat(testFrais.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testFrais.getValeur()).isEqualTo(DEFAULT_VALEUR);
    }

    @Test
    @Transactional
    public void createFraisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fraisRepository.findAll().size();

        // Create the Frais with an existing ID
        frais.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFraisMockMvc.perform(post("/api/frais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(frais)))
            .andExpect(status().isBadRequest());

        // Validate the Frais in the database
        List<Frais> fraisList = fraisRepository.findAll();
        assertThat(fraisList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFrais() throws Exception {
        // Initialize the database
        fraisRepository.saveAndFlush(frais);

        // Get all the fraisList
        restFraisMockMvc.perform(get("/api/frais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(frais.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].valeur").value(hasItem(DEFAULT_VALEUR.intValue())));
    }
    
    @Test
    @Transactional
    public void getFrais() throws Exception {
        // Initialize the database
        fraisRepository.saveAndFlush(frais);

        // Get the frais
        restFraisMockMvc.perform(get("/api/frais/{id}", frais.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(frais.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.valeur").value(DEFAULT_VALEUR.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingFrais() throws Exception {
        // Get the frais
        restFraisMockMvc.perform(get("/api/frais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFrais() throws Exception {
        // Initialize the database
        fraisRepository.saveAndFlush(frais);

        int databaseSizeBeforeUpdate = fraisRepository.findAll().size();

        // Update the frais
        Frais updatedFrais = fraisRepository.findById(frais.getId()).get();
        // Disconnect from session so that the updates on updatedFrais are not directly saved in db
        em.detach(updatedFrais);
        updatedFrais
            .libelle(UPDATED_LIBELLE)
            .valeur(UPDATED_VALEUR);

        restFraisMockMvc.perform(put("/api/frais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFrais)))
            .andExpect(status().isOk());

        // Validate the Frais in the database
        List<Frais> fraisList = fraisRepository.findAll();
        assertThat(fraisList).hasSize(databaseSizeBeforeUpdate);
        Frais testFrais = fraisList.get(fraisList.size() - 1);
        assertThat(testFrais.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testFrais.getValeur()).isEqualTo(UPDATED_VALEUR);
    }

    @Test
    @Transactional
    public void updateNonExistingFrais() throws Exception {
        int databaseSizeBeforeUpdate = fraisRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFraisMockMvc.perform(put("/api/frais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(frais)))
            .andExpect(status().isBadRequest());

        // Validate the Frais in the database
        List<Frais> fraisList = fraisRepository.findAll();
        assertThat(fraisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFrais() throws Exception {
        // Initialize the database
        fraisRepository.saveAndFlush(frais);

        int databaseSizeBeforeDelete = fraisRepository.findAll().size();

        // Delete the frais
        restFraisMockMvc.perform(delete("/api/frais/{id}", frais.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Frais> fraisList = fraisRepository.findAll();
        assertThat(fraisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
