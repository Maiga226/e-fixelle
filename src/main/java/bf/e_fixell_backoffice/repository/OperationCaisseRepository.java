package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.OperationCaisse;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OperationCaisse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperationCaisseRepository extends JpaRepository<OperationCaisse, Long> {
}
