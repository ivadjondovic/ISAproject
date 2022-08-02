package net.javaguides.isa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.javaguides.isa.utils.ReservationType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuickReservationRequest {

    private LocalTime startTimeReservation;
    private LocalTime endTimeReservation;
    private LocalDate startDateReservation;
    private LocalDate endDateReservation;
    private int maxNumberOfPeople;
    private double price;
    private int expiresIn;
    private List<AdditionalServiceRequest> additionalServices;
    private ReservationType reservationType;
    private Long idOfType;
}
