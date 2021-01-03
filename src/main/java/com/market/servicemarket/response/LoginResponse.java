package com.market.servicemarket.response;

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
public class LoginResponse{


    private String name;
    private UserToken userToken;
    private UserDetails userDetails;


}
