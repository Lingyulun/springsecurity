package config.security.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import vo.ResponseVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 挥霍的人生
 */

public class RestAuthFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResponseVO vo=new ResponseVO();
       vo.setCode("500");
        vo.setData("login fail");

        response.setContentType("application/json;charset=UTF-8");

        ObjectMapper objectMapper=new ObjectMapper();
        //得到对象的输出流
        objectMapper.writeValue(response.getOutputStream(),vo);
    }
}
