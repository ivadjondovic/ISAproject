package net.javaguides.isa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.isa.utils.QuickReservationType;

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
    private LocalTime startTimeExamination;

    @Column(name = "end_time_quick_reservation")
    private LocalTime endTimeExamination;

    @Column(name = "start_date_quick_reservation")
    private LocalDate startDateExamination;

    @Column(name = "end_date_quick_reservation")
    private LocalDate endDateExamination;

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

    @Column(name = "quick_reservation_type")
    private QuickReservationType quickReservationType;

}
