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

import bf.e_fixell_backoffice.domain.TypeFrais;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.TypeFraisRepository;
import bf.e_fixell_backoffice.service.dto.TypeFraisCriteria;
import bf.e_fixell_backoffice.service.dto.TypeFraisDTO;
import bf.e_fixell_backoffice.service.mapper.TypeFraisMapper;

/**
 * Service for executing complex queries for {@link TypeFrais} entities in the database.
 * The main input is a {@link TypeFraisCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TypeFraisDTO} or a {@link Page} of {@link TypeFraisDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TypeFraisQueryService extends QueryService<TypeFrais> {

    private final Logger log = LoggerFactory.getLogger(TypeFraisQueryService.class);

    private final TypeFraisRepository typeFraisRepository;

    private final TypeFraisMapper typeFraisMapper;

    public TypeFraisQueryService(TypeFraisRepository typeFraisRepository, TypeFraisMapper typeFraisMapper) {
        this.typeFraisRepository = typeFraisRepository;
        this.typeFraisMapper = typeFraisMapper;
    }

    /**
     * Return a {@link List} of {@link TypeFraisDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TypeFraisDTO> findByCriteria(TypeFraisCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TypeFrais> specification = createSpecification(criteria);
        return typeFraisMapper.toDto(typeFraisRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TypeFraisDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TypeFraisDTO> findByCriteria(TypeFraisCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TypeFrais> specification = createSpecification(criteria);
        return typeFraisRepository.findAll(specification, page)
            .map(typeFraisMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TypeFraisCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TypeFrais> specification = createSpecification(criteria);
        return typeFraisRepository.count(specification);
    }

    /**
     * Function to convert {@link TypeFraisCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TypeFrais> createSpecification(TypeFraisCriteria criteria) {
        Specification<TypeFrais> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TypeFrais_.id));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), TypeFrais_.libelle));
            }
            if (criteria.getFraisId() != null) {
                specification = specification.and(buildSpecification(criteria.getFraisId(),
                    root -> root.join(TypeFrais_.frais, JoinType.LEFT).get(Frais_.id)));
            }
        }
        return specification;
    }
}
