
package com.cts.Flexride.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cts.Flexride.Entity.BookingEntity;
import com.cts.Flexride.Entity.CarEntity;
import com.cts.Flexride.Entity.UserEntity;
import com.cts.Flexride.Service.BookingService;
import com.cts.Flexride.Service.CarService;
import com.cts.Flexride.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarService carService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private UserService userService;

	// Show all cars in CarDetails page
	@GetMapping("/list")
	public String showCarDetailsPage(Model model, HttpSession session) {
		System.out.println("--> from list(model):" + model.getAttribute("errorMessage")); // This line prints the value
																							// of the "errorMessage"
																							// attribute from the model
																							// to the console. This is
																							// useful for debugging
																							// purposes.
		System.out.println("--> from list(session):" + session.getAttribute("errorMessage"));
		model.addAttribute("cars", carService.getAllCars());
		model.addAttribute("car", new CarEntity());
		return "Cardetails";// This line returns the view name "Cardetails", which will render the car
							// details page.
	}

	// Add or Update Car
	@PostMapping("/save")
	public String saveCar(@ModelAttribute("car") CarEntity car) { // It binds form data to a CarEntity object using the
																	// @ModelAttribute annotation.
		carService.saveCar(car);
		return "redirect:/car/list";
	}

	// ADDING

	@GetMapping("/delete/{id}")//return both model and view
	public String deleteCar(@PathVariable int id, RedirectAttributes redirectAttributes) {
		try {
			carService.deleteCar(id);
		} catch (RuntimeException e) // If a RuntimeException is thrown (e.g., if the car is booked and cannot be
										// deleted), this block catches the exception.
		{
			System.out.println(e.getMessage() + "<------------");
			redirectAttributes.addFlashAttribute("errorMessage", "Cannot delete this car as it is booked");
			return "redirect:/car/list";
		}
		return "redirect:/car/list";
	}

	// Edit Car (Load existing data into form)
	@GetMapping("/edit/{id}")
	public String editCar(@PathVariable int id, Model model) {
		CarEntity car = carService.getCarById(id);
		if (car != null) {
			model.addAttribute("car", car); // add the car to the model with the attribute name car
		} else {
			model.addAttribute("car", new CarEntity()); // If the car is not found, it adds a new CarEntity to the model
														// to Ensures form does not break
		}
		model.addAttribute("cars", carService.getAllCars());
		return "Cardetails"; // Loads the page with populated form data
	}

	@GetMapping("/booking")
	public String showAvailableCars(Model model, HttpSession session) {
		List<CarEntity> cars = carService.getAllCars();
		System.out.println(cars);
		model.addAttribute("carsList", cars);

		// Assuming user is stored in the session after login
		UserEntity user = (UserEntity) session.getAttribute("loggedInUser"); // This block retrieves the logged-in user
																				// from the session using the attribute
																				// name "loggedInUser".
		if (user != null) {
			model.addAttribute("users", user);
		}

		return "book"; // Renders book.html
	}

	// Book a car and stay on book.html with a popup
	@PostMapping("/book/{carId}")
	public String bookCar(@PathVariable int carId, HttpSession session, Model model) {
		Integer userId = (Integer) session.getAttribute("userId"); // This line retrieves the user ID from the session
																	// using the attribute name "userId".

		CarEntity car = carService.getCarById(carId);
		UserEntity user = userService.getUserById(userId);

		if (car != null && user != null) {
			System.out.println("Booking car for User ID: " + userId + ", Car ID: " + carId);
			bookingService.bookCar(user, car);
			model.addAttribute("bookingSuccess", "Your car has been booked successfully!");
		} else {
			model.addAttribute("bookingError", "Booking failed. Please try again.");
		}

		// Refresh car list
		model.addAttribute("carsList", carService.getAllCars());
		return "book"; // Stay on book.html
	}

	// Show the admin dashboard with bookings (Only for admin)
	@GetMapping("/dashboard")
	public String showDashboard(HttpSession session, Model model) {
		// Get logged-in user from session
		UserEntity user = (UserEntity) session.getAttribute("loggedInUser");

		// Only allow admin to see dashboard
		if (user == null || !"anushka@gmail.com".equalsIgnoreCase(user.getEmail())) {
			return "redirect:/car/booking"; // Redirect normal users to the booking page
		}

		List<BookingEntity> bookings = bookingService.getAllBookings();
		model.addAttribute("bookings", bookings);
		return "dashboard"; // Only admin can see this
	}
}
