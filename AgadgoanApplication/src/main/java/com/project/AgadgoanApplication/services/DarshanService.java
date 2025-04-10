package com.project.AgadgoanApplication.services;

import java.util.List;

import com.project.AgadgoanApplication.model.DarshanBooking;
import com.project.AgadgoanApplication.model.Devotee;

public interface DarshanService {
    DarshanBooking saveBooking(DarshanBooking booking, Devotee devotee);
    List<DarshanBooking> getAllBookings();
    DarshanBooking updateBookingByDevoteeName(String name, DarshanBooking booking);
    void deleteBookingByDevoteeName(String name);
    int getTotalBookedSeats(String date, String timeSlot);
    List<DarshanBooking> getBookingsByDevotee(Devotee devotee);
    void deleteBookingById(int id);

    
    
}
