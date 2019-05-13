<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
<title>Edit Product</title>
</head>
<body>
<div id="menu">
<h2>Menu</h2>
<a href="./adminPanelUsers">Users</a><br>
<a href="./adminPanelProducts">Products</a><br>
<a href="./AddProduct">Add Product</a><br>
</div>
<div id="container">
<form action="editProduct" method="post" name="edit">
<table>
<tr>
<td><p>ID : </p></td>
<td><input type="text" name="id" readonly="readonly" value="<c:out value="${product.getID()}"/>"></td>
</tr>
<tr>
<td><p>Image : </p></td>
<td><input type="text" name="image" readonly="readonly" value="<c:out value="${product.getImage()}"/>"><input type="file" name="changeimage"></td>
</tr>
<tr>
<td><p>Name : </p></td>
<td><input type="text" name="name" value="<c:out value="${product.getProduct_Name()}"/>"></td>
</tr>
<tr>
<td><p>Type : </p></td>
<td><input type="text" name="type" value="<c:out value="${product.getProduct_Type()}"/>"></td>
</tr>
<tr>
<td><p>Price : </p></td>
<td><input type="number" name="price" value="<c:out value="${product.getPrice()}"/>"></td>
</tr>
<tr>
<td><p>Stok : </p></td>
 <td><input type="number" name="stock" value="${product.getStock().getStock()}"></td>
</tr>
<tr>
<td><p>Detail : </p></td>
<td><textarea name="detail" rows="10" cols="21"><c:out value="${product.getDetail()}"/></textarea></td>
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