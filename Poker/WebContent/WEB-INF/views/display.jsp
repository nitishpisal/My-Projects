<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
	<body>
		<c:if test="${who == 'player'}">
			<pre>
				{	
					"id":"${player.id}", 
					"firstname":"${player.firstname}",
					"lastname":"${player.lastname}",
					"email":"${player.email}"
				}
			</pre>
		</c:if>
		
		<c:if test="${who == 'sponsor'}">
			<pre>
				{	
					"id":"${details.id}", 
					"name":"${details.name}",
					"Description":"${details.description}"
				}
			</pre>
		</c:if>
	</body>
</html>