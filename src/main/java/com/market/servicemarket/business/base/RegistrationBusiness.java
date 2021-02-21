package com.market.servicemarket.business.base;

import com.market.servicemarket.request.RegistrationUser;
import com.market.servicemarket.response.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationBusiness {
    BaseResponse registerUser(RegistrationUser registrationUser)throws Exception ;
}
