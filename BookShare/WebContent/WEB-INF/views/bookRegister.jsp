<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>  
 	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>  
 
	<title>Simply Hired - Get your Job done</title>

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/resources/forwards/css/bootstrap.min.css" />" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<c:url value="/resources/forwards/css/shop-homepage.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/freelancer.css" />" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700"  rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

</head>

<body id="page-top" class="index">

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<c:url value="/index.jsp" />">Simply Hired</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                   <%--  <%if(request.getSession().getAttribute("login").equals("true")) { %>
                    <li>
                        <a href="#">Welcome, <%= request.getSession().getAttribute("userName") %></a>
                    </li>
                     <li>
                        <a href="<%=request.getContextPath() %>/myStulance/?jobs=all">My Account</a>
                    </li>
                    
                    <li class="page-scroll">
                        <a href="<%= request.getContextPath()%>/logout">Logout</a>
                    </li>
                    <%} %> --%>
                   
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
    <hr>
	
    <!-- Header -->
  <!-- post your requirement -->
  <c:if test="${what == 'buyRequest'}">
    <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h3>Post Your requirements</h3>
                    <hr class="star-primary">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
                    <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
                    <form name="buyrequest" id="buyrequest" method="post" action='<%=request.getContextPath() %>/postrequirements'>
                        <div class="row control-group">
                        	<h4>Please give your requirements</h4><br><br>
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <input type="text" class="form-control" placeholder="ISBN" id="isbn" name="isbn" >
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <input type="text" class="form-control" placeholder="Title" id="title" name="title" >
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <input type="text" class="form-control" placeholder="Author" id="author" name="author" >
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <input type="text" class="form-control" placeholder="Publisher" id="publisher" name="publisher" >
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <input type="text" class="form-control" placeholder="Year" id="year" name="year" >
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <input type="text" class="form-control" placeholder="Quantity" id="quantity" name="quantity" >
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                       <!--  <br>
                          <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                 <label>Describe it</label>
                                <textarea rows="5" name="desc" class="form-control" placeholder="Description of the job" id="desc" ></textarea>
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                         -->
                        <br>
                        <div id="success"></div>
                        <div class="row">
                            <div class="form-group col-xs-12">
                                <button type="submit" name="send2" id="send2" class="btn btn-success btn-lg">Submit</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    
   </c:if>

   <!-- FindLancer Ends -->
	
	 <!-- Post your book to sell -->
	<c:if test="${who == 'sellRequest'}">
	    <section id="contact">
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-12 text-center">
	                    <h3>Post Your add</h3>
	                    <hr class="star-primary">
	                </div>
	            </div>
	            <div class="row">
	                <div class="col-lg-8 col-lg-offset-2">
	                    <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
	                    <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
	                    <form name="contactForm2" id="contactForm2" method="post" action='submitJob'>
	                        <div class="row control-group">
	                        	<h4>Add your Book details</h4><br><br>
	                            <div class="form-group col-xs-12 floating-label-form-group controls">
	                                <input type="text" class="form-control" placeholder="ISBN" id="isbn" name="isbn" >
	                                <p class="help-block text-danger"></p>
	                            </div>
	                            <div class="form-group col-xs-12 floating-label-form-group controls">
	                                <input type="text" class="form-control" placeholder="Title" id="title" name="title" >
	                                <p class="help-block text-danger"></p>
	                            </div>
	                            <div class="form-group col-xs-12 floating-label-form-group controls">
	                                <input type="text" class="form-control" placeholder="Author" id="author" name="author" >
	                                <p class="help-block text-danger"></p>
	                            </div>
	                            <div class="form-group col-xs-12 floating-label-form-group controls">
	                                <input type="text" class="form-control" placeholder="Publisher" id="publisher" name="publisher" >
	                                <p class="help-block text-danger"></p>
	                            </div>
	                            <div class="form-group col-xs-12 floating-label-form-group controls">
	                                <input type="text" class="form-control" placeholder="Year" id="year" name="year" >
	                                <p class="help-block text-danger"></p>
	                            </div>
	                            <div class="form-group col-xs-12 floating-label-form-group controls">
	                                <input type="text" class="form-control" placeholder="Quantity" id="quantity" name="quantity" >
	                                <p class="help-block text-danger"></p>
	                            </div>
	                            <div class="form-group col-xs-12 floating-label-form-group controls">
	                                <input type="text" class="form-control" placeholder="Price" id="price" name="price" >
	                                <p class="help-block text-danger"></p>
	                            </div>
	                            <div class="row control-group">
		                            <div class="form-group col-xs-12 floating-label-form-group controls">
				                    	<!-- <input type="tel" class="form-control" name="price" placeholder="Enter the pay amount" id="price" > -->
			                       			<input type="tel" class="form-control" name="price" placeholder="Enable bidding" id="price" >
						                       	<form action="">
						                       	<input type="radio" name="bid" value="yes" >Yes<br>      
												<input type="radio" name="bid" value="no">No
												</form>
			                           			<p class="help-block text-danger"></p>
			                     	</div>
		                        </div>
	                        </div>
	                       <!--  <br>
	                          <div class="row control-group">
	                            <div class="form-group col-xs-12 floating-label-form-group controls">
	                                 <label>Describe it</label>
	                                <textarea rows="5" name="desc" class="form-control" placeholder="Description of the job" id="desc" ></textarea>
	                                <p class="help-block text-danger"></p>
	                            </div>
	                        </div>
	                         -->
	                        <br>
	                        <div id="success"></div>
	                        <div class="row">
	                            <div class="form-group col-xs-12">
	                                <button type="submit" name="send2" id="send2" class="btn btn-success btn-lg">Submit</button>
	                            </div>
	                        </div>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </section>
	</c:if>
    <!-- Footer -->
    <footer class="text-center">
        <div class="footer-above">
            <div class="container">
                <div class="row">
                    <div class="footer-col col-md-4">
                        <h3>Location</h3>
                        <p>3481 Melrose Place<br>Beverly Hills, CA 90210</p>
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
                            <li>
                                <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-dribbble"></i></a>
                            </li>
                        </ul>
                    </div>
                    <div class="footer-col col-md-4">
                        <h3>About Freelancer</h3>
                        <p>Freelance is a free to use, open source Bootstrap theme created by <a href="http://startbootstrap.com">Start Bootstrap</a>.</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer-below">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        Copyright &copy; Your Website 2014
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
    <script src="<c:url value="/resources/js/jquery.js" />" ></script>

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
