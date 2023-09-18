package grpc

import (
	"context"
	"golang-sample-tdc/config"
	"golang-sample-tdc/internal/adapter/learningpb"
	"log"
	"time"

	"google.golang.org/grpc"
	pb "google.golang.org/protobuf"
)

func findLearningTrail(time time.Time) (*learningpb.LearningTrailResponse, error) {

	appConfig, err := config.NewAppConfig("config.json")

	conn, err := grpc.Dial(appConfig.ADDRESS_PORT, grpc.WithInsecure())
	if err != nil {
		log.Fatalf("connection failed: %v", err)
	}
	defer conn.Close()

	client := learningpb.NewLearningTrailAPIClient(conn)

	timestamp := &pb.Timestamp{
		Seconds: int64(time.Second()),
		Nanos:   int32(time.Nanosecond()),
	}

	request := &grpc.FindLearningTrailByDateRequest{
		Date: timestamp,
	}

	response, err := client.FindLearningTrail(context.Background(), request)
	if err != nil {
		log.Fatalf("request failed: %v", err)
	}

	return response, err
}
