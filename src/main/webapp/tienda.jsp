<%-- Página de órdenes de pedido --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" import="java.util.*, es.studium.LibreriaMVC.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Pedido</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	
</head>
<body>

	<div class="w-50 mx-auto py-5">
	
	
		<%
		// guardamos el usuario, por que si no existe, te llevo al login
		String usuario = (String) session.getAttribute("usuario");
		if(usuario == null){
			// no estamos logueados
			response.sendRedirect("./login.jsp");
		}
		%>
	
	
		<h1>Tienda <%=usuario %></h1>
		<p>
			<strong>Elegir un libro y la cantidad:</strong>
		</p>
		<form name="AgregarForm" action="shopping" method="POST">
			<input type="hidden" name="todo" value="add" class="form-select"> 
			Título: 
			<select	name="idLibro" class="form-select mb-4">
				<%
				// rellenas el desplegable
				LibreriaMVC.cargarDatos(); // solo carga la lista de LIBROS
				// Scriplet 1: Carga los libros en el SELECT
				for (int i = 0; i < LibreriaMVC.tamano(); i++) // una vuelta por cada liro, en la lista de LIBROS
				{
					out.println("<option value='" + i + "'>");
					out.println(LibreriaMVC.getTitulo(i) + " | " + LibreriaMVC.getAutor(i) + " | " + LibreriaMVC.getPrecio(i) + "€");
					out.println("</option>");
				}
				%>
			</select> 
			
			<label>Cantidad:</label>
			<input type="number" name="cantidad" size="10" value="1" class="form-control mb-4">
			
			<input type="submit" value="Añadir a la cesta" class="btn btn-success">
		</form>
		<hr />
		<br />
		
		
		
		<%
		// Scriplet 2: Chequea el contenido de la cesta
		ArrayList<ElementoPedido> cesta = (ArrayList<ElementoPedido>) session.getAttribute("carrito");
		
		if (cesta != null && cesta.size() > 0)	{
		%>
			<p>
				<strong>Tu cesta contiene:</strong>
			</p>
			<table class="table table-striped">
				<tr>
					<th>Autor</th>
					<th>Título</th>
					<th>Precio</th>
					<th>Cantidad</th>
					<th>&nbsp;</th>
				</tr>
				<%
				// Scriplet 3: Muestra los libros del carrito
				for (int i = 0; i < cesta.size(); i++)
				{
					ElementoPedido elementoPedido = cesta.get(i);
				%>
				<tr>
					<form name="borrarForm" action="shopping" method="POST">
					
						<input type="hidden" name="todo" value="remove">
						<input type="hidden" name="indiceElemento" value="<%=i%>">
						
						<td><%=elementoPedido.getAutor()%></td>
						<td><%=elementoPedido.getTitulo()%></td>
						<td ><%=elementoPedido.getPrecio()%> €</td>
						<td ><%=elementoPedido.getCantidad()%></td>
						<td><input type="submit" value="Eliminar de la cesta" class="btn btn-danger"></td>
					</form>
				</tr>
			<%
			}
			%>
		</table>
		<br />
		<form name="checkoutForm" action="shopping" method="POST">
			<input type="hidden" name="todo" value="checkout"> 
			<input	type="submit" class="btn btn-success" value="Confirmar compra">
		</form>
		<%
	}
	%>
	
	
	
		<a class="btn btn-danger mt-3" href="Logout">Salir</a>
	</div>

</body>
</html>
