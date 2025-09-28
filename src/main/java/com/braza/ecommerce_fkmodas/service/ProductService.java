package com.braza.ecommerce_fkmodas.service;

import com.braza.ecommerce_fkmodas.domain.IProductMapper;
import com.braza.ecommerce_fkmodas.domain.IProductRepository;
import com.braza.ecommerce_fkmodas.domain.ProductDto;
import com.braza.ecommerce_fkmodas.domain.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private IProductRepository repo;

    @Autowired
    private IProductMapper mapper;

    public List<ProductModel> listAll(){
        return repo.findAll();
    }

    public ProductModel findByName(String name){
        return repo.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    public ProductModel addNewProduct(ProductDto productDto){
       return repo.save(mapper.toModel(productDto));
    }

    public ProductModel removeProduct(Long id){
        var removeProduct = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        repo.delete(removeProduct);
        return removeProduct;
    }

    public ProductModel updateProduct(Long id, ProductDto productDto) {
        var updateProduct = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        mapper.updateProductFromDto(productDto,updateProduct);
        return repo.save(updateProduct);
    }
}
