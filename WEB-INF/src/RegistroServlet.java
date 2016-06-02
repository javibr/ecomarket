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

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try (DBManager db = new DBManager()) {
			HttpSession session = req.getSession();

			String email = req.getParameter("email");
			String pass = req.getParameter("password");
			String name = req.getParameter("name");

			/* Cifrado */
			String salt = BCrypt.gensalt(); /* Hash a password for the first time gensalt's 
												log_rounds parameter determines the complexity 
												the work factor is 2**log_rounds, and the default is 10 */
			String hashed = BCrypt.hashpw(pass, salt);
			
			if ( db.registrarUsuario(email, hashed, name, salt) ) {
				Cliente cliente = db.getCliente(email, hashed);
				
				session.setAttribute("cliente", cliente);
				resp.sendRedirect("search");
				
			} else {
					/* RequestDispatcher */
					String error = "El correo ya está registrado";
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
		req.getRequestDispatcher("registro.jsp").forward(req, resp);

	}

}