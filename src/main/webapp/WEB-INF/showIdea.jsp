<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ page isErrorPage="true" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
                crossorigin="anonymous">
<meta charset="UTF-8">
<title>Show Idea</title>
</head>
<body>
<jsp:include page="base.jsp"></jsp:include>

<h1 class="m-2">View the Idea</h1>  
<h5 class="m-2">Welcome, ${user.firstName} ${user.lastName}</h5>
<hr>
<hr>

<h3 class="m-4">${idea.name}</h3>
<h5 class="m-4">Created On: <fmt:formatDate type="date" value="${idea.createdAt}" />
<h4 class="m-4">Created by: ${idea.creator.firstName} ${idea.creator.lastName}</h4>



<table class="m-4 table table-hover">
    <thead>
        <tr>
            <th>This has been Liked By ${idea.usersLiked.size()} user(s)</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${idea.usersLiked}" var="user">
        <tr>
            <td> ${user.firstName} ${user.lastName}</td>
        </tr>
        </c:forEach>
    </tbody>
</table>

<div class="container">
<c:choose>
    <c:when test="${ idea.creator.id == user.id }">
        <br>
        <h5 class="m-4">Since you created the Idea, Would you like to Edit it?</h5>
        
        <a href="/ideas/edit/${idea.id}" class="m-4 btn btn-primary ">Edit</a>

    </c:when>
    <c:otherwise>
        <c:choose>
        <c:when test="${ idea.usersLiked.contains(user)}">
            <a href="/ideas/${idea.id}/unlike/${user.id}" class="btn btn-warning">Unlike</a>
		</c:when>
        <c:otherwise>
        <a href="/ideas/${idea.id}/like/${user.id}" class="btn btn-primary">Like</a>
		</c:otherwise>
		</c:choose>
    </c:otherwise>
</c:choose>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
</body>
</html>