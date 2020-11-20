package com.market.servicemarket.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Constants")
public class ResponseConstantsEntity implements Serializable {

    @Id
    private String id;

    private String code;

    private String message;


}
