package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.Produit;

import org.hibernate.validator.constraints.EAN;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Produit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>, JpaSpecificationExecutor<Produit> {

    @Query("select p from Produit p where p.deleted=false")
    List<Produit> findAll();

    @Query("select p from Produit p  where(" +
        "(:code is null or :code='' or UPPER(p.code) like upper('%'||:code||'%'))" +
        "and (:libelle is null or :libelle='' or UPPER(p.libelle) like upper('%'||:libelle||'%') )" +
        "and (:categorieId is null or p.categorie.id=:categorieId)" +
        "and (:classificationId is null  or p.classification.id=:classificationId)"+
        "and (p.deleted=false)" +
        ")")
    Page<Produit> findWithCriteria(
        Pageable pageable,
        @Param(value = "code") String code,
        @Param(value = "libelle") String libelle,
        @Param(value = "categorieId") Long categorieId,
        @Param(value = "classificationId") Long classificationId
    );

    @Modifying
    @Query("UPDATE Produit p SET  p.deleted= true where p.id=:id")
    void deleteById(@Param("id") Long id);
}
