<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ page isErrorPage="true" %> 

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
                crossorigin="anonymous">
<meta charset="UTF-8">
<title>DashBoard</title>
</head>
<body>
<jsp:include page="base.jsp"></jsp:include>

<h1 class="m-2">Idea Factory</h1>

<h5 class="m-2">Welcome, ${user.firstName} ${user.lastName}</h5>
<hr>
<%-- this link doesnt work --%>
<%-- <a href="/dashboardhigh">High List</a> --%>

<div class="container">
<table class="m-2 table table-hover ">
    <tr class="table-info">
        <th>Ideas</th>
        <th>Created By</th>
        <th>Likes</th>
        <th>Action</th>
        <th>Creator Actions<th>
    </tr>
    <tbody>
        <c:forEach items="${ideas}" var="idea">
            <tr>
                <td><a href="/ideas/show/${idea.id}">${idea.name}</a></td>
                <td>${idea.creator.firstName} ${idea.creator.lastName}</td>

                <td>${idea.usersLiked.size()}</td> 
                <td>
					<c:choose>
						<c:when test="${ idea.usersLiked.contains(user)}">
							<a href="/ideas/${idea.id}/unlike/${user.id}">Unlike</a>
						</c:when>
						<c:otherwise>
						<a href="/ideas/${idea.id}/like/${user.id}">Like</a>
						</c:otherwise>
					</c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${idea.creator.id == user.id}">
                            <a href="/ideas/edit/${idea.id}">Edit</a>
                        </c:when>
                        <c:otherwise>
                            <span>Not your Idea</span>
                        </c:otherwise>
                    </c:choose>
                </td>

            </tr>
        </c:forEach>
    </tbody>
</table>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>

</body>
</html>