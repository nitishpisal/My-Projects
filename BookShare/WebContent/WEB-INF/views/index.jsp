<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Book Share - Share Knowledge, Go Wise </title>

    <!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value="/resources/css/freelancer.css" />" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:url value="/resources/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>  
 <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>  

<script>

$(function() {
	  
    // Setup form validation on the #register-form element
    $("#contactForm4").validate({
    
        // Specify the validation rules
        rules: {
            name4: { required : true,
            		minlength:2
            		 
            	},            
            email4 : {	required : true , 
            			email : true
            		},
		
		
            message4 : {	required : true,
            				minlength: 10
            	
            		}
            
        },
		//contactForm username password cPassword 
        
        // Specify the validation error messages
        messages:
        {
           name4: { 
        	   required:  "Please enter your first name"
            },
            
            email4: {
              // required: "Please provide a valid email-ID"
            },
				message4:  { required : "Please enter your concern ",
						  minlength : "Length should be atleast 10 "
							  
				}
          
        },
        
        submitHandler: function(form) 
        {
            form.send();
        }
    });

  });
 
$(function() {
    $("#loginform").validate({
    rules: {
    		username : {	required : true,
    			email: true
      		},
	           password: {
            	required: true,
            	minlength: 5
            }
    },
    messages: {
    	 username: { required:  "Please enter your first name",
         	minlength : "should be atleast 2 characters"
         },
        username: { required:  "Please enter your first name",
        	minlength : "should be atleast 2 characters"
        },
        password: {
            required: "Please provide a password",
            minlength: "Your password must be at least 5 characters long"
        }
				
    },
    submitHandler: function(form) {
        form.submit();
    }
});
});
 
$(function() {
    $("#register").validate({
    rules: {
    		email : {	required : true , 
    		email : true
      		},
	           password1: {
            	required: true,
            	minlength: 5
            },
			confirmPass2: { 
	        	required : true,		
		    	equalTo : "#password1"
		    }
    },
    messages: {
    	 email: { required:  "Please enter your first name",
         	minlength : "should be atleast 2 characters"
         },
        username: { required:  "Please enter your first name",
        	minlength : "should be atleast 2 characters"
        },
        password1: {
            required: "Please provide a password",
            minlength: "Your password must be at least 5 characters long"
        },
		confirmPass2: { 
				required : "Please provide a password",
				equalTo: "Please enter the same password as above"
				}
				
    },
    submitHandler: function(form) {
        form.submit();
    }
});
});
 
</script>


</head>

