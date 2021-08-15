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

import bf.e_fixell_backoffice.domain.Fournisseur;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.FournisseurRepository;
import bf.e_fixell_backoffice.service.dto.FournisseurCriteria;
import bf.e_fixell_backoffice.service.dto.FournisseurDTO;
import bf.e_fixell_backoffice.service.mapper.FournisseurMapper;

/**
 * Service for executing complex queries for {@link Fournisseur} entities in the database.
 * The main input is a {@link FournisseurCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FournisseurDTO} or a {@link Page} of {@link FournisseurDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FournisseurQueryService extends QueryService<Fournisseur> {

    private final Logger log = LoggerFactory.getLogger(FournisseurQueryService.class);

    private final FournisseurRepository fournisseurRepository;

    private final FournisseurMapper fournisseurMapper;

    public FournisseurQueryService(FournisseurRepository fournisseurRepository, FournisseurMapper fournisseurMapper) {
        this.fournisseurRepository = fournisseurRepository;
        this.fournisseurMapper = fournisseurMapper;
    }

    /**
     * Return a {@link List} of {@link FournisseurDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FournisseurDTO> findByCriteria(FournisseurCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Fournisseur> specification = createSpecification(criteria);
        return fournisseurMapper.toDto(fournisseurRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FournisseurDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FournisseurDTO> findByCriteria(FournisseurCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Fournisseur> specification = createSpecification(criteria);
        return fournisseurRepository.findAll(specification, page)
            .map(fournisseurMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FournisseurCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Fournisseur> specification = createSpecification(criteria);
        return fournisseurRepository.count(specification);
    }

    /**
     * Function to convert {@link FournisseurCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Fournisseur> createSpecification(FournisseurCriteria criteria) {
        Specification<Fournisseur> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Fournisseur_.id));
            }
            if (criteria.getNom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNom(), Fournisseur_.nom));
            }
            if (criteria.getPrenom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrenom(), Fournisseur_.prenom));
            }
            if (criteria.getRaisonSocial() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRaisonSocial(), Fournisseur_.raisonSocial));
            }
            if (criteria.getRue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRue(), Fournisseur_.rue));
            }
            if (criteria.getTelephone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone(), Fournisseur_.telephone));
            }
            if (criteria.getFixe() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFixe(), Fournisseur_.fixe));
            }
            if (criteria.getCodePostale() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodePostale(), Fournisseur_.codePostale));
            }
            if (criteria.getNumeroReseauSocial() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroReseauSocial(), Fournisseur_.numeroReseauSocial));
            }
            if (criteria.getTypePersonne() != null) {
                specification = specification.and(buildSpecification(criteria.getTypePersonne(), Fournisseur_.typePersonne));
            }
            if (criteria.getCommandeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCommandeId(),
                    root -> root.join(Fournisseur_.commandes, JoinType.LEFT).get(Commande_.id)));
            }
            if (criteria.getApprovisionnementId() != null) {
                specification = specification.and(buildSpecification(criteria.getApprovisionnementId(),
                    root -> root.join(Fournisseur_.approvisionnements, JoinType.LEFT).get(Approvisionnement_.id)));
            }
            if (criteria.getLivraisonId() != null) {
                specification = specification.and(buildSpecification(criteria.getLivraisonId(),
                    root -> root.join(Fournisseur_.livraisons, JoinType.LEFT).get(Livraison_.id)));
            }
            if (criteria.getAdresseId() != null) {
                specification = specification.and(buildSpecification(criteria.getAdresseId(),
                    root -> root.join(Fournisseur_.adresse, JoinType.LEFT).get(UniteOrganisation_.id)));
            }
        }
        return specification;
    }
}
