<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Catalog</title>
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
                <a class="nav-item nav-link active" href="#">About</a>
                <a class="nav-item nav-link active" href="#">Admin</a>
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
                <div class="btn-group" role="group">
                    <a class="btn btn-secondary" href="#" role="button">Sign up</a>
                    <a class="btn btn-secondary" href="#" role="button">Sign in</a>
                </div>
            </li>
        </ul>
    </nav>
</header>
<article>
    <div class="container mt-3">
        <form:form method="get" action="/catalog" modelAttribute="categories">
            <div class="row">
                <div class="col">
                    <select id="categoryDto" name="categoryDto" class="form-control">
                        <option value="">Category</option>
                        <core:forEach var="category" items="${categories}">
                            <option value="${category.name}">${category.name}</option>
                        </core:forEach>
                    </select>
                </div>
                <div class="col">
                    <input class="form-control" id="name" name="name" type="text" placeholder="Name"/>
                </div>
                <div class="col">
                    <input class="form-control" id="minPrice" name="minPrice" value="" min="1" max="1000000"
                           pattern="^[0-9]+$" placeholder="Min price"/>
                </div>
                <div class="col">
                    <input class="form-control" id="maxPrice" name="maxPrice" value="" min="1" max="1000000"
                           pattern="^[0-9]+$" placeholder="Max price"/>
                </div>
                <div class="col">
                    <input class="form-control" id="brand" name="brand" type="text" placeholder="Brand"/>
                </div>
                <div class="col">
                    <input class="form-control" id="color" name="color" type="text" placeholder="Color"/>
                </div>
                <div class="col">
                    <input type="submit" value="Filter" class="btn btn-secondary">
                </div>
            </div>
        </form:form>
    </div>
    <div class="container">
        <div class="row row-cols-3">
            <core:forEach items="${products}" var="product">
                <div class="col mt-3">
                    <div class="card text-center" style="width: 20rem;">
                        <img src="https://i.ebayimg.com/images/g/CRUAAOSwVmNdTvTj/s-l1600.jpg" class="card-img-top"
                             alt="">
                        <div class="card-body">
                            <h5 class="card-title">${product.name}</h5>
                            <h6 class="card-subtitle mb-2 text-muted">${product.categoryDto}</h6>
                            <div class="card-text text-left">Brand: ${product.brand}</div>
                            <div class="card-text text-left">Color: ${product.color}</div>
                            <div class="card-text text-left">Weight: ${product.weight} g</div>
                            <div class="card-text text-left">HxWxD:
                                    ${product.height}x${product.width}x${product.depth} mm
                            </div>
                            <div class="card-text text-left">UPC: ${product.upc}</div>
                            <div class="card-text text-left">Quantity: ${product.inStock}</div>
                            <h5 class="card-title">$${product.price}</h5>
                            <a href="#" class="btn btn-primary btn-lg btn-block">Buy</a>
                            <a href="${pageContext.request.contextPath}/admin/edit_product?upc=${product.upc}"
                               class="btn btn-warning btn-lg btn-block">Edit</a>
                            <a href="${pageContext.request.contextPath}/admin/delete_product?upc=${product.upc}"
                               class="btn btn-danger btn-lg btn-block">Delete</a>
                        </div>
                    </div>
                </div>
            </core:forEach>
        </div>
    </div>
</article>
</body>
</html>
