import java.io.IOException;
import java.io.PrintWriter;

import Modelo.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import javax.naming.NamingException;

@WebServlet("/presentacion")
public class PresentacionServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try (DBManager db = new DBManager()) {
			HttpSession session = req.getSession();

			String id = req.getParameter("id");
			int idProducto = Integer.parseInt(id);

			Producto producto = db.getProducto(idProducto);
			List<Producto> productos = db.getPresentaciones(producto.getId());

			if(!productos.isEmpty()) {
				req.setAttribute("productos", productos);
				req.getRequestDispatcher("presentacion.jsp").forward(req, resp);
			} else {
				String error = "El producto seleccionado está fuera de precio";
				session.setAttribute("error", error);
				resp.sendRedirect("error");
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("SQLException: "+ex.getMessage());
			System.out.println("SQLState: "+ex.getSQLState());
			System.out.println("VendorError: "+ex.getErrorCode());
		}
		catch (NamingException na){
		na.printStackTrace();
		
		}
	}

}