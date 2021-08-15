package bf.e_fixell_backoffice.web.rest;

import bf.e_fixell_backoffice.service.PrixProduitService;
import bf.e_fixell_backoffice.web.rest.errors.BadRequestAlertException;
import bf.e_fixell_backoffice.service.dto.PrixProduitDTO;
import bf.e_fixell_backoffice.service.dto.PrixProduitCriteria;
import bf.e_fixell_backoffice.service.PrixProduitQueryService;

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
 * REST controller for managing {@link bf.e_fixell_backoffice.domain.PrixProduit}.
 */
@RestController
@RequestMapping("/api")
public class PrixProduitResource {

    private final Logger log = LoggerFactory.getLogger(PrixProduitResource.class);

    private static final String ENTITY_NAME = "prixProduit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrixProduitService prixProduitService;

    private final PrixProduitQueryService prixProduitQueryService;

    public PrixProduitResource(PrixProduitService prixProduitService, PrixProduitQueryService prixProduitQueryService) {
        this.prixProduitService = prixProduitService;
        this.prixProduitQueryService = prixProduitQueryService;
    }

    /**
     * {@code POST  /prix-produits} : Create a new prixProduit.
     *
     * @param prixProduitDTO the prixProduitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new prixProduitDTO, or with status {@code 400 (Bad Request)} if the prixProduit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/prix-produits")
    public ResponseEntity<PrixProduitDTO> createPrixProduit(@RequestBody PrixProduitDTO prixProduitDTO) throws URISyntaxException {
        log.debug("REST request to save PrixProduit : {}", prixProduitDTO);
        if (prixProduitDTO.getId() != null) {
            throw new BadRequestAlertException("A new prixProduit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrixProduitDTO result = prixProduitService.save(prixProduitDTO);
        return ResponseEntity.created(new URI("/api/prix-produits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /prix-produits} : Updates an existing prixProduit.
     *
     * @param prixProduitDTO the prixProduitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prixProduitDTO,
     * or with status {@code 400 (Bad Request)} if the prixProduitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the prixProduitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/prix-produits")
    public ResponseEntity<PrixProduitDTO> updatePrixProduit(@RequestBody PrixProduitDTO prixProduitDTO) throws URISyntaxException {
        log.debug("REST request to update PrixProduit : {}", prixProduitDTO);
        if (prixProduitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PrixProduitDTO result = prixProduitService.save(prixProduitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, prixProduitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /prix-produits} : get all the prixProduits.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of prixProduits in body.
     */
    @GetMapping("/prix-produits")
    public ResponseEntity<List<PrixProduitDTO>> getAllPrixProduits(PrixProduitCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PrixProduits by criteria: {}", criteria);
        Page<PrixProduitDTO> page = prixProduitQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /prix-produits/count} : count all the prixProduits.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/prix-produits/count")
    public ResponseEntity<Long> countPrixProduits(PrixProduitCriteria criteria) {
        log.debug("REST request to count PrixProduits by criteria: {}", criteria);
        return ResponseEntity.ok().body(prixProduitQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /prix-produits/:id} : get the "id" prixProduit.
     *
     * @param id the id of the prixProduitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the prixProduitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/prix-produits/{id}")
    public ResponseEntity<PrixProduitDTO> getPrixProduit(@PathVariable Long id) {
        log.debug("REST request to get PrixProduit : {}", id);
        Optional<PrixProduitDTO> prixProduitDTO = prixProduitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(prixProduitDTO);
    }

    /**
     * {@code DELETE  /prix-produits/:id} : delete the "id" prixProduit.
     *
     * @param id the id of the prixProduitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/prix-produits/{id}")
    public ResponseEntity<Void> deletePrixProduit(@PathVariable Long id) {
        log.debug("REST request to delete PrixProduit : {}", id);
        prixProduitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
