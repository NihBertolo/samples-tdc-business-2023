package entity

import "github.com/google/uuid"

type Lecture struct {
	ID          uuid.UUID `json:"id"`
	Name        string    `json:"name"`
	Description string    `json:"description"`
	Speaker     Speaker   `json:"speaker"`
	Time        string    `json:"time"`
}
