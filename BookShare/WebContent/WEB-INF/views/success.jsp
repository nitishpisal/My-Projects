<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Book Share - Share Knowledge, Go Wise</title>

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/resources/forwards/css/bootstrap.min.css" />" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<c:url value="/resources/forwards/css/shop-homepage.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/freelancer.css"/>" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

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
                <a class="navbar-brand" href="<c:url value="/resources/index.jsp" />">BookShare</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <%String login= (String)request.getSession().getAttribute("login");
                    if(login != null && login.equals("true")) { %>
                    <li>
                        <a href="#">Welcome, <%= request.getSession().getAttribute("firstname") %></a>
                    </li>
                     <li>
                        <a href="<%=request.getContextPath() %>/myaccount?action=mybooks">My Account</a>
                    </li>
                    
                    <li class="page-scroll">
                        <a href="<%= request.getContextPath()%>/logout">Logout</a>
                    </li>
                    <%} else{ %>
                     <li class="page-scroll">
                        <a href="<%=request.getContextPath() %>/login">Login/Register</a>
                    </li>
                    <%} %>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
	<hr>
    <!-- Page Content -->
    <div class="container">

        <div class="row">
			
            <div class="col-md-3">
                <p class="lead" style="color:#2c3e50"><b>Navigation</b></p>
                <div class="list-group">
                	<h5><a href = "<%=request.getContextPath()%>/books">Go to..</a></h5>
                	<ul>
	                    <li><a href="<%=request.getContextPath()%>/" class="l">Home</a></li>                	             	                 	
	                    <li><a href="<%=request.getContextPath()%>/requestbook" class="l">Request A Book</a></li>
	                	<li><a href="<%=request.getContextPath()%>/sellbook" class="l">Sell A Book</a></li>
	                    <li><a href="<%=request.getContextPath()%>/books?action=available" class="l">Available Books</a></li>
	                	<li><a href="<%=request.getContextPath()%>/books?action=required" class="l">Required Books</a></li>
				 	</ul>
                </div>
            </div>

            <div class="col-md-9">            		 
                <div class="well" style="color:#2c3e50">
                    <div class="row">
                        <div class="col-md-12">
                            <form > 
								<font size="4"><b>${success}</font><br><br>
								 <c:if test="${not empty order}">
								 	<font size="3"><b> Order ID :</b> ${order.orderId }</font><br>
									<font size="3"><b> Quantity : </b>${order.quantity }</font><br>
									<font size="3"><b> Amount : </b>${order.amount }</font><br>
									<hr>
								 </c:if>
                                <button type="button" onclick="location.href='<%=request.getContextPath() %>/'" class="btn btn-success btn-lg">  Home</button>
                         	</form>
                        </div>
                    </div>
                 </div> 
			</div>
        </div>
    </div>
    <!-- /.container -->

    <div class="container">

        <hr>

      
    </div>
    <!-- /.container -->

    <!-- jQuery Version 1.11.0 -->
    <script src="<c:url value="/resources/forwards/js/jquery-1.11.0.js" />"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/resources/forwards/js/bootstrap.min.js" />"></script>

</body>

</html>
