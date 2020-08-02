<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Delete product</title>
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
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-5">
                <h1>Are you sure you want to remove this product?</h1>
                <form:form method="post" action="/admin/delete_product" modelAttribute="productDto">
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-danger btn-lg">Delete</button>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <form:input path="name" type="hidden" name="name"
                                        value="${productDto.name}" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <form:input path="categoryDto" type="hidden" name="categoryDto"
                                        value="${productDto.categoryDto}" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <form:input path="brand" type="hidden" name="brand"
                                        value="${productDto.brand}" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <form:input path="color" type="hidden" name="color"
                                        value="${productDto.color}" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <form:input path="weight" type="hidden" name="weight"
                                        value="${productDto.weight}" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <form:input path="height" type="hidden" name="height"
                                        value="${productDto.height}" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <form:input path="width" type="hidden" name="width"
                                        value="${productDto.width}" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <form:input path="depth" type="hidden" name="depth"
                                        value="${productDto.depth}" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <form:input path="upc" type="hidden" name="upc"
                                        value="${productDto.upc}" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <form:input path="inStock" type="hidden" name="inStock"
                                        value="${productDto.inStock}" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <form:input path="price" type="hidden" name="price"
                                        value="${productDto.price}" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <form:input path="deleted" type="hidden" name="deleted"
                                        value="${productDto.deleted}" cssClass="form-control"/>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</article>
</body>
</html>
