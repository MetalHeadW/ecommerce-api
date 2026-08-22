package com.eccomerce.user_api.controller;

import com.eccomerce.user_api.dto.UserDTO;
import com.eccomerce.user_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/search")
    public List<UserDTO> search(@RequestParam String nome) {
        return userService.queryByName(nome);
    }

    @GetMapping("/cpf/{cpf}")
    public UserDTO getByCpf(@PathVariable String cpf) {
        return userService.findByCpf(cpf);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO inserir(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @PutMapping("/{id}")
    public UserDTO editar(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO) {

        return userService.editUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        userService.delete(id);
    }
}