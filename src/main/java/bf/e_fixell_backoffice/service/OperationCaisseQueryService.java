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

import bf.e_fixell_backoffice.domain.OperationCaisse;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.OperationCaisseRepository;
import bf.e_fixell_backoffice.service.dto.OperationCaisseCriteria;
import bf.e_fixell_backoffice.service.dto.OperationCaisseDTO;
import bf.e_fixell_backoffice.service.mapper.OperationCaisseMapper;

/**
 * Service for executing complex queries for {@link OperationCaisse} entities in the database.
 * The main input is a {@link OperationCaisseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OperationCaisseDTO} or a {@link Page} of {@link OperationCaisseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OperationCaisseQueryService extends QueryService<OperationCaisse> {

    private final Logger log = LoggerFactory.getLogger(OperationCaisseQueryService.class);

    private final OperationCaisseRepository operationCaisseRepository;

    private final OperationCaisseMapper operationCaisseMapper;

    public OperationCaisseQueryService(OperationCaisseRepository operationCaisseRepository, OperationCaisseMapper operationCaisseMapper) {
        this.operationCaisseRepository = operationCaisseRepository;
        this.operationCaisseMapper = operationCaisseMapper;
    }

    /**
     * Return a {@link List} of {@link OperationCaisseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OperationCaisseDTO> findByCriteria(OperationCaisseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<OperationCaisse> specification = createSpecification(criteria);
        return operationCaisseMapper.toDto(operationCaisseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OperationCaisseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OperationCaisseDTO> findByCriteria(OperationCaisseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OperationCaisse> specification = createSpecification(criteria);
        return operationCaisseRepository.findAll(specification, page)
            .map(operationCaisseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OperationCaisseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<OperationCaisse> specification = createSpecification(criteria);
        return operationCaisseRepository.count(specification);
    }

    /**
     * Function to convert {@link OperationCaisseCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<OperationCaisse> createSpecification(OperationCaisseCriteria criteria) {
        Specification<OperationCaisse> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), OperationCaisse_.id));
            }
            if (criteria.getTypeOperationCaisse() != null) {
                specification = specification.and(buildSpecification(criteria.getTypeOperationCaisse(), OperationCaisse_.typeOperationCaisse));
            }
            if (criteria.getMontant() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontant(), OperationCaisse_.montant));
            }
            if (criteria.getCaisseSrcId() != null) {
                specification = specification.and(buildSpecification(criteria.getCaisseSrcId(),
                    root -> root.join(OperationCaisse_.caisseSrc, JoinType.LEFT).get(Caisse_.id)));
            }
            if (criteria.getCaisseDstId() != null) {
                specification = specification.and(buildSpecification(criteria.getCaisseDstId(),
                    root -> root.join(OperationCaisse_.caisseDst, JoinType.LEFT).get(Caisse_.id)));
            }
        }
        return specification;
    }
}
