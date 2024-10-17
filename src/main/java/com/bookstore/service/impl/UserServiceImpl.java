package com.bookstore.service.impl;

import com.bookstore.dto.UserDTO;
import com.bookstore.entities.User;
import com.bookstore.exceptions.UserException;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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

    private User mapUserDTOtoUser(UserDTO userDTO) {
        return this.modelMapper.map(userDTO, User.class);
    }
}
