<%-- Página de confirmación del pedido --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" import="java.util.*, es.studium.LibreriaMVC.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Confirmación</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		
	</head>
	<body>
		<div class="w-50 mx-auto py-5">
			<h1>Librería MVC: Confirmación</h1>
			<p>
				<strong>Has comprado los siguientes libros:</strong>
			</p>
			<table class="table table-striped">
				<tr>
					<th>Autor</th>
					<th>Título</th>
					<th>Precio</th>
					<th>Cantidad</th>
				</tr>
				<%
				// Muestra los elementos del carrito 
				ArrayList<ElementoPedido> cesta = (ArrayList<ElementoPedido>) session.getAttribute("carrito");
				for (ElementoPedido item : cesta) {
				%>
				<tr>
					<td><%=item.getAutor()%></td>
					<td><%=item.getTitulo()%></td>
					<td><%=item.getPrecio()%> €</td>
					<td><%=item.getCantidad()%></td>
				</tr>
				<%
				
		
				}
				session.removeAttribute("carrito");
				%>
				<tr>
					<th align="right" colspan="2">Total</th>
					<th align="right"><%=request.getAttribute("precioTotal")%></th>
					<th align="right"><%=request.getAttribute("cantidadTotal")%></th>
				</tr>
			</table>
			<br />
			<a href="shopping">Pulsa aquí para realizar otro pedido</a>
		</div>
	</body>
</html>