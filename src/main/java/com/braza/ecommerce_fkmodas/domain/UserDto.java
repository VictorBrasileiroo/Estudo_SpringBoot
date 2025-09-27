package com.braza.ecommerce_fkmodas.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "O nome não pode ser vazio")
    private String name;
    private String phone;
}
