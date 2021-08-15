package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.Fonction;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Fonction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FonctionRepository extends JpaRepository<Fonction, Long>, JpaSpecificationExecutor<Fonction> {
}
