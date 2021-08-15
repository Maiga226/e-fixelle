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

import bf.e_fixell_backoffice.domain.Fonction;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.FonctionRepository;
import bf.e_fixell_backoffice.service.dto.FonctionCriteria;
import bf.e_fixell_backoffice.service.dto.FonctionDTO;
import bf.e_fixell_backoffice.service.mapper.FonctionMapper;

/**
 * Service for executing complex queries for {@link Fonction} entities in the database.
 * The main input is a {@link FonctionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FonctionDTO} or a {@link Page} of {@link FonctionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FonctionQueryService extends QueryService<Fonction> {

    private final Logger log = LoggerFactory.getLogger(FonctionQueryService.class);

    private final FonctionRepository fonctionRepository;

    private final FonctionMapper fonctionMapper;

    public FonctionQueryService(FonctionRepository fonctionRepository, FonctionMapper fonctionMapper) {
        this.fonctionRepository = fonctionRepository;
        this.fonctionMapper = fonctionMapper;
    }

    /**
     * Return a {@link List} of {@link FonctionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FonctionDTO> findByCriteria(FonctionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Fonction> specification = createSpecification(criteria);
        return fonctionMapper.toDto(fonctionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FonctionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FonctionDTO> findByCriteria(FonctionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Fonction> specification = createSpecification(criteria);
        return fonctionRepository.findAll(specification, page)
            .map(fonctionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FonctionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Fonction> specification = createSpecification(criteria);
        return fonctionRepository.count(specification);
    }

    /**
     * Function to convert {@link FonctionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Fonction> createSpecification(FonctionCriteria criteria) {
        Specification<Fonction> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Fonction_.id));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Fonction_.libelle));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Fonction_.description));
            }
            if (criteria.getSalaireMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSalaireMin(), Fonction_.salaireMin));
            }
            if (criteria.getSalaireMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSalaireMax(), Fonction_.salaireMax));
            }
            if (criteria.getHistoriqueAffectationId() != null) {
                specification = specification.and(buildSpecification(criteria.getHistoriqueAffectationId(),
                    root -> root.join(Fonction_.historiqueAffectations, JoinType.LEFT).get(HistoriqueAffectation_.id)));
            }
        }
        return specification;
    }
}
