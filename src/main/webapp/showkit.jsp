<script type="text/javascript">

</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Corona Kit Products(User)</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>
		<jsp:include page="header.jsp"/>
		<hr/>
       <!--       <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="" class="navbar-brand"> Corona Kit-All </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">KitDetail</a></li>
                    </ul>
                </nav>
            </header>-->
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Products</h3>
                    <hr>
                    <div class="container text-left">
					<input name="action" onclick="history.back()" type="submit" value="Edit Cart"/>
                     
                    </div>
                    <form action="user?action=ordersummary" method="post">
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                               
                                <th>Product</th>
                                <th>Quantity</th>
                                <th>Amount</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="kitDetail" items="${listAllProductsInKit}">

                                <tr>
                                    <td>
                                        <c:out value="${kitDetail.productName}" />
                                    </td>
                                    <td>
                                        <c:out value="${kitDetail.quantity}" />
                                    </td>
                                    <td>
                                        <c:out value="${kitDetail.amount}" />
                                    </td>
                                    
                                   <!--   <td><a href="admin?action=editproduct&id=${kitDetail.productId}">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${kitDetail.productId}' />">Delete</a></td>-->
                                
                                </tr>
                            </c:forEach>
                            <c:forEach var="confirmedKitDetail" items="${confirmedKitDetail}">

                                <tr>
                                   <td><strong>TOTAL</strong></td><td></td>
                                    <td>
                                        <strong><c:out value="${confirmedKitDetail.value}" /></strong>
                                    </td>
                                    
                                    
                                   <!-- <td><a href="admin?action=editproduct&id=${kitDetail.productId}">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${kitDetail.productId}' />">Delete</a></td> -->
                                
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table> <div class="container text-right">
                    <button type="submit" class="btn btn-success">Proceed</button></div>
                </div>
                
               </form>
            </div>
           <hr/>	
		<jsp:include page="footer.jsp"/>
        </body>

        </html>