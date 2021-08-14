package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.Fournisseur;
import bf.e_fixell_backoffice.repository.FournisseurRepository;

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

import bf.e_fixell_backoffice.domain.enumeration.TypePersonne;
/**
 * Integration tests for the {@link FournisseurResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FournisseurResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_RAISON_SOCIAL = "AAAAAAAAAA";
    private static final String UPDATED_RAISON_SOCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_RUE = "AAAAAAAAAA";
    private static final String UPDATED_RUE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_FIXE = "AAAAAAAAAA";
    private static final String UPDATED_FIXE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_POSTALE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_POSTALE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_RESEAU_SOCIAL = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_RESEAU_SOCIAL = "BBBBBBBBBB";

    private static final TypePersonne DEFAULT_TYPE_PERSONNE = TypePersonne.MORALE;
    private static final TypePersonne UPDATED_TYPE_PERSONNE = TypePersonne.PHYSIQUE;

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFournisseurMockMvc;

    private Fournisseur fournisseur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fournisseur createEntity(EntityManager em) {
        Fournisseur fournisseur = new Fournisseur()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .raisonSocial(DEFAULT_RAISON_SOCIAL)
            .rue(DEFAULT_RUE)
            .telephone(DEFAULT_TELEPHONE)
            .fixe(DEFAULT_FIXE)
            .codePostale(DEFAULT_CODE_POSTALE)
            .numeroReseauSocial(DEFAULT_NUMERO_RESEAU_SOCIAL)
            .typePersonne(DEFAULT_TYPE_PERSONNE);
        return fournisseur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fournisseur createUpdatedEntity(EntityManager em) {
        Fournisseur fournisseur = new Fournisseur()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .raisonSocial(UPDATED_RAISON_SOCIAL)
            .rue(UPDATED_RUE)
            .telephone(UPDATED_TELEPHONE)
            .fixe(UPDATED_FIXE)
            .codePostale(UPDATED_CODE_POSTALE)
            .numeroReseauSocial(UPDATED_NUMERO_RESEAU_SOCIAL)
            .typePersonne(UPDATED_TYPE_PERSONNE);
        return fournisseur;
    }

    @BeforeEach
    public void initTest() {
        fournisseur = createEntity(em);
    }

    @Test
    @Transactional
    public void createFournisseur() throws Exception {
        int databaseSizeBeforeCreate = fournisseurRepository.findAll().size();
        // Create the Fournisseur
        restFournisseurMockMvc.perform(post("/api/fournisseurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fournisseur)))
            .andExpect(status().isCreated());

        // Validate the Fournisseur in the database
        List<Fournisseur> fournisseurList = fournisseurRepository.findAll();
        assertThat(fournisseurList).hasSize(databaseSizeBeforeCreate + 1);
        Fournisseur testFournisseur = fournisseurList.get(fournisseurList.size() - 1);
        assertThat(testFournisseur.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testFournisseur.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testFournisseur.getRaisonSocial()).isEqualTo(DEFAULT_RAISON_SOCIAL);
        assertThat(testFournisseur.getRue()).isEqualTo(DEFAULT_RUE);
        assertThat(testFournisseur.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testFournisseur.getFixe()).isEqualTo(DEFAULT_FIXE);
        assertThat(testFournisseur.getCodePostale()).isEqualTo(DEFAULT_CODE_POSTALE);
        assertThat(testFournisseur.getNumeroReseauSocial()).isEqualTo(DEFAULT_NUMERO_RESEAU_SOCIAL);
        assertThat(testFournisseur.getTypePersonne()).isEqualTo(DEFAULT_TYPE_PERSONNE);
    }

    @Test
    @Transactional
    public void createFournisseurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fournisseurRepository.findAll().size();

        // Create the Fournisseur with an existing ID
        fournisseur.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFournisseurMockMvc.perform(post("/api/fournisseurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fournisseur)))
            .andExpect(status().isBadRequest());

        // Validate the Fournisseur in the database
        List<Fournisseur> fournisseurList = fournisseurRepository.findAll();
        assertThat(fournisseurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFournisseurs() throws Exception {
        // Initialize the database
        fournisseurRepository.saveAndFlush(fournisseur);

        // Get all the fournisseurList
        restFournisseurMockMvc.perform(get("/api/fournisseurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fournisseur.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].raisonSocial").value(hasItem(DEFAULT_RAISON_SOCIAL)))
            .andExpect(jsonPath("$.[*].rue").value(hasItem(DEFAULT_RUE)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].fixe").value(hasItem(DEFAULT_FIXE)))
            .andExpect(jsonPath("$.[*].codePostale").value(hasItem(DEFAULT_CODE_POSTALE)))
            .andExpect(jsonPath("$.[*].numeroReseauSocial").value(hasItem(DEFAULT_NUMERO_RESEAU_SOCIAL)))
            .andExpect(jsonPath("$.[*].typePersonne").value(hasItem(DEFAULT_TYPE_PERSONNE.toString())));
    }
    
    @Test
    @Transactional
    public void getFournisseur() throws Exception {
        // Initialize the database
        fournisseurRepository.saveAndFlush(fournisseur);

        // Get the fournisseur
        restFournisseurMockMvc.perform(get("/api/fournisseurs/{id}", fournisseur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fournisseur.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.raisonSocial").value(DEFAULT_RAISON_SOCIAL))
            .andExpect(jsonPath("$.rue").value(DEFAULT_RUE))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.fixe").value(DEFAULT_FIXE))
            .andExpect(jsonPath("$.codePostale").value(DEFAULT_CODE_POSTALE))
            .andExpect(jsonPath("$.numeroReseauSocial").value(DEFAULT_NUMERO_RESEAU_SOCIAL))
            .andExpect(jsonPath("$.typePersonne").value(DEFAULT_TYPE_PERSONNE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingFournisseur() throws Exception {
        // Get the fournisseur
        restFournisseurMockMvc.perform(get("/api/fournisseurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFournisseur() throws Exception {
        // Initialize the database
        fournisseurRepository.saveAndFlush(fournisseur);

        int databaseSizeBeforeUpdate = fournisseurRepository.findAll().size();

        // Update the fournisseur
        Fournisseur updatedFournisseur = fournisseurRepository.findById(fournisseur.getId()).get();
        // Disconnect from session so that the updates on updatedFournisseur are not directly saved in db
        em.detach(updatedFournisseur);
        updatedFournisseur
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .raisonSocial(UPDATED_RAISON_SOCIAL)
            .rue(UPDATED_RUE)
            .telephone(UPDATED_TELEPHONE)
            .fixe(UPDATED_FIXE)
            .codePostale(UPDATED_CODE_POSTALE)
            .numeroReseauSocial(UPDATED_NUMERO_RESEAU_SOCIAL)
            .typePersonne(UPDATED_TYPE_PERSONNE);

        restFournisseurMockMvc.perform(put("/api/fournisseurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFournisseur)))
            .andExpect(status().isOk());

        // Validate the Fournisseur in the database
        List<Fournisseur> fournisseurList = fournisseurRepository.findAll();
        assertThat(fournisseurList).hasSize(databaseSizeBeforeUpdate);
        Fournisseur testFournisseur = fournisseurList.get(fournisseurList.size() - 1);
        assertThat(testFournisseur.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testFournisseur.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testFournisseur.getRaisonSocial()).isEqualTo(UPDATED_RAISON_SOCIAL);
        assertThat(testFournisseur.getRue()).isEqualTo(UPDATED_RUE);
        assertThat(testFournisseur.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testFournisseur.getFixe()).isEqualTo(UPDATED_FIXE);
        assertThat(testFournisseur.getCodePostale()).isEqualTo(UPDATED_CODE_POSTALE);
        assertThat(testFournisseur.getNumeroReseauSocial()).isEqualTo(UPDATED_NUMERO_RESEAU_SOCIAL);
        assertThat(testFournisseur.getTypePersonne()).isEqualTo(UPDATED_TYPE_PERSONNE);
    }

    @Test
    @Transactional
    public void updateNonExistingFournisseur() throws Exception {
        int databaseSizeBeforeUpdate = fournisseurRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFournisseurMockMvc.perform(put("/api/fournisseurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fournisseur)))
            .andExpect(status().isBadRequest());

        // Validate the Fournisseur in the database
        List<Fournisseur> fournisseurList = fournisseurRepository.findAll();
        assertThat(fournisseurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFournisseur() throws Exception {
        // Initialize the database
        fournisseurRepository.saveAndFlush(fournisseur);

        int databaseSizeBeforeDelete = fournisseurRepository.findAll().size();

        // Delete the fournisseur
        restFournisseurMockMvc.perform(delete("/api/fournisseurs/{id}", fournisseur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Fournisseur> fournisseurList = fournisseurRepository.findAll();
        assertThat(fournisseurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
