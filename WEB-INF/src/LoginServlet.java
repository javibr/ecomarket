import java.io.IOException;
import java.io.PrintWriter;

import Modelo.*;
import Cifrado.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import javax.naming.NamingException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try (DBManager db = new DBManager()) {
			HttpSession session = req.getSession();

			String email = req.getParameter("email");
			String pass = req.getParameter("password");

			/* Cifrado */
			String salt = db.getSalt(email);
			Cliente cliente = (Cliente)null;
			if (!salt.equals("")) {

				String hashed = BCrypt.hashpw(pass, salt);
				cliente = db.getCliente(email, hashed);
			}

			if(cliente != null) {
				session.setAttribute("cliente", cliente);
				resp.sendRedirect("search");
			} else {
				String error = "Usuario o pass incorrectos";
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
		
		/* RequestDispatcher */
		req.getRequestDispatcher("login.jsp").forward(req, resp);

	}

}