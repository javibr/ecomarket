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

@WebServlet("/carro")
public class CarroServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try (DBManager db = new DBManager()) {
			HttpSession session = req.getSession();

			Cliente cliente = (Cliente)session.getAttribute("cliente");
			if (cliente != null) {
				List<Carro> carros = db.getCarro(cliente.getId());

				req.setAttribute("carros", carros);
				req.getRequestDispatcher("carro.jsp").forward(req, resp);
				
			} else {
				String error = "Es necesario estar logueado para entrar al carrito";
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try (DBManager db = new DBManager()) {
			HttpSession session = req.getSession();

			Cliente cliente = (Cliente)session.getAttribute("cliente");
			List<Integer> sinStock = new ArrayList<Integer>();
			int tam = Integer.parseInt(req.getParameter("tam"));
			for(int i=1; i < tam; i++){
				sinStock.add(Integer.parseInt(req.getParameter("" + i)));
			}

			if(db.eliminarAgotados(cliente.getId(), sinStock)) {
				resp.sendRedirect("carro");
			} else {
				String error = "Error al eliminar los productos agotados";
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
