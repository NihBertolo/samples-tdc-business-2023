package com.nicolebertolo.Microservice.Communication.application.service.impl;

import com.nicolebertolo.Microservice.Communication.adapter.input.LearningTrailResponse;
import com.nicolebertolo.Microservice.Communication.domain.model.LearningTrail;
import com.nicolebertolo.Microservice.Communication.domain.repository.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static com.nicolebertolo.Microservice.Communication.domain.model.LearningTrail.toResponse;

@Service
public class FindTrailUseCase implements com.nicolebertolo.Microservice.Communication.application.port.input.FindTrailUseCase {
    @Autowired
    private TrailRepository repository;

    @Override
    public LearningTrailResponse findLearningTrail(LocalDateTime dateTime) {

        LearningTrail learningTrail = repository.findByDateAndLecturesTime(
                dateTime.toLocalDate(),
                dateTime.toLocalTime()
        ).orElseThrow(() -> new NoSuchElementException("Learning Trail with this Date and Time not found."));

        return LearningTrailResponse.builder()
                .learningTrails(List.of(toResponse(learningTrail)))
                .build();
    }

    @Override
    public LearningTrailResponse findAllLearningTrail() {
        return LearningTrailResponse.builder()
                .learningTrails(repository.findAll().stream().map(LearningTrail::toResponse).toList())
                .build();
    }

    @Override
    public LearningTrailResponse findAllLearningTrailByDate(LocalDate date) {
        return LearningTrailResponse.builder()
                .learningTrails(repository.findAllByDate(date).stream().map(LearningTrail::toResponse).toList())
                .build();
    }
}
