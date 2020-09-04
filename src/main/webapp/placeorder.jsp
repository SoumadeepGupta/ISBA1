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
	<h3 class="text-center">ORDER DETAILS</h3>
	<% String kitId = (String) request.getAttribute("kitId"); %>
	<div style="text-align:center;color:red"><h4><strong>CONFIRMED ORDER NO: <%=kitId %></strong></h4></div>
<% String FirstName = (String) request.getAttribute("FirstName"); %>
<% String Email = (String) request.getAttribute("Email"); %>
<% String MobileNo = (String) request.getAttribute("MobileNo"); %>
<% String Address = (String) request.getAttribute("Address"); %>

<%-- Required View Template --%>
<p><strong><%=FirstName %></strong>
<p><strong><%=Email %></strong>
<p><strong><%=MobileNo %></strong>
<p><strong><%=Address %></strong>
<hr/>
		
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
                    
                                      
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                               
                                                              <th><strong>Total Amount to be paid:</strong></th>
                               
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="confirmedKitDetail" items="${confirmedKitDetail}">

                                <tr>
                                   
                                    <td>
                                        <c:out value="Rs: ${confirmedKitDetail.value}" />
                                    </td>
                                    
                                    
                                   <!-- <td><a href="admin?action=editproduct&id=${kitDetail.productId}">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${kitDetail.productId}' />">Delete</a></td> -->
                                
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
                
            </div>
            <!--  <div class="container text-right">
            <button type="submit" class="btn btn-success">Proceed</button></div>--> 
           
            
           <hr/>	
           
		<jsp:include page="footer.jsp"/>
        </body>

        </html>