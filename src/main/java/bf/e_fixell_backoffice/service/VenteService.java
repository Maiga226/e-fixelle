package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.Vente;
import bf.e_fixell_backoffice.repository.VenteRepository;
import bf.e_fixell_backoffice.service.dto.VenteDTO;
import bf.e_fixell_backoffice.service.mapper.VenteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Vente}.
 */
@Service
@Transactional
public class VenteService {

    private final Logger log = LoggerFactory.getLogger(VenteService.class);

    private final VenteRepository venteRepository;

    private final VenteMapper venteMapper;

    public VenteService(VenteRepository venteRepository, VenteMapper venteMapper) {
        this.venteRepository = venteRepository;
        this.venteMapper = venteMapper;
    }

    /**
     * Save a vente.
     *
     * @param venteDTO the entity to save.
     * @return the persisted entity.
     */
    public VenteDTO save(VenteDTO venteDTO) {
        log.debug("Request to save Vente : {}", venteDTO);
        Vente vente = venteMapper.toEntity(venteDTO);
        vente = venteRepository.save(vente);
        return venteMapper.toDto(vente);
    }

    /**
     * Get all the ventes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<VenteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ventes");
        return venteRepository.findAll(pageable)
            .map(venteMapper::toDto);
    }


    /**
     * Get one vente by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<VenteDTO> findOne(Long id) {
        log.debug("Request to get Vente : {}", id);
        return venteRepository.findById(id)
            .map(venteMapper::toDto);
    }

    /**
     * Delete the vente by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Vente : {}", id);
        venteRepository.deleteById(id);
    }
}
