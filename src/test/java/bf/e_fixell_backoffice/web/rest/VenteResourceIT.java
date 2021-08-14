package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.Vente;
import bf.e_fixell_backoffice.repository.VenteRepository;

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

/**
 * Integration tests for the {@link VenteResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VenteResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final BigDecimal DEFAULT_MONTANT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_RESTE = new BigDecimal(1);
    private static final BigDecimal UPDATED_RESTE = new BigDecimal(2);

    private static final Boolean DEFAULT_SOLDER = false;
    private static final Boolean UPDATED_SOLDER = true;

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVenteMockMvc;

    private Vente vente;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vente createEntity(EntityManager em) {
        Vente vente = new Vente()
            .libelle(DEFAULT_LIBELLE)
            .code(DEFAULT_CODE)
            .date(DEFAULT_DATE)
            .montant(DEFAULT_MONTANT)
            .reste(DEFAULT_RESTE)
            .solder(DEFAULT_SOLDER);
        return vente;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vente createUpdatedEntity(EntityManager em) {
        Vente vente = new Vente()
            .libelle(UPDATED_LIBELLE)
            .code(UPDATED_CODE)
            .date(UPDATED_DATE)
            .montant(UPDATED_MONTANT)
            .reste(UPDATED_RESTE)
            .solder(UPDATED_SOLDER);
        return vente;
    }

    @BeforeEach
    public void initTest() {
        vente = createEntity(em);
    }

    @Test
    @Transactional
    public void createVente() throws Exception {
        int databaseSizeBeforeCreate = venteRepository.findAll().size();
        // Create the Vente
        restVenteMockMvc.perform(post("/api/ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vente)))
            .andExpect(status().isCreated());

        // Validate the Vente in the database
        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeCreate + 1);
        Vente testVente = venteList.get(venteList.size() - 1);
        assertThat(testVente.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testVente.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testVente.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testVente.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testVente.getReste()).isEqualTo(DEFAULT_RESTE);
        assertThat(testVente.isSolder()).isEqualTo(DEFAULT_SOLDER);
    }

    @Test
    @Transactional
    public void createVenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = venteRepository.findAll().size();

        // Create the Vente with an existing ID
        vente.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVenteMockMvc.perform(post("/api/ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vente)))
            .andExpect(status().isBadRequest());

        // Validate the Vente in the database
        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllVentes() throws Exception {
        // Initialize the database
        venteRepository.saveAndFlush(vente);

        // Get all the venteList
        restVenteMockMvc.perform(get("/api/ventes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vente.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].reste").value(hasItem(DEFAULT_RESTE.intValue())))
            .andExpect(jsonPath("$.[*].solder").value(hasItem(DEFAULT_SOLDER.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getVente() throws Exception {
        // Initialize the database
        venteRepository.saveAndFlush(vente);

        // Get the vente
        restVenteMockMvc.perform(get("/api/ventes/{id}", vente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vente.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.intValue()))
            .andExpect(jsonPath("$.reste").value(DEFAULT_RESTE.intValue()))
            .andExpect(jsonPath("$.solder").value(DEFAULT_SOLDER.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingVente() throws Exception {
        // Get the vente
        restVenteMockMvc.perform(get("/api/ventes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVente() throws Exception {
        // Initialize the database
        venteRepository.saveAndFlush(vente);

        int databaseSizeBeforeUpdate = venteRepository.findAll().size();

        // Update the vente
        Vente updatedVente = venteRepository.findById(vente.getId()).get();
        // Disconnect from session so that the updates on updatedVente are not directly saved in db
        em.detach(updatedVente);
        updatedVente
            .libelle(UPDATED_LIBELLE)
            .code(UPDATED_CODE)
            .date(UPDATED_DATE)
            .montant(UPDATED_MONTANT)
            .reste(UPDATED_RESTE)
            .solder(UPDATED_SOLDER);

        restVenteMockMvc.perform(put("/api/ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedVente)))
            .andExpect(status().isOk());

        // Validate the Vente in the database
        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeUpdate);
        Vente testVente = venteList.get(venteList.size() - 1);
        assertThat(testVente.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testVente.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testVente.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testVente.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testVente.getReste()).isEqualTo(UPDATED_RESTE);
        assertThat(testVente.isSolder()).isEqualTo(UPDATED_SOLDER);
    }

    @Test
    @Transactional
    public void updateNonExistingVente() throws Exception {
        int databaseSizeBeforeUpdate = venteRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVenteMockMvc.perform(put("/api/ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vente)))
            .andExpect(status().isBadRequest());

        // Validate the Vente in the database
        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVente() throws Exception {
        // Initialize the database
        venteRepository.saveAndFlush(vente);

        int databaseSizeBeforeDelete = venteRepository.findAll().size();

        // Delete the vente
        restVenteMockMvc.perform(delete("/api/ventes/{id}", vente.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
