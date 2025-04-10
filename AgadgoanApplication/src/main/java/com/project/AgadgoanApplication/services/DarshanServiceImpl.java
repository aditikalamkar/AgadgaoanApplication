package com.project.AgadgoanApplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.AgadgoanApplication.dao.DarshanRepository;
import com.project.AgadgoanApplication.model.DarshanBooking;
import com.project.AgadgoanApplication.model.Devotee;

@Service
public class DarshanServiceImpl implements DarshanService {

    @Autowired
    private DarshanRepository repo;

    @Override
    public DarshanBooking saveBooking(DarshanBooking booking, Devotee devotee) {
        booking.setDevotee(devotee);
        return repo.save(booking);
    }

    @Override
    public List<DarshanBooking> getAllBookings() {
        return repo.findAll();
    }

    @Override
    public DarshanBooking updateBookingByDevoteeName(String name, DarshanBooking booking) {
        List<DarshanBooking> bookings = repo.findByDevotee_Name(name);
        if (!bookings.isEmpty()) {
            DarshanBooking existing = bookings.get(0);
            booking.setId(existing.getId());
            booking.setDevotee(existing.getDevotee());
            return repo.save(booking);
        }
        return null;
    }

    @Override
    public void deleteBookingByDevoteeName(String name) {
        List<DarshanBooking> bookings = repo.findByDevotee_Name(name);
        if (!bookings.isEmpty()) {
            repo.deleteById(bookings.get(0).getId());
        }
    }

    @Override
    public int getTotalBookedSeats(String date, String timeSlot) {
        List<DarshanBooking> bookings = repo.findByDateAndTimeSlot(date, timeSlot);
        return bookings.stream().mapToInt(DarshanBooking::getNumberOfPeople).sum();
    }

    @Override
    public List<DarshanBooking> getBookingsByDevotee(Devotee devotee) {
        return repo.findByDevotee(devotee);
    }

    @Override
	public void deleteBookingById(int id) {
		if (repo.existsById(id)) {
            repo.deleteById(id);
        }
		
	}
    
    

}
