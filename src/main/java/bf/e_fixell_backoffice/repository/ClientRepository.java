package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.Client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Client entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {

    @Query("select c from Client c  where(" +
        "(:nom is null or :nom='' or UPPER(c.nom) like upper('%'||:nom||'%'))" +
        "and (:prenom is null or :prenom='' or UPPER(c.prenom) like upper('%'||:prenom||'%') )" +
        "and (:raisonSocial is null or :raisonSocial=''  or LOWER(c.raisonSocial) like LOWER('%'||:raisonSocial||'%'))" +
        "and (:identifiant is null or :identifiant='' or UPPER(c.identifiant) like UPPER('%'||:identifiant||'%' ))" +
        "and (:telephone is null or :telephone='' or c.telephone=:telephone)" +
        "and (c.deleted=false)" +
        ")")
    Page<Client> findWithCriteria(
        Pageable pageable,
        @Param(value = "nom") String nom,
        @Param(value = "prenom") String prenom,
        @Param(value = "raisonSocial") String raisonSocial,
        @Param(value = "identifiant") String identifiant,
        @Param(value = "telephone") String telephone
    );

    @Modifying
    @Query("UPDATE Client c SET  c.deleted= true where c.id=:id")
    void deleteById(@Param("id") Long id);
}
