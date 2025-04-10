package com.project.AgadgoanApplication.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.AgadgoanApplication.dao.PangatBookingRepository;
import com.project.AgadgoanApplication.model.Devotee;
import com.project.AgadgoanApplication.model.PangatBooking;

@Service
public class PangatServiceImpl implements PangatService {

    @Autowired
    private PangatBookingRepository repo;

    @Override
    public PangatBooking saveBooking(PangatBooking booking, Devotee devotee) {
        booking.setDevotee(devotee);
        return repo.save(booking);
    }

    @Override
    public List<PangatBooking> getAllBookings() {
        return repo.findAll();
    }

    @Override
    public PangatBooking updateBookingByDevoteeName(String name, PangatBooking booking) {
        List<PangatBooking> bookings = repo.findByDevotee_Name(name);
        if (!bookings.isEmpty()) {
            PangatBooking existing = bookings.get(0); // ⚠️ only updates first match
            booking.setId(existing.getId());
            booking.setDevotee(existing.getDevotee());
            return repo.save(booking);
        }
        return null;
    }

    @Override
    public void deleteBookingByDevoteeName(String name) {
        List<PangatBooking> bookings = repo.findByDevotee_Name(name);
        if (!bookings.isEmpty()) {
            repo.deleteById(bookings.get(0).getId()); // ⚠️ only deletes first match
        }
    }

    @Override
    public Map<String, Object> getSlotAvailability(String date, String timeSlot) {
        int totalSlots = 90;
        int booked = repo.countByDateAndTimeSlot(date, timeSlot);

        Map<String, Object> result = new HashMap<>();
        result.put("total", totalSlots);
        result.put("booked", booked);
        result.put("available", totalSlots - booked);

        return result;
    }

    @Override
    public List<PangatBooking> getBookingsByDevotee(Devotee devotee) {
        return repo.findByDevotee(devotee);
    }
    
   

	@Override
	public void deleteBookingById(int id) {
		if (repo.existsById(id)) {
            repo.deleteById(id);
        }
		
	}

}
