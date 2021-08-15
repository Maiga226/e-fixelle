package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.service.ApprovisionnementService;
import bf.e_fixell_backoffice.web.rest.errors.BadRequestAlertException;
import bf.e_fixell_backoffice.service.dto.ApprovisionnementDTO;
import bf.e_fixell_backoffice.service.dto.ApprovisionnementCriteria;
import bf.e_fixell_backoffice.service.ApprovisionnementQueryService;

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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.Approvisionnement}.
 */
@RestController
@RequestMapping("/api")
public class ApprovisionnementResource {

    private final Logger log = LoggerFactory.getLogger(ApprovisionnementResource.class);

    private static final String ENTITY_NAME = "approvisionnement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApprovisionnementService approvisionnementService;

    private final ApprovisionnementQueryService approvisionnementQueryService;

    public ApprovisionnementResource(ApprovisionnementService approvisionnementService, ApprovisionnementQueryService approvisionnementQueryService) {
        this.approvisionnementService = approvisionnementService;
        this.approvisionnementQueryService = approvisionnementQueryService;
    }

    /**
     * {@code POST  /approvisionnements} : Create a new approvisionnement.
     *
     * @param approvisionnementDTO the approvisionnementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new approvisionnementDTO, or with status {@code 400 (Bad Request)} if the approvisionnement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/approvisionnements")
    public ResponseEntity<ApprovisionnementDTO> createApprovisionnement(@RequestBody ApprovisionnementDTO approvisionnementDTO) throws URISyntaxException {
        log.debug("REST request to save Approvisionnement : {}", approvisionnementDTO);
        if (approvisionnementDTO.getId() != null) {
            throw new BadRequestAlertException("A new approvisionnement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApprovisionnementDTO result = approvisionnementService.save(approvisionnementDTO);
        return ResponseEntity.created(new URI("/api/approvisionnements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /approvisionnements} : Updates an existing approvisionnement.
     *
     * @param approvisionnementDTO the approvisionnementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated approvisionnementDTO,
     * or with status {@code 400 (Bad Request)} if the approvisionnementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the approvisionnementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/approvisionnements")
    public ResponseEntity<ApprovisionnementDTO> updateApprovisionnement(@RequestBody ApprovisionnementDTO approvisionnementDTO) throws URISyntaxException {
        log.debug("REST request to update Approvisionnement : {}", approvisionnementDTO);
        if (approvisionnementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApprovisionnementDTO result = approvisionnementService.save(approvisionnementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, approvisionnementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /approvisionnements} : get all the approvisionnements.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of approvisionnements in body.
     */
    @GetMapping("/approvisionnements")
    public ResponseEntity<List<ApprovisionnementDTO>> getAllApprovisionnements(ApprovisionnementCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Approvisionnements by criteria: {}", criteria);
        Page<ApprovisionnementDTO> page = approvisionnementQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /approvisionnements/count} : count all the approvisionnements.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/approvisionnements/count")
    public ResponseEntity<Long> countApprovisionnements(ApprovisionnementCriteria criteria) {
        log.debug("REST request to count Approvisionnements by criteria: {}", criteria);
        return ResponseEntity.ok().body(approvisionnementQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /approvisionnements/:id} : get the "id" approvisionnement.
     *
     * @param id the id of the approvisionnementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the approvisionnementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/approvisionnements/{id}")
    public ResponseEntity<ApprovisionnementDTO> getApprovisionnement(@PathVariable Long id) {
        log.debug("REST request to get Approvisionnement : {}", id);
        Optional<ApprovisionnementDTO> approvisionnementDTO = approvisionnementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(approvisionnementDTO);
    }

    /**
     * {@code DELETE  /approvisionnements/:id} : delete the "id" approvisionnement.
     *
     * @param id the id of the approvisionnementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/approvisionnements/{id}")
    public ResponseEntity<Void> deleteApprovisionnement(@PathVariable Long id) {
        log.debug("REST request to delete Approvisionnement : {}", id);
        approvisionnementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
