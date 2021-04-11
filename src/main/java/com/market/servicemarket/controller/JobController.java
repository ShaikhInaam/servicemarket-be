package com.market.servicemarket.controller;

import com.market.servicemarket.business.base.JobBusiness;
import com.market.servicemarket.request.BaseRequest;
import com.market.servicemarket.response.BaseResponse;
import com.market.servicemarket.util.ConfigurationUtil;
import com.market.servicemarket.util.Constants;
import com.market.servicemarket.util.TransactionLoggerService;
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

    @Autowired
    TransactionLoggerService transactionLoggerService;

    @Autowired
    ConfigurationUtil configurationUtil;


    @PostMapping("/job-shift")
    public ResponseEntity<BaseResponse> jobShift(@Valid @RequestBody BaseRequest request)throws Exception{


        BaseResponse responseObject = jobBusiness.getJobShift(request);
        transactionLoggerService.log(request.getTransactionId(), "/portal/job/job-shift",
                request, responseObject, configurationUtil.getMessage(Constants.POST_REQUEST_RESPONSE_CODE));

        return ResponseEntity.ok(responseObject);

    }

    @PostMapping("/job-type")
    public ResponseEntity<BaseResponse> jobType(@Valid @RequestBody BaseRequest request)throws Exception{


        BaseResponse responseObject = jobBusiness.getJobType(request);
        transactionLoggerService.log(request.getTransactionId(), "/portal/job/job-type",
                request, responseObject, configurationUtil.getMessage(Constants.POST_REQUEST_RESPONSE_CODE));

        return ResponseEntity.ok(responseObject);

    }

    @PostMapping("/apply-job")
    public ResponseEntity<BaseResponse> applyJob(@Valid @RequestBody BaseRequest request)throws Exception{


        BaseResponse responseObject = jobBusiness.applyJob(request);
        transactionLoggerService.log(request.getTransactionId(), "/portal/job/apply-job",
                request, responseObject, configurationUtil.getMessage(Constants.POST_REQUEST_RESPONSE_CODE));

        return ResponseEntity.ok(responseObject);

    }
}
