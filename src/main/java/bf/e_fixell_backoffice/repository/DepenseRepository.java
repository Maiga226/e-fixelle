package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.Depense;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Depense entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepenseRepository extends JpaRepository<Depense, Long>, JpaSpecificationExecutor<Depense> {
}
