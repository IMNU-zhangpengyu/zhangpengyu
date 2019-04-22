<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="../css/index.css" type="text/css">
<script type="text/javascript" src="../js/jquery.min.js"></script>

</head>
<body>

<%@ include file="/jsp/header.jsp" %>

<form action="${pageContext.request.contextPath }/login.action" method="post">
用户账号：<input type="text" name="username" /><br/>
用户密码 ：<input type="password" name="password" /><br/>
<input type="submit" value="登陆"/>


</form>
</body>
</html>