package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.Livraison;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Livraison entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LivraisonRepository extends JpaRepository<Livraison, Long>, JpaSpecificationExecutor<Livraison> {
}
