package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.SessionCaisse;
import bf.e_fixell_backoffice.repository.SessionCaisseRepository;

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
 * Integration tests for the {@link SessionCaisseResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SessionCaisseResourceIT {

    private static final Long DEFAULT_CODE = 1L;
    private static final Long UPDATED_CODE = 2L;

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_DEBUT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_DEBUT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_FIN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_FIN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final BigDecimal DEFAULT_SOMME_DEBUT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SOMME_DEBUT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SOMME_FIN = new BigDecimal(1);
    private static final BigDecimal UPDATED_SOMME_FIN = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DEPASSEMENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEPASSEMENT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MANQUANT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MANQUANT = new BigDecimal(2);

    private static final Statut DEFAULT_STATUT = Statut.OUVERT;
    private static final Statut UPDATED_STATUT = Statut.FERME;

    @Autowired
    private SessionCaisseRepository sessionCaisseRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSessionCaisseMockMvc;

    private SessionCaisse sessionCaisse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SessionCaisse createEntity(EntityManager em) {
        SessionCaisse sessionCaisse = new SessionCaisse()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN)
            .sommeDebut(DEFAULT_SOMME_DEBUT)
            .sommeFin(DEFAULT_SOMME_FIN)
            .depassement(DEFAULT_DEPASSEMENT)
            .manquant(DEFAULT_MANQUANT)
            .statut(DEFAULT_STATUT);
        return sessionCaisse;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SessionCaisse createUpdatedEntity(EntityManager em) {
        SessionCaisse sessionCaisse = new SessionCaisse()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .sommeDebut(UPDATED_SOMME_DEBUT)
            .sommeFin(UPDATED_SOMME_FIN)
            .depassement(UPDATED_DEPASSEMENT)
            .manquant(UPDATED_MANQUANT)
            .statut(UPDATED_STATUT);
        return sessionCaisse;
    }

    @BeforeEach
    public void initTest() {
        sessionCaisse = createEntity(em);
    }

    @Test
    @Transactional
    public void createSessionCaisse() throws Exception {
        int databaseSizeBeforeCreate = sessionCaisseRepository.findAll().size();
        // Create the SessionCaisse
        restSessionCaisseMockMvc.perform(post("/api/session-caisses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sessionCaisse)))
            .andExpect(status().isCreated());

        // Validate the SessionCaisse in the database
        List<SessionCaisse> sessionCaisseList = sessionCaisseRepository.findAll();
        assertThat(sessionCaisseList).hasSize(databaseSizeBeforeCreate + 1);
        SessionCaisse testSessionCaisse = sessionCaisseList.get(sessionCaisseList.size() - 1);
        assertThat(testSessionCaisse.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testSessionCaisse.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testSessionCaisse.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testSessionCaisse.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
        assertThat(testSessionCaisse.getSommeDebut()).isEqualTo(DEFAULT_SOMME_DEBUT);
        assertThat(testSessionCaisse.getSommeFin()).isEqualTo(DEFAULT_SOMME_FIN);
        assertThat(testSessionCaisse.getDepassement()).isEqualTo(DEFAULT_DEPASSEMENT);
        assertThat(testSessionCaisse.getManquant()).isEqualTo(DEFAULT_MANQUANT);
        assertThat(testSessionCaisse.getStatut()).isEqualTo(DEFAULT_STATUT);
    }

    @Test
    @Transactional
    public void createSessionCaisseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sessionCaisseRepository.findAll().size();

        // Create the SessionCaisse with an existing ID
        sessionCaisse.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSessionCaisseMockMvc.perform(post("/api/session-caisses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sessionCaisse)))
            .andExpect(status().isBadRequest());

        // Validate the SessionCaisse in the database
        List<SessionCaisse> sessionCaisseList = sessionCaisseRepository.findAll();
        assertThat(sessionCaisseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSessionCaisses() throws Exception {
        // Initialize the database
        sessionCaisseRepository.saveAndFlush(sessionCaisse);

        // Get all the sessionCaisseList
        restSessionCaisseMockMvc.perform(get("/api/session-caisses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sessionCaisse.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(DEFAULT_DATE_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(DEFAULT_DATE_FIN.toString())))
            .andExpect(jsonPath("$.[*].sommeDebut").value(hasItem(DEFAULT_SOMME_DEBUT.intValue())))
            .andExpect(jsonPath("$.[*].sommeFin").value(hasItem(DEFAULT_SOMME_FIN.intValue())))
            .andExpect(jsonPath("$.[*].depassement").value(hasItem(DEFAULT_DEPASSEMENT.intValue())))
            .andExpect(jsonPath("$.[*].manquant").value(hasItem(DEFAULT_MANQUANT.intValue())))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT.toString())));
    }
    
    @Test
    @Transactional
    public void getSessionCaisse() throws Exception {
        // Initialize the database
        sessionCaisseRepository.saveAndFlush(sessionCaisse);

        // Get the sessionCaisse
        restSessionCaisseMockMvc.perform(get("/api/session-caisses/{id}", sessionCaisse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sessionCaisse.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.dateDebut").value(DEFAULT_DATE_DEBUT.toString()))
            .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()))
            .andExpect(jsonPath("$.sommeDebut").value(DEFAULT_SOMME_DEBUT.intValue()))
            .andExpect(jsonPath("$.sommeFin").value(DEFAULT_SOMME_FIN.intValue()))
            .andExpect(jsonPath("$.depassement").value(DEFAULT_DEPASSEMENT.intValue()))
            .andExpect(jsonPath("$.manquant").value(DEFAULT_MANQUANT.intValue()))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSessionCaisse() throws Exception {
        // Get the sessionCaisse
        restSessionCaisseMockMvc.perform(get("/api/session-caisses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSessionCaisse() throws Exception {
        // Initialize the database
        sessionCaisseRepository.saveAndFlush(sessionCaisse);

        int databaseSizeBeforeUpdate = sessionCaisseRepository.findAll().size();

        // Update the sessionCaisse
        SessionCaisse updatedSessionCaisse = sessionCaisseRepository.findById(sessionCaisse.getId()).get();
        // Disconnect from session so that the updates on updatedSessionCaisse are not directly saved in db
        em.detach(updatedSessionCaisse);
        updatedSessionCaisse
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .sommeDebut(UPDATED_SOMME_DEBUT)
            .sommeFin(UPDATED_SOMME_FIN)
            .depassement(UPDATED_DEPASSEMENT)
            .manquant(UPDATED_MANQUANT)
            .statut(UPDATED_STATUT);

        restSessionCaisseMockMvc.perform(put("/api/session-caisses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSessionCaisse)))
            .andExpect(status().isOk());

        // Validate the SessionCaisse in the database
        List<SessionCaisse> sessionCaisseList = sessionCaisseRepository.findAll();
        assertThat(sessionCaisseList).hasSize(databaseSizeBeforeUpdate);
        SessionCaisse testSessionCaisse = sessionCaisseList.get(sessionCaisseList.size() - 1);
        assertThat(testSessionCaisse.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSessionCaisse.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testSessionCaisse.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testSessionCaisse.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testSessionCaisse.getSommeDebut()).isEqualTo(UPDATED_SOMME_DEBUT);
        assertThat(testSessionCaisse.getSommeFin()).isEqualTo(UPDATED_SOMME_FIN);
        assertThat(testSessionCaisse.getDepassement()).isEqualTo(UPDATED_DEPASSEMENT);
        assertThat(testSessionCaisse.getManquant()).isEqualTo(UPDATED_MANQUANT);
        assertThat(testSessionCaisse.getStatut()).isEqualTo(UPDATED_STATUT);
    }

    @Test
    @Transactional
    public void updateNonExistingSessionCaisse() throws Exception {
        int databaseSizeBeforeUpdate = sessionCaisseRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSessionCaisseMockMvc.perform(put("/api/session-caisses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sessionCaisse)))
            .andExpect(status().isBadRequest());

        // Validate the SessionCaisse in the database
        List<SessionCaisse> sessionCaisseList = sessionCaisseRepository.findAll();
        assertThat(sessionCaisseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSessionCaisse() throws Exception {
        // Initialize the database
        sessionCaisseRepository.saveAndFlush(sessionCaisse);

        int databaseSizeBeforeDelete = sessionCaisseRepository.findAll().size();

        // Delete the sessionCaisse
        restSessionCaisseMockMvc.perform(delete("/api/session-caisses/{id}", sessionCaisse.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SessionCaisse> sessionCaisseList = sessionCaisseRepository.findAll();
        assertThat(sessionCaisseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
