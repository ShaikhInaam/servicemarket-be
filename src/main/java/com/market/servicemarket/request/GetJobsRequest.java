package com.market.servicemarket.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class GetJobsRequest extends BaseRequest {

    @NotNull
    @NotBlank
    private String username;
}
