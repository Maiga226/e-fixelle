package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import bf.e_fixell_backoffice.domain.enumeration.TypeTransport;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.Transport} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.TransportResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /transports?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TransportCriteria implements Serializable, Criteria {
    /**
     * Class for filtering TypeTransport
     */
    public static class TypeTransportFilter extends Filter<TypeTransport> {

        public TypeTransportFilter() {
        }

        public TypeTransportFilter(TypeTransportFilter filter) {
            super(filter);
        }

        @Override
        public TypeTransportFilter copy() {
            return new TypeTransportFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private TypeTransportFilter typeTransport;

    private LongFilter livraisonId;

    private LongFilter societeTransportId;

    public TransportCriteria() {
    }

    public TransportCriteria(TransportCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.typeTransport = other.typeTransport == null ? null : other.typeTransport.copy();
        this.livraisonId = other.livraisonId == null ? null : other.livraisonId.copy();
        this.societeTransportId = other.societeTransportId == null ? null : other.societeTransportId.copy();
    }

    @Override
    public TransportCriteria copy() {
        return new TransportCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public TypeTransportFilter getTypeTransport() {
        return typeTransport;
    }

    public void setTypeTransport(TypeTransportFilter typeTransport) {
        this.typeTransport = typeTransport;
    }

    public LongFilter getLivraisonId() {
        return livraisonId;
    }

    public void setLivraisonId(LongFilter livraisonId) {
        this.livraisonId = livraisonId;
    }

    public LongFilter getSocieteTransportId() {
        return societeTransportId;
    }

    public void setSocieteTransportId(LongFilter societeTransportId) {
        this.societeTransportId = societeTransportId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TransportCriteria that = (TransportCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(typeTransport, that.typeTransport) &&
            Objects.equals(livraisonId, that.livraisonId) &&
            Objects.equals(societeTransportId, that.societeTransportId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        typeTransport,
        livraisonId,
        societeTransportId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransportCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (typeTransport != null ? "typeTransport=" + typeTransport + ", " : "") +
                (livraisonId != null ? "livraisonId=" + livraisonId + ", " : "") +
                (societeTransportId != null ? "societeTransportId=" + societeTransportId + ", " : "") +
            "}";
    }

}
