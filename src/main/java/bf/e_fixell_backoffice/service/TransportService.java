package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.Transport;
import bf.e_fixell_backoffice.repository.TransportRepository;
import bf.e_fixell_backoffice.service.dto.TransportDTO;
import bf.e_fixell_backoffice.service.mapper.TransportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Transport}.
 */
@Service
@Transactional
public class TransportService {

    private final Logger log = LoggerFactory.getLogger(TransportService.class);

    private final TransportRepository transportRepository;

    private final TransportMapper transportMapper;

    public TransportService(TransportRepository transportRepository, TransportMapper transportMapper) {
        this.transportRepository = transportRepository;
        this.transportMapper = transportMapper;
    }

    /**
     * Save a transport.
     *
     * @param transportDTO the entity to save.
     * @return the persisted entity.
     */
    public TransportDTO save(TransportDTO transportDTO) {
        log.debug("Request to save Transport : {}", transportDTO);
        Transport transport = transportMapper.toEntity(transportDTO);
        transport = transportRepository.save(transport);
        return transportMapper.toDto(transport);
    }

    /**
     * Get all the transports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TransportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Transports");
        return transportRepository.findAll(pageable)
            .map(transportMapper::toDto);
    }


    /**
     * Get one transport by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TransportDTO> findOne(Long id) {
        log.debug("Request to get Transport : {}", id);
        return transportRepository.findById(id)
            .map(transportMapper::toDto);
    }

    /**
     * Delete the transport by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Transport : {}", id);
        transportRepository.deleteById(id);
    }
}