<body id="page-top" class="index">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#page-top">BookShare</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li class="page-scroll">
                        <a href="#">Books</a>
                        <ul>
                        <li class="page-scroll">
                        <a href="#requestbooks">Request A Book</a>
                        </li>
                        <li class="page-scroll">
                        <a href="#sellbooks">Sell A Book</a>
                        </li>
                        <li class="page-scroll">
                        <a href="<%= request.getContextPath()%>/books?action=available">Available Books</a>
                        </li>
                        <li class="page-scroll">
                        <a href="<%= request.getContextPath()%>/books?action=required">Required Books</a>
                        </li>
                        </ul>
                    </li>
                    <li class="page-scroll">
                        <a href="#about">About</a>
                    </li>
                    <% String login=(String) request.getSession().getAttribute("login");
                    	if (login != null && login.equalsIgnoreCase("true")){%>
                    <li>
                        <a href="<%=request.getContextPath() %>/myaccount?action=mybooks">My Account</a>
                    </li>
                    <li class="page-scroll">
                        <a href="<%= request.getContextPath()%>/logout">Logout</a>
                    </li>
                   <%} else {%>
                    <li class="page-scroll">
                        <a href="#contact">Contact</a>
                    </li>
                    <li class="page-scroll">
                        <a href="#login">Login/Register</a>
                    </li>
                    <%} %>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
	
    <!-- Header -->
    <header>
    	<hr>
        <div class="container">
        	
          <div class="row">
           <div class="col-lg-12">
           		<img class="img-responsive" src="<c:url value="/resources/img/profile.png" />" alt="">
                 	<div class="intro-text">
                        <span class="name"><font size="5">Get Your Books Today!</font></span>
                        <hr style="border-top: solid 2px; max-width: 400px;">
                        <span class="skills">Buy - Sell - Bid</span>
                    </div>
                </div>
          </div>  
        </div>
    </header>

    <!-- Portfolio Grid Section -->
    <section id="requestbooks">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h3>Request Books</h3>
                    <hr class="star-primary">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-lg-offset-2">
                    <p>You can post the details of the books that you need and other users can give you proposals. </p>
                </div>
                <div class="col-lg-4">
                    <p>You can receive multiple proposals for your requests and you can choose which one to go with!</p>
                </div>
                <%-- <div class="col-lg-8 col-lg-offset-2 text-center">
                    <a href="<c:url value="/resources/postjob" />" class="btn btn-lg btn-outline">
                         Post Your Job!
                    </a>
                </div> --%>
                 <div class="col-lg-8 col-lg-offset-2 text-center">
		          	<button onclick="window.location.href='<%=request.getContextPath() %>/requestbook'" class="btn btn-success btn-lg">Request Book</button>
		       	</div>
            </div>
        </div>
    </section>
	<!-- Find Jobs -->
		 <!-- Portfolio Grid Section -->
    <section class="success" id="sellbooks">
    
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h3>Sell your Book</h3>
                    <hr class="star-light">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-lg-offset-2">
                    <p>You can post the books that are availabe in your inventory and you can sell them to others. </p>
                </div>
                <div class="col-lg-4">
                    <p>You can also enable bidding option for your book, Choose which user you want to go with!</p>
                </div>
               <%--  <div class="col-lg-8 col-lg-offset-2 text-center">
                    <a href="<c:url value="<%=request.getContextPath() %>/sellbook'" />" class="btn btn-lg btn-outline">
                         Sell your Book!
                    </a> --%>
                    <div class="col-lg-8 col-lg-offset-2 text-center">
		          		<button onclick="window.location.href='<%=request.getContextPath() %>/sellbook'" class="btn btn-lg btn-outline">Sell your Book</button>
		       		</div>
                <!-- </div> -->
            </div>
        </div>
     
    </section>
	<!-- FindLancer Ends -->
	
	
	
	<!--  Login/Signup  Section -->
	<section class="container" id="login">
	 <div class="container">
          <div class="row">
                <div class="col-lg-12 text-center">
                    <h3>Login or Register</h3>
                    <hr class="star-primary">
				</div>
           </div> 
            <div class="row">
            	
                <div class="col-lg-5 col-lg-offset-1">
                    <form  name="loginform" id="loginform" method="post" action="<%=request.getContextPath() %>/validate">
                        <div class="row control-group">
                            <div class="form-group col-xs-10 floating-label-form-group">
                                <label>Email</label>
                                <input type="text" class="form-control" name="username" placeholder="Email" id="name" >
                                <!-- <p class="help-block text-danger"></p>  -->
                            </div>
                        </div>
                        <div class="row control-group">
                            <div class="form-group col-xs-10 floating-label-form-group ">
                                <label>password</label>
                                <input type="password" class="form-control" name="password" placeholder="Password" id="password" >
                                <!-- <p class="help-block text-danger"></p>  -->
                            </div>
                        </div>
                        <br>
                        <div id="success"></div>
                        <div class="row">
                            <div class="form-group col-xs-10">
                                <button type="submit" class="btn btn-success btn-lg">Login</button>
                            </div>
                        </div>
                    </form>
                </div>
                <!-- Register Section -->
                <div class="col-lg-5 col-lg-offset-1">
                    <form name="register" id="register" method="post" action="<%=request.getContextPath() %>/register" >
                        
                        <div class="row control-group">
                            <div class="form-group col-xs-10 floating-label-form-group controls">
                                <label>Email</label>
                                <input type="text" class="form-control" placeholder="Email" name="email" id="rEmail" >
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        <div class="row control-group">
                            <div class="form-group col-xs-10 floating-label-form-group controls">
                                <label>pasword</label>
                                <input type="password" class="form-control" name="password1" placeholder="Password" id="password1" >
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        <div class="row control-group">
                            <div class="form-group col-xs-10 floating-label-form-group controls">
                                <label>confirm password</label>
                                <input type="password" class="form-control" placeholder="Confirm Password" name="confirmPass2" id="confirmPass2" >
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        <br>
                        <div id="success"></div>
                        <div class="row">
                            <div class="form-group col-xs-10">
                                <button type="submit" class="btn btn-success btn-lg">Continue</button>
                            </div>
                        </div>
                    </form>
                </div>
                
            </div>
        </div>
       </section>
	<!-- Login/Signup Ends -->
	
	
	
    <!-- About Section -->
    
    <section class="success" id="about">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h3>About</h3>
                    <hr class="star-light">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-lg-offset-2">
                    <p>BookShare is reinventing the way we work. Today more than 2 million busines tap into BookShare to find, Buy and Sell the world's best Books, making it easier than ever to build successful companies and thriving careers</p>
                </div>
                <div class="col-lg-4">
                    <p>People are connecting via Bookshare to Bid, offer proposals, sell and buy books. The company is providing transparency in the process and Users are loving it.</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h3>Founders</h3>
                    <hr class="star-light">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-3">
                <img style="background-repeat: no-repeat; background-position: 50%; border-radius: 50%; width: 100px; height: 100px;" alt="" src="<%=request.getContextPath() %>/resources/images/badal.jpg">
                <h5>Badal Jain</h5> 
                </div>
                <div class="col-sm-3">
                <img style="background-repeat: no-repeat; background-position: 50%; border-radius: 50%; width: 100px; height: 100px;" alt="" src="<%=request.getContextPath() %>/resources/images/rishi.jpg">
                <h5>Rishi Khurana</h5> 
                </div>
                <div class="col-sm-3">
                <img style="background-repeat: no-repeat; background-position: 50%; border-radius: 50%; width: 100px; height: 100px;" alt="" src="<%=request.getContextPath() %>/resources/images/aditya.jpg">
                <h5>Aditya Shingvi</h5> 
                </div>
                <div class="col-sm-3">
                <img style="background-repeat: no-repeat; background-position: 50%; border-radius: 50%; width: 100px; height: 100px;" alt="" src="<%=request.getContextPath() %>/resources/images/pranjal.jpg">
                <h5>Pranjal Shah</h5> 
                </div>
                <div class="col-sm-3">
                <img style="background-repeat: no-repeat; background-position: 50%; border-radius: 50%; width: 100px; height: 100px;" alt="" src="<%=request.getContextPath() %>/resources/images/agam.jpg">
                <h5>Agam Masalia</h5> 
                </div>
            </div>
        </div>
    </section>

   
	 <!-- Contact Section -->
    <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>Contact Us</h2>
                    <hr class="star-primary">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
                    <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
                    <form name="sentMessage" id="contactForm4" novalidate>
                        <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <label>Name</label>
                                <input type="text" class="form-control" placeholder="Name" id="name4" id="name4" >
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <label>Email Address</label>
                                <input type="email" class="form-control" placeholder="Email Address" id="email4" name="email4" >
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <label>Message</label>
                                <textarea rows="5" class="form-control" placeholder="Message" id="message4" name="message4" ></textarea>
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        <br>
                        <div id="success"></div>
                        <div class="row">
                            <div class="form-group col-xs-12">
                                <button type="submit" name="send" value="send"  class="btn btn-success btn-lg">Send</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer class="text-center">
        <div class="footer-above">
            <div class="container">
                <div class="row">
                    <div class="footer-col col-md-4">
                        <h3>Location</h3>
                        <p>San Farnando, CA 95112</p>
                    </div>
                    <div class="footer-col col-md-4">
                        <h3>Around the Web</h3>
                        <ul class="list-inline">
                            <li>
                                <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-facebook"></i></a>
                            </li>
                            <li>
                                <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-google-plus"></i></a>
                            </li>
                            <li>
                                <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-twitter"></i></a>
                            </li>
                            <li>
                                <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-linkedin"></i></a>
                            </li>
                            
                        </ul>
                    </div>
                    <div class="footer-col col-md-4">
                        <h3>About BookShare Team</h3>
                        <p>BookShare is a web app created by a group of SJSU students</p>
                    </div>
                </div>
            </div>
        </div>
       <div class="footer-below">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        Copyright@Bookshare.com . All Rights Reserved
                    </div>
                </div>
            </div>
        </div>
    </footer>

    <!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
    <div class="scroll-top page-scroll visible-xs visble-sm">
        <a class="btn btn-primary" href="#page-top">
            <i class="fa fa-chevron-up"></i>
        </a>
    </div>

    <!-- Portfolio Modals Removed -->
    
    
  
    <!-- jQuery -->
  
    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

    <!-- Plugin JavaScript -->
    <script src="<c:url value="/resources/js/jquery.easing.min.js" />"></script>
    <script src="<c:url value="/resources/js/classie.js" />"></script>
    <script src="<c:url value="/resources/js/cbpAnimatedHeader.js" />"></script>

    <!-- Contact Form JavaScript -->
    <script src="<c:url value="/resources/js/jqBootstrapValidation.js" />"></script>
   

    <!-- Custom Theme JavaScript -->
    <script src="<c:url value="/resources/js/freelancer.js" />"></script>

</body>

</html>
