<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Color</title>
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
top:5%;
position:absolute;
left:19%;
}
input{
width:100%;
}
textarea{
width:100%;
}
</style>
</head>
<body>
<div id="menu">
<h2>Menu</h2>
<a href="./adminPanelUsers">Users</a><br>
<a href="./adminPanelProducts">Products</a><br>
<a href="./AddProduct">Add Product</a><br>
</div>
<div id="container">
<form action="addColor" method="post" name="edit">
<table>
<tr>
<td><p>ID : </p></td>
<td><input type="text" name="id" readonly="readonly" value="<c:out value="${product.getID()}"/>"></td>
</tr>
<tr>
<td><p>Color : </p></td>
<td><input type="text" name="color" value="" placeholder="Write Color"/></td>
</tr>
<tr>
<td colspan="2" >
<input type="submit"  style="width:100%; height:30px">
</td>
</tr>
</table>
</form>
</div>
</body>
</html>