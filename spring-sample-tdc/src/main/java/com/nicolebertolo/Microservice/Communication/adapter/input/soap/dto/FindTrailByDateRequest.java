package com.nicolebertolo.Microservice.Communication.adapter.input.soap.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.time.LocalDate;


@XmlRootElement(name = "FindAllTrailByDateRequest")
@Data
public class FindTrailByDateRequest {
    @XmlElement(name = "date")
    private LocalDate date;
}
