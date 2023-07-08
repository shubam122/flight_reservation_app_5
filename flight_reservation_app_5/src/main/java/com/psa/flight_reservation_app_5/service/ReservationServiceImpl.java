package com.psa.flight_reservation_app_5.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.flight_reservation_app_5.dto.ReservationRequest;
import com.psa.flight_reservation_app_5.entity.Flight;
import com.psa.flight_reservation_app_5.entity.Passenger;
import com.psa.flight_reservation_app_5.entity.Reservation;
import com.psa.flight_reservation_app_5.repository.FlightRepository;
import com.psa.flight_reservation_app_5.repository.PassengerRepository;
import com.psa.flight_reservation_app_5.repository.ReservationRepository;
import com.psa.flight_reservation_app_5.utilities.EmailUtil;
import com.psa.flight_reservation_app_5.utilities.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private PassengerRepository passengerRepo;
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private PDFGenerator pdfGenerator;
	
	@Autowired
	private EmailUtil emailUtil;
	
	@Autowired
	private ReservationRepository reservationRepo;
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getFirstName());
		passenger.setLastName(request.getLastName());
		passenger.setMiddleName(request.getMiddleName());
		passenger.setEmail(request.getEmail());
		passenger.setPhone(request.getPhone());
		passengerRepo.save(passenger);
		
		long flightId = request.getFlightId();
		Optional<Flight> findById = flightRepo.findById(flightId);
		Flight flight = findById.get();
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(passenger);
		reservation.setCheckedIn(false);
		reservation.setNumberOfBags(0);
		String filePath = "C:\\Users\\Samsung\\Desktop\\stsFile\\flight_reservation_app_5\\tickets\\reservation"+reservation.getId()+".pdf";
		reservationRepo.save(reservation);
		
		pdfGenerator.generateItinerary(reservation, filePath); 
//		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		
		return reservation;
	}

}
