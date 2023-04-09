package cauchyma.coocpcycle.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Commande.
 */
@Entity
@Table(name = "commande")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "id_co", nullable = false)
    private Integer idCo;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "prix_total", nullable = false)
    private Float prixTotal;

    @NotNull
    @Column(name = "date", nullable = false)
    private Instant date;

    @NotNull
    @Column(name = "adresse_livraison", nullable = false)
    private String adresseLivraison;

    @ManyToOne
    @JsonIgnoreProperties(value = { "produits", "commandes" }, allowSetters = true)
    private Client client;

    @JsonIgnoreProperties(value = { "commande", "client", "restaurant" }, allowSetters = true)
    @OneToOne(mappedBy = "commande")
    private Produit produit;

    @ManyToOne
    @JsonIgnoreProperties(value = { "commandes", "association" }, allowSetters = true)
    private Livreur livreur;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Commande id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdCo() {
        return this.idCo;
    }

    public Commande idCo(Integer idCo) {
        this.setIdCo(idCo);
        return this;
    }

    public void setIdCo(Integer idCo) {
        this.idCo = idCo;
    }

    public Float getPrixTotal() {
        return this.prixTotal;
    }

    public Commande prixTotal(Float prixTotal) {
        this.setPrixTotal(prixTotal);
        return this;
    }

    public void setPrixTotal(Float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Instant getDate() {
        return this.date;
    }

    public Commande date(Instant date) {
        this.setDate(date);
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getAdresseLivraison() {
        return this.adresseLivraison;
    }

    public Commande adresseLivraison(String adresseLivraison) {
        this.setAdresseLivraison(adresseLivraison);
        return this;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Commande client(Client client) {
        this.setClient(client);
        return this;
    }

    public Produit getProduit() {
        return this.produit;
    }

    public void setProduit(Produit produit) {
        if (this.produit != null) {
            this.produit.setCommande(null);
        }
        if (produit != null) {
            produit.setCommande(this);
        }
        this.produit = produit;
    }

    public Commande produit(Produit produit) {
        this.setProduit(produit);
        return this;
    }

    public Livreur getLivreur() {
        return this.livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    public Commande livreur(Livreur livreur) {
        this.setLivreur(livreur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Commande)) {
            return false;
        }
        return id != null && id.equals(((Commande) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Commande{" +
            "id=" + getId() +
            ", idCo=" + getIdCo() +
            ", prixTotal=" + getPrixTotal() +
            ", date='" + getDate() + "'" +
            ", adresseLivraison='" + getAdresseLivraison() + "'" +
            "}";
    }
}
