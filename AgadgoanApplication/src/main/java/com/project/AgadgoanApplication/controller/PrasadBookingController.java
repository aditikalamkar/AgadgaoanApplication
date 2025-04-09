package com.project.AgadgoanApplication.controller;

import com.project.AgadgoanApplication.model.Devotee;
import com.project.AgadgoanApplication.model.PrasadBooking;
import com.project.AgadgoanApplication.services.PrasadService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/prasad")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")

public class PrasadBookingController {

    @Autowired
    private PrasadService service;

    // 🔐 Book Prasad for logged-in user
    @PostMapping("/book")
    public ResponseEntity<?> book(@RequestBody PrasadBooking booking, HttpSession session) {
        Devotee user = (Devotee) session.getAttribute("user");

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Unauthorized: Please login to book prasad."));
        }

        PrasadBooking saved = service.saveBooking(booking, user);
        return ResponseEntity.ok(Map.of("message", "Booking successful", "data", saved));
    }

    // ✅ Get all bookings (public)
    @GetMapping("/all")
    public ResponseEntity<List<PrasadBooking>> getAll() {
        return ResponseEntity.ok(service.getAllBookings());
    }

    // 🔐 Update booking by Devotee name
    @PutMapping("/update/{name}")
    public ResponseEntity<?> update(@PathVariable String name, @RequestBody PrasadBooking booking, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized: Please login to update booking.");
        }

        PrasadBooking updated = service.updateBookingByDevoteeName(name, booking);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Booking not found for devotee name: " + name);
        }
    }

    // 🔐 Delete booking by Devotee name
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> delete(@PathVariable String name, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized: Please login to delete booking.");
        }

        service.deleteBookingByDevoteeName(name);
        return ResponseEntity.ok("Deleted booking for devotee name: " + name);
    }

    // ✅ Check slot availability
    @GetMapping("/availability")
    public ResponseEntity<Map<String, Integer>> getTotalBooked(
            @RequestParam String date,
            @RequestParam String timeSlot) {

        int total = 50;
        int booked = service.getTotalBookedSeats(date, timeSlot);

        Map<String, Integer> response = new HashMap<>();
        response.put("total", total);
        response.put("booked", booked);
        response.put("available", total - booked);

        return ResponseEntity.ok(response);
    }

    // 🔐 Get bookings for logged-in user
    @GetMapping("/my-bookings")
    public ResponseEntity<?> getUserBookings(HttpSession session) {
        Devotee user = (Devotee) session.getAttribute("user");

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized: Please login to view your prasad bookings.");
        }

        List<PrasadBooking> bookings = service.getBookingsByDevotee(user);
        return ResponseEntity.ok(bookings);
    }
}
