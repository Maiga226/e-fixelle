package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.OperationCaisse;
import bf.e_fixell_backoffice.repository.OperationCaisseRepository;
import bf.e_fixell_backoffice.service.dto.OperationCaisseDTO;
import bf.e_fixell_backoffice.service.mapper.OperationCaisseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OperationCaisse}.
 */
@Service
@Transactional
public class OperationCaisseService {

    private final Logger log = LoggerFactory.getLogger(OperationCaisseService.class);

    private final OperationCaisseRepository operationCaisseRepository;

    private final OperationCaisseMapper operationCaisseMapper;

    public OperationCaisseService(OperationCaisseRepository operationCaisseRepository, OperationCaisseMapper operationCaisseMapper) {
        this.operationCaisseRepository = operationCaisseRepository;
        this.operationCaisseMapper = operationCaisseMapper;
    }

    /**
     * Save a operationCaisse.
     *
     * @param operationCaisseDTO the entity to save.
     * @return the persisted entity.
     */
    public OperationCaisseDTO save(OperationCaisseDTO operationCaisseDTO) {
        log.debug("Request to save OperationCaisse : {}", operationCaisseDTO);
        OperationCaisse operationCaisse = operationCaisseMapper.toEntity(operationCaisseDTO);
        operationCaisse = operationCaisseRepository.save(operationCaisse);
        return operationCaisseMapper.toDto(operationCaisse);
    }

    /**
     * Get all the operationCaisses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OperationCaisseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OperationCaisses");
        return operationCaisseRepository.findAll(pageable)
            .map(operationCaisseMapper::toDto);
    }


    /**
     * Get one operationCaisse by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OperationCaisseDTO> findOne(Long id) {
        log.debug("Request to get OperationCaisse : {}", id);
        return operationCaisseRepository.findById(id)
            .map(operationCaisseMapper::toDto);
    }

    /**
     * Delete the operationCaisse by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OperationCaisse : {}", id);
        operationCaisseRepository.deleteById(id);
    }
}
