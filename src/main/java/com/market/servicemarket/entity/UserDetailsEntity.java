package com.market.servicemarket.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            updatable = false,
            insertable = false)
    private UserEntity userEntity;

}
