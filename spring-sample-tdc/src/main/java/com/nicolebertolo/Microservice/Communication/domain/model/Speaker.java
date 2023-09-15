package com.nicolebertolo.Microservice.Communication.domain.model;

import com.nicolebertolo.Microservice.Communication.adapter.input.LearningTrailResponse;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "tb_speakers")
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String selfDescription;

    @OneToOne
    private Lecture lecture;

    @OneToMany(mappedBy = "speaker", fetch = FetchType.EAGER)
    private List<SocialMedia> socialMedias;

    public static LearningTrailResponse.LearningTrail.Lecture.Speaker toResponse(Speaker model) {
        return LearningTrailResponse.LearningTrail.Lecture.Speaker.builder()
                .name(model.name)
                .selfDescription(model.getSelfDescription())
                .socialMedias(model.getSocialMedias().stream().map(SocialMedia::toResponse).toList())
                .build();
    }
}
