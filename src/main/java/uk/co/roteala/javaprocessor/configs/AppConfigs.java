package uk.co.roteala.javaprocessor.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "roteala.eth.processor")
public class AppConfigs {
    private String url;
    private String masterKey;
}
