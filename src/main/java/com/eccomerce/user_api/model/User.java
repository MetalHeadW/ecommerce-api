package com.eccomerce.user_api.model;

import com.eccomerce.user_api.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String telefone;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;


    public static User convert(UserDTO userDTO) {
        User user = new User();

        user.setNome(userDTO.getNome());
        user.setEndereco(userDTO.getEndereco());
        user.setCpf(userDTO.getCpf());
        user.setEmail(userDTO.getEmail());
        user.setTelefone(userDTO.getTelefone());


        if (userDTO.getDataCadastro() != null) {
            user.setDataCadastro(userDTO.getDataCadastro());
        } else {
            user.setDataCadastro(LocalDateTime.now());
        }

        return user;
    }
}