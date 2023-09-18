package config

import (
	"fmt"

	"github.com/spf13/viper"
)

// AppConfig representa a configuração da aplicação.
type AppConfig struct {
	APIURL       string
	ADDRESS_PORT string
}

func NewAppConfig(configPath string) (*AppConfig, error) {
	viper.SetConfigFile(configPath)

	if err := viper.ReadInConfig(); err != nil {
		return nil, fmt.Errorf("erro ao ler o arquivo de configuração: %v", err)
	}

	apiURL := viper.GetString("api_url")
	addressPort := viper.GetString("address_port")

	return &AppConfig{
		APIURL:       apiURL,
		ADDRESS_PORT: addressPort,
	}, nil
}
