<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="topBar">
<div class="topBox f-cb">
<a href="${pageContext.request.contextPath}/jsp/index.jsp" class="topLogo"></a>
<p class="topBoxlist">
<a href="${pageContext.request.contextPath}/jsp/index.jsp" target="_blank"><i></i>首页</a><span>&middot;</span>
<a href="http://my.zhaouc.com/" target="_blank"><i></i>直播</a><span>&middot;</span>
<a href="http://trpay.zhaouc.com" target="_blank"><i></i>视频</a><span>&middot;</span>
<a href="http://kf.zhaouc.com/" target="_blank"><i></i>冰柜</a><span>&middot;</span>

<c:if test="${empty username}">
<a href="${pageContext.request.contextPath}/jsp/login.jsp" target="_blank"><i></i>注册登录</a>
</c:if>

<c:if test="${username!=null}">
<a href="">Hi,${username}</a>
<a href="${pageContext.request.contextPath }/userCenter.action"><img src="../img/pubg.png" style="height:50%"/></a>
<a href="${pageContext.request.contextPath }/logout.action">退出</a>
</c:if>

</p>



</div>
</div>

</body>
</html>