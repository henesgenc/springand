<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"
    import="java.util.ArrayList, edu.aydin.sda.Entity.Colors" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
<style>
td{
	border:1px solid;
	padding: 5px;
	text-align:center;
}
#menu{
width:10%;
position:fixed;
top:0px;
left:0px;
height:100%;
background-color:#ffe6c6;
padding:50px;
}
#menu a{
text-decoration:none;
color:black;
font-size:x-large;
}
#menu a:hover{
color:blue;
}
#container{
position:absolute;
left:19%;
top:5%;
}
</style>
<title>AdminPanelProducts</title>
</head>
<body>
<div id="menu">
<h2>Menu</h1>
<a href="./adminPanelUsers">Users</a><br>
<a href="./adminPanelProducts">Products</a><br>
<a href="./AddProduct">Add Product</a><br>
</div>
<div id="container">
<table style="margin-right: 50px; margin-bottom: 50px">
<tr style="font-weight: bold;">
<td>ID</td><td>Name</td><td>Price</td><td>Image</td><td>Type</td><td>Colors</td><td>Detail</td><td>Stocks</td>
</tr>
<c:forEach items ="${products}" var="value">
<tr>
<td><c:out value = "${value.getID()}"/></td>
<td><c:out value = "${value.getProduct_Name()}"/></td>
<td>₺<c:out value = "${value.getPrice()}"/></td>
<td>/img/<c:out value = "${value.getImage()}"/></td>
<td><c:out value = "${value.getProduct_Type()}"/></td>
<td><c:forEach items = "${value.getColors()}" var="x"><c:out value = "${x.getColor()}"/><br></c:forEach></td>
<td><c:out value = "${value.getDetail()}"/></td>
<td><c:out value = "${value.getStock().getStock()}"/></td>
<td><a href="./editProduct<c:out value = "${value.getID()}"/>">Edit</a></td>
<td><a href="./deleteProduct<c:out value = "${value.getID()}"/>">Delete</a></td>
<td><a href="./addColor<c:out value = "${value.getID()}"/>">Add Color</a></td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>