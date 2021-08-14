package bf.e_fixell_backoffice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import bf.e_fixell_backoffice.domain.enumeration.TypeTransport;

/**
 * A Transport.
 */
@Entity
@Table(name = "transport")
public class Transport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_transport")
    private TypeTransport typeTransport;

    @OneToMany(mappedBy = "transport")
    private Set<Livraison> livraisons = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "transports", allowSetters = true)
    private SocieteTransport societeTransport;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeTransport getTypeTransport() {
        return typeTransport;
    }

    public Transport typeTransport(TypeTransport typeTransport) {
        this.typeTransport = typeTransport;
        return this;
    }

    public void setTypeTransport(TypeTransport typeTransport) {
        this.typeTransport = typeTransport;
    }

    public Set<Livraison> getLivraisons() {
        return livraisons;
    }

    public Transport livraisons(Set<Livraison> livraisons) {
        this.livraisons = livraisons;
        return this;
    }

    public Transport addLivraison(Livraison livraison) {
        this.livraisons.add(livraison);
        livraison.setTransport(this);
        return this;
    }

    public Transport removeLivraison(Livraison livraison) {
        this.livraisons.remove(livraison);
        livraison.setTransport(null);
        return this;
    }

    public void setLivraisons(Set<Livraison> livraisons) {
        this.livraisons = livraisons;
    }

    public SocieteTransport getSocieteTransport() {
        return societeTransport;
    }

    public Transport societeTransport(SocieteTransport societeTransport) {
        this.societeTransport = societeTransport;
        return this;
    }

    public void setSocieteTransport(SocieteTransport societeTransport) {
        this.societeTransport = societeTransport;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transport)) {
            return false;
        }
        return id != null && id.equals(((Transport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transport{" +
            "id=" + getId() +
            ", typeTransport='" + getTypeTransport() + "'" +
            "}";
    }
}
