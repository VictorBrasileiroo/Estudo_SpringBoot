package com.braza.ecommerce_fkmodas.domain;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IUserMapper
{
    //Funcao que Converte um Dto em uma entidade
    @Mapping(source = "name", target = "name")
    @Mapping(source = "phone", target = "phone")
    UserModel toEntity(UserDto userDto);

    //Funcao que Converte uma entidade em um Dto
    @Mapping(source = "name", target = "name")
    @Mapping(source = "phone", target = "phone")
    UserDto toDto(UserModel userModel);

    //Aqui so atualiza os campos que não são nulos no Dto
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "phone", target = "phone")
    void updateUserFromDto(UserDto userDto, @MappingTarget UserModel userModel);
}
