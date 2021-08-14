package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.domain.FicheTechnique;
import bf.e_fixell_backoffice.repository.FicheTechniqueRepository;
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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.FicheTechnique}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FicheTechniqueResource {

    private final Logger log = LoggerFactory.getLogger(FicheTechniqueResource.class);

    private static final String ENTITY_NAME = "ficheTechnique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FicheTechniqueRepository ficheTechniqueRepository;

    public FicheTechniqueResource(FicheTechniqueRepository ficheTechniqueRepository) {
        this.ficheTechniqueRepository = ficheTechniqueRepository;
    }

    /**
     * {@code POST  /fiche-techniques} : Create a new ficheTechnique.
     *
     * @param ficheTechnique the ficheTechnique to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ficheTechnique, or with status {@code 400 (Bad Request)} if the ficheTechnique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fiche-techniques")
    public ResponseEntity<FicheTechnique> createFicheTechnique(@RequestBody FicheTechnique ficheTechnique) throws URISyntaxException {
        log.debug("REST request to save FicheTechnique : {}", ficheTechnique);
        if (ficheTechnique.getId() != null) {
            throw new BadRequestAlertException("A new ficheTechnique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FicheTechnique result = ficheTechniqueRepository.save(ficheTechnique);
        return ResponseEntity.created(new URI("/api/fiche-techniques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fiche-techniques} : Updates an existing ficheTechnique.
     *
     * @param ficheTechnique the ficheTechnique to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheTechnique,
     * or with status {@code 400 (Bad Request)} if the ficheTechnique is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ficheTechnique couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fiche-techniques")
    public ResponseEntity<FicheTechnique> updateFicheTechnique(@RequestBody FicheTechnique ficheTechnique) throws URISyntaxException {
        log.debug("REST request to update FicheTechnique : {}", ficheTechnique);
        if (ficheTechnique.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FicheTechnique result = ficheTechniqueRepository.save(ficheTechnique);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheTechnique.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fiche-techniques} : get all the ficheTechniques.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ficheTechniques in body.
     */
    @GetMapping("/fiche-techniques")
    public List<FicheTechnique> getAllFicheTechniques() {
        log.debug("REST request to get all FicheTechniques");
        return ficheTechniqueRepository.findAll();
    }

    /**
     * {@code GET  /fiche-techniques/:id} : get the "id" ficheTechnique.
     *
     * @param id the id of the ficheTechnique to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ficheTechnique, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fiche-techniques/{id}")
    public ResponseEntity<FicheTechnique> getFicheTechnique(@PathVariable Long id) {
        log.debug("REST request to get FicheTechnique : {}", id);
        Optional<FicheTechnique> ficheTechnique = ficheTechniqueRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(ficheTechnique);
    }

    /**
     * {@code DELETE  /fiche-techniques/:id} : delete the "id" ficheTechnique.
     *
     * @param id the id of the ficheTechnique to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fiche-techniques/{id}")
    public ResponseEntity<Void> deleteFicheTechnique(@PathVariable Long id) {
        log.debug("REST request to delete FicheTechnique : {}", id);
        ficheTechniqueRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
