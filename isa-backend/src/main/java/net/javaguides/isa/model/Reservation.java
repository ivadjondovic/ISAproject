package net.javaguides.isa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.isa.utils.ReservationType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time_quick_reservation")
    private LocalTime startTimeReservation;

    @Column(name = "end_time_quick_reservation")
    private LocalTime endTimeReservation;

    @Column(name = "start_date_quick_reservation")
    private LocalDate startDateReservation;

    @Column(name = "end_date_quick_reservation")
    private LocalDate endDateReservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cottage_id")
    private Cottage cottage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boat_id")
    private Boat boat;

    @Column(name = "reservation_type")
    private ReservationType ReservationType;

}
