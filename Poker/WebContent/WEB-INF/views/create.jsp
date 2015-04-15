<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<script type="text/javascript">
	function createPlayer() {
		//var userId = document.getElementById("pageId").value;
		var firstname = document.getElementById("firstname").value;
		var lastname = document.getElementById("lastname").value;
		var email = document.getElementById("email").value;
		var street = document.getElementById("street").value;
		var city = document.getElementById("city").value;
		var description = document.getElementById("description").value;
		var state = document.getElementById("state").value;
		var zip = document.getElementById("zip").value;
		var sponsor = document.getElementById("sponsor").value;
		var regForm = document.forms['playerRegisterationForm'];
		regForm.method = "POST";
		console.log(regForm.action);
		regForm.action = "http://localhost:8080/Poker/player?"
				+ "firstname=" + firstname + "&lastname=" + lastname
				+ "&email=" + email + "&street=" + street + "&city=" + city
				+ "&description=" + description + "&state=" + state + "&zip=" + zip
				+ "&sponsor=" + sponsor;
		/* regForm.action = "http://localhost:8080/Poker/player?"
					+ "firstname=" + firstname + "&lastname=" + lastname
					+ "&email=" + email + "&description=" + description; */
		/* if(email == "" || firstname == "" || lastname == "")
			alert("Email, Firstname or Lastname cannot be null");
		else{ */
			console.log(regForm.action);
			regForm.submit();
			//alert("Player created...");
		//}
	}
	
	/* function createSponsor(){
		var regForm = document.forms['createSponsor'];
		regForm.method = "POST";
		regForm.action = "http://localhost:8080/Poker/register/sponsor";
		regForm.submit();
	} */
	
	function createSponsor() {
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
		
		/* if(name == "")
			alert("Please enter your name");
		else{ */
			console.log(regForm.action);
			regForm.submit();
		/* 	alert("Sponsor created...");
		} */
	}
	
	
</script>


	<body>
	<c:if test="${who == 'player'}">
		<h1>Hello user please enter your details</h1>
		<form:form modelAttribute="playerRegisterationForm">
		<table>
		<tr>
			<td> First Name: </td><td><input type="text" id="firstname"/></td> 
		</tr>
		<tr>
			<td> Last Name: </td><td><input type="text" id="lastname"/></td> 
		</tr>
		<tr>
			<td> Email: </td><td><input type="text" id="email"/></td> 
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
			<td> Sponsor: </td><td><input type="text" id="sponsor"/></td> 
		</tr>
		<tr>
				<td style="border: 0;"><input type="button" value="Register"
					onclick="javascript:createPlayer();"></td>
		</tr>
		
		</table>
		<br>
		<!-- <input type="submit" value = "Create" /> -->
	<%-- 	</form> --%>
	</form:form>
	</c:if>
	<c:if test="${who == 'sponsor'}">
		<h1>Hello Sponsor please enter your details</h1>
		<form:form modelAttribute="sponsorRegistration">
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
				<td style="border: 0;"><input type="button" value="Register"
					onclick="javascript:createSponsor();"></td>
		</tr>
		</table>
		<br>
	</form:form>
	</c:if>
		
		
	</body>

</html>