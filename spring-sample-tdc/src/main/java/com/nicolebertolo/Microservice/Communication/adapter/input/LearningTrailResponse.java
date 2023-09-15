package com.nicolebertolo.Microservice.Communication.adapter.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@XmlRootElement(name = "LearningTrailResponse")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LearningTrailResponse {

    @JsonProperty("learningTrails")
    private List<LearningTrail> learningTrails;

    @Data
    @Builder
    public static class LearningTrail {
        @JsonProperty("name")
        @XmlElement(name = "name")
        private String name;

        @JsonProperty("lecture")
        @XmlElement(name = "lecture")
        private List<Lecture> lectures;

        @JsonProperty("date")
        @XmlElement(name = "date")
        private LocalDate date;

        @Data
        @Builder
        public static class Lecture {

            @JsonProperty("name")
            @XmlElement(name = "name")
            private String name;

            @JsonProperty("description")
            @XmlElement(name = "description")
            private String description;

            @JsonProperty("speaker")
            @XmlElement(name = "speaker")
            private Speaker speaker;

            @JsonProperty("time")
            @XmlElement(name = "time")
            private LocalTime time;

            @Data
            @Builder
            public static class Speaker {
                @JsonProperty("name")
                @XmlElement(name = "name")
                private String name;

                @JsonProperty("selfDescription")
                @XmlElement(name = "selfDescription")
                private String selfDescription;

                @JsonProperty("socialMedias")
                @XmlElement(name = "socialMedias")
                private List<SocialMedia> socialMedias;

                @Data
                @Builder
                public static class SocialMedia {
                    @JsonProperty("name")
                    @XmlElement(name = "name")
                    private String name;

                    @JsonProperty("icon")
                    @XmlElement(name = "icon")
                    private String icon;

                    @JsonProperty("link")
                    @XmlElement(name = "link")
                    private String link;
                }
            }
        }
    }

}
