# DelegatingFilterProxy
作用是从spring容器中找出一个其它的过滤器来干活
查找的方式是getBean("名字"),这个名字就是你配置
委托过滤器时指定的filterName

委托过滤器本质作用是把自己的工作交给一个被spring管理的过滤器来做
这样可以本来由tomcat容器管理filter的执行,变成spring容器管理
过滤器的初始化.

# FilterChainProxy
此过滤器由spring容器管理,是DelegatingFilterProxy委托到的一个过滤器
此过滤器就是被称之为springSecurityFilterChain
这个过滤器管理着整个spring security安全的过滤器链

# 多条安全过滤链的配置
1.创建多个配置类,要求加上EnableWebSecurity注解
2.配置类必须继承WebSecurityConfigurerAdapter
3.利用Order注解调整顺序,保证不一样,默认的顺序是100

# HttpSecurity构建器
此构建器的配置,是用来操作安全链的
比如:
http.csrf.disable() 就表示从过滤链中移除进行csrf出来的一个过滤器
http.formLogin().loginPage():表示:
1. 往过滤链里面添加了至少一个过滤器:UsernamePasswordAuthenticationFilter
2. 调整了UsernamePasswordAuthenticationFilter的登录url值
总之:HttpSecurity配置的是一个安全链


# WebSecurity
此对象是一个构建器(builder),它是用来处理FilterChainProxy
所以WebSecurity的配置,就会影响所有的安全链.
它常用来配置一个项目,就是忽略静态文件,代码如下:
```java
@Override
public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/static/**");
}
```

所以:WebSecurity用来构建(builder) FilterChainProxy
HttpSecurity用来构建spring security的整个安全链

# spring security的安全链

## SecurityContextPersistenceFilter
用来读取与存储SecurityContext.其作用是
1.如果能读取用到有效的SecurityContext,一般意味着
你以前验证过,就不需要重复验证
2. 验证通过之后,会把SecurityContext保存起来,默认是保存到会话中

## UsernamePasswordAuthenticationFilter
这个过滤器就是用来做验证的.主要是靠FormLogin来配置它

## ExceptionTranslationFilter

主要是处理整个安全链的异常的,重点关注AuthenticationException
和AuthorizeException.
## FilterSecurityInterceptor
这个是重中之重,它是专门处理你的验证表达式与授权表达式


## 验证流程
访问一个需要验证的地址,如果没有登录
1. 经过SecurityContextPersistenceFilter过滤器,从会话中读取不到SecurityContext
2. 会叫FilterSecurityInterceptor做权限处理,然后因为没有验证,所以抛出异常
3. ExceptionTranslationFilter会捕获异常,就交给AuthenticationEntryPoint的commence
方法发起验证处理,默认就是跳转到登录页面
4. 登录之后交给UsernamePasswordAuthenticationFilter去验证,而验证就交给AuthenticationManager
去验证
5. FilterSecurityInterceptor验证通过
6.回到SecurityContextPersistenceFilter把SecurityContext对象保存到http session
7.下次再访问要验证的url地址,经过第一个过滤器SecurityContextPersistenceFilter
此时可以从会话读取到SecurityContext对象,所以就不需要再验证


做法一:
```java

@Configuration
@EnableWebSecurity(debug = true)
@Order(99)
public class AChain extends WebSecurityConfigurerAdapter {
  @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/bar/**");
    }
}


@Configuration
@EnableWebSecurity(debug = true)
@Order(99)
public class BChain extends WebSecurityConfigurerAdapter {
      @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/foo/**");
        }
}
```

第二种方法:利用内部类的形式:
```java
@Configuration

public class SecurityAuthenticationFlowConfig {


    @Order(98)
    @Configuration
    @EnableWebSecurity(debug = true)
    public static class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/foo/**");
        }

    }
    @Configuration
    @EnableWebSecurity(debug = true)
    @Order(99)
    public static class  MobileSecurityConfig extends WebSecurityConfigurerAdapter{


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/bar/**");
        }
      
    }

}
```
# cors 
方法一:只在安全过滤层面进行配置,不在mvc中进行任何配置
```java
 http.cors().configurationSource(configurationSource())


private UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://127.0.0.1:8848");

        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("HEAD");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
```

方法二: 在安全层面配置http.cors(),在mvc层面配置具体的跨域设置
```java
http.cors();
```

mvc:
```java
  
    public void addCorsMappings(CorsRegistry registry) {
     /*   registry.addMapping("/**")
                .allowedMethods("GET","POST","OPTIONS","HEAD","DELETE","PUT")
                .allowedOrigins("http://127.0.0.1:8848")
                .allowCredentials(true);*/
    }
```

这种配置的原理是spring security在容器中找跨域相关的bean
找不到会报错,所以这种配置能生效的前提是spring security相关配置
所在的spring容器和spring mvc所在的spring容器是同一个容器或者
spring security的spring容器是mvc的spring容器的子容器

# 术语简介
委托过滤器(DelegatingFilterProxy)
过滤链代理(FilterChainProxy)
构建器(builder)比如HttpSecurity,WebSecurity,AuthenticationManagerBuilder
配置器(configurer) CsrfConfigurer
构建器构建的目标对象,有:filter,authenticationManager


# 作业
1. 如何查看当前配置所使用的过滤链
debug=true

2. 配置http.csrf.disable(),观察过滤链的情况

不显示CsrfFilter过滤器

3. 配置http.csrf.disable().and().csrf(),请问是否添加了csrf的过滤器
会添加过滤器
4.配置http.formLogin().loginPage().and().formLogin().loginSuccessUrl()
请问这里配置了2个UsernamePasswordAuthenticationFilter还是一个?
如果是一个,那么是否把loginPage与loginSuccessUrl都配置到了同一个UsernamePasswordAuthenticationFilter里

5. 自己写一个简单的过滤器,添加到安全链中
提示:addFilterBefore与addFilterAfter()

自己写的过滤器,调用chain.doFilter是什么情况,不调用又会是什么情况?


6. http.authorizeRequest().antMatchers("/admin").authenticated()
.antMatchers("/admin").permitAll();
请求访问/admin地址到底是permitAll这种情况还是authenticated的情况

请求访问/admin地址是permitAll

----------------------------------------

7.实战作业:做一个校验码的过滤器,并添加到安全链中


8. 配置多条链出来.
一条链是在浏览器登录,并重定向
一条链是在rest客户端可以登录





    