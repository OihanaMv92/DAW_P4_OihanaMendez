package es.studium.LibreriaMVC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class Autores
{
	

	public static String getAutores() throws ServletException {
		
		String html = ""; // el String que almacenará el HTML del desplegable
		
		// conexion
		Connection conn = null;
		Statement stmt = null;

		try // para la conexion a bbdd
		{
			
			// cargar pool
			PoolServlet.cargarpool();
			// establecemos conexion
			conn = PoolServlet.pool.getConnection();
			stmt = conn.createStatement();
				
			// sentencia
			String sqlStr = "SELECT * FROM autores";
			
			// ejecutar sentenncia
			ResultSet rset = stmt.executeQuery(sqlStr);
			
			
			while(rset.next()) { // por cada autor
				
				// guradamos ID y NOMBRE
				String idAutor = rset.getString("idAutor");
				String nombreAutor = rset.getString("nombreAutor");
				
				// añadimos la opcion del desplegable
				html += "<option value="+idAutor+">";
				html += 	nombreAutor;
				html += "</option>";
				
			}
			
		} catch(SQLException ex) {
			return "error";
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
		
		return html;  // devolvemos el HTML del desplegable
	}
	
	public static String getTablaAutores() throws ServletException {
		
		String html = ""; // string que contendrá el HTML de la tabla
		
		// cosas de la conexion
		Connection conn = null;
		Statement stmt = null;

		try { // nos obliga la conn

			// cargar pool
			PoolServlet.cargarpool();
			// conectar a bbdd
			conn = PoolServlet.pool.getConnection();
			stmt = conn.createStatement();
				
			// sentencia
			String sqlStr = "SELECT * FROM autores";
			// ejecutamos
			ResultSet rset = stmt.executeQuery(sqlStr);
			
			while(rset.next()) { // por cada AUTOR
				
				// datos del autor
				String idAutor = rset.getString("idAutor");
				String nombreAutor = rset.getString("nombreAutor");
				String apellidosAutor = rset.getString("apellidosAutor");
				
				// generar FILAS de la TABLA
				html += "<tr>"; // fila
				html += "	<td>" + idAutor + "</td>"; 		 // columna
				html += "	<td>" + nombreAutor +"</td>"; 	 // columna
				html += "	<td>" + apellidosAutor +"</td>"; // columna
				html += "</tr>";
				
			}
			
		} catch(SQLException ex) {
			return "error";
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
		
		// devolver la tabla
		return html;
	}
	

}
