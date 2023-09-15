package com.nicolebertolo.Microservice.Communication.domain.repository;

import com.nicolebertolo.Microservice.Communication.domain.model.LearningTrail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrailRepository extends JpaRepository<LearningTrail, UUID> {
    Optional<LearningTrail> findByDateAndLecturesTime(LocalDate date, LocalTime time);
    List<LearningTrail> findAllByDate(LocalDate date);
}
