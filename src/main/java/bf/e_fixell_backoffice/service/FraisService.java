package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.Frais;
import bf.e_fixell_backoffice.repository.FraisRepository;
import bf.e_fixell_backoffice.service.dto.FraisDTO;
import bf.e_fixell_backoffice.service.mapper.FraisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Frais}.
 */
@Service
@Transactional
public class FraisService {

    private final Logger log = LoggerFactory.getLogger(FraisService.class);

    private final FraisRepository fraisRepository;

    private final FraisMapper fraisMapper;

    public FraisService(FraisRepository fraisRepository, FraisMapper fraisMapper) {
        this.fraisRepository = fraisRepository;
        this.fraisMapper = fraisMapper;
    }

    /**
     * Save a frais.
     *
     * @param fraisDTO the entity to save.
     * @return the persisted entity.
     */
    public FraisDTO save(FraisDTO fraisDTO) {
        log.debug("Request to save Frais : {}", fraisDTO);
        Frais frais = fraisMapper.toEntity(fraisDTO);
        frais = fraisRepository.save(frais);
        return fraisMapper.toDto(frais);
    }

    /**
     * Get all the frais.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FraisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Frais");
        return fraisRepository.findAll(pageable)
            .map(fraisMapper::toDto);
    }


    /**
     * Get one frais by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FraisDTO> findOne(Long id) {
        log.debug("Request to get Frais : {}", id);
        return fraisRepository.findById(id)
            .map(fraisMapper::toDto);
    }

    /**
     * Delete the frais by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Frais : {}", id);
        fraisRepository.deleteById(id);
    }
}
