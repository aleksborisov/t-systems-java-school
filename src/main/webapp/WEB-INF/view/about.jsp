<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>About</title>
    <%@ include file="/WEB-INF/view/head.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/view/header.jsp" %>
</header>
<article>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-5">
                <h1 class="text-center">About</h1>
                <h3 class="text-center">
                    Our store has been pleasing its customers with reliable, inexpensive and authentic equipment
                    of Russian and Soviet production since 2015. Join the army of satisfied customers and experience
                    legendary reliability, comrade!
                </h3>
                <img src="${pageContext.request.contextPath}/images/about.jpg" class="img-fluid" alt="">
            </div>
        </div>
    </div>
</article>
</body>
</html>
