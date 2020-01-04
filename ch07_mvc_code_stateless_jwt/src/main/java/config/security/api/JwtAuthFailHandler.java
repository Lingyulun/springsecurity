package config.security.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vo.ResponseVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 挥霍的人生
 */
public class JwtAuthFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResponseVO vo=new ResponseVO();
        vo.setCode("500");
        vo.setMsg("login failed");
        response.setContentType("application/json;charset=UTF-8");
        ObjectMapper mapper=new ObjectMapper();

        mapper.writeValue(response.getOutputStream(),vo);
    }
}
