package com.nicolebertolo.Microservice.Communication;

import com.nicolebertolo.Microservice.Communication.adapter.output.grpc.LearningTrailServiceClientGRPC;
import com.nicolebertolo.Microservice.Communication.adapter.output.rest.RestClient;
import com.nicolebertolo.Microservice.Communication.adapter.output.soap.SoapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private LearningTrailServiceClientGRPC learningTrailServiceClientGRPC;

    @Autowired
    private RestClient restClient;

    @Autowired
    private SoapClient soapClient;


    @GetMapping("/grpc")
    public void testGrpc(@RequestHeader String localDate) {
        learningTrailServiceClientGRPC.findLearningTrail(LocalDate.parse(localDate));
    }

    @GetMapping("/rest")
    public void testRest(@RequestHeader String localDate) {
        restClient.findAll(LocalDate.parse(localDate).toString(), null);
    }

    @GetMapping("/soap")
    public void testSoap(@RequestHeader String localDate) {
        soapClient.findAllLearningTrailByDate(LocalDate.parse(localDate));
    }
 }
