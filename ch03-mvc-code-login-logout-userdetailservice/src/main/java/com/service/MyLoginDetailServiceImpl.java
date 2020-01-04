package com.service;

import com.dao.LoginDao;
import com.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;

/**
 * @author 挥霍的人生
 */
@Service
public class MyLoginDetailServiceImpl implements UserDetailsService{

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LoginDao loginDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login=loginDao.loginAll(username);
        if(StringUtils.isEmpty(login)){
            throw new UsernameNotFoundException("不是合法用户名");
        }
        List<GrantedAuthority> authorities =
                AuthorityUtils.createAuthorityList("xxx", "yyy");
        return new User(login.getUsername(),
                passwordEncoder.encode(login.getPassword()),authorities);

    }
    public Login loginAll(String username) {
        Login login=loginDao.loginAll(username);
        return login;
    }
}
