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

import bf.e_fixell_backoffice.domain.Profil;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.ProfilRepository;
import bf.e_fixell_backoffice.service.dto.ProfilCriteria;
import bf.e_fixell_backoffice.service.dto.ProfilDTO;
import bf.e_fixell_backoffice.service.mapper.ProfilMapper;

/**
 * Service for executing complex queries for {@link Profil} entities in the database.
 * The main input is a {@link ProfilCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProfilDTO} or a {@link Page} of {@link ProfilDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProfilQueryService extends QueryService<Profil> {

    private final Logger log = LoggerFactory.getLogger(ProfilQueryService.class);

    private final ProfilRepository profilRepository;

    private final ProfilMapper profilMapper;

    public ProfilQueryService(ProfilRepository profilRepository, ProfilMapper profilMapper) {
        this.profilRepository = profilRepository;
        this.profilMapper = profilMapper;
    }

    /**
     * Return a {@link List} of {@link ProfilDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProfilDTO> findByCriteria(ProfilCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Profil> specification = createSpecification(criteria);
        return profilMapper.toDto(profilRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProfilDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProfilDTO> findByCriteria(ProfilCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Profil> specification = createSpecification(criteria);
        return profilRepository.findAll(specification, page)
            .map(profilMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProfilCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Profil> specification = createSpecification(criteria);
        return profilRepository.count(specification);
    }

    /**
     * Function to convert {@link ProfilCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Profil> createSpecification(ProfilCriteria criteria) {
        Specification<Profil> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Profil_.id));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Profil_.libelle));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Profil_.description));
            }
        }
        return specification;
    }
}
