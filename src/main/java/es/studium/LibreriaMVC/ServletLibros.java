package es.studium.LibreriaMVC;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class ServletLibros
 */
@WebServlet("/ServletLibros")
public class ServletLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletLibros() {
        super();
        // TODO Auto-generated constructor stub
    }

    Connection conn = null;
	Statement stmt = null;
	String nextPage = "/altaLibro.jsp";
	HttpSession session = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// datos que necesitmaos. TODO, sesion
		request.setCharacterEncoding("UTF-8");
		String todo = request.getParameter("todo"); // TO DO, que vamos a hacer
		session = request.getSession(true);

		
		// segun el TODO, hacemos al ta o modificacion
		if(todo.equals("altaLibro")) { // si voy a hacer alta
			
			hacerAltaLibro(request);
			
		} else if(todo.equals("modificarLibro")) {

			hacerModificacionLibro(request);
			
		}
		
		
		// redirigimos a alguna vista
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
		
	}
	
	
	private void hacerModificacionLibro(HttpServletRequest request)	{
		
		
		try{
			// cargar pool
			PoolServlet.cargarpool();
			// conectar a la base de datos
			conn = PoolServlet.pool.getConnection();
			stmt = conn.createStatement();
			
			// sacar todos los datos del FORMULARIO
			String idLibro= request.getParameter("idLibro");
			String nombre = request.getParameter("nombre");
			String cantidad = request.getParameter("cantidad"); // 
			String precio = request.getParameter("precio");		/// "22.37"
			String idAutor = request.getParameter("idAutor");	
			String idEditorial = request.getParameter("idEditorial");
			
			// sentencia
			String sentencia = "UPDATE libros SET cantidadLibro = "+cantidad+", precioLibro = "+precio+", nombreLibro = '"+nombre+"', idEditorialFK = "+idEditorial+", idAutorFK = "+idAutor+" WHERE idLibro =" + idLibro; 

		
			
			// comprobar que que estan todos los datos relleno 
			if(nombre.length() != 0 && cantidad.length() != 0 && precio.length() != 0) {
				
				// EJECUTAMOS LA SENTENCIA, luego almacenar si ha habido algun error
				int info = stmt.executeUpdate(sentencia);
				
				
				if(info == 1) { // no error
					// guardo a donde me tendrá que redirigir, a tienda
					nextPage = "/shopping";
					// almacenar que todo ha ido bien en la session
					session.setAttribute("info","Libro editado correctamente");
				} else { // error
					// guardo a donde me tendrá que redirigir
					nextPage = "/modificarLibro.jsp";
					// guardar en la sesion de nombre "info", el error
					session.setAttribute("info","Error al editar libro.");
				}
			} else {
				nextPage = "/modificarLibro.jsp";
				session.setAttribute("info","<p class='text-danger'>Faltan datos... <p>");
			}
			
		} catch (SQLException e){
			// TODO Auto-generated catch blocke
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void hacerAltaLibro(HttpServletRequest request)  {
		
		try{
			PoolServlet.cargarpool(); // me conecto el POOL
			// conectar a la base de datos
			conn = PoolServlet.pool.getConnection();
			stmt = conn.createStatement();
			
			// datos del FORMULARIO
			String nombre = request.getParameter("nombre");
			String cantidad = request.getParameter("cantidad"); // 
			String precio = request.getParameter("precio");		/// "22.37"
			String idAutor = request.getParameter("idAutor");	
			String idEditorial = request.getParameter("idEditorial");
			
			// comprobar que que estan todos los datos relleno 
			if(nombre.length() != 0 && cantidad.length() != 0 && precio.length() != 0) {
				
				// sentencia
				String sqlStr = "INSERT INTO libros VALUES(NULL, "+cantidad+", "+precio+", '"+nombre+"', "+idEditorial+", "+idAutor+" )"; 
				
				int info = stmt.executeUpdate(sqlStr); // ejecuto la INSERCCIÓN
				
				if(info == 1) { // todo ok
					nextPage = "/shopping";
					// session.setAttribute() "info", valor;
					session.setAttribute("info","<p class='text-success'>Libro insertado correctamente.<p>");
				} else { // error
					nextPage = "/altaLibro.jsp";
					session.setAttribute("info","<p class='text-danger'>Error al insertar libro.<p>");
				}
			} else {
				nextPage = "/altaLibro.jsp";
				session.setAttribute("info","<p class='text-danger'>Faltan datos... <p>");
			}
			
			
		} catch (SQLException e){
			// TODO Auto-generated catch blocke
			e.printStackTrace();
		} catch (ServletException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
