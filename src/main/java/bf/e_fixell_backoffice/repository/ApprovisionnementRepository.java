package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.Approvisionnement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Approvisionnement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApprovisionnementRepository extends JpaRepository<Approvisionnement, Long>, JpaSpecificationExecutor<Approvisionnement> {
}
