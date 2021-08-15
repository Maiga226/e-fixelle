package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
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
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.Commande} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.CommandeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /commandes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CommandeCriteria implements Serializable, Criteria {
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

    private StringFilter libelle;

    private InstantFilter date;

    private BigDecimalFilter somme;

    private InstantFilter dateLivraisonPrevu;

    private EtatFilter etat;

    private StringFilter motif;

    private BigDecimalFilter avance;

    private BooleanFilter avanceEnPercent;

    private LongFilter transactionId;

    private LongFilter livraisonId;

    private LongFilter paiementId;

    private LongFilter fraisId;

    private LongFilter fournisseurId;

    private LongFilter clientId;

    public CommandeCriteria() {
    }

    public CommandeCriteria(CommandeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.somme = other.somme == null ? null : other.somme.copy();
        this.dateLivraisonPrevu = other.dateLivraisonPrevu == null ? null : other.dateLivraisonPrevu.copy();
        this.etat = other.etat == null ? null : other.etat.copy();
        this.motif = other.motif == null ? null : other.motif.copy();
        this.avance = other.avance == null ? null : other.avance.copy();
        this.avanceEnPercent = other.avanceEnPercent == null ? null : other.avanceEnPercent.copy();
        this.transactionId = other.transactionId == null ? null : other.transactionId.copy();
        this.livraisonId = other.livraisonId == null ? null : other.livraisonId.copy();
        this.paiementId = other.paiementId == null ? null : other.paiementId.copy();
        this.fraisId = other.fraisId == null ? null : other.fraisId.copy();
        this.fournisseurId = other.fournisseurId == null ? null : other.fournisseurId.copy();
        this.clientId = other.clientId == null ? null : other.clientId.copy();
    }

    @Override
    public CommandeCriteria copy() {
        return new CommandeCriteria(this);
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

    public InstantFilter getDate() {
        return date;
    }

    public void setDate(InstantFilter date) {
        this.date = date;
    }

    public BigDecimalFilter getSomme() {
        return somme;
    }

    public void setSomme(BigDecimalFilter somme) {
        this.somme = somme;
    }

    public InstantFilter getDateLivraisonPrevu() {
        return dateLivraisonPrevu;
    }

    public void setDateLivraisonPrevu(InstantFilter dateLivraisonPrevu) {
        this.dateLivraisonPrevu = dateLivraisonPrevu;
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

    public BigDecimalFilter getAvance() {
        return avance;
    }

    public void setAvance(BigDecimalFilter avance) {
        this.avance = avance;
    }

    public BooleanFilter getAvanceEnPercent() {
        return avanceEnPercent;
    }

    public void setAvanceEnPercent(BooleanFilter avanceEnPercent) {
        this.avanceEnPercent = avanceEnPercent;
    }

    public LongFilter getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(LongFilter transactionId) {
        this.transactionId = transactionId;
    }

    public LongFilter getLivraisonId() {
        return livraisonId;
    }

    public void setLivraisonId(LongFilter livraisonId) {
        this.livraisonId = livraisonId;
    }

    public LongFilter getPaiementId() {
        return paiementId;
    }

    public void setPaiementId(LongFilter paiementId) {
        this.paiementId = paiementId;
    }

    public LongFilter getFraisId() {
        return fraisId;
    }

    public void setFraisId(LongFilter fraisId) {
        this.fraisId = fraisId;
    }

    public LongFilter getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(LongFilter fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    public LongFilter getClientId() {
        return clientId;
    }

    public void setClientId(LongFilter clientId) {
        this.clientId = clientId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CommandeCriteria that = (CommandeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(date, that.date) &&
            Objects.equals(somme, that.somme) &&
            Objects.equals(dateLivraisonPrevu, that.dateLivraisonPrevu) &&
            Objects.equals(etat, that.etat) &&
            Objects.equals(motif, that.motif) &&
            Objects.equals(avance, that.avance) &&
            Objects.equals(avanceEnPercent, that.avanceEnPercent) &&
            Objects.equals(transactionId, that.transactionId) &&
            Objects.equals(livraisonId, that.livraisonId) &&
            Objects.equals(paiementId, that.paiementId) &&
            Objects.equals(fraisId, that.fraisId) &&
            Objects.equals(fournisseurId, that.fournisseurId) &&
            Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        libelle,
        date,
        somme,
        dateLivraisonPrevu,
        etat,
        motif,
        avance,
        avanceEnPercent,
        transactionId,
        livraisonId,
        paiementId,
        fraisId,
        fournisseurId,
        clientId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommandeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (somme != null ? "somme=" + somme + ", " : "") +
                (dateLivraisonPrevu != null ? "dateLivraisonPrevu=" + dateLivraisonPrevu + ", " : "") +
                (etat != null ? "etat=" + etat + ", " : "") +
                (motif != null ? "motif=" + motif + ", " : "") +
                (avance != null ? "avance=" + avance + ", " : "") +
                (avanceEnPercent != null ? "avanceEnPercent=" + avanceEnPercent + ", " : "") +
                (transactionId != null ? "transactionId=" + transactionId + ", " : "") +
                (livraisonId != null ? "livraisonId=" + livraisonId + ", " : "") +
                (paiementId != null ? "paiementId=" + paiementId + ", " : "") +
                (fraisId != null ? "fraisId=" + fraisId + ", " : "") +
                (fournisseurId != null ? "fournisseurId=" + fournisseurId + ", " : "") +
                (clientId != null ? "clientId=" + clientId + ", " : "") +
            "}";
    }

}
