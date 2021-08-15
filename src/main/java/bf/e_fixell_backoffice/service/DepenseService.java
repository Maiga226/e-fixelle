package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.Depense;
import bf.e_fixell_backoffice.repository.DepenseRepository;
import bf.e_fixell_backoffice.service.dto.DepenseDTO;
import bf.e_fixell_backoffice.service.mapper.DepenseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Depense}.
 */
@Service
@Transactional
public class DepenseService {

    private final Logger log = LoggerFactory.getLogger(DepenseService.class);

    private final DepenseRepository depenseRepository;

    private final DepenseMapper depenseMapper;

    public DepenseService(DepenseRepository depenseRepository, DepenseMapper depenseMapper) {
        this.depenseRepository = depenseRepository;
        this.depenseMapper = depenseMapper;
    }

    /**
     * Save a depense.
     *
     * @param depenseDTO the entity to save.
     * @return the persisted entity.
     */
    public DepenseDTO save(DepenseDTO depenseDTO) {
        log.debug("Request to save Depense : {}", depenseDTO);
        Depense depense = depenseMapper.toEntity(depenseDTO);
        depense = depenseRepository.save(depense);
        return depenseMapper.toDto(depense);
    }

    /**
     * Get all the depenses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DepenseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Depenses");
        return depenseRepository.findAll(pageable)
            .map(depenseMapper::toDto);
    }


    /**
     * Get one depense by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DepenseDTO> findOne(Long id) {
        log.debug("Request to get Depense : {}", id);
        return depenseRepository.findById(id)
            .map(depenseMapper::toDto);
    }

    /**
     * Delete the depense by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Depense : {}", id);
        depenseRepository.deleteById(id);
    }
}
