package bf.e_fixell_backoffice.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;

import bf.e_fixell_backoffice.domain.FicheTechnique;
import bf.e_fixell_backoffice.domain.PrixProduit;
import bf.e_fixell_backoffice.domain.enumeration.TypeTransaction;
import bf.e_fixell_backoffice.domain.enumeration.Etat;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.Transaction} entity.
 */
public class TransactionDTO implements Serializable {

    private Long id;

    private String code;

    private Instant date;

    private Integer quantite;

    private BigDecimal prixUnitaire;

    private TypeTransaction typeTransaction;

    private Etat etat;

    private String motif;


    private Long ficheTechniqueId;

    private Long produitId;

    private Long commandeId;

    private Long approvisionnementId;

    private Long livraisonId;

    private Long venteId;

    private FicheTechnique ficheTechnique;

    public PrixProduit getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(PrixProduit prixProduit) {
        this.prixProduit = prixProduit;
    }

    private PrixProduit prixProduit;



    public FicheTechnique getFicheTechnique() {
        return ficheTechnique;
    }

    public void setFicheTechnique(FicheTechnique ficheTechnique) {
        this.ficheTechnique = ficheTechnique;
    }

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

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public TypeTransaction getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(TypeTransaction typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Long getFicheTechniqueId() {
        return ficheTechniqueId;
    }

    public void setFicheTechniqueId(Long ficheTechniqueId) {
        this.ficheTechniqueId = ficheTechniqueId;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }

    public Long getApprovisionnementId() {
        return approvisionnementId;
    }

    public void setApprovisionnementId(Long approvisionnementId) {
        this.approvisionnementId = approvisionnementId;
    }

    public Long getLivraisonId() {
        return livraisonId;
    }

    public void setLivraisonId(Long livraisonId) {
        this.livraisonId = livraisonId;
    }

    public Long getVenteId() {
        return venteId;
    }

    public void setVenteId(Long venteId) {
        this.venteId = venteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransactionDTO)) {
            return false;
        }

        return id != null && id.equals(((TransactionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransactionDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", date='" + getDate() + "'" +
            ", quantite=" + getQuantite() +
            ", prixUnitaire=" + getPrixUnitaire() +
            ", typeTransaction='" + getTypeTransaction() + "'" +
            ", etat='" + getEtat() + "'" +
            ", motif='" + getMotif() + "'" +
            ", ficheTechniqueId=" + getFicheTechniqueId() +
            ", produitId=" + getProduitId() +
            ", commandeId=" + getCommandeId() +
            ", approvisionnementId=" + getApprovisionnementId() +
            ", livraisonId=" + getLivraisonId() +
            ", venteId=" + getVenteId() +
            "}";
    }
}
