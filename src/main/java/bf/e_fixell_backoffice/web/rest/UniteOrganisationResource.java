package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.service.UniteOrganisationService;
import bf.e_fixell_backoffice.web.rest.errors.BadRequestAlertException;
import bf.e_fixell_backoffice.service.dto.UniteOrganisationDTO;
import bf.e_fixell_backoffice.service.dto.UniteOrganisationCriteria;
import bf.e_fixell_backoffice.service.UniteOrganisationQueryService;

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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.UniteOrganisation}.
 */
@RestController
@RequestMapping("/api")
public class UniteOrganisationResource {

    private final Logger log = LoggerFactory.getLogger(UniteOrganisationResource.class);

    private static final String ENTITY_NAME = "uniteOrganisation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UniteOrganisationService uniteOrganisationService;

    private final UniteOrganisationQueryService uniteOrganisationQueryService;

    public UniteOrganisationResource(UniteOrganisationService uniteOrganisationService, UniteOrganisationQueryService uniteOrganisationQueryService) {
        this.uniteOrganisationService = uniteOrganisationService;
        this.uniteOrganisationQueryService = uniteOrganisationQueryService;
    }

    /**
     * {@code POST  /unite-organisations} : Create a new uniteOrganisation.
     *
     * @param uniteOrganisationDTO the uniteOrganisationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new uniteOrganisationDTO, or with status {@code 400 (Bad Request)} if the uniteOrganisation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/unite-organisations")
    public ResponseEntity<UniteOrganisationDTO> createUniteOrganisation(@RequestBody UniteOrganisationDTO uniteOrganisationDTO) throws URISyntaxException {
        log.debug("REST request to save UniteOrganisation : {}", uniteOrganisationDTO);
        if (uniteOrganisationDTO.getId() != null) {
            throw new BadRequestAlertException("A new uniteOrganisation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UniteOrganisationDTO result = uniteOrganisationService.save(uniteOrganisationDTO);
        return ResponseEntity.created(new URI("/api/unite-organisations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /unite-organisations} : Updates an existing uniteOrganisation.
     *
     * @param uniteOrganisationDTO the uniteOrganisationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uniteOrganisationDTO,
     * or with status {@code 400 (Bad Request)} if the uniteOrganisationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the uniteOrganisationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/unite-organisations")
    public ResponseEntity<UniteOrganisationDTO> updateUniteOrganisation(@RequestBody UniteOrganisationDTO uniteOrganisationDTO) throws URISyntaxException {
        log.debug("REST request to update UniteOrganisation : {}", uniteOrganisationDTO);
        if (uniteOrganisationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UniteOrganisationDTO result = uniteOrganisationService.save(uniteOrganisationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, uniteOrganisationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /unite-organisations} : get all the uniteOrganisations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of uniteOrganisations in body.
     */
    @GetMapping("/unite-organisations")
    public ResponseEntity<List<UniteOrganisationDTO>> getAllUniteOrganisations(UniteOrganisationCriteria criteria, Pageable pageable) {
        log.debug("REST request to get UniteOrganisations by criteria: {}", criteria);
        Page<UniteOrganisationDTO> page = uniteOrganisationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /unite-organisations/count} : count all the uniteOrganisations.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/unite-organisations/count")
    public ResponseEntity<Long> countUniteOrganisations(UniteOrganisationCriteria criteria) {
        log.debug("REST request to count UniteOrganisations by criteria: {}", criteria);
        return ResponseEntity.ok().body(uniteOrganisationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /unite-organisations/:id} : get the "id" uniteOrganisation.
     *
     * @param id the id of the uniteOrganisationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the uniteOrganisationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/unite-organisations/{id}")
    public ResponseEntity<UniteOrganisationDTO> getUniteOrganisation(@PathVariable Long id) {
        log.debug("REST request to get UniteOrganisation : {}", id);
        Optional<UniteOrganisationDTO> uniteOrganisationDTO = uniteOrganisationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(uniteOrganisationDTO);
    }

    /**
     * {@code DELETE  /unite-organisations/:id} : delete the "id" uniteOrganisation.
     *
     * @param id the id of the uniteOrganisationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/unite-organisations/{id}")
    public ResponseEntity<Void> deleteUniteOrganisation(@PathVariable Long id) {
        log.debug("REST request to delete UniteOrganisation : {}", id);
        uniteOrganisationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
