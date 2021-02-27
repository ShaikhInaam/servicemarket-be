package com.market.servicemarket.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction_logger")
public class TransactionLoggerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "request_json")
    private String requestJson;

    @Column(name = "response_json")
    private String responseJson;

    private Timestamp time;

    private String method;

    private String url;

}
