package com.codegym.springsecurity.bai15_springsecurity_jwt.service;

import com.codegym.springsecurity.bai15_springsecurity_jwt.model.dto.User;
import com.codegym.springsecurity.bai15_springsecurity_jwt.model.dto.UserDTO;
import com.codegym.springsecurity.bai15_springsecurity_jwt.model.dto.UserPrinciple;
import com.codegym.springsecurity.bai15_springsecurity_jwt.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService1 implements UserDetailsService {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<UserDTO> findAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User u : iUserRepository.findAll()) {
            userDTOS.add(toDTO(u));
        }
        return userDTOS;
    }

    public UserDTO findById(Long id) {
        Optional<User> user = iUserRepository.findById(id);
        return user.map(this::toDTO).orElse(null);
    }

    public User findByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }

    public boolean add(User user) {
        String passwordEncode = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncode);
        iUserRepository.save(user);
        return true;
    }

    public void delete(Long id) {
        iUserRepository.deleteById(id);
    }

    public UserDetails loadUserByUsername(String username) {
        User user = iUserRepository.findByUsername(username);
        if (user != null) {
            return UserPrinciple.build(user);
        }
        return null;
    }

    public UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getRoles());
    }
}
