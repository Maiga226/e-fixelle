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
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.Fournisseur} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.FournisseurResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /fournisseurs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FournisseurCriteria implements Serializable, Criteria {
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

    private StringFilter rue;

    private StringFilter telephone;

    private StringFilter fixe;

    private StringFilter codePostale;

    private StringFilter numeroReseauSocial;

    private TypePersonneFilter typePersonne;

    private LongFilter commandeId;

    private LongFilter approvisionnementId;

    private LongFilter livraisonId;

    private LongFilter adresseId;

    public FournisseurCriteria() {
    }

    public FournisseurCriteria(FournisseurCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nom = other.nom == null ? null : other.nom.copy();
        this.prenom = other.prenom == null ? null : other.prenom.copy();
        this.raisonSocial = other.raisonSocial == null ? null : other.raisonSocial.copy();
        this.rue = other.rue == null ? null : other.rue.copy();
        this.telephone = other.telephone == null ? null : other.telephone.copy();
        this.fixe = other.fixe == null ? null : other.fixe.copy();
        this.codePostale = other.codePostale == null ? null : other.codePostale.copy();
        this.numeroReseauSocial = other.numeroReseauSocial == null ? null : other.numeroReseauSocial.copy();
        this.typePersonne = other.typePersonne == null ? null : other.typePersonne.copy();
        this.commandeId = other.commandeId == null ? null : other.commandeId.copy();
        this.approvisionnementId = other.approvisionnementId == null ? null : other.approvisionnementId.copy();
        this.livraisonId = other.livraisonId == null ? null : other.livraisonId.copy();
        this.adresseId = other.adresseId == null ? null : other.adresseId.copy();
    }

    @Override
    public FournisseurCriteria copy() {
        return new FournisseurCriteria(this);
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

    public StringFilter getRue() {
        return rue;
    }

    public void setRue(StringFilter rue) {
        this.rue = rue;
    }

    public StringFilter getTelephone() {
        return telephone;
    }

    public void setTelephone(StringFilter telephone) {
        this.telephone = telephone;
    }

    public StringFilter getFixe() {
        return fixe;
    }

    public void setFixe(StringFilter fixe) {
        this.fixe = fixe;
    }

    public StringFilter getCodePostale() {
        return codePostale;
    }

    public void setCodePostale(StringFilter codePostale) {
        this.codePostale = codePostale;
    }

    public StringFilter getNumeroReseauSocial() {
        return numeroReseauSocial;
    }

    public void setNumeroReseauSocial(StringFilter numeroReseauSocial) {
        this.numeroReseauSocial = numeroReseauSocial;
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

    public LongFilter getApprovisionnementId() {
        return approvisionnementId;
    }

    public void setApprovisionnementId(LongFilter approvisionnementId) {
        this.approvisionnementId = approvisionnementId;
    }

    public LongFilter getLivraisonId() {
        return livraisonId;
    }

    public void setLivraisonId(LongFilter livraisonId) {
        this.livraisonId = livraisonId;
    }

    public LongFilter getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(LongFilter adresseId) {
        this.adresseId = adresseId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FournisseurCriteria that = (FournisseurCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nom, that.nom) &&
            Objects.equals(prenom, that.prenom) &&
            Objects.equals(raisonSocial, that.raisonSocial) &&
            Objects.equals(rue, that.rue) &&
            Objects.equals(telephone, that.telephone) &&
            Objects.equals(fixe, that.fixe) &&
            Objects.equals(codePostale, that.codePostale) &&
            Objects.equals(numeroReseauSocial, that.numeroReseauSocial) &&
            Objects.equals(typePersonne, that.typePersonne) &&
            Objects.equals(commandeId, that.commandeId) &&
            Objects.equals(approvisionnementId, that.approvisionnementId) &&
            Objects.equals(livraisonId, that.livraisonId) &&
            Objects.equals(adresseId, that.adresseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nom,
        prenom,
        raisonSocial,
        rue,
        telephone,
        fixe,
        codePostale,
        numeroReseauSocial,
        typePersonne,
        commandeId,
        approvisionnementId,
        livraisonId,
        adresseId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FournisseurCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nom != null ? "nom=" + nom + ", " : "") +
                (prenom != null ? "prenom=" + prenom + ", " : "") +
                (raisonSocial != null ? "raisonSocial=" + raisonSocial + ", " : "") +
                (rue != null ? "rue=" + rue + ", " : "") +
                (telephone != null ? "telephone=" + telephone + ", " : "") +
                (fixe != null ? "fixe=" + fixe + ", " : "") +
                (codePostale != null ? "codePostale=" + codePostale + ", " : "") +
                (numeroReseauSocial != null ? "numeroReseauSocial=" + numeroReseauSocial + ", " : "") +
                (typePersonne != null ? "typePersonne=" + typePersonne + ", " : "") +
                (commandeId != null ? "commandeId=" + commandeId + ", " : "") +
                (approvisionnementId != null ? "approvisionnementId=" + approvisionnementId + ", " : "") +
                (livraisonId != null ? "livraisonId=" + livraisonId + ", " : "") +
                (adresseId != null ? "adresseId=" + adresseId + ", " : "") +
            "}";
    }

}
