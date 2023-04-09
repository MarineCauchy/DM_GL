package cauchyma.coocpcycle.service.mapper;

import cauchyma.coocpcycle.domain.Restaurant;
import cauchyma.coocpcycle.service.dto.RestaurantDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Restaurant} and its DTO {@link RestaurantDTO}.
 */
@Mapper(componentModel = "spring")
public interface RestaurantMapper extends EntityMapper<RestaurantDTO, Restaurant> {}
