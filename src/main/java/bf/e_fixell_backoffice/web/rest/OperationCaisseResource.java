package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.service.OperationCaisseService;
import bf.e_fixell_backoffice.web.rest.errors.BadRequestAlertException;
import bf.e_fixell_backoffice.service.dto.OperationCaisseDTO;
import bf.e_fixell_backoffice.service.dto.OperationCaisseCriteria;
import bf.e_fixell_backoffice.service.OperationCaisseQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
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
public class OperationCaisseResource {

    private final Logger log = LoggerFactory.getLogger(OperationCaisseResource.class);

    private static final String ENTITY_NAME = "operationCaisse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OperationCaisseService operationCaisseService;

    private final OperationCaisseQueryService operationCaisseQueryService;

    public OperationCaisseResource(OperationCaisseService operationCaisseService, OperationCaisseQueryService operationCaisseQueryService) {
        this.operationCaisseService = operationCaisseService;
        this.operationCaisseQueryService = operationCaisseQueryService;
    }

    /**
     * {@code POST  /operation-caisses} : Create a new operationCaisse.
     *
     * @param operationCaisseDTO the operationCaisseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new operationCaisseDTO, or with status {@code 400 (Bad Request)} if the operationCaisse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/operation-caisses")
    public ResponseEntity<OperationCaisseDTO> createOperationCaisse(@RequestBody OperationCaisseDTO operationCaisseDTO) throws URISyntaxException {
        log.debug("REST request to save OperationCaisse : {}", operationCaisseDTO);
        if (operationCaisseDTO.getId() != null) {
            throw new BadRequestAlertException("A new operationCaisse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OperationCaisseDTO result = operationCaisseService.save(operationCaisseDTO);
        return ResponseEntity.created(new URI("/api/operation-caisses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /operation-caisses} : Updates an existing operationCaisse.
     *
     * @param operationCaisseDTO the operationCaisseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operationCaisseDTO,
     * or with status {@code 400 (Bad Request)} if the operationCaisseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the operationCaisseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/operation-caisses")
    public ResponseEntity<OperationCaisseDTO> updateOperationCaisse(@RequestBody OperationCaisseDTO operationCaisseDTO) throws URISyntaxException {
        log.debug("REST request to update OperationCaisse : {}", operationCaisseDTO);
        if (operationCaisseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OperationCaisseDTO result = operationCaisseService.save(operationCaisseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, operationCaisseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /operation-caisses} : get all the operationCaisses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of operationCaisses in body.
     */
    @GetMapping("/operation-caisses")
    public ResponseEntity<List<OperationCaisseDTO>> getAllOperationCaisses(OperationCaisseCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OperationCaisses by criteria: {}", criteria);
        Page<OperationCaisseDTO> page = operationCaisseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /operation-caisses/count} : count all the operationCaisses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/operation-caisses/count")
    public ResponseEntity<Long> countOperationCaisses(OperationCaisseCriteria criteria) {
        log.debug("REST request to count OperationCaisses by criteria: {}", criteria);
        return ResponseEntity.ok().body(operationCaisseQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /operation-caisses/:id} : get the "id" operationCaisse.
     *
     * @param id the id of the operationCaisseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the operationCaisseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/operation-caisses/{id}")
    public ResponseEntity<OperationCaisseDTO> getOperationCaisse(@PathVariable Long id) {
        log.debug("REST request to get OperationCaisse : {}", id);
        Optional<OperationCaisseDTO> operationCaisseDTO = operationCaisseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(operationCaisseDTO);
    }

    /**
     * {@code DELETE  /operation-caisses/:id} : delete the "id" operationCaisse.
     *
     * @param id the id of the operationCaisseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/operation-caisses/{id}")
    public ResponseEntity<Void> deleteOperationCaisse(@PathVariable Long id) {
        log.debug("REST request to delete OperationCaisse : {}", id);
        operationCaisseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
