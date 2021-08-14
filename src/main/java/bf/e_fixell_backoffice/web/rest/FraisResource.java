package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.domain.Frais;
import bf.e_fixell_backoffice.repository.FraisRepository;
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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.Frais}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FraisResource {

    private final Logger log = LoggerFactory.getLogger(FraisResource.class);

    private static final String ENTITY_NAME = "frais";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FraisRepository fraisRepository;

    public FraisResource(FraisRepository fraisRepository) {
        this.fraisRepository = fraisRepository;
    }

    /**
     * {@code POST  /frais} : Create a new frais.
     *
     * @param frais the frais to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new frais, or with status {@code 400 (Bad Request)} if the frais has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/frais")
    public ResponseEntity<Frais> createFrais(@RequestBody Frais frais) throws URISyntaxException {
        log.debug("REST request to save Frais : {}", frais);
        if (frais.getId() != null) {
            throw new BadRequestAlertException("A new frais cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Frais result = fraisRepository.save(frais);
        return ResponseEntity.created(new URI("/api/frais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /frais} : Updates an existing frais.
     *
     * @param frais the frais to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated frais,
     * or with status {@code 400 (Bad Request)} if the frais is not valid,
     * or with status {@code 500 (Internal Server Error)} if the frais couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/frais")
    public ResponseEntity<Frais> updateFrais(@RequestBody Frais frais) throws URISyntaxException {
        log.debug("REST request to update Frais : {}", frais);
        if (frais.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Frais result = fraisRepository.save(frais);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, frais.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /frais} : get all the frais.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of frais in body.
     */
    @GetMapping("/frais")
    public List<Frais> getAllFrais() {
        log.debug("REST request to get all Frais");
        return fraisRepository.findAll();
    }

    /**
     * {@code GET  /frais/:id} : get the "id" frais.
     *
     * @param id the id of the frais to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the frais, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/frais/{id}")
    public ResponseEntity<Frais> getFrais(@PathVariable Long id) {
        log.debug("REST request to get Frais : {}", id);
        Optional<Frais> frais = fraisRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(frais);
    }

    /**
     * {@code DELETE  /frais/:id} : delete the "id" frais.
     *
     * @param id the id of the frais to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/frais/{id}")
    public ResponseEntity<Void> deleteFrais(@PathVariable Long id) {
        log.debug("REST request to delete Frais : {}", id);
        fraisRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
