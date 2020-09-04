
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <!-- <head>
            <title>Corona Kit-All Login(User)</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head> -->

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
                            <form action="user?action=showproducts" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                
                                <c:if test="${productMaster == null}">
                                    User Details
                                </c:if>
                            </h2>
                        </caption>

                        <fieldset class="form-group">
                            <label>Full Name</label> <input type="text" value="<c:out value='${productMaster.productName}' />" class="form-control" name="fullName" id="fullName" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>E-Mail ID</label> <input type="email" id="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2, 4}$" class="form-control">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Mobile Number</label> <input type="text" title="Enter Valid Mob No" pattern="[1-9]{1}[0-9]{9}" class="form-control" id="MobileNumber" name="MobileNumber"> 
                          <!--  <input type="number" pattern=".{10}" title="Enter Valid Mob No" class="form-control" id="MobileNumber" name="MobileNumber">-->
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>
</html>