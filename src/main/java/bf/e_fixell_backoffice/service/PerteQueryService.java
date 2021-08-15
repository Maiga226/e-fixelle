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

import bf.e_fixell_backoffice.domain.Perte;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.PerteRepository;
import bf.e_fixell_backoffice.service.dto.PerteCriteria;
import bf.e_fixell_backoffice.service.dto.PerteDTO;
import bf.e_fixell_backoffice.service.mapper.PerteMapper;

/**
 * Service for executing complex queries for {@link Perte} entities in the database.
 * The main input is a {@link PerteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PerteDTO} or a {@link Page} of {@link PerteDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PerteQueryService extends QueryService<Perte> {

    private final Logger log = LoggerFactory.getLogger(PerteQueryService.class);

    private final PerteRepository perteRepository;

    private final PerteMapper perteMapper;

    public PerteQueryService(PerteRepository perteRepository, PerteMapper perteMapper) {
        this.perteRepository = perteRepository;
        this.perteMapper = perteMapper;
    }

    /**
     * Return a {@link List} of {@link PerteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PerteDTO> findByCriteria(PerteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Perte> specification = createSpecification(criteria);
        return perteMapper.toDto(perteRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PerteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PerteDTO> findByCriteria(PerteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Perte> specification = createSpecification(criteria);
        return perteRepository.findAll(specification, page)
            .map(perteMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PerteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Perte> specification = createSpecification(criteria);
        return perteRepository.count(specification);
    }

    /**
     * Function to convert {@link PerteCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Perte> createSpecification(PerteCriteria criteria) {
        Specification<Perte> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Perte_.id));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Perte_.libelle));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), Perte_.date));
            }
            if (criteria.getQuantite() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantite(), Perte_.quantite));
            }
            if (criteria.getMontant() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontant(), Perte_.montant));
            }
            if (criteria.getProduitId() != null) {
                specification = specification.and(buildSpecification(criteria.getProduitId(),
                    root -> root.join(Perte_.produit, JoinType.LEFT).get(Produit_.id)));
            }
        }
        return specification;
    }
}
