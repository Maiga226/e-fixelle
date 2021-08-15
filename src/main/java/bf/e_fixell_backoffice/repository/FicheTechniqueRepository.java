package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.FicheTechnique;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FicheTechnique entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FicheTechniqueRepository extends JpaRepository<FicheTechnique, Long>, JpaSpecificationExecutor<FicheTechnique> {
}
