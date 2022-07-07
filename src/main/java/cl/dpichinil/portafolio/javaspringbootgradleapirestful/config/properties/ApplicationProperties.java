package cl.dpichinil.portafolio.javaspringbootgradleapirestful.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ApplicationProperties {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.expiration}")
    private int jwtExpiration;
}
