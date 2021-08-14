package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.domain.Caisse;
import bf.e_fixell_backoffice.repository.CaisseRepository;
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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.Caisse}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CaisseResource {

    private final Logger log = LoggerFactory.getLogger(CaisseResource.class);

    private static final String ENTITY_NAME = "caisse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaisseRepository caisseRepository;

    public CaisseResource(CaisseRepository caisseRepository) {
        this.caisseRepository = caisseRepository;
    }

    /**
     * {@code POST  /caisses} : Create a new caisse.
     *
     * @param caisse the caisse to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caisse, or with status {@code 400 (Bad Request)} if the caisse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/caisses")
    public ResponseEntity<Caisse> createCaisse(@RequestBody Caisse caisse) throws URISyntaxException {
        log.debug("REST request to save Caisse : {}", caisse);
        if (caisse.getId() != null) {
            throw new BadRequestAlertException("A new caisse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Caisse result = caisseRepository.save(caisse);
        return ResponseEntity.created(new URI("/api/caisses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /caisses} : Updates an existing caisse.
     *
     * @param caisse the caisse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caisse,
     * or with status {@code 400 (Bad Request)} if the caisse is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caisse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/caisses")
    public ResponseEntity<Caisse> updateCaisse(@RequestBody Caisse caisse) throws URISyntaxException {
        log.debug("REST request to update Caisse : {}", caisse);
        if (caisse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Caisse result = caisseRepository.save(caisse);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, caisse.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /caisses} : get all the caisses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caisses in body.
     */
    @GetMapping("/caisses")
    public List<Caisse> getAllCaisses() {
        log.debug("REST request to get all Caisses");
        return caisseRepository.findAll();
    }

    /**
     * {@code GET  /caisses/:id} : get the "id" caisse.
     *
     * @param id the id of the caisse to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caisse, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/caisses/{id}")
    public ResponseEntity<Caisse> getCaisse(@PathVariable Long id) {
        log.debug("REST request to get Caisse : {}", id);
        Optional<Caisse> caisse = caisseRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(caisse);
    }

    /**
     * {@code DELETE  /caisses/:id} : delete the "id" caisse.
     *
     * @param id the id of the caisse to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/caisses/{id}")
    public ResponseEntity<Void> deleteCaisse(@PathVariable Long id) {
        log.debug("REST request to delete Caisse : {}", id);
        caisseRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
