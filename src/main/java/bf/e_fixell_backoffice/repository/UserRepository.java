package bf.e_fixell_backoffice.repository;

import bf.e_fixell_backoffice.domain.User;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.time.Instant;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    String USERS_BY_LOGIN_CACHE = "usersByLogin";

    String USERS_BY_EMAIL_CACHE = "usersByEmail";

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByEmailIgnoreCase(String email);

    Optional<User> findOneByLogin(String login);

    Optional<User> findById(Long id);



    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesById(Long id);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
    Optional<User> findOneWithAuthoritiesByLogin(String login);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
    Optional<User> findOneWithAuthoritiesByEmailIgnoreCase(String email);

    Page<User> findAllByLoginNot(Pageable pageable, String login);

    @Query("select user from User user  where(" +
        "(:nom is null or :nom='' or UPPER(user.lastName) like upper('%'||:nom||'%'))" +
        "and (:prenom is null or :prenom='' or UPPER(user.firstName) like upper('%'||:prenom||'%') )" +
        "and (:login is null or :login=''  or LOWER(user.login) like LOWER('%'||:login||'%'))" +
        "and (:email is null or :email='' or UPPER(user.email) like UPPER('%'||:email||'%' ))" +
        "and (:profilId is null  or user.profil.id=:profilId)" +
        "and (user.deleted=false)" +
        ")")
    Page<User> findUserWithCriteria(
        Pageable pageable,
        @Param(value = "nom") String nom,
        @Param(value = "prenom") String prenom,
        @Param(value = "login") String login,
        @Param(value = "email") String email,
        @Param(value = "profilId") Long profilId
    );
    List<User> findAllByProfilId(Long id);

    @Modifying
    @Query("UPDATE User user SET  user.deleted= true, user.activated =false where user.id=:id")
    void deleteById(@Param("id") Long id);
}
