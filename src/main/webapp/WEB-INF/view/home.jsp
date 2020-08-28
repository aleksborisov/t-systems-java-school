<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Mars Army Store</title>
    <%@ include file="/WEB-INF/view/head.jsp" %>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/view/header.jsp" %>
</header>
<article>
    <img src="${pageContext.request.contextPath}/images/home.jpg" class="img-fluid" alt="">
</article>
</body>
</html>