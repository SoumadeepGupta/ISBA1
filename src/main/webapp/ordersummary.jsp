<script type="text/javascript">
div {
	  padding: 35px;
	}
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Corona Kit Products(User)</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body style="margin:4; padding:4">
		<jsp:include page="header.jsp"/>
		<hr/>
		<hr/>
<% String FirstName = (String) request.getAttribute("FirstName"); %>
<% String Email = (String) request.getAttribute("Email"); %>
<% String MobileNo = (String) request.getAttribute("MobileNo"); %>
<%-- Required View Template --%>
<p><strong><%=FirstName %></strong>
<p><strong><%=Email %></strong>
<p><strong><%=MobileNo %></strong>
<hr/>
		<form action="user?action=placeorder" method="post">
		<div>
			<div><strong><label for="Address">Shipping Address:</label> </strong></div>
			<div><textarea id="Address" name="Address" rows="2" cols="50"></textarea></div>
		</div>

           <!--  <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="" class="navbar-brand"> Corona Kit-All </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">KitDetail</a></li>
                    </ul>
                </nav>
            </header> --> 
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">ORDER SUMMARY</h3>
                    <hr>
                   
                    
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                               
                                <th>Total Items</th>
                                <th>Total Cost</th>
                               
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="confirmedKit" items="${confirmedKit}">

                                <tr>
                                    <td>
                                        <c:out value="${confirmedKit.key}" />
                                    </td>
                                    <td>
                                        <c:out value="${confirmedKit.value}" />
                                    </td>
                                    
                                    
                                   <!-- <td><a href="admin?action=editproduct&id=${kitDetail.productId}">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${kitDetail.productId}' />">Delete</a></td> -->
                                
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
                
            </div>
             <div class="container text-right">
            <button type="submit" class="btn btn-success">Confirm Order</button></div>
            </form>
            
           <hr/>	
           
		<jsp:include page="footer.jsp"/>
        </body>

        </html>