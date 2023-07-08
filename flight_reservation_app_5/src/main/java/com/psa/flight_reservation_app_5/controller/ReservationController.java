package com.psa.flight_reservation_app_5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.psa.flight_reservation_app_5.dto.ReservationRequest;
import com.psa.flight_reservation_app_5.entity.Reservation;
import com.psa.flight_reservation_app_5.service.ReservationService;
import com.psa.flight_reservation_app_5.utilities.PDFGenerator;

@Controller
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@RequestMapping("/completeReservation")
	public String completeReservation(ReservationRequest request, ModelMap modelMap) {
		Reservation reservationId = reservationService.bookFlight(request);
		modelMap.addAttribute("reservationId", reservationId.getId());
//		PDFGenerator pdf = new PDFGenerator();
//		pdf.generatepdf(filePath, name, emailId, phone, operatingAirlines, departureDate, departureCity, arrivalCity);
		return "confirmationreservation";
	}
	
	
	
	
}
