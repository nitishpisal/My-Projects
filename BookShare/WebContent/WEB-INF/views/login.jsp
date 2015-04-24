<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<script type="text/javascript">
	
	function login(){
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var regForm = document.forms['navigate'];
		regForm.method = "POST";
		regForm.action = "http://localhost:8080/Poker/login?username="+username
				+ "&password=" + password;
		regForm.submit();
	}

</script>

<body>
	<h1>Please choose your option</h1>
		<form:form modelAttribute="navigate">
			<table>
				<tr>
					<td>	username: </td>
					<td>	<input type="text" id="username"> </td>
				</tr>
				<tr>
					<td>	Password: </td>
					<td>	<input type="password" id="password"> </td>
				</tr>
				<tr>
						<td style="border: 0;"><input type="button" value="Login"
							onclick="javascript:login();"></td>
				</tr>
			</table>
		</form:form>