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
 * A Livreur.
 */
@Entity
@Table(name = "livreur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Livreur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "id_l", nullable = false, unique = true)
    private Integer idL;

    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    @Column(name = "nom_l", length = 50, nullable = false)
    private String nomL;

    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    @Column(name = "prenom_l", length = 50, nullable = false)
    private String prenomL;

    @NotNull
    @Pattern(regexp = "(+\\d+)?[0-9 ]+")
    @Column(name = "telephone_l", nullable = false)
    private String telephoneL;

    @OneToMany(mappedBy = "livreur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "client", "produit", "livreur" }, allowSetters = true)
    private Set<Commande> commandes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "livreurs" }, allowSetters = true)
    private Association association;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Livreur id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdL() {
        return this.idL;
    }

    public Livreur idL(Integer idL) {
        this.setIdL(idL);
        return this;
    }

    public void setIdL(Integer idL) {
        this.idL = idL;
    }

    public String getNomL() {
        return this.nomL;
    }

    public Livreur nomL(String nomL) {
        this.setNomL(nomL);
        return this;
    }

    public void setNomL(String nomL) {
        this.nomL = nomL;
    }

    public String getPrenomL() {
        return this.prenomL;
    }

    public Livreur prenomL(String prenomL) {
        this.setPrenomL(prenomL);
        return this;
    }

    public void setPrenomL(String prenomL) {
        this.prenomL = prenomL;
    }

    public String getTelephoneL() {
        return this.telephoneL;
    }

    public Livreur telephoneL(String telephoneL) {
        this.setTelephoneL(telephoneL);
        return this;
    }

    public void setTelephoneL(String telephoneL) {
        this.telephoneL = telephoneL;
    }

    public Set<Commande> getCommandes() {
        return this.commandes;
    }

    public void setCommandes(Set<Commande> commandes) {
        if (this.commandes != null) {
            this.commandes.forEach(i -> i.setLivreur(null));
        }
        if (commandes != null) {
            commandes.forEach(i -> i.setLivreur(this));
        }
        this.commandes = commandes;
    }

    public Livreur commandes(Set<Commande> commandes) {
        this.setCommandes(commandes);
        return this;
    }

    public Livreur addCommande(Commande commande) {
        this.commandes.add(commande);
        commande.setLivreur(this);
        return this;
    }

    public Livreur removeCommande(Commande commande) {
        this.commandes.remove(commande);
        commande.setLivreur(null);
        return this;
    }

    public Association getAssociation() {
        return this.association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }

    public Livreur association(Association association) {
        this.setAssociation(association);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Livreur)) {
            return false;
        }
        return id != null && id.equals(((Livreur) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Livreur{" +
            "id=" + getId() +
            ", idL=" + getIdL() +
            ", nomL='" + getNomL() + "'" +
            ", prenomL='" + getPrenomL() + "'" +
            ", telephoneL='" + getTelephoneL() + "'" +
            "}";
    }
}
