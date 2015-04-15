<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<script type="text/javascript">
	function createPlayer() {
		//var userId = document.getElementById("pageId").value;
		var name = document.getElementById("name").value;
		var description = document.getElementById("description").value;
		var street = document.getElementById("street").value;
		var city = document.getElementById("city").value;
		var state = document.getElementById("state").value;
		var zip = document.getElementById("zip").value;
		var regForm = document.forms['sponsorRegistration'];
		regForm.method = "POST";
		console.log(regForm.action);
		regForm.action = "http://localhost:8080/Poker/sponsor?"
				+ "name=" + name 
				+ "&description=" + description 
				+ "&street=" + street 
				+ "&city=" + city
				+ "&state=" + state 
				+ "&zip=" + zip;
		
		if(name == "")
			alert("Please enter your name");
		else{
			console.log(regForm.action);
			regForm.submit();
			alert("Sponsor created...");
		}
	}
</script>


	<body>
		<h1>Hello Sponsor please enter your details</h1>
		<form:form modelAttribute="sponsorRegistration">
		<%-- <form action="/Poker/player" method="post"> --%>
		<table>
		<tr>
			<td> Name: </td><td><input type="text" id="name"/></td> 
		</tr>
		<tr>
			<td> Description: </td><td><input type="text" id="description"/></td> 
		</tr>		
		<tr>
			<td> Street: </td><td><input type="text" id="street"/></td> 
		</tr>
		<tr>
			<td> City: </td><td><input type="text" id="city"/></td> 
		</tr>
		<tr>
			<td> State: </td><td><input type="text" id="state"/></td> 
		</tr>
		<tr>
			<td> Zip: </td><td><input type="text" id="zip"/></td> 
		</tr>
		<tr>
				<td style="border: 0;"><input type="button" value="Create"
					onclick="javascript:createPlayer();"></td>
		</tr>
		</table>
		<br>
	</form:form>
		
	</body>

</html>