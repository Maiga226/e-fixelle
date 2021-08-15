package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.service.FraisService;
import bf.e_fixell_backoffice.web.rest.errors.BadRequestAlertException;
import bf.e_fixell_backoffice.service.dto.FraisDTO;
import bf.e_fixell_backoffice.service.dto.FraisCriteria;
import bf.e_fixell_backoffice.service.FraisQueryService;

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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.Frais}.
 */
@RestController
@RequestMapping("/api")
public class FraisResource {

    private final Logger log = LoggerFactory.getLogger(FraisResource.class);

    private static final String ENTITY_NAME = "frais";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FraisService fraisService;

    private final FraisQueryService fraisQueryService;

    public FraisResource(FraisService fraisService, FraisQueryService fraisQueryService) {
        this.fraisService = fraisService;
        this.fraisQueryService = fraisQueryService;
    }

    /**
     * {@code POST  /frais} : Create a new frais.
     *
     * @param fraisDTO the fraisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fraisDTO, or with status {@code 400 (Bad Request)} if the frais has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/frais")
    public ResponseEntity<FraisDTO> createFrais(@RequestBody FraisDTO fraisDTO) throws URISyntaxException {
        log.debug("REST request to save Frais : {}", fraisDTO);
        if (fraisDTO.getId() != null) {
            throw new BadRequestAlertException("A new frais cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FraisDTO result = fraisService.save(fraisDTO);
        return ResponseEntity.created(new URI("/api/frais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /frais} : Updates an existing frais.
     *
     * @param fraisDTO the fraisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fraisDTO,
     * or with status {@code 400 (Bad Request)} if the fraisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fraisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/frais")
    public ResponseEntity<FraisDTO> updateFrais(@RequestBody FraisDTO fraisDTO) throws URISyntaxException {
        log.debug("REST request to update Frais : {}", fraisDTO);
        if (fraisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FraisDTO result = fraisService.save(fraisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fraisDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /frais} : get all the frais.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of frais in body.
     */
    @GetMapping("/frais")
    public ResponseEntity<List<FraisDTO>> getAllFrais(FraisCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Frais by criteria: {}", criteria);
        Page<FraisDTO> page = fraisQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /frais/count} : count all the frais.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/frais/count")
    public ResponseEntity<Long> countFrais(FraisCriteria criteria) {
        log.debug("REST request to count Frais by criteria: {}", criteria);
        return ResponseEntity.ok().body(fraisQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /frais/:id} : get the "id" frais.
     *
     * @param id the id of the fraisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fraisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/frais/{id}")
    public ResponseEntity<FraisDTO> getFrais(@PathVariable Long id) {
        log.debug("REST request to get Frais : {}", id);
        Optional<FraisDTO> fraisDTO = fraisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fraisDTO);
    }

    /**
     * {@code DELETE  /frais/:id} : delete the "id" frais.
     *
     * @param id the id of the fraisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/frais/{id}")
    public ResponseEntity<Void> deleteFrais(@PathVariable Long id) {
        log.debug("REST request to delete Frais : {}", id);
        fraisService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
