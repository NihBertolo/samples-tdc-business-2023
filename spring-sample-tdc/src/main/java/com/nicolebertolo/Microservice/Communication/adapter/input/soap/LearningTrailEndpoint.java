package com.nicolebertolo.Microservice.Communication.adapter.input.soap;

import com.nicolebertolo.Microservice.Communication.adapter.input.LearningTrailResponse;
import com.nicolebertolo.Microservice.Communication.adapter.input.soap.dto.FindTrailByDateRequest;
import com.nicolebertolo.Microservice.Communication.adapter.input.soap.dto.FindTrailByDateTimeRequest;
import com.nicolebertolo.Microservice.Communication.application.port.input.FindTrailUseCase;
import jakarta.xml.bind.JAXBElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import javax.xml.namespace.QName;

@Endpoint
public class LearningTrailEndpoint {
    private static final String NAMESPACE = "http://tdc.microservicejava.com";

    @Autowired
    private FindTrailUseCase findTrailUseCase;

    @PayloadRoot(namespace = NAMESPACE, localPart = "FindTrailByDateTimeRequest")
    public JAXBElement<LearningTrailResponse> findTrail(
            @RequestPayload JAXBElement<FindTrailByDateTimeRequest> findTrailRequest
    ) {
        return new JAXBElement<>(
                new QName(NAMESPACE, "LearningTrailResponse"),
                LearningTrailResponse.class,
                findTrailUseCase.findLearningTrail(findTrailRequest.getValue().getDateTime())
        );
    }
    @PayloadRoot(namespace = NAMESPACE, localPart = "FindTrailRequest")
    public JAXBElement<LearningTrailResponse> findAllTrail() {
        return new JAXBElement<>(
                new QName(NAMESPACE, "LearningTrailResponse"),
                LearningTrailResponse.class,
                findTrailUseCase.findAllLearningTrail()
        );
    }
    @PayloadRoot(namespace = NAMESPACE, localPart = "FindAllTrailByDateRequest")
    public JAXBElement<LearningTrailResponse> findAllTrailByDate(
            @RequestPayload JAXBElement<FindTrailByDateRequest> findTrailRequest
    ) {
        return new JAXBElement<>(
                new QName(NAMESPACE, "LearningTrailResponse"),
                LearningTrailResponse.class,
                findTrailUseCase.findAllLearningTrailByDate(findTrailRequest.getValue().getDate())
        );
    }
}
