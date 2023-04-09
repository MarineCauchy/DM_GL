package cauchyma.coocpcycle.service.mapper;

import cauchyma.coocpcycle.domain.Association;
import cauchyma.coocpcycle.domain.Livreur;
import cauchyma.coocpcycle.service.dto.AssociationDTO;
import cauchyma.coocpcycle.service.dto.LivreurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Livreur} and its DTO {@link LivreurDTO}.
 */
@Mapper(componentModel = "spring")
public interface LivreurMapper extends EntityMapper<LivreurDTO, Livreur> {
    @Mapping(target = "association", source = "association", qualifiedByName = "associationId")
    LivreurDTO toDto(Livreur s);

    @Named("associationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AssociationDTO toDtoAssociationId(Association association);
}
