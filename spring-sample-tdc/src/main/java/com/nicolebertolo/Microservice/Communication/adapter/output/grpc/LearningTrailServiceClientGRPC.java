package com.nicolebertolo.Microservice.Communication.adapter.output.grpc;

import com.google.protobuf.Timestamp;
import grpc.nicolebertolo.microservice.communication.FindLearningTrailByDateRequest;
import grpc.nicolebertolo.microservice.communication.LearningTrailApiGrpc;
import grpc.nicolebertolo.microservice.communication.LearningTrailResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;

@Service
public class LearningTrailServiceClientGRPC {

    @Value("${grpc.clients.learning-trail.address}")
    private String address;

    @Value("${grpc.clients.learning-trail.port}")
    private int port;

    private ManagedChannel getChannel() {
        return ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();
    }

    public LearningTrailResponse findLearningTrail(
            LocalDate date
    ) {
        var channel = this.getChannel();
        var timestamp = Timestamp.newBuilder()
                .setSeconds(date.atStartOfDay().toEpochSecond(ZoneOffset.UTC))
                .build();

        try {
            var request = FindLearningTrailByDateRequest.newBuilder()
                    .setDate(timestamp)
                    .build();

            var response = LearningTrailApiGrpc.newBlockingStub(channel).findLearningTrail(request);
            channel.shutdown();
            return response;
        } catch (StatusRuntimeException ex) {
            if (ex.getStatus().getCode().toStatus().equals(Status.NOT_FOUND)) {
                throw new ResourceNotFoundException("Learning Trail not found.");
            } else if (ex.getStatus().getCode().toStatus().equals(Status.UNAVAILABLE)) {
                throw new RuntimeException("Service unavailable");
            } else {
                throw new RuntimeException("Error at communication.");
            }
        }
    }
}
