package restclient

import (
	"encoding/json"
	"fmt"
	"golang-sample-tdc/config"
	"golang-sample-tdc/internal/adapter"
	"log"
	"net/http"
	"net/url"
)

type RestClient struct {
	baseURL    string
	httpClient *http.Client
}

func fetchLearningTrails(localDate string, localDateTime string) (adapter.LearningTrailResponse, error) {
	var learningTrailResponse adapter.LearningTrailResponse

	appConfig, err := config.NewAppConfig("config.json")
	if err != nil {
		log.Fatalf("Error at load configs: %v\n", err)
		return learningTrailResponse, err
	}

	queryParams := url.Values{}
	if localDate != "" {
		queryParams.Add("localDate", localDate)
	}
	if localDateTime != "" {
		queryParams.Add("localDateTime", localDateTime)
	}

	apiURL := appConfig.APIURL + "?" + queryParams.Encode()

	resp, err := http.Get(apiURL)
	if err != nil {
		return learningTrailResponse, err
	}
	defer resp.Body.Close()

	if resp.StatusCode != http.StatusOK {
		err := fmt.Errorf("Unexpected status code: %d\n", resp.StatusCode)
		log.Fatal(err.Error())
		return learningTrailResponse, err
	}

	err = json.NewDecoder(resp.Body).Decode(&learningTrailResponse)
	if err != nil {
		return learningTrailResponse, err
	}

	return learningTrailResponse, nil
}
