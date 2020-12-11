package com.market.servicemarket.business.base;

import com.market.servicemarket.request.BaseRequest;
import com.market.servicemarket.response.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface JobBusiness {

    BaseResponse getJobShift(BaseRequest request)throws Exception;

}
