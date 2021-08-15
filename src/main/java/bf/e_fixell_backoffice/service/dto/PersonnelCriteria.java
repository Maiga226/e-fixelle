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
import io.github.jhipster.service.filter.BigDecimalFilter;

/**
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.Personnel} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.PersonnelResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /personnel?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PersonnelCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nom;

    private StringFilter prenom;

    private StringFilter telephone;

    private StringFilter matricule;

    private BigDecimalFilter salaire;

    private LongFilter historiqueAffectationId;

    public PersonnelCriteria() {
    }

    public PersonnelCriteria(PersonnelCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nom = other.nom == null ? null : other.nom.copy();
        this.prenom = other.prenom == null ? null : other.prenom.copy();
        this.telephone = other.telephone == null ? null : other.telephone.copy();
        this.matricule = other.matricule == null ? null : other.matricule.copy();
        this.salaire = other.salaire == null ? null : other.salaire.copy();
        this.historiqueAffectationId = other.historiqueAffectationId == null ? null : other.historiqueAffectationId.copy();
    }

    @Override
    public PersonnelCriteria copy() {
        return new PersonnelCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNom() {
        return nom;
    }

    public void setNom(StringFilter nom) {
        this.nom = nom;
    }

    public StringFilter getPrenom() {
        return prenom;
    }

    public void setPrenom(StringFilter prenom) {
        this.prenom = prenom;
    }

    public StringFilter getTelephone() {
        return telephone;
    }

    public void setTelephone(StringFilter telephone) {
        this.telephone = telephone;
    }

    public StringFilter getMatricule() {
        return matricule;
    }

    public void setMatricule(StringFilter matricule) {
        this.matricule = matricule;
    }

    public BigDecimalFilter getSalaire() {
        return salaire;
    }

    public void setSalaire(BigDecimalFilter salaire) {
        this.salaire = salaire;
    }

    public LongFilter getHistoriqueAffectationId() {
        return historiqueAffectationId;
    }

    public void setHistoriqueAffectationId(LongFilter historiqueAffectationId) {
        this.historiqueAffectationId = historiqueAffectationId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PersonnelCriteria that = (PersonnelCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nom, that.nom) &&
            Objects.equals(prenom, that.prenom) &&
            Objects.equals(telephone, that.telephone) &&
            Objects.equals(matricule, that.matricule) &&
            Objects.equals(salaire, that.salaire) &&
            Objects.equals(historiqueAffectationId, that.historiqueAffectationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nom,
        prenom,
        telephone,
        matricule,
        salaire,
        historiqueAffectationId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonnelCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nom != null ? "nom=" + nom + ", " : "") +
                (prenom != null ? "prenom=" + prenom + ", " : "") +
                (telephone != null ? "telephone=" + telephone + ", " : "") +
                (matricule != null ? "matricule=" + matricule + ", " : "") +
                (salaire != null ? "salaire=" + salaire + ", " : "") +
                (historiqueAffectationId != null ? "historiqueAffectationId=" + historiqueAffectationId + ", " : "") +
            "}";
    }

}
