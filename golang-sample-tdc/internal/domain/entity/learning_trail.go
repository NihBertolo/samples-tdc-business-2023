package entity

import (
	"github.com/google/uuid"
	"gorm.io/gorm"
)

type LearningTrail struct {
	gorm.Model
	ID       uuid.UUID `gorm:"not null"`
	Name     string    `gorm:"not null"`
	Lectures []Lecture
	Date     string `gorm:"not null"`
}

func NewLearningTrail(name string, lectures []Lecture, date string) *LearningTrail {
	return &LearningTrail{
		ID:       uuid.New(),
		Name:     name,
		Lectures: lectures,
		Date:     date,
	}
}
