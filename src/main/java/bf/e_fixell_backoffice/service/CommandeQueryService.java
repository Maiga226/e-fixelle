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

import bf.e_fixell_backoffice.domain.Commande;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.CommandeRepository;
import bf.e_fixell_backoffice.service.dto.CommandeCriteria;
import bf.e_fixell_backoffice.service.dto.CommandeDTO;
import bf.e_fixell_backoffice.service.mapper.CommandeMapper;

/**
 * Service for executing complex queries for {@link Commande} entities in the database.
 * The main input is a {@link CommandeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CommandeDTO} or a {@link Page} of {@link CommandeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CommandeQueryService extends QueryService<Commande> {

    private final Logger log = LoggerFactory.getLogger(CommandeQueryService.class);

    private final CommandeRepository commandeRepository;

    private final CommandeMapper commandeMapper;

    public CommandeQueryService(CommandeRepository commandeRepository, CommandeMapper commandeMapper) {
        this.commandeRepository = commandeRepository;
        this.commandeMapper = commandeMapper;
    }

    /**
     * Return a {@link List} of {@link CommandeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CommandeDTO> findByCriteria(CommandeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Commande> specification = createSpecification(criteria);
        return commandeMapper.toDto(commandeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CommandeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CommandeDTO> findByCriteria(CommandeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Commande> specification = createSpecification(criteria);
        return commandeRepository.findAll(specification, page)
            .map(commandeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CommandeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Commande> specification = createSpecification(criteria);
        return commandeRepository.count(specification);
    }

    /**
     * Function to convert {@link CommandeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Commande> createSpecification(CommandeCriteria criteria) {
        Specification<Commande> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Commande_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Commande_.code));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Commande_.libelle));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), Commande_.date));
            }
            if (criteria.getSomme() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSomme(), Commande_.somme));
            }
            if (criteria.getDateLivraisonPrevu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateLivraisonPrevu(), Commande_.dateLivraisonPrevu));
            }
            if (criteria.getEtat() != null) {
                specification = specification.and(buildSpecification(criteria.getEtat(), Commande_.etat));
            }
            if (criteria.getMotif() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMotif(), Commande_.motif));
            }
            if (criteria.getAvance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAvance(), Commande_.avance));
            }
            if (criteria.getAvanceEnPercent() != null) {
                specification = specification.and(buildSpecification(criteria.getAvanceEnPercent(), Commande_.avanceEnPercent));
            }
            if (criteria.getTransactionId() != null) {
                specification = specification.and(buildSpecification(criteria.getTransactionId(),
                    root -> root.join(Commande_.transactions, JoinType.LEFT).get(Transaction_.id)));
            }
            if (criteria.getLivraisonId() != null) {
                specification = specification.and(buildSpecification(criteria.getLivraisonId(),
                    root -> root.join(Commande_.livraisons, JoinType.LEFT).get(Livraison_.id)));
            }
            if (criteria.getPaiementId() != null) {
                specification = specification.and(buildSpecification(criteria.getPaiementId(),
                    root -> root.join(Commande_.paiements, JoinType.LEFT).get(Paiement_.id)));
            }
            if (criteria.getFraisId() != null) {
                specification = specification.and(buildSpecification(criteria.getFraisId(),
                    root -> root.join(Commande_.frais, JoinType.LEFT).get(Frais_.id)));
            }
            if (criteria.getFournisseurId() != null) {
                specification = specification.and(buildSpecification(criteria.getFournisseurId(),
                    root -> root.join(Commande_.fournisseur, JoinType.LEFT).get(Fournisseur_.id)));
            }
            if (criteria.getClientId() != null) {
                specification = specification.and(buildSpecification(criteria.getClientId(),
                    root -> root.join(Commande_.client, JoinType.LEFT).get(Client_.id)));
            }
        }
        return specification;
    }
}
