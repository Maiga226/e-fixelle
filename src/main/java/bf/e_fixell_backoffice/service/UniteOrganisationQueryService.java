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

import bf.e_fixell_backoffice.domain.UniteOrganisation;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.UniteOrganisationRepository;
import bf.e_fixell_backoffice.service.dto.UniteOrganisationCriteria;
import bf.e_fixell_backoffice.service.dto.UniteOrganisationDTO;
import bf.e_fixell_backoffice.service.mapper.UniteOrganisationMapper;

/**
 * Service for executing complex queries for {@link UniteOrganisation} entities in the database.
 * The main input is a {@link UniteOrganisationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UniteOrganisationDTO} or a {@link Page} of {@link UniteOrganisationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UniteOrganisationQueryService extends QueryService<UniteOrganisation> {

    private final Logger log = LoggerFactory.getLogger(UniteOrganisationQueryService.class);

    private final UniteOrganisationRepository uniteOrganisationRepository;

    private final UniteOrganisationMapper uniteOrganisationMapper;

    public UniteOrganisationQueryService(UniteOrganisationRepository uniteOrganisationRepository, UniteOrganisationMapper uniteOrganisationMapper) {
        this.uniteOrganisationRepository = uniteOrganisationRepository;
        this.uniteOrganisationMapper = uniteOrganisationMapper;
    }

    /**
     * Return a {@link List} of {@link UniteOrganisationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UniteOrganisationDTO> findByCriteria(UniteOrganisationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<UniteOrganisation> specification = createSpecification(criteria);
        return uniteOrganisationMapper.toDto(uniteOrganisationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UniteOrganisationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UniteOrganisationDTO> findByCriteria(UniteOrganisationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<UniteOrganisation> specification = createSpecification(criteria);
        return uniteOrganisationRepository.findAll(specification, page)
            .map(uniteOrganisationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(UniteOrganisationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<UniteOrganisation> specification = createSpecification(criteria);
        return uniteOrganisationRepository.count(specification);
    }

    /**
     * Function to convert {@link UniteOrganisationCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<UniteOrganisation> createSpecification(UniteOrganisationCriteria criteria) {
        Specification<UniteOrganisation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), UniteOrganisation_.id));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), UniteOrganisation_.libelle));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildSpecification(criteria.getType(), UniteOrganisation_.type));
            }
            if (criteria.getFournisseurId() != null) {
                specification = specification.and(buildSpecification(criteria.getFournisseurId(),
                    root -> root.join(UniteOrganisation_.fournisseurs, JoinType.LEFT).get(Fournisseur_.id)));
            }
        }
        return specification;
    }
}
