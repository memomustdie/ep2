/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.isil.webappjsp.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pe.isil.webappjsp.user.dao.UserDao;
import pe.isil.webappjsp.user.model.User;

/**
 *
 * @author Guillermo Hurtado
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserDao userDao = new UserDao();
        int rowsAffected=0;
        String mensaje = "";
        
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        String tipDoc = request.getParameter("tipDoc");
        String nroDoc = request.getParameter("nroDoc");
        String enable = request.getParameter("enable");
        String email = request.getParameter("email");
        
        User user = new User();   
        user.setName(name);
        user.setLastname(lastname);
        user.setUsername(username);
        user.setPass(pass);
        user.setTipDoc(tipDoc);
        user.setNroDoc(nroDoc);
        user.setEnable(Integer.parseInt(enable) );
        user.setEmail(email);
        
        try{
            rowsAffected = userDao.registerUser(user);
            mensaje = "Usuario registrado satisfactoriamente";
            
        }catch(Exception e){
            mensaje = e.toString();
            e.printStackTrace();
            
            
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/success.jsp");
        
        request.setAttribute("message", mensaje);
        
        dispatcher.forward(request,response);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
