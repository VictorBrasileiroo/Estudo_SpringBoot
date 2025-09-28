package com.braza.ecommerce_fkmodas.domain;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IProductMapper {

    ProductModel toModel(ProductDto productDto);
    ProductDto toDto(ProductModel productModel);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductDto productDto, @MappingTarget ProductModel productModel);
}
