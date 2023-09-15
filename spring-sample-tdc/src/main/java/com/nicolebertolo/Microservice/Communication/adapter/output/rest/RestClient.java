package com.nicolebertolo.Microservice.Communication.adapter.output.rest;

import com.nicolebertolo.Microservice.Communication.adapter.input.LearningTrailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "restClient", url = "http://application.default.svc.cluster.local:8080/v1/learning-trails")
public interface RestClient {

    @GetMapping
    public ResponseEntity<LearningTrailResponse> findAll(
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "dateTime", required = false) String dateTime
    );

}
