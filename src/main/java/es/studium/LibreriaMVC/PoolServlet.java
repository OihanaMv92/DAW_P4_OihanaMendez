package es.studium.LibreriaMVC;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import javax.sql.DataSource;

/**
 * Servlet implementation class PoolServlet
 */
@WebServlet("/PoolServlet")
public class PoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public static DataSource pool;
	
	public static void cargarpool() throws ServletException {
		try
		{
			// Crea un contexto para poder luego buscar el recurso DataSource
			InitialContext ctx = new InitialContext();
			// Busca el recurso DataSource en el contexto
			pool = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql_tiendalibrosoihana");
			if(pool == null)
			{
				throw new ServletException("DataSource desconocida'mysql_tiendalibrosoihana'");
			}
		}
		catch(NamingException ex){}
	}

}
