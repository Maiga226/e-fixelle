package bf.e_fixell_backoffice.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import bf.e_fixell_backoffice.domain.FicheTechnique;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.FicheTechniqueRepository;
import bf.e_fixell_backoffice.service.dto.FicheTechniqueCriteria;
import bf.e_fixell_backoffice.service.dto.FicheTechniqueDTO;
import bf.e_fixell_backoffice.service.mapper.FicheTechniqueMapper;

/**
 * Service for executing complex queries for {@link FicheTechnique} entities in the database.
 * The main input is a {@link FicheTechniqueCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FicheTechniqueDTO} or a {@link Page} of {@link FicheTechniqueDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FicheTechniqueQueryService extends QueryService<FicheTechnique> {

    private final Logger log = LoggerFactory.getLogger(FicheTechniqueQueryService.class);

    private final FicheTechniqueRepository ficheTechniqueRepository;

    private final FicheTechniqueMapper ficheTechniqueMapper;

    public FicheTechniqueQueryService(FicheTechniqueRepository ficheTechniqueRepository, FicheTechniqueMapper ficheTechniqueMapper) {
        this.ficheTechniqueRepository = ficheTechniqueRepository;
        this.ficheTechniqueMapper = ficheTechniqueMapper;
    }

    /**
     * Return a {@link List} of {@link FicheTechniqueDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FicheTechniqueDTO> findByCriteria(FicheTechniqueCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FicheTechnique> specification = createSpecification(criteria);
        return ficheTechniqueMapper.toDto(ficheTechniqueRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FicheTechniqueDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FicheTechniqueDTO> findByCriteria(FicheTechniqueCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FicheTechnique> specification = createSpecification(criteria);
        return ficheTechniqueRepository.findAll(specification, page)
            .map(ficheTechniqueMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FicheTechniqueCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FicheTechnique> specification = createSpecification(criteria);
        return ficheTechniqueRepository.count(specification);
    }

    /**
     * Function to convert {@link FicheTechniqueCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<FicheTechnique> createSpecification(FicheTechniqueCriteria criteria) {
        Specification<FicheTechnique> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), FicheTechnique_.id));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), FicheTechnique_.libelle));
            }
            if (criteria.getCaracteristiqueId() != null) {
                specification = specification.and(buildSpecification(criteria.getCaracteristiqueId(),
                    root -> root.join(FicheTechnique_.caracteristiques, JoinType.LEFT).get(Caracteristique_.id)));
            }
        }
        return specification;
    }
}
