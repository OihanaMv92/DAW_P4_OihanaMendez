<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page session="true" import="java.util.*, es.studium.LibreriaMVC.*"%>
<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>TITULO</title>
			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		
	</head>
	<body>
		<div class="w-50 mx-auto py-5">
			<h1>Alta libros</h1>
			
			<%
			String info = (String) session.getAttribute("info");
			if(info != null){
				out.println(info);
			}
			%>
			
			<form action="ServletLibros" method="POST">
				<input type="hidden" name="todo" value="altaLibro">
			 	<p>Nombre libro: <input type="text" name="nombre" class="form-control"></p>
			  	<p>Precio libro: <input type="number" name="precio" class="form-control"></p>
		    	<p>Cantidad libro: <input type="number" name="cantidad" class="form-control"></p>
		  		<label>Autor:</label>
		  		<select class="form-select" name="idAutor">
		  			<%=Autores.getAutores()%>
		  		</select>
		  		<label>Editorial:</label>
		  		<select class="form-select" name="idEditorial">
		  			<%=Editoriales.getEditoriales()%>
		  		</select>
		  
				<p class="mt-4">
				    <input type="submit" value="Enviar" class="btn btn-success">
				    <input type="reset" value="Borrar" class="btn btn-danger">
				    <a class="btn btn-warning" href="libros.jsp">Volver</a>
				</p>
			</form>
			
		</div>
	</body>
</html>