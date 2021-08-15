package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.PrixProduit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PrixProduit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrixProduitRepository extends JpaRepository<PrixProduit, Long>, JpaSpecificationExecutor<PrixProduit> {
}
