package com.project.AgadgoanApplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.AgadgoanApplication.model.PrasadBooking;
import com.project.AgadgoanApplication.model.Devotee;

@Repository
public interface PrasadBookingRepository extends JpaRepository<PrasadBooking, Integer> {

    List<PrasadBooking> findByDevotee_Name(String name); // Valid since name is in Devotee

    @Query("SELECT COALESCE(SUM(p.noOfPeople), 0) FROM PrasadBooking p WHERE p.date = :date AND p.timeSlot = :timeSlot")
    int getTotalBooked(@Param("date") String date, @Param("timeSlot") String timeSlot);

    List<PrasadBooking> findByDevotee(Devotee devotee); // Preferred for user profile data
}
