package com.nicolebertolo.Microservice.Communication.adapter.input.grpc;

import com.nicolebertolo.Microservice.Communication.application.port.input.FindTrailUseCase;
import grpc.nicolebertolo.microservice.communication.*;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j2;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@GrpcService
@Log4j2
public class LearningTrailGrpcService extends LearningTrailApiGrpc.LearningTrailApiImplBase {

    @Autowired
    private FindTrailUseCase findTrailUseCase;

    @Autowired
    private GrpcErrorHandler grpcErrorHandler;

    @Override
    public void findLearningTrail(
            FindLearningTrailByDateRequest request,
            StreamObserver<LearningTrailResponse> streamObserver
    ) {
        Instant instant = Instant.ofEpochSecond(request.getDate().getSeconds(), request.getDate().getNanos());
        var trails = findTrailUseCase.findLearningTrail(LocalDateTime.ofInstant(instant, ZoneOffset.UTC));

        try {
            var response = LearningTrailResponse.newBuilder()
                    .addAllLearningTrails(trails.getLearningTrails().stream().map(trail ->
                            LearningTrail.newBuilder()
                                    .addAllLectures(trail.getLectures().stream().map(this::toGrpc).toList())
                                    .build()
                    ).toList())
                    .build();

            streamObserver.onNext(response);
            streamObserver.onCompleted();
        } catch (StatusRuntimeException ex) {
            streamObserver.onError(grpcErrorHandler.handle(ex));
        }
    }

    @Override
    public void findAllLearningTrail(
            FindAllLearningTrailRequest request,
            StreamObserver<LearningTrailResponse> streamObserver
    ) {
        var trails = findTrailUseCase.findAllLearningTrail();

        var response = LearningTrailResponse.newBuilder()
                .addAllLearningTrails(trails.getLearningTrails().stream().map(trail ->
                        LearningTrail.newBuilder()
                                .addAllLectures(trail.getLectures().stream().map(this::toGrpc).toList())
                                .build()
                ).toList())
                .build();

        streamObserver.onNext(response);
        streamObserver.onCompleted();
    }

    public void findAllLearningTrailByDate(
            FindLearningTrailByDateRequest request,
            StreamObserver<LearningTrailResponse> streamObserver
    ) {

        Instant instant = Instant.ofEpochSecond(request.getDate().getSeconds(), request.getDate().getNanos());
        var trails = findTrailUseCase.findAllLearningTrailByDate(LocalDate.ofInstant(instant, ZoneOffset.UTC));

        var response = LearningTrailResponse.newBuilder()
                .addAllLearningTrails(trails.getLearningTrails().stream().map(trail ->
                        LearningTrail.newBuilder()
                                .addAllLectures(trail.getLectures().stream().map(this::toGrpc).toList())
                                .build()
                ).toList())
                .build();

        streamObserver.onNext(response);
        streamObserver.onCompleted();
    }



    private Lecture toGrpc(com.nicolebertolo.Microservice.Communication.adapter.input.LearningTrailResponse.LearningTrail.Lecture lecture) {
        return Lecture.newBuilder()
                .setDescription(lecture.getDescription())
                .setName(lecture.getName())
                .setSpeaker(Speaker.newBuilder()
                        .setName(lecture.getSpeaker().getName())
                        .setSelfDescription(lecture.getSpeaker().getSelfDescription())
                        .addAllSocialMedias(lecture.getSpeaker().getSocialMedias().stream()
                                .map(media -> SocialMedia.newBuilder()
                                        .setIcon(media.getIcon())
                                        .setName(media.getName())
                                        .setLink(media.getLink())
                                        .build())
                                .toList())
                        .build())
                .build();
    }
}
