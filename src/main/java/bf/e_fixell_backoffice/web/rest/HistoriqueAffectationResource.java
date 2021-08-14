package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.domain.HistoriqueAffectation;
import bf.e_fixell_backoffice.repository.HistoriqueAffectationRepository;
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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.HistoriqueAffectation}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class HistoriqueAffectationResource {

    private final Logger log = LoggerFactory.getLogger(HistoriqueAffectationResource.class);

    private static final String ENTITY_NAME = "historiqueAffectation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HistoriqueAffectationRepository historiqueAffectationRepository;

    public HistoriqueAffectationResource(HistoriqueAffectationRepository historiqueAffectationRepository) {
        this.historiqueAffectationRepository = historiqueAffectationRepository;
    }

    /**
     * {@code POST  /historique-affectations} : Create a new historiqueAffectation.
     *
     * @param historiqueAffectation the historiqueAffectation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new historiqueAffectation, or with status {@code 400 (Bad Request)} if the historiqueAffectation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/historique-affectations")
    public ResponseEntity<HistoriqueAffectation> createHistoriqueAffectation(@RequestBody HistoriqueAffectation historiqueAffectation) throws URISyntaxException {
        log.debug("REST request to save HistoriqueAffectation : {}", historiqueAffectation);
        if (historiqueAffectation.getId() != null) {
            throw new BadRequestAlertException("A new historiqueAffectation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HistoriqueAffectation result = historiqueAffectationRepository.save(historiqueAffectation);
        return ResponseEntity.created(new URI("/api/historique-affectations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /historique-affectations} : Updates an existing historiqueAffectation.
     *
     * @param historiqueAffectation the historiqueAffectation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historiqueAffectation,
     * or with status {@code 400 (Bad Request)} if the historiqueAffectation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the historiqueAffectation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/historique-affectations")
    public ResponseEntity<HistoriqueAffectation> updateHistoriqueAffectation(@RequestBody HistoriqueAffectation historiqueAffectation) throws URISyntaxException {
        log.debug("REST request to update HistoriqueAffectation : {}", historiqueAffectation);
        if (historiqueAffectation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HistoriqueAffectation result = historiqueAffectationRepository.save(historiqueAffectation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, historiqueAffectation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /historique-affectations} : get all the historiqueAffectations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of historiqueAffectations in body.
     */
    @GetMapping("/historique-affectations")
    public List<HistoriqueAffectation> getAllHistoriqueAffectations() {
        log.debug("REST request to get all HistoriqueAffectations");
        return historiqueAffectationRepository.findAll();
    }

    /**
     * {@code GET  /historique-affectations/:id} : get the "id" historiqueAffectation.
     *
     * @param id the id of the historiqueAffectation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the historiqueAffectation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/historique-affectations/{id}")
    public ResponseEntity<HistoriqueAffectation> getHistoriqueAffectation(@PathVariable Long id) {
        log.debug("REST request to get HistoriqueAffectation : {}", id);
        Optional<HistoriqueAffectation> historiqueAffectation = historiqueAffectationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(historiqueAffectation);
    }

    /**
     * {@code DELETE  /historique-affectations/:id} : delete the "id" historiqueAffectation.
     *
     * @param id the id of the historiqueAffectation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/historique-affectations/{id}")
    public ResponseEntity<Void> deleteHistoriqueAffectation(@PathVariable Long id) {
        log.debug("REST request to delete HistoriqueAffectation : {}", id);
        historiqueAffectationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
