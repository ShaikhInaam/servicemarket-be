package com.market.servicemarket.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;

@Getter @Setter
public class RegisterUser extends BaseRequest{
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private Date dateOfBirth;

    private String nicNumber;

    private Date nicExpiryDate;

    private String city;

    private String country;

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    private Date createdDate;

}
