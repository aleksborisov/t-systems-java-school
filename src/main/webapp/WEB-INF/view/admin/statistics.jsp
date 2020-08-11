<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Statistics</title>
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
                    <a class="btn btn-primary" href="#" role="button">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-bag" fill="currentColor"
                             xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                  d="M14 5H2v9a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1V5zM1 4v10a2 2 0 0 0 2
                                  2h10a2 2 0 0 0 2-2V4H1z"></path>
                            <path d="M8 1.5A2.5 2.5 0 0 0 5.5 4h-1a3.5 3.5 0 1 1 7 0h-1A2.5 2.5 0 0 0 8 1.5z"></path>
                        </svg>
                        <span class="badge badge-light">0</span>
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
            <div class="col">
                <p>
                <h1>Top 10 products</h1>
                <core:if test="${productsStatisticsDto.size() == 0}">
                    <h2><b>No product has been sold yet!</b></h2>
                </core:if>
                <core:if test="${productsStatisticsDto.size() != 0}">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">UPC</th>
                            <th scope="col">Name</th>
                            <th scope="col">Color</th>
                            <th scope="col">Brand</th>
                            <th scope="col">Category</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity sold</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%! int i = 1; %>
                        <core:forEach items="${productsStatisticsDto}" var="productStatisticsDto">
                            <tr>
                                <th scope="row"><%=i++%>
                                </th>
                                <td>${productStatisticsDto.upc}</td>
                                <td>${productStatisticsDto.name}</td>
                                <td>${productStatisticsDto.color}</td>
                                <td>${productStatisticsDto.brand}</td>
                                <td>${productStatisticsDto.category}</td>
                                <td>$${productStatisticsDto.price}</td>
                                <td><b>${productStatisticsDto.quantitySold}</b></td>
                            </tr>
                        </core:forEach>
                        <% i = 1; %>
                        </tbody>
                    </table>
                </core:if>

                <p>
                <h1>Top 10 customers</h1>
                <core:if test="${customersStatisticsDto.size() == 0}">
                    <h2><b>No customer has made a purchase yet!</b></h2>
                </core:if>
                <core:if test="${customersStatisticsDto.size() != 0}">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">First name</th>
                            <th scope="col">Last name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Purchases total</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%! int j = 1; %>
                        <core:forEach items="${customersStatisticsDto}" var="customerStatisticsDto">
                            <tr>
                                <th scope="row"><%=j++%>
                                </th>
                                <td>${customerStatisticsDto.firstName}</td>
                                <td>${customerStatisticsDto.lastName}</td>
                                <td>${customerStatisticsDto.email}</td>
                                <td><b>$${customerStatisticsDto.purchasesTotal}</b></td>
                            </tr>
                        </core:forEach>
                        <% j = 1; %>
                        </tbody>
                    </table>
                </core:if>

                <p>
                <h1>Income for the last week:</h1>
                <h2><b>$${lastWeekIncome}</b></h2>

                <p>
                <h1>Income for the last month:</h1>
                <h2><b>$${lastMonthIncome}</b></h2>

            </div>
        </div>
    </div>
</article>
</body>
</html>