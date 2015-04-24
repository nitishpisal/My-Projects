<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<script type="text/javascript">
	
	function sponsor(){
		var regForm = document.forms['navigate'];
		regForm.method = "GET";
		regForm.action = "http://localhost:8080/Poker/register/sponsor";
		regForm.submit();
	}
	
	function login(){
		var regForm = document.forms['navigate'];
		regForm.method = "GET";
		regForm.action = "http://localhost:8080/Poker/login";
		regForm.submit();
	}
	
	function books(){
		var regForm = document.forms['navigate'];
		regForm.method = "GET";
		regForm.action = "http://localhost:8080/Poker/books";
		regForm.submit();
	}
	
	function player(){
		var regForm = document.forms['navigate'];
		regForm.method = "GET";
		regForm.action = "http://localhost:8080/Poker/register/player";
		regForm.submit();
	}
	
	function getPlayer(){
		var id = document.getElementById("id").value;
		var regForm = document.forms['navigate'];
		regForm.method = "GET";
		regForm.action = "http://localhost:8080/Poker/player/" + id;
		regForm.submit();
	}
	
	function updatePlayer(){
		var id = document.getElementById("id2").value;
		var regForm = document.forms['navigate'];
		regForm.method = "GET";
		regForm.action = "http://localhost:8080/Poker/update/player/" + id;
		regForm.submit();
	}
	
	function getSponsor(){
		var id = document.getElementById("id3").value;
		var regForm = document.forms['navigate'];
		regForm.method = "GET";
		regForm.action = "http://localhost:8080/Poker/sponsor/" + id;
		regForm.submit();
	}
	
	function updateSponsor(){
		var id = document.getElementById("id4").value;
		var regForm = document.forms['navigate'];
		regForm.method = "GET";
		regForm.action = "http://localhost:8080/Poker/update/sponsor/" + id;
		regForm.submit();
	}
	
	function deletePlayer(){
		var id = document.getElementById("id5").value;
		var regForm = document.forms['deleteEntity'];
		regForm.method = "POST";
		regForm.action = "http://localhost:8080/Poker/player/" + id;
		regForm.submit();
	}
	
	function deleteSponsor(){
		var id = document.getElementById("id6").value;
		var regForm = document.forms['deleteEntity'];
		regForm.method = "POST";
		regForm.action = "http://localhost:8080/Poker/sponsor/" + id;
		regForm.submit();
	}
	
	function createOpponent(){
		var id1 = document.getElementById("id7").value;
		var id2 = document.getElementById("id8").value;
		var regForm = document.forms['createOpponent'];
		regForm.method = "POST";
		regForm.action = "http://localhost:8080/Poker/opponents/" + id1 + "/" + id2;
		regForm.submit();
	}
	

	function deleteOpponent(){
		var id1 = document.getElementById("id9").value;
		var id2 = document.getElementById("id10").value;
		var regForm = document.forms['deleteOpponent'];
		regForm.method = "POST";
		regForm.action = "http://localhost:8080/Poker/opponents/" + id1 + "/" + id2;
		regForm.submit();
	}
	
</script>

	<body>
		<h1>Please choose your option</h1>
		<form:form modelAttribute="navigate">
			<table>
				<!-- <tr>
						<td style="border: 0;">
						<input type="button" value="Sponsor Register"
							onclick="javascript:sponsor();"></td>
				</tr> -->
				<tr>
						<td style="border: 0;">
						<input type="button" value="Login"
							onclick="javascript:login();"></td>
				</tr>
				<tr>
						<td style="border: 0;">
						<input type="button" value="Books"
							onclick="javascript:books();"></td>
				</tr>
				<tr>
						<td style="border: 0;"><input type="button" value="Player Register"
							onclick="javascript:player();"></td>
				</tr>
				<tr>
					<td> Id: </td>
					<td><input type="text" id="id"/></td>
					<td style="border: 0;"><input type="button" value="Get Player"
							onclick="javascript:getPlayer();"></td> 
				</tr>
				<tr>
					<td> Id: </td>
					<td><input type="text" id="id2"/></td> 
					<td style="border: 0;"><input type="button" value="Update Player"
							onclick="javascript:updatePlayer();"></td>
				</tr>
				<tr>
					<td> Id: </td>
					<td><input type="text" id="id3"/></td> 
					<td style="border: 0;"><input type="button" value="Get Sponsor"
							onclick="javascript:getSponsor();"></td>
				</tr>
				<tr>
					<td> Id: </td>
					<td><input type="text" id="id4"/></td>
					<td style="border: 0;"><input type="button" value="Update Sponsor"
							onclick="javascript:updateSponsor();"></td>
				</tr>
		</table>
	</form:form>
	
		<form:form modelAttribute="deleteEntity">
			<table>
				<input type="hidden" name="_method" value="delete"/>
				<tr>
					<td> Id: </td>
					<td><input type="text" id="id5"/></td> 
					<td style="border: 0;">
						<input type="button" path="Delete" value = "Delete Player" 
						onclick="javascript:deletePlayer();">
					</td>
				</tr>
				<tr>
					<td> Id: </td><td><input type="text" id="id6"/></td> 
					<td style="border: 0;">
						<input type="button" path="Delete" value = "Delete Sponsor" 
						onclick="javascript:deleteSponsor();">
					</td>
				</tr>
			</table>
		</form:form>
		
		<form:form modelAttribute="createOpponent">
			<table>
				<input type="hidden" name="_method" value="put"/>
				<tr>
					<td> Player 1: </td>
					<td><input type="text" id="id7"/></td> 
					<td> Player 2: </td>
					<td><input type="text" id="id8"/></td> 
				</tr>
				<tr>
					
					<td style="border: 0;">
						<input type="button" path="Put" value = "Create Opponent" 
						onclick="javascript:createOpponent();">
					</td>
				</tr>
			</table>
		</form:form>
		
		<form:form modelAttribute="deleteOpponent">
			<table>
				<input type="hidden" name="_method" value="delete"/>
				<tr>
					<td> Player 1: </td>
					<td><input type="text" id="id9"/></td> 
					<td> Player 2: </td>
					<td><input type="text" id="id10"/></td> 
				</tr>
				<tr>
					
					<td style="border: 0;">
						<input type="button" path="Delete" value = "Delete Opponent" 
						onclick="javascript:deleteOpponent();">
					</td>
				</tr>
			</table>
		</form:form>
		
	</body>

</html>