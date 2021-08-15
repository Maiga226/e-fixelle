package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import bf.e_fixell_backoffice.domain.enumeration.TypeTransaction;
import bf.e_fixell_backoffice.domain.enumeration.Etat;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.Transaction} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.TransactionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /transactions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TransactionCriteria implements Serializable, Criteria {
    /**
     * Class for filtering TypeTransaction
     */
    public static class TypeTransactionFilter extends Filter<TypeTransaction> {

        public TypeTransactionFilter() {
        }

        public TypeTransactionFilter(TypeTransactionFilter filter) {
            super(filter);
        }

        @Override
        public TypeTransactionFilter copy() {
            return new TypeTransactionFilter(this);
        }

    }
    /**
     * Class for filtering Etat
     */
    public static class EtatFilter extends Filter<Etat> {

        public EtatFilter() {
        }

        public EtatFilter(EtatFilter filter) {
            super(filter);
        }

        @Override
        public EtatFilter copy() {
            return new EtatFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private InstantFilter date;

    private IntegerFilter quantite;

    private BigDecimalFilter prixUnitaire;

    private TypeTransactionFilter typeTransaction;

    private EtatFilter etat;

    private StringFilter motif;

    private LongFilter ficheTechniqueId;

    private LongFilter produitId;

    private LongFilter commandeId;

    private LongFilter approvisionnementId;

    private LongFilter livraisonId;

    private LongFilter venteId;

    public TransactionCriteria() {
    }

    public TransactionCriteria(TransactionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.quantite = other.quantite == null ? null : other.quantite.copy();
        this.prixUnitaire = other.prixUnitaire == null ? null : other.prixUnitaire.copy();
        this.typeTransaction = other.typeTransaction == null ? null : other.typeTransaction.copy();
        this.etat = other.etat == null ? null : other.etat.copy();
        this.motif = other.motif == null ? null : other.motif.copy();
        this.ficheTechniqueId = other.ficheTechniqueId == null ? null : other.ficheTechniqueId.copy();
        this.produitId = other.produitId == null ? null : other.produitId.copy();
        this.commandeId = other.commandeId == null ? null : other.commandeId.copy();
        this.approvisionnementId = other.approvisionnementId == null ? null : other.approvisionnementId.copy();
        this.livraisonId = other.livraisonId == null ? null : other.livraisonId.copy();
        this.venteId = other.venteId == null ? null : other.venteId.copy();
    }

    @Override
    public TransactionCriteria copy() {
        return new TransactionCriteria(this);
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

    public InstantFilter getDate() {
        return date;
    }

    public void setDate(InstantFilter date) {
        this.date = date;
    }

    public IntegerFilter getQuantite() {
        return quantite;
    }

    public void setQuantite(IntegerFilter quantite) {
        this.quantite = quantite;
    }

    public BigDecimalFilter getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimalFilter prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public TypeTransactionFilter getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(TypeTransactionFilter typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public EtatFilter getEtat() {
        return etat;
    }

    public void setEtat(EtatFilter etat) {
        this.etat = etat;
    }

    public StringFilter getMotif() {
        return motif;
    }

    public void setMotif(StringFilter motif) {
        this.motif = motif;
    }

    public LongFilter getFicheTechniqueId() {
        return ficheTechniqueId;
    }

    public void setFicheTechniqueId(LongFilter ficheTechniqueId) {
        this.ficheTechniqueId = ficheTechniqueId;
    }

    public LongFilter getProduitId() {
        return produitId;
    }

    public void setProduitId(LongFilter produitId) {
        this.produitId = produitId;
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
        final TransactionCriteria that = (TransactionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(date, that.date) &&
            Objects.equals(quantite, that.quantite) &&
            Objects.equals(prixUnitaire, that.prixUnitaire) &&
            Objects.equals(typeTransaction, that.typeTransaction) &&
            Objects.equals(etat, that.etat) &&
            Objects.equals(motif, that.motif) &&
            Objects.equals(ficheTechniqueId, that.ficheTechniqueId) &&
            Objects.equals(produitId, that.produitId) &&
            Objects.equals(commandeId, that.commandeId) &&
            Objects.equals(approvisionnementId, that.approvisionnementId) &&
            Objects.equals(livraisonId, that.livraisonId) &&
            Objects.equals(venteId, that.venteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        date,
        quantite,
        prixUnitaire,
        typeTransaction,
        etat,
        motif,
        ficheTechniqueId,
        produitId,
        commandeId,
        approvisionnementId,
        livraisonId,
        venteId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransactionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (quantite != null ? "quantite=" + quantite + ", " : "") +
                (prixUnitaire != null ? "prixUnitaire=" + prixUnitaire + ", " : "") +
                (typeTransaction != null ? "typeTransaction=" + typeTransaction + ", " : "") +
                (etat != null ? "etat=" + etat + ", " : "") +
                (motif != null ? "motif=" + motif + ", " : "") +
                (ficheTechniqueId != null ? "ficheTechniqueId=" + ficheTechniqueId + ", " : "") +
                (produitId != null ? "produitId=" + produitId + ", " : "") +
                (commandeId != null ? "commandeId=" + commandeId + ", " : "") +
                (approvisionnementId != null ? "approvisionnementId=" + approvisionnementId + ", " : "") +
                (livraisonId != null ? "livraisonId=" + livraisonId + ", " : "") +
                (venteId != null ? "venteId=" + venteId + ", " : "") +
            "}";
    }

}
