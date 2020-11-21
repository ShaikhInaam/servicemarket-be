package com.market.servicemarket.service.base;

import com.market.servicemarket.entity.ConfigurationEntity;


import java.util.Map;


public interface ConfigurationService {
    Map updateConstants();
    ConfigurationEntity findConstantsByCode(String code);
}
