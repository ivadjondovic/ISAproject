package net.javaguides.isa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CottageResponse {
    private Long id;
    private String name;
    private String address;
    private String description;
    private String rulesOfConduct;
    private String priceList;
    private String otherInformation;
    private int numberOfRooms;
    private String numberOfBedsPerRoom;
}
