package com.market.servicemarket.business.base;

import com.market.servicemarket.request.BaseRequest;
import com.market.servicemarket.response.BaseResponse;

public interface MenuBusiness {


    BaseResponse getMenu(BaseRequest request)throws Exception;
}
