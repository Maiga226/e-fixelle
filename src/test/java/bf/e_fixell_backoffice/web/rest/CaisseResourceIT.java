package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.Caisse;
import bf.e_fixell_backoffice.repository.CaisseRepository;

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

import bf.e_fixell_backoffice.domain.enumeration.Statut;
import bf.e_fixell_backoffice.domain.enumeration.TypeCaisse;
/**
 * Integration tests for the {@link CaisseResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CaisseResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SOMME_MIN = new BigDecimal(1);
    private static final BigDecimal UPDATED_SOMME_MIN = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SOMME_MAX = new BigDecimal(1);
    private static final BigDecimal UPDATED_SOMME_MAX = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SOMME = new BigDecimal(1);
    private static final BigDecimal UPDATED_SOMME = new BigDecimal(2);

    private static final Statut DEFAULT_STATUT = Statut.OUVERT;
    private static final Statut UPDATED_STATUT = Statut.FERME;

    private static final TypeCaisse DEFAULT_TYPE_CAISSE = TypeCaisse.SECONDAIRE;
    private static final TypeCaisse UPDATED_TYPE_CAISSE = TypeCaisse.PRINCIPALE;

    @Autowired
    private CaisseRepository caisseRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCaisseMockMvc;

    private Caisse caisse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Caisse createEntity(EntityManager em) {
        Caisse caisse = new Caisse()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .sommeMin(DEFAULT_SOMME_MIN)
            .sommeMax(DEFAULT_SOMME_MAX)
            .somme(DEFAULT_SOMME)
            .statut(DEFAULT_STATUT)
            .typeCaisse(DEFAULT_TYPE_CAISSE);
        return caisse;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Caisse createUpdatedEntity(EntityManager em) {
        Caisse caisse = new Caisse()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .sommeMin(UPDATED_SOMME_MIN)
            .sommeMax(UPDATED_SOMME_MAX)
            .somme(UPDATED_SOMME)
            .statut(UPDATED_STATUT)
            .typeCaisse(UPDATED_TYPE_CAISSE);
        return caisse;
    }

    @BeforeEach
    public void initTest() {
        caisse = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaisse() throws Exception {
        int databaseSizeBeforeCreate = caisseRepository.findAll().size();
        // Create the Caisse
        restCaisseMockMvc.perform(post("/api/caisses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caisse)))
            .andExpect(status().isCreated());

        // Validate the Caisse in the database
        List<Caisse> caisseList = caisseRepository.findAll();
        assertThat(caisseList).hasSize(databaseSizeBeforeCreate + 1);
        Caisse testCaisse = caisseList.get(caisseList.size() - 1);
        assertThat(testCaisse.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCaisse.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testCaisse.getSommeMin()).isEqualTo(DEFAULT_SOMME_MIN);
        assertThat(testCaisse.getSommeMax()).isEqualTo(DEFAULT_SOMME_MAX);
        assertThat(testCaisse.getSomme()).isEqualTo(DEFAULT_SOMME);
        assertThat(testCaisse.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testCaisse.getTypeCaisse()).isEqualTo(DEFAULT_TYPE_CAISSE);
    }

    @Test
    @Transactional
    public void createCaisseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caisseRepository.findAll().size();

        // Create the Caisse with an existing ID
        caisse.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaisseMockMvc.perform(post("/api/caisses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caisse)))
            .andExpect(status().isBadRequest());

        // Validate the Caisse in the database
        List<Caisse> caisseList = caisseRepository.findAll();
        assertThat(caisseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCaisses() throws Exception {
        // Initialize the database
        caisseRepository.saveAndFlush(caisse);

        // Get all the caisseList
        restCaisseMockMvc.perform(get("/api/caisses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caisse.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].sommeMin").value(hasItem(DEFAULT_SOMME_MIN.intValue())))
            .andExpect(jsonPath("$.[*].sommeMax").value(hasItem(DEFAULT_SOMME_MAX.intValue())))
            .andExpect(jsonPath("$.[*].somme").value(hasItem(DEFAULT_SOMME.intValue())))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT.toString())))
            .andExpect(jsonPath("$.[*].typeCaisse").value(hasItem(DEFAULT_TYPE_CAISSE.toString())));
    }
    
    @Test
    @Transactional
    public void getCaisse() throws Exception {
        // Initialize the database
        caisseRepository.saveAndFlush(caisse);

        // Get the caisse
        restCaisseMockMvc.perform(get("/api/caisses/{id}", caisse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(caisse.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.sommeMin").value(DEFAULT_SOMME_MIN.intValue()))
            .andExpect(jsonPath("$.sommeMax").value(DEFAULT_SOMME_MAX.intValue()))
            .andExpect(jsonPath("$.somme").value(DEFAULT_SOMME.intValue()))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT.toString()))
            .andExpect(jsonPath("$.typeCaisse").value(DEFAULT_TYPE_CAISSE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCaisse() throws Exception {
        // Get the caisse
        restCaisseMockMvc.perform(get("/api/caisses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaisse() throws Exception {
        // Initialize the database
        caisseRepository.saveAndFlush(caisse);

        int databaseSizeBeforeUpdate = caisseRepository.findAll().size();

        // Update the caisse
        Caisse updatedCaisse = caisseRepository.findById(caisse.getId()).get();
        // Disconnect from session so that the updates on updatedCaisse are not directly saved in db
        em.detach(updatedCaisse);
        updatedCaisse
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .sommeMin(UPDATED_SOMME_MIN)
            .sommeMax(UPDATED_SOMME_MAX)
            .somme(UPDATED_SOMME)
            .statut(UPDATED_STATUT)
            .typeCaisse(UPDATED_TYPE_CAISSE);

        restCaisseMockMvc.perform(put("/api/caisses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCaisse)))
            .andExpect(status().isOk());

        // Validate the Caisse in the database
        List<Caisse> caisseList = caisseRepository.findAll();
        assertThat(caisseList).hasSize(databaseSizeBeforeUpdate);
        Caisse testCaisse = caisseList.get(caisseList.size() - 1);
        assertThat(testCaisse.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCaisse.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testCaisse.getSommeMin()).isEqualTo(UPDATED_SOMME_MIN);
        assertThat(testCaisse.getSommeMax()).isEqualTo(UPDATED_SOMME_MAX);
        assertThat(testCaisse.getSomme()).isEqualTo(UPDATED_SOMME);
        assertThat(testCaisse.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testCaisse.getTypeCaisse()).isEqualTo(UPDATED_TYPE_CAISSE);
    }

    @Test
    @Transactional
    public void updateNonExistingCaisse() throws Exception {
        int databaseSizeBeforeUpdate = caisseRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaisseMockMvc.perform(put("/api/caisses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caisse)))
            .andExpect(status().isBadRequest());

        // Validate the Caisse in the database
        List<Caisse> caisseList = caisseRepository.findAll();
        assertThat(caisseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaisse() throws Exception {
        // Initialize the database
        caisseRepository.saveAndFlush(caisse);

        int databaseSizeBeforeDelete = caisseRepository.findAll().size();

        // Delete the caisse
        restCaisseMockMvc.perform(delete("/api/caisses/{id}", caisse.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Caisse> caisseList = caisseRepository.findAll();
        assertThat(caisseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
