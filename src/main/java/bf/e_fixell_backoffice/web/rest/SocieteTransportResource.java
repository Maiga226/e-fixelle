package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.domain.SocieteTransport;
import bf.e_fixell_backoffice.repository.SocieteTransportRepository;
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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.SocieteTransport}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SocieteTransportResource {

    private final Logger log = LoggerFactory.getLogger(SocieteTransportResource.class);

    private static final String ENTITY_NAME = "societeTransport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SocieteTransportRepository societeTransportRepository;

    public SocieteTransportResource(SocieteTransportRepository societeTransportRepository) {
        this.societeTransportRepository = societeTransportRepository;
    }

    /**
     * {@code POST  /societe-transports} : Create a new societeTransport.
     *
     * @param societeTransport the societeTransport to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new societeTransport, or with status {@code 400 (Bad Request)} if the societeTransport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/societe-transports")
    public ResponseEntity<SocieteTransport> createSocieteTransport(@RequestBody SocieteTransport societeTransport) throws URISyntaxException {
        log.debug("REST request to save SocieteTransport : {}", societeTransport);
        if (societeTransport.getId() != null) {
            throw new BadRequestAlertException("A new societeTransport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SocieteTransport result = societeTransportRepository.save(societeTransport);
        return ResponseEntity.created(new URI("/api/societe-transports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /societe-transports} : Updates an existing societeTransport.
     *
     * @param societeTransport the societeTransport to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated societeTransport,
     * or with status {@code 400 (Bad Request)} if the societeTransport is not valid,
     * or with status {@code 500 (Internal Server Error)} if the societeTransport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/societe-transports")
    public ResponseEntity<SocieteTransport> updateSocieteTransport(@RequestBody SocieteTransport societeTransport) throws URISyntaxException {
        log.debug("REST request to update SocieteTransport : {}", societeTransport);
        if (societeTransport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SocieteTransport result = societeTransportRepository.save(societeTransport);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, societeTransport.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /societe-transports} : get all the societeTransports.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of societeTransports in body.
     */
    @GetMapping("/societe-transports")
    public List<SocieteTransport> getAllSocieteTransports() {
        log.debug("REST request to get all SocieteTransports");
        return societeTransportRepository.findAll();
    }

    /**
     * {@code GET  /societe-transports/:id} : get the "id" societeTransport.
     *
     * @param id the id of the societeTransport to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the societeTransport, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/societe-transports/{id}")
    public ResponseEntity<SocieteTransport> getSocieteTransport(@PathVariable Long id) {
        log.debug("REST request to get SocieteTransport : {}", id);
        Optional<SocieteTransport> societeTransport = societeTransportRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(societeTransport);
    }

    /**
     * {@code DELETE  /societe-transports/:id} : delete the "id" societeTransport.
     *
     * @param id the id of the societeTransport to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/societe-transports/{id}")
    public ResponseEntity<Void> deleteSocieteTransport(@PathVariable Long id) {
        log.debug("REST request to delete SocieteTransport : {}", id);
        societeTransportRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
