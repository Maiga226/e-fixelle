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
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.Produit} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.ProduitResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /produits?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProduitCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter libelle;

    private IntegerFilter quantite;

    private StringFilter hsCode;

    private LongFilter prixProduitId;

    private LongFilter perteId;

    private LongFilter transactionId;

    private LongFilter categorieId;

    private LongFilter classificationId;

    public ProduitCriteria() {
    }

    public ProduitCriteria(ProduitCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.quantite = other.quantite == null ? null : other.quantite.copy();
        this.hsCode = other.hsCode == null ? null : other.hsCode.copy();
        this.prixProduitId = other.prixProduitId == null ? null : other.prixProduitId.copy();
        this.perteId = other.perteId == null ? null : other.perteId.copy();
        this.transactionId = other.transactionId == null ? null : other.transactionId.copy();
        this.categorieId = other.categorieId == null ? null : other.categorieId.copy();
        this.classificationId = other.classificationId == null ? null : other.classificationId.copy();
    }

    @Override
    public ProduitCriteria copy() {
        return new ProduitCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getLibelle() {
        return libelle;
    }

    public void setLibelle(StringFilter libelle) {
        this.libelle = libelle;
    }

    public IntegerFilter getQuantite() {
        return quantite;
    }

    public void setQuantite(IntegerFilter quantite) {
        this.quantite = quantite;
    }

    public StringFilter getHsCode() {
        return hsCode;
    }

    public void setHsCode(StringFilter hsCode) {
        this.hsCode = hsCode;
    }

    public LongFilter getPrixProduitId() {
        return prixProduitId;
    }

    public void setPrixProduitId(LongFilter prixProduitId) {
        this.prixProduitId = prixProduitId;
    }

    public LongFilter getPerteId() {
        return perteId;
    }

    public void setPerteId(LongFilter perteId) {
        this.perteId = perteId;
    }

    public LongFilter getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(LongFilter transactionId) {
        this.transactionId = transactionId;
    }

    public LongFilter getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(LongFilter categorieId) {
        this.categorieId = categorieId;
    }

    public LongFilter getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(LongFilter classificationId) {
        this.classificationId = classificationId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProduitCriteria that = (ProduitCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(quantite, that.quantite) &&
            Objects.equals(hsCode, that.hsCode) &&
            Objects.equals(prixProduitId, that.prixProduitId) &&
            Objects.equals(perteId, that.perteId) &&
            Objects.equals(transactionId, that.transactionId) &&
            Objects.equals(categorieId, that.categorieId) &&
            Objects.equals(classificationId, that.classificationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        libelle,
        quantite,
        hsCode,
        prixProduitId,
        perteId,
        transactionId,
        categorieId,
        classificationId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProduitCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (quantite != null ? "quantite=" + quantite + ", " : "") +
                (hsCode != null ? "hsCode=" + hsCode + ", " : "") +
                (prixProduitId != null ? "prixProduitId=" + prixProduitId + ", " : "") +
                (perteId != null ? "perteId=" + perteId + ", " : "") +
                (transactionId != null ? "transactionId=" + transactionId + ", " : "") +
                (categorieId != null ? "categorieId=" + categorieId + ", " : "") +
                (classificationId != null ? "classificationId=" + classificationId + ", " : "") +
            "}";
    }

}
