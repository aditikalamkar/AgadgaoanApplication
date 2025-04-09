package com.project.AgadgoanApplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.AgadgoanApplication.model.PangatBooking;
import com.project.AgadgoanApplication.model.Devotee;

@Repository
public interface PangatBookingRepository extends JpaRepository<PangatBooking, Integer> {

    List<PangatBooking> findByDevotee_Name(String name); // Get bookings by devotee name

    int countByDateAndTimeSlot(String date, String timeSlot); // Adjusted to use existing fields

    List<PangatBooking> findByDevotee(Devotee devotee); // For dynamic profile view
}
