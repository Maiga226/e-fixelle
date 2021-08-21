package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import java.time.Instant;

import bf.e_fixell_backoffice.domain.Client;
import bf.e_fixell_backoffice.domain.enumeration.TypePersonne;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.Client} entity.
 */
public class ClientDTO implements Serializable {

    private Long id;

    private String nom;

    private String prenom;

    private String raisonSocial;

    private String adresse;

    private String telephone;

    private String identifiant;

    private TypePersonne typePersonne;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private boolean deleted;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.nom = client.getNom();
        this.prenom = client.getPrenom();
        this.raisonSocial = client.getRaisonSocial();
        this.adresse = client.getAdresse();
        this.telephone = client.getTelephone();
        this.identifiant = client.getIdentifiant();
        this.typePersonne = client.getTypePersonne();
        this.createdBy = client.getCreatedBy();
        this.createdDate = client.getCreatedDate();
        this.lastModifiedBy = client.getLastModifiedBy();
        this.lastModifiedDate = client.getLastModifiedDate();
        this.deleted = client.getDeleted();
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public TypePersonne getTypePersonne() {
        return typePersonne;
    }

    public void setTypePersonne(TypePersonne typePersonne) {
        this.typePersonne = typePersonne;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientDTO)) {
            return false;
        }

        return id != null && id.equals(((ClientDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
            "id=" + id +
            ", nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            ", raisonSocial='" + raisonSocial + '\'' +
            ", adresse='" + adresse + '\'' +
            ", telephone='" + telephone + '\'' +
            ", identifiant='" + identifiant + '\'' +
            ", typePersonne=" + typePersonne +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", lastModifiedBy='" + lastModifiedBy + '\'' +
            ", lastModifiedDate=" + lastModifiedDate +
            ", deleted=" + deleted +
            '}';
    }
}
