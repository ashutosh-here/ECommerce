/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learn.ecommerce.servlets;

import com.learn.ecommerce.entities.User;
import com.learn.ecommerce.helper.FactoryProvider;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ashut
 */
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            try {

                String userName = request.getParameter("user_name");
                String userEmail = request.getParameter("user_email");
                String userPassword = request.getParameter("user_password");
                String userPhone = request.getParameter("user_phone");
                String userAddress = request.getParameter("user_address");
                String userGender = request.getParameter("user_gender");

//         //  testing     
//          out.println(userName);
//          out.println(userEmail);
//          out.println(userPassword);
//          out.println(userPhone);
//          out.println(userAddress);
                // validations               
//         creating user object to store form data to DB
                User user = new User(userName, userEmail, userPassword, userPhone, userGender, userAddress, "normal");

                try {
                    
                } catch (Exception e) {
                }
   Session hibernateSession = FactoryProvider.getFactory().openSession();
                Transaction tx = hibernateSession.beginTransaction();

               
                int userId = (int) hibernateSession.save(user);

                tx.commit();
                hibernateSession.close();

         //       out.println("sucesss<br>" + userId);
                
                HttpSession httpSession=request.getSession();
                httpSession.setAttribute("message", "Registeration Successfully ! ! User ID is : "+userId);
                response.sendRedirect("register.jsp");
               
                
                out.println("success");
                
                return;
                
            } catch (HibernateException e) {
                e.printStackTrace();
                
                HttpSession httpSession=request.getSession();
                httpSession.setAttribute("message", "Something went Wrong ! ! Either you used alphabets in place of number or email is already registered with us.");
                response.sendRedirect("register.jsp");
                
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
