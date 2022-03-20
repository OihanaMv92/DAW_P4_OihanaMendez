package es.studium.LibreriaMVC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletControlador
 */
@WebServlet("/shopping")
public class ServletControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		LibreriaMVC.cargarDatos();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// Recupera la sesi�n actual o crea una nueva si no existe
		HttpSession session = request.getSession(true);

		// Recupera el carrito de la sesi�n actual
		@SuppressWarnings("unchecked")
		ArrayList<ElementoPedido> elCarrito = (ArrayList<ElementoPedido>) session.getAttribute("carrito");
		request.setCharacterEncoding("UTF-8");
		// Determina a qu� p�gina jsp se redirigir�
		String nextPage = "/tienda.jsp";
		String todo = request.getParameter("todo");


		if(todo==null) { // 
			// Primer acceso, redirigir a tienda.jsp
			nextPage = "/tienda.jsp";
		} else if(todo.equals("add")) { // a�adir cesta btn.
			
			// Mandado por tienda.jsp con los par�metros idLibro y cantidad
			// Creamos un elementoPedido y lo a�adimos al carrito
			ElementoPedido nuevoElementoPedido = new ElementoPedido(Integer.parseInt(request.getParameter("idLibro")), Integer.parseInt(request.getParameter("cantidad")));
			
			// si no hay carrito, crearlo y le a�ade el pedido
			// si ya existe, a�adir/sumar
			if(elCarrito==null) {
				// El carrito est� vac�o
				elCarrito = new ArrayList<>(); // crear la lista
				elCarrito.add(nuevoElementoPedido); // a�ado el primer elemento
				// Enlazar el carrito con la sesi�n
				session.setAttribute("carrito", elCarrito); // guardar en la sesion este carrito.
			} else {
				
				// Comprueba si el libro est� ya en el carrito
				// Si lo est�, actualizamos la cantidad
				// Si no est�, lo a�adimos
				boolean encontrado = false;
				Iterator<ElementoPedido> iter = elCarrito.iterator();
				while(!encontrado && iter.hasNext()) { // si, todavia no he encontrado y, la tabla sigue teniendo elementos
					ElementoPedido unElementoPedido = (ElementoPedido)iter.next();
					if(unElementoPedido.getIdLibro() == nuevoElementoPedido.getIdLibro()) { // el ID del pedido actual del carrito, es igual que el ID del libro que voy a pedir
						
						if(unElementoPedido.getCantidad() + nuevoElementoPedido.getCantidad()>0) {
							unElementoPedido.setCantidad(unElementoPedido.getCantidad() + nuevoElementoPedido.getCantidad());
							encontrado = true;
						}else if (unElementoPedido.getCantidad() + nuevoElementoPedido.getCantidad()<=0) {
							unElementoPedido.setCantidad(0);
							encontrado = true;
						}
						
					}
				}
				if(!encontrado) {
					// Lo a�ade al carrito como nuevo
					elCarrito.add(nuevoElementoPedido);
				}
			}
			// Volvemos a tienda.jps para seguir con la compra
			nextPage = "/tienda.jsp";
			
			
			
			
			
		} else if(todo.equals("remove")) {
			// Enviado por tienda.jsp con el par�metro indiceElemento
			// Borra el elemento indiceElemento del carrito
			int indiceCarrito = Integer.parseInt(request.getParameter("indiceElemento"));
			elCarrito.remove(indiceCarrito);
			// Vuelve a tienda.jsp para seguir con la compra
			nextPage = "/tienda.jsp";
		} else if (todo.equals("checkout")) {
			// Enviado por tienda.jsp
			// Calcula el precio total de todos los elementos del carrito
			double precioTotal = 0;
			int cantidadTotalOrdenada = 0;
			for(ElementoPedido item: elCarrito) {
				double precio = item.getPrecio();
				int cantidadOrdenada = item.getCantidad();
				precioTotal += precio * cantidadOrdenada;
				cantidadTotalOrdenada += cantidadOrdenada;
			}
			// Da formato al precio con dos decimales
			StringBuilder precioTotalFormateado = new StringBuilder();
			Formatter formatter = new Formatter(precioTotalFormateado);
			formatter.format("%.2f", precioTotal);
			formatter.close();
			// Coloca el precioTotal y la cantidadtotal en el request
			request.setAttribute("precioTotal", precioTotalFormateado.toString()); // precioTotal: precioTotalFormateado
			request.setAttribute("cantidadTotal", cantidadTotalOrdenada+"");
			// Redirige a checkout.jsp
			nextPage = "/checkout.jsp";
		}
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}
}

