<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>HTML Registration Form Template | SliceMaker Demo</title>
	<meta name="keywords" content="html registration form, html registration form free download, html registration form template">
	<meta name="description" content="This is a free HTML Registration Form Template created by SliceMaker Soft. You can free download this HTML registration form template and then edit it for your own use.">
	<link rel="stylesheet" href="/Ebuy_Inventory/CSS/loginStyle.css" />        
	<script type="text/javascript">
		window.onload=function(){
			var oForm=document.getElementById('form-control');
			var aLabel=oForm.getElementsByTagName('label');
			var aInput=oForm.getElementsByTagName('input')
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
</head>
	<body>
		<div class="page">
		
			<section class="demo">
		
		
				<div class='login clearfix'>
					<h2>Login </h2>
					<form name = 'loginform' method='post' action='validate' id='form-control' >
					
						<div class='control-round' id='control-round'>
							<label for='uName' class='active'>1</label>
							<label for='password'>2</label>
							<label for='btn1'>go</label>
						</div>
						<div class='control-group'>
							<input type='text' name='username' id='uName' placeholder='User Name'>
						</div>
						<div class='control-group'>
							<input type='password' name='password' id='password' placeholder='Password'>
						</div>
							<div class='form-actions'>
								<button class='btn' id='btn1' type='submit' >Sign In</button>
								<button class='btn' id='btn2' type='button' onclick="location.href='signup.jsp';" >Register</button>
							</div>	
					</form>
				</div> 
			</section>
		</div>
	</body>
</html>