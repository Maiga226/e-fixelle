package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.Perte;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Perte entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PerteRepository extends JpaRepository<Perte, Long>, JpaSpecificationExecutor<Perte> {
}
