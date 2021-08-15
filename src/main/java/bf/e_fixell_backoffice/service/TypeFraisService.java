package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.TypeFrais;
import bf.e_fixell_backoffice.repository.TypeFraisRepository;
import bf.e_fixell_backoffice.service.dto.TypeFraisDTO;
import bf.e_fixell_backoffice.service.mapper.TypeFraisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeFrais}.
 */
@Service
@Transactional
public class TypeFraisService {

    private final Logger log = LoggerFactory.getLogger(TypeFraisService.class);

    private final TypeFraisRepository typeFraisRepository;

    private final TypeFraisMapper typeFraisMapper;

    public TypeFraisService(TypeFraisRepository typeFraisRepository, TypeFraisMapper typeFraisMapper) {
        this.typeFraisRepository = typeFraisRepository;
        this.typeFraisMapper = typeFraisMapper;
    }

    /**
     * Save a typeFrais.
     *
     * @param typeFraisDTO the entity to save.
     * @return the persisted entity.
     */
    public TypeFraisDTO save(TypeFraisDTO typeFraisDTO) {
        log.debug("Request to save TypeFrais : {}", typeFraisDTO);
        TypeFrais typeFrais = typeFraisMapper.toEntity(typeFraisDTO);
        typeFrais = typeFraisRepository.save(typeFrais);
        return typeFraisMapper.toDto(typeFrais);
    }

    /**
     * Get all the typeFrais.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TypeFraisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeFrais");
        return typeFraisRepository.findAll(pageable)
            .map(typeFraisMapper::toDto);
    }


    /**
     * Get one typeFrais by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TypeFraisDTO> findOne(Long id) {
        log.debug("Request to get TypeFrais : {}", id);
        return typeFraisRepository.findById(id)
            .map(typeFraisMapper::toDto);
    }

    /**
     * Delete the typeFrais by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TypeFrais : {}", id);
        typeFraisRepository.deleteById(id);
    }
}
