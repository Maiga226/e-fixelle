package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.Personnel;
import bf.e_fixell_backoffice.repository.PersonnelRepository;
import bf.e_fixell_backoffice.service.dto.PersonnelDTO;
import bf.e_fixell_backoffice.service.mapper.PersonnelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Personnel}.
 */
@Service
@Transactional
public class PersonnelService {

    private final Logger log = LoggerFactory.getLogger(PersonnelService.class);

    private final PersonnelRepository personnelRepository;

    private final PersonnelMapper personnelMapper;

    public PersonnelService(PersonnelRepository personnelRepository, PersonnelMapper personnelMapper) {
        this.personnelRepository = personnelRepository;
        this.personnelMapper = personnelMapper;
    }

    /**
     * Save a personnel.
     *
     * @param personnelDTO the entity to save.
     * @return the persisted entity.
     */
    public PersonnelDTO save(PersonnelDTO personnelDTO) {
        log.debug("Request to save Personnel : {}", personnelDTO);
        Personnel personnel = personnelMapper.toEntity(personnelDTO);
        personnel = personnelRepository.save(personnel);
        return personnelMapper.toDto(personnel);
    }

    /**
     * Get all the personnel.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PersonnelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Personnel");
        return personnelRepository.findAll(pageable)
            .map(personnelMapper::toDto);
    }


    /**
     * Get one personnel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PersonnelDTO> findOne(Long id) {
        log.debug("Request to get Personnel : {}", id);
        return personnelRepository.findById(id)
            .map(personnelMapper::toDto);
    }

    /**
     * Delete the personnel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Personnel : {}", id);
        personnelRepository.deleteById(id);
    }
}
