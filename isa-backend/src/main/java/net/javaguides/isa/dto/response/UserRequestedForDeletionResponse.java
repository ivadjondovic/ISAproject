package net.javaguides.isa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class UserRequestedForDeletionResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String reasonForDeletion;
}
