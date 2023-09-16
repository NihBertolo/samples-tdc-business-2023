package router

import (
	"golang-sample-tdc/internal/adapter/input/rest/handler"
	"net/http"

	"github.com/gorilla/mux"
)

func NewRouter() *mux.Router {
	router := mux.NewRouter()
	learningTrailHandler := handler.NewLearningTrailHandler()

	router.HandleFunc("/v1/learning-trails", learningTrailHandler.GetLearningTrails).Methods(http.MethodGet)

	return router
}
