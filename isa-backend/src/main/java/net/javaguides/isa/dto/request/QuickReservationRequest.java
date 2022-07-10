package net.javaguides.isa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.javaguides.isa.model.AdditionalService;
import net.javaguides.isa.utils.QuickReservationType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuickReservationRequest {

    private LocalTime startTimeExamination;

    private LocalTime endTimeExamination;

    private LocalDate startDateExamination;

    private LocalDate endDateExamination;

    private int maxNumberOfPeople;

    private double price;

    private int expiresIn;

    private List<AdditionalServiceRequest> additionalServices;

    private QuickReservationType quickReservationType;

    private Long idOfType;
}
