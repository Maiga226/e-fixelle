package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import bf.e_fixell_backoffice.domain.enumeration.TypePersonne;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.Client} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.ClientResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /clients?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ClientCriteria implements Serializable, Criteria {
    /**
     * Class for filtering TypePersonne
     */
    public static class TypePersonneFilter extends Filter<TypePersonne> {

        public TypePersonneFilter() {
        }

        public TypePersonneFilter(TypePersonneFilter filter) {
            super(filter);
        }

        @Override
        public TypePersonneFilter copy() {
            return new TypePersonneFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nom;

    private StringFilter prenom;

    private StringFilter raisonSocial;

    private StringFilter adresse;

    private StringFilter telephone;

    private StringFilter identifiant;

    private TypePersonneFilter typePersonne;

    private LongFilter commandeId;

    private LongFilter venteId;

    public ClientCriteria() {
    }

    public ClientCriteria(ClientCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nom = other.nom == null ? null : other.nom.copy();
        this.prenom = other.prenom == null ? null : other.prenom.copy();
        this.raisonSocial = other.raisonSocial == null ? null : other.raisonSocial.copy();
        this.adresse = other.adresse == null ? null : other.adresse.copy();
        this.telephone = other.telephone == null ? null : other.telephone.copy();
        this.identifiant = other.identifiant == null ? null : other.identifiant.copy();
        this.typePersonne = other.typePersonne == null ? null : other.typePersonne.copy();
        this.commandeId = other.commandeId == null ? null : other.commandeId.copy();
        this.venteId = other.venteId == null ? null : other.venteId.copy();
    }

    @Override
    public ClientCriteria copy() {
        return new ClientCriteria(this);
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

    public StringFilter getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(StringFilter raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public StringFilter getAdresse() {
        return adresse;
    }

    public void setAdresse(StringFilter adresse) {
        this.adresse = adresse;
    }

    public StringFilter getTelephone() {
        return telephone;
    }

    public void setTelephone(StringFilter telephone) {
        this.telephone = telephone;
    }

    public StringFilter getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(StringFilter identifiant) {
        this.identifiant = identifiant;
    }

    public TypePersonneFilter getTypePersonne() {
        return typePersonne;
    }

    public void setTypePersonne(TypePersonneFilter typePersonne) {
        this.typePersonne = typePersonne;
    }

    public LongFilter getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(LongFilter commandeId) {
        this.commandeId = commandeId;
    }

    public LongFilter getVenteId() {
        return venteId;
    }

    public void setVenteId(LongFilter venteId) {
        this.venteId = venteId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ClientCriteria that = (ClientCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nom, that.nom) &&
            Objects.equals(prenom, that.prenom) &&
            Objects.equals(raisonSocial, that.raisonSocial) &&
            Objects.equals(adresse, that.adresse) &&
            Objects.equals(telephone, that.telephone) &&
            Objects.equals(identifiant, that.identifiant) &&
            Objects.equals(typePersonne, that.typePersonne) &&
            Objects.equals(commandeId, that.commandeId) &&
            Objects.equals(venteId, that.venteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nom,
        prenom,
        raisonSocial,
        adresse,
        telephone,
        identifiant,
        typePersonne,
        commandeId,
        venteId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nom != null ? "nom=" + nom + ", " : "") +
                (prenom != null ? "prenom=" + prenom + ", " : "") +
                (raisonSocial != null ? "raisonSocial=" + raisonSocial + ", " : "") +
                (adresse != null ? "adresse=" + adresse + ", " : "") +
                (telephone != null ? "telephone=" + telephone + ", " : "") +
                (identifiant != null ? "identifiant=" + identifiant + ", " : "") +
                (typePersonne != null ? "typePersonne=" + typePersonne + ", " : "") +
                (commandeId != null ? "commandeId=" + commandeId + ", " : "") +
                (venteId != null ? "venteId=" + venteId + ", " : "") +
            "}";
    }

}
