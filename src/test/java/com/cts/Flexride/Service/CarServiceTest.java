

package com.cts.Flexride.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.cts.Flexride.Entity.CarEntity;
import com.cts.Flexride.Repo.BookingRepo;
import com.cts.Flexride.Repo.CarRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepo carRepo;

    @Mock
    private BookingRepo bookingRepo;

    @InjectMocks
    private CarService carService;

    private CarEntity car;

    @BeforeEach
    void setUp() {
        car = new CarEntity();
        car.setId(1);
        car.setName("Tesla Model S");
        car.setModel("Model S");
        car.setCompany("Tesla");
        car.setPricePerDay(1500.0);
    }

    @Test
    void testSaveCar() {
        // Act
        carService.saveCar(car);

        // Assert
        verify(carRepo, times(1)).save(car);
    }

    @Test
    void testGetAllCars() {
        // Arrange
        List<CarEntity> cars = Arrays.asList(car, new CarEntity());
        when(carRepo.findAll()).thenReturn(cars);

        // Act
        List<CarEntity> result = carService.getAllCars();

        // Assert
        assertEquals(2, result.size());
        verify(carRepo, times(1)).findAll();
    }

    @Test
    void testGetCarById_CarExists() {
        // Arrange
        when(carRepo.findById(1)).thenReturn(Optional.of(car));

        // Act
        CarEntity result = carService.getCarById(1);

        // Assert
        assertNotNull(result);
        assertEquals("Tesla Model S", result.getName());
        verify(carRepo, times(1)).findById(1);
    }

    @Test
    void testGetCarById_CarNotFound() {
        // Arrange
        when(carRepo.findById(2)).thenReturn(Optional.empty());

        // Act
        CarEntity result = carService.getCarById(2);

        // Assert
        assertNull(result);
        verify(carRepo, times(1)).findById(2);
    }

    @Test
    void testDeleteCar_SuccessfulDeletion() {
        // Arrange
        when(carRepo.findById(1)).thenReturn(Optional.of(car));
        when(bookingRepo.findByCar(car)).thenReturn(List.of()); // No active bookings

        // Act
        carService.deleteCar(1);

        // Assert
        verify(carRepo, times(1)).delete(car);
    }

    @Test
    void testDeleteCar_CarNotFound() {
        // Arrange
        when(carRepo.findById(2)).thenReturn(Optional.empty());

        // Act & Assert
        assertDoesNotThrow(() -> carService.deleteCar(2));
        verify(carRepo, never()).delete(any());
    }

}