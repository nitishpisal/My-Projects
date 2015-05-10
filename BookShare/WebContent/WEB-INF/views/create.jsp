<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<script type="text/javascript">
	function create() {
		//var userId = document.getElementById("pageId").value;
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var firstname = document.getElementById("firstname").value;
		var lastname = document.getElementById("lastname").value;
		var email = document.getElementById("email").value;
		var street = document.getElementById("street").value;
		var city = document.getElementById("city").value;
		var description = document.getElementById("description").value;
		var state = document.getElementById("state").value;
		var zip = document.getElementById("zip").value;
		var doi = document.getElementById("doi").value;
		var regForm = document.forms['playerRegisterationForm'];
		regForm.method = "POST";
		console.log(regForm.action);
		regForm.action = "<%=request.getContextPath()%>/player?"
				+ "firstname=" + firstname + "&lastname=" + lastname
				+ "&email=" + email + "&street=" + street + "&city=" + city
				+ "&description=" + description + "&state=" + state + "&zip=" + zip
				+ "&username=" + username + "&password=" + password
				+ "&doi=" + doi;
			console.log(regForm.action);
			regForm.submit();
	}
	
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
		regForm.action = "<%=request.getContextPath()%>/sponsor?"
				+ "name=" + name 
				+ "&description=" + description 
				+ "&street=" + street 
				+ "&city=" + city
				+ "&state=" + state 
				+ "&zip=" + zip;
		
			console.log(regForm.action);
			regForm.submit();
	}
	
	
</script>


	<body>
	<c:if test="${who == 'player'}">
		<h1>Hello user please enter your details</h1>
		<form:form modelAttribute="playerRegisterationForm">
		<table>
		<tr>
			<td> User Name: </td><td><input type="text" id="username"/></td> 
		</tr>
		<tr>
			<td> Password: </td><td><input type="password" id="password"/></td> 
		</tr>
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
		<!-- <tr>
			<td> Sponsor: </td><td><input type="text" id="sponsor"/></td> 
		</tr> -->
		<tr>
			<select id="doi">
				<option value="dealer">Dealer</option>
				<option value="individual">Individual</option>
			</select>
		</tr>
		<tr>
				<td style="border: 0;"><input type="button" value="Register"
					onclick="javascript:create();"></td>
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