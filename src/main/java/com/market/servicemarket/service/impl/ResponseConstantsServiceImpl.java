package com.market.servicemarket.service.impl;

import com.market.servicemarket.entity.ResponseConstantsEntity;
import com.market.servicemarket.repository.ResponseConstantsRepository;
import com.market.servicemarket.service.base.ResponseConstantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ResponseConstantsServiceImpl implements ResponseConstantsService {

    private final ResponseConstantsRepository constantsRepository;
    public Map<String, ResponseConstantsEntity> responseConstants = null;

    @Autowired
    public ResponseConstantsServiceImpl(ResponseConstantsRepository constantsRepository){
        this.constantsRepository = constantsRepository;
    }

    @Override
    public void updateConstants() {
        responseConstants = constantsRepository.findAll().stream()
                .collect(Collectors.toMap(ResponseConstantsEntity::getId, Function.identity()));
    }

    @Override
    public ResponseConstantsEntity findConstantsByCode(String code) {
        return responseConstants.get(code);
    }
}
