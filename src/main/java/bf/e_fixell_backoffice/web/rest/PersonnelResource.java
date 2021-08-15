package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.service.PersonnelService;
import bf.e_fixell_backoffice.web.rest.errors.BadRequestAlertException;
import bf.e_fixell_backoffice.service.dto.PersonnelDTO;
import bf.e_fixell_backoffice.service.dto.PersonnelCriteria;
import bf.e_fixell_backoffice.service.PersonnelQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
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
public class PersonnelResource {

    private final Logger log = LoggerFactory.getLogger(PersonnelResource.class);

    private static final String ENTITY_NAME = "personnel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonnelService personnelService;

    private final PersonnelQueryService personnelQueryService;

    public PersonnelResource(PersonnelService personnelService, PersonnelQueryService personnelQueryService) {
        this.personnelService = personnelService;
        this.personnelQueryService = personnelQueryService;
    }

    /**
     * {@code POST  /personnel} : Create a new personnel.
     *
     * @param personnelDTO the personnelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personnelDTO, or with status {@code 400 (Bad Request)} if the personnel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/personnel")
    public ResponseEntity<PersonnelDTO> createPersonnel(@RequestBody PersonnelDTO personnelDTO) throws URISyntaxException {
        log.debug("REST request to save Personnel : {}", personnelDTO);
        if (personnelDTO.getId() != null) {
            throw new BadRequestAlertException("A new personnel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonnelDTO result = personnelService.save(personnelDTO);
        return ResponseEntity.created(new URI("/api/personnel/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /personnel} : Updates an existing personnel.
     *
     * @param personnelDTO the personnelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personnelDTO,
     * or with status {@code 400 (Bad Request)} if the personnelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personnelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/personnel")
    public ResponseEntity<PersonnelDTO> updatePersonnel(@RequestBody PersonnelDTO personnelDTO) throws URISyntaxException {
        log.debug("REST request to update Personnel : {}", personnelDTO);
        if (personnelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PersonnelDTO result = personnelService.save(personnelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, personnelDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /personnel} : get all the personnel.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personnel in body.
     */
    @GetMapping("/personnel")
    public ResponseEntity<List<PersonnelDTO>> getAllPersonnel(PersonnelCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Personnel by criteria: {}", criteria);
        Page<PersonnelDTO> page = personnelQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /personnel/count} : count all the personnel.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/personnel/count")
    public ResponseEntity<Long> countPersonnel(PersonnelCriteria criteria) {
        log.debug("REST request to count Personnel by criteria: {}", criteria);
        return ResponseEntity.ok().body(personnelQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /personnel/:id} : get the "id" personnel.
     *
     * @param id the id of the personnelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personnelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/personnel/{id}")
    public ResponseEntity<PersonnelDTO> getPersonnel(@PathVariable Long id) {
        log.debug("REST request to get Personnel : {}", id);
        Optional<PersonnelDTO> personnelDTO = personnelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(personnelDTO);
    }

    /**
     * {@code DELETE  /personnel/:id} : delete the "id" personnel.
     *
     * @param id the id of the personnelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/personnel/{id}")
    public ResponseEntity<Void> deletePersonnel(@PathVariable Long id) {
        log.debug("REST request to delete Personnel : {}", id);
        personnelService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
