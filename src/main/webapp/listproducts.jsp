
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <!-- <head>
            <title>Corona Kit-All Products(Admin)</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head> -->
<!-- <style>
.myDiv {
  border: 5px outset red;
  background-color: lightblue;
  text-align: right;
}
</style>  -->
        <body>
        
		<jsp:include page="header.jsp"/>
		<hr/>
          <!-- <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="" class="navbar-brand"> Corona Kit-All </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">ProductMaster</a></li>
                    </ul>
                </nav>
            </header> -->
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Products</h3>
                    <hr>
                     <div class="container text-right" ">
	
                        <a href="admin?action=logout" class="btn btn-success" style="background-color:Grey;">Logout</a>
                    </div>
                    <div class="container text-left">
	
                        <a href="admin?action=newproduct" class="btn btn-success">Add
    				 New Product</a>
                    </div>
                   
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Product</th>
                                <th>Cost</th>
                                <th>Description</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="productMaster" items="${listAllProducts}">

                                <tr>
                                    <td>
                                        <c:out value="${productMaster.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${productMaster.productName}" />
                                    </td>
                                    <td>
                                        <c:out value="${productMaster.cost}" />
                                    </td>
                                    <td>
                                        <c:out value="${productMaster.productDescription}" />
                                    </td>
                                    <td><a href="admin?action=editproduct&id=${productMaster.id}">Edit</a><!--   &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${productMaster.id}' />">Delete</a>--></td>
                                
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
           <hr/>	
		<jsp:include page="footer.jsp"/>
        </body>

        </html>