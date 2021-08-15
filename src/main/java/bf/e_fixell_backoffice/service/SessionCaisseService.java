package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.SessionCaisse;
import bf.e_fixell_backoffice.repository.SessionCaisseRepository;
import bf.e_fixell_backoffice.service.dto.SessionCaisseDTO;
import bf.e_fixell_backoffice.service.mapper.SessionCaisseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SessionCaisse}.
 */
@Service
@Transactional
public class SessionCaisseService {

    private final Logger log = LoggerFactory.getLogger(SessionCaisseService.class);

    private final SessionCaisseRepository sessionCaisseRepository;

    private final SessionCaisseMapper sessionCaisseMapper;

    public SessionCaisseService(SessionCaisseRepository sessionCaisseRepository, SessionCaisseMapper sessionCaisseMapper) {
        this.sessionCaisseRepository = sessionCaisseRepository;
        this.sessionCaisseMapper = sessionCaisseMapper;
    }

    /**
     * Save a sessionCaisse.
     *
     * @param sessionCaisseDTO the entity to save.
     * @return the persisted entity.
     */
    public SessionCaisseDTO save(SessionCaisseDTO sessionCaisseDTO) {
        log.debug("Request to save SessionCaisse : {}", sessionCaisseDTO);
        SessionCaisse sessionCaisse = sessionCaisseMapper.toEntity(sessionCaisseDTO);
        sessionCaisse = sessionCaisseRepository.save(sessionCaisse);
        return sessionCaisseMapper.toDto(sessionCaisse);
    }

    /**
     * Get all the sessionCaisses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SessionCaisseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SessionCaisses");
        return sessionCaisseRepository.findAll(pageable)
            .map(sessionCaisseMapper::toDto);
    }


    /**
     * Get one sessionCaisse by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SessionCaisseDTO> findOne(Long id) {
        log.debug("Request to get SessionCaisse : {}", id);
        return sessionCaisseRepository.findById(id)
            .map(sessionCaisseMapper::toDto);
    }

    /**
     * Delete the sessionCaisse by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SessionCaisse : {}", id);
        sessionCaisseRepository.deleteById(id);
    }
}
