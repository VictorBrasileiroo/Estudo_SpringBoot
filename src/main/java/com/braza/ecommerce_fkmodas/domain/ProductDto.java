package com.braza.ecommerce_fkmodas.domain;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotNull
    @NotEmpty
    @Size(min=2, max=256, message = "Nome deve conter entre 2 e 256 caracteres")
    private String name;

    @Size(max=512, message = "Descrição deve conter no máximo 512 caracteres")
    private String description;

    @NotNull
    @PositiveOrZero
    @NotEmpty
    private BigDecimal price;
}
