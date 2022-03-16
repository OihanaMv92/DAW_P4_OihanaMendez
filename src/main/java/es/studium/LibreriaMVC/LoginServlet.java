package es.studium.LibreriaMVC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(
		name = "LoginServlet",
		urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// datos en general
		request.setCharacterEncoding("UTF-8");
		// instanciar cosas para la conexion
		Connection conn = null;
		Statement stmt = null;
		// 
		String nextPage = "/login.jsp";
		HttpSession session = request.getSession(false);

		try
		{

			// cargar el pool
			PoolServlet.cargarpool();
			
			// conectar a la base de datos
			conn = PoolServlet.pool.getConnection();
			stmt = conn.createStatement();


			// datos del FORMULARIO
			String usuario = request.getParameter("usuario");
			String password = request.getParameter("password");
			
			
			// comprobamos que los INPUTs no esten vacios
			if(usuario.length()==0)
			{
				session.setAttribute("error", "<p>Debes introducir tu usuario</p>");
			}
			else if(password.length()==0)
			{
				session.setAttribute("error", "<p>Debes introducir tu contraseña</p>");
			}
			else
			{
				StringBuilder sqlStr = new StringBuilder();

				// sentencia de SELECT
				sqlStr.append("SELECT * FROM usuarios WHERE ");
				sqlStr.append("STRCMP(usuarios.nombreUsuario,'").append(usuario).append("') = 0");
				sqlStr.append(" AND STRCMP(usuarios.passwdUsuario,MD5('").append(password).append("')) = 0");

				// ejecuta la sentencia
				ResultSet rset = stmt.executeQuery(sqlStr.toString());
				
				// comprobar que las credenciales son correctas o no.
				if(!rset.next()) {
					// credenciales incorrectas
					session.setAttribute("error", "<p>Nombre de usuario o contraseña incorrectos</p>");
				} else {
					// Si los datos introducidos son correctos

					// privilegio del usuario
					String privilegioUsuario = (String) rset.getString("tipoUsuario");
					
					
					if(privilegioUsuario.equals("0")) { // no tiene permisos
						nextPage = "/shopping";
					} else {
						nextPage = "/pantallaPrincipalGestor.jsp";
					}
					
					
					// borrar la session
					if(session != null)
					{
						session.invalidate();
					}
					session = request.getSession(true); // volver a crearla
					
					// guarda en la sesion, el nombre de usuario
					synchronized(session)
					{
						// guardamos en la sesion esto, para dejar constancia de que hemos iniciado sesion.
						session.setAttribute("usuario", usuario); // guardo mi nombre de usuario en la sesion.
					}

				}
			}
		}		catch(SQLException ex)		{
			session.setAttribute("error", ex.getMessage());
		}		finally		{
			try	{
				if(stmt != null)
				{
					stmt.close();
				}
				if(conn != null)
				{
					conn.close();
				}
			}
			catch(SQLException ex){}
		}

				
		// redireccionar a donde diga nextPage
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
