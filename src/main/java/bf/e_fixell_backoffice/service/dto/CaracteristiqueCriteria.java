package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.Caracteristique} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.CaracteristiqueResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /caracteristiques?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaracteristiqueCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelle;

    private StringFilter valeur;

    private LongFilter ficheTechniqueId;

    public CaracteristiqueCriteria() {
    }

    public CaracteristiqueCriteria(CaracteristiqueCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.valeur = other.valeur == null ? null : other.valeur.copy();
        this.ficheTechniqueId = other.ficheTechniqueId == null ? null : other.ficheTechniqueId.copy();
    }

    @Override
    public CaracteristiqueCriteria copy() {
        return new CaracteristiqueCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getLibelle() {
        return libelle;
    }

    public void setLibelle(StringFilter libelle) {
        this.libelle = libelle;
    }

    public StringFilter getValeur() {
        return valeur;
    }

    public void setValeur(StringFilter valeur) {
        this.valeur = valeur;
    }

    public LongFilter getFicheTechniqueId() {
        return ficheTechniqueId;
    }

    public void setFicheTechniqueId(LongFilter ficheTechniqueId) {
        this.ficheTechniqueId = ficheTechniqueId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CaracteristiqueCriteria that = (CaracteristiqueCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(valeur, that.valeur) &&
            Objects.equals(ficheTechniqueId, that.ficheTechniqueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        libelle,
        valeur,
        ficheTechniqueId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CaracteristiqueCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (valeur != null ? "valeur=" + valeur + ", " : "") +
                (ficheTechniqueId != null ? "ficheTechniqueId=" + ficheTechniqueId + ", " : "") +
            "}";
    }

}
