package net.javaguides.isa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "room")
public class Room {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int beds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cottage_id")
    private Cottage cottage;
}
