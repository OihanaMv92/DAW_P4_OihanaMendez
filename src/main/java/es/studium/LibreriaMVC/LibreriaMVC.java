/**
 * 
 */
package es.studium.LibreriaMVC;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.Statement; 
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.sql.DataSource;
/**
 * @author Oihana
 * LibrosMVC
 * Encapsula la comunicación con la base de datos 
 * Almacena títulos, autores y precios en tres tablas
 */
public class LibreriaMVC { 
	
	static ArrayList<Libro> listaLibros = new ArrayList<Libro>();
	
	public static void cargarDatos() throws ServletException {
		
		PoolServlet.cargarpool(); // cargo el pool		
		listaLibros.clear(); // vacio la lista de libros
		
		// Creamos objetos para la conexión 
		Connection conn = null; 
		Statement stmt = null; 
		try { 

			// Paso 3: Crear las sentencias SQL utilizando objetos de la clase Statement 
			conn = PoolServlet.pool.getConnection(); // me conecto a la bse de datos
			stmt = conn.createStatement(); // instanciamos el Statement, con el que ejecutaremos sentencias SQL
			
			// Paso 4: Ejecutar las sentencias 
			String sqlStr = "SELECT * FROM libros JOIN autores ON idAutorFK = autores.idAutor ORDER BY idLibro"; 
			ResultSet rs = stmt.executeQuery(sqlStr);  // ejecuto sentencia
			Libro libro; 
			while(rs.next()) { 
				libro = new Libro(rs.getInt("idLibro"), rs.getString("nombreLibro"), rs.getString("nombreAutor"), rs.getDouble("precioLibro"));
				listaLibros.add(libro); 
			} 
		} 
		catch(Exception ex) { 
			ex.printStackTrace(); 
		} 
		finally { 
			try {
				// Cerramos el resto de recursos 
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) { 
					conn.close(); 
				}
			}
			catch(Exception ex) { 
				ex.printStackTrace();
			}
		}
	}
	/** * Devuelve el número de libros obtenidos */ 
	public static int tamano() { 
		return listaLibros.size();
	}
	/** * Devuelve el título del libro identificado con idLibro */ 
	public static String getTitulo(int idLibro) { 
		return listaLibros.get(idLibro).getTitulo(); 
	}
	/** * Devuelve el autor del libro identificado con idLibro */ 
	public static String getAutor(int idLibro) {
		return listaLibros.get(idLibro).getAutor();
	}
	/** * Devuelve el precio del libro identificado con idLibro */
	public static double getPrecio(int idLibro) { 
		return listaLibros.get(idLibro).getPrecio();
	}
}