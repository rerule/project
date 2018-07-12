<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>page User</title>
<style type="text/css">
table{
	border-collapse:collapse;
}
tbody,tbody tr{
	border-collapse:collapse;
	border: 1px solid #8c8e0a;	
}

tbody tr td{
	border-collapse:collapse;
	border: 1px solid #8c8e0a;	
}
</style>
</head>
<body>
	<table>
		<tbody>
			<tr>
				<td>id</td>
				<td>loginname</td>
				<td>password</td>
				<td>username</td>
				<td>phone</td>
				<td>address</td>
			</tr>
			<c:forEach var="user" items="${requestScope.userList }">
				<tr>
					<td>${user.id}</td>
					<td>${user.loginname}</td>
					<td>${user.password }</td>
					<td>${user.username }</td>
					<td>${user.phone }</td>
					<td>${user.address }</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr >
			<td colspan="6">
			<div>
			<a href="${pageContext.request.contextPath}/page/now?currentPage=${requestScope.page.prePage}">前一页</a>
			<a href="${pageContext.request.contextPath}/page/now?currentPage=${requestScope.page.nextPage}">下一页</a>
			</div>
			</td>
			</tr>
		</tfoot>
	</table>
	
	
</body>
</html>