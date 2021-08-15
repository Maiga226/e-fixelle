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

import bf.e_fixell_backoffice.domain.Client;
import bf.e_fixell_backoffice.domain.*; // for static metamodels
import bf.e_fixell_backoffice.repository.ClientRepository;
import bf.e_fixell_backoffice.service.dto.ClientCriteria;
import bf.e_fixell_backoffice.service.dto.ClientDTO;
import bf.e_fixell_backoffice.service.mapper.ClientMapper;

/**
 * Service for executing complex queries for {@link Client} entities in the database.
 * The main input is a {@link ClientCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ClientDTO} or a {@link Page} of {@link ClientDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ClientQueryService extends QueryService<Client> {

    private final Logger log = LoggerFactory.getLogger(ClientQueryService.class);

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    public ClientQueryService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    /**
     * Return a {@link List} of {@link ClientDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ClientDTO> findByCriteria(ClientCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Client> specification = createSpecification(criteria);
        return clientMapper.toDto(clientRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ClientDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ClientDTO> findByCriteria(ClientCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Client> specification = createSpecification(criteria);
        return clientRepository.findAll(specification, page)
            .map(clientMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ClientCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Client> specification = createSpecification(criteria);
        return clientRepository.count(specification);
    }

    /**
     * Function to convert {@link ClientCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Client> createSpecification(ClientCriteria criteria) {
        Specification<Client> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Client_.id));
            }
            if (criteria.getNom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNom(), Client_.nom));
            }
            if (criteria.getPrenom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrenom(), Client_.prenom));
            }
            if (criteria.getRaisonSocial() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRaisonSocial(), Client_.raisonSocial));
            }
            if (criteria.getAdresse() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAdresse(), Client_.adresse));
            }
            if (criteria.getTelephone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone(), Client_.telephone));
            }
            if (criteria.getIdentifiant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdentifiant(), Client_.identifiant));
            }
            if (criteria.getTypePersonne() != null) {
                specification = specification.and(buildSpecification(criteria.getTypePersonne(), Client_.typePersonne));
            }
            if (criteria.getCommandeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCommandeId(),
                    root -> root.join(Client_.commandes, JoinType.LEFT).get(Commande_.id)));
            }
            if (criteria.getVenteId() != null) {
                specification = specification.and(buildSpecification(criteria.getVenteId(),
                    root -> root.join(Client_.ventes, JoinType.LEFT).get(Vente_.id)));
            }
        }
        return specification;
    }
}
