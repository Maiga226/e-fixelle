package bf.e_fixell_backoffice.service.dto;

import java.io.Serializable;
import bf.e_fixell_backoffice.domain.enumeration.TypeTransport;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.Transport} entity.
 */
public class TransportDTO implements Serializable {
    
    private Long id;

    private TypeTransport typeTransport;


    private Long societeTransportId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeTransport getTypeTransport() {
        return typeTransport;
    }

    public void setTypeTransport(TypeTransport typeTransport) {
        this.typeTransport = typeTransport;
    }

    public Long getSocieteTransportId() {
        return societeTransportId;
    }

    public void setSocieteTransportId(Long societeTransportId) {
        this.societeTransportId = societeTransportId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransportDTO)) {
            return false;
        }

        return id != null && id.equals(((TransportDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransportDTO{" +
            "id=" + getId() +
            ", typeTransport='" + getTypeTransport() + "'" +
            ", societeTransportId=" + getSocieteTransportId() +
            "}";
    }
}
