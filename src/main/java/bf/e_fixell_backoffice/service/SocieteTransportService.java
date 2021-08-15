package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.SocieteTransport;
import bf.e_fixell_backoffice.repository.SocieteTransportRepository;
import bf.e_fixell_backoffice.service.dto.SocieteTransportDTO;
import bf.e_fixell_backoffice.service.mapper.SocieteTransportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SocieteTransport}.
 */
@Service
@Transactional
public class SocieteTransportService {

    private final Logger log = LoggerFactory.getLogger(SocieteTransportService.class);

    private final SocieteTransportRepository societeTransportRepository;

    private final SocieteTransportMapper societeTransportMapper;

    public SocieteTransportService(SocieteTransportRepository societeTransportRepository, SocieteTransportMapper societeTransportMapper) {
        this.societeTransportRepository = societeTransportRepository;
        this.societeTransportMapper = societeTransportMapper;
    }

    /**
     * Save a societeTransport.
     *
     * @param societeTransportDTO the entity to save.
     * @return the persisted entity.
     */
    public SocieteTransportDTO save(SocieteTransportDTO societeTransportDTO) {
        log.debug("Request to save SocieteTransport : {}", societeTransportDTO);
        SocieteTransport societeTransport = societeTransportMapper.toEntity(societeTransportDTO);
        societeTransport = societeTransportRepository.save(societeTransport);
        return societeTransportMapper.toDto(societeTransport);
    }

    /**
     * Get all the societeTransports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SocieteTransportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SocieteTransports");
        return societeTransportRepository.findAll(pageable)
            .map(societeTransportMapper::toDto);
    }


    /**
     * Get one societeTransport by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SocieteTransportDTO> findOne(Long id) {
        log.debug("Request to get SocieteTransport : {}", id);
        return societeTransportRepository.findById(id)
            .map(societeTransportMapper::toDto);
    }

    /**
     * Delete the societeTransport by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SocieteTransport : {}", id);
        societeTransportRepository.deleteById(id);
    }
}
