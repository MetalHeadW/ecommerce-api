package com.eccomerce.user_api.service;

import com.eccomerce.user_api.dto.UserDTO;
import com.eccomerce.user_api.model.User;
import com.eccomerce.user_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        return UserDTO.convert(user);
    }

    public UserDTO save(UserDTO userDTO) {
        User user = User.convert(userDTO);
        User savedUser = userRepository.save(user);

        return UserDTO.convert(savedUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);

        if (user == null) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        return UserDTO.convert(user);
    }

    public List<UserDTO> queryByName(String name) {
        return userRepository.queryByNomeLike(name)
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    public UserDTO editUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        user.setNome(userDTO.getNome());
        user.setCpf(userDTO.getCpf());
        user.setEndereco(userDTO.getEndereco());
        user.setEmail(userDTO.getEmail());
        user.setTelefone(userDTO.getTelefone());

        User updatedUser = userRepository.save(user);

        return UserDTO.convert(updatedUser);
    }
}