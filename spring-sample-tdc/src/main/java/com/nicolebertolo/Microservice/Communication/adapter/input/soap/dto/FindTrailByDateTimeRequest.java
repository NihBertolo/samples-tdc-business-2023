package com.nicolebertolo.Microservice.Communication.adapter.input.soap.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.time.LocalDateTime;


@XmlRootElement(name = "FindTrailByDateTimeRequest")
@Data
public class FindTrailByDateTimeRequest {
    @XmlElement(name = "dateTime")
    private LocalDateTime dateTime;
}
