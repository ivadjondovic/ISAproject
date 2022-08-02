package net.javaguides.isa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cottage")
public class Cottage {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String description;

    private int numberOfRooms;

    private String numberOfBedsPerRoom;

    @OneToMany(mappedBy = "cottage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuickReservation> quickReservations;

    @OneToMany(mappedBy = "cottage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    private String rulesOfConduct;

    private String priceList;

    private String otherInformation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cottage_owner_id")
    private CottageOwner cottageOwner;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "subscribed_clients_cottages",
            joinColumns = @JoinColumn(name = "cottage_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"))
    private List<Client> clients;
}
