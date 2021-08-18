package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.Profil;

import org.springframework.boot.loader.tools.LibraryScope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Profil entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfilRepository extends JpaRepository<Profil, Long>, JpaSpecificationExecutor<Profil> {
    @Query("select p from  Profil p where p.id=:id")
     Profil findOne(@Param("id") Long id);

    Profil findByLibelle(String libelle);

    @Modifying
    @Query("UPDATE Profil profil SET  profil.deleted= true where profil.id=:id")
    void deleteById(@Param("id") Long id);

    @Query("select p from Profil p where p.deleted=false")
    Page<Profil> findAll(@Nullable Specification sp, Pageable pageable);
}
