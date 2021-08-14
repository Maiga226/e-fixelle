package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.domain.SessionCaisse;
import bf.e_fixell_backoffice.repository.SessionCaisseRepository;
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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.SessionCaisse}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SessionCaisseResource {

    private final Logger log = LoggerFactory.getLogger(SessionCaisseResource.class);

    private static final String ENTITY_NAME = "sessionCaisse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SessionCaisseRepository sessionCaisseRepository;

    public SessionCaisseResource(SessionCaisseRepository sessionCaisseRepository) {
        this.sessionCaisseRepository = sessionCaisseRepository;
    }

    /**
     * {@code POST  /session-caisses} : Create a new sessionCaisse.
     *
     * @param sessionCaisse the sessionCaisse to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sessionCaisse, or with status {@code 400 (Bad Request)} if the sessionCaisse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/session-caisses")
    public ResponseEntity<SessionCaisse> createSessionCaisse(@RequestBody SessionCaisse sessionCaisse) throws URISyntaxException {
        log.debug("REST request to save SessionCaisse : {}", sessionCaisse);
        if (sessionCaisse.getId() != null) {
            throw new BadRequestAlertException("A new sessionCaisse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SessionCaisse result = sessionCaisseRepository.save(sessionCaisse);
        return ResponseEntity.created(new URI("/api/session-caisses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /session-caisses} : Updates an existing sessionCaisse.
     *
     * @param sessionCaisse the sessionCaisse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sessionCaisse,
     * or with status {@code 400 (Bad Request)} if the sessionCaisse is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sessionCaisse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/session-caisses")
    public ResponseEntity<SessionCaisse> updateSessionCaisse(@RequestBody SessionCaisse sessionCaisse) throws URISyntaxException {
        log.debug("REST request to update SessionCaisse : {}", sessionCaisse);
        if (sessionCaisse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SessionCaisse result = sessionCaisseRepository.save(sessionCaisse);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sessionCaisse.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /session-caisses} : get all the sessionCaisses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sessionCaisses in body.
     */
    @GetMapping("/session-caisses")
    public List<SessionCaisse> getAllSessionCaisses() {
        log.debug("REST request to get all SessionCaisses");
        return sessionCaisseRepository.findAll();
    }

    /**
     * {@code GET  /session-caisses/:id} : get the "id" sessionCaisse.
     *
     * @param id the id of the sessionCaisse to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sessionCaisse, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/session-caisses/{id}")
    public ResponseEntity<SessionCaisse> getSessionCaisse(@PathVariable Long id) {
        log.debug("REST request to get SessionCaisse : {}", id);
        Optional<SessionCaisse> sessionCaisse = sessionCaisseRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sessionCaisse);
    }

    /**
     * {@code DELETE  /session-caisses/:id} : delete the "id" sessionCaisse.
     *
     * @param id the id of the sessionCaisse to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/session-caisses/{id}")
    public ResponseEntity<Void> deleteSessionCaisse(@PathVariable Long id) {
        log.debug("REST request to delete SessionCaisse : {}", id);
        sessionCaisseRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
