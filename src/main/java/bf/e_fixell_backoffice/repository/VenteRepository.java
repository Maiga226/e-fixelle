package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.Vente;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Vente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VenteRepository extends JpaRepository<Vente, Long>, JpaSpecificationExecutor<Vente> {
}
