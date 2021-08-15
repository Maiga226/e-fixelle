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

import bf.e_fixell_backoffice.domain.TypeDepense;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.TypeDepenseRepository;
import bf.e_fixell_backoffice.service.dto.TypeDepenseCriteria;
import bf.e_fixell_backoffice.service.dto.TypeDepenseDTO;
import bf.e_fixell_backoffice.service.mapper.TypeDepenseMapper;

/**
 * Service for executing complex queries for {@link TypeDepense} entities in the database.
 * The main input is a {@link TypeDepenseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TypeDepenseDTO} or a {@link Page} of {@link TypeDepenseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TypeDepenseQueryService extends QueryService<TypeDepense> {

    private final Logger log = LoggerFactory.getLogger(TypeDepenseQueryService.class);

    private final TypeDepenseRepository typeDepenseRepository;

    private final TypeDepenseMapper typeDepenseMapper;

    public TypeDepenseQueryService(TypeDepenseRepository typeDepenseRepository, TypeDepenseMapper typeDepenseMapper) {
        this.typeDepenseRepository = typeDepenseRepository;
        this.typeDepenseMapper = typeDepenseMapper;
    }

    /**
     * Return a {@link List} of {@link TypeDepenseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TypeDepenseDTO> findByCriteria(TypeDepenseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TypeDepense> specification = createSpecification(criteria);
        return typeDepenseMapper.toDto(typeDepenseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TypeDepenseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TypeDepenseDTO> findByCriteria(TypeDepenseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TypeDepense> specification = createSpecification(criteria);
        return typeDepenseRepository.findAll(specification, page)
            .map(typeDepenseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TypeDepenseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TypeDepense> specification = createSpecification(criteria);
        return typeDepenseRepository.count(specification);
    }

    /**
     * Function to convert {@link TypeDepenseCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TypeDepense> createSpecification(TypeDepenseCriteria criteria) {
        Specification<TypeDepense> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TypeDepense_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), TypeDepense_.code));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), TypeDepense_.libelle));
            }
            if (criteria.getDepenseId() != null) {
                specification = specification.and(buildSpecification(criteria.getDepenseId(),
                    root -> root.join(TypeDepense_.depenses, JoinType.LEFT).get(Depense_.id)));
            }
        }
        return specification;
    }
}
