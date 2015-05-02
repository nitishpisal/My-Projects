
<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">

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
                <a class="navbar-brand" href="<%=request.getContextPath()%>/">Simply Hired</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                	<li>
                        <a href="<%=request.getContextPath()%>/postjob">Post Your Job</a>
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
                <p class="lead" style="color:#2c3e50"><b>Navigate</b></p>
                <div class="list-group">
                	<h5><a href = "#">Actions</a></h5>
                	<ul>
                    <li><a href="<%=request.getContextPath()%>/myaccount?action=myrequestedbooks" class="l">My requested Books</a></li>
                    <li><a href="<%=request.getContextPath()%>/myaccount?action=mybooks" class="l">My posted Books</a></li>
                    <li><a href="<%=request.getContextPath()%>/myaccount?action=myproposals" class="l">Proposals for Me</a></li>
                    <li><a href="<%=request.getContextPath()%>/myaccount?action=bidsformybooks" class="l">Bids for my Books</a></li>   
					</ul>
                </div>
            </div>
            <div class="col-md-9"> 
            <c:choose>
            	<c:when test="${what == 'myproposals'}">
				<c:if test="${not empty books}">
				<form method= "post" action="<%=request.getContextPath()%>/awardJob">
					<h5><b>Your Inventory</b></h5>
	                <c:forEach var="book" items="${books}">
			            	
			                <div class="well" style="color:#2c3e50">
			                	<h5><b>Title : </b> ${book.title }</h5>
			                	<hr>
			                	<font size="3"><b> ISBN :</b> ${book.isbn }</font><br><br>
			                	<font size="3"><b> Author :</b> ${book.author }</font><br><br>
								<font size="3"><b> Publisher : </b>${book.publisher }</font>
								<hr>
								<font size="2"><b> Year: </b>  ${book.year }</font><br>
								<font size="2"><b> Available Quantity :</b> ${book.quantity }</font><br>
								<%-- <input type="hidden" id="proposerId" value=<%=request.getSession().getAttribute("userid")%>>  --%>
								
								<c:if test="${what=='reqbooks'}">
								<input type="hidden" id="postid" value=${book.postId }>
								</c:if>
								<c:if test="${what=='mybooks'}">
								<input type="hidden" id="postid" value=${book.bookId }>
								</c:if>
			                    <div class="text-right">
			                        <button type="submit" onclick="javascript:submitProposal();" class="btn btn-success btn-lg">Submit</button>
			                    </div>
								<hr>   
			                    
			                </div>
			                </c:forEach>
			          	</form>
			         </c:if>
			        </c:when>
			        
			        <c:when test="${what == 'mybids'}">
			        I am here
				<c:if test="${not empty bids}">
				<form method= "post" action="<%=request.getContextPath()%>/awardJob">
					<h5><b>Bids for your books</b></h5>
	                <c:forEach var="bid" items="${bids}">
			            	
			                <div class="well" style="color:#2c3e50">
			                	<h5><b>Bid Id : </b> ${bid[0].bidId }</h5>
			                	<hr>
			                	<font size="2"><b> ISBN :</b> ${bid[0].bookId.isbn }</font><br><br>
			                	<font size="2"><b> Title :</b> ${bid[0].bookId.title }</font><br><br>
			                	<font size="2"><b> Author :</b> ${bid[0].bookId.author }</font><br><br>
			                	<font size="2"><b> Bid Price :</b> ${bid[0].bidPrice }</font><br><br>
								<hr>
								<h5><b>Bidder Details </b></h5>
								<font size="2"><b> Name :</b> ${bid[0].bidder.firstname }</font><br><br>
			                	<font size="2"><b> Phone Number :</b> ${bid[0].bidder.phoneno }</font><br><br>
			                	<font size="2"><b> Bidder :</b> ${bid[0].bidder.email }</font><br><br>
								
			                    <div class="text-right">
			                        <button type="submit" onclick="javascript:submitProposal();" class="btn btn-success btn-lg">Submit</button>
			                    </div>
								<hr>   
			                    
			                </div>
			                </c:forEach>
			          	</form>
			         </c:if>
			        </c:when>
			        
			        
			        
			        <c:otherwise>
			        	<%-- <c:if test="${not empty books}"> --%>
						<form method= "post" action="<%=request.getContextPath()%>/awardJob">
						<h5><b>Your Inventory</b></h5>
	                	<c:forEach var="detail" items="${details}">
			                <div class="well" style="color:#2c3e50">
			                	<h5><b>Proposal ID : </b> ${detail.proposalID }</h5>
			                	<hr>
			                	<font size="3"><b> ISBN/ Author/ Title:</b> ${detail.bookdetails }</font><br><br>
			                	<font size="2"><b> Proposal :</b> ${detail.proposal }</font><br>
								<hr>
								<font size="2"><b> Accepted :</b> ${detail.accepted }</font><br>
								<font size="2"><b> Username : </b>  ${detail.username }</font><br>
								<font size="2"><b> Phone Number : </b>  ${detail.phno }</font><br>
								<font size="2"><b> Accepted :</b> ${detail.accepted }</font><br>
								<%-- <input type="hidden" id="proposerId" value=<%=request.getSession().getAttribute("userid")%>>  --%>
								
								<%-- <c:if test="${what=='reqbooks'}">
								<input type="hidden" id="postid" value=${book.postId }>
								</c:if>
								<c:if test="${what=='mybooks'}">
								<input type="hidden" id="postid" value=${book.bookId }>
								</c:if> --%>
			                    <div class="text-right">
			                        <button type="submit" onclick="javascript:submitProposal();" class="btn btn-success btn-lg">Submit</button>
			                    </div>
								<hr>   
			                    
			                </div>
			                </c:forEach>
			          	</form>
			         <%-- </c:if> --%>
			        
			        </c:otherwise>
			      </c:choose>
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
