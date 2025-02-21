
package com.cts.Flexride.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.cts.Flexride.Entity.AdminEntity;
import com.cts.Flexride.Repo.AdminRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private AdminRepo adminRepo;

    @InjectMocks
    private AdminService adminService;

    private AdminEntity admin;

    @BeforeEach
    void setUp() {
        admin = new AdminEntity();
        admin.setId(1);
        admin.setName("AdminUser");
        admin.setPhnumber(9876543210L);
        admin.setEmail("admin@example.com");
        admin.setPassword("admin123");
    }

    @Test
    void testSaveAdmin() {
        when(adminRepo.save(any(AdminEntity.class))).thenReturn(admin);

        AdminEntity savedAdmin = adminService.saveAdmin(admin);

        assertNotNull(savedAdmin);
        assertEquals("AdminUser", savedAdmin.getName());
        assertEquals("admin@example.com", savedAdmin.getEmail());
        assertEquals(9876543210L, savedAdmin.getPhnumber());
    }

    @Test
    void testValidateAdmin_Success() {
        when(adminRepo.findByEmail("admin@example.com")).thenReturn(admin);

        AdminEntity validatedAdmin = adminService.validateAdmin("admin@example.com", "admin123");

        assertNotNull(validatedAdmin);
        assertEquals("AdminUser", validatedAdmin.getName());
        assertEquals("admin@example.com", validatedAdmin.getEmail());
    }

    @Test
    void testValidateAdmin_Failure() {
        when(adminRepo.findByEmail("admin@example.com")).thenReturn(admin);

        AdminEntity validatedAdmin = adminService.validateAdmin("admin@example.com", "wrongpassword");

        assertNull(validatedAdmin);
    }

    @Test
    void testValidateAdmin_NotFound() {
        when(adminRepo.findByEmail("unknown@example.com")).thenReturn(null);

        AdminEntity validatedAdmin = adminService.validateAdmin("unknown@example.com", "password");

        assertNull(validatedAdmin);
    }
}