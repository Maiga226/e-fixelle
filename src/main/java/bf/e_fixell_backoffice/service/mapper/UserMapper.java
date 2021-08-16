package bf.e_fixell_backoffice.service.mapper;

import bf.e_fixell_backoffice.domain.Authority;
import bf.e_fixell_backoffice.domain.User;
import bf.e_fixell_backoffice.service.dto.UserDTO;
import bf.e_fixell_backoffice.repository.ProfilRepository;
import bf.e_fixell_backoffice.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link User} and its DTO called {@link UserDTO}.
 *
 * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct
 * support is still in beta, and requires a manual step with an IDE.
 */
@Service
public class UserMapper {
    private final ProfilRepository profilRepository;
    private final AuthorityRepository authorityRepository;

    public UserMapper(ProfilRepository profilRepository,AuthorityRepository authorityRepository){
            this.profilRepository=profilRepository;
            this.authorityRepository=authorityRepository;
    }

    public List<UserDTO> usersToUserDTOs(List<User> users) {
        return users.stream()
            .filter(Objects::nonNull)
            .map(this::userToUserDTO)
            .collect(Collectors.toList());
    }

    public UserDTO userToUserDTO(User user) {
        return new UserDTO(user);
    }

    public List<User> userDTOsToUsers(List<UserDTO> userDTOs) {
        return userDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::userDTOToUser)
            .collect(Collectors.toList());
    }

    public User userDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        } else {
            User user = new User();
            user.setId(userDTO.getId());
            user.setLogin(userDTO.getLogin());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setImageUrl(userDTO.getImageUrl());
            user.setActivated(userDTO.isActivated());
            user.setLangKey(userDTO.getLangKey());
            user.setProfil(profilRepository.findOne(userDTO.getProfilId()));
            if(userDTO.getProfilId()!=null) {
                Set<Authority> profilAuthorities=profilRepository.findOne(userDTO.getProfilId()).getAuthorities();
                Set<Authority> authorities=new HashSet<>();
                profilAuthorities.forEach(item->{
                    authorities.add(authorityRepository.getOne(item.getName()));
                });
                user.setAuthorities(authorities);
            }
            return user;
        }
    }


    private Set<Authority> authoritiesFromStrings(Set<String> authoritiesAsString) {
        Set<Authority> authorities = new HashSet<>();

        if (authoritiesAsString != null) {
            authorities = authoritiesAsString.stream().map(string -> {
                Authority auth = new Authority();
                auth.setName(string);
                return auth;
            }).collect(Collectors.toSet());
        }

        return authorities;
    }

    public User userFromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
