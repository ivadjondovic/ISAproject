package net.javaguides.isa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.javaguides.isa.utils.NavigationEquipment;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NavigationEquipmentResponse {
    private Long id;
    private NavigationEquipment name;
}
