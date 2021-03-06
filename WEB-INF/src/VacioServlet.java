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

@WebServlet("/vaciar")
public class VacioServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try (DBManager db = new DBManager()) {
			HttpSession session = req.getSession();
			Cliente cliente = (Cliente)session.getAttribute("cliente");

			if ( db.vaciarCarro(cliente.getId()) ) {
				resp.sendRedirect("carro");
				
			} else {
					/* RequestDispatcher */
					String error = "Fallo al vaciar el carro";
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