package com.bookstore.service;

import com.bookstore.dto.UserDTO;
import com.bookstore.entities.User;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Spy
    private ModelMapper modelMapper;

    public UserServiceTest() {
        this.userService = new UserServiceImpl();
    }


    @Test
    public void addUser(){
        User mockUser = new User(1L, "username", "password");
        UserDTO mockUserDTO = new UserDTO("username", "password");

        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        UserDTO userDTO = userService.addUser(mockUserDTO);

        assertEquals(mockUser.getUsername(), userDTO.getUsername());

    }
}
