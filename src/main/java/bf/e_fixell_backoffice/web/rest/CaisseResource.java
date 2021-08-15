package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.service.CaisseService;
import bf.e_fixell_backoffice.web.rest.errors.BadRequestAlertException;
import bf.e_fixell_backoffice.service.dto.CaisseDTO;
import bf.e_fixell_backoffice.service.dto.CaisseCriteria;
import bf.e_fixell_backoffice.service.CaisseQueryService;

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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.Caisse}.
 */
@RestController
@RequestMapping("/api")
public class CaisseResource {

    private final Logger log = LoggerFactory.getLogger(CaisseResource.class);

    private static final String ENTITY_NAME = "caisse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaisseService caisseService;

    private final CaisseQueryService caisseQueryService;

    public CaisseResource(CaisseService caisseService, CaisseQueryService caisseQueryService) {
        this.caisseService = caisseService;
        this.caisseQueryService = caisseQueryService;
    }

    /**
     * {@code POST  /caisses} : Create a new caisse.
     *
     * @param caisseDTO the caisseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caisseDTO, or with status {@code 400 (Bad Request)} if the caisse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/caisses")
    public ResponseEntity<CaisseDTO> createCaisse(@RequestBody CaisseDTO caisseDTO) throws URISyntaxException {
        log.debug("REST request to save Caisse : {}", caisseDTO);
        if (caisseDTO.getId() != null) {
            throw new BadRequestAlertException("A new caisse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaisseDTO result = caisseService.save(caisseDTO);
        return ResponseEntity.created(new URI("/api/caisses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /caisses} : Updates an existing caisse.
     *
     * @param caisseDTO the caisseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caisseDTO,
     * or with status {@code 400 (Bad Request)} if the caisseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caisseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/caisses")
    public ResponseEntity<CaisseDTO> updateCaisse(@RequestBody CaisseDTO caisseDTO) throws URISyntaxException {
        log.debug("REST request to update Caisse : {}", caisseDTO);
        if (caisseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaisseDTO result = caisseService.save(caisseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, caisseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /caisses} : get all the caisses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caisses in body.
     */
    @GetMapping("/caisses")
    public ResponseEntity<List<CaisseDTO>> getAllCaisses(CaisseCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Caisses by criteria: {}", criteria);
        Page<CaisseDTO> page = caisseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /caisses/count} : count all the caisses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/caisses/count")
    public ResponseEntity<Long> countCaisses(CaisseCriteria criteria) {
        log.debug("REST request to count Caisses by criteria: {}", criteria);
        return ResponseEntity.ok().body(caisseQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /caisses/:id} : get the "id" caisse.
     *
     * @param id the id of the caisseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caisseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/caisses/{id}")
    public ResponseEntity<CaisseDTO> getCaisse(@PathVariable Long id) {
        log.debug("REST request to get Caisse : {}", id);
        Optional<CaisseDTO> caisseDTO = caisseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caisseDTO);
    }

    /**
     * {@code DELETE  /caisses/:id} : delete the "id" caisse.
     *
     * @param id the id of the caisseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/caisses/{id}")
    public ResponseEntity<Void> deleteCaisse(@PathVariable Long id) {
        log.debug("REST request to delete Caisse : {}", id);
        caisseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
