package com.project.AgadgoanApplication.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "Darshan_Booking")
public class DarshanBooking {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;    
    
    private String date;
    private String timeSlot;
    private int numberOfPeople;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "devotee_id", nullable = false)
    @JsonIgnoreProperties("darshanBookings")
    private Devotee devotee;
    
    public DarshanBooking() {}

	public DarshanBooking(int id, String date, String timeSlot, int numberOfPeople, String notes, Devotee devotee) {
		super();
		this.id = id;
		this.date = date;
		this.timeSlot = timeSlot;
		this.numberOfPeople = numberOfPeople;
		this.notes = notes;
		this.devotee = devotee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Devotee getDevotee() {
		return devotee;
	}

	public void setDevotee(Devotee devotee) {
		this.devotee = devotee;
	}

	@Override
	public String toString() {
		return "DarshanBooking [id=" + id + ", date=" + date + ", timeSlot=" + timeSlot + ", numberOfPeople="
				+ numberOfPeople + ", notes=" + notes + ", devotee=" + devotee + "]";
	}
	
	  
    
    
}
