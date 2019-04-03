<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改商品信息</title>

</head>
<body> 

<!-- 显示错误信息 -->
	<c:if test="${allErrors!=null }">
		<c:forEach items="${allErrors }" var="error">
		${ error.defaultMessage}<br/>
	</c:forEach>
	</c:if>

<form id="typeForm" action="${pageContext.request.contextPath }/type/editTypeSubmit.action" method="post" enctype="multipart/form-data">
<input type="hidden" name="typeid" value="${typeCustom.typeid }"/>
修改商品信息：
<table width="100%" border=1>
<tr>
	<td>分类ID</td>
	<td><input type="text" name="typeid" value="${typeCustom.typeid }"/></td>
</tr>
<tr>
	<td>分类名称</td>
	<td><input type="text" name="type" value="${typeCustom.type }"/></td>
</tr>
<%--<tr>
	<td>商品生产日期</td>
	<td><input type="text" name="createtime" value="<fmt:formatDate value="${typeCustom.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/></td>
</tr>
--%>
 <tr>
	<td>商品图片</td>
	<td>
		<%--<c:if test="${item.pic !=null}">
			<img src="/pic/${item.pic}" width=100 height=100/>
			<br/>
		</c:if>--%>
		<input type="file"  name="pic"/> 
	</td>
</tr> 
<tr>
	<td>商品简介</td>
	<td>
	<textarea rows="3" cols="30" name="detail">${typeCustom.typeid }</textarea>
	</td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="提交"/>
</td>
</tr>
</table>

</form>
</body>

</html>