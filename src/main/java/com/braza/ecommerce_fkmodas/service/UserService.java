package com.braza.ecommerce_fkmodas.service;

import com.braza.ecommerce_fkmodas.domain.IUserMapper;
import com.braza.ecommerce_fkmodas.domain.IUserRepository;
import com.braza.ecommerce_fkmodas.domain.UserDto;
import com.braza.ecommerce_fkmodas.domain.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IUserMapper iUserMapper;

    public List<UserModel> listarUsers(){
        return iUserRepository.findAll();
    }

    public UserModel adicionarNovoUser(UserDto userDto){
        var newUser = iUserMapper.toEntity(userDto); //Ja que ta usando mapper ele faz a conversao automaticamente
        iUserRepository.save(newUser);
        return (newUser);
    }

    public UserModel removerUser(Long id){
        var user = iUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado"));
        iUserRepository.delete(user);
        return user;
    }

    public UserModel atualizarUser(Long id, UserDto userDto)
    {
        var userUpdate = iUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado"));

        iUserMapper.updateUserFromDto(userDto, userUpdate);

        iUserRepository.save(userUpdate);
        return userUpdate;
    }
}
