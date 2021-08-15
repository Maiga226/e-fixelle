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

import bf.e_fixell_backoffice.domain.Produit;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.ProduitRepository;
import bf.e_fixell_backoffice.service.dto.ProduitCriteria;
import bf.e_fixell_backoffice.service.dto.ProduitDTO;
import bf.e_fixell_backoffice.service.mapper.ProduitMapper;

/**
 * Service for executing complex queries for {@link Produit} entities in the database.
 * The main input is a {@link ProduitCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProduitDTO} or a {@link Page} of {@link ProduitDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProduitQueryService extends QueryService<Produit> {

    private final Logger log = LoggerFactory.getLogger(ProduitQueryService.class);

    private final ProduitRepository produitRepository;

    private final ProduitMapper produitMapper;

    public ProduitQueryService(ProduitRepository produitRepository, ProduitMapper produitMapper) {
        this.produitRepository = produitRepository;
        this.produitMapper = produitMapper;
    }

    /**
     * Return a {@link List} of {@link ProduitDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProduitDTO> findByCriteria(ProduitCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Produit> specification = createSpecification(criteria);
        return produitMapper.toDto(produitRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProduitDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProduitDTO> findByCriteria(ProduitCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Produit> specification = createSpecification(criteria);
        return produitRepository.findAll(specification, page)
            .map(produitMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProduitCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Produit> specification = createSpecification(criteria);
        return produitRepository.count(specification);
    }

    /**
     * Function to convert {@link ProduitCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Produit> createSpecification(ProduitCriteria criteria) {
        Specification<Produit> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Produit_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Produit_.code));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Produit_.libelle));
            }
            if (criteria.getQuantite() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantite(), Produit_.quantite));
            }
            if (criteria.getHsCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHsCode(), Produit_.hsCode));
            }
            if (criteria.getPrixProduitId() != null) {
                specification = specification.and(buildSpecification(criteria.getPrixProduitId(),
                    root -> root.join(Produit_.prixProduits, JoinType.LEFT).get(PrixProduit_.id)));
            }
            if (criteria.getPerteId() != null) {
                specification = specification.and(buildSpecification(criteria.getPerteId(),
                    root -> root.join(Produit_.pertes, JoinType.LEFT).get(Perte_.id)));
            }
            if (criteria.getTransactionId() != null) {
                specification = specification.and(buildSpecification(criteria.getTransactionId(),
                    root -> root.join(Produit_.transactions, JoinType.LEFT).get(Transaction_.id)));
            }
            if (criteria.getCategorieId() != null) {
                specification = specification.and(buildSpecification(criteria.getCategorieId(),
                    root -> root.join(Produit_.categorie, JoinType.LEFT).get(Categorie_.id)));
            }
            if (criteria.getClassificationId() != null) {
                specification = specification.and(buildSpecification(criteria.getClassificationId(),
                    root -> root.join(Produit_.classification, JoinType.LEFT).get(Classification_.id)));
            }
        }
        return specification;
    }
}
