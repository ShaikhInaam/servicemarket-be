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
public class RegistrationUser extends BaseRequest{
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date dateOfBirth;

    private String nicNumber;

    private Date nicExpiryDate;

    private String city;

    private String country;
//    @NotBlank
    private Timestamp lastLogin;
    @NotBlank
    private String email;

    private String status;
    @NotBlank
    private String name;

    private Date createdDate;

}
