package bf.e_fixell_backoffice.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.Paiement} entity.
 */
public class PaiementDTO implements Serializable {
    
    private Long id;

    private String code;

    private Instant date;

    private BigDecimal montant;


    private Long commandeId;

    private Long venteId;

    private Long sessioncaisseId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }

    public Long getVenteId() {
        return venteId;
    }

    public void setVenteId(Long venteId) {
        this.venteId = venteId;
    }

    public Long getSessioncaisseId() {
        return sessioncaisseId;
    }

    public void setSessioncaisseId(Long sessionCaisseId) {
        this.sessioncaisseId = sessionCaisseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaiementDTO)) {
            return false;
        }

        return id != null && id.equals(((PaiementDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaiementDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", date='" + getDate() + "'" +
            ", montant=" + getMontant() +
            ", commandeId=" + getCommandeId() +
            ", venteId=" + getVenteId() +
            ", sessioncaisseId=" + getSessioncaisseId() +
            "}";
    }
}
