package com.market.servicemarket.controller;

import com.market.servicemarket.business.base.JobBusiness;
import com.market.servicemarket.request.BaseRequest;
import com.market.servicemarket.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/portal/job")
public class JobController {

    @Autowired
    JobBusiness jobBusiness;


    @PostMapping("/job-shift")
    public ResponseEntity<BaseResponse> login(@Valid @RequestBody BaseRequest request)throws Exception{



        BaseResponse responseObject = jobBusiness.getJobShift(request);
        return ResponseEntity.ok(responseObject);

    }

}
