package es.studium.LibreriaMVC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;

public class Editoriales
{
	public static String getEditoriales() throws ServletException {
		
		String html = ""; // devolver
		Connection conn = null;
		Statement stmt = null;

		try
		{

			PoolServlet.cargarpool();
			conn = PoolServlet.pool.getConnection();
			stmt = conn.createStatement();
				
			String sqlStr = "SELECT * FROM editoriales;"; 
			
			ResultSet rset = stmt.executeQuery(sqlStr); // todas las filas de editoriales
			
			while(rset.next()) { // tantas vueltas como editoriales
				
				String idEditorial = rset.getString("idEditorial");
				String nombreEditorial = rset.getString("nombreEditorial");
				
				html += "<option value="+idEditorial+">"; // "opcion del  desplegable"
				html += 	nombreEditorial;
				html += "</option>";
				
			}
			
		} catch(SQLException ex) {
			return "error";
		}	finally		{
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
		
		return html; // devuelve los datos
	}

	
	public static String getTablaEditoriales() throws ServletException {
		
		String html = ""; // devolver
		Connection conn = null;
		Statement stmt = null;

		try
		{

			PoolServlet.cargarpool();
			conn = PoolServlet.pool.getConnection();
			stmt = conn.createStatement();
				
			String sqlStr = "SELECT * FROM editoriales;"; 
			
			ResultSet rset = stmt.executeQuery(sqlStr);
			
			while(rset.next()) {
				
				html += "<tr> <td>"+ rset.getString("idEditorial")+"</td> <td>"+ rset.getString("nombreEditorial") +"</td> </tr>";
				
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
		
		return html;
	}
	
}
