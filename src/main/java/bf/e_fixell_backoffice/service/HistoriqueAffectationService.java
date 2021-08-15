package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.HistoriqueAffectation;
import bf.e_fixell_backoffice.repository.HistoriqueAffectationRepository;
import bf.e_fixell_backoffice.service.dto.HistoriqueAffectationDTO;
import bf.e_fixell_backoffice.service.mapper.HistoriqueAffectationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link HistoriqueAffectation}.
 */
@Service
@Transactional
public class HistoriqueAffectationService {

    private final Logger log = LoggerFactory.getLogger(HistoriqueAffectationService.class);

    private final HistoriqueAffectationRepository historiqueAffectationRepository;

    private final HistoriqueAffectationMapper historiqueAffectationMapper;

    public HistoriqueAffectationService(HistoriqueAffectationRepository historiqueAffectationRepository, HistoriqueAffectationMapper historiqueAffectationMapper) {
        this.historiqueAffectationRepository = historiqueAffectationRepository;
        this.historiqueAffectationMapper = historiqueAffectationMapper;
    }

    /**
     * Save a historiqueAffectation.
     *
     * @param historiqueAffectationDTO the entity to save.
     * @return the persisted entity.
     */
    public HistoriqueAffectationDTO save(HistoriqueAffectationDTO historiqueAffectationDTO) {
        log.debug("Request to save HistoriqueAffectation : {}", historiqueAffectationDTO);
        HistoriqueAffectation historiqueAffectation = historiqueAffectationMapper.toEntity(historiqueAffectationDTO);
        historiqueAffectation = historiqueAffectationRepository.save(historiqueAffectation);
        return historiqueAffectationMapper.toDto(historiqueAffectation);
    }

    /**
     * Get all the historiqueAffectations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<HistoriqueAffectationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HistoriqueAffectations");
        return historiqueAffectationRepository.findAll(pageable)
            .map(historiqueAffectationMapper::toDto);
    }


    /**
     * Get one historiqueAffectation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<HistoriqueAffectationDTO> findOne(Long id) {
        log.debug("Request to get HistoriqueAffectation : {}", id);
        return historiqueAffectationRepository.findById(id)
            .map(historiqueAffectationMapper::toDto);
    }

    /**
     * Delete the historiqueAffectation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete HistoriqueAffectation : {}", id);
        historiqueAffectationRepository.deleteById(id);
    }
}
