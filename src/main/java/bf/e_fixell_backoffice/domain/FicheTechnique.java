package bf.e_fixell_backoffice.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A FicheTechnique.
 */
@Entity
@Table(name = "fiche_technique")
public class FicheTechnique implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "ficheTechnique")
    private Set<Caracteristique> caracteristiques = new HashSet<>();

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

    public FicheTechnique libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Caracteristique> getCaracteristiques() {
        return caracteristiques;
    }

    public FicheTechnique caracteristiques(Set<Caracteristique> caracteristiques) {
        this.caracteristiques = caracteristiques;
        return this;
    }

    public FicheTechnique addCaracteristique(Caracteristique caracteristique) {
        this.caracteristiques.add(caracteristique);
        caracteristique.setFicheTechnique(this);
        return this;
    }

    public FicheTechnique removeCaracteristique(Caracteristique caracteristique) {
        this.caracteristiques.remove(caracteristique);
        caracteristique.setFicheTechnique(null);
        return this;
    }

    public void setCaracteristiques(Set<Caracteristique> caracteristiques) {
        this.caracteristiques = caracteristiques;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FicheTechnique)) {
            return false;
        }
        return id != null && id.equals(((FicheTechnique) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FicheTechnique{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
