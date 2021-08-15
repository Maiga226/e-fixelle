package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.Categorie;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Categorie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long>, JpaSpecificationExecutor<Categorie> {
}
