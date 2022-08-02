package net.javaguides.isa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.javaguides.isa.utils.NavigationEquipment;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NavigationEquipmentRequest {
    private NavigationEquipment name;
}
