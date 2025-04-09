package com.project.AgadgoanApplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pangat_Booking")
public class PangatBooking {

	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    private String date;
	    private String timeSlot;
	    private int noOfPeople;
	    private String message;

	    @ManyToOne
	    @JoinColumn(name = "devotee_id", nullable = false)
	    private Devotee devotee;
	    
	    public PangatBooking() {}

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

		public int getNoOfPeople() {
			return noOfPeople;
		}

		public void setNoOfPeople(int noOfPeople) {
			this.noOfPeople = noOfPeople;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Devotee getDevotee() {
			return devotee;
		}

		public void setDevotee(Devotee devotee) {
			this.devotee = devotee;
		}

		public PangatBooking(int id, String date, String timeSlot, int noOfPeople, String message, Devotee devotee) {
			super();
			this.id = id;
			this.date = date;
			this.timeSlot = timeSlot;
			this.noOfPeople = noOfPeople;
			this.message = message;
			this.devotee = devotee;
		}

		@Override
		public String toString() {
			return "PangatBooking [id=" + id + ", date=" + date + ", timeSlot=" + timeSlot + ", noOfPeople="
					+ noOfPeople + ", message=" + message + ", devotee=" + devotee + "]";
		}
	    
	    
}
