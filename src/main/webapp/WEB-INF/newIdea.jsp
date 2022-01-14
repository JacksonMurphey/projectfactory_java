<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
                crossorigin="anonymous">
<meta charset="UTF-8">
<title>New Idea</title>
</head>
<body>
<jsp:include page="base.jsp"></jsp:include>



<h1 class="m-2">Create A New Idea</h1>
<h5 class="m-2">Welcome, ${user.firstName} ${user.lastName}</h5>

<hr>

<strong>
        <p class="text-lg-left m-2" style="font-size: larger;"><form:errors path="idea.*"/></p>
</strong>
<form:form action="/ideas/new" method="POST" modelAttribute="idea" class="m-4">
    <p>
        <form:label path="name">Content:</form:label>
        <form:input path="name"/>
    </p>
    <form:hidden path="creator" value="${ user.id }"/>
    <input type="submit" value="Create"/>
</form:form>   


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
</body>
</html>