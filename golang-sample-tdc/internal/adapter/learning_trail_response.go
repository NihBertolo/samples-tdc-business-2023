package adapter

import "golang-sample-tdc/internal/adapter/learningpb"

type LearningTrailResponse struct {
	LearningTrails []LearningTrail `json:"learningTrails"`
}

type LearningTrail struct {
	Name     string    `json:"name"`
	Lectures []Lecture `json:"lectures"`
	Date     string    `json:"date"`
}

func ToLearningTrailModel(grpc *learningpb.LearningTrail) LearningTrail {
	lectures := []Lecture{}

	for _, i := range grpc.Lectures {
		lecture := toLectureModel(i)
		lectures = append(lectures, lecture)
	}

	return LearningTrail{
		Name:     grpc.Name,
		Date:     grpc.GetDate().AsTime().GoString(),
		Lectures: lectures,
	}
}

type Lecture struct {
	Name        string  `json:"name"`
	Description string  `json:"description"`
	Speaker     Speaker `json:"speaker"`
	Time        string  `json:"time"`
}

func ToLectureModel(grpc *learningpb.Lecture) Lecture {

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
