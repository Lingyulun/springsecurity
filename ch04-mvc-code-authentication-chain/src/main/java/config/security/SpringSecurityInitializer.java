package config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @author 挥霍的人生
 * 继承AbstractSecurityWebApplicationInitialize等价于注册了委托过滤器
 */
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
