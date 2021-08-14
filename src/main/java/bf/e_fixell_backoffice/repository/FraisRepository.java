package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.Frais;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Frais entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FraisRepository extends JpaRepository<Frais, Long> {
}
