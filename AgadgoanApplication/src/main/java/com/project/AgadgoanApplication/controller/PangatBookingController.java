package com.project.AgadgoanApplication.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.AgadgoanApplication.model.Devotee;
import com.project.AgadgoanApplication.model.PangatBooking;
import com.project.AgadgoanApplication.services.PangatService;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/pangat")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class PangatBookingController {

    @Autowired
    private PangatService service;

    // 🔐 Create a new pangat booking (for logged-in users)
    @PostMapping("/book")
    public ResponseEntity<?> createBooking(@RequestBody PangatBooking booking, HttpSession session) {
        Devotee currentUser = (Devotee) session.getAttribute("user");

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Unauthorized: Please login to book Pangat."));
        }

        PangatBooking savedBooking = service.saveBooking(booking, currentUser);
        return ResponseEntity.ok(Map.of("message", "Booking successful", "data", savedBooking));
    }

    // ✅ Get all bookings (public)
    @GetMapping("/all")
    public ResponseEntity<List<PangatBooking>> getAllBookings() {
        return ResponseEntity.ok(service.getAllBookings());
    }

    // 🔐 Update booking by Devotee name
    @PutMapping("/update/{name}")
    public ResponseEntity<?> updateByName(@PathVariable String name, @RequestBody PangatBooking booking, HttpSession session) {
        Devotee currentUser = (Devotee) session.getAttribute("user");

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized: Please login to update Pangat booking.");
        }

        PangatBooking updated = service.updateBookingByDevoteeName(name, booking);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No booking found for update.");
        }
    }

    // 🔐 Delete booking by Devotee name
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteByName(@PathVariable String name, HttpSession session) {
        Devotee currentUser = (Devotee) session.getAttribute("user");

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized: Please login to delete Pangat booking.");
        }

        service.deleteBookingByDevoteeName(name);
        return ResponseEntity.ok("Booking deleted for devotee name: " + name);
    }

    // ✅ Check slot availability
    @GetMapping("/availability")
    public ResponseEntity<Map<String, Object>> getSlotAvailability(
            @RequestParam String date,
            @RequestParam String timeSlot) {

        return ResponseEntity.ok(service.getSlotAvailability(date, timeSlot));
    }

    // 🔐 Get bookings for the logged-in devotee
    @GetMapping("/my-bookings")
    public ResponseEntity<?> getUserBookings(HttpSession session) {
        Devotee currentUser = (Devotee) session.getAttribute("user");

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized: Please login to view your Pangat bookings.");
        }

        List<PangatBooking> bookings = service.getBookingsByDevotee(currentUser);
        return ResponseEntity.ok(bookings);
    }
}
