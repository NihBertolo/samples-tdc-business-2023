package com.nicolebertolo.Microservice.Communication.adapter.input.rest;

import com.nicolebertolo.Microservice.Communication.adapter.input.LearningTrailResponse;
import com.nicolebertolo.Microservice.Communication.application.port.input.FindTrailUseCase;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/v1/learning-trails")
public class LearningTrailController {

    @Autowired
    private FindTrailUseCase findTrailUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LearningTrailResponse> findAll(
            @RequestParam(name = "date", required = false) Optional<String> date,
            @RequestParam(name = "dateTime", required = false) Optional<String> dateTime
    ) {
        LearningTrailResponse response;

        response = date.isPresent() ?
                findTrailUseCase.findAllLearningTrailByDate(LocalDate.parse(date.get())) : dateTime.isPresent() ?
                findTrailUseCase.findLearningTrail(LocalDateTime.parse(dateTime.get())) : findTrailUseCase.findAllLearningTrail();

        return ResponseEntity.ok(response);
    }

}
