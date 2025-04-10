package com.project.AgadgoanApplication.services;

import java.util.List;
import java.util.Map;

import com.project.AgadgoanApplication.model.Devotee;
import com.project.AgadgoanApplication.model.PangatBooking;

public interface PangatService {
    PangatBooking saveBooking(PangatBooking booking, Devotee devotee);
    List<PangatBooking> getAllBookings();
    PangatBooking updateBookingByDevoteeName(String name, PangatBooking booking); // changed method name
    void deleteBookingByDevoteeName(String name); // changed method name
    Map<String, Object> getSlotAvailability(String date, String timeSlot);
    List<PangatBooking> getBookingsByDevotee(Devotee devotee); 
    void deleteBookingById(int id);

}
