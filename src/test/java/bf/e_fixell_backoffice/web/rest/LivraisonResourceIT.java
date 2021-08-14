package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.Livraison;
import bf.e_fixell_backoffice.repository.LivraisonRepository;

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

import bf.e_fixell_backoffice.domain.enumeration.Etat;
/**
 * Integration tests for the {@link LivraisonResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LivraisonResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final BigDecimal DEFAULT_SOMME = new BigDecimal(1);
    private static final BigDecimal UPDATED_SOMME = new BigDecimal(2);

    private static final Etat DEFAULT_ETAT = Etat.ANNULER;
    private static final Etat UPDATED_ETAT = Etat.PROVISOIRE;

    private static final String DEFAULT_MOTIF = "AAAAAAAAAA";
    private static final String UPDATED_MOTIF = "BBBBBBBBBB";

    @Autowired
    private LivraisonRepository livraisonRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLivraisonMockMvc;

    private Livraison livraison;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Livraison createEntity(EntityManager em) {
        Livraison livraison = new Livraison()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .date(DEFAULT_DATE)
            .somme(DEFAULT_SOMME)
            .etat(DEFAULT_ETAT)
            .motif(DEFAULT_MOTIF);
        return livraison;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Livraison createUpdatedEntity(EntityManager em) {
        Livraison livraison = new Livraison()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .date(UPDATED_DATE)
            .somme(UPDATED_SOMME)
            .etat(UPDATED_ETAT)
            .motif(UPDATED_MOTIF);
        return livraison;
    }

    @BeforeEach
    public void initTest() {
        livraison = createEntity(em);
    }

    @Test
    @Transactional
    public void createLivraison() throws Exception {
        int databaseSizeBeforeCreate = livraisonRepository.findAll().size();
        // Create the Livraison
        restLivraisonMockMvc.perform(post("/api/livraisons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(livraison)))
            .andExpect(status().isCreated());

        // Validate the Livraison in the database
        List<Livraison> livraisonList = livraisonRepository.findAll();
        assertThat(livraisonList).hasSize(databaseSizeBeforeCreate + 1);
        Livraison testLivraison = livraisonList.get(livraisonList.size() - 1);
        assertThat(testLivraison.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testLivraison.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testLivraison.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testLivraison.getSomme()).isEqualTo(DEFAULT_SOMME);
        assertThat(testLivraison.getEtat()).isEqualTo(DEFAULT_ETAT);
        assertThat(testLivraison.getMotif()).isEqualTo(DEFAULT_MOTIF);
    }

    @Test
    @Transactional
    public void createLivraisonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = livraisonRepository.findAll().size();

        // Create the Livraison with an existing ID
        livraison.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLivraisonMockMvc.perform(post("/api/livraisons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(livraison)))
            .andExpect(status().isBadRequest());

        // Validate the Livraison in the database
        List<Livraison> livraisonList = livraisonRepository.findAll();
        assertThat(livraisonList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLivraisons() throws Exception {
        // Initialize the database
        livraisonRepository.saveAndFlush(livraison);

        // Get all the livraisonList
        restLivraisonMockMvc.perform(get("/api/livraisons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(livraison.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].somme").value(hasItem(DEFAULT_SOMME.intValue())))
            .andExpect(jsonPath("$.[*].etat").value(hasItem(DEFAULT_ETAT.toString())))
            .andExpect(jsonPath("$.[*].motif").value(hasItem(DEFAULT_MOTIF)));
    }
    
    @Test
    @Transactional
    public void getLivraison() throws Exception {
        // Initialize the database
        livraisonRepository.saveAndFlush(livraison);

        // Get the livraison
        restLivraisonMockMvc.perform(get("/api/livraisons/{id}", livraison.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(livraison.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.somme").value(DEFAULT_SOMME.intValue()))
            .andExpect(jsonPath("$.etat").value(DEFAULT_ETAT.toString()))
            .andExpect(jsonPath("$.motif").value(DEFAULT_MOTIF));
    }
    @Test
    @Transactional
    public void getNonExistingLivraison() throws Exception {
        // Get the livraison
        restLivraisonMockMvc.perform(get("/api/livraisons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLivraison() throws Exception {
        // Initialize the database
        livraisonRepository.saveAndFlush(livraison);

        int databaseSizeBeforeUpdate = livraisonRepository.findAll().size();

        // Update the livraison
        Livraison updatedLivraison = livraisonRepository.findById(livraison.getId()).get();
        // Disconnect from session so that the updates on updatedLivraison are not directly saved in db
        em.detach(updatedLivraison);
        updatedLivraison
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .date(UPDATED_DATE)
            .somme(UPDATED_SOMME)
            .etat(UPDATED_ETAT)
            .motif(UPDATED_MOTIF);

        restLivraisonMockMvc.perform(put("/api/livraisons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLivraison)))
            .andExpect(status().isOk());

        // Validate the Livraison in the database
        List<Livraison> livraisonList = livraisonRepository.findAll();
        assertThat(livraisonList).hasSize(databaseSizeBeforeUpdate);
        Livraison testLivraison = livraisonList.get(livraisonList.size() - 1);
        assertThat(testLivraison.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testLivraison.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testLivraison.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testLivraison.getSomme()).isEqualTo(UPDATED_SOMME);
        assertThat(testLivraison.getEtat()).isEqualTo(UPDATED_ETAT);
        assertThat(testLivraison.getMotif()).isEqualTo(UPDATED_MOTIF);
    }

    @Test
    @Transactional
    public void updateNonExistingLivraison() throws Exception {
        int databaseSizeBeforeUpdate = livraisonRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLivraisonMockMvc.perform(put("/api/livraisons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(livraison)))
            .andExpect(status().isBadRequest());

        // Validate the Livraison in the database
        List<Livraison> livraisonList = livraisonRepository.findAll();
        assertThat(livraisonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLivraison() throws Exception {
        // Initialize the database
        livraisonRepository.saveAndFlush(livraison);

        int databaseSizeBeforeDelete = livraisonRepository.findAll().size();

        // Delete the livraison
        restLivraisonMockMvc.perform(delete("/api/livraisons/{id}", livraison.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Livraison> livraisonList = livraisonRepository.findAll();
        assertThat(livraisonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
