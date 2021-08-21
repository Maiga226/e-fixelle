package bf.e_fixell_backoffice.service.dto;

import bf.e_fixell_backoffice.domain.Produit;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link bf.e_fixell_backoffice.domain.Produit} entity.
 */
public class ProduitDTO implements Serializable {

    private Long id;

    private String code;

    private String libelle;

    private Integer quantite;

    private String hsCode;


    private Long categorieId;

    private Long classificationId;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private boolean deleted;

    public ProduitDTO() {
    }

    public ProduitDTO(Produit produit) {
        this.id = produit.getId();
        this.code = produit.getCode();
        this.libelle = produit.getLibelle();
        this.quantite = produit.getQuantite();
        this.hsCode = produit.getHsCode();
        if (produit.getCategorie()!=null){
            this.categorieId = produit.getCategorie().getId();
        }
        if (produit.getClassification()!=null){
            this.classificationId = produit.getClassification().getId();
        }
        this.createdBy = produit.getCreatedBy();
        this.createdDate = produit.getCreatedDate();
        this.lastModifiedBy = produit.getLastModifiedBy();
        this.lastModifiedDate = produit.getLastModifiedDate();
        this.deleted = produit.getDeleted();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getHsCode() {
        return hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    public Long getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Long classificationId) {
        this.classificationId = classificationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProduitDTO)) {
            return false;
        }

        return id != null && id.equals(((ProduitDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProduitDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", quantite=" + getQuantite() +
            ", hsCode='" + getHsCode() + "'" +
            ", categorieId=" + getCategorieId() +
            ", classificationId=" + getClassificationId() +
            "}";
    }
}
