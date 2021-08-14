package bf.e_fixell_backoffice.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TypeFrais.
 */
@Entity
@Table(name = "type_frais")
public class TypeFrais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "typeFrais")
    private Set<Frais> frais = new HashSet<>();

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

    public TypeFrais libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Frais> getFrais() {
        return frais;
    }

    public TypeFrais frais(Set<Frais> frais) {
        this.frais = frais;
        return this;
    }

    public TypeFrais addFrais(Frais frais) {
        this.frais.add(frais);
        frais.setTypeFrais(this);
        return this;
    }

    public TypeFrais removeFrais(Frais frais) {
        this.frais.remove(frais);
        frais.setTypeFrais(null);
        return this;
    }

    public void setFrais(Set<Frais> frais) {
        this.frais = frais;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeFrais)) {
            return false;
        }
        return id != null && id.equals(((TypeFrais) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeFrais{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
