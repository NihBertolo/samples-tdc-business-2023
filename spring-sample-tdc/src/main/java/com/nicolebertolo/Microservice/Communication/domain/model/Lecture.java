package com.nicolebertolo.Microservice.Communication.domain.model;

import com.nicolebertolo.Microservice.Communication.adapter.input.LearningTrailResponse;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity(name = "tb_lectures")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String description;
    private LocalTime time;

    @ManyToOne
    private LearningTrail learningTrail;

    @OneToOne(mappedBy = "lecture", fetch = FetchType.EAGER)
    private Speaker speaker;

    public static LearningTrailResponse.LearningTrail.Lecture toResponse(Lecture model) {
        return LearningTrailResponse.LearningTrail.Lecture.builder()
                .description(model.getDescription())
                .name(model.getName())
                .speaker(Speaker.toResponse(model.getSpeaker()))
                .build();
    }
}
