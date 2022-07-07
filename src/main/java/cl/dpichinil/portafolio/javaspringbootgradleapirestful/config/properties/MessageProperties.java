package cl.dpichinil.portafolio.javaspringbootgradleapirestful.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Configuration
@ConfigurationProperties(prefix = "msg")
@PropertySource("message.properties")
@Getter @Setter
public class MessageProperties {
    private Map<Integer, String> generalMessage;
    private Map<Integer, String> insertUser;

}
