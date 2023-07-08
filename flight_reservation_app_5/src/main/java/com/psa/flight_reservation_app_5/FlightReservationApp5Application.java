package com.psa.flight_reservation_app_5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class FlightReservationApp5Application {

	public static void main(String[] args) {
		SpringApplication.run(FlightReservationApp5Application.class, args);
	}

}
