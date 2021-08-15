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

import bf.e_fixell_backoffice.domain.HistoriqueAffectation;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.HistoriqueAffectationRepository;
import bf.e_fixell_backoffice.service.dto.HistoriqueAffectationCriteria;
import bf.e_fixell_backoffice.service.dto.HistoriqueAffectationDTO;
import bf.e_fixell_backoffice.service.mapper.HistoriqueAffectationMapper;

/**
 * Service for executing complex queries for {@link HistoriqueAffectation} entities in the database.
 * The main input is a {@link HistoriqueAffectationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link HistoriqueAffectationDTO} or a {@link Page} of {@link HistoriqueAffectationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class HistoriqueAffectationQueryService extends QueryService<HistoriqueAffectation> {

    private final Logger log = LoggerFactory.getLogger(HistoriqueAffectationQueryService.class);

    private final HistoriqueAffectationRepository historiqueAffectationRepository;

    private final HistoriqueAffectationMapper historiqueAffectationMapper;

    public HistoriqueAffectationQueryService(HistoriqueAffectationRepository historiqueAffectationRepository, HistoriqueAffectationMapper historiqueAffectationMapper) {
        this.historiqueAffectationRepository = historiqueAffectationRepository;
        this.historiqueAffectationMapper = historiqueAffectationMapper;
    }

    /**
     * Return a {@link List} of {@link HistoriqueAffectationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<HistoriqueAffectationDTO> findByCriteria(HistoriqueAffectationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<HistoriqueAffectation> specification = createSpecification(criteria);
        return historiqueAffectationMapper.toDto(historiqueAffectationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link HistoriqueAffectationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<HistoriqueAffectationDTO> findByCriteria(HistoriqueAffectationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<HistoriqueAffectation> specification = createSpecification(criteria);
        return historiqueAffectationRepository.findAll(specification, page)
            .map(historiqueAffectationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(HistoriqueAffectationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<HistoriqueAffectation> specification = createSpecification(criteria);
        return historiqueAffectationRepository.count(specification);
    }

    /**
     * Function to convert {@link HistoriqueAffectationCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<HistoriqueAffectation> createSpecification(HistoriqueAffectationCriteria criteria) {
        Specification<HistoriqueAffectation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), HistoriqueAffectation_.id));
            }
            if (criteria.getDateDebut() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateDebut(), HistoriqueAffectation_.dateDebut));
            }
            if (criteria.getDateFin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateFin(), HistoriqueAffectation_.dateFin));
            }
            if (criteria.getSalaire() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSalaire(), HistoriqueAffectation_.salaire));
            }
            if (criteria.getPersonnelId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonnelId(),
                    root -> root.join(HistoriqueAffectation_.personnel, JoinType.LEFT).get(Personnel_.id)));
            }
            if (criteria.getFonctionId() != null) {
                specification = specification.and(buildSpecification(criteria.getFonctionId(),
                    root -> root.join(HistoriqueAffectation_.fonction, JoinType.LEFT).get(Fonction_.id)));
            }
        }
        return specification;
    }
}
