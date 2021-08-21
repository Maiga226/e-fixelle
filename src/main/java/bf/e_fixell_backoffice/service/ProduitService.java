package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.Produit;
import bf.e_fixell_backoffice.repository.ProduitRepository;
import bf.e_fixell_backoffice.service.dto.ProduitDTO;
import bf.e_fixell_backoffice.service.mapper.ProduitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Produit}.
 */
@Service
@Transactional
public class ProduitService {

    private final Logger log = LoggerFactory.getLogger(ProduitService.class);

    private final ProduitRepository produitRepository;

    private final ProduitMapper produitMapper;

    public ProduitService(ProduitRepository produitRepository, ProduitMapper produitMapper) {
        this.produitRepository = produitRepository;
        this.produitMapper = produitMapper;
    }

    /**
     * Save a produit.
     *
     * @param produitDTO the entity to save.
     * @return the persisted entity.
     */
    public ProduitDTO save(ProduitDTO produitDTO) {
        log.debug("Request to save Produit : {}", produitDTO);
        Produit produit = produitMapper.toEntity(produitDTO);
        produit = produitRepository.save(produit);
        return produitMapper.toDto(produit);
    }

    /**
     * Get all the produits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProduitDTO> findAll() {
        log.debug("Request to get all Produits");
        return produitRepository.findAll()
            .stream().map(produitMapper::toDto).collect(Collectors.toList());
    }


    /**
     * Get one produit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProduitDTO> findOne(Long id) {
        log.debug("Request to get Produit : {}", id);
        return produitRepository.findById(id)
            .map(produitMapper::toDto);
    }

    /**
     * Delete the produit by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Produit : {}", id);
        produitRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<ProduitDTO> findWithCriteria(Pageable pageable,ProduitDTO produitDTO) {
        return produitRepository.findWithCriteria(pageable,produitDTO.getCode(),produitDTO.getLibelle(),produitDTO.getCategorieId(),produitDTO.getClassificationId()).map(ProduitDTO::new);
    }

    public void deleteProduitById(Long id) {
        produitRepository.findById(id).ifPresent(client -> {
            produitRepository.deleteById(id);
            log.debug("Deleted User: {}", client);
        });
    }
}
