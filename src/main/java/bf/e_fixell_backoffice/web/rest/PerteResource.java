package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.domain.Perte;
import bf.e_fixell_backoffice.repository.PerteRepository;
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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.Perte}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PerteResource {

    private final Logger log = LoggerFactory.getLogger(PerteResource.class);

    private static final String ENTITY_NAME = "perte";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PerteRepository perteRepository;

    public PerteResource(PerteRepository perteRepository) {
        this.perteRepository = perteRepository;
    }

    /**
     * {@code POST  /pertes} : Create a new perte.
     *
     * @param perte the perte to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new perte, or with status {@code 400 (Bad Request)} if the perte has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pertes")
    public ResponseEntity<Perte> createPerte(@RequestBody Perte perte) throws URISyntaxException {
        log.debug("REST request to save Perte : {}", perte);
        if (perte.getId() != null) {
            throw new BadRequestAlertException("A new perte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Perte result = perteRepository.save(perte);
        return ResponseEntity.created(new URI("/api/pertes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pertes} : Updates an existing perte.
     *
     * @param perte the perte to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated perte,
     * or with status {@code 400 (Bad Request)} if the perte is not valid,
     * or with status {@code 500 (Internal Server Error)} if the perte couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pertes")
    public ResponseEntity<Perte> updatePerte(@RequestBody Perte perte) throws URISyntaxException {
        log.debug("REST request to update Perte : {}", perte);
        if (perte.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Perte result = perteRepository.save(perte);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, perte.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pertes} : get all the pertes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pertes in body.
     */
    @GetMapping("/pertes")
    public List<Perte> getAllPertes() {
        log.debug("REST request to get all Pertes");
        return perteRepository.findAll();
    }

    /**
     * {@code GET  /pertes/:id} : get the "id" perte.
     *
     * @param id the id of the perte to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the perte, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pertes/{id}")
    public ResponseEntity<Perte> getPerte(@PathVariable Long id) {
        log.debug("REST request to get Perte : {}", id);
        Optional<Perte> perte = perteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(perte);
    }

    /**
     * {@code DELETE  /pertes/:id} : delete the "id" perte.
     *
     * @param id the id of the perte to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pertes/{id}")
    public ResponseEntity<Void> deletePerte(@PathVariable Long id) {
        log.debug("REST request to delete Perte : {}", id);
        perteRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
