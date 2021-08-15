package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.HistoriqueAffectation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the HistoriqueAffectation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistoriqueAffectationRepository extends JpaRepository<HistoriqueAffectation, Long>, JpaSpecificationExecutor<HistoriqueAffectation> {
}
