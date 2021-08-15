package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.service.TypeFraisService;
import bf.e_fixell_backoffice.web.rest.errors.BadRequestAlertException;
import bf.e_fixell_backoffice.service.dto.TypeFraisDTO;
import bf.e_fixell_backoffice.service.dto.TypeFraisCriteria;
import bf.e_fixell_backoffice.service.TypeFraisQueryService;

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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.TypeFrais}.
 */
@RestController
@RequestMapping("/api")
public class TypeFraisResource {

    private final Logger log = LoggerFactory.getLogger(TypeFraisResource.class);

    private static final String ENTITY_NAME = "typeFrais";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeFraisService typeFraisService;

    private final TypeFraisQueryService typeFraisQueryService;

    public TypeFraisResource(TypeFraisService typeFraisService, TypeFraisQueryService typeFraisQueryService) {
        this.typeFraisService = typeFraisService;
        this.typeFraisQueryService = typeFraisQueryService;
    }

    /**
     * {@code POST  /type-frais} : Create a new typeFrais.
     *
     * @param typeFraisDTO the typeFraisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeFraisDTO, or with status {@code 400 (Bad Request)} if the typeFrais has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-frais")
    public ResponseEntity<TypeFraisDTO> createTypeFrais(@RequestBody TypeFraisDTO typeFraisDTO) throws URISyntaxException {
        log.debug("REST request to save TypeFrais : {}", typeFraisDTO);
        if (typeFraisDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeFrais cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeFraisDTO result = typeFraisService.save(typeFraisDTO);
        return ResponseEntity.created(new URI("/api/type-frais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-frais} : Updates an existing typeFrais.
     *
     * @param typeFraisDTO the typeFraisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeFraisDTO,
     * or with status {@code 400 (Bad Request)} if the typeFraisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeFraisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-frais")
    public ResponseEntity<TypeFraisDTO> updateTypeFrais(@RequestBody TypeFraisDTO typeFraisDTO) throws URISyntaxException {
        log.debug("REST request to update TypeFrais : {}", typeFraisDTO);
        if (typeFraisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeFraisDTO result = typeFraisService.save(typeFraisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, typeFraisDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-frais} : get all the typeFrais.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeFrais in body.
     */
    @GetMapping("/type-frais")
    public ResponseEntity<List<TypeFraisDTO>> getAllTypeFrais(TypeFraisCriteria criteria, Pageable pageable) {
        log.debug("REST request to get TypeFrais by criteria: {}", criteria);
        Page<TypeFraisDTO> page = typeFraisQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-frais/count} : count all the typeFrais.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/type-frais/count")
    public ResponseEntity<Long> countTypeFrais(TypeFraisCriteria criteria) {
        log.debug("REST request to count TypeFrais by criteria: {}", criteria);
        return ResponseEntity.ok().body(typeFraisQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /type-frais/:id} : get the "id" typeFrais.
     *
     * @param id the id of the typeFraisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeFraisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-frais/{id}")
    public ResponseEntity<TypeFraisDTO> getTypeFrais(@PathVariable Long id) {
        log.debug("REST request to get TypeFrais : {}", id);
        Optional<TypeFraisDTO> typeFraisDTO = typeFraisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeFraisDTO);
    }

    /**
     * {@code DELETE  /type-frais/:id} : delete the "id" typeFrais.
     *
     * @param id the id of the typeFraisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-frais/{id}")
    public ResponseEntity<Void> deleteTypeFrais(@PathVariable Long id) {
        log.debug("REST request to delete TypeFrais : {}", id);
        typeFraisService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
