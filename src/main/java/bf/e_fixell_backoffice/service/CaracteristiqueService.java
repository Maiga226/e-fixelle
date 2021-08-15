package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.Caracteristique;
import bf.e_fixell_backoffice.repository.CaracteristiqueRepository;
import bf.e_fixell_backoffice.service.dto.CaracteristiqueDTO;
import bf.e_fixell_backoffice.service.mapper.CaracteristiqueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Caracteristique}.
 */
@Service
@Transactional
public class CaracteristiqueService {

    private final Logger log = LoggerFactory.getLogger(CaracteristiqueService.class);

    private final CaracteristiqueRepository caracteristiqueRepository;

    private final CaracteristiqueMapper caracteristiqueMapper;

    public CaracteristiqueService(CaracteristiqueRepository caracteristiqueRepository, CaracteristiqueMapper caracteristiqueMapper) {
        this.caracteristiqueRepository = caracteristiqueRepository;
        this.caracteristiqueMapper = caracteristiqueMapper;
    }

    /**
     * Save a caracteristique.
     *
     * @param caracteristiqueDTO the entity to save.
     * @return the persisted entity.
     */
    public CaracteristiqueDTO save(CaracteristiqueDTO caracteristiqueDTO) {
        log.debug("Request to save Caracteristique : {}", caracteristiqueDTO);
        Caracteristique caracteristique = caracteristiqueMapper.toEntity(caracteristiqueDTO);
        caracteristique = caracteristiqueRepository.save(caracteristique);
        return caracteristiqueMapper.toDto(caracteristique);
    }

    /**
     * Get all the caracteristiques.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CaracteristiqueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Caracteristiques");
        return caracteristiqueRepository.findAll(pageable)
            .map(caracteristiqueMapper::toDto);
    }


    /**
     * Get one caracteristique by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CaracteristiqueDTO> findOne(Long id) {
        log.debug("Request to get Caracteristique : {}", id);
        return caracteristiqueRepository.findById(id)
            .map(caracteristiqueMapper::toDto);
    }

    /**
     * Delete the caracteristique by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Caracteristique : {}", id);
        caracteristiqueRepository.deleteById(id);
    }
}
