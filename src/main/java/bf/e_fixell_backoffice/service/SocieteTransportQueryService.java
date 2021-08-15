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

import bf.e_fixell_backoffice.domain.SocieteTransport;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.SocieteTransportRepository;
import bf.e_fixell_backoffice.service.dto.SocieteTransportCriteria;
import bf.e_fixell_backoffice.service.dto.SocieteTransportDTO;
import bf.e_fixell_backoffice.service.mapper.SocieteTransportMapper;

/**
 * Service for executing complex queries for {@link SocieteTransport} entities in the database.
 * The main input is a {@link SocieteTransportCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SocieteTransportDTO} or a {@link Page} of {@link SocieteTransportDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SocieteTransportQueryService extends QueryService<SocieteTransport> {

    private final Logger log = LoggerFactory.getLogger(SocieteTransportQueryService.class);

    private final SocieteTransportRepository societeTransportRepository;

    private final SocieteTransportMapper societeTransportMapper;

    public SocieteTransportQueryService(SocieteTransportRepository societeTransportRepository, SocieteTransportMapper societeTransportMapper) {
        this.societeTransportRepository = societeTransportRepository;
        this.societeTransportMapper = societeTransportMapper;
    }

    /**
     * Return a {@link List} of {@link SocieteTransportDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SocieteTransportDTO> findByCriteria(SocieteTransportCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SocieteTransport> specification = createSpecification(criteria);
        return societeTransportMapper.toDto(societeTransportRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SocieteTransportDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SocieteTransportDTO> findByCriteria(SocieteTransportCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SocieteTransport> specification = createSpecification(criteria);
        return societeTransportRepository.findAll(specification, page)
            .map(societeTransportMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SocieteTransportCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SocieteTransport> specification = createSpecification(criteria);
        return societeTransportRepository.count(specification);
    }

    /**
     * Function to convert {@link SocieteTransportCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<SocieteTransport> createSpecification(SocieteTransportCriteria criteria) {
        Specification<SocieteTransport> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), SocieteTransport_.id));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), SocieteTransport_.libelle));
            }
            if (criteria.getAdresse() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAdresse(), SocieteTransport_.adresse));
            }
            if (criteria.getTelephone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone(), SocieteTransport_.telephone));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), SocieteTransport_.email));
            }
            if (criteria.getTransportId() != null) {
                specification = specification.and(buildSpecification(criteria.getTransportId(),
                    root -> root.join(SocieteTransport_.transports, JoinType.LEFT).get(Transport_.id)));
            }
        }
        return specification;
    }
}
