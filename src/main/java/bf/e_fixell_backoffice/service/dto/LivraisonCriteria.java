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
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.Livraison} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.LivraisonResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /livraisons?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class LivraisonCriteria implements Serializable, Criteria {
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

    private EtatFilter etat;

    private StringFilter motif;

    private LongFilter transactionId;

    private LongFilter fraisId;

    private LongFilter commandeId;

    private LongFilter fournisseurId;

    private LongFilter transportId;

    public LivraisonCriteria() {
    }

    public LivraisonCriteria(LivraisonCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.somme = other.somme == null ? null : other.somme.copy();
        this.etat = other.etat == null ? null : other.etat.copy();
        this.motif = other.motif == null ? null : other.motif.copy();
        this.transactionId = other.transactionId == null ? null : other.transactionId.copy();
        this.fraisId = other.fraisId == null ? null : other.fraisId.copy();
        this.commandeId = other.commandeId == null ? null : other.commandeId.copy();
        this.fournisseurId = other.fournisseurId == null ? null : other.fournisseurId.copy();
        this.transportId = other.transportId == null ? null : other.transportId.copy();
    }

    @Override
    public LivraisonCriteria copy() {
        return new LivraisonCriteria(this);
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

    public LongFilter getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(LongFilter transactionId) {
        this.transactionId = transactionId;
    }

    public LongFilter getFraisId() {
        return fraisId;
    }

    public void setFraisId(LongFilter fraisId) {
        this.fraisId = fraisId;
    }

    public LongFilter getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(LongFilter commandeId) {
        this.commandeId = commandeId;
    }

    public LongFilter getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(LongFilter fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    public LongFilter getTransportId() {
        return transportId;
    }

    public void setTransportId(LongFilter transportId) {
        this.transportId = transportId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LivraisonCriteria that = (LivraisonCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(date, that.date) &&
            Objects.equals(somme, that.somme) &&
            Objects.equals(etat, that.etat) &&
            Objects.equals(motif, that.motif) &&
            Objects.equals(transactionId, that.transactionId) &&
            Objects.equals(fraisId, that.fraisId) &&
            Objects.equals(commandeId, that.commandeId) &&
            Objects.equals(fournisseurId, that.fournisseurId) &&
            Objects.equals(transportId, that.transportId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        libelle,
        date,
        somme,
        etat,
        motif,
        transactionId,
        fraisId,
        commandeId,
        fournisseurId,
        transportId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LivraisonCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (somme != null ? "somme=" + somme + ", " : "") +
                (etat != null ? "etat=" + etat + ", " : "") +
                (motif != null ? "motif=" + motif + ", " : "") +
                (transactionId != null ? "transactionId=" + transactionId + ", " : "") +
                (fraisId != null ? "fraisId=" + fraisId + ", " : "") +
                (commandeId != null ? "commandeId=" + commandeId + ", " : "") +
                (fournisseurId != null ? "fournisseurId=" + fournisseurId + ", " : "") +
                (transportId != null ? "transportId=" + transportId + ", " : "") +
            "}";
    }

}
