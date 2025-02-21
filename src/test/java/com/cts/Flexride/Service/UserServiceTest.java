

package com.cts.Flexride.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.cts.Flexride.Entity.UserEntity;
import com.cts.Flexride.Repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    private UserEntity user;

    @BeforeEach
    void setUp() {
        user = new UserEntity();
        user.setId(1);
        user.setFullName("Test User");
        user.setEmail("user@example.com");
        user.setPassword("password123");
        user.setPhnumber("9876543210");
        user.setAddress("123 Street, City");
    }

    @Test
    void testSaveUser() {
        when(userRepo.save(any(UserEntity.class))).thenReturn(user);

        UserEntity savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
        assertEquals("Test User", savedUser.getFullName());
        assertEquals("user@example.com", savedUser.getEmail());
        assertEquals("9876543210", savedUser.getPhnumber());
        assertEquals("123 Street, City", savedUser.getAddress());
    }

    @Test
    void testValidateUser_Success() {
        when(userRepo.findByEmail("user@example.com")).thenReturn(user);

        boolean isValid = userService.validateUser("user@example.com", "password123");

        assertTrue(isValid);
    }

    @Test
    void testValidateUser_Failure() {
        when(userRepo.findByEmail("user@example.com")).thenReturn(user);

        boolean isValid = userService.validateUser("user@example.com", "wrongpassword");

        assertFalse(isValid);
    }

    @Test
    void testValidateUser_NotFound() {
        when(userRepo.findByEmail("unknown@example.com")).thenReturn(null);

        boolean isValid = userService.validateUser("unknown@example.com", "password");

        assertFalse(isValid);
    }

    @Test
    void testGetUserId_Success() {
        when(userRepo.findByEmail("user@example.com")).thenReturn(user);

        Integer userId = userService.getUserId("user@example.com", "password123");

        assertNotNull(userId);
        assertEquals(1, userId);
    }

    @Test
    void testGetUserId_Failure() {
        when(userRepo.findByEmail("user@example.com")).thenReturn(null);

        Integer userId = userService.getUserId("user@example.com", "password123");

        assertNull(userId);
    }

    @Test
    void testGetUserById_Success() {
        when(userRepo.findById(1)).thenReturn(Optional.of(user));

        UserEntity foundUser = userService.getUserById(1);

        assertNotNull(foundUser);
        assertEquals("Test User", foundUser.getFullName());
    }

    @Test
    void testGetUserById_NotFound() {
        when(userRepo.findById(2)).thenReturn(Optional.empty());

        UserEntity foundUser = userService.getUserById(2);

        assertNull(foundUser);
    }
}