package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.domain.PrixProduit;
import bf.e_fixell_backoffice.repository.PrixProduitRepository;
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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.PrixProduit}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PrixProduitResource {

    private final Logger log = LoggerFactory.getLogger(PrixProduitResource.class);

    private static final String ENTITY_NAME = "prixProduit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrixProduitRepository prixProduitRepository;

    public PrixProduitResource(PrixProduitRepository prixProduitRepository) {
        this.prixProduitRepository = prixProduitRepository;
    }

    /**
     * {@code POST  /prix-produits} : Create a new prixProduit.
     *
     * @param prixProduit the prixProduit to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new prixProduit, or with status {@code 400 (Bad Request)} if the prixProduit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/prix-produits")
    public ResponseEntity<PrixProduit> createPrixProduit(@RequestBody PrixProduit prixProduit) throws URISyntaxException {
        log.debug("REST request to save PrixProduit : {}", prixProduit);
        if (prixProduit.getId() != null) {
            throw new BadRequestAlertException("A new prixProduit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrixProduit result = prixProduitRepository.save(prixProduit);
        return ResponseEntity.created(new URI("/api/prix-produits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /prix-produits} : Updates an existing prixProduit.
     *
     * @param prixProduit the prixProduit to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prixProduit,
     * or with status {@code 400 (Bad Request)} if the prixProduit is not valid,
     * or with status {@code 500 (Internal Server Error)} if the prixProduit couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/prix-produits")
    public ResponseEntity<PrixProduit> updatePrixProduit(@RequestBody PrixProduit prixProduit) throws URISyntaxException {
        log.debug("REST request to update PrixProduit : {}", prixProduit);
        if (prixProduit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PrixProduit result = prixProduitRepository.save(prixProduit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, prixProduit.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /prix-produits} : get all the prixProduits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of prixProduits in body.
     */
    @GetMapping("/prix-produits")
    public List<PrixProduit> getAllPrixProduits() {
        log.debug("REST request to get all PrixProduits");
        return prixProduitRepository.findAll();
    }

    /**
     * {@code GET  /prix-produits/:id} : get the "id" prixProduit.
     *
     * @param id the id of the prixProduit to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the prixProduit, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/prix-produits/{id}")
    public ResponseEntity<PrixProduit> getPrixProduit(@PathVariable Long id) {
        log.debug("REST request to get PrixProduit : {}", id);
        Optional<PrixProduit> prixProduit = prixProduitRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(prixProduit);
    }

    /**
     * {@code DELETE  /prix-produits/:id} : delete the "id" prixProduit.
     *
     * @param id the id of the prixProduit to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/prix-produits/{id}")
    public ResponseEntity<Void> deletePrixProduit(@PathVariable Long id) {
        log.debug("REST request to delete PrixProduit : {}", id);
        prixProduitRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
