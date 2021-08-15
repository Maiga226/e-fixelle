package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.UniteOrganisation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UniteOrganisation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UniteOrganisationRepository extends JpaRepository<UniteOrganisation, Long>, JpaSpecificationExecutor<UniteOrganisation> {
}
