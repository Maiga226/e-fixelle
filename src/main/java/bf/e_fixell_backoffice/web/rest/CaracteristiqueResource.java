package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.service.CaracteristiqueService;
import bf.e_fixell_backoffice.web.rest.errors.BadRequestAlertException;
import bf.e_fixell_backoffice.service.dto.CaracteristiqueDTO;
import bf.e_fixell_backoffice.service.dto.CaracteristiqueCriteria;
import bf.e_fixell_backoffice.service.CaracteristiqueQueryService;

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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.Caracteristique}.
 */
@RestController
@RequestMapping("/api")
public class CaracteristiqueResource {

    private final Logger log = LoggerFactory.getLogger(CaracteristiqueResource.class);

    private static final String ENTITY_NAME = "caracteristique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaracteristiqueService caracteristiqueService;

    private final CaracteristiqueQueryService caracteristiqueQueryService;

    public CaracteristiqueResource(CaracteristiqueService caracteristiqueService, CaracteristiqueQueryService caracteristiqueQueryService) {
        this.caracteristiqueService = caracteristiqueService;
        this.caracteristiqueQueryService = caracteristiqueQueryService;
    }

    /**
     * {@code POST  /caracteristiques} : Create a new caracteristique.
     *
     * @param caracteristiqueDTO the caracteristiqueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caracteristiqueDTO, or with status {@code 400 (Bad Request)} if the caracteristique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/caracteristiques")
    public ResponseEntity<CaracteristiqueDTO> createCaracteristique(@RequestBody CaracteristiqueDTO caracteristiqueDTO) throws URISyntaxException {
        log.debug("REST request to save Caracteristique : {}", caracteristiqueDTO);
        if (caracteristiqueDTO.getId() != null) {
            throw new BadRequestAlertException("A new caracteristique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaracteristiqueDTO result = caracteristiqueService.save(caracteristiqueDTO);
        return ResponseEntity.created(new URI("/api/caracteristiques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /caracteristiques} : Updates an existing caracteristique.
     *
     * @param caracteristiqueDTO the caracteristiqueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caracteristiqueDTO,
     * or with status {@code 400 (Bad Request)} if the caracteristiqueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caracteristiqueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/caracteristiques")
    public ResponseEntity<CaracteristiqueDTO> updateCaracteristique(@RequestBody CaracteristiqueDTO caracteristiqueDTO) throws URISyntaxException {
        log.debug("REST request to update Caracteristique : {}", caracteristiqueDTO);
        if (caracteristiqueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaracteristiqueDTO result = caracteristiqueService.save(caracteristiqueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, caracteristiqueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /caracteristiques} : get all the caracteristiques.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caracteristiques in body.
     */
    @GetMapping("/caracteristiques")
    public ResponseEntity<List<CaracteristiqueDTO>> getAllCaracteristiques(CaracteristiqueCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Caracteristiques by criteria: {}", criteria);
        Page<CaracteristiqueDTO> page = caracteristiqueQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /caracteristiques/count} : count all the caracteristiques.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/caracteristiques/count")
    public ResponseEntity<Long> countCaracteristiques(CaracteristiqueCriteria criteria) {
        log.debug("REST request to count Caracteristiques by criteria: {}", criteria);
        return ResponseEntity.ok().body(caracteristiqueQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /caracteristiques/:id} : get the "id" caracteristique.
     *
     * @param id the id of the caracteristiqueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caracteristiqueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/caracteristiques/{id}")
    public ResponseEntity<CaracteristiqueDTO> getCaracteristique(@PathVariable Long id) {
        log.debug("REST request to get Caracteristique : {}", id);
        Optional<CaracteristiqueDTO> caracteristiqueDTO = caracteristiqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caracteristiqueDTO);
    }

    /**
     * {@code DELETE  /caracteristiques/:id} : delete the "id" caracteristique.
     *
     * @param id the id of the caracteristiqueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/caracteristiques/{id}")
    public ResponseEntity<Void> deleteCaracteristique(@PathVariable Long id) {
        log.debug("REST request to delete Caracteristique : {}", id);
        caracteristiqueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
