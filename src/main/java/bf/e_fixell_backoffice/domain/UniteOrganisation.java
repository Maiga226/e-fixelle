package bf.e_fixell_backoffice.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import bf.e_fixell_backoffice.domain.enumeration.TypeUniteOrganisation;

/**
 * A UniteOrganisation.
 */
@Entity
@Table(name = "unite_organisation")
public class UniteOrganisation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeUniteOrganisation type;

    @OneToMany(mappedBy = "adresse")
    private Set<Fournisseur> fournisseurs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public UniteOrganisation libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public TypeUniteOrganisation getType() {
        return type;
    }

    public UniteOrganisation type(TypeUniteOrganisation type) {
        this.type = type;
        return this;
    }

    public void setType(TypeUniteOrganisation type) {
        this.type = type;
    }

    public Set<Fournisseur> getFournisseurs() {
        return fournisseurs;
    }

    public UniteOrganisation fournisseurs(Set<Fournisseur> fournisseurs) {
        this.fournisseurs = fournisseurs;
        return this;
    }

    public UniteOrganisation addFournisseur(Fournisseur fournisseur) {
        this.fournisseurs.add(fournisseur);
        fournisseur.setAdresse(this);
        return this;
    }

    public UniteOrganisation removeFournisseur(Fournisseur fournisseur) {
        this.fournisseurs.remove(fournisseur);
        fournisseur.setAdresse(null);
        return this;
    }

    public void setFournisseurs(Set<Fournisseur> fournisseurs) {
        this.fournisseurs = fournisseurs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UniteOrganisation)) {
            return false;
        }
        return id != null && id.equals(((UniteOrganisation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UniteOrganisation{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
