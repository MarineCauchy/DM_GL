package cauchyma.coocpcycle.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link cauchyma.coocpcycle.domain.Livreur} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LivreurDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer idL;

    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String nomL;

    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String prenomL;

    @NotNull
    @Pattern(regexp = "(+\\d+)?[0-9 ]+")
    private String telephoneL;

    private AssociationDTO association;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdL() {
        return idL;
    }

    public void setIdL(Integer idL) {
        this.idL = idL;
    }

    public String getNomL() {
        return nomL;
    }

    public void setNomL(String nomL) {
        this.nomL = nomL;
    }

    public String getPrenomL() {
        return prenomL;
    }

    public void setPrenomL(String prenomL) {
        this.prenomL = prenomL;
    }

    public String getTelephoneL() {
        return telephoneL;
    }

    public void setTelephoneL(String telephoneL) {
        this.telephoneL = telephoneL;
    }

    public AssociationDTO getAssociation() {
        return association;
    }

    public void setAssociation(AssociationDTO association) {
        this.association = association;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LivreurDTO)) {
            return false;
        }

        LivreurDTO livreurDTO = (LivreurDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, livreurDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LivreurDTO{" +
            "id=" + getId() +
            ", idL=" + getIdL() +
            ", nomL='" + getNomL() + "'" +
            ", prenomL='" + getPrenomL() + "'" +
            ", telephoneL='" + getTelephoneL() + "'" +
            ", association=" + getAssociation() +
            "}";
    }
}
