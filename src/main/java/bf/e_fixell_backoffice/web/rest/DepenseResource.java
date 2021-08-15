package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.service.DepenseService;
import bf.e_fixell_backoffice.web.rest.errors.BadRequestAlertException;
import bf.e_fixell_backoffice.service.dto.DepenseDTO;
import bf.e_fixell_backoffice.service.dto.DepenseCriteria;
import bf.e_fixell_backoffice.service.DepenseQueryService;

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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.Depense}.
 */
@RestController
@RequestMapping("/api")
public class DepenseResource {

    private final Logger log = LoggerFactory.getLogger(DepenseResource.class);

    private static final String ENTITY_NAME = "depense";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DepenseService depenseService;

    private final DepenseQueryService depenseQueryService;

    public DepenseResource(DepenseService depenseService, DepenseQueryService depenseQueryService) {
        this.depenseService = depenseService;
        this.depenseQueryService = depenseQueryService;
    }

    /**
     * {@code POST  /depenses} : Create a new depense.
     *
     * @param depenseDTO the depenseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new depenseDTO, or with status {@code 400 (Bad Request)} if the depense has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/depenses")
    public ResponseEntity<DepenseDTO> createDepense(@RequestBody DepenseDTO depenseDTO) throws URISyntaxException {
        log.debug("REST request to save Depense : {}", depenseDTO);
        if (depenseDTO.getId() != null) {
            throw new BadRequestAlertException("A new depense cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DepenseDTO result = depenseService.save(depenseDTO);
        return ResponseEntity.created(new URI("/api/depenses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /depenses} : Updates an existing depense.
     *
     * @param depenseDTO the depenseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated depenseDTO,
     * or with status {@code 400 (Bad Request)} if the depenseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the depenseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/depenses")
    public ResponseEntity<DepenseDTO> updateDepense(@RequestBody DepenseDTO depenseDTO) throws URISyntaxException {
        log.debug("REST request to update Depense : {}", depenseDTO);
        if (depenseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DepenseDTO result = depenseService.save(depenseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, depenseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /depenses} : get all the depenses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of depenses in body.
     */
    @GetMapping("/depenses")
    public ResponseEntity<List<DepenseDTO>> getAllDepenses(DepenseCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Depenses by criteria: {}", criteria);
        Page<DepenseDTO> page = depenseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /depenses/count} : count all the depenses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/depenses/count")
    public ResponseEntity<Long> countDepenses(DepenseCriteria criteria) {
        log.debug("REST request to count Depenses by criteria: {}", criteria);
        return ResponseEntity.ok().body(depenseQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /depenses/:id} : get the "id" depense.
     *
     * @param id the id of the depenseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the depenseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/depenses/{id}")
    public ResponseEntity<DepenseDTO> getDepense(@PathVariable Long id) {
        log.debug("REST request to get Depense : {}", id);
        Optional<DepenseDTO> depenseDTO = depenseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(depenseDTO);
    }

    /**
     * {@code DELETE  /depenses/:id} : delete the "id" depense.
     *
     * @param id the id of the depenseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/depenses/{id}")
    public ResponseEntity<Void> deleteDepense(@PathVariable Long id) {
        log.debug("REST request to delete Depense : {}", id);
        depenseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
