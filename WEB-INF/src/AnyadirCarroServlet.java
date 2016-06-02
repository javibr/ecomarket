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

@WebServlet("/anyadir")
public class AnyadirCarroServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try (DBManager db = new DBManager()) {
			HttpSession session = req.getSession();

			String id = req.getParameter("id");
			int idPresentacion = Integer.parseInt(id);

			String cantidad = req.getParameter("unidades");
			int unidades = Integer.parseInt(cantidad);

			Cliente cliente = (Cliente)session.getAttribute("cliente");
			if (cliente != null) {
				db.anyadirCarro(cliente.getId(), idPresentacion, unidades);
				resp.sendRedirect("carro");
				
			} else {
				String error = "Es necesario estar logueado para añadir productos al carro";
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