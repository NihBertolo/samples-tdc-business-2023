package com.nicolebertolo.Microservice.Communication.domain.model;

import com.nicolebertolo.Microservice.Communication.adapter.input.LearningTrailResponse;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "tb_learning_trails")
public class LearningTrail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String description;
    private LocalDate date;

    @OneToMany(mappedBy = "learningTrail", fetch = FetchType.EAGER)
    private List<Lecture> lectures;


    public static LearningTrailResponse.LearningTrail toResponse(LearningTrail model) {
        return LearningTrailResponse.LearningTrail.builder()
                .name(model.getName())
                .date(model.getDate())
                .lectures(model.getLectures().stream().map(Lecture::toResponse).toList())
                .build();
    }
}
