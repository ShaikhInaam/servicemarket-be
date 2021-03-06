package com.market.servicemarket.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetails {

    private String dateOfBirth;
    private String nicNumber;
    private String nicExpiryDate;
    private String city;
    private String country;
    private String lastLogin;
    private String email;

}
