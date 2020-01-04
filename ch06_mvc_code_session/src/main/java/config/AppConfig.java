package config;

import config.security.SessionCreatePolicySecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 挥霍的人生
 */
@Configuration
@Import(SessionCreatePolicySecurityConfig.class)
public class AppConfig {

}
