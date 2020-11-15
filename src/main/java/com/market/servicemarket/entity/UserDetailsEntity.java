package com.market.servicemarket.entity;

import lombok.*;

import javax.persistence.Column;
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

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "nic_number")
    private String nicNumber;

    @Column(name = "nic_expiry_date")
    private Date nicExpiryDate;

    private String latitude;

    private String longitude;

    private String city;

    private String country;

    @Column(name = "last_login")
    private Timestamp lastLogin;

    private String email;

    @Column(name = "created_date")
    private Date createdDate;


}
