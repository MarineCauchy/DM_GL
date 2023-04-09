package cauchyma.coocpcycle.service.mapper;

import cauchyma.coocpcycle.domain.Association;
import cauchyma.coocpcycle.service.dto.AssociationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Association} and its DTO {@link AssociationDTO}.
 */
@Mapper(componentModel = "spring")
public interface AssociationMapper extends EntityMapper<AssociationDTO, Association> {}
