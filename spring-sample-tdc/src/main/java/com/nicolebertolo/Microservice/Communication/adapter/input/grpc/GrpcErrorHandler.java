package com.nicolebertolo.Microservice.Communication.adapter.input.grpc;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.springframework.stereotype.Component;

@Component
public class GrpcErrorHandler {

    public StatusRuntimeException handle(Exception ex) {
        var status = ex instanceof StatusRuntimeException ? Status
                .fromCode(((StatusRuntimeException) ex).getStatus().getCode())
                .withDescription(((StatusRuntimeException) ex).getStatus().getDescription()) : Status.UNKNOWN;

        return status.withCause(ex).asRuntimeException();
    }
}