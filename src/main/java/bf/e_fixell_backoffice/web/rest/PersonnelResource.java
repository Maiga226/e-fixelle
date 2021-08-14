package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.domain.Personnel;
import bf.e_fixell_backoffice.repository.PersonnelRepository;
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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.Personnel}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PersonnelResource {

    private final Logger log = LoggerFactory.getLogger(PersonnelResource.class);

    private static final String ENTITY_NAME = "personnel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonnelRepository personnelRepository;

    public PersonnelResource(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }

    /**
     * {@code POST  /personnel} : Create a new personnel.
     *
     * @param personnel the personnel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personnel, or with status {@code 400 (Bad Request)} if the personnel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/personnel")
    public ResponseEntity<Personnel> createPersonnel(@RequestBody Personnel personnel) throws URISyntaxException {
        log.debug("REST request to save Personnel : {}", personnel);
        if (personnel.getId() != null) {
            throw new BadRequestAlertException("A new personnel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Personnel result = personnelRepository.save(personnel);
        return ResponseEntity.created(new URI("/api/personnel/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /personnel} : Updates an existing personnel.
     *
     * @param personnel the personnel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personnel,
     * or with status {@code 400 (Bad Request)} if the personnel is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personnel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/personnel")
    public ResponseEntity<Personnel> updatePersonnel(@RequestBody Personnel personnel) throws URISyntaxException {
        log.debug("REST request to update Personnel : {}", personnel);
        if (personnel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Personnel result = personnelRepository.save(personnel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, personnel.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /personnel} : get all the personnel.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personnel in body.
     */
    @GetMapping("/personnel")
    public List<Personnel> getAllPersonnel() {
        log.debug("REST request to get all Personnel");
        return personnelRepository.findAll();
    }

    /**
     * {@code GET  /personnel/:id} : get the "id" personnel.
     *
     * @param id the id of the personnel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personnel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/personnel/{id}")
    public ResponseEntity<Personnel> getPersonnel(@PathVariable Long id) {
        log.debug("REST request to get Personnel : {}", id);
        Optional<Personnel> personnel = personnelRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(personnel);
    }

    /**
     * {@code DELETE  /personnel/:id} : delete the "id" personnel.
     *
     * @param id the id of the personnel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/personnel/{id}")
    public ResponseEntity<Void> deletePersonnel(@PathVariable Long id) {
        log.debug("REST request to delete Personnel : {}", id);
        personnelRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
