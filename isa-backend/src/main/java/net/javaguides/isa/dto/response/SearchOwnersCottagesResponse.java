package net.javaguides.isa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchOwnersCottagesResponse {
    private List<CottageResponse> cottages;
}
