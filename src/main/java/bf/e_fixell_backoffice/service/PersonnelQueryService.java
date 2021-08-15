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

import bf.e_fixell_backoffice.domain.Personnel;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.PersonnelRepository;
import bf.e_fixell_backoffice.service.dto.PersonnelCriteria;
import bf.e_fixell_backoffice.service.dto.PersonnelDTO;
import bf.e_fixell_backoffice.service.mapper.PersonnelMapper;

/**
 * Service for executing complex queries for {@link Personnel} entities in the database.
 * The main input is a {@link PersonnelCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PersonnelDTO} or a {@link Page} of {@link PersonnelDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PersonnelQueryService extends QueryService<Personnel> {

    private final Logger log = LoggerFactory.getLogger(PersonnelQueryService.class);

    private final PersonnelRepository personnelRepository;

    private final PersonnelMapper personnelMapper;

    public PersonnelQueryService(PersonnelRepository personnelRepository, PersonnelMapper personnelMapper) {
        this.personnelRepository = personnelRepository;
        this.personnelMapper = personnelMapper;
    }

    /**
     * Return a {@link List} of {@link PersonnelDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PersonnelDTO> findByCriteria(PersonnelCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Personnel> specification = createSpecification(criteria);
        return personnelMapper.toDto(personnelRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PersonnelDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PersonnelDTO> findByCriteria(PersonnelCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Personnel> specification = createSpecification(criteria);
        return personnelRepository.findAll(specification, page)
            .map(personnelMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PersonnelCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Personnel> specification = createSpecification(criteria);
        return personnelRepository.count(specification);
    }

    /**
     * Function to convert {@link PersonnelCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Personnel> createSpecification(PersonnelCriteria criteria) {
        Specification<Personnel> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Personnel_.id));
            }
            if (criteria.getNom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNom(), Personnel_.nom));
            }
            if (criteria.getPrenom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrenom(), Personnel_.prenom));
            }
            if (criteria.getTelephone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone(), Personnel_.telephone));
            }
            if (criteria.getMatricule() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMatricule(), Personnel_.matricule));
            }
            if (criteria.getSalaire() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSalaire(), Personnel_.salaire));
            }
            if (criteria.getHistoriqueAffectationId() != null) {
                specification = specification.and(buildSpecification(criteria.getHistoriqueAffectationId(),
                    root -> root.join(Personnel_.historiqueAffectations, JoinType.LEFT).get(HistoriqueAffectation_.id)));
            }
        }
        return specification;
    }
}
