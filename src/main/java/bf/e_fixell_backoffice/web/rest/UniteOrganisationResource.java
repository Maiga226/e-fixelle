package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.domain.UniteOrganisation;
import bf.e_fixell_backoffice.repository.UniteOrganisationRepository;
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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.UniteOrganisation}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UniteOrganisationResource {

    private final Logger log = LoggerFactory.getLogger(UniteOrganisationResource.class);

    private static final String ENTITY_NAME = "uniteOrganisation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UniteOrganisationRepository uniteOrganisationRepository;

    public UniteOrganisationResource(UniteOrganisationRepository uniteOrganisationRepository) {
        this.uniteOrganisationRepository = uniteOrganisationRepository;
    }

    /**
     * {@code POST  /unite-organisations} : Create a new uniteOrganisation.
     *
     * @param uniteOrganisation the uniteOrganisation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new uniteOrganisation, or with status {@code 400 (Bad Request)} if the uniteOrganisation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/unite-organisations")
    public ResponseEntity<UniteOrganisation> createUniteOrganisation(@RequestBody UniteOrganisation uniteOrganisation) throws URISyntaxException {
        log.debug("REST request to save UniteOrganisation : {}", uniteOrganisation);
        if (uniteOrganisation.getId() != null) {
            throw new BadRequestAlertException("A new uniteOrganisation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UniteOrganisation result = uniteOrganisationRepository.save(uniteOrganisation);
        return ResponseEntity.created(new URI("/api/unite-organisations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /unite-organisations} : Updates an existing uniteOrganisation.
     *
     * @param uniteOrganisation the uniteOrganisation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uniteOrganisation,
     * or with status {@code 400 (Bad Request)} if the uniteOrganisation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the uniteOrganisation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/unite-organisations")
    public ResponseEntity<UniteOrganisation> updateUniteOrganisation(@RequestBody UniteOrganisation uniteOrganisation) throws URISyntaxException {
        log.debug("REST request to update UniteOrganisation : {}", uniteOrganisation);
        if (uniteOrganisation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UniteOrganisation result = uniteOrganisationRepository.save(uniteOrganisation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, uniteOrganisation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /unite-organisations} : get all the uniteOrganisations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of uniteOrganisations in body.
     */
    @GetMapping("/unite-organisations")
    public List<UniteOrganisation> getAllUniteOrganisations() {
        log.debug("REST request to get all UniteOrganisations");
        return uniteOrganisationRepository.findAll();
    }

    /**
     * {@code GET  /unite-organisations/:id} : get the "id" uniteOrganisation.
     *
     * @param id the id of the uniteOrganisation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the uniteOrganisation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/unite-organisations/{id}")
    public ResponseEntity<UniteOrganisation> getUniteOrganisation(@PathVariable Long id) {
        log.debug("REST request to get UniteOrganisation : {}", id);
        Optional<UniteOrganisation> uniteOrganisation = uniteOrganisationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(uniteOrganisation);
    }

    /**
     * {@code DELETE  /unite-organisations/:id} : delete the "id" uniteOrganisation.
     *
     * @param id the id of the uniteOrganisation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/unite-organisations/{id}")
    public ResponseEntity<Void> deleteUniteOrganisation(@PathVariable Long id) {
        log.debug("REST request to delete UniteOrganisation : {}", id);
        uniteOrganisationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
