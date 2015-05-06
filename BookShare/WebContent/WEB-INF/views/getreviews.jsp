<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
    <link href="<c:url value="/resources/css/freelancer.css" />" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet"  type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

</head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<script>
	$(document).ready(function(){
		$('#feedback').hide();
		
		$('#id1 a').click(function(){
			$('#hidden2').val($(this).attr("id"));
		});
		
	});
	
	function rateNow(id){
		$('#feedback').show();
		$('#hidden1').val(id);
	}
	
</script>

	<body id="page-top">
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
    
    	<div id="checkType" class="container">
		<h3>Please choose User to Rate</h3>
		<div id="id1">
		<a id="seller" href="<%= request.getContextPath()%>/feedback?action=rateBuyer&role=seller"><span>Rate Buyer</span></a>
		<br />
		<a id="buyer" href="<%= request.getContextPath()%>/feedback?action=rateSeller&role=buyer"><span>Rate Seller</span></a>
		</div>
		<c:if test="${not empty ratingsTo}">
			<c:forEach var="ratingTo" items="${ratingsTo}">
				<div class="well" style="color:#2c3e50" id="div${ratingTo[0].userId.userid}">
				<h3><b>Rate </b>${ratingTo[0].userId.firstname}<a href="javascript:rateNow(${ratingTo[0].userId.userid});"> here</a></h3>
				</div>
				</c:forEach>
				</c:if>
		<div class="container" id="feedback" >
			<form id="feedbackForm" method="post" action="<%= request.getContextPath()%>/feedback/save">
       		<fieldset>
         	<legend>Your Feedback,</legend>
         	<input type="text" name="comment" placeholder="Write your feedback here" width="500px" height="100px">
         	<p>
            <label>Rating?</label>
             <select id = "myList" name="rating">
               <option value = "1">1</option>
               <option value = "2">2</option>
               <option value = "3">3</option>
               <option value = "4">4</option>
               <option value = "4">5</option>
             </select>
          </p>
          <input type="hidden" name="ratingTo" id="hidden1">
          <input type="hidden" name="userRole" id="hidden2" value="${role }">
          <div class="form-group col-xs-10">
          	<button type="submit" class="btn btn-success btn-lg">Rate</button>
          </div>
       </fieldset>
    </form>
		</div>
	</div>
		<script src="<c:url value="/resources/forwards/js/bootstrap.min.js" />"></script>
</body>
</html>