<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cart</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.0.1">

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/navbar-fixed/">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/"><b>Mars Army Store</b></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/catalog">Catalog</a>
                <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/about">About</a>
                <sec:authorize access="hasRole('ADMIN')">
                    <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/admin/admin">Admin</a>
                </sec:authorize>
            </div>
        </div>
        <ul class="navbar-nav ml-md-auto">
            <li class="nav-item">
                <div style="margin-right: 20px">
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/cart/cart" role="button">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-bag" fill="currentColor"
                             xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                  d="M14 5H2v9a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1V5zM1 4v10a2 2 0 0 0 2
                                  2h10a2 2 0 0 0 2-2V4H1z"></path>
                            <path d="M8 1.5A2.5 2.5 0 0 0 5.5 4h-1a3.5 3.5 0 1 1 7 0h-1A2.5 2.5 0 0 0 8 1.5z"></path>
                        </svg>
                        <span class="badge badge-light">
                            <core:out value="${sessionScope.cartSize}" default="0"/>
                        </span>
                    </a>
                </div>
            </li>
            <li class="nav-item">
                <sec:authorize access="!isAuthenticated()">
                    <div class="btn-group" role="group">
                        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/sign_up"
                           role="button">Sign up</a>
                        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/login"
                           role="button">Sign in</a>
                    </div>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <div class="btn-group" role="group">
                        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/account/account"
                           role="button">Account</a>
                        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/logout"
                           role="button">Sign out</a>
                    </div>
                </sec:authorize>
            </li>
        </ul>
    </nav>
</header>
<article>
    <core:if test="${sessionScope.cartSize < 1}">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-5 text-center">
                    <h1>
                        Your cart is empty.<br>
                        Let's buy something!
                    </h1>
                    <a href="${pageContext.request.contextPath}/catalog" class="btn btn-primary btn-lg">Go to
                        catalog</a>
                </div>
            </div>
        </div>
    </core:if>
    <core:if test="${sessionScope.cartSize > 0}">
        <div class="container">
            <div class="row">
                <div class="col">
                    <h1>Shopping cart</h1>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Color</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <%! int i = 1; %>
                        <core:set var="total" value="${0}"/>
                        <core:forEach items="${productsDtoMap}" var="entry">
                            <tr>
                                <th scope="row"><%=i++%>
                                </th>
                                <td>${entry.key.name}</td>
                                <td>${entry.key.color}</td>
                                <td>$${entry.key.price}</td>
                                <td>${entry.value}</td>
                                <td><a href="${pageContext.request.contextPath}/cart/delete_from_card?upc=${entry.key.upc}"
                                       class="btn btn-danger btn-sm">Delete</a></td>
                            </tr>
                            <core:set var="total" value="${total + entry.key.price * entry.value}"/>
                        </core:forEach>
                        <% i = 1; %>
                        </tbody>
                    </table>
                    <h3>Total: <core:out value="$${total}"/></h3><br>
                    <sec:authorize access="isAnonymous()">
                        <h1>Please sign in or sign up to continue shopping!</h1>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <form:form method="post" action="/cart/cart" modelAttribute="orderDto">
                            <form>
                                <div class="form-group row">
                                    <label for="paymentMethod" class="col-sm-2 col-form-label">Payment method</label>
                                    <div class="col-sm-10">
                                        <form:select path="paymentMethod" cssClass="form-control">
                                            <form:option value="CASH"/>
                                            <form:option value="CARD"/>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="deliveryMethod" class="col-sm-2 col-form-label">Delivery method</label>
                                    <div class="col-sm-10">
                                        <form:select path="deliveryMethod" cssClass="form-control">
                                            <form:option value="RUSSIAN_POST"/>
                                            <form:option value="DHL"/>
                                        </form:select>
                                    </div>
                                </div>
                                <core:if test="${empty addresses}">
                                <div class="form-group row">
                                    <label for="address" class="col-sm-2 col-form-label">Address</label>
                                    <div class="col-sm-10">
                                        <a href="${pageContext.request.contextPath}/account/create_address"
                                           class="btn btn-danger btn-sm">Create address</a>
                                    </div>
                                </div>
                                </core:if>
                                <core:if test="${not empty addresses}">
                                    <div class="form-group row">
                                        <label for="address" class="col-sm-2 col-form-label">Address</label>
                                        <div class="col-sm-10">
                                            <form:select path="address" cssClass="form-control">
                                                <form:options items="${addresses}"/>
                                            </form:select>
                                        </div>
                                    </div>
                                </core:if>
                                <div class="form-group row">
                                    <div class="col-sm-10">
                                        <button type="submit" class="btn btn-primary btn-lg">Checkout</button>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-10">
                                        <form:input path="customerDto" type="hidden" name="customerDto"
                                                    value="${customerDto}" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-10">
                                        <form:input path="paymentStatus" type="hidden" name="paymentStatus"
                                                    value="AWAITING_PAYMENT" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-10">
                                        <form:input path="orderStatus" type="hidden" name="orderStatus"
                                                    value="AWAITING_PAYMENT" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-10">
                                        <form:input path="total" type="hidden" name="total"
                                                    value="${total}" cssClass="form-control"/>
                                    </div>
                                </div>
                            </form>
                        </form:form>
                    </sec:authorize>
                </div>
            </div>
        </div>
    </core:if>
</article>
</body>
</html>