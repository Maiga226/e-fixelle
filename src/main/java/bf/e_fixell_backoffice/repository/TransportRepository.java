package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.Transport;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Transport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransportRepository extends JpaRepository<Transport, Long>, JpaSpecificationExecutor<Transport> {
}
