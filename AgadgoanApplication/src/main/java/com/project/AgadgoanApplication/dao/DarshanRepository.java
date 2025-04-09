package com.project.AgadgoanApplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.AgadgoanApplication.model.DarshanBooking;
import com.project.AgadgoanApplication.model.Devotee;

@Repository
public interface DarshanRepository extends JpaRepository<DarshanBooking, Integer> {

    List<DarshanBooking> findByDevotee_Name(String name); // Via join with Devotee

    List<DarshanBooking> findByDateAndTimeSlot(String date, String timeSlot);

    List<DarshanBooking> findByDevotee(Devotee devotee); // Recommended for profile usage
}
