package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.domain.OperationCaisse;
import bf.e_fixell_backoffice.repository.OperationCaisseRepository;
import bf.e_fixell_backoffice.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.OperationCaisse}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OperationCaisseResource {

    private final Logger log = LoggerFactory.getLogger(OperationCaisseResource.class);

    private static final String ENTITY_NAME = "operationCaisse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OperationCaisseRepository operationCaisseRepository;

    public OperationCaisseResource(OperationCaisseRepository operationCaisseRepository) {
        this.operationCaisseRepository = operationCaisseRepository;
    }

    /**
     * {@code POST  /operation-caisses} : Create a new operationCaisse.
     *
     * @param operationCaisse the operationCaisse to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new operationCaisse, or with status {@code 400 (Bad Request)} if the operationCaisse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/operation-caisses")
    public ResponseEntity<OperationCaisse> createOperationCaisse(@RequestBody OperationCaisse operationCaisse) throws URISyntaxException {
        log.debug("REST request to save OperationCaisse : {}", operationCaisse);
        if (operationCaisse.getId() != null) {
            throw new BadRequestAlertException("A new operationCaisse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OperationCaisse result = operationCaisseRepository.save(operationCaisse);
        return ResponseEntity.created(new URI("/api/operation-caisses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /operation-caisses} : Updates an existing operationCaisse.
     *
     * @param operationCaisse the operationCaisse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operationCaisse,
     * or with status {@code 400 (Bad Request)} if the operationCaisse is not valid,
     * or with status {@code 500 (Internal Server Error)} if the operationCaisse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/operation-caisses")
    public ResponseEntity<OperationCaisse> updateOperationCaisse(@RequestBody OperationCaisse operationCaisse) throws URISyntaxException {
        log.debug("REST request to update OperationCaisse : {}", operationCaisse);
        if (operationCaisse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OperationCaisse result = operationCaisseRepository.save(operationCaisse);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, operationCaisse.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /operation-caisses} : get all the operationCaisses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of operationCaisses in body.
     */
    @GetMapping("/operation-caisses")
    public List<OperationCaisse> getAllOperationCaisses() {
        log.debug("REST request to get all OperationCaisses");
        return operationCaisseRepository.findAll();
    }

    /**
     * {@code GET  /operation-caisses/:id} : get the "id" operationCaisse.
     *
     * @param id the id of the operationCaisse to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the operationCaisse, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/operation-caisses/{id}")
    public ResponseEntity<OperationCaisse> getOperationCaisse(@PathVariable Long id) {
        log.debug("REST request to get OperationCaisse : {}", id);
        Optional<OperationCaisse> operationCaisse = operationCaisseRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(operationCaisse);
    }

    /**
     * {@code DELETE  /operation-caisses/:id} : delete the "id" operationCaisse.
     *
     * @param id the id of the operationCaisse to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/operation-caisses/{id}")
    public ResponseEntity<Void> deleteOperationCaisse(@PathVariable Long id) {
        log.debug("REST request to delete OperationCaisse : {}", id);
        operationCaisseRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
