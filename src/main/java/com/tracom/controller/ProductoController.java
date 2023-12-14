package com.tracom.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tracom.dao.ProductoDAO;
import com.tracom.model.Producto;

@WebServlet(description = "administra peticiones para la table productos", urlPatterns = { "/productos" })
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		if (opcion.equals("crear")) {
			System.out.println("Creando");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equals("listar")) {

			ProductoDAO productoDAO = new ProductoDAO();
			List<Producto> lista = new ArrayList<>();
			try {
				lista = productoDAO.obtenerProductos();

				for (Producto p : lista) {
					System.out.println(p);
				}
				request.setAttribute("lista", lista);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Listando");
		} else if (opcion.equals("meditar")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("Editar id= " + id);
			
			ProductoDAO productoDAO = new ProductoDAO();
			Producto p = new Producto();
			try {
				p=productoDAO.obtenerProducto(id);
				System.out.println(p);
				request.setAttribute("producto", p);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}else if (opcion.equals("eliminar")) {
			ProductoDAO productoDAO = new ProductoDAO();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				productoDAO.eliminar(id);
				System.out.println("Eliminado");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("opcion");
		Date fechaActual = new Date();
		
		
		if(opcion.equals("guardar")) {
			ProductoDAO productoDAO = new ProductoDAO();
			Producto producto = new Producto();
			producto.setNombre(request.getParameter("nombre"));
			producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
			producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
			producto.setFechaCrear(new java.sql.Date(fechaActual.getTime()));

			try {
				productoDAO.crear(producto);
				System.out.println("CREADO.....!!!!");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if (opcion.equals("editar")) {
			Producto producto = new Producto();
			ProductoDAO productoDAO = new ProductoDAO();
			
			producto.setId(Integer.parseInt(request.getParameter("id")));
			producto.setNombre(request.getParameter("nombre"));
			producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
			producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
			producto.setFechaActualizar(new java.sql.Date(fechaActual.getTime()));
		
			try {
				productoDAO.editar(producto);
				System.out.println("EDITANDO PRODUCTO");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}

}
