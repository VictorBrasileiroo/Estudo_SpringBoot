package com.braza.ecommerce_fkmodas.controller;

import com.braza.ecommerce_fkmodas.domain.ProductDto;
import com.braza.ecommerce_fkmodas.domain.ProductModel;
import com.braza.ecommerce_fkmodas.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/products/"))
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductModel>> listarTodosProduto(){
        var response = service.listAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{name}")
    public ResponseEntity<ProductModel> buscarProdutoPorNome(String name){
        var response = service.findByName(name);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductModel> adicionarNovoProduto(@RequestBody ProductDto dto){
        var response = service.addNewProduct(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProductModel> removerProduto(@PathVariable Long id){
        var response = service.removeProduct(id);
        return ResponseEntity.ok(response);
    }
}
