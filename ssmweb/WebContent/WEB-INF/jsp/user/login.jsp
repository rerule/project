<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/login" method="post">  
        <table>  
            <tr>  
                <td><label>登录名：</label></td>  
                <td><input type="text" id="loginname" name="loginname"></td>  
            </tr>  
            <tr>  
                <td><label>密码：</label></td>  
                <td><input type="password" id="password" name="password"></td>  
            </tr>  
              
            <tr>  
                <td><input id="submit" type="submit" value="登陆"/></td>  
            </tr>  
        </table>
          
    </form>
    <button type="button"><a href="${pageContext.request.contextPath}/user/toRegister">注册</a></button>
</body>
</html>