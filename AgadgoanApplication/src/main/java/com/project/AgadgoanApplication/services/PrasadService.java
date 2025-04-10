package com.project.AgadgoanApplication.services;

import java.util.List;

import com.project.AgadgoanApplication.model.PrasadBooking;
import com.project.AgadgoanApplication.model.Devotee;

public interface PrasadService {
    PrasadBooking saveBooking(PrasadBooking booking, Devotee devotee);
    List<PrasadBooking> getAllBookings();
    PrasadBooking updateBookingByDevoteeName(String name, PrasadBooking booking); // changed
    void deleteBookingByDevoteeName(String name); // changed
    int getTotalBookedSeats(String date, String timeSlot);
    List<PrasadBooking> getBookingsByDevotee(Devotee devotee); 
    void deleteBookingById(int id);

}
