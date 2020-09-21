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
@MultipartConfig(fileSizeThreshold = 1024*1024*10 , maxFileSize = 1024*1024*50 , maxRequestSize = 1024*1024*100 )
public class AddProductServlet extends HttpServlet {

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
            out.println("<title>Servlet AddProductServlet</title>");        
            
            out.println("</head>");
            out.println("<body>");
                
            

            System.out.println("******started*********");
            
              // printing values for testing  
         Enumeration<String> names=request.getParameterNames();
            
            while(names.hasMoreElements()){
                String nn=names.nextElement();
                System.out.println(nn);
                System.out.println("*******values********");
               System.out.println(request.getParameter(nn));
            }

// String pnName = request.getParameter("pName");
//                    String pDesc = request.getParameter("pDesc");
////                    int pPrice = Integer.parseInt(request.getParameter("pPrice"));
////                    int pDiscount = Integer.parseInt(request.getParameter("pDiscount"));
////                    int pQuantity = Integer.parseInt(request.getParameter("pQuantity"));
////                    int catId = Integer.parseInt(request.getParameter("catId"));
////                    Part part = request.getPart("pPic");
//
//System.out.println(pnName);
// System.out.println(pDesc);
//// System.out.println(pDiscount);
//// System.out.println(pPrice);
//// System.out.println(pQuantity);
//// System.out.println(catId);

 System.out.println("********END*******");







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
                             fos.flush();
                            fos.close();
                
                            
                            byte b[] = new byte[is.available()];
       
                            
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
