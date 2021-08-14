package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.SocieteTransport;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SocieteTransport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SocieteTransportRepository extends JpaRepository<SocieteTransport, Long> {
}
