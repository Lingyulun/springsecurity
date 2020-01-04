package config.security;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 挥霍的人生
 */
public class BFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    /*
    * chain.doFilter(request, response);
    * 过滤器的作用就是之一就是在用户的请求到达servlet之前，
    * 拦截下来做预处理，处理之后便执行chain.doFilter(request, response)这个方法，
    * 如果还有别的过滤器，那么将处理好的请求传给下个过滤器，依此类推，当所有的过滤器都把这个请求处理好了之后，再将处理完的请求发给servlet；如果就这一个过滤器，那么就将处理好的请求直接发给servlet
    * */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤器拦截-------");
        chain.doFilter(request,response);
        System.out.println("回来执行doFilter方法。。。");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁---");
    }

}
