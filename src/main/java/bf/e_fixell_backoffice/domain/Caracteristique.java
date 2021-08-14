package bf.e_fixell_backoffice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Caracteristique.
 */
@Entity
@Table(name = "caracteristique")
public class Caracteristique implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "valeur")
    private String valeur;

    @ManyToOne
    @JsonIgnoreProperties(value = "caracteristiques", allowSetters = true)
    private FicheTechnique ficheTechnique;

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

    public Caracteristique libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getValeur() {
        return valeur;
    }

    public Caracteristique valeur(String valeur) {
        this.valeur = valeur;
        return this;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public FicheTechnique getFicheTechnique() {
        return ficheTechnique;
    }

    public Caracteristique ficheTechnique(FicheTechnique ficheTechnique) {
        this.ficheTechnique = ficheTechnique;
        return this;
    }

    public void setFicheTechnique(FicheTechnique ficheTechnique) {
        this.ficheTechnique = ficheTechnique;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Caracteristique)) {
            return false;
        }
        return id != null && id.equals(((Caracteristique) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Caracteristique{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", valeur='" + getValeur() + "'" +
            "}";
    }
}
