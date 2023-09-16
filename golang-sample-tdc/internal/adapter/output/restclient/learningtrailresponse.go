package restclient

type LearningTrailResponse struct {
	LearningTrails []LearningTrail `json:"learningTrails"`
}

type LearningTrail struct {
	Name     string    `json:"name"`
	Lectures []Lecture `json:"lecture"`
	Date     string    `json:"date"`
}

type Lecture struct {
	Name        string  `json:"name"`
	Description string  `json:"description"`
	Speaker     Speaker `json:"speaker"`
	Time        string  `json:"time"`
}

type Speaker struct {
	Name            string        `json:"name"`
	SelfDescription string        `json:"selfDescription"`
	SocialMedias    []SocialMedia `json:"socialMedias"`
}

type SocialMedia struct {
	Name string `json:"name"`
	Icon string `json:"icon"`
	Link string `json:"link"`
}
