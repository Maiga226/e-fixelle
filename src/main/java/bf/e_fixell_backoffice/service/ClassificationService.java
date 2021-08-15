package bf.e_fixell_backoffice.service;

import bf.e_fixell_backoffice.domain.Classification;
import bf.e_fixell_backoffice.repository.ClassificationRepository;
import bf.e_fixell_backoffice.service.dto.ClassificationDTO;
import bf.e_fixell_backoffice.service.mapper.ClassificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Classification}.
 */
@Service
@Transactional
public class ClassificationService {

    private final Logger log = LoggerFactory.getLogger(ClassificationService.class);

    private final ClassificationRepository classificationRepository;

    private final ClassificationMapper classificationMapper;

    public ClassificationService(ClassificationRepository classificationRepository, ClassificationMapper classificationMapper) {
        this.classificationRepository = classificationRepository;
        this.classificationMapper = classificationMapper;
    }

    /**
     * Save a classification.
     *
     * @param classificationDTO the entity to save.
     * @return the persisted entity.
     */
    public ClassificationDTO save(ClassificationDTO classificationDTO) {
        log.debug("Request to save Classification : {}", classificationDTO);
        Classification classification = classificationMapper.toEntity(classificationDTO);
        classification = classificationRepository.save(classification);
        return classificationMapper.toDto(classification);
    }

    /**
     * Get all the classifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ClassificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Classifications");
        return classificationRepository.findAll(pageable)
            .map(classificationMapper::toDto);
    }


    /**
     * Get one classification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ClassificationDTO> findOne(Long id) {
        log.debug("Request to get Classification : {}", id);
        return classificationRepository.findById(id)
            .map(classificationMapper::toDto);
    }

    /**
     * Delete the classification by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Classification : {}", id);
        classificationRepository.deleteById(id);
    }
}
