package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.PrixProduit;
import bf.e_fixell_backoffice.repository.PrixProduitRepository;
import bf.e_fixell_backoffice.service.dto.PrixProduitDTO;
import bf.e_fixell_backoffice.service.mapper.PrixProduitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PrixProduit}.
 */
@Service
@Transactional
public class PrixProduitService {

    private final Logger log = LoggerFactory.getLogger(PrixProduitService.class);

    private final PrixProduitRepository prixProduitRepository;

    private final PrixProduitMapper prixProduitMapper;

    public PrixProduitService(PrixProduitRepository prixProduitRepository, PrixProduitMapper prixProduitMapper) {
        this.prixProduitRepository = prixProduitRepository;
        this.prixProduitMapper = prixProduitMapper;
    }

    /**
     * Save a prixProduit.
     *
     * @param prixProduitDTO the entity to save.
     * @return the persisted entity.
     */
    public PrixProduitDTO save(PrixProduitDTO prixProduitDTO) {
        log.debug("Request to save PrixProduit : {}", prixProduitDTO);
        PrixProduit prixProduit = prixProduitMapper.toEntity(prixProduitDTO);
        prixProduit = prixProduitRepository.save(prixProduit);
        return prixProduitMapper.toDto(prixProduit);
    }

    /**
     * Get all the prixProduits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PrixProduitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PrixProduits");
        return prixProduitRepository.findAll(pageable)
            .map(prixProduitMapper::toDto);
    }


    /**
     * Get one prixProduit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PrixProduitDTO> findOne(Long id) {
        log.debug("Request to get PrixProduit : {}", id);
        return prixProduitRepository.findById(id)
            .map(prixProduitMapper::toDto);
    }

    /**
     * Delete the prixProduit by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PrixProduit : {}", id);
        prixProduitRepository.deleteById(id);
    }
}
