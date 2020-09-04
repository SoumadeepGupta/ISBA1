<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

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
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${productMaster == null}">
                            <form action="admin?action=insertproduct" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                
                                <c:if test="${productMaster == null}">
                                    Add New Product
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${productMaster != null}">
                            <input type="hidden" name="id" value="<c:out value='${productMaster.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Product Name</label> <input type="text" value="<c:out value='${productMaster.productName}' />" class="form-control" name="productName" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Product Cost</label> <input type="text" value="<c:out value='${productMaster.cost}' />" class="form-control" name="cost">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Product Description</label> <input type="text" value="<c:out value='${productMaster.productDescription}' />" class="form-control" name="productDescription">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>