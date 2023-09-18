package cmd

import (
	"context"
	"golang-sample-tdc/internal/adapter/learningpb"
	"golang-sample-tdc/internal/app/usecase"
	"log"
	"net"

	"google.golang.org/grpc"
)

type server struct{}

func main() {
	lis, err := net.Listen("tcp", "0.0.0.0:50051")
	if err != nil {
		log.Fatalf("Failed to listen %v", err)
	}

	s := grpc.NewServer()
	learningpb.RegisterLearningTrailAPIServer(s, &server{})

	if err := s.Serve(lis); err != nil {
		log.Fatalf("Failed to start server %v", err)
	}
}

func (*server) FindLearningTrail(
	ctx context.Context,
	in *learningpb.FindLearningTrailByDateRequest) (*learningpb.LearningTrailResponse, error) {
	learningTrails, err := usecase.GetLearningTrails()
	res := learningpb.LearningTrailResponse{
		LearningTrails: learningTrails,
	}

	return &res, nil
}
