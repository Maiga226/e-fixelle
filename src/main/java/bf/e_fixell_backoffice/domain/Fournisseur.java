package bf.e_fixell_backoffice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import bf.e_fixell_backoffice.domain.enumeration.TypePersonne;

/**
 * A Fournisseur.
 */
@Entity
@Table(name = "fournisseur")
public class Fournisseur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "raison_social")
    private String raisonSocial;

    @Column(name = "rue")
    private String rue;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "fixe")
    private String fixe;

    @Column(name = "code_postale")
    private String codePostale;

    @Column(name = "numero_reseau_social")
    private String numeroReseauSocial;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_personne")
    private TypePersonne typePersonne;

    @OneToMany(mappedBy = "fournisseur")
    private Set<Commande> commandes = new HashSet<>();

    @OneToMany(mappedBy = "fournisseur")
    private Set<Approvisionnement> approvisionnements = new HashSet<>();

    @OneToMany(mappedBy = "fournisseur")
    private Set<Livraison> livraisons = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "fournisseurs", allowSetters = true)
    private UniteOrganisation adresse;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Fournisseur nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Fournisseur prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public Fournisseur raisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
        return this;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getRue() {
        return rue;
    }

    public Fournisseur rue(String rue) {
        this.rue = rue;
        return this;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getTelephone() {
        return telephone;
    }

    public Fournisseur telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFixe() {
        return fixe;
    }

    public Fournisseur fixe(String fixe) {
        this.fixe = fixe;
        return this;
    }

    public void setFixe(String fixe) {
        this.fixe = fixe;
    }

    public String getCodePostale() {
        return codePostale;
    }

    public Fournisseur codePostale(String codePostale) {
        this.codePostale = codePostale;
        return this;
    }

    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale;
    }

    public String getNumeroReseauSocial() {
        return numeroReseauSocial;
    }

    public Fournisseur numeroReseauSocial(String numeroReseauSocial) {
        this.numeroReseauSocial = numeroReseauSocial;
        return this;
    }

    public void setNumeroReseauSocial(String numeroReseauSocial) {
        this.numeroReseauSocial = numeroReseauSocial;
    }

    public TypePersonne getTypePersonne() {
        return typePersonne;
    }

    public Fournisseur typePersonne(TypePersonne typePersonne) {
        this.typePersonne = typePersonne;
        return this;
    }

    public void setTypePersonne(TypePersonne typePersonne) {
        this.typePersonne = typePersonne;
    }

    public Set<Commande> getCommandes() {
        return commandes;
    }

    public Fournisseur commandes(Set<Commande> commandes) {
        this.commandes = commandes;
        return this;
    }

    public Fournisseur addCommande(Commande commande) {
        this.commandes.add(commande);
        commande.setFournisseur(this);
        return this;
    }

    public Fournisseur removeCommande(Commande commande) {
        this.commandes.remove(commande);
        commande.setFournisseur(null);
        return this;
    }

    public void setCommandes(Set<Commande> commandes) {
        this.commandes = commandes;
    }

    public Set<Approvisionnement> getApprovisionnements() {
        return approvisionnements;
    }

    public Fournisseur approvisionnements(Set<Approvisionnement> approvisionnements) {
        this.approvisionnements = approvisionnements;
        return this;
    }

    public Fournisseur addApprovisionnement(Approvisionnement approvisionnement) {
        this.approvisionnements.add(approvisionnement);
        approvisionnement.setFournisseur(this);
        return this;
    }

    public Fournisseur removeApprovisionnement(Approvisionnement approvisionnement) {
        this.approvisionnements.remove(approvisionnement);
        approvisionnement.setFournisseur(null);
        return this;
    }

    public void setApprovisionnements(Set<Approvisionnement> approvisionnements) {
        this.approvisionnements = approvisionnements;
    }

    public Set<Livraison> getLivraisons() {
        return livraisons;
    }

    public Fournisseur livraisons(Set<Livraison> livraisons) {
        this.livraisons = livraisons;
        return this;
    }

    public Fournisseur addLivraison(Livraison livraison) {
        this.livraisons.add(livraison);
        livraison.setFournisseur(this);
        return this;
    }

    public Fournisseur removeLivraison(Livraison livraison) {
        this.livraisons.remove(livraison);
        livraison.setFournisseur(null);
        return this;
    }

    public void setLivraisons(Set<Livraison> livraisons) {
        this.livraisons = livraisons;
    }

    public UniteOrganisation getAdresse() {
        return adresse;
    }

    public Fournisseur adresse(UniteOrganisation uniteOrganisation) {
        this.adresse = uniteOrganisation;
        return this;
    }

    public void setAdresse(UniteOrganisation uniteOrganisation) {
        this.adresse = uniteOrganisation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fournisseur)) {
            return false;
        }
        return id != null && id.equals(((Fournisseur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Fournisseur{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", raisonSocial='" + getRaisonSocial() + "'" +
            ", rue='" + getRue() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", fixe='" + getFixe() + "'" +
            ", codePostale='" + getCodePostale() + "'" +
            ", numeroReseauSocial='" + getNumeroReseauSocial() + "'" +
            ", typePersonne='" + getTypePersonne() + "'" +
            "}";
    }
}
