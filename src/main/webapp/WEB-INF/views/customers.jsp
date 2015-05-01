<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Customers List</title>
		<style type="text/css">
			.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
			.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
			.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
			.tg .tg-4eph{background-color:#f9f9f9}
		</style>
	</head>
	<body>
	<h3>Customers List</h3>
		<c:if test="${!empty listCustomers}">
			<table class="tg">
			<tr>
				<th width="80">ID</th>
				<th width="120">First Name</th>
				<th width="120">Last Name</th>
				<th width="120">Email</th>
				<th width="120">Birthdate</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${listCustomers}" var="customer">
				<tr>
					<td>${customer.id}</td>
					<td>${customer.firstName}</td>
					<td>${customer.lastName}</td>
					<td>${customer.email}</td>
					<td>${customer.birthdate}</td>
					<td><a href="<c:url value='/edit/${customer.id}' />" >Edit</a></td>
					<td><a href="<c:url value='/remove/${customer.id}' />" >Delete</a></td>
				</tr>
			</c:forEach>
			</table>
		</c:if>
		<br/>
		<a href="<c:url value='/read' />" >Read XML file</a>
		<br/>
		<br/>
		<a href="<c:url value='/add' />" >Add a customer</a>
		<br/>
		<br/>
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<a href="<c:url value="/j_spring_security_logout" />" >Logout</a>
		</c:if>
	</body>
</html>
