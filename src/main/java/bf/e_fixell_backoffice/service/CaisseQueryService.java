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

import bf.e_fixell_backoffice.domain.Caisse;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.CaisseRepository;
import bf.e_fixell_backoffice.service.dto.CaisseCriteria;
import bf.e_fixell_backoffice.service.dto.CaisseDTO;
import bf.e_fixell_backoffice.service.mapper.CaisseMapper;

/**
 * Service for executing complex queries for {@link Caisse} entities in the database.
 * The main input is a {@link CaisseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaisseDTO} or a {@link Page} of {@link CaisseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaisseQueryService extends QueryService<Caisse> {

    private final Logger log = LoggerFactory.getLogger(CaisseQueryService.class);

    private final CaisseRepository caisseRepository;

    private final CaisseMapper caisseMapper;

    public CaisseQueryService(CaisseRepository caisseRepository, CaisseMapper caisseMapper) {
        this.caisseRepository = caisseRepository;
        this.caisseMapper = caisseMapper;
    }

    /**
     * Return a {@link List} of {@link CaisseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaisseDTO> findByCriteria(CaisseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Caisse> specification = createSpecification(criteria);
        return caisseMapper.toDto(caisseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaisseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaisseDTO> findByCriteria(CaisseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Caisse> specification = createSpecification(criteria);
        return caisseRepository.findAll(specification, page)
            .map(caisseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CaisseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Caisse> specification = createSpecification(criteria);
        return caisseRepository.count(specification);
    }

    /**
     * Function to convert {@link CaisseCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Caisse> createSpecification(CaisseCriteria criteria) {
        Specification<Caisse> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Caisse_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Caisse_.code));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Caisse_.libelle));
            }
            if (criteria.getSommeMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSommeMin(), Caisse_.sommeMin));
            }
            if (criteria.getSommeMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSommeMax(), Caisse_.sommeMax));
            }
            if (criteria.getSomme() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSomme(), Caisse_.somme));
            }
            if (criteria.getStatut() != null) {
                specification = specification.and(buildSpecification(criteria.getStatut(), Caisse_.statut));
            }
            if (criteria.getTypeCaisse() != null) {
                specification = specification.and(buildSpecification(criteria.getTypeCaisse(), Caisse_.typeCaisse));
            }
            if (criteria.getSessionCaisseId() != null) {
                specification = specification.and(buildSpecification(criteria.getSessionCaisseId(),
                    root -> root.join(Caisse_.sessionCaisses, JoinType.LEFT).get(SessionCaisse_.id)));
            }
        }
        return specification;
    }
}
