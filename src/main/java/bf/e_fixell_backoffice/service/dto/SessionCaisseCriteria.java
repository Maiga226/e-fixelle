package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import bf.e_fixell_backoffice.domain.enumeration.Statut;
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
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.SessionCaisse} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.SessionCaisseResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /session-caisses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SessionCaisseCriteria implements Serializable, Criteria {
    /**
     * Class for filtering Statut
     */
    public static class StatutFilter extends Filter<Statut> {

        public StatutFilter() {
        }

        public StatutFilter(StatutFilter filter) {
            super(filter);
        }

        @Override
        public StatutFilter copy() {
            return new StatutFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter code;

    private StringFilter libelle;

    private InstantFilter dateDebut;

    private InstantFilter dateFin;

    private BigDecimalFilter sommeDebut;

    private BigDecimalFilter sommeFin;

    private BigDecimalFilter depassement;

    private BigDecimalFilter manquant;

    private StatutFilter statut;

    private LongFilter paiementId;

    private LongFilter depenseId;

    private LongFilter caisseId;

    public SessionCaisseCriteria() {
    }

    public SessionCaisseCriteria(SessionCaisseCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.dateDebut = other.dateDebut == null ? null : other.dateDebut.copy();
        this.dateFin = other.dateFin == null ? null : other.dateFin.copy();
        this.sommeDebut = other.sommeDebut == null ? null : other.sommeDebut.copy();
        this.sommeFin = other.sommeFin == null ? null : other.sommeFin.copy();
        this.depassement = other.depassement == null ? null : other.depassement.copy();
        this.manquant = other.manquant == null ? null : other.manquant.copy();
        this.statut = other.statut == null ? null : other.statut.copy();
        this.paiementId = other.paiementId == null ? null : other.paiementId.copy();
        this.depenseId = other.depenseId == null ? null : other.depenseId.copy();
        this.caisseId = other.caisseId == null ? null : other.caisseId.copy();
    }

    @Override
    public SessionCaisseCriteria copy() {
        return new SessionCaisseCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getCode() {
        return code;
    }

    public void setCode(LongFilter code) {
        this.code = code;
    }

    public StringFilter getLibelle() {
        return libelle;
    }

    public void setLibelle(StringFilter libelle) {
        this.libelle = libelle;
    }

    public InstantFilter getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(InstantFilter dateDebut) {
        this.dateDebut = dateDebut;
    }

    public InstantFilter getDateFin() {
        return dateFin;
    }

    public void setDateFin(InstantFilter dateFin) {
        this.dateFin = dateFin;
    }

    public BigDecimalFilter getSommeDebut() {
        return sommeDebut;
    }

    public void setSommeDebut(BigDecimalFilter sommeDebut) {
        this.sommeDebut = sommeDebut;
    }

    public BigDecimalFilter getSommeFin() {
        return sommeFin;
    }

    public void setSommeFin(BigDecimalFilter sommeFin) {
        this.sommeFin = sommeFin;
    }

    public BigDecimalFilter getDepassement() {
        return depassement;
    }

    public void setDepassement(BigDecimalFilter depassement) {
        this.depassement = depassement;
    }

    public BigDecimalFilter getManquant() {
        return manquant;
    }

    public void setManquant(BigDecimalFilter manquant) {
        this.manquant = manquant;
    }

    public StatutFilter getStatut() {
        return statut;
    }

    public void setStatut(StatutFilter statut) {
        this.statut = statut;
    }

    public LongFilter getPaiementId() {
        return paiementId;
    }

    public void setPaiementId(LongFilter paiementId) {
        this.paiementId = paiementId;
    }

    public LongFilter getDepenseId() {
        return depenseId;
    }

    public void setDepenseId(LongFilter depenseId) {
        this.depenseId = depenseId;
    }

    public LongFilter getCaisseId() {
        return caisseId;
    }

    public void setCaisseId(LongFilter caisseId) {
        this.caisseId = caisseId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SessionCaisseCriteria that = (SessionCaisseCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(dateDebut, that.dateDebut) &&
            Objects.equals(dateFin, that.dateFin) &&
            Objects.equals(sommeDebut, that.sommeDebut) &&
            Objects.equals(sommeFin, that.sommeFin) &&
            Objects.equals(depassement, that.depassement) &&
            Objects.equals(manquant, that.manquant) &&
            Objects.equals(statut, that.statut) &&
            Objects.equals(paiementId, that.paiementId) &&
            Objects.equals(depenseId, that.depenseId) &&
            Objects.equals(caisseId, that.caisseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        libelle,
        dateDebut,
        dateFin,
        sommeDebut,
        sommeFin,
        depassement,
        manquant,
        statut,
        paiementId,
        depenseId,
        caisseId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SessionCaisseCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (dateDebut != null ? "dateDebut=" + dateDebut + ", " : "") +
                (dateFin != null ? "dateFin=" + dateFin + ", " : "") +
                (sommeDebut != null ? "sommeDebut=" + sommeDebut + ", " : "") +
                (sommeFin != null ? "sommeFin=" + sommeFin + ", " : "") +
                (depassement != null ? "depassement=" + depassement + ", " : "") +
                (manquant != null ? "manquant=" + manquant + ", " : "") +
                (statut != null ? "statut=" + statut + ", " : "") +
                (paiementId != null ? "paiementId=" + paiementId + ", " : "") +
                (depenseId != null ? "depenseId=" + depenseId + ", " : "") +
                (caisseId != null ? "caisseId=" + caisseId + ", " : "") +
            "}";
    }

}
