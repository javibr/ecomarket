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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try (DBManager db = new DBManager()) {
			HttpSession session = req.getSession();

			String producto = req.getParameter("Producto");
			req.setAttribute("producto", producto);

			List<Producto> productos = db.getProducto(producto);
			
			if(!productos.isEmpty()) {
				session.setAttribute("busqueda", productos);
				resp.sendRedirect("productos");
			} else {
				String error = "Aún no tenemos lo que buscas";
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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try (DBManager db = new DBManager()) {
			HttpSession session = req.getSession();

			List<Producto> productos = db.getLista();
			session.setAttribute("productos", productos);
		}catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("SQLException: "+ex.getMessage());
			System.out.println("SQLState: "+ex.getSQLState());
			System.out.println("VendorError: "+ex.getErrorCode());
		}
		catch (NamingException na){
		na.printStackTrace();
		
		}
		/* RequestDispatcher */
		req.getRequestDispatcher("search.jsp").forward(req, resp);

	}
}