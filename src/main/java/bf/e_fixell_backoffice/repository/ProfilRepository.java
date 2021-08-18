package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.Profil;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Profil entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfilRepository extends JpaRepository<Profil, Long>, JpaSpecificationExecutor<Profil> {
    @Query("select p from  Profil p where p.id=:id")
     Profil findOne(@Param("id") Long id);

    Profil findByLibelle(String libelle);
}
