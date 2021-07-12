package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.Manager;
import Model.User;



public class LoginServlet extends HttpServlet {

    


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            String emailOrPhone = request.getParameter("userEmail");
            String password = request.getParameter("userPassword");
            HttpSession session = request.getSession();
            Manager manager=new Manager();
            User user = manager.getUser(emailOrPhone, password);
            
            if (user != null) {
                session.setAttribute("user", user);
                if (session.getAttribute("currentUrl") == null) {
                    response.sendRedirect("home.jsp");
                } else {
                    String curUrl = session.getAttribute("currentUrl").toString();
                    System.out.println(curUrl);
                    session.setAttribute("currentUrl", null);
                    response.sendRedirect(curUrl);
                }
                
                
            } 
            else {
            	RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
    			PrintWriter out= response.getWriter();
    			
    			rd.include(request, response);
    			out.println("<font color=red>Either user name or password is wrong.</font>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   


}
