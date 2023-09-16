package handler

import (
	"encoding/json"
	"net/http"

	"golang-sample-tdc/internal/app/usecase"
)

type LearningTrailHandler struct {
	// Injete a dependência para o caso de uso aqui, se necessário
}

func NewLearningTrailHandler() *LearningTrailHandler {
	return &LearningTrailHandler{}
}

func (h *LearningTrailHandler) GetLearningTrails(w http.ResponseWriter, r *http.Request) {
	learningTrails, err := usecase.GetLearningTrails()
	if err != nil {
		http.Error(w, "Error at retrieve learning trail info", http.StatusInternalServerError)
		return
	}

	// Serializa as trilhas de aprendizado em JSON e escreve a resposta
	w.Header().Set("Content-Type", "application/json")
	if err := json.NewEncoder(w).Encode(learningTrails); err != nil {
		http.Error(w, "Error at serialize response", http.StatusInternalServerError)
		return
	}
}
