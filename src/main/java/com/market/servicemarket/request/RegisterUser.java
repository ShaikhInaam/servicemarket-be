package com.market.servicemarket.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

import static org.hibernate.type.descriptor.java.DateTypeDescriptor.DATE_FORMAT;

@Getter @Setter
public class RegisterUser extends BaseRequest{
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
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
