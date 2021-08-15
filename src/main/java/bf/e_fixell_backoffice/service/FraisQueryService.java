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

import bf.e_fixell_backoffice.domain.Frais;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.FraisRepository;
import bf.e_fixell_backoffice.service.dto.FraisCriteria;
import bf.e_fixell_backoffice.service.dto.FraisDTO;
import bf.e_fixell_backoffice.service.mapper.FraisMapper;

/**
 * Service for executing complex queries for {@link Frais} entities in the database.
 * The main input is a {@link FraisCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FraisDTO} or a {@link Page} of {@link FraisDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FraisQueryService extends QueryService<Frais> {

    private final Logger log = LoggerFactory.getLogger(FraisQueryService.class);

    private final FraisRepository fraisRepository;

    private final FraisMapper fraisMapper;

    public FraisQueryService(FraisRepository fraisRepository, FraisMapper fraisMapper) {
        this.fraisRepository = fraisRepository;
        this.fraisMapper = fraisMapper;
    }

    /**
     * Return a {@link List} of {@link FraisDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FraisDTO> findByCriteria(FraisCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Frais> specification = createSpecification(criteria);
        return fraisMapper.toDto(fraisRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FraisDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FraisDTO> findByCriteria(FraisCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Frais> specification = createSpecification(criteria);
        return fraisRepository.findAll(specification, page)
            .map(fraisMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FraisCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Frais> specification = createSpecification(criteria);
        return fraisRepository.count(specification);
    }

    /**
     * Function to convert {@link FraisCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Frais> createSpecification(FraisCriteria criteria) {
        Specification<Frais> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Frais_.id));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Frais_.libelle));
            }
            if (criteria.getValeur() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValeur(), Frais_.valeur));
            }
            if (criteria.getCommandeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCommandeId(),
                    root -> root.join(Frais_.commande, JoinType.LEFT).get(Commande_.id)));
            }
            if (criteria.getTypeFraisId() != null) {
                specification = specification.and(buildSpecification(criteria.getTypeFraisId(),
                    root -> root.join(Frais_.typeFrais, JoinType.LEFT).get(TypeFrais_.id)));
            }
            if (criteria.getLivraisonId() != null) {
                specification = specification.and(buildSpecification(criteria.getLivraisonId(),
                    root -> root.join(Frais_.livraison, JoinType.LEFT).get(Livraison_.id)));
            }
        }
        return specification;
    }
}
