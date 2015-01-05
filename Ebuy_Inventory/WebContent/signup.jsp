
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Ebuy | Register</title>

    <!-- Bootstrap Core CSS -->
    <link href="/Ebuy_Inventory/CSS/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/Ebuy_Inventory/CSS/half-slider.css" rel="stylesheet">
    <link href="/Ebuy_Inventory/CSS/login-style_1.css" rel="stylesheet">
    <link rel="stylesheet" href="/Ebuy_Inventory/CSS/js.css" />
    <script src="/Ebuy_Inventory/JavaScript/jquery.js" type="text/javascript"></script>
	<script src="/Ebuy_Inventory/JavaScript/jquery-ui.js" type="text/javascript"></script>

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="http://localhost:4000/">Home</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#">About</a>
                    </li>
                    <li>
                        <a href="#">Services</a>
                    </li>
                    <li>
                        <a href="#">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Half Page Image Background Carousel Header -->
    <header id="myCarousel" class="carousel slide">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for Slides -->
        <div class="carousel-inner">
            <div class="item active">
                <!-- Set the first background image using inline CSS below. -->
                <div class="fill" style="background-image:url('/Ebuy_Inventory/img/carjsp.jpg');"></div>
                <div class="carousel-caption">
                    
                </div>
            </div>
            <div class="item">
                <!-- Set the second background image using inline CSS below. -->
                <div class="fill" style="background-image:url('/Ebuy_Inventory/img/wifijsp.jpg');"></div>
                <div class="carousel-caption">
                    
                </div>
            </div>
            <div class="item">
                <!-- Set the third background image using inline CSS below. -->
                <div class="fill" style="background-image:url('/Ebuy_Inventory/img/tvjsp.jpg');"></div>
                <div class="carousel-caption">
                    
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>

    </header>

    <!-- Page Content -->
    <div class="container">
		<div class="signInFormHolder"> 
   			<div class="signInForm">
		        <div class="content">
		        <div class="title">Register</div>
		                <form name = 'loginform' method='post' action='register' id='form-control'>
		                   <ul> 
		                    <li><input type='text' name='email' id='email' placeholder='Email Address'></li>
		                    <li><input type='text' name='firstName' id='firstName' placeholder='First Name'></li>
		                    <li><input type='text' name='lastName' id='lastName' placeholder='Last Name'></li>
		                    <li><input type='text' name='username' id='uName' placeholder='Choose User Name'><li>
		                    <li><input type='text' name='phNumber' id='phNumber' placeholder='Phone Number(Optional)'><li>
		                    <li><input type='text' name='dateOfBirth' id='date' placeholder='Date of Birth'><li>
		                    <li><input type='password' name='password' id='sPassword' placeholder='Password'><li>
		                    <li><input type='password' name='verifyPassword' id='vpassword' placeholder='Verify Password'><li>
		                    <li><input name="" type="checkbox" value=""   /> Remember on this Computer
		                    <button class='btn' id='btn1' type='submit' ><b>Submit</button><hr> >>Already have an Account?
		                    <button class='btn' id='btn2' type='button' onclick="location.href='login.jsp';" ><b>Login</button>	                    
		                    </li>
		                   </ul>
		                    
		                </form>
		        </div>
      		</div>
      	</div>
    </div>
    <!-- /.container -->

    <!-- jQuery Version 1.11.0 -->
    <script src="/Ebuy_Inventory/JavaScript/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/Ebuy_Inventory/JavaScript/bootstrap.min.js"></script>
	<script src="/Ebuy_Inventory/JavaScript/jquery.watermark.min.js"></script>
    <!-- Script to Activate the Carousel -->
    <script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    });
    
    $(function() {
	    $( "#date" ).datepicker({dateFormat: 'yy-mm-dd'});
	  });
  	
    </script>

</body>

</html>
