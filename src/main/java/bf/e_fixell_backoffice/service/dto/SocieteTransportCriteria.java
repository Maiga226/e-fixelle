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
 * Criteria class for the {@link bf.e_fixell_backoffice.domain.SocieteTransport} entity. This class is used
 * in {@link bf.e_fixell_backoffice.web.rest.SocieteTransportResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /societe-transports?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SocieteTransportCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelle;

    private StringFilter adresse;

    private StringFilter telephone;

    private StringFilter email;

    private LongFilter transportId;

    public SocieteTransportCriteria() {
    }

    public SocieteTransportCriteria(SocieteTransportCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.adresse = other.adresse == null ? null : other.adresse.copy();
        this.telephone = other.telephone == null ? null : other.telephone.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.transportId = other.transportId == null ? null : other.transportId.copy();
    }

    @Override
    public SocieteTransportCriteria copy() {
        return new SocieteTransportCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getLibelle() {
        return libelle;
    }

    public void setLibelle(StringFilter libelle) {
        this.libelle = libelle;
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

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
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
        final SocieteTransportCriteria that = (SocieteTransportCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(adresse, that.adresse) &&
            Objects.equals(telephone, that.telephone) &&
            Objects.equals(email, that.email) &&
            Objects.equals(transportId, that.transportId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        libelle,
        adresse,
        telephone,
        email,
        transportId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SocieteTransportCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (adresse != null ? "adresse=" + adresse + ", " : "") +
                (telephone != null ? "telephone=" + telephone + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (transportId != null ? "transportId=" + transportId + ", " : "") +
            "}";
    }

}
