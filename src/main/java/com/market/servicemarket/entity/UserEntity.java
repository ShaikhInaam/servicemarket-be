package com.market.servicemarket.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    private String username;

    private String name;

    @Column(name = "user_pass")
    private String userPass;

    private String status;


}
