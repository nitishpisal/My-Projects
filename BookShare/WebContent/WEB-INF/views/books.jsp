<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<script type="text/javascript">
	
	function search(){
		var search = document.getElementById("search").value;
		var regForm = document.forms['navigate'];
		regForm.method = "GET";
		regForm.action = "http://localhost:8080/Poker/login?search="+search;
		regForm.submit();
	}

</script>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Simply Hired - Get your Job done</title>

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/resources/forwards/css/bootstrap.min.css" />" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<c:url value="/resources/forwards/css/shop-homepage.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/freelancer.css" />" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet"  type="text/css">
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
                <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">Simply Hired</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                    	
                        <a href="<%=request.getContextPath()%>/postjob">Post Your Job &nbsp &nbsp
                        &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
                        </a>
                    </li>
                </ul>
                <ul class="nav navbar-nav">
                	<li>
	                	<form:form modelAttribute="navigate">
							<table>
								<tr>
									<td>	<input type="text" id="search"> </td>
									<td style="border: 0;"><input type="button" value="Search"
									onclick="javascript:search();"></td>
								</tr>
							</table>
						</form:form>
					</li>
				</ul>
                <ul class="nav navbar-nav navbar-right">
                	
                    <%String login= (String)request.getSession().getAttribute("login");
                    if(login != null && login.equals("true")) { %>
                    <li>
                        <a href="#">Welcome, <%= request.getSession().getAttribute("userName") %></a>
                    </li>
                     <li>
                        <a href="<%=request.getContextPath() %>/myStulance/?jobs=all">My Account</a>
                    </li>
                    
                    <li class="page-scroll">
                        <a href="<%= request.getContextPath()%>/logout">Logout</a>
                    </li>
                    <%} else{ %>
                     <li class="page-scroll">
                        <a href="<%=request.getContextPath()%>/login.jsp">Login/Register</a>
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
                	<h5><a href = "<%=request.getContextPath()%>/get/jobs/?category=householdandhomecare&field=all">Household & Homecare</a></h5>
                	<ul>
	                    <li><a href="<%=request.getContextPath()%>/get/jobs/?category=householdandhomecare&field=lawnmowing" class="l">Home</a></li>                	             	                 	
	                	<li><a href="<%=request.getContextPath()%>/get/jobs/?category=householdandhomecare&field=housecleaning" class="l">Books</a></li>
	                    <li><a href="<%=request.getContextPath()%>/get/jobs/?category=householdandhomecare&field=babysitting" class="l">Post Order</a></li>
	                	<li><a href="<%=request.getContextPath()%>/get/jobs/?category=householdandhomecare&field=tutor" class="l">Sell Books</a></li>
				 	</ul>
                </div>
                
                <%-- <div class="list-group">
                	<img src="<c:url value="/resources/images/books1.jpg" />" alt="">
                </div> --%>
            </div>

            <div class="col-md-9">

                <div class="row carousel-holder">

                    <div class="col-md-12">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol>
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img class="slide-image" src="<c:url value="/resources/forwards/images/freelancer1.jpeg" />" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="<c:url value="/resources/forwards/images/freelancer2.jpeg" />" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="<c:url value="/resources/forwards/images/freelancer3.jpeg" />" alt="">
                                </div>
                            </div>
                            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>

                </div>
                <c:if test="${not empty books}">
                	<c:forEach var="book" items="${books}">
						<form method= "post" action="<%=request.getContextPath()%>/proposal.jsp"> 
		                <div class="well" style="color:#2c3e50">
		                	<h5><b>Title:</b> ${book.title}</h5>
							<h6> <b>Author:</b> ${book.author}</h6> 
							 Publisher:    ${book.publisher} &nbsp;<b>| </b>&nbsp;  year:  ${book.year} &nbsp;<br>
							 price: $  ${book.price}<br>
							 
							<%-- <input type="hidden" name="jobId" value="<%= itr.getJobId() %>">						        
							<input type="hidden" name="jobTitle" value="<%= itr.getTitle() %>">
							<input type="hidden" name="jobDesc" value="<%= itr.getDescription() %>">
							<input type="hidden" name="jobDeadline" value="<%= itr.getDeadlines() %>">
							<input type="hidden" name="jobPay" value="<%= itr.getPay() %>"> --%>
							<%-- <%if(login != null && login.equals("true")) { %> --%>
		                    <div class="text-right">
		                    	<button type="submit" class="btn btn-success btn-lg">Buy now</button>
		                    </div>
		                    <%-- <%}else { %>
		                    <div class="text-right">
		                    <font size=2>**Please login to apply for this job</font>
		                    	<button type="submit" class="btn btn-success btn-lg" disabled>Buy now</button>
		                    </div>
		                    <%} %> --%>
		                </div>
		                </form>
		            </c:forEach>
				</c:if>
            </div>

        </div>

    </div>
    <!-- /.container -->

    <div class="container">

        <hr>

      
    </div>
    <!-- /.container -->

    <!-- jQuery Version 1.11.0 -->
    <script src="<c:url value="/resources/forwards/js/jquery-1.11.0.js" />" ></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/resources/forwards/js/bootstrap.min.js" />"></script>

</body>

</html>