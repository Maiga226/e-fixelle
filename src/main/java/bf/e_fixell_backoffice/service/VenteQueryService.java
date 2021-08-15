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

import bf.e_fixell_backoffice.domain.Vente;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.VenteRepository;
import bf.e_fixell_backoffice.service.dto.VenteCriteria;
import bf.e_fixell_backoffice.service.dto.VenteDTO;
import bf.e_fixell_backoffice.service.mapper.VenteMapper;

/**
 * Service for executing complex queries for {@link Vente} entities in the database.
 * The main input is a {@link VenteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VenteDTO} or a {@link Page} of {@link VenteDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VenteQueryService extends QueryService<Vente> {

    private final Logger log = LoggerFactory.getLogger(VenteQueryService.class);

    private final VenteRepository venteRepository;

    private final VenteMapper venteMapper;

    public VenteQueryService(VenteRepository venteRepository, VenteMapper venteMapper) {
        this.venteRepository = venteRepository;
        this.venteMapper = venteMapper;
    }

    /**
     * Return a {@link List} of {@link VenteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VenteDTO> findByCriteria(VenteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Vente> specification = createSpecification(criteria);
        return venteMapper.toDto(venteRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link VenteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VenteDTO> findByCriteria(VenteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Vente> specification = createSpecification(criteria);
        return venteRepository.findAll(specification, page)
            .map(venteMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VenteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Vente> specification = createSpecification(criteria);
        return venteRepository.count(specification);
    }

    /**
     * Function to convert {@link VenteCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Vente> createSpecification(VenteCriteria criteria) {
        Specification<Vente> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Vente_.id));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Vente_.libelle));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Vente_.code));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), Vente_.date));
            }
            if (criteria.getMontant() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontant(), Vente_.montant));
            }
            if (criteria.getReste() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReste(), Vente_.reste));
            }
            if (criteria.getSolder() != null) {
                specification = specification.and(buildSpecification(criteria.getSolder(), Vente_.solder));
            }
            if (criteria.getTransactionId() != null) {
                specification = specification.and(buildSpecification(criteria.getTransactionId(),
                    root -> root.join(Vente_.transactions, JoinType.LEFT).get(Transaction_.id)));
            }
            if (criteria.getPaiementId() != null) {
                specification = specification.and(buildSpecification(criteria.getPaiementId(),
                    root -> root.join(Vente_.paiements, JoinType.LEFT).get(Paiement_.id)));
            }
            if (criteria.getClientId() != null) {
                specification = specification.and(buildSpecification(criteria.getClientId(),
                    root -> root.join(Vente_.client, JoinType.LEFT).get(Client_.id)));
            }
        }
        return specification;
    }
}
