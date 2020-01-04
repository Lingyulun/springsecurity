package config.security.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vo.ResponseVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 挥霍的人生
 */
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseVO vo=new ResponseVO();
        vo.setCode("403");
        vo.setMsg("你还没有登录或者权限不够");
        ObjectMapper mapper=new ObjectMapper();
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(httpServletResponse.getOutputStream(),vo);
    }
}
