package cauchyma.coocpcycle.service.mapper;

import cauchyma.coocpcycle.domain.Client;
import cauchyma.coocpcycle.domain.Commande;
import cauchyma.coocpcycle.domain.Livreur;
import cauchyma.coocpcycle.service.dto.ClientDTO;
import cauchyma.coocpcycle.service.dto.CommandeDTO;
import cauchyma.coocpcycle.service.dto.LivreurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Commande} and its DTO {@link CommandeDTO}.
 */
@Mapper(componentModel = "spring")
public interface CommandeMapper extends EntityMapper<CommandeDTO, Commande> {
    @Mapping(target = "client", source = "client", qualifiedByName = "clientId")
    @Mapping(target = "livreur", source = "livreur", qualifiedByName = "livreurId")
    CommandeDTO toDto(Commande s);

    @Named("clientId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ClientDTO toDtoClientId(Client client);

    @Named("livreurId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    LivreurDTO toDtoLivreurId(Livreur livreur);
}
