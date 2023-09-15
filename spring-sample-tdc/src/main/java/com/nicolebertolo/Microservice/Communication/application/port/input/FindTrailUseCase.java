package com.nicolebertolo.Microservice.Communication.application.port.input;


import com.nicolebertolo.Microservice.Communication.adapter.input.LearningTrailResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface FindTrailUseCase {

    LearningTrailResponse findLearningTrail(LocalDateTime dateTime);

    LearningTrailResponse findAllLearningTrail();

    LearningTrailResponse findAllLearningTrailByDate(LocalDate date);
}
