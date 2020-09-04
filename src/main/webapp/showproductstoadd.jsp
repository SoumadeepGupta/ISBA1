<script type="text/javascript">
function incrementValue(id)
{
    var value = parseInt(document.getElementById('quantity'+id).value, 10);
    value = isNaN(value) ? 0 : value;
    value++;
    document.getElementById('quantity'+id).value = value;
}
function decrementValue(id)
{
	
    var value = parseInt(document.getElementById('quantity'+id).value, 10);
    if(value > 0)
	{
    value = isNaN(value) ? 0 : value;
    value--;
    document.getElementById('quantity'+id).value = value;
		}
}
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Corona Kit-All Products(User)</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>
		<jsp:include page="header.jsp"/>
		<hr/>
         <!--     <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="" class="navbar-brand"> Corona Kit-All </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">ProductMaster</a></li>
                    </ul>
                </nav>
            </header>-->
            <br>
			
			<form action="user?action=showkit" method="post">
            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Products</h3>
                    <hr>
                   
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                
                                <th>Product</th>
                                <th>Cost</th>
                                <th>Description</th>
                                <th>Quantity</th>
                                <th></th>
                                <th></th>
                               
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="productMaster" items="${listAllProducts}">

                                <tr>
                                    <!-- td>
                                        <c:out value="${productMaster.id}" />
                                    </td-->
                                    <td>
                                        <c:out value="${productMaster.productName}" />
                                    </td>
                                    <td>
                                        <c:out value="${productMaster.cost}" />
                                    </td>
                                    <td>
                                      	  <c:out value="${productMaster.productDescription}" />
                                    </td>
                                    <td>
                            		 <input type=text min="0" step="1" oninput="validity.valid||(value='');" pattern="[0-9]*" title="quantity" id="quantity${productMaster.id}" name="quantity${productMaster.id}" maxlength="4" size="4">
                        			</td>
                                    <td><input type="button" onclick="incrementValue(${productMaster.id})" value="Add"  maxlength="4" size="4" /></td>
                                    <td><input type="button" onclick="decrementValue(${productMaster.id})" value="Remove"  maxlength="4" size="4"  /></td>
                                
                                </tr>
                            </c:forEach>
                            <!-- } -->
                            
                        </tbody>

                    </table>
                    <div class="container text-right">
            <button type="submit" class="btn btn-success">PROCEED</button></div>
                       <!--  <a href="user?action=showkit" class="btn btn-success" >PROCEED</a> -->
                    	</div>
                </div>
            </div>
           <hr/>	
           </form>
		<jsp:include page="footer.jsp"/>
        </body>

        </html>