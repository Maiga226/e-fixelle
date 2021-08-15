package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.service.SocieteTransportService;
import bf.e_fixell_backoffice.web.rest.errors.BadRequestAlertException;
import bf.e_fixell_backoffice.service.dto.SocieteTransportDTO;
import bf.e_fixell_backoffice.service.dto.SocieteTransportCriteria;
import bf.e_fixell_backoffice.service.SocieteTransportQueryService;

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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.SocieteTransport}.
 */
@RestController
@RequestMapping("/api")
public class SocieteTransportResource {

    private final Logger log = LoggerFactory.getLogger(SocieteTransportResource.class);

    private static final String ENTITY_NAME = "societeTransport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SocieteTransportService societeTransportService;

    private final SocieteTransportQueryService societeTransportQueryService;

    public SocieteTransportResource(SocieteTransportService societeTransportService, SocieteTransportQueryService societeTransportQueryService) {
        this.societeTransportService = societeTransportService;
        this.societeTransportQueryService = societeTransportQueryService;
    }

    /**
     * {@code POST  /societe-transports} : Create a new societeTransport.
     *
     * @param societeTransportDTO the societeTransportDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new societeTransportDTO, or with status {@code 400 (Bad Request)} if the societeTransport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/societe-transports")
    public ResponseEntity<SocieteTransportDTO> createSocieteTransport(@RequestBody SocieteTransportDTO societeTransportDTO) throws URISyntaxException {
        log.debug("REST request to save SocieteTransport : {}", societeTransportDTO);
        if (societeTransportDTO.getId() != null) {
            throw new BadRequestAlertException("A new societeTransport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SocieteTransportDTO result = societeTransportService.save(societeTransportDTO);
        return ResponseEntity.created(new URI("/api/societe-transports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /societe-transports} : Updates an existing societeTransport.
     *
     * @param societeTransportDTO the societeTransportDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated societeTransportDTO,
     * or with status {@code 400 (Bad Request)} if the societeTransportDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the societeTransportDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/societe-transports")
    public ResponseEntity<SocieteTransportDTO> updateSocieteTransport(@RequestBody SocieteTransportDTO societeTransportDTO) throws URISyntaxException {
        log.debug("REST request to update SocieteTransport : {}", societeTransportDTO);
        if (societeTransportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SocieteTransportDTO result = societeTransportService.save(societeTransportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, societeTransportDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /societe-transports} : get all the societeTransports.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of societeTransports in body.
     */
    @GetMapping("/societe-transports")
    public ResponseEntity<List<SocieteTransportDTO>> getAllSocieteTransports(SocieteTransportCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SocieteTransports by criteria: {}", criteria);
        Page<SocieteTransportDTO> page = societeTransportQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /societe-transports/count} : count all the societeTransports.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/societe-transports/count")
    public ResponseEntity<Long> countSocieteTransports(SocieteTransportCriteria criteria) {
        log.debug("REST request to count SocieteTransports by criteria: {}", criteria);
        return ResponseEntity.ok().body(societeTransportQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /societe-transports/:id} : get the "id" societeTransport.
     *
     * @param id the id of the societeTransportDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the societeTransportDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/societe-transports/{id}")
    public ResponseEntity<SocieteTransportDTO> getSocieteTransport(@PathVariable Long id) {
        log.debug("REST request to get SocieteTransport : {}", id);
        Optional<SocieteTransportDTO> societeTransportDTO = societeTransportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(societeTransportDTO);
    }

    /**
     * {@code DELETE  /societe-transports/:id} : delete the "id" societeTransport.
     *
     * @param id the id of the societeTransportDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/societe-transports/{id}")
    public ResponseEntity<Void> deleteSocieteTransport(@PathVariable Long id) {
        log.debug("REST request to delete SocieteTransport : {}", id);
        societeTransportService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
