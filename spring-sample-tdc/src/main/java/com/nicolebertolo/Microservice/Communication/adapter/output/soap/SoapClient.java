package com.nicolebertolo.Microservice.Communication.adapter.output.soap;

import com.example.LearningTrailResponse;
import com.nicolebertolo.Microservice.Communication.adapter.input.soap.dto.FindTrailByDateRequest;
import com.nicolebertolo.Microservice.Communication.adapter.input.soap.dto.FindTrailByDateTimeRequest;
import com.nicolebertolo.Microservice.Communication.adapter.input.soap.dto.FindTrailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class SoapClient extends WebServiceGatewaySupport {

    public LearningTrailResponse findLearningTrail(LocalDateTime dateTime) {
        FindTrailByDateTimeRequest request = new FindTrailByDateTimeRequest();
        request.setDateTime(dateTime);

        return (LearningTrailResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public LearningTrailResponse findAllLearningTrail() {
        FindTrailRequest request = new FindTrailRequest();

        return (LearningTrailResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public LearningTrailResponse findAllLearningTrailByDate(LocalDate date) {
        FindTrailByDateRequest request = new FindTrailByDateRequest();
        request.setDate(date);

        return (LearningTrailResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }
}
