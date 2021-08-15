package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.service.PerteService;
import bf.e_fixell_backoffice.web.rest.errors.BadRequestAlertException;
import bf.e_fixell_backoffice.service.dto.PerteDTO;
import bf.e_fixell_backoffice.service.dto.PerteCriteria;
import bf.e_fixell_backoffice.service.PerteQueryService;

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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.Perte}.
 */
@RestController
@RequestMapping("/api")
public class PerteResource {

    private final Logger log = LoggerFactory.getLogger(PerteResource.class);

    private static final String ENTITY_NAME = "perte";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PerteService perteService;

    private final PerteQueryService perteQueryService;

    public PerteResource(PerteService perteService, PerteQueryService perteQueryService) {
        this.perteService = perteService;
        this.perteQueryService = perteQueryService;
    }

    /**
     * {@code POST  /pertes} : Create a new perte.
     *
     * @param perteDTO the perteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new perteDTO, or with status {@code 400 (Bad Request)} if the perte has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pertes")
    public ResponseEntity<PerteDTO> createPerte(@RequestBody PerteDTO perteDTO) throws URISyntaxException {
        log.debug("REST request to save Perte : {}", perteDTO);
        if (perteDTO.getId() != null) {
            throw new BadRequestAlertException("A new perte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PerteDTO result = perteService.save(perteDTO);
        return ResponseEntity.created(new URI("/api/pertes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pertes} : Updates an existing perte.
     *
     * @param perteDTO the perteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated perteDTO,
     * or with status {@code 400 (Bad Request)} if the perteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the perteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pertes")
    public ResponseEntity<PerteDTO> updatePerte(@RequestBody PerteDTO perteDTO) throws URISyntaxException {
        log.debug("REST request to update Perte : {}", perteDTO);
        if (perteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PerteDTO result = perteService.save(perteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, perteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pertes} : get all the pertes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pertes in body.
     */
    @GetMapping("/pertes")
    public ResponseEntity<List<PerteDTO>> getAllPertes(PerteCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Pertes by criteria: {}", criteria);
        Page<PerteDTO> page = perteQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pertes/count} : count all the pertes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/pertes/count")
    public ResponseEntity<Long> countPertes(PerteCriteria criteria) {
        log.debug("REST request to count Pertes by criteria: {}", criteria);
        return ResponseEntity.ok().body(perteQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /pertes/:id} : get the "id" perte.
     *
     * @param id the id of the perteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the perteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pertes/{id}")
    public ResponseEntity<PerteDTO> getPerte(@PathVariable Long id) {
        log.debug("REST request to get Perte : {}", id);
        Optional<PerteDTO> perteDTO = perteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(perteDTO);
    }

    /**
     * {@code DELETE  /pertes/:id} : delete the "id" perte.
     *
     * @param id the id of the perteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pertes/{id}")
    public ResponseEntity<Void> deletePerte(@PathVariable Long id) {
        log.debug("REST request to delete Perte : {}", id);
        perteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
