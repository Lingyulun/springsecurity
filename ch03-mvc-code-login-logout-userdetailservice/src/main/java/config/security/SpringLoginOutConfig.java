package config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 挥霍的人生
 */
@Configuration
@EnableWebSecurity
public class SpringLoginOutConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user")
            .password(passwordEncoder().encode("123"))
            .authorities("asdf")
            .and()
                .withUser("admin")
                .password(passwordEncoder().encode("123"))
                .authorities("zxcb");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
             .formLogin()
                .loginPage("/loginView")
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin").authenticated();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
