package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.Fournisseur;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Fournisseur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long>, JpaSpecificationExecutor<Fournisseur> {
}
