package com.market.servicemarket.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_details")
public class UserDetailsEntity implements Serializable {

    @Id
    private Integer id;

    private String username;

    private Date dateOfBirth;

    private String nicNumber;

    private Date nicExpiryDate;

    private String latitude;

    private String longitude;

    private String city;

    private String country;

    private Timestamp lastLogin;

    private String email;

    private Date createdDate;


}
