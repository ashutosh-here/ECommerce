/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learn.ecommerce.servlets;

import com.learn.ecommerce.dao.CategoryDao;
import com.learn.ecommerce.dao.ProductDao;
import com.learn.ecommerce.entities.Category;
import com.learn.ecommerce.entities.Product;
import com.learn.ecommerce.helper.FactoryProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author ashut
 */
@MultipartConfig
public class ProductOperationServlet extends HttpServlet {

   // private static final long serialVersionUID = 1L;

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

            try {

                
//                
//                
//                //  printing values for testing  
//         Enumeration<String> names=request.getParameterNames();
//            
//            while(names.hasMoreElements()){
//                String nn=names.nextElement();
//                System.out.println(nn);
//            }
                
                
         
                
                
                String op = request.getParameter("operation");
                
                
//                testing products
//                String pname = request.getParameter("pName");
//                String pesc = request.getParameter("pDesc");
//                System.out.println(op);
//                System.out.println(pname);
//                System.out.println(pesc);
//                  int caatId = Integer.parseInt(request.getParameter("catId"));
//                System.out.println(caatId);
                
                if (op.trim().equals("addcategory")) {

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

                } else if (op.trim().equals("addproduct")) {

                    //add product
                    String pName = request.getParameter("pName");
                    String pDesc = request.getParameter("pDesc");
                    int pPrice = Integer.parseInt(request.getParameter("pPrice"));
                    int pDiscount = Integer.parseInt(request.getParameter("pDiscount"));
                    int pQuantity = Integer.parseInt(request.getParameter("pQuantity"));
                    int catId = Integer.parseInt(request.getParameter("catId"));
                    Part part = request.getPart("pPic");

                    //getting category by id
                    CategoryDao cdao = new CategoryDao(FactoryProvider.getFactory());
                    Category category = cdao.getCategoryById(catId);

                    Product product = new Product(pName, pDesc, part.getSubmittedFileName(), pPrice, pDiscount, pQuantity, category);

                    ProductDao pDao = new ProductDao(FactoryProvider.getFactory());
                    boolean save = pDao.saveProduct(product);
                    
                    // pic uploading
                    
                    
                    
                    if (save) {
                        
                        //pic uploading
                        
                
                        try {
                            
                      
     
                        if (part != null) {

                String path = request.getSession().getServletContext().getRealPath("/") + "img" + File.separator + "products"+File.separator+part.getSubmittedFileName();

                            FileOutputStream fos= new FileOutputStream(path);
                            InputStream is=part.getInputStream();
                            
                            byte[] data=new byte[is.available()];
                            is.read(data);
                            
                            fos.write(data);
                            
                            fos.close();
                
                   }
                   else{
                       response.sendRedirect("/MYSITE/error_page.jsp");
                   }
                        
                        
                         } catch (IOException e) {
                             e.printStackTrace();
                        } 
                        
                        
                        out.println("product saved");

                        HttpSession ss = request.getSession();
                        ss.setAttribute("message", "Product Added Successfully");
                        response.sendRedirect("admin.jsp");
                    }

                }   

            } catch (IOException e) {
                e.printStackTrace();
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
