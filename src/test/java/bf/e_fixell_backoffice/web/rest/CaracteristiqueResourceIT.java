package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.Caracteristique;
import bf.e_fixell_backoffice.repository.CaracteristiqueRepository;

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
 * Integration tests for the {@link CaracteristiqueResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CaracteristiqueResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_VALEUR = "AAAAAAAAAA";
    private static final String UPDATED_VALEUR = "BBBBBBBBBB";

    @Autowired
    private CaracteristiqueRepository caracteristiqueRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCaracteristiqueMockMvc;

    private Caracteristique caracteristique;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Caracteristique createEntity(EntityManager em) {
        Caracteristique caracteristique = new Caracteristique()
            .libelle(DEFAULT_LIBELLE)
            .valeur(DEFAULT_VALEUR);
        return caracteristique;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Caracteristique createUpdatedEntity(EntityManager em) {
        Caracteristique caracteristique = new Caracteristique()
            .libelle(UPDATED_LIBELLE)
            .valeur(UPDATED_VALEUR);
        return caracteristique;
    }

    @BeforeEach
    public void initTest() {
        caracteristique = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaracteristique() throws Exception {
        int databaseSizeBeforeCreate = caracteristiqueRepository.findAll().size();
        // Create the Caracteristique
        restCaracteristiqueMockMvc.perform(post("/api/caracteristiques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caracteristique)))
            .andExpect(status().isCreated());

        // Validate the Caracteristique in the database
        List<Caracteristique> caracteristiqueList = caracteristiqueRepository.findAll();
        assertThat(caracteristiqueList).hasSize(databaseSizeBeforeCreate + 1);
        Caracteristique testCaracteristique = caracteristiqueList.get(caracteristiqueList.size() - 1);
        assertThat(testCaracteristique.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testCaracteristique.getValeur()).isEqualTo(DEFAULT_VALEUR);
    }

    @Test
    @Transactional
    public void createCaracteristiqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caracteristiqueRepository.findAll().size();

        // Create the Caracteristique with an existing ID
        caracteristique.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaracteristiqueMockMvc.perform(post("/api/caracteristiques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caracteristique)))
            .andExpect(status().isBadRequest());

        // Validate the Caracteristique in the database
        List<Caracteristique> caracteristiqueList = caracteristiqueRepository.findAll();
        assertThat(caracteristiqueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCaracteristiques() throws Exception {
        // Initialize the database
        caracteristiqueRepository.saveAndFlush(caracteristique);

        // Get all the caracteristiqueList
        restCaracteristiqueMockMvc.perform(get("/api/caracteristiques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caracteristique.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].valeur").value(hasItem(DEFAULT_VALEUR)));
    }
    
    @Test
    @Transactional
    public void getCaracteristique() throws Exception {
        // Initialize the database
        caracteristiqueRepository.saveAndFlush(caracteristique);

        // Get the caracteristique
        restCaracteristiqueMockMvc.perform(get("/api/caracteristiques/{id}", caracteristique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(caracteristique.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.valeur").value(DEFAULT_VALEUR));
    }
    @Test
    @Transactional
    public void getNonExistingCaracteristique() throws Exception {
        // Get the caracteristique
        restCaracteristiqueMockMvc.perform(get("/api/caracteristiques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaracteristique() throws Exception {
        // Initialize the database
        caracteristiqueRepository.saveAndFlush(caracteristique);

        int databaseSizeBeforeUpdate = caracteristiqueRepository.findAll().size();

        // Update the caracteristique
        Caracteristique updatedCaracteristique = caracteristiqueRepository.findById(caracteristique.getId()).get();
        // Disconnect from session so that the updates on updatedCaracteristique are not directly saved in db
        em.detach(updatedCaracteristique);
        updatedCaracteristique
            .libelle(UPDATED_LIBELLE)
            .valeur(UPDATED_VALEUR);

        restCaracteristiqueMockMvc.perform(put("/api/caracteristiques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCaracteristique)))
            .andExpect(status().isOk());

        // Validate the Caracteristique in the database
        List<Caracteristique> caracteristiqueList = caracteristiqueRepository.findAll();
        assertThat(caracteristiqueList).hasSize(databaseSizeBeforeUpdate);
        Caracteristique testCaracteristique = caracteristiqueList.get(caracteristiqueList.size() - 1);
        assertThat(testCaracteristique.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testCaracteristique.getValeur()).isEqualTo(UPDATED_VALEUR);
    }

    @Test
    @Transactional
    public void updateNonExistingCaracteristique() throws Exception {
        int databaseSizeBeforeUpdate = caracteristiqueRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaracteristiqueMockMvc.perform(put("/api/caracteristiques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caracteristique)))
            .andExpect(status().isBadRequest());

        // Validate the Caracteristique in the database
        List<Caracteristique> caracteristiqueList = caracteristiqueRepository.findAll();
        assertThat(caracteristiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaracteristique() throws Exception {
        // Initialize the database
        caracteristiqueRepository.saveAndFlush(caracteristique);

        int databaseSizeBeforeDelete = caracteristiqueRepository.findAll().size();

        // Delete the caracteristique
        restCaracteristiqueMockMvc.perform(delete("/api/caracteristiques/{id}", caracteristique.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Caracteristique> caracteristiqueList = caracteristiqueRepository.findAll();
        assertThat(caracteristiqueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
