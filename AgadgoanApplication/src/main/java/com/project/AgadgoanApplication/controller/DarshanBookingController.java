package com.project.AgadgoanApplication.controller;

import com.project.AgadgoanApplication.model.DarshanBooking;
import com.project.AgadgoanApplication.model.Devotee;
import com.project.AgadgoanApplication.services.DarshanService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/darshan")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class DarshanBookingController {

    @Autowired
    private DarshanService service;

    // 🔐 Book a darshan (Logged-in users only)
    @PostMapping("/book")
    public ResponseEntity<?> book(@RequestBody DarshanBooking booking, HttpSession session) {
        Devotee currentUser = (Devotee) session.getAttribute("user");

        if (currentUser == null) {
            return ResponseEntity.status(401).body("Unauthorized: Please login to book darshan.");
        }

        DarshanBooking saved = service.saveBooking(booking, currentUser);
        return ResponseEntity.ok(saved);
    }

    // ✅ Get all bookings (Public)
    @GetMapping("/all")
    public ResponseEntity<List<DarshanBooking>> getAll() {
        return ResponseEntity.ok(service.getAllBookings());
    }

    // 🔐 Update booking by Devotee's name
    @PutMapping("/update/{name}")
    public ResponseEntity<?> update(@PathVariable String name, @RequestBody DarshanBooking booking, HttpSession session) {
        Devotee currentUser = (Devotee) session.getAttribute("user");

        if (currentUser == null) {
            return ResponseEntity.status(401).body("Unauthorized: Please login to update booking.");
        }

        DarshanBooking updated = service.updateBookingByDevoteeName(name, booking);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(404).body("Booking not found.");
        }
    }

    // 🔐 Delete booking by Devotee's name
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> delete(@PathVariable String name, HttpSession session) {
        Devotee currentUser = (Devotee) session.getAttribute("user");

        if (currentUser == null) {
            return ResponseEntity.status(401).body("Unauthorized: Please login to delete booking.");
        }

        service.deleteBookingByDevoteeName(name);
        return ResponseEntity.ok("Booking deleted successfully.");
    }

    // ✅ Check slot availability
    @GetMapping("/availability")
    public ResponseEntity<Map<String, Integer>> getSlotAvailability(
            @RequestParam String date,
            @RequestParam String timeSlot) {

        int total = 50;
        int booked = service.getTotalBookedSeats(date, timeSlot);

        Map<String, Integer> response = new HashMap<>();
        response.put("booked", booked);
        response.put("total", total);
        response.put("available", total - booked);

        return ResponseEntity.ok(response);
    }

    // Fetch logged-in user's bookings
    @GetMapping("/my-bookings")
    public ResponseEntity<?> getUserBookings(HttpSession session) {
        Devotee currentUser = (Devotee) session.getAttribute("user");

        if (currentUser == null) {
            return ResponseEntity.status(401).body("Unauthorized: Please login to view your bookings.");
        }

        return ResponseEntity.ok(service.getBookingsByDevotee(currentUser)); 
    }
}
