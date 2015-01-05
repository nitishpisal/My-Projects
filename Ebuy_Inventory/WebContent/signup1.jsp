<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>HTML Registration Form Template | SliceMaker Demo</title>
	<meta name="keywords" content="html registration form, html registration form free download, html registration form template">
	<meta name="description" content="This is a free HTML Registration Form Template created by SliceMaker Soft. You can free download this HTML registration form template and then edit it for your own use.">
	
	<link rel="stylesheet" href="/Ebuy_Inventory/CSS/loginStyle.css" />
	
	<link rel="stylesheet" href="/Ebuy_Inventory/CSS/js.css" />
	<!--  <script src="http://code.jquery.com/jquery-1.9.1.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" type="text/javascript"></script>  -->
	<script src="/Ebuy_Inventory/JavaScript/jquery.js" type="text/javascript"></script>
	<script src="/Ebuy_Inventory/JavaScript/jquery-ui.js" type="text/javascript"></script>
	<script type="text/javascript">
		window.onload=function(){
			var oForm=document.getElementById('form-control');
			var aLabel=oForm.getElementsByTagName('label');
			var aInput=oForm.getElementsByTagName('input');
		
			for(var i=0;i<aLabel.length;i++){
				aInput[i].index=i;
				aLabel[i].onclick=function(){
					for(var i=0;i<aLabel.length;i++){
						aLabel[i].className='';	
					}
					this.className='active';
				}
				aInput[i].onclick=function(){
					for(var i=0;i<aLabel.length;i++){
						aLabel[i].className='';	
					}
					aLabel[this.index].className='active';
				}
			}
		}
	
	</script>
	<script>
	  $(function() {
	    $( "#date" ).datepicker({dateFormat: 'yy-mm-dd'});
	  });
  </script>
</head>
	<body>
		<div class="page">
		
			<section class="demo">
	
				<div class='login clearfix'>
					<h2>Register Now!</h2>
					<form method='post' action='register' id='form-control' >

						<div class='control-group'>
							<input type='text' name='email' id='email' placeholder='Email Address'>
						</div>
						<div class='control-group'>
							<input type='text' name='username' id='uName' placeholder='Choose User Name'>
						</div>
						<div class='control-group'>
							<input type='number' name='phNumber' id='phNumber' placeholder='Phone Number(Optional)'>
						</div>
						<div class='control-group'>
							<input type='text' name='dateOfBirth' id='date' placeholder='Date of Birth'>
						</div>
						<div class='control-group'>
							<input type='password' name='password' id='sPassword' placeholder='Password'>
						</div>
						<div class='control-group'>
							<input type='password' name='verifyPassword' id='vpassword' placeholder='Verify Password'>
						</div>
						
							<div class='form-actions'>
							
								<button class='btn' type='submit' id='btn1'>Sign Up</button>
								<button class='btn' type='reset' >Clear</button>
								<p>
								<h4>Alredy a member?</h4>
								</p>
								<button class='btn' type='button' id='btn1' onclick="location.href='login.jsp';">Login</button>	
							</div>
					</form>
				</div> 
			</section>
		</div>
	</body>
</html>