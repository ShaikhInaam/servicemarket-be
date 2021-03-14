package com.market.servicemarket.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.sql.Date;

@Getter
@Setter
public class UpdateRegisteredUserRequest extends BaseRequest{

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @Past
    private Date dateOfBirth;

    @NotBlank
    private String nicNumber;

    @Future
    private Date nicExpiryDate;

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    @NotBlank
    private String email;


}
