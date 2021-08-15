package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.UniteOrganisation;
import bf.e_fixell_backoffice.repository.UniteOrganisationRepository;
import bf.e_fixell_backoffice.service.dto.UniteOrganisationDTO;
import bf.e_fixell_backoffice.service.mapper.UniteOrganisationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UniteOrganisation}.
 */
@Service
@Transactional
public class UniteOrganisationService {

    private final Logger log = LoggerFactory.getLogger(UniteOrganisationService.class);

    private final UniteOrganisationRepository uniteOrganisationRepository;

    private final UniteOrganisationMapper uniteOrganisationMapper;

    public UniteOrganisationService(UniteOrganisationRepository uniteOrganisationRepository, UniteOrganisationMapper uniteOrganisationMapper) {
        this.uniteOrganisationRepository = uniteOrganisationRepository;
        this.uniteOrganisationMapper = uniteOrganisationMapper;
    }

    /**
     * Save a uniteOrganisation.
     *
     * @param uniteOrganisationDTO the entity to save.
     * @return the persisted entity.
     */
    public UniteOrganisationDTO save(UniteOrganisationDTO uniteOrganisationDTO) {
        log.debug("Request to save UniteOrganisation : {}", uniteOrganisationDTO);
        UniteOrganisation uniteOrganisation = uniteOrganisationMapper.toEntity(uniteOrganisationDTO);
        uniteOrganisation = uniteOrganisationRepository.save(uniteOrganisation);
        return uniteOrganisationMapper.toDto(uniteOrganisation);
    }

    /**
     * Get all the uniteOrganisations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<UniteOrganisationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UniteOrganisations");
        return uniteOrganisationRepository.findAll(pageable)
            .map(uniteOrganisationMapper::toDto);
    }


    /**
     * Get one uniteOrganisation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UniteOrganisationDTO> findOne(Long id) {
        log.debug("Request to get UniteOrganisation : {}", id);
        return uniteOrganisationRepository.findById(id)
            .map(uniteOrganisationMapper::toDto);
    }

    /**
     * Delete the uniteOrganisation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UniteOrganisation : {}", id);
        uniteOrganisationRepository.deleteById(id);
    }
}
