package config;

import config.security.rest.RestApiSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 挥霍的人生
 */
@Configuration
@Import(RestApiSecurityConfig.class)
public class AppConfig {
}
