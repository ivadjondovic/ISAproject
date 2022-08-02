package net.javaguides.isa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.javaguides.isa.utils.ReservationStatus;
import net.javaguides.isa.utils.ReservationType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    private Long id;
    private LocalTime startTimeReservation;
    private LocalTime endTimeReservation;
    private LocalDate startDateReservation;
    private LocalDate endDateReservation;
    private ReservationType reservationType;
    private ReservationStatus reservationStatus;
    private Long idOfType;
}
