<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<script type="text/javascript">
	
	function search(){
		alert("I am here 1");
		var search = document.getElementById("search").value;
		alert(search);
		var regForm = document.forms['searchForm'];
		regForm.method = "POST";
		regForm.action = "<%=request.getContextPath()%>/search/?value="+search;
		regForm.submit();
	}
	
	function login(){
		var loginVal = document.getElementById("buyNow").value;
		var bookId = document.getElementById("bookIdt").value;
		var regForm = document.forms['buyForm'];
		alert(loginVal + bookId);
		regForm.method = "POST";
		/* regForm.action = "http://localhost:8080/Poker/login/" + loginVal + "?bookId=" + bookId2
				+ "&action=buy";
		 */
		if(loginVal == "noLogin"){
			regForm.action = "<%=request.getContextPath()%>/login/?bookId=" + bookId
					+ "&action=buy" ;
		}
		else{
			regForm.action = "<%=request.getContextPath()%>/buy?bookId=" + bookId;
		}
		
		regForm.submit();
	}
	
	function required(){
		
		var loginVal = document.getElementById("sellNow").value;
		var postId = document.getElementById("postId").value;
		var regForm = document.forms['buyForm'];
		alert(loginVal + postId);
		regForm.method = "POST";
		if(loginVal == "noLogin"){
			regForm.action = "<%=request.getContextPath()%>/login/?postId=" + postId 
					+ "&action=fulfill" ;
		}
		else{
			regForm.action = "<%=request.getContextPath()%>/fulfill?postId=" + postId;
		}
		regForm.submit();
	}
	

</script>

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
                <a class="navbar-brand" href="<%=request.getContextPath()%>/">BookShare</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                	
                	<li>
                	  	 &nbsp;
	                	<%-- <form:form modelAttribute="searchForm"> --%>
	                	<form method="post" action="<%=request.getContextPath() %>/search">
							<table>
								<tr>
									<td> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 
											&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp;	
										<input type="text" id="value" name="value"> 
									</td>
									<td style="border: 0;"><input type="submit" value="Search"></td>
								</tr>
							</table>
						</form>
						<%-- </form:form> --%>
					</li>
				</ul>
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
                        <a href="<%=request.getContextPath()%>/login">Login/Register</a>
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
                	<%-- <form:form modelAttribute="buyForm"> --%>
                	
                	<c:if test="${what == 'available' }">
						<form method= "post" action="<%=request.getContextPath()%>/buy/?bookId=${book.bookId }&action=buy"> 
		                <div class="well" style="color:#2c3e50">
		                	<h5><b>Title:</b> ${book.title}</h5>
							<h6> <b>Author:</b> ${book.author}</h6> 
							 Publisher:    ${book.publisher} &nbsp;<b>| </b>&nbsp;  year:  ${book.year} &nbsp;<br>
							 Bid:    ${book.bid} &nbsp;
							 <c:if test="${what == 'available' }">
							 	price: $  ${book.price}<br>
							 </c:if>
							
							
									<input type="hidden" id="bookIdt" value="${book.bookId}">
									<input type="hidden" id="buyNow" value="loggedin">
								<%if(login != null && login.equals("true")) { %>
									<c:if test="${book.bid == 'Y' }">
								 		<div class="text-right">
					                    	<button type="submit"  class="btn btn-success btn-lg">Bid Now</button>
					                    </div>
				                    </c:if>
				                    <c:if test="${book.bid == 'N' }">
								 		<div class="text-right">
					                    	<button type="submit" class="btn btn-success btn-lg">Buy now</button>
					                    </div>
				                    </c:if>

				                    
		                     <%}else {%>
		                     	
		                     		<div class="text-right">
		                     				**Please Login
					                    	<button type="submit" class="btn btn-success btn-lg" disabled>Buy/Bid</button>
					                </div>
		                    <%} %>
		                </div>
		              <%--  </form:form> --%>
		             
		             	</form>
		             </c:if>
		             
		             <!-- form for required -->
		             
		             <c:if test="${what == 'required' }">
						<form method= "post" action="<%=request.getContextPath()%>/fulfill/?postId=${book.postId }&action=buy"> 
		                <div class="well" style="color:#2c3e50">
		                	<h5><b>Title:</b> ${book.title}</h5>
							<h6> <b>Author:</b> ${book.author}</h6> 
							 Publisher:    ${book.publisher} &nbsp;<b>| </b>&nbsp;  year:  ${book.year} &nbsp;<br>
							 <c:if test="${what == 'available' }">
							 	price: $  ${book.price}<br>
							 </c:if>
							<%if(login != null && login.equals("true")) { %>
							
							 		<input type="hidden" id="postId" value="${book.postId}">
							 		<input type="hidden" id="sellNow" value="loggedin">
							 		<div class="text-right">
				                    	<button type="submit" class="btn btn-success btn-lg">Fulfill</button>
				                    </div>
				                    
		                     <%}else {%>
		                     	
			                    	<div class="text-right">
		                     				**Please Login
					                    	<button type="submit" class="btn btn-success btn-lg" disabled>Fulfill</button>
					                </div>
		                    <%} %>
		                </div>
		             
		             	</form>
		             </c:if>
		             
		             
		            </c:forEach>
				</c:if>
            </div>

        </div>

    </div>
    <!-- /.container -->

    <!-- jQuery Version 1.11.0 -->
    <script src="<c:url value="/resources/forwards/js/jquery-1.11.0.js" />" ></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/resources/forwards/js/bootstrap.min.js" />"></script>

</body>

</html>