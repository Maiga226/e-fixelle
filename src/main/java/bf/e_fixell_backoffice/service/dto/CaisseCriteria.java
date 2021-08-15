package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import bf.e_fixell_backoffice.domain.enumeration.Statut;
import bf.e_fixell_backoffice.domain.enumeration.TypeCaisse;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;

/**
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.Caisse} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.CaisseResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /caisses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaisseCriteria implements Serializable, Criteria {
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
    /**
     * Class for filtering TypeCaisse
     */
    public static class TypeCaisseFilter extends Filter<TypeCaisse> {

        public TypeCaisseFilter() {
        }

        public TypeCaisseFilter(TypeCaisseFilter filter) {
            super(filter);
        }

        @Override
        public TypeCaisseFilter copy() {
            return new TypeCaisseFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter libelle;

    private BigDecimalFilter sommeMin;

    private BigDecimalFilter sommeMax;

    private BigDecimalFilter somme;

    private StatutFilter statut;

    private TypeCaisseFilter typeCaisse;

    private LongFilter sessionCaisseId;

    public CaisseCriteria() {
    }

    public CaisseCriteria(CaisseCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.sommeMin = other.sommeMin == null ? null : other.sommeMin.copy();
        this.sommeMax = other.sommeMax == null ? null : other.sommeMax.copy();
        this.somme = other.somme == null ? null : other.somme.copy();
        this.statut = other.statut == null ? null : other.statut.copy();
        this.typeCaisse = other.typeCaisse == null ? null : other.typeCaisse.copy();
        this.sessionCaisseId = other.sessionCaisseId == null ? null : other.sessionCaisseId.copy();
    }

    @Override
    public CaisseCriteria copy() {
        return new CaisseCriteria(this);
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

    public BigDecimalFilter getSommeMin() {
        return sommeMin;
    }

    public void setSommeMin(BigDecimalFilter sommeMin) {
        this.sommeMin = sommeMin;
    }

    public BigDecimalFilter getSommeMax() {
        return sommeMax;
    }

    public void setSommeMax(BigDecimalFilter sommeMax) {
        this.sommeMax = sommeMax;
    }

    public BigDecimalFilter getSomme() {
        return somme;
    }

    public void setSomme(BigDecimalFilter somme) {
        this.somme = somme;
    }

    public StatutFilter getStatut() {
        return statut;
    }

    public void setStatut(StatutFilter statut) {
        this.statut = statut;
    }

    public TypeCaisseFilter getTypeCaisse() {
        return typeCaisse;
    }

    public void setTypeCaisse(TypeCaisseFilter typeCaisse) {
        this.typeCaisse = typeCaisse;
    }

    public LongFilter getSessionCaisseId() {
        return sessionCaisseId;
    }

    public void setSessionCaisseId(LongFilter sessionCaisseId) {
        this.sessionCaisseId = sessionCaisseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CaisseCriteria that = (CaisseCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(sommeMin, that.sommeMin) &&
            Objects.equals(sommeMax, that.sommeMax) &&
            Objects.equals(somme, that.somme) &&
            Objects.equals(statut, that.statut) &&
            Objects.equals(typeCaisse, that.typeCaisse) &&
            Objects.equals(sessionCaisseId, that.sessionCaisseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        code,
        libelle,
        sommeMin,
        sommeMax,
        somme,
        statut,
        typeCaisse,
        sessionCaisseId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CaisseCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (sommeMin != null ? "sommeMin=" + sommeMin + ", " : "") +
                (sommeMax != null ? "sommeMax=" + sommeMax + ", " : "") +
                (somme != null ? "somme=" + somme + ", " : "") +
                (statut != null ? "statut=" + statut + ", " : "") +
                (typeCaisse != null ? "typeCaisse=" + typeCaisse + ", " : "") +
                (sessionCaisseId != null ? "sessionCaisseId=" + sessionCaisseId + ", " : "") +
            "}";
    }

}
