package com.braza.ecommerce_fkmodas.controller;

import com.braza.ecommerce_fkmodas.domain.UserDto;
import com.braza.ecommerce_fkmodas.domain.UserModel;
import com.braza.ecommerce_fkmodas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserModel> listarTodosUsuarios(){
        return userService.listarUsers();
    }

    @PostMapping
    public ResponseEntity<UserModel> adicionarNovoUsuario(@Valid @RequestBody UserDto userDto){
       var response = userService.adicionarNovoUser(userDto);
       return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserModel> removerUsuario(@PathVariable Long id){
        var response = userService.removerUser(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public  ResponseEntity<UserModel> atualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UserDto userDto
    ){
        var response = userService.atualizarUser(id, userDto);
        return ResponseEntity.ok(response);
    }
}
