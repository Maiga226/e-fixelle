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

import bf.e_fixell_backoffice.domain.Transaction;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.TransactionRepository;
import bf.e_fixell_backoffice.service.dto.TransactionCriteria;
import bf.e_fixell_backoffice.service.dto.TransactionDTO;
import bf.e_fixell_backoffice.service.mapper.TransactionMapper;

/**
 * Service for executing complex queries for {@link Transaction} entities in the database.
 * The main input is a {@link TransactionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TransactionDTO} or a {@link Page} of {@link TransactionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TransactionQueryService extends QueryService<Transaction> {

    private final Logger log = LoggerFactory.getLogger(TransactionQueryService.class);

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    public TransactionQueryService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    /**
     * Return a {@link List} of {@link TransactionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TransactionDTO> findByCriteria(TransactionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Transaction> specification = createSpecification(criteria);
        return transactionMapper.toDto(transactionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TransactionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TransactionDTO> findByCriteria(TransactionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Transaction> specification = createSpecification(criteria);
        return transactionRepository.findAll(specification, page)
            .map(transactionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TransactionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Transaction> specification = createSpecification(criteria);
        return transactionRepository.count(specification);
    }

    /**
     * Function to convert {@link TransactionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Transaction> createSpecification(TransactionCriteria criteria) {
        Specification<Transaction> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Transaction_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Transaction_.code));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), Transaction_.date));
            }
            if (criteria.getQuantite() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantite(), Transaction_.quantite));
            }
            if (criteria.getPrixUnitaire() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrixUnitaire(), Transaction_.prixUnitaire));
            }
            if (criteria.getTypeTransaction() != null) {
                specification = specification.and(buildSpecification(criteria.getTypeTransaction(), Transaction_.typeTransaction));
            }
            if (criteria.getEtat() != null) {
                specification = specification.and(buildSpecification(criteria.getEtat(), Transaction_.etat));
            }
            if (criteria.getMotif() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMotif(), Transaction_.motif));
            }
            if (criteria.getFicheTechniqueId() != null) {
                specification = specification.and(buildSpecification(criteria.getFicheTechniqueId(),
                    root -> root.join(Transaction_.ficheTechnique, JoinType.LEFT).get(FicheTechnique_.id)));
            }
            if (criteria.getProduitId() != null) {
                specification = specification.and(buildSpecification(criteria.getProduitId(),
                    root -> root.join(Transaction_.produit, JoinType.LEFT).get(Produit_.id)));
            }
            if (criteria.getCommandeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCommandeId(),
                    root -> root.join(Transaction_.commande, JoinType.LEFT).get(Commande_.id)));
            }
            if (criteria.getApprovisionnementId() != null) {
                specification = specification.and(buildSpecification(criteria.getApprovisionnementId(),
                    root -> root.join(Transaction_.approvisionnement, JoinType.LEFT).get(Approvisionnement_.id)));
            }
            if (criteria.getLivraisonId() != null) {
                specification = specification.and(buildSpecification(criteria.getLivraisonId(),
                    root -> root.join(Transaction_.livraison, JoinType.LEFT).get(Livraison_.id)));
            }
            if (criteria.getVenteId() != null) {
                specification = specification.and(buildSpecification(criteria.getVenteId(),
                    root -> root.join(Transaction_.vente, JoinType.LEFT).get(Vente_.id)));
            }
        }
        return specification;
    }
}
