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

import bf.e_fixell_backoffice.domain.SessionCaisse;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.SessionCaisseRepository;
import bf.e_fixell_backoffice.service.dto.SessionCaisseCriteria;
import bf.e_fixell_backoffice.service.dto.SessionCaisseDTO;
import bf.e_fixell_backoffice.service.mapper.SessionCaisseMapper;

/**
 * Service for executing complex queries for {@link SessionCaisse} entities in the database.
 * The main input is a {@link SessionCaisseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SessionCaisseDTO} or a {@link Page} of {@link SessionCaisseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SessionCaisseQueryService extends QueryService<SessionCaisse> {

    private final Logger log = LoggerFactory.getLogger(SessionCaisseQueryService.class);

    private final SessionCaisseRepository sessionCaisseRepository;

    private final SessionCaisseMapper sessionCaisseMapper;

    public SessionCaisseQueryService(SessionCaisseRepository sessionCaisseRepository, SessionCaisseMapper sessionCaisseMapper) {
        this.sessionCaisseRepository = sessionCaisseRepository;
        this.sessionCaisseMapper = sessionCaisseMapper;
    }

    /**
     * Return a {@link List} of {@link SessionCaisseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SessionCaisseDTO> findByCriteria(SessionCaisseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SessionCaisse> specification = createSpecification(criteria);
        return sessionCaisseMapper.toDto(sessionCaisseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SessionCaisseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SessionCaisseDTO> findByCriteria(SessionCaisseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SessionCaisse> specification = createSpecification(criteria);
        return sessionCaisseRepository.findAll(specification, page)
            .map(sessionCaisseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SessionCaisseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SessionCaisse> specification = createSpecification(criteria);
        return sessionCaisseRepository.count(specification);
    }

    /**
     * Function to convert {@link SessionCaisseCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<SessionCaisse> createSpecification(SessionCaisseCriteria criteria) {
        Specification<SessionCaisse> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), SessionCaisse_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCode(), SessionCaisse_.code));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), SessionCaisse_.libelle));
            }
            if (criteria.getDateDebut() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateDebut(), SessionCaisse_.dateDebut));
            }
            if (criteria.getDateFin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateFin(), SessionCaisse_.dateFin));
            }
            if (criteria.getSommeDebut() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSommeDebut(), SessionCaisse_.sommeDebut));
            }
            if (criteria.getSommeFin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSommeFin(), SessionCaisse_.sommeFin));
            }
            if (criteria.getDepassement() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDepassement(), SessionCaisse_.depassement));
            }
            if (criteria.getManquant() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getManquant(), SessionCaisse_.manquant));
            }
            if (criteria.getStatut() != null) {
                specification = specification.and(buildSpecification(criteria.getStatut(), SessionCaisse_.statut));
            }
            if (criteria.getPaiementId() != null) {
                specification = specification.and(buildSpecification(criteria.getPaiementId(),
                    root -> root.join(SessionCaisse_.paiements, JoinType.LEFT).get(Paiement_.id)));
            }
            if (criteria.getDepenseId() != null) {
                specification = specification.and(buildSpecification(criteria.getDepenseId(),
                    root -> root.join(SessionCaisse_.depenses, JoinType.LEFT).get(Depense_.id)));
            }
            if (criteria.getCaisseId() != null) {
                specification = specification.and(buildSpecification(criteria.getCaisseId(),
                    root -> root.join(SessionCaisse_.caisse, JoinType.LEFT).get(Caisse_.id)));
            }
        }
        return specification;
    }
}
