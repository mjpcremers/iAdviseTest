<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Customer</title>
	<style>
		.error {
		    color: #ff0000;
		    font-style: italic;
		    font-weight: bold;
		}
	</style>
</head>
<body>
<h1>
	<c:if test="${customer.id==0}">
	Add customer
	</c:if>
	<c:if test="${customer.id!=0}">
	Edit customer
	</c:if>
</h1>

<c:url var="addAction" value="/customer/add" ></c:url>

<form:form action="${addAction}" commandName="customer">
<table>
	<c:if test="${customer.id!=0}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
		<td><form:errors path="id" cssClass="error" /></td>
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="firstName">
				<spring:message text="First name"/>
			</form:label>
		</td>
		<td>
			<form:input path="firstName" />
		</td> 
		<td><form:errors path="firstName" cssClass="error" /></td>
	</tr>
	<tr>
		<td>
			<form:label path="lastName">
				<spring:message text="Last name"/>
			</form:label>
		</td>
		<td>
			<form:input path="lastName" />
		</td> 
		<td><form:errors path="lastName" cssClass="error" /></td>
	</tr>
	<tr>
		<td>
			<form:label path="email">
				<spring:message text="Email"/>
			</form:label>
		</td>
		<td>
			<form:input path="email" />
		</td>
		<td><form:errors path="email" cssClass="error" /></td>
	</tr>
	<tr>
		<td>
			<form:label path="birthdate">
				<spring:message text="Birthdate"/>
			</form:label>
		</td>
		<td>
			<form:input path="birthdate" />
		</td>
		<td><form:errors path="birthdate" cssClass="error" /></td>
	</tr>
	<tr>
		<td colspan="3">
			<input type="submit"
				value="<spring:message text="Save"/>" />
		</td>
	</tr>
</table>	
</form:form>

</body>
</html>
