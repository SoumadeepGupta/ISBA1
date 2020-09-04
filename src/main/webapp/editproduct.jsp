<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>ProductMaster Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="" class="navbar-brand"> ProductMaster Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">ProductMasters</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${showEditProductForm != null}">
                            <form action="admin?action=updateproduct" method="post">
                        </c:if>
                        

                        <caption>
                            <h2>
                                <c:if test="${showEditProductForm != null}">
                                    Edit Product
                                </c:if>
                                
                            </h2>
                        </caption>

                        <c:if test="${showEditProductForm != null}">
                            <input type="hidden" name="id" value="<c:out value='${showEditProductForm.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Product Name</label> <input type="text" value="<c:out value='${showEditProductForm.productName}' />" class="form-control" name="productName" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Product Cost</label> <input type="text" value="<c:out value='${showEditProductForm.cost}' />" class="form-control" name="cost">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Product Description</label> <input type="text" value="<c:out value='${showEditProductForm.productDescription}' />" class="form-control" name="productDescription">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>