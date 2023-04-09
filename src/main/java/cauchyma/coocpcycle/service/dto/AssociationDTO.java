package cauchyma.coocpcycle.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link cauchyma.coocpcycle.domain.Association} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AssociationDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer idA;

    @NotNull
    private String nomA;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdA() {
        return idA;
    }

    public void setIdA(Integer idA) {
        this.idA = idA;
    }

    public String getNomA() {
        return nomA;
    }

    public void setNomA(String nomA) {
        this.nomA = nomA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AssociationDTO)) {
            return false;
        }

        AssociationDTO associationDTO = (AssociationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, associationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AssociationDTO{" +
            "id=" + getId() +
            ", idA=" + getIdA() +
            ", nomA='" + getNomA() + "'" +
            "}";
    }
}
