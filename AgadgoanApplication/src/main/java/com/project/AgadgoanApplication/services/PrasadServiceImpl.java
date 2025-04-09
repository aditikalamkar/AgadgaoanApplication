package com.project.AgadgoanApplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.AgadgoanApplication.dao.PrasadBookingRepository;
import com.project.AgadgoanApplication.model.PrasadBooking;
import com.project.AgadgoanApplication.model.Devotee;

@Service
public class PrasadServiceImpl implements PrasadService {

    @Autowired
    private PrasadBookingRepository repo;

    @Override
    public PrasadBooking saveBooking(PrasadBooking booking, Devotee devotee) {
        booking.setDevotee(devotee);
        return repo.save(booking);
    }

    @Override
    public List<PrasadBooking> getAllBookings() {
        return repo.findAll();
    }

    @Override
    public PrasadBooking updateBookingByDevoteeName(String name, PrasadBooking booking) {
        List<PrasadBooking> bookings = repo.findByDevotee_Name(name);
        if (!bookings.isEmpty()) {
            PrasadBooking existing = bookings.get(0);
            booking.setId(existing.getId());
            booking.setDevotee(existing.getDevotee());
            return repo.save(booking);
        }
        return null;
    }

    @Override
    public void deleteBookingByDevoteeName(String name) {
        List<PrasadBooking> bookings = repo.findByDevotee_Name(name);
        if (!bookings.isEmpty()) {
            repo.deleteById(bookings.get(0).getId());
        }
    }

    @Override
    public int getTotalBookedSeats(String date, String timeSlot) {
        return repo.getTotalBooked(date, timeSlot);
    }

    @Override
    public List<PrasadBooking> getBookingsByDevotee(Devotee devotee) {
        return repo.findByDevotee(devotee);
    }
}
