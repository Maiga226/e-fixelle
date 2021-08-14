package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.domain.TypeFrais;
import bf.e_fixell_backoffice.repository.TypeFraisRepository;
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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.TypeFrais}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TypeFraisResource {

    private final Logger log = LoggerFactory.getLogger(TypeFraisResource.class);

    private static final String ENTITY_NAME = "typeFrais";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeFraisRepository typeFraisRepository;

    public TypeFraisResource(TypeFraisRepository typeFraisRepository) {
        this.typeFraisRepository = typeFraisRepository;
    }

    /**
     * {@code POST  /type-frais} : Create a new typeFrais.
     *
     * @param typeFrais the typeFrais to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeFrais, or with status {@code 400 (Bad Request)} if the typeFrais has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-frais")
    public ResponseEntity<TypeFrais> createTypeFrais(@RequestBody TypeFrais typeFrais) throws URISyntaxException {
        log.debug("REST request to save TypeFrais : {}", typeFrais);
        if (typeFrais.getId() != null) {
            throw new BadRequestAlertException("A new typeFrais cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeFrais result = typeFraisRepository.save(typeFrais);
        return ResponseEntity.created(new URI("/api/type-frais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-frais} : Updates an existing typeFrais.
     *
     * @param typeFrais the typeFrais to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeFrais,
     * or with status {@code 400 (Bad Request)} if the typeFrais is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeFrais couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-frais")
    public ResponseEntity<TypeFrais> updateTypeFrais(@RequestBody TypeFrais typeFrais) throws URISyntaxException {
        log.debug("REST request to update TypeFrais : {}", typeFrais);
        if (typeFrais.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeFrais result = typeFraisRepository.save(typeFrais);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, typeFrais.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-frais} : get all the typeFrais.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeFrais in body.
     */
    @GetMapping("/type-frais")
    public List<TypeFrais> getAllTypeFrais() {
        log.debug("REST request to get all TypeFrais");
        return typeFraisRepository.findAll();
    }

    /**
     * {@code GET  /type-frais/:id} : get the "id" typeFrais.
     *
     * @param id the id of the typeFrais to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeFrais, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-frais/{id}")
    public ResponseEntity<TypeFrais> getTypeFrais(@PathVariable Long id) {
        log.debug("REST request to get TypeFrais : {}", id);
        Optional<TypeFrais> typeFrais = typeFraisRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(typeFrais);
    }

    /**
     * {@code DELETE  /type-frais/:id} : delete the "id" typeFrais.
     *
     * @param id the id of the typeFrais to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-frais/{id}")
    public ResponseEntity<Void> deleteTypeFrais(@PathVariable Long id) {
        log.debug("REST request to delete TypeFrais : {}", id);
        typeFraisRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
