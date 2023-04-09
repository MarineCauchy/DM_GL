package cauchyma.coocpcycle.service.mapper;

import cauchyma.coocpcycle.domain.Client;
import cauchyma.coocpcycle.domain.Commande;
import cauchyma.coocpcycle.domain.Produit;
import cauchyma.coocpcycle.domain.Restaurant;
import cauchyma.coocpcycle.service.dto.ClientDTO;
import cauchyma.coocpcycle.service.dto.CommandeDTO;
import cauchyma.coocpcycle.service.dto.ProduitDTO;
import cauchyma.coocpcycle.service.dto.RestaurantDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Produit} and its DTO {@link ProduitDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProduitMapper extends EntityMapper<ProduitDTO, Produit> {
    @Mapping(target = "commande", source = "commande", qualifiedByName = "commandeId")
    @Mapping(target = "client", source = "client", qualifiedByName = "clientId")
    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "restaurantId")
    ProduitDTO toDto(Produit s);

    @Named("commandeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CommandeDTO toDtoCommandeId(Commande commande);

    @Named("clientId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ClientDTO toDtoClientId(Client client);

    @Named("restaurantId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    RestaurantDTO toDtoRestaurantId(Restaurant restaurant);
}
