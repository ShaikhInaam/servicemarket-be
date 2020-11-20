package com.market.servicemarket.service.base;

import com.market.servicemarket.entity.ResponseConstantsEntity;
import org.springframework.stereotype.Service;

public interface ResponseConstantsService {
    void updateConstants();
    ResponseConstantsEntity findConstantsByCode(String code);
}
