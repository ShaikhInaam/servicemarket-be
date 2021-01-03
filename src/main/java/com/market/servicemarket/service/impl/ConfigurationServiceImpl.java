package com.market.servicemarket.service.impl;

import com.market.servicemarket.entity.ConfigurationEntity;
import com.market.servicemarket.repository.ConfigurationRepository;
import com.market.servicemarket.service.base.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {


    @Autowired
    ConfigurationRepository constantsRepository;


    @Override
    public Map updateConstants() {

        Map<String, String> constants = new HashMap<>();

        List<ConfigurationEntity> entityList =  constantsRepository.findAll();
        for (ConfigurationEntity entity: entityList) {

            constants.put(entity.getCode(), entity.getMessageEnglish());
        }

        return constants;
    }

    @Override
    public ConfigurationEntity findConstantsByCode(String code) {
        return constantsRepository.findByCode(code);
    }
}
