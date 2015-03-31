<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
	<body>

		<h1>${hdr}</h1>
		<form action="/SpringWebMVC/homepage/${details.id}" method="post">
			<c:if test="${json == 'yes'}">
				<pre>
					{	
						"id":"${details.id}", 
						"firstname":"${details.firstname}",
						"lastname":"${details.lastname}",
						"email":"${details.email}",
						"address":"${details.address}",
						"organization":"${details.organization}",
						"aboutMyself":"${details.aboutMyself}"
					}
				</pre>
			</c:if>
		<c:if test="${json=='no' }">
		<table>
		<tr>
			<td> ID: </td><td><label> ${details.id}</label></td> 
		</tr>
		<tr>
			<td> First Name: </td><td><input type="text" name = "firstname" value=${details.firstname} ></td> 
		</tr>
		<tr>
			<td> Last Name: </td><td><input type="text" name = "lastname" value=${details.lastname}></td> 
		</tr>
		<tr>
			<td> Email: </td><td><input type="text" name = "email" value=${details.email}></td> 
		</tr>
		<tr>
			<td> Address: </td><td><input type="text" name = "address" value=${details.address}></td> 
		</tr>
		<tr>
			<td> Organization: </td><td><input type="text" name = "organization" value=${details.organization}></td> 
		</tr>
		<tr>
			<td> About Myself: </td><td><input type="text" name = "aboutMyself" value=${details.aboutMyself}></td> 
		</tr>
		
		
		</table>
		
		<input type="hidden" name="id" value="${details.id}">
		<input type="hidden" name="firstname" value="${details.firstname}">
		<br>
		<input type="submit" value = "update" />
		</c:if>
		</form>
		<c:if test="${json=='no' }">
		<form action="/SpringWebMVC/homepage/${details.id}" method="post">
			<input type="hidden" name="_method" value="delete"/>
			<input type="submit" path="Delete" value = "Delete" />
		</form>
		<form action="/SpringWebMVC/homepage/${details.id}" method="get">
			<input type="hidden" name="brief" value="true" />
			<input type="submit" value = "Get Json" />
		</form>
		</c:if>
		
	</body>

</html>