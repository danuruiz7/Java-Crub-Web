<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Producto</title>
</head>
<body>
	<h1>Editar Producto</h1>

	<form action="productos" method="post">
		<c:set var="producto" value="${producto }"></c:set>
		<input type="hidden" name="opcion" value="editar" /> <input
			type="hidden" name="id" value="${producto.id }" />
		<table border="1">
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre" size="50"
					value="${producto.nombre }" /></td>
			</tr>
			<tr>
				<td>Cantidad:</td>
				<td><input type="text" name="cantidad" size="50"
					value="${producto.cantidad }" /></td>
			</tr>
			<tr>
				<td>Precio:</td>
				<td><input type="text" name="precio" size="50"
					value="${producto.precio }" /></td>
			</tr>

		</table>

		<input type="submit" value="Crear">

	</form>

</body>
</html>