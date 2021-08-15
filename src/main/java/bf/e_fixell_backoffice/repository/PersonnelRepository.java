package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.Personnel;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Personnel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long>, JpaSpecificationExecutor<Personnel> {
}
