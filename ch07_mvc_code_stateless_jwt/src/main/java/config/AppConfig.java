package config;

import config.security.MultiChainSecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 挥霍的人生
 */
@Configuration
@ComponentScan({"com.service","com.security"})
@Import(MultiChainSecurityConfig.class)
public class AppConfig {

}
