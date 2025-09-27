package com.braza.ecommerce_fkmodas.domain;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IUserMapper
{
    UserModel toEntity(UserDto userDto);
    UserDto toDto(UserModel userModel);

    //Aqui so atualiza os campos que não são nulos no Dto
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserDto userDto, @MappingTarget UserModel userModel);
}
