package cauchyma.coocpcycle.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link cauchyma.coocpcycle.domain.Client} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClientDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer idC;

    @NotNull
    @Size(max = 20)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String nomC;

    @NotNull
    @Size(max = 20)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String prenomC;

    @NotNull
    @Size(max = 100)
    private String adresseC;

    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "[a-zA-Z0-9.]+@[a-zA-Z0-9.]+.[a-z]+")
    private String emailC;

    @NotNull
    @Pattern(regexp = "(+\\d+)?[0-9 ]+")
    private String telephoneC;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdC() {
        return idC;
    }

    public void setIdC(Integer idC) {
        this.idC = idC;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public String getPrenomC() {
        return prenomC;
    }

    public void setPrenomC(String prenomC) {
        this.prenomC = prenomC;
    }

    public String getAdresseC() {
        return adresseC;
    }

    public void setAdresseC(String adresseC) {
        this.adresseC = adresseC;
    }

    public String getEmailC() {
        return emailC;
    }

    public void setEmailC(String emailC) {
        this.emailC = emailC;
    }

    public String getTelephoneC() {
        return telephoneC;
    }

    public void setTelephoneC(String telephoneC) {
        this.telephoneC = telephoneC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientDTO)) {
            return false;
        }

        ClientDTO clientDTO = (ClientDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, clientDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientDTO{" +
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
