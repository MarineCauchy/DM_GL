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
 * A Association.
 */
@Entity
@Table(name = "association")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Association implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "id_a", nullable = false, unique = true)
    private Integer idA;

    @NotNull
    @Column(name = "nom_a", nullable = false)
    private String nomA;

    @OneToMany(mappedBy = "association")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "commandes", "association" }, allowSetters = true)
    private Set<Livreur> livreurs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Association id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdA() {
        return this.idA;
    }

    public Association idA(Integer idA) {
        this.setIdA(idA);
        return this;
    }

    public void setIdA(Integer idA) {
        this.idA = idA;
    }

    public String getNomA() {
        return this.nomA;
    }

    public Association nomA(String nomA) {
        this.setNomA(nomA);
        return this;
    }

    public void setNomA(String nomA) {
        this.nomA = nomA;
    }

    public Set<Livreur> getLivreurs() {
        return this.livreurs;
    }

    public void setLivreurs(Set<Livreur> livreurs) {
        if (this.livreurs != null) {
            this.livreurs.forEach(i -> i.setAssociation(null));
        }
        if (livreurs != null) {
            livreurs.forEach(i -> i.setAssociation(this));
        }
        this.livreurs = livreurs;
    }

    public Association livreurs(Set<Livreur> livreurs) {
        this.setLivreurs(livreurs);
        return this;
    }

    public Association addLivreur(Livreur livreur) {
        this.livreurs.add(livreur);
        livreur.setAssociation(this);
        return this;
    }

    public Association removeLivreur(Livreur livreur) {
        this.livreurs.remove(livreur);
        livreur.setAssociation(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Association)) {
            return false;
        }
        return id != null && id.equals(((Association) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Association{" +
            "id=" + getId() +
            ", idA=" + getIdA() +
            ", nomA='" + getNomA() + "'" +
            "}";
    }
}
