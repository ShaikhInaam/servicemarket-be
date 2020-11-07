package com.market.servicemarket.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    private String dateOfBirth;
    private String nicNumber;
    private String nicExpiryDate;
    private String city;
    private String country;
    private String lastLogin;
    private String email;

}
