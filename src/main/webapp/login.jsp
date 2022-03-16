<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>

	<div class="container py-5">

		<div class="w-50 mx-auto">
			<form method="POST" action="login">
				<h2>Acceso</h2>

				<div class="mb-3">
					<label for="usuario" class="form-label">Introduce tu
						usuario:</label> 
						
						<input type="text" name="usuario" class="form-control" id="usuario">
				</div>
				<div class="mb-3">
					<label for="exampleInputPassword1" class="form-label">Introduce	tu contraseña:</label> 
					<input type="password" name="password"	class="form-control" id="exampleInputPassword1">
				</div>



				<%
					String error = (String) session.getAttribute("error");
					if(error!=null){
						out.println("<div class='text-danger'>"+error+"</div>");
					}
				%>

				<input type="submit" name="Acceder" class="btn btn-primary" /> 
				<input type="reset" name="Cancelar" class="btn btn-danger" />

			</form>
		</div>
	</div>


</body>
</html>