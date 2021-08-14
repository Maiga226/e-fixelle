package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.domain.TypeDepense;
import bf.e_fixell_backoffice.repository.TypeDepenseRepository;
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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.TypeDepense}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TypeDepenseResource {

    private final Logger log = LoggerFactory.getLogger(TypeDepenseResource.class);

    private static final String ENTITY_NAME = "typeDepense";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeDepenseRepository typeDepenseRepository;

    public TypeDepenseResource(TypeDepenseRepository typeDepenseRepository) {
        this.typeDepenseRepository = typeDepenseRepository;
    }

    /**
     * {@code POST  /type-depenses} : Create a new typeDepense.
     *
     * @param typeDepense the typeDepense to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeDepense, or with status {@code 400 (Bad Request)} if the typeDepense has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-depenses")
    public ResponseEntity<TypeDepense> createTypeDepense(@RequestBody TypeDepense typeDepense) throws URISyntaxException {
        log.debug("REST request to save TypeDepense : {}", typeDepense);
        if (typeDepense.getId() != null) {
            throw new BadRequestAlertException("A new typeDepense cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeDepense result = typeDepenseRepository.save(typeDepense);
        return ResponseEntity.created(new URI("/api/type-depenses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-depenses} : Updates an existing typeDepense.
     *
     * @param typeDepense the typeDepense to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeDepense,
     * or with status {@code 400 (Bad Request)} if the typeDepense is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeDepense couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-depenses")
    public ResponseEntity<TypeDepense> updateTypeDepense(@RequestBody TypeDepense typeDepense) throws URISyntaxException {
        log.debug("REST request to update TypeDepense : {}", typeDepense);
        if (typeDepense.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeDepense result = typeDepenseRepository.save(typeDepense);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, typeDepense.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-depenses} : get all the typeDepenses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeDepenses in body.
     */
    @GetMapping("/type-depenses")
    public List<TypeDepense> getAllTypeDepenses() {
        log.debug("REST request to get all TypeDepenses");
        return typeDepenseRepository.findAll();
    }

    /**
     * {@code GET  /type-depenses/:id} : get the "id" typeDepense.
     *
     * @param id the id of the typeDepense to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeDepense, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-depenses/{id}")
    public ResponseEntity<TypeDepense> getTypeDepense(@PathVariable Long id) {
        log.debug("REST request to get TypeDepense : {}", id);
        Optional<TypeDepense> typeDepense = typeDepenseRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(typeDepense);
    }

    /**
     * {@code DELETE  /type-depenses/:id} : delete the "id" typeDepense.
     *
     * @param id the id of the typeDepense to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-depenses/{id}")
    public ResponseEntity<Void> deleteTypeDepense(@PathVariable Long id) {
        log.debug("REST request to delete TypeDepense : {}", id);
        typeDepenseRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
