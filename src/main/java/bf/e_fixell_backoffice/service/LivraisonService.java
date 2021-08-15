package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.Livraison;
import bf.e_fixell_backoffice.repository.LivraisonRepository;
import bf.e_fixell_backoffice.service.dto.LivraisonDTO;
import bf.e_fixell_backoffice.service.mapper.LivraisonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Livraison}.
 */
@Service
@Transactional
public class LivraisonService {

    private final Logger log = LoggerFactory.getLogger(LivraisonService.class);

    private final LivraisonRepository livraisonRepository;

    private final LivraisonMapper livraisonMapper;

    public LivraisonService(LivraisonRepository livraisonRepository, LivraisonMapper livraisonMapper) {
        this.livraisonRepository = livraisonRepository;
        this.livraisonMapper = livraisonMapper;
    }

    /**
     * Save a livraison.
     *
     * @param livraisonDTO the entity to save.
     * @return the persisted entity.
     */
    public LivraisonDTO save(LivraisonDTO livraisonDTO) {
        log.debug("Request to save Livraison : {}", livraisonDTO);
        Livraison livraison = livraisonMapper.toEntity(livraisonDTO);
        livraison = livraisonRepository.save(livraison);
        return livraisonMapper.toDto(livraison);
    }

    /**
     * Get all the livraisons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LivraisonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Livraisons");
        return livraisonRepository.findAll(pageable)
            .map(livraisonMapper::toDto);
    }


    /**
     * Get one livraison by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LivraisonDTO> findOne(Long id) {
        log.debug("Request to get Livraison : {}", id);
        return livraisonRepository.findById(id)
            .map(livraisonMapper::toDto);
    }

    /**
     * Delete the livraison by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Livraison : {}", id);
        livraisonRepository.deleteById(id);
    }
}
