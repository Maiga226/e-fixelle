package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.domain.Approvisionnement;
import bf.e_fixell_backoffice.repository.ApprovisionnementRepository;
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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.Approvisionnement}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ApprovisionnementResource {

    private final Logger log = LoggerFactory.getLogger(ApprovisionnementResource.class);

    private static final String ENTITY_NAME = "approvisionnement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApprovisionnementRepository approvisionnementRepository;

    public ApprovisionnementResource(ApprovisionnementRepository approvisionnementRepository) {
        this.approvisionnementRepository = approvisionnementRepository;
    }

    /**
     * {@code POST  /approvisionnements} : Create a new approvisionnement.
     *
     * @param approvisionnement the approvisionnement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new approvisionnement, or with status {@code 400 (Bad Request)} if the approvisionnement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/approvisionnements")
    public ResponseEntity<Approvisionnement> createApprovisionnement(@RequestBody Approvisionnement approvisionnement) throws URISyntaxException {
        log.debug("REST request to save Approvisionnement : {}", approvisionnement);
        if (approvisionnement.getId() != null) {
            throw new BadRequestAlertException("A new approvisionnement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Approvisionnement result = approvisionnementRepository.save(approvisionnement);
        return ResponseEntity.created(new URI("/api/approvisionnements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /approvisionnements} : Updates an existing approvisionnement.
     *
     * @param approvisionnement the approvisionnement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated approvisionnement,
     * or with status {@code 400 (Bad Request)} if the approvisionnement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the approvisionnement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/approvisionnements")
    public ResponseEntity<Approvisionnement> updateApprovisionnement(@RequestBody Approvisionnement approvisionnement) throws URISyntaxException {
        log.debug("REST request to update Approvisionnement : {}", approvisionnement);
        if (approvisionnement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Approvisionnement result = approvisionnementRepository.save(approvisionnement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, approvisionnement.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /approvisionnements} : get all the approvisionnements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of approvisionnements in body.
     */
    @GetMapping("/approvisionnements")
    public List<Approvisionnement> getAllApprovisionnements() {
        log.debug("REST request to get all Approvisionnements");
        return approvisionnementRepository.findAll();
    }

    /**
     * {@code GET  /approvisionnements/:id} : get the "id" approvisionnement.
     *
     * @param id the id of the approvisionnement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the approvisionnement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/approvisionnements/{id}")
    public ResponseEntity<Approvisionnement> getApprovisionnement(@PathVariable Long id) {
        log.debug("REST request to get Approvisionnement : {}", id);
        Optional<Approvisionnement> approvisionnement = approvisionnementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(approvisionnement);
    }

    /**
     * {@code DELETE  /approvisionnements/:id} : delete the "id" approvisionnement.
     *
     * @param id the id of the approvisionnement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/approvisionnements/{id}")
    public ResponseEntity<Void> deleteApprovisionnement(@PathVariable Long id) {
        log.debug("REST request to delete Approvisionnement : {}", id);
        approvisionnementRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
