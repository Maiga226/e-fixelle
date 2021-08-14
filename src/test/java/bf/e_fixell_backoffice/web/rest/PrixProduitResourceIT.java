package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.PrixProduit;
import bf.e_fixell_backoffice.repository.PrixProduitRepository;

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

import bf.e_fixell_backoffice.domain.enumeration.Statut;
/**
 * Integration tests for the {@link PrixProduitResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PrixProduitResourceIT {

    private static final Instant DEFAULT_DATE_DEBUT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_DEBUT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_FIN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_FIN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final BigDecimal DEFAULT_PRIX = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRIX = new BigDecimal(2);

    private static final Statut DEFAULT_STATUT = Statut.OUVERT;
    private static final Statut UPDATED_STATUT = Statut.FERME;

    @Autowired
    private PrixProduitRepository prixProduitRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrixProduitMockMvc;

    private PrixProduit prixProduit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrixProduit createEntity(EntityManager em) {
        PrixProduit prixProduit = new PrixProduit()
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN)
            .prix(DEFAULT_PRIX)
            .statut(DEFAULT_STATUT);
        return prixProduit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrixProduit createUpdatedEntity(EntityManager em) {
        PrixProduit prixProduit = new PrixProduit()
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .prix(UPDATED_PRIX)
            .statut(UPDATED_STATUT);
        return prixProduit;
    }

    @BeforeEach
    public void initTest() {
        prixProduit = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrixProduit() throws Exception {
        int databaseSizeBeforeCreate = prixProduitRepository.findAll().size();
        // Create the PrixProduit
        restPrixProduitMockMvc.perform(post("/api/prix-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prixProduit)))
            .andExpect(status().isCreated());

        // Validate the PrixProduit in the database
        List<PrixProduit> prixProduitList = prixProduitRepository.findAll();
        assertThat(prixProduitList).hasSize(databaseSizeBeforeCreate + 1);
        PrixProduit testPrixProduit = prixProduitList.get(prixProduitList.size() - 1);
        assertThat(testPrixProduit.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testPrixProduit.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
        assertThat(testPrixProduit.getPrix()).isEqualTo(DEFAULT_PRIX);
        assertThat(testPrixProduit.getStatut()).isEqualTo(DEFAULT_STATUT);
    }

    @Test
    @Transactional
    public void createPrixProduitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = prixProduitRepository.findAll().size();

        // Create the PrixProduit with an existing ID
        prixProduit.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrixProduitMockMvc.perform(post("/api/prix-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prixProduit)))
            .andExpect(status().isBadRequest());

        // Validate the PrixProduit in the database
        List<PrixProduit> prixProduitList = prixProduitRepository.findAll();
        assertThat(prixProduitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPrixProduits() throws Exception {
        // Initialize the database
        prixProduitRepository.saveAndFlush(prixProduit);

        // Get all the prixProduitList
        restPrixProduitMockMvc.perform(get("/api/prix-produits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prixProduit.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(DEFAULT_DATE_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(DEFAULT_DATE_FIN.toString())))
            .andExpect(jsonPath("$.[*].prix").value(hasItem(DEFAULT_PRIX.intValue())))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT.toString())));
    }
    
    @Test
    @Transactional
    public void getPrixProduit() throws Exception {
        // Initialize the database
        prixProduitRepository.saveAndFlush(prixProduit);

        // Get the prixProduit
        restPrixProduitMockMvc.perform(get("/api/prix-produits/{id}", prixProduit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(prixProduit.getId().intValue()))
            .andExpect(jsonPath("$.dateDebut").value(DEFAULT_DATE_DEBUT.toString()))
            .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()))
            .andExpect(jsonPath("$.prix").value(DEFAULT_PRIX.intValue()))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPrixProduit() throws Exception {
        // Get the prixProduit
        restPrixProduitMockMvc.perform(get("/api/prix-produits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrixProduit() throws Exception {
        // Initialize the database
        prixProduitRepository.saveAndFlush(prixProduit);

        int databaseSizeBeforeUpdate = prixProduitRepository.findAll().size();

        // Update the prixProduit
        PrixProduit updatedPrixProduit = prixProduitRepository.findById(prixProduit.getId()).get();
        // Disconnect from session so that the updates on updatedPrixProduit are not directly saved in db
        em.detach(updatedPrixProduit);
        updatedPrixProduit
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .prix(UPDATED_PRIX)
            .statut(UPDATED_STATUT);

        restPrixProduitMockMvc.perform(put("/api/prix-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPrixProduit)))
            .andExpect(status().isOk());

        // Validate the PrixProduit in the database
        List<PrixProduit> prixProduitList = prixProduitRepository.findAll();
        assertThat(prixProduitList).hasSize(databaseSizeBeforeUpdate);
        PrixProduit testPrixProduit = prixProduitList.get(prixProduitList.size() - 1);
        assertThat(testPrixProduit.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testPrixProduit.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testPrixProduit.getPrix()).isEqualTo(UPDATED_PRIX);
        assertThat(testPrixProduit.getStatut()).isEqualTo(UPDATED_STATUT);
    }

    @Test
    @Transactional
    public void updateNonExistingPrixProduit() throws Exception {
        int databaseSizeBeforeUpdate = prixProduitRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrixProduitMockMvc.perform(put("/api/prix-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prixProduit)))
            .andExpect(status().isBadRequest());

        // Validate the PrixProduit in the database
        List<PrixProduit> prixProduitList = prixProduitRepository.findAll();
        assertThat(prixProduitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrixProduit() throws Exception {
        // Initialize the database
        prixProduitRepository.saveAndFlush(prixProduit);

        int databaseSizeBeforeDelete = prixProduitRepository.findAll().size();

        // Delete the prixProduit
        restPrixProduitMockMvc.perform(delete("/api/prix-produits/{id}", prixProduit.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PrixProduit> prixProduitList = prixProduitRepository.findAll();
        assertThat(prixProduitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
