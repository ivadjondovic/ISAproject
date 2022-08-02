package net.javaguides.isa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.javaguides.isa.utils.RegistrationType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProviderRegistrationRequest {

    private String username;
    private String password;
    private String rePassword;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String description;
    private RegistrationType registrationType;
}
