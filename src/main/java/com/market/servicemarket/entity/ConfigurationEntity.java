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
@Table(name = "configuration")
public class ConfigurationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;

    private String code;

    @Column(name = "message_english")
    private String messageEnglish;


}
