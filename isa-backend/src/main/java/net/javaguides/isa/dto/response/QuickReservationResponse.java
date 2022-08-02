package net.javaguides.isa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.javaguides.isa.utils.ReservationType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuickReservationResponse {
    private Long id;
    private LocalTime startTimeReservation;
    private LocalTime endTimeReservation;
    private LocalDate startDateReservation;
    private LocalDate endDateReservation;
    private int maxNumberOfPeople;
    private double price;
    private int expiresIn;
    private List<AdditionalServiceResponse> additionalServices;
    private ReservationType reservationType;
    private Long idOfType;
}
