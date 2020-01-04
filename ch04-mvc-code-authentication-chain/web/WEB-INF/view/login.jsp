<%--
  Created by IntelliJ IDEA.
  User: 挥霍的人生
  Date: 2019/12/31
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <script>
        // 刷新验证码
        function  refreshImg() {
            document.getElementById("imgCode").src = "${path}/ImageCode/showCode?timestamp="+(new Date()).getTime();
        }
    </script>
</head>
<body>
    <form action="/authentication/form" method="post">
        账户：<input type="text" name="username" /> <br>
        密码：<input type="text" name="password" /> <br>
        验证码： <input type="text" maxlength="4" name="txtCode" id="txtCode"  placeholder="输入验证码">
        <img src="${path}/ImageCode/showCode" alt="点击切换" id="imgCode" onclick="refreshImg()"/><br/><br/>
        <input type="submit" value="登录">
    </form>
</body>
</html>
