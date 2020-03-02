<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
    <title>Java Sample</title>

    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="page-header">
                <h1><span style="color: ${style};">This Container IP is ${myIp}</span></h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <h3>Registered Containers On Redis</h3>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <table class="table" id="table">
                <thead>
                <tr>
                    <th>Host</th>
                    <th>Port</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${containers}" var="item">
                    <tr>
                        <td>${item.host}</td>
                        <td>${item.port}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <hr>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <h3>Sample Data On MySql</h3>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <table class="table" id="table">
                <thead>
                <tr>
                    <th>User ID</th>
                    <th>User Name</th>
                    <th>User Age</th>
                    <th>User Gender</th>
                    <th>User Status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="item">
                    <tr>
                        <td>${item.userId}</td>
                        <td>${item.userName}</td>
                        <td>${item.userAge}</td>
                        <td>${item.userGender}</td>
                        <td>${item.userStatus}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <hr>
        </div>
    </div>

</div>
<script src="//rawgithub.com/stidges/jquery-searchable/master/dist/jquery.searchable-1.0.0.min.js"></script>

</body>
</html>
