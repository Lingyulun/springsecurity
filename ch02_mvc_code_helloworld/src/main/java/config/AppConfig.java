package config;

import config.security.SecurityConfigHello;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 挥霍的人生
 */
@Configuration
@Import(SecurityConfigHello.class)
public class AppConfig {
}
