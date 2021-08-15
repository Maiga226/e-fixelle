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

import bf.e_fixell_backoffice.domain.PrixProduit;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.PrixProduitRepository;
import bf.e_fixell_backoffice.service.dto.PrixProduitCriteria;
import bf.e_fixell_backoffice.service.dto.PrixProduitDTO;
import bf.e_fixell_backoffice.service.mapper.PrixProduitMapper;

/**
 * Service for executing complex queries for {@link PrixProduit} entities in the database.
 * The main input is a {@link PrixProduitCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PrixProduitDTO} or a {@link Page} of {@link PrixProduitDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PrixProduitQueryService extends QueryService<PrixProduit> {

    private final Logger log = LoggerFactory.getLogger(PrixProduitQueryService.class);

    private final PrixProduitRepository prixProduitRepository;

    private final PrixProduitMapper prixProduitMapper;

    public PrixProduitQueryService(PrixProduitRepository prixProduitRepository, PrixProduitMapper prixProduitMapper) {
        this.prixProduitRepository = prixProduitRepository;
        this.prixProduitMapper = prixProduitMapper;
    }

    /**
     * Return a {@link List} of {@link PrixProduitDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PrixProduitDTO> findByCriteria(PrixProduitCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PrixProduit> specification = createSpecification(criteria);
        return prixProduitMapper.toDto(prixProduitRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PrixProduitDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PrixProduitDTO> findByCriteria(PrixProduitCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PrixProduit> specification = createSpecification(criteria);
        return prixProduitRepository.findAll(specification, page)
            .map(prixProduitMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PrixProduitCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PrixProduit> specification = createSpecification(criteria);
        return prixProduitRepository.count(specification);
    }

    /**
     * Function to convert {@link PrixProduitCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<PrixProduit> createSpecification(PrixProduitCriteria criteria) {
        Specification<PrixProduit> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), PrixProduit_.id));
            }
            if (criteria.getDateDebut() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateDebut(), PrixProduit_.dateDebut));
            }
            if (criteria.getDateFin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateFin(), PrixProduit_.dateFin));
            }
            if (criteria.getPrix() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrix(), PrixProduit_.prix));
            }
            if (criteria.getStatut() != null) {
                specification = specification.and(buildSpecification(criteria.getStatut(), PrixProduit_.statut));
            }
            if (criteria.getProduitId() != null) {
                specification = specification.and(buildSpecification(criteria.getProduitId(),
                    root -> root.join(PrixProduit_.produit, JoinType.LEFT).get(Produit_.id)));
            }
        }
        return specification;
    }
}
