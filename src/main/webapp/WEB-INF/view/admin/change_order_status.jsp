<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Change order status</title>
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
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-6">
                <h1>Change order status</h1>
                <form:form method="post" action="/admin/change_order_status" modelAttribute="orderDto">
                    <form>
                        <div class="form-group row">
                            <label for="paymentStatus" class="col-sm-2 col-form-label">Payment status</label>
                            <div class="col-sm-10">
                                <form:select path="paymentStatus" cssClass="form-control">
                                    <form:option value="AWAITING_PAYMENT"/>
                                    <form:option value="PAID"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="orderStatus" class="col-sm-2 col-form-label">Order status</label>
                            <div class="col-sm-10">
                                <form:select path="orderStatus" cssClass="form-control">
                                    <form:option value="AWAITING_PAYMENT"/>
                                    <form:option value="AWAITING_SHIPMENT"/>
                                    <form:option value="SHIPPED"/>
                                    <form:option value="DELIVERED"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-10">
                                <button type="submit" class="btn btn-warning btn-lg">Change status</button>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-10">
                                <form:input path="id" type="hidden" name="id"
                                            value="${orderDto.id}" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-10">
                                <form:input path="customerDto" type="hidden" name="customerDto"
                                            value="${orderDto.customerDto}" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-10">
                                <form:input path="paymentMethod" type="hidden" name="paymentMethod"
                                            value="${orderDto.paymentMethod}" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-10">
                                <form:input path="deliveryMethod" type="hidden" name="deliveryMethod"
                                            value="${orderDto.deliveryMethod}" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-10">
                                <form:input path="address" type="hidden" name="address"
                                            value="${orderDto.address}" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-10">
                                <form:input path="total" type="hidden" name="total"
                                            value="${orderDto.total}" cssClass="form-control"/>
                            </div>
                        </div>
                    </form>
                </form:form>
            </div>
        </div>
    </div>
</article>
</body>
</html>
