package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.TypeFrais;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeFrais entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeFraisRepository extends JpaRepository<TypeFrais, Long> {
}
