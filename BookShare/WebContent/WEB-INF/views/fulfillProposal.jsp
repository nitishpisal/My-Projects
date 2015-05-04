<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<html>

<script type="text/javascript">
	
	function submitProposal(){
		var postId = document.getElementById("postid").value;
		var desc = document.getElementById("desc").value;
		var regForm = document.forms['submitProposal'];
		regForm.method = "POST";
		regForm.action = "http://localhost:8080/Poker/proposal?"
				+ "postId="+postId
				+ "&desc="+desc;
		alert(regForm.action);
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
                <a class="navbar-brand" href='<%=request.getContextPath() %>/'>BookShare</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <%String login= (String)request.getSession().getAttribute("login");
                    if(login != null && login.equals("true")) { %>
                    <li>
                        <a href="#">Welcome, <%= request.getSession().getAttribute("firstname") %></a>
                    </li>
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
            <form:form modelAttribute="submitProposal">
                <div class="well" style="color:#2c3e50">
                	<h5><b>Title : </b> ${post.title }</h5>
                	<hr>
                	<font size="2"><b> ISBN :</b> ${post.isbn }</font><br>
                	<font size="2"><b> Author :</b> ${post.author }</font><br>
					<font size="2"><b> Publisher : </b>${post.publisher }</font>
					<hr>
					<font size="2"><b> Year: </b>  ${post.year }</font><br>
					<font size="2"><b> Required Quantity :</b> ${post.quantity }</font><br><br>
					
					<div class="form-group col-xs-12 floating-label-form-group controls">
                       	<textarea rows="5" name="desc" class="form-control" placeholder="Your Proposal" id="desc" ></textarea>
                       	<p class="help-block text-danger"></p>
                    </div>
					
					<%-- <input type="hidden" id="proposerId" value=<%=request.getSession().getAttribute("userid")%>>  --%>
					<input type="hidden" id="postid" value=${post.postId }>
                    <div class="text-right">
                        <button type="submit" onclick="javascript:submitProposal();" class="btn btn-success btn-lg">Submit</button>
                    </div>
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
