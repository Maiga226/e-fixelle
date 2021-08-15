package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.service.HistoriqueAffectationService;
import bf.e_fixell_backoffice.web.rest.errors.BadRequestAlertException;
import bf.e_fixell_backoffice.service.dto.HistoriqueAffectationDTO;
import bf.e_fixell_backoffice.service.dto.HistoriqueAffectationCriteria;
import bf.e_fixell_backoffice.service.HistoriqueAffectationQueryService;

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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.HistoriqueAffectation}.
 */
@RestController
@RequestMapping("/api")
public class HistoriqueAffectationResource {

    private final Logger log = LoggerFactory.getLogger(HistoriqueAffectationResource.class);

    private static final String ENTITY_NAME = "historiqueAffectation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HistoriqueAffectationService historiqueAffectationService;

    private final HistoriqueAffectationQueryService historiqueAffectationQueryService;

    public HistoriqueAffectationResource(HistoriqueAffectationService historiqueAffectationService, HistoriqueAffectationQueryService historiqueAffectationQueryService) {
        this.historiqueAffectationService = historiqueAffectationService;
        this.historiqueAffectationQueryService = historiqueAffectationQueryService;
    }

    /**
     * {@code POST  /historique-affectations} : Create a new historiqueAffectation.
     *
     * @param historiqueAffectationDTO the historiqueAffectationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new historiqueAffectationDTO, or with status {@code 400 (Bad Request)} if the historiqueAffectation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/historique-affectations")
    public ResponseEntity<HistoriqueAffectationDTO> createHistoriqueAffectation(@RequestBody HistoriqueAffectationDTO historiqueAffectationDTO) throws URISyntaxException {
        log.debug("REST request to save HistoriqueAffectation : {}", historiqueAffectationDTO);
        if (historiqueAffectationDTO.getId() != null) {
            throw new BadRequestAlertException("A new historiqueAffectation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HistoriqueAffectationDTO result = historiqueAffectationService.save(historiqueAffectationDTO);
        return ResponseEntity.created(new URI("/api/historique-affectations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /historique-affectations} : Updates an existing historiqueAffectation.
     *
     * @param historiqueAffectationDTO the historiqueAffectationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historiqueAffectationDTO,
     * or with status {@code 400 (Bad Request)} if the historiqueAffectationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the historiqueAffectationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/historique-affectations")
    public ResponseEntity<HistoriqueAffectationDTO> updateHistoriqueAffectation(@RequestBody HistoriqueAffectationDTO historiqueAffectationDTO) throws URISyntaxException {
        log.debug("REST request to update HistoriqueAffectation : {}", historiqueAffectationDTO);
        if (historiqueAffectationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HistoriqueAffectationDTO result = historiqueAffectationService.save(historiqueAffectationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, historiqueAffectationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /historique-affectations} : get all the historiqueAffectations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of historiqueAffectations in body.
     */
    @GetMapping("/historique-affectations")
    public ResponseEntity<List<HistoriqueAffectationDTO>> getAllHistoriqueAffectations(HistoriqueAffectationCriteria criteria, Pageable pageable) {
        log.debug("REST request to get HistoriqueAffectations by criteria: {}", criteria);
        Page<HistoriqueAffectationDTO> page = historiqueAffectationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /historique-affectations/count} : count all the historiqueAffectations.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/historique-affectations/count")
    public ResponseEntity<Long> countHistoriqueAffectations(HistoriqueAffectationCriteria criteria) {
        log.debug("REST request to count HistoriqueAffectations by criteria: {}", criteria);
        return ResponseEntity.ok().body(historiqueAffectationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /historique-affectations/:id} : get the "id" historiqueAffectation.
     *
     * @param id the id of the historiqueAffectationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the historiqueAffectationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/historique-affectations/{id}")
    public ResponseEntity<HistoriqueAffectationDTO> getHistoriqueAffectation(@PathVariable Long id) {
        log.debug("REST request to get HistoriqueAffectation : {}", id);
        Optional<HistoriqueAffectationDTO> historiqueAffectationDTO = historiqueAffectationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(historiqueAffectationDTO);
    }

    /**
     * {@code DELETE  /historique-affectations/:id} : delete the "id" historiqueAffectation.
     *
     * @param id the id of the historiqueAffectationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/historique-affectations/{id}")
    public ResponseEntity<Void> deleteHistoriqueAffectation(@PathVariable Long id) {
        log.debug("REST request to delete HistoriqueAffectation : {}", id);
        historiqueAffectationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
