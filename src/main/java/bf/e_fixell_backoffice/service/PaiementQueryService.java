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

import bf.e_fixell_backoffice.domain.Paiement;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.PaiementRepository;
import bf.e_fixell_backoffice.service.dto.PaiementCriteria;
import bf.e_fixell_backoffice.service.dto.PaiementDTO;
import bf.e_fixell_backoffice.service.mapper.PaiementMapper;

/**
 * Service for executing complex queries for {@link Paiement} entities in the database.
 * The main input is a {@link PaiementCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PaiementDTO} or a {@link Page} of {@link PaiementDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PaiementQueryService extends QueryService<Paiement> {

    private final Logger log = LoggerFactory.getLogger(PaiementQueryService.class);

    private final PaiementRepository paiementRepository;

    private final PaiementMapper paiementMapper;

    public PaiementQueryService(PaiementRepository paiementRepository, PaiementMapper paiementMapper) {
        this.paiementRepository = paiementRepository;
        this.paiementMapper = paiementMapper;
    }

    /**
     * Return a {@link List} of {@link PaiementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PaiementDTO> findByCriteria(PaiementCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Paiement> specification = createSpecification(criteria);
        return paiementMapper.toDto(paiementRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PaiementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PaiementDTO> findByCriteria(PaiementCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Paiement> specification = createSpecification(criteria);
        return paiementRepository.findAll(specification, page)
            .map(paiementMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PaiementCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Paiement> specification = createSpecification(criteria);
        return paiementRepository.count(specification);
    }

    /**
     * Function to convert {@link PaiementCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Paiement> createSpecification(PaiementCriteria criteria) {
        Specification<Paiement> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Paiement_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Paiement_.code));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), Paiement_.date));
            }
            if (criteria.getMontant() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontant(), Paiement_.montant));
            }
            if (criteria.getCommandeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCommandeId(),
                    root -> root.join(Paiement_.commande, JoinType.LEFT).get(Commande_.id)));
            }
            if (criteria.getVenteId() != null) {
                specification = specification.and(buildSpecification(criteria.getVenteId(),
                    root -> root.join(Paiement_.vente, JoinType.LEFT).get(Vente_.id)));
            }
            if (criteria.getSessioncaisseId() != null) {
                specification = specification.and(buildSpecification(criteria.getSessioncaisseId(),
                    root -> root.join(Paiement_.sessioncaisse, JoinType.LEFT).get(SessionCaisse_.id)));
            }
        }
        return specification;
    }
}
