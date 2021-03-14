package com.market.servicemarket.business.base;

import com.market.servicemarket.request.RegisterUser;
import com.market.servicemarket.response.BaseResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface RegisterBusiness {

    BaseResponse registerUser(RegisterUser request)throws Exception;
}
