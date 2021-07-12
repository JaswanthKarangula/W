package Servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import Manager.Manager;
import Model.*;




public class PostAddServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String postName=request.getParameter("postTitle").trim();
            String postContent = request.getParameter("postContent").trim();
           
            RequestDispatcher dis = request.getRequestDispatcher("addPost.html");
            HttpSession session=request.getSession();
            // Add to database
            Manager manager=new Manager();
            if (manager.addPost(postName,postContent,(User)session.getAttribute("user"))) {
                response.sendRedirect("home.jsp");
            } 
            else {
            	PrintWriter out= response.getWriter();
    			out.println("<font color=red>something is wrong.</font>");
                dis.forward(request, response);
            }
            
        } 
        catch (Exception e) {
           e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
