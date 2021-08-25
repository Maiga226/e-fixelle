package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.FicheTechnique;
import bf.e_fixell_backoffice.repository.FicheTechniqueRepository;
import bf.e_fixell_backoffice.service.dto.FicheTechniqueDTO;
import bf.e_fixell_backoffice.service.mapper.FicheTechniqueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FicheTechnique}.
 */
@Service
@Transactional
public class FicheTechniqueService {

    private final Logger log = LoggerFactory.getLogger(FicheTechniqueService.class);

    private final FicheTechniqueRepository ficheTechniqueRepository;

    private final FicheTechniqueMapper ficheTechniqueMapper;

    public FicheTechniqueService(FicheTechniqueRepository ficheTechniqueRepository, FicheTechniqueMapper ficheTechniqueMapper) {
        this.ficheTechniqueRepository = ficheTechniqueRepository;
        this.ficheTechniqueMapper = ficheTechniqueMapper;
    }

    /**
     * Save a ficheTechnique.
     *
     * @param ficheTechniqueDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheTechnique save(FicheTechniqueDTO ficheTechniqueDTO) {
        log.debug("Request to save FicheTechnique : {}", ficheTechniqueDTO);
        FicheTechnique ficheTechnique = ficheTechniqueMapper.toEntity(ficheTechniqueDTO);
        return ficheTechniqueRepository.save(ficheTechnique);
       /* ficheTechnique = ficheTechniqueRepository.save(ficheTechnique);
        return ficheTechniqueMapper.toDto(ficheTechnique);*/
    }

    /**
     * Get all the ficheTechniques.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FicheTechniqueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FicheTechniques");
        return ficheTechniqueRepository.findAll(pageable)
            .map(ficheTechniqueMapper::toDto);
    }


    /**
     * Get one ficheTechnique by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FicheTechniqueDTO> findOne(Long id) {
        log.debug("Request to get FicheTechnique : {}", id);
        return ficheTechniqueRepository.findById(id)
            .map(ficheTechniqueMapper::toDto);
    }

    /**
     * Delete the ficheTechnique by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FicheTechnique : {}", id);
        ficheTechniqueRepository.deleteById(id);
    }
}
