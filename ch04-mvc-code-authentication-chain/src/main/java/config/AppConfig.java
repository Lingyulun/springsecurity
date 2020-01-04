package config;

import config.security.SecurityAuthenticationFlowConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 挥霍的人生
 */
@Configuration
@Import(SecurityAuthenticationFlowConfig.class)
public class AppConfig {
}
