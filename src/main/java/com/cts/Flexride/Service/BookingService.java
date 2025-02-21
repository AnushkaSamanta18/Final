

package com.cts.Flexride.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.Flexride.Entity.BookingEntity;
import com.cts.Flexride.Entity.UserEntity;
import com.cts.Flexride.Entity.CarEntity;
import com.cts.Flexride.Repo.BookingRepo;

@Service
public class BookingService {
private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private BookingRepo bookingRepo;
    
     public void bookCar(UserEntity user, CarEntity car) {
        if (user == null || car == null) {
            logger.error("Booking failed: User or Car is null");
            return;
        }
        logger.info("Booking car: {} for user: {}", car, user); //This line logs an informational message indicating that a car is being booked for a user.
        BookingEntity booking = new BookingEntity(user, car); //This line creates a new BookingEntity object using the provided user and car.
        bookingRepo.save(booking); // calls the save method of the bookingRepo repository to save the BookingEntity to the database.
    }


    public List<BookingEntity> getAllBookings() {
     logger.info("Fetching all bookings");
        return bookingRepo.findAll();
    }
    
    
}



