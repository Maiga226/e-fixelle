package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.service.SessionCaisseService;
import bf.e_fixell_backoffice.web.rest.errors.BadRequestAlertException;
import bf.e_fixell_backoffice.service.dto.SessionCaisseDTO;
import bf.e_fixell_backoffice.service.dto.SessionCaisseCriteria;
import bf.e_fixell_backoffice.service.SessionCaisseQueryService;

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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.SessionCaisse}.
 */
@RestController
@RequestMapping("/api")
public class SessionCaisseResource {

    private final Logger log = LoggerFactory.getLogger(SessionCaisseResource.class);

    private static final String ENTITY_NAME = "sessionCaisse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SessionCaisseService sessionCaisseService;

    private final SessionCaisseQueryService sessionCaisseQueryService;

    public SessionCaisseResource(SessionCaisseService sessionCaisseService, SessionCaisseQueryService sessionCaisseQueryService) {
        this.sessionCaisseService = sessionCaisseService;
        this.sessionCaisseQueryService = sessionCaisseQueryService;
    }

    /**
     * {@code POST  /session-caisses} : Create a new sessionCaisse.
     *
     * @param sessionCaisseDTO the sessionCaisseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sessionCaisseDTO, or with status {@code 400 (Bad Request)} if the sessionCaisse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/session-caisses")
    public ResponseEntity<SessionCaisseDTO> createSessionCaisse(@RequestBody SessionCaisseDTO sessionCaisseDTO) throws URISyntaxException {
        log.debug("REST request to save SessionCaisse : {}", sessionCaisseDTO);
        if (sessionCaisseDTO.getId() != null) {
            throw new BadRequestAlertException("A new sessionCaisse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SessionCaisseDTO result = sessionCaisseService.save(sessionCaisseDTO);
        return ResponseEntity.created(new URI("/api/session-caisses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /session-caisses} : Updates an existing sessionCaisse.
     *
     * @param sessionCaisseDTO the sessionCaisseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sessionCaisseDTO,
     * or with status {@code 400 (Bad Request)} if the sessionCaisseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sessionCaisseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/session-caisses")
    public ResponseEntity<SessionCaisseDTO> updateSessionCaisse(@RequestBody SessionCaisseDTO sessionCaisseDTO) throws URISyntaxException {
        log.debug("REST request to update SessionCaisse : {}", sessionCaisseDTO);
        if (sessionCaisseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SessionCaisseDTO result = sessionCaisseService.save(sessionCaisseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sessionCaisseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /session-caisses} : get all the sessionCaisses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sessionCaisses in body.
     */
    @GetMapping("/session-caisses")
    public ResponseEntity<List<SessionCaisseDTO>> getAllSessionCaisses(SessionCaisseCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SessionCaisses by criteria: {}", criteria);
        Page<SessionCaisseDTO> page = sessionCaisseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /session-caisses/count} : count all the sessionCaisses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/session-caisses/count")
    public ResponseEntity<Long> countSessionCaisses(SessionCaisseCriteria criteria) {
        log.debug("REST request to count SessionCaisses by criteria: {}", criteria);
        return ResponseEntity.ok().body(sessionCaisseQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /session-caisses/:id} : get the "id" sessionCaisse.
     *
     * @param id the id of the sessionCaisseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sessionCaisseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/session-caisses/{id}")
    public ResponseEntity<SessionCaisseDTO> getSessionCaisse(@PathVariable Long id) {
        log.debug("REST request to get SessionCaisse : {}", id);
        Optional<SessionCaisseDTO> sessionCaisseDTO = sessionCaisseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sessionCaisseDTO);
    }

    /**
     * {@code DELETE  /session-caisses/:id} : delete the "id" sessionCaisse.
     *
     * @param id the id of the sessionCaisseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/session-caisses/{id}")
    public ResponseEntity<Void> deleteSessionCaisse(@PathVariable Long id) {
        log.debug("REST request to delete SessionCaisse : {}", id);
        sessionCaisseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
