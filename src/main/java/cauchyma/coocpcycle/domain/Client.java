package cauchyma.coocpcycle.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "id_c", nullable = false, unique = true)
    private Integer idC;

    @NotNull
    @Size(max = 20)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    @Column(name = "nom_c", length = 20, nullable = false)
    private String nomC;

    @NotNull
    @Size(max = 20)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    @Column(name = "prenom_c", length = 20, nullable = false)
    private String prenomC;

    @NotNull
    @Size(max = 100)
    @Column(name = "adresse_c", length = 100, nullable = false)
    private String adresseC;

    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "[a-zA-Z0-9.]+@[a-zA-Z0-9.]+.[a-z]+")
    @Column(name = "email_c", length = 50, nullable = false)
    private String emailC;

    @NotNull
    @Pattern(regexp = "(+\\d+)?[0-9 ]+")
    @Column(name = "telephone_c", nullable = false)
    private String telephoneC;

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "commande", "client", "restaurant" }, allowSetters = true)
    private Set<Produit> produits = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "client", "produit", "livreur" }, allowSetters = true)
    private Set<Commande> commandes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Client id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdC() {
        return this.idC;
    }

    public Client idC(Integer idC) {
        this.setIdC(idC);
        return this;
    }

    public void setIdC(Integer idC) {
        this.idC = idC;
    }

    public String getNomC() {
        return this.nomC;
    }

    public Client nomC(String nomC) {
        this.setNomC(nomC);
        return this;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public String getPrenomC() {
        return this.prenomC;
    }

    public Client prenomC(String prenomC) {
        this.setPrenomC(prenomC);
        return this;
    }

    public void setPrenomC(String prenomC) {
        this.prenomC = prenomC;
    }

    public String getAdresseC() {
        return this.adresseC;
    }

    public Client adresseC(String adresseC) {
        this.setAdresseC(adresseC);
        return this;
    }

    public void setAdresseC(String adresseC) {
        this.adresseC = adresseC;
    }

    public String getEmailC() {
        return this.emailC;
    }

    public Client emailC(String emailC) {
        this.setEmailC(emailC);
        return this;
    }

    public void setEmailC(String emailC) {
        this.emailC = emailC;
    }

    public String getTelephoneC() {
        return this.telephoneC;
    }

    public Client telephoneC(String telephoneC) {
        this.setTelephoneC(telephoneC);
        return this;
    }

    public void setTelephoneC(String telephoneC) {
        this.telephoneC = telephoneC;
    }

    public Set<Produit> getProduits() {
        return this.produits;
    }

    public void setProduits(Set<Produit> produits) {
        if (this.produits != null) {
            this.produits.forEach(i -> i.setClient(null));
        }
        if (produits != null) {
            produits.forEach(i -> i.setClient(this));
        }
        this.produits = produits;
    }

    public Client produits(Set<Produit> produits) {
        this.setProduits(produits);
        return this;
    }

    public Client addProduit(Produit produit) {
        this.produits.add(produit);
        produit.setClient(this);
        return this;
    }

    public Client removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.setClient(null);
        return this;
    }

    public Set<Commande> getCommandes() {
        return this.commandes;
    }

    public void setCommandes(Set<Commande> commandes) {
        if (this.commandes != null) {
            this.commandes.forEach(i -> i.setClient(null));
        }
        if (commandes != null) {
            commandes.forEach(i -> i.setClient(this));
        }
        this.commandes = commandes;
    }

    public Client commandes(Set<Commande> commandes) {
        this.setCommandes(commandes);
        return this;
    }

    public Client addCommande(Commande commande) {
        this.commandes.add(commande);
        commande.setClient(this);
        return this;
    }

    public Client removeCommande(Commande commande) {
        this.commandes.remove(commande);
        commande.setClient(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", idC=" + getIdC() +
            ", nomC='" + getNomC() + "'" +
            ", prenomC='" + getPrenomC() + "'" +
            ", adresseC='" + getAdresseC() + "'" +
            ", emailC='" + getEmailC() + "'" +
            ", telephoneC='" + getTelephoneC() + "'" +
            "}";
    }
}
