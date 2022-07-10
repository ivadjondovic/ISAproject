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

    @OneToMany(mappedBy = "cottage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> room;

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
}
