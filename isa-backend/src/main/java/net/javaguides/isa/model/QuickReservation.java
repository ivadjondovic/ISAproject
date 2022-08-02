package net.javaguides.isa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.isa.utils.ReservationType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "quick_reservation")
public class QuickReservation {

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

    @Column(name = "max_number_of_people")
    private int maxNumberOfPeople;

    private double price;

    private int expiresIn;

    @OneToMany(mappedBy = "quickReservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdditionalService> additionalServices;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cottage_id")
    private Cottage cottage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boat_id")
    private Boat boat;

    @Column(name = "reservation_type")
    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;

    private Long serviceId;
}
