<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<html>

<script type="text/javascript">
	
	function buyNow(){
		var bookid = document.getElementById("bookid").value;
		var buyerid = document.getElementById("buyerid").value;
		var price = document.getElementById("price").value;
		var quantity = document.getElementById("quantity").value;
		var regForm = document.forms['buyForm'];
		regForm.method = "POST";
		regForm.action = "http://localhost:8080/Poker/buybook?"
				+ "bookid="+bookid
				+ "&buyerid="+buyerid 
				+ "&price=" + price
				+ "&quantity=" + quantity;
		regForm.submit();
	}
	
	function placeBid(){
		var bookid = document.getElementById("bookid").value;
		var buyerid = document.getElementById("buyerid").value;
		var price = document.getElementById("price").value;
		var quantity = document.getElementById("quantity").value;
		var regForm = document.forms['buyForm'];
		var bidPrice = document.getElementById("bidPrice").value;
		regForm.method = "POST";
		regForm.action = "http://localhost:8080/Poker/placebid?"
				+ "bookid="+bookid
				+ "&buyerid="+buyerid 
				+ "&price=" + price
				+ "&bidPrice=" + bidPrice;
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
                <a class="navbar-brand" href="<c:url value="/resources/index.jsp" />">Simply Hired</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                	<li>
                        <a href="<c:url value="/resources/postjob" />">Post Your Job</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <%String login= (String)request.getSession().getAttribute("login");
                    if(login != null && login.equals("true")) { %>
                    <li class="page-scroll">
                        <a href="<%= request.getContextPath()%>/logout">Logout</a>
                    </li>
                    <%} else{ %>
                     <li class="page-scroll">
                        <a href="<%= request.getContextPath()%>/login">Login/Register</a>
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
                <p class="lead" style="color:#2c3e50"><b>Categories</b></p>
                <div class="list-group">
                	<h5><a href = "#">As a Client</a></h5>
                	<ul>
                    <li><a href="<c:url value="/resources/myStulance/?jobs=all" />"class="l">Posted Jobs</a></li>
                    <li><a href="<c:url value="/resources/myStulance/?jobs=proposalsForMe" />" class="l">Received Proposals</a></li>
                    <li><a href="<c:url value="/resources/myStulance/?jobs=completed" />" class="l">Completed Jobs</a></li>   
					</ul>
                </div>
				<div class="list-group">
                	<h5><a href = "#">As a Student</a></h5>
                	<ul>
                    <li><a href="<c:url value="/resources/myStulance/?jobs=myJobs" />" class="l">Assigned Jobs</a></li>
                    <li><a href="<c:url value="/resources/myStulance/?jobs=myProposals" />"class="l">My Proposals</a></li>
                    <li><a href="<c:url value="/resources/myStulance/?jobs=completedByMe" />" class="l">Completed Jobs</a></li>   
					</ul>
                </div>
                <%-- <div class="list-group">
                	<h5><a href = "<c:url value="/resources/personalitySurvey.jsp" />">Discover Your Interest</h5>
                </div> --%>				
            </div>

            <div class="col-md-9">  
            <form:form modelAttribute="buyForm">
                <div class="well" style="color:#2c3e50">
                	<h5><b>Title : </b> ${book.title }</h5>
                	<hr>
                	<font size="3"><b> Author :</b> ${book.author }</font><br><br>
					<font size="3"><b> Publisher : </b>${book.publisher }</font>
					<hr>
					<font size="2"><b> Year: </b>  ${book.year }</font><br>
					<font size="2"><b> Available Quantity :</b> ${book.quantity }</font><br>
					
					<font size="2"><b> Bid: </b>  ${book.bid }</font><br>
					<font size="2"><b> Price :</b> $ ${book.price }</font><br>
					<font size="2"><b> Available : </b>${book.available }</font>
					<hr>
					<div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <label>Quantity</label>
                                <input type="text" class="form-control" placeholder="Quantity" id="quantity" >
                                <p class="help-block text-danger"></p>
                            </div>
                   	</div>
					
					<input type="hidden" id="buyerid" value=<%=request.getSession().getAttribute("userid")%>> 
					<input type="hidden" id="bookid" value=${book.bookId }>
					<input type="hidden" id="price" value=${book.price }>
					<input type="hidden" id="bid" value=${book.bid }>
					<c:if test="${book.bid == 'N' }">
	                    <div class="text-right">
	                        <button type="submit" onclick="javascript:buyNow();" class="btn btn-success btn-lg">Buy now</button>
	                    </div>
                    </c:if>
                    
                    <c:if test="${book.bid == 'Y' }">
                    	<input type="text" class="form-control" placeholder="Place Bid" id="bidPrice" >
	                    <div class="text-right">
	                        <button type="submit" onclick="javascript:placeBid();" class="btn btn-success btn-lg">Bid now</button>
	                    </div>
                    </c:if>
					<hr>   
                    
                	</div>
          	</form:form>
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
