package com.market.servicemarket.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.market.servicemarket.dto.UserDetails;
import com.market.servicemarket.dto.UserToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse{


    private String name;
    private UserToken userToken;
    private UserDetails userDetails;


}
