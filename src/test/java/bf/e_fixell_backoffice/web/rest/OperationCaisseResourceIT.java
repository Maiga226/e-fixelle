package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.EFixellBackofficeApp;
import bf.e_fixell_backoffice.domain.OperationCaisse;
import bf.e_fixell_backoffice.repository.OperationCaisseRepository;

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

import bf.e_fixell_backoffice.domain.enumeration.TypeOperationCaisse;
/**
 * Integration tests for the {@link OperationCaisseResource} REST controller.
 */
@SpringBootTest(classes = EFixellBackofficeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OperationCaisseResourceIT {

    private static final TypeOperationCaisse DEFAULT_TYPE_OPERATION_CAISSE = TypeOperationCaisse.APPROVISIONNEMENT;
    private static final TypeOperationCaisse UPDATED_TYPE_OPERATION_CAISSE = TypeOperationCaisse.REVERSEMENT;

    private static final BigDecimal DEFAULT_MONTANT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT = new BigDecimal(2);

    @Autowired
    private OperationCaisseRepository operationCaisseRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOperationCaisseMockMvc;

    private OperationCaisse operationCaisse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OperationCaisse createEntity(EntityManager em) {
        OperationCaisse operationCaisse = new OperationCaisse()
            .typeOperationCaisse(DEFAULT_TYPE_OPERATION_CAISSE)
            .montant(DEFAULT_MONTANT);
        return operationCaisse;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OperationCaisse createUpdatedEntity(EntityManager em) {
        OperationCaisse operationCaisse = new OperationCaisse()
            .typeOperationCaisse(UPDATED_TYPE_OPERATION_CAISSE)
            .montant(UPDATED_MONTANT);
        return operationCaisse;
    }

    @BeforeEach
    public void initTest() {
        operationCaisse = createEntity(em);
    }

    @Test
    @Transactional
    public void createOperationCaisse() throws Exception {
        int databaseSizeBeforeCreate = operationCaisseRepository.findAll().size();
        // Create the OperationCaisse
        restOperationCaisseMockMvc.perform(post("/api/operation-caisses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operationCaisse)))
            .andExpect(status().isCreated());

        // Validate the OperationCaisse in the database
        List<OperationCaisse> operationCaisseList = operationCaisseRepository.findAll();
        assertThat(operationCaisseList).hasSize(databaseSizeBeforeCreate + 1);
        OperationCaisse testOperationCaisse = operationCaisseList.get(operationCaisseList.size() - 1);
        assertThat(testOperationCaisse.getTypeOperationCaisse()).isEqualTo(DEFAULT_TYPE_OPERATION_CAISSE);
        assertThat(testOperationCaisse.getMontant()).isEqualTo(DEFAULT_MONTANT);
    }

    @Test
    @Transactional
    public void createOperationCaisseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = operationCaisseRepository.findAll().size();

        // Create the OperationCaisse with an existing ID
        operationCaisse.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOperationCaisseMockMvc.perform(post("/api/operation-caisses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operationCaisse)))
            .andExpect(status().isBadRequest());

        // Validate the OperationCaisse in the database
        List<OperationCaisse> operationCaisseList = operationCaisseRepository.findAll();
        assertThat(operationCaisseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOperationCaisses() throws Exception {
        // Initialize the database
        operationCaisseRepository.saveAndFlush(operationCaisse);

        // Get all the operationCaisseList
        restOperationCaisseMockMvc.perform(get("/api/operation-caisses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(operationCaisse.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeOperationCaisse").value(hasItem(DEFAULT_TYPE_OPERATION_CAISSE.toString())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())));
    }
    
    @Test
    @Transactional
    public void getOperationCaisse() throws Exception {
        // Initialize the database
        operationCaisseRepository.saveAndFlush(operationCaisse);

        // Get the operationCaisse
        restOperationCaisseMockMvc.perform(get("/api/operation-caisses/{id}", operationCaisse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(operationCaisse.getId().intValue()))
            .andExpect(jsonPath("$.typeOperationCaisse").value(DEFAULT_TYPE_OPERATION_CAISSE.toString()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingOperationCaisse() throws Exception {
        // Get the operationCaisse
        restOperationCaisseMockMvc.perform(get("/api/operation-caisses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOperationCaisse() throws Exception {
        // Initialize the database
        operationCaisseRepository.saveAndFlush(operationCaisse);

        int databaseSizeBeforeUpdate = operationCaisseRepository.findAll().size();

        // Update the operationCaisse
        OperationCaisse updatedOperationCaisse = operationCaisseRepository.findById(operationCaisse.getId()).get();
        // Disconnect from session so that the updates on updatedOperationCaisse are not directly saved in db
        em.detach(updatedOperationCaisse);
        updatedOperationCaisse
            .typeOperationCaisse(UPDATED_TYPE_OPERATION_CAISSE)
            .montant(UPDATED_MONTANT);

        restOperationCaisseMockMvc.perform(put("/api/operation-caisses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOperationCaisse)))
            .andExpect(status().isOk());

        // Validate the OperationCaisse in the database
        List<OperationCaisse> operationCaisseList = operationCaisseRepository.findAll();
        assertThat(operationCaisseList).hasSize(databaseSizeBeforeUpdate);
        OperationCaisse testOperationCaisse = operationCaisseList.get(operationCaisseList.size() - 1);
        assertThat(testOperationCaisse.getTypeOperationCaisse()).isEqualTo(UPDATED_TYPE_OPERATION_CAISSE);
        assertThat(testOperationCaisse.getMontant()).isEqualTo(UPDATED_MONTANT);
    }

    @Test
    @Transactional
    public void updateNonExistingOperationCaisse() throws Exception {
        int databaseSizeBeforeUpdate = operationCaisseRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOperationCaisseMockMvc.perform(put("/api/operation-caisses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operationCaisse)))
            .andExpect(status().isBadRequest());

        // Validate the OperationCaisse in the database
        List<OperationCaisse> operationCaisseList = operationCaisseRepository.findAll();
        assertThat(operationCaisseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOperationCaisse() throws Exception {
        // Initialize the database
        operationCaisseRepository.saveAndFlush(operationCaisse);

        int databaseSizeBeforeDelete = operationCaisseRepository.findAll().size();

        // Delete the operationCaisse
        restOperationCaisseMockMvc.perform(delete("/api/operation-caisses/{id}", operationCaisse.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OperationCaisse> operationCaisseList = operationCaisseRepository.findAll();
        assertThat(operationCaisseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
