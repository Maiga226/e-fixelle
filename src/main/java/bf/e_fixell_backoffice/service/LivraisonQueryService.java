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

import bf.e_fixell_backoffice.domain.Livraison;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.LivraisonRepository;
import bf.e_fixell_backoffice.service.dto.LivraisonCriteria;
import bf.e_fixell_backoffice.service.dto.LivraisonDTO;
import bf.e_fixell_backoffice.service.mapper.LivraisonMapper;

/**
 * Service for executing complex queries for {@link Livraison} entities in the database.
 * The main input is a {@link LivraisonCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LivraisonDTO} or a {@link Page} of {@link LivraisonDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LivraisonQueryService extends QueryService<Livraison> {

    private final Logger log = LoggerFactory.getLogger(LivraisonQueryService.class);

    private final LivraisonRepository livraisonRepository;

    private final LivraisonMapper livraisonMapper;

    public LivraisonQueryService(LivraisonRepository livraisonRepository, LivraisonMapper livraisonMapper) {
        this.livraisonRepository = livraisonRepository;
        this.livraisonMapper = livraisonMapper;
    }

    /**
     * Return a {@link List} of {@link LivraisonDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LivraisonDTO> findByCriteria(LivraisonCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Livraison> specification = createSpecification(criteria);
        return livraisonMapper.toDto(livraisonRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link LivraisonDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LivraisonDTO> findByCriteria(LivraisonCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Livraison> specification = createSpecification(criteria);
        return livraisonRepository.findAll(specification, page)
            .map(livraisonMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LivraisonCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Livraison> specification = createSpecification(criteria);
        return livraisonRepository.count(specification);
    }

    /**
     * Function to convert {@link LivraisonCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Livraison> createSpecification(LivraisonCriteria criteria) {
        Specification<Livraison> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Livraison_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Livraison_.code));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Livraison_.libelle));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), Livraison_.date));
            }
            if (criteria.getSomme() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSomme(), Livraison_.somme));
            }
            if (criteria.getEtat() != null) {
                specification = specification.and(buildSpecification(criteria.getEtat(), Livraison_.etat));
            }
            if (criteria.getMotif() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMotif(), Livraison_.motif));
            }
            if (criteria.getTransactionId() != null) {
                specification = specification.and(buildSpecification(criteria.getTransactionId(),
                    root -> root.join(Livraison_.transactions, JoinType.LEFT).get(Transaction_.id)));
            }
            if (criteria.getFraisId() != null) {
                specification = specification.and(buildSpecification(criteria.getFraisId(),
                    root -> root.join(Livraison_.frais, JoinType.LEFT).get(Frais_.id)));
            }
            if (criteria.getCommandeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCommandeId(),
                    root -> root.join(Livraison_.commande, JoinType.LEFT).get(Commande_.id)));
            }
            if (criteria.getFournisseurId() != null) {
                specification = specification.and(buildSpecification(criteria.getFournisseurId(),
                    root -> root.join(Livraison_.fournisseur, JoinType.LEFT).get(Fournisseur_.id)));
            }
            if (criteria.getTransportId() != null) {
                specification = specification.and(buildSpecification(criteria.getTransportId(),
                    root -> root.join(Livraison_.transport, JoinType.LEFT).get(Transport_.id)));
            }
        }
        return specification;
    }
}
