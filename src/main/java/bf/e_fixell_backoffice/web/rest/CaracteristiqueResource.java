package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.domain.Caracteristique;
import bf.e_fixell_backoffice.repository.CaracteristiqueRepository;
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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.Caracteristique}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CaracteristiqueResource {

    private final Logger log = LoggerFactory.getLogger(CaracteristiqueResource.class);

    private static final String ENTITY_NAME = "caracteristique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaracteristiqueRepository caracteristiqueRepository;

    public CaracteristiqueResource(CaracteristiqueRepository caracteristiqueRepository) {
        this.caracteristiqueRepository = caracteristiqueRepository;
    }

    /**
     * {@code POST  /caracteristiques} : Create a new caracteristique.
     *
     * @param caracteristique the caracteristique to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caracteristique, or with status {@code 400 (Bad Request)} if the caracteristique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/caracteristiques")
    public ResponseEntity<Caracteristique> createCaracteristique(@RequestBody Caracteristique caracteristique) throws URISyntaxException {
        log.debug("REST request to save Caracteristique : {}", caracteristique);
        if (caracteristique.getId() != null) {
            throw new BadRequestAlertException("A new caracteristique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Caracteristique result = caracteristiqueRepository.save(caracteristique);
        return ResponseEntity.created(new URI("/api/caracteristiques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /caracteristiques} : Updates an existing caracteristique.
     *
     * @param caracteristique the caracteristique to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caracteristique,
     * or with status {@code 400 (Bad Request)} if the caracteristique is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caracteristique couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/caracteristiques")
    public ResponseEntity<Caracteristique> updateCaracteristique(@RequestBody Caracteristique caracteristique) throws URISyntaxException {
        log.debug("REST request to update Caracteristique : {}", caracteristique);
        if (caracteristique.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Caracteristique result = caracteristiqueRepository.save(caracteristique);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, caracteristique.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /caracteristiques} : get all the caracteristiques.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caracteristiques in body.
     */
    @GetMapping("/caracteristiques")
    public List<Caracteristique> getAllCaracteristiques() {
        log.debug("REST request to get all Caracteristiques");
        return caracteristiqueRepository.findAll();
    }

    /**
     * {@code GET  /caracteristiques/:id} : get the "id" caracteristique.
     *
     * @param id the id of the caracteristique to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caracteristique, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/caracteristiques/{id}")
    public ResponseEntity<Caracteristique> getCaracteristique(@PathVariable Long id) {
        log.debug("REST request to get Caracteristique : {}", id);
        Optional<Caracteristique> caracteristique = caracteristiqueRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(caracteristique);
    }

    /**
     * {@code DELETE  /caracteristiques/:id} : delete the "id" caracteristique.
     *
     * @param id the id of the caracteristique to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/caracteristiques/{id}")
    public ResponseEntity<Void> deleteCaracteristique(@PathVariable Long id) {
        log.debug("REST request to delete Caracteristique : {}", id);
        caracteristiqueRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
