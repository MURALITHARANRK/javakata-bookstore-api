package com.bookstore.service.impl;

import com.bookstore.dto.UserDTO;
import com.bookstore.entities.User;
import com.bookstore.exceptions.UserException;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = mapUserDTOtoUser(userDTO);
        try {
            this.userRepository.save(user);
            userDTO.setPassword("");
            return userDTO;
        } catch (Exception e) {
            throw new UserException("Failed to add user into database");
        }
    }

    @Override
    public User findUserbyName(String username) {
        return this.userRepository.findByUsername(username).get();
    }

    @Override
    public boolean authenticate(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            if (!passwordEncoder.matches(password, optionalUser.get().getPassword())) {
                return true;
            } else {
                throw new UserException("Invalid username or password");
            }
        } else {
            throw new UserException("User not found: " + username);
        }
    }

    @Override
    public String generateTokenForUser(Object user) {
        return tokenService.generateToken((User)user);
    }

    private User mapUserDTOtoUser(UserDTO userDTO) {
        return this.modelMapper.map(userDTO, User.class);
    }
}
