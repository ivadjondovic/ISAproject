package net.javaguides.isa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.isa.utils.BoatType;
import net.javaguides.isa.utils.CancellationReservationFee;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "boat")
public class Boat {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private BoatType type;

    private String length;

    @Column(name = "engine_number")
    private int engineNumber;

    @Column(name = "engine_power")
    private int enginePower;

    @Column(name = "max_speed")
    private String maxSpeed;

    private String address;

    private String description;

    private int capacity;

    @Column(name = "rules_of_conduct")
    private String rulesOfConduct;

    @Column(name = "price_list")
    private String priceList;

    @Column(name = "fishing_equipment")
    private String fishingEquipment;

    @Column(name = "cancellation_reservation_fee")
    private CancellationReservationFee cancellationReservationFee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boat_owner_id")
    private BoatOwner boatOwner;

    @OneToMany(mappedBy = "boat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuickReservation> quickReservations = new ArrayList<>();

    @OneToMany(mappedBy = "boat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "subscribed_clients_boats",
            joinColumns = @JoinColumn(name = "boat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"))
    private List<Client> clients;

    @OneToMany(mappedBy = "boat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NavigationEquipment> navigationEquipments;
}
