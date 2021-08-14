package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.SessionCaisse;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SessionCaisse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SessionCaisseRepository extends JpaRepository<SessionCaisse, Long> {
}
