<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<script type="text/javascript">
	function updatePlayer() {
		//var userId = document.getElementById("pageId").value;
		var id = document.getElementById("id").value;
		var firstname = document.getElementById("firstname").value;
		var lastname = document.getElementById("lastname").value;
		var email = document.getElementById("email").value;
		var street = document.getElementById("street").value;
		var city = document.getElementById("city").value;
		var state = document.getElementById("state").value;
		var zip = document.getElementById("zip").value;
		var description = document.getElementById("description").value;
		var sponsor = document.getElementById("sponsor").value;
		var regForm = document.forms['updatePlayer'];
		regForm.method = "POST";
		console.log(regForm.action);
		regForm.action = "http://localhost:8080/Poker/player/" + id + "?"
		+ "firstname=" + firstname + "&lastname=" + lastname
		+ "&email=" + email + "&street=" + street + "&city=" + city
		+ "&state=" + state + "&zip=" + zip + "&description=" + description
		+ "&sponsor=" + sponsor;
		
		if(firstname == "" || email == "" || lastname =="" )
			alert("Please enter your name");
		else{
			console.log(regForm.action);
			regForm.submit();
			alert("Player Updated...");
		}
	}
	
	function updateSponsor() {
		//var userId = document.getElementById("pageId").value;
		
		var id = document.getElementById("id").value;
		var name = document.getElementById("name").value;
		var description = document.getElementById("description").value;
		var street = document.getElementById("street").value;
		var city = document.getElementById("city").value;
		var state = document.getElementById("state").value;
		var zip = document.getElementById("zip").value;
		var regForm = document.forms['updateSponsor'];
		regForm.method = "POST";
		console.log(regForm.action);
		regForm.action = "http://localhost:8080/Poker/sponsor/" + id + "?"
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
			alert("Sponsor updated...");
		}
	}
	
</script>
	<body>
		<c:if test="${valid == 'yes' }">
		<h1>${hdr}</h1>
		<c:if test="${who == 'player'}">
		<form:form modelAttribute="updatePlayer">
			<table>
			<tr>
				<td> ID: </td><td><label> ${details.getId()}</label></td> 
			</tr>
			<tr>
				<td><input type="hidden" id = "id" value="${details.getId()}" ></td>
			</tr>
			<tr>
				<td> First Name: </td><td><input type="text" id = "firstname" value="${details.getFirstname()}" ></td> 
			</tr>
			<tr>
				<td> Last Name: </td><td><input type="text" id = "lastname" value="${details.getLastname()}"></td> 
			</tr>
			<tr>
				<td> Email: </td><td><input type="text" id = "email" value="${details.getEmail()}"></td> 
			</tr>
			<tr>
				<td> Description: </td><td><input type="text" id="description" value="${details.getDescription()}"></td> 
			</tr>		
			<tr>
				<td> Street: </td><td><input type="text" id="street" value="${details.getAddress().getStreet()}" ></td> 
			</tr>
			<tr>
				<td> City: </td><td><input type="text" id="city" value="${details.getAddress().getCity()}"></td> 
			</tr>
			<tr>
				<td> State: </td><td><input type="text" id="state" value="${details.getAddress().getState()}"></td> 
			</tr>
			<tr>
				<td> Zip: </td><td><input type="text" id="zip" value="${details.getAddress().getZip()}"></td> 
			</tr>
			<tr>
				<td> Sponsor: </td><td><input type="text" id="sponsor" value="${details.getSponsor().getId()}"></td> 
			</tr>
			<tr>
					<td style="border: 0;"><input type="button" value="Update"
						onclick="javascript:updatePlayer();"></td>
			</tr>
			
			</table>
		
		</form:form>
		</c:if>
	</c:if>
	<c:if test="${valid == 'yes'}">
	<c:if test="${who == 'sponsor'}">
		<form:form modelAttribute="updateSponsor">
		
			<table>
			<tr>
				<td> ID: </td><td><label> ${details.getId()}</label></td> 
			</tr>
			<tr>
				<td><input type="hidden" id = "id" value="${details.getId()}" ></td>
			</tr>
			<tr>
				<td> Name: </td><td><input type="text" id = "name" value="${details.getName()}" ></td> 
			</tr>
			<tr>
				<td> Description: </td><td><input type="text" id="description" value="${details.getDescription()}"></td> 
			</tr>		
			<tr>
				<td> Street: </td><td><input type="text" id="street" value="${details.getAddress().getStreet()}" ></td> 
			</tr>
			<tr>
				<td> City: </td><td><input type="text" id="city" value="${details.getAddress().getCity()}"></td> 
			</tr>
			<tr>
				<td> State: </td><td><input type="text" id="state" value="${details.getAddress().getState()}"></td> 
			</tr>
			<tr>
				<td> Zip: </td><td><input type="text" id="zip" value="${details.getAddress().getZip()}"></td> 
			</tr>
			<tr>
					<td style="border: 0;"><input type="button" value="Update"
						onclick="javascript:updateSponsor();"></td>
			</tr>
			
			</table>
		
		</form:form>
		</c:if>
	</c:if>
	<c:if test="${valid == 'no' }">
		404 Not Found
	</c:if>
	</body>

</html>