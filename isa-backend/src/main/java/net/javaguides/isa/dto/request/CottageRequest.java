package net.javaguides.isa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CottageRequest {
    private String name;
    private String address;
    private String description;
    private String rulesOfConduct;
    private String priceList;
    private String otherInformation;
    private int numberOfRooms;
    private String numberOfBedsPerRoom;
    private Long cottageOwnerId;
}
