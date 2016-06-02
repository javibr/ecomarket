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

@WebServlet("/confirmar")
public class ConfirmarServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try (DBManager db = new DBManager()) {
			HttpSession session = req.getSession();

			String slugar = req.getParameter("lugares");
			int lugar = Integer.parseInt(slugar);
			String recogida = req.getParameter("fecha");
			int fecha = Integer.parseInt(recogida);

			Cliente cliente = (Cliente)session.getAttribute("cliente");
			float precio = db.precioCarro(cliente);

			if (db.vender(cliente.getId(), lugar, precio, fecha)) {
				List<Resumen> resumen = db.getResumen(cliente.getId());
				req.setAttribute("resumen", resumen);
				req.getRequestDispatcher("confirmar.jsp").forward(req, resp);
				
			} else {
				resp.sendRedirect("agotados");
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