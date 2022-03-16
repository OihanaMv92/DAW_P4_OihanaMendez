<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page session="true" import="java.util.*, es.studium.LibreriaMVC.*"%>
<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>Editoriales</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		
	</head>
	<body style="background: url('./fondo.jpg') no-repeat center;" class="vh-100 pt-5">
		<div class="w-50 mx-auto py-5 bg-light bg-opacity-75 p-4">
			<h1 class="mb-5 text-center">Editoriales.</h1>
			
			<table class="table table-striped table-dark">
				<thead>
					<tr>
						<th> ID </th>
						<th> Nombre </th>
					</tr>
				</thead>
				<tbody>
					<%=Editoriales.getTablaEditoriales()%>
				</tbody>
			</table>
			<a class="btn btn-warning mb-3 w-100" href="pantallaPrincipalGestor.jsp">Volver</a>
		</div>	
	</body>
</html>