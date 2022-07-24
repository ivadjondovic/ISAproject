package net.javaguides.isa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.javaguides.isa.utils.BoatType;
import net.javaguides.isa.utils.CancellationReservationFee;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoatRequest {
    private String name;
    private BoatType type;
    private String length;
    private int engineNumber;
    private int enginePower;
    private String maxSpeed;
    private String address;
    private String description;
    private int capacity;
    private String rulesOfConduct;
    private String priceList;
    private String fishingEquipment;
    private CancellationReservationFee cancellationReservationFee;
    private Long boatOwnerId;
}
