package net.javaguides.isa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class UserResponse {

    private Long id;
    private String username;
    private String token;
    private String userRole;
    private int tokenExpiresIn;
}
