package com.dao;

import com.entity.Login;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 挥霍的人生
 */
@Repository
public class LoginDao {
   public Login loginAll(String username){
       return new Login();
   }
}
