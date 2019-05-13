<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<style>
td{ border: 1px solid; width:100px; }
#menu{ width:10%; position:fixed; top:0px; left:0px; height:100%; background-color:#ffe6c6; padding:4%; }
#menu a{ text-decoration:none; color:black; font-size:x-large; }
#menu a:hover{ color:blue; }
table{ margin-left:18%;}
</style>
<title>Admin Panel Users</title>
</head>
<body>
<div id="menu">
<h2>Menu</h1>
<a href="./adminPanelUsers">Users</a><br>
<a href="./adminPanelProducts">Products</a><br>
<a href="./adminPanelAddProduct.jsp">Add Product</a><br>
</div>
<table>
<tr style="font-weight: bold;">

<td>ID</td><td>Username</td><td>Password</td><td>Name</td><td>Surname</td><td>Birthdate</td>
</tr>
<c:forEach items ="${users}" var="value">
<tr>
<td><c:out value="${value.getID()}"/></td>
<td><c:out value="${value.getUsername()}"/></td>
<td><c:out value="${value.getUser_password()}"/></td>
<td><c:out value="${value.getUser_name()}"/></td>
<td><c:out value="${value.getUser_surname()}"/></td>
<td><c:out value="${value.getUser_birthdate()}"/></td>
<td><a href="./editUser<c:out value="${value.getID()}"/>">Edit</a></td>
<td><a href="./deleteUser<c:out value="${value.getID()}"/>">Delete</a></td>
</tr>
</c:forEach>
</table>
</body>
</html>
