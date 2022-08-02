package net.javaguides.isa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.javaguides.isa.utils.ReservationStatus;
import net.javaguides.isa.utils.ReservationType;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {

    private LocalTime startTimeReservation;

    private LocalTime endTimeReservation;

    private LocalDate startDateReservation;

    private LocalDate endDateReservation;

    private ReservationType reservationType;

    private Long idOfType;

    private ReservationStatus reservationStatus;
}
