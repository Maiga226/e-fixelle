package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import bf.e_fixell_backoffice.domain.enumeration.TypePersonne;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.Fournisseur} entity.
 */
public class FournisseurDTO implements Serializable {
    
    private Long id;

    private String nom;

    private String prenom;

    private String raisonSocial;

    private String rue;

    private String telephone;

    private String fixe;

    private String codePostale;

    private String numeroReseauSocial;

    private TypePersonne typePersonne;


    private Long adresseId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFixe() {
        return fixe;
    }

    public void setFixe(String fixe) {
        this.fixe = fixe;
    }

    public String getCodePostale() {
        return codePostale;
    }

    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale;
    }

    public String getNumeroReseauSocial() {
        return numeroReseauSocial;
    }

    public void setNumeroReseauSocial(String numeroReseauSocial) {
        this.numeroReseauSocial = numeroReseauSocial;
    }

    public TypePersonne getTypePersonne() {
        return typePersonne;
    }

    public void setTypePersonne(TypePersonne typePersonne) {
        this.typePersonne = typePersonne;
    }

    public Long getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Long uniteOrganisationId) {
        this.adresseId = uniteOrganisationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FournisseurDTO)) {
            return false;
        }

        return id != null && id.equals(((FournisseurDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FournisseurDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", raisonSocial='" + getRaisonSocial() + "'" +
            ", rue='" + getRue() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", fixe='" + getFixe() + "'" +
            ", codePostale='" + getCodePostale() + "'" +
            ", numeroReseauSocial='" + getNumeroReseauSocial() + "'" +
            ", typePersonne='" + getTypePersonne() + "'" +
            ", adresseId=" + getAdresseId() +
            "}";
    }
}
