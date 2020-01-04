package config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author 挥霍的人生
 */
@Configuration
@EnableWebSecurity(debug = true)
//1. 如何查看当前配置所使用的过滤链 debug=true
public class SecurityAuthenticationFlowConfig extends WebSecurityConfigurerAdapter {

   /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("1234"))
                .authorities("xxx");
    }*/
   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        *//*http.formLogin()
                .loginPage("/loginView")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .and()
                .authorizeRequests()
                    .antMatchers("/admin").authenticated()
                    .antMatchers("/").permitAll();*//*
        //添加过滤器
       // http.addFilterBefore(new BFilter(), UsernamePasswordAuthenticationFilter.class);
    }
*/


    /**
     * 类似于xml中的AuthenticationManager的配置
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //@formatter:off
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("123"))
                .authorities("ROLE_USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("123"))
                .authorities("ROLE_ADMIN");
        //@formatter:on
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 这块的配置类似于xml中的http块的配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests()
                .antMatchers("/admin").authenticated()
                .antMatchers("/").permitAll();
    }
}
