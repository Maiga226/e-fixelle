package bf.e_fixell_backoffice.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.Vente} entity.
 */
public class VenteDTO implements Serializable {
    
    private Long id;

    private String libelle;

    private String code;

    private Instant date;

    private BigDecimal montant;

    private BigDecimal reste;

    private Boolean solder;


    private Long clientId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getReste() {
        return reste;
    }

    public void setReste(BigDecimal reste) {
        this.reste = reste;
    }

    public Boolean isSolder() {
        return solder;
    }

    public void setSolder(Boolean solder) {
        this.solder = solder;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VenteDTO)) {
            return false;
        }

        return id != null && id.equals(((VenteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VenteDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", code='" + getCode() + "'" +
            ", date='" + getDate() + "'" +
            ", montant=" + getMontant() +
            ", reste=" + getReste() +
            ", solder='" + isSolder() + "'" +
            ", clientId=" + getClientId() +
            "}";
    }
}
