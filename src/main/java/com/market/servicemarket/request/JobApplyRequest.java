package com.market.servicemarket.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplyRequest extends BaseRequest {

    @NotBlank
    private Integer jobId;
    @NotBlank
    private String cvPath;
    private String coverLetter;
    private String description;
}
