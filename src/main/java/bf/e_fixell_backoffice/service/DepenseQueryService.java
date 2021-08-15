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

import bf.e_fixell_backoffice.domain.Depense;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.DepenseRepository;
import bf.e_fixell_backoffice.service.dto.DepenseCriteria;
import bf.e_fixell_backoffice.service.dto.DepenseDTO;
import bf.e_fixell_backoffice.service.mapper.DepenseMapper;

/**
 * Service for executing complex queries for {@link Depense} entities in the database.
 * The main input is a {@link DepenseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DepenseDTO} or a {@link Page} of {@link DepenseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DepenseQueryService extends QueryService<Depense> {

    private final Logger log = LoggerFactory.getLogger(DepenseQueryService.class);

    private final DepenseRepository depenseRepository;

    private final DepenseMapper depenseMapper;

    public DepenseQueryService(DepenseRepository depenseRepository, DepenseMapper depenseMapper) {
        this.depenseRepository = depenseRepository;
        this.depenseMapper = depenseMapper;
    }

    /**
     * Return a {@link List} of {@link DepenseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DepenseDTO> findByCriteria(DepenseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Depense> specification = createSpecification(criteria);
        return depenseMapper.toDto(depenseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DepenseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DepenseDTO> findByCriteria(DepenseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Depense> specification = createSpecification(criteria);
        return depenseRepository.findAll(specification, page)
            .map(depenseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DepenseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Depense> specification = createSpecification(criteria);
        return depenseRepository.count(specification);
    }

    /**
     * Function to convert {@link DepenseCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Depense> createSpecification(DepenseCriteria criteria) {
        Specification<Depense> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Depense_.id));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Depense_.libelle));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Depense_.description));
            }
            if (criteria.getMontant() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontant(), Depense_.montant));
            }
            if (criteria.getTypeDepenseId() != null) {
                specification = specification.and(buildSpecification(criteria.getTypeDepenseId(),
                    root -> root.join(Depense_.typeDepense, JoinType.LEFT).get(TypeDepense_.id)));
            }
            if (criteria.getSessionCaisseId() != null) {
                specification = specification.and(buildSpecification(criteria.getSessionCaisseId(),
                    root -> root.join(Depense_.sessionCaisse, JoinType.LEFT).get(SessionCaisse_.id)));
            }
        }
        return specification;
    }
}
