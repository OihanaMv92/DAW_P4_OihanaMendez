/**
 * 
 */
package es.studium.LibreriaMVC;

/**
 * @author Alvca
 * ElementoPedido 
 * * Representa un elemento del pedido 
 * * Incluye identificador del libro y cantidad
 */
public class ElementoPedido { 
	private int idLibro; 
	private int cantidad; 
	public ElementoPedido(int idLibro, int cantidad) { 
		this.idLibro = idLibro; 
		this.cantidad = cantidad; 
	} 
	public int getIdLibro() { 
		return idLibro; 
	}
	public void setIdLibro(int idLibro) { 
		this.idLibro = idLibro; 
	} 
	public int getCantidad() { 
		return cantidad; 
	}
	public void setCantidad(int cantidad){
		this.cantidad = cantidad; 
	}
	public String getAutor() { 
		return LibreriaMVC.getAutor(idLibro); 
	}
	public String getTitulo() { 
		return LibreriaMVC.getTitulo(idLibro); 
	} 
	public double getPrecio() { 
		return LibreriaMVC.getPrecio(idLibro); 
	} 
}