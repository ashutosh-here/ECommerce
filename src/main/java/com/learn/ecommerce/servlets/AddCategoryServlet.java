/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learn.ecommerce.servlets;

import com.learn.ecommerce.dao.CategoryDao;
import com.learn.ecommerce.entities.Category;
import com.learn.ecommerce.helper.FactoryProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ashut
 */
public class AddCategoryServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddCategoryServlet</title>");            
            out.println("</head>");
            out.println("<body>");
         
            
              //add category
                    String title = request.getParameter("catTitle");
                    String description = request.getParameter("catDescription");

//                      out.println(title);
//                       out.println(description);
                    Category category = new Category();
                    category.setCategoryTitle(title);
                    category.setCategoryDescription(description);

                    CategoryDao categoryDao = new CategoryDao(FactoryProvider.getFactory());
                    int catId = categoryDao.saveCategory(category);
                    out.println("category saved");

                    HttpSession ss = request.getSession();
                    ss.setAttribute("message", "Category Added Successfully : " + catId);
                    response.sendRedirect("admin.jsp");

                    //getting list of Categories
                    // here used again so that the categories list in Add Product modal keeps updated without logging out and in again
                    // 
                    try {

                        CategoryDao cdao = new CategoryDao(FactoryProvider.getFactory());
                        List<Category> list = cdao.getCategories();
                        HttpSession ss2 = request.getSession();
                        ss.setAttribute("categoryList", list);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            
            
            
            
            
            
            out.println("</body>");
            out.println("</html>");
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
