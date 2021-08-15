package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.Perte;
import bf.e_fixell_backoffice.repository.PerteRepository;
import bf.e_fixell_backoffice.service.dto.PerteDTO;
import bf.e_fixell_backoffice.service.mapper.PerteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Perte}.
 */
@Service
@Transactional
public class PerteService {

    private final Logger log = LoggerFactory.getLogger(PerteService.class);

    private final PerteRepository perteRepository;

    private final PerteMapper perteMapper;

    public PerteService(PerteRepository perteRepository, PerteMapper perteMapper) {
        this.perteRepository = perteRepository;
        this.perteMapper = perteMapper;
    }

    /**
     * Save a perte.
     *
     * @param perteDTO the entity to save.
     * @return the persisted entity.
     */
    public PerteDTO save(PerteDTO perteDTO) {
        log.debug("Request to save Perte : {}", perteDTO);
        Perte perte = perteMapper.toEntity(perteDTO);
        perte = perteRepository.save(perte);
        return perteMapper.toDto(perte);
    }

    /**
     * Get all the pertes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PerteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pertes");
        return perteRepository.findAll(pageable)
            .map(perteMapper::toDto);
    }


    /**
     * Get one perte by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PerteDTO> findOne(Long id) {
        log.debug("Request to get Perte : {}", id);
        return perteRepository.findById(id)
            .map(perteMapper::toDto);
    }

    /**
     * Delete the perte by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Perte : {}", id);
        perteRepository.deleteById(id);
    }
}
