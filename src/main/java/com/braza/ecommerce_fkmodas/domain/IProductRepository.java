package com.braza.ecommerce_fkmodas.domain;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<ProductModel, Long> {
        Optional<ProductModel> findByName(String name);
}
