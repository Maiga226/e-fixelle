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

import bf.e_fixell_backoffice.domain.Transport;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.TransportRepository;
import bf.e_fixell_backoffice.service.dto.TransportCriteria;
import bf.e_fixell_backoffice.service.dto.TransportDTO;
import bf.e_fixell_backoffice.service.mapper.TransportMapper;

/**
 * Service for executing complex queries for {@link Transport} entities in the database.
 * The main input is a {@link TransportCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TransportDTO} or a {@link Page} of {@link TransportDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TransportQueryService extends QueryService<Transport> {

    private final Logger log = LoggerFactory.getLogger(TransportQueryService.class);

    private final TransportRepository transportRepository;

    private final TransportMapper transportMapper;

    public TransportQueryService(TransportRepository transportRepository, TransportMapper transportMapper) {
        this.transportRepository = transportRepository;
        this.transportMapper = transportMapper;
    }

    /**
     * Return a {@link List} of {@link TransportDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TransportDTO> findByCriteria(TransportCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Transport> specification = createSpecification(criteria);
        return transportMapper.toDto(transportRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TransportDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TransportDTO> findByCriteria(TransportCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Transport> specification = createSpecification(criteria);
        return transportRepository.findAll(specification, page)
            .map(transportMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TransportCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Transport> specification = createSpecification(criteria);
        return transportRepository.count(specification);
    }

    /**
     * Function to convert {@link TransportCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Transport> createSpecification(TransportCriteria criteria) {
        Specification<Transport> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Transport_.id));
            }
            if (criteria.getTypeTransport() != null) {
                specification = specification.and(buildSpecification(criteria.getTypeTransport(), Transport_.typeTransport));
            }
            if (criteria.getLivraisonId() != null) {
                specification = specification.and(buildSpecification(criteria.getLivraisonId(),
                    root -> root.join(Transport_.livraisons, JoinType.LEFT).get(Livraison_.id)));
            }
            if (criteria.getSocieteTransportId() != null) {
                specification = specification.and(buildSpecification(criteria.getSocieteTransportId(),
                    root -> root.join(Transport_.societeTransport, JoinType.LEFT).get(SocieteTransport_.id)));
            }
        }
        return specification;
    }
}
