package com.market.servicemarket.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class JobPostRequest extends BaseRequest{

    @NotBlank
    private String username;

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotBlank
    private String companyName;

    private String companyAddress;

    @NotBlank
    private String experienceStart;

    private String experienceEnd;

    private String salaryStart;

    private String salaryEnd;

    @NotBlank
    private String qualification;

    private String totalVacancies;

    @NotNull
    private Integer jobTypeId;

    @NotNull
    private Integer jobShiftId;

    private String jobCategory;

}