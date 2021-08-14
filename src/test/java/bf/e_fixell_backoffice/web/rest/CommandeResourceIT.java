package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.Commande;
import bf.e_fixell_backoffice.repository.CommandeRepository;

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
 * Integration tests for the {@link CommandeResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CommandeResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final BigDecimal DEFAULT_SOMME = new BigDecimal(1);
    private static final BigDecimal UPDATED_SOMME = new BigDecimal(2);

    private static final Instant DEFAULT_DATE_LIVRAISON_PREVU = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_LIVRAISON_PREVU = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Etat DEFAULT_ETAT = Etat.ANNULER;
    private static final Etat UPDATED_ETAT = Etat.PROVISOIRE;

    private static final String DEFAULT_MOTIF = "AAAAAAAAAA";
    private static final String UPDATED_MOTIF = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AVANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_AVANCE = new BigDecimal(2);

    private static final Boolean DEFAULT_AVANCE_EN_PERCENT = false;
    private static final Boolean UPDATED_AVANCE_EN_PERCENT = true;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommandeMockMvc;

    private Commande commande;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Commande createEntity(EntityManager em) {
        Commande commande = new Commande()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .date(DEFAULT_DATE)
            .somme(DEFAULT_SOMME)
            .dateLivraisonPrevu(DEFAULT_DATE_LIVRAISON_PREVU)
            .etat(DEFAULT_ETAT)
            .motif(DEFAULT_MOTIF)
            .avance(DEFAULT_AVANCE)
            .avanceEnPercent(DEFAULT_AVANCE_EN_PERCENT);
        return commande;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Commande createUpdatedEntity(EntityManager em) {
        Commande commande = new Commande()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .date(UPDATED_DATE)
            .somme(UPDATED_SOMME)
            .dateLivraisonPrevu(UPDATED_DATE_LIVRAISON_PREVU)
            .etat(UPDATED_ETAT)
            .motif(UPDATED_MOTIF)
            .avance(UPDATED_AVANCE)
            .avanceEnPercent(UPDATED_AVANCE_EN_PERCENT);
        return commande;
    }

    @BeforeEach
    public void initTest() {
        commande = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommande() throws Exception {
        int databaseSizeBeforeCreate = commandeRepository.findAll().size();
        // Create the Commande
        restCommandeMockMvc.perform(post("/api/commandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commande)))
            .andExpect(status().isCreated());

        // Validate the Commande in the database
        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeCreate + 1);
        Commande testCommande = commandeList.get(commandeList.size() - 1);
        assertThat(testCommande.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCommande.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testCommande.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testCommande.getSomme()).isEqualTo(DEFAULT_SOMME);
        assertThat(testCommande.getDateLivraisonPrevu()).isEqualTo(DEFAULT_DATE_LIVRAISON_PREVU);
        assertThat(testCommande.getEtat()).isEqualTo(DEFAULT_ETAT);
        assertThat(testCommande.getMotif()).isEqualTo(DEFAULT_MOTIF);
        assertThat(testCommande.getAvance()).isEqualTo(DEFAULT_AVANCE);
        assertThat(testCommande.isAvanceEnPercent()).isEqualTo(DEFAULT_AVANCE_EN_PERCENT);
    }

    @Test
    @Transactional
    public void createCommandeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commandeRepository.findAll().size();

        // Create the Commande with an existing ID
        commande.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommandeMockMvc.perform(post("/api/commandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commande)))
            .andExpect(status().isBadRequest());

        // Validate the Commande in the database
        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCommandes() throws Exception {
        // Initialize the database
        commandeRepository.saveAndFlush(commande);

        // Get all the commandeList
        restCommandeMockMvc.perform(get("/api/commandes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commande.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].somme").value(hasItem(DEFAULT_SOMME.intValue())))
            .andExpect(jsonPath("$.[*].dateLivraisonPrevu").value(hasItem(DEFAULT_DATE_LIVRAISON_PREVU.toString())))
            .andExpect(jsonPath("$.[*].etat").value(hasItem(DEFAULT_ETAT.toString())))
            .andExpect(jsonPath("$.[*].motif").value(hasItem(DEFAULT_MOTIF)))
            .andExpect(jsonPath("$.[*].avance").value(hasItem(DEFAULT_AVANCE.intValue())))
            .andExpect(jsonPath("$.[*].avanceEnPercent").value(hasItem(DEFAULT_AVANCE_EN_PERCENT.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCommande() throws Exception {
        // Initialize the database
        commandeRepository.saveAndFlush(commande);

        // Get the commande
        restCommandeMockMvc.perform(get("/api/commandes/{id}", commande.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(commande.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.somme").value(DEFAULT_SOMME.intValue()))
            .andExpect(jsonPath("$.dateLivraisonPrevu").value(DEFAULT_DATE_LIVRAISON_PREVU.toString()))
            .andExpect(jsonPath("$.etat").value(DEFAULT_ETAT.toString()))
            .andExpect(jsonPath("$.motif").value(DEFAULT_MOTIF))
            .andExpect(jsonPath("$.avance").value(DEFAULT_AVANCE.intValue()))
            .andExpect(jsonPath("$.avanceEnPercent").value(DEFAULT_AVANCE_EN_PERCENT.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCommande() throws Exception {
        // Get the commande
        restCommandeMockMvc.perform(get("/api/commandes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommande() throws Exception {
        // Initialize the database
        commandeRepository.saveAndFlush(commande);

        int databaseSizeBeforeUpdate = commandeRepository.findAll().size();

        // Update the commande
        Commande updatedCommande = commandeRepository.findById(commande.getId()).get();
        // Disconnect from session so that the updates on updatedCommande are not directly saved in db
        em.detach(updatedCommande);
        updatedCommande
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .date(UPDATED_DATE)
            .somme(UPDATED_SOMME)
            .dateLivraisonPrevu(UPDATED_DATE_LIVRAISON_PREVU)
            .etat(UPDATED_ETAT)
            .motif(UPDATED_MOTIF)
            .avance(UPDATED_AVANCE)
            .avanceEnPercent(UPDATED_AVANCE_EN_PERCENT);

        restCommandeMockMvc.perform(put("/api/commandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCommande)))
            .andExpect(status().isOk());

        // Validate the Commande in the database
        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeUpdate);
        Commande testCommande = commandeList.get(commandeList.size() - 1);
        assertThat(testCommande.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCommande.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testCommande.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testCommande.getSomme()).isEqualTo(UPDATED_SOMME);
        assertThat(testCommande.getDateLivraisonPrevu()).isEqualTo(UPDATED_DATE_LIVRAISON_PREVU);
        assertThat(testCommande.getEtat()).isEqualTo(UPDATED_ETAT);
        assertThat(testCommande.getMotif()).isEqualTo(UPDATED_MOTIF);
        assertThat(testCommande.getAvance()).isEqualTo(UPDATED_AVANCE);
        assertThat(testCommande.isAvanceEnPercent()).isEqualTo(UPDATED_AVANCE_EN_PERCENT);
    }

    @Test
    @Transactional
    public void updateNonExistingCommande() throws Exception {
        int databaseSizeBeforeUpdate = commandeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommandeMockMvc.perform(put("/api/commandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commande)))
            .andExpect(status().isBadRequest());

        // Validate the Commande in the database
        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommande() throws Exception {
        // Initialize the database
        commandeRepository.saveAndFlush(commande);

        int databaseSizeBeforeDelete = commandeRepository.findAll().size();

        // Delete the commande
        restCommandeMockMvc.perform(delete("/api/commandes/{id}", commande.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Commande> commandeList = commandeRepository.findAll();
        assertThat(commandeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
