package com.market.servicemarket.usage_analysis_entity;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usage_analysis")
public class UsageAnalysisEntity implements Serializable {

    @Id
    private Integer id;

    private String method;
    private String operation;
    private String  url;
    private String ip;
    private Timestamp timestamp;


}
