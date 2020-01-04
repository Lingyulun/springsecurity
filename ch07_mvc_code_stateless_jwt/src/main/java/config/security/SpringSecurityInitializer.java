package config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 继承AbstractSecurityWebApplicationContext等价于注册了委托过滤器
 * @author 挥霍的人生
 */
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
