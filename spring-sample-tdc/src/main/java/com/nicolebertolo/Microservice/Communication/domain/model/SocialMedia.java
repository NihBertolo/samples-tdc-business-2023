package com.nicolebertolo.Microservice.Communication.domain.model;

import com.nicolebertolo.Microservice.Communication.adapter.input.LearningTrailResponse;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity(name = "tb_social_medias")
public class SocialMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String icon;
    private String link;

    @ManyToOne
    private Speaker speaker;

    public static LearningTrailResponse.LearningTrail.Lecture.Speaker.SocialMedia toResponse(SocialMedia model) {
        return LearningTrailResponse.LearningTrail.Lecture.Speaker.SocialMedia.builder()
                .icon(model.getIcon())
                .link(model.getLink())
                .name(model.getName())
                .build();
    }
}
