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

import bf.e_fixell_backoffice.domain.Approvisionnement;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.ApprovisionnementRepository;
import bf.e_fixell_backoffice.service.dto.ApprovisionnementCriteria;
import bf.e_fixell_backoffice.service.dto.ApprovisionnementDTO;
import bf.e_fixell_backoffice.service.mapper.ApprovisionnementMapper;

/**
 * Service for executing complex queries for {@link Approvisionnement} entities in the database.
 * The main input is a {@link ApprovisionnementCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApprovisionnementDTO} or a {@link Page} of {@link ApprovisionnementDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApprovisionnementQueryService extends QueryService<Approvisionnement> {

    private final Logger log = LoggerFactory.getLogger(ApprovisionnementQueryService.class);

    private final ApprovisionnementRepository approvisionnementRepository;

    private final ApprovisionnementMapper approvisionnementMapper;

    public ApprovisionnementQueryService(ApprovisionnementRepository approvisionnementRepository, ApprovisionnementMapper approvisionnementMapper) {
        this.approvisionnementRepository = approvisionnementRepository;
        this.approvisionnementMapper = approvisionnementMapper;
    }

    /**
     * Return a {@link List} of {@link ApprovisionnementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ApprovisionnementDTO> findByCriteria(ApprovisionnementCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Approvisionnement> specification = createSpecification(criteria);
        return approvisionnementMapper.toDto(approvisionnementRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ApprovisionnementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ApprovisionnementDTO> findByCriteria(ApprovisionnementCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Approvisionnement> specification = createSpecification(criteria);
        return approvisionnementRepository.findAll(specification, page)
            .map(approvisionnementMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ApprovisionnementCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Approvisionnement> specification = createSpecification(criteria);
        return approvisionnementRepository.count(specification);
    }

    /**
     * Function to convert {@link ApprovisionnementCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Approvisionnement> createSpecification(ApprovisionnementCriteria criteria) {
        Specification<Approvisionnement> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Approvisionnement_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Approvisionnement_.code));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Approvisionnement_.libelle));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), Approvisionnement_.date));
            }
            if (criteria.getMontant() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMontant(), Approvisionnement_.montant));
            }
            if (criteria.getTransactionId() != null) {
                specification = specification.and(buildSpecification(criteria.getTransactionId(),
                    root -> root.join(Approvisionnement_.transactions, JoinType.LEFT).get(Transaction_.id)));
            }
            if (criteria.getFournisseurId() != null) {
                specification = specification.and(buildSpecification(criteria.getFournisseurId(),
                    root -> root.join(Approvisionnement_.fournisseur, JoinType.LEFT).get(Fournisseur_.id)));
            }
        }
        return specification;
    }
}
