package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.service.FicheTechniqueService;
import bf.e_fixell_backoffice.web.rest.errors.BadRequestAlertException;
import bf.e_fixell_backoffice.service.dto.FicheTechniqueDTO;
import bf.e_fixell_backoffice.service.dto.FicheTechniqueCriteria;
import bf.e_fixell_backoffice.service.FicheTechniqueQueryService;

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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.FicheTechnique}.
 */
@RestController
@RequestMapping("/api")
public class FicheTechniqueResource {

    private final Logger log = LoggerFactory.getLogger(FicheTechniqueResource.class);

    private static final String ENTITY_NAME = "ficheTechnique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FicheTechniqueService ficheTechniqueService;

    private final FicheTechniqueQueryService ficheTechniqueQueryService;

    public FicheTechniqueResource(FicheTechniqueService ficheTechniqueService, FicheTechniqueQueryService ficheTechniqueQueryService) {
        this.ficheTechniqueService = ficheTechniqueService;
        this.ficheTechniqueQueryService = ficheTechniqueQueryService;
    }

    /**
     * {@code POST  /fiche-techniques} : Create a new ficheTechnique.
     *
     * @param ficheTechniqueDTO the ficheTechniqueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ficheTechniqueDTO, or with status {@code 400 (Bad Request)} if the ficheTechnique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fiche-techniques")
    public ResponseEntity<FicheTechniqueDTO> createFicheTechnique(@RequestBody FicheTechniqueDTO ficheTechniqueDTO) throws URISyntaxException {
        log.debug("REST request to save FicheTechnique : {}", ficheTechniqueDTO);
        if (ficheTechniqueDTO.getId() != null) {
            throw new BadRequestAlertException("A new ficheTechnique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FicheTechniqueDTO result = ficheTechniqueService.save(ficheTechniqueDTO);
        return ResponseEntity.created(new URI("/api/fiche-techniques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fiche-techniques} : Updates an existing ficheTechnique.
     *
     * @param ficheTechniqueDTO the ficheTechniqueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheTechniqueDTO,
     * or with status {@code 400 (Bad Request)} if the ficheTechniqueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ficheTechniqueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fiche-techniques")
    public ResponseEntity<FicheTechniqueDTO> updateFicheTechnique(@RequestBody FicheTechniqueDTO ficheTechniqueDTO) throws URISyntaxException {
        log.debug("REST request to update FicheTechnique : {}", ficheTechniqueDTO);
        if (ficheTechniqueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FicheTechniqueDTO result = ficheTechniqueService.save(ficheTechniqueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheTechniqueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fiche-techniques} : get all the ficheTechniques.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ficheTechniques in body.
     */
    @GetMapping("/fiche-techniques")
    public ResponseEntity<List<FicheTechniqueDTO>> getAllFicheTechniques(FicheTechniqueCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FicheTechniques by criteria: {}", criteria);
        Page<FicheTechniqueDTO> page = ficheTechniqueQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fiche-techniques/count} : count all the ficheTechniques.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/fiche-techniques/count")
    public ResponseEntity<Long> countFicheTechniques(FicheTechniqueCriteria criteria) {
        log.debug("REST request to count FicheTechniques by criteria: {}", criteria);
        return ResponseEntity.ok().body(ficheTechniqueQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /fiche-techniques/:id} : get the "id" ficheTechnique.
     *
     * @param id the id of the ficheTechniqueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ficheTechniqueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fiche-techniques/{id}")
    public ResponseEntity<FicheTechniqueDTO> getFicheTechnique(@PathVariable Long id) {
        log.debug("REST request to get FicheTechnique : {}", id);
        Optional<FicheTechniqueDTO> ficheTechniqueDTO = ficheTechniqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ficheTechniqueDTO);
    }

    /**
     * {@code DELETE  /fiche-techniques/:id} : delete the "id" ficheTechnique.
     *
     * @param id the id of the ficheTechniqueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fiche-techniques/{id}")
    public ResponseEntity<Void> deleteFicheTechnique(@PathVariable Long id) {
        log.debug("REST request to delete FicheTechnique : {}", id);
        ficheTechniqueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
