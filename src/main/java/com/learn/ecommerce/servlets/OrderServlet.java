/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learn.ecommerce.servlets;

import com.learn.ecommerce.Transaction.OrderDetail;
import com.learn.ecommerce.Transaction.PaymentServices;
import com.learn.ecommerce.dao.ProductDao;
import com.learn.ecommerce.dao.UserDao;
import com.learn.ecommerce.dao.UserProductDao;
import com.learn.ecommerce.entities.Product;
import com.learn.ecommerce.entities.User;
import com.learn.ecommerce.helper.FactoryProvider;
import com.learn.ecommerce.helper.OrderTemp;
import com.paypal.base.rest.PayPalRESTException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ashut
 */
public class OrderServlet extends HttpServlet {

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
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet OrderServlet</title>");
//            out.println("</head>");
//            out.println("<body>");

            
            
            
            
            
//            
//            
//              //List of allowed origins
//        List<String> incomingURLs = Arrays.asList(getServletContext().getInitParameter("incomingURLs").trim().split(","));
// 
//        
//        // Get client's origin
//        String clientOrigin = request.getHeader("origin");
//        
//        // Get client's IP address
//        String ipAddress = request.getHeader("x-forwarded-for");
//        if (ipAddress == null) {
//            ipAddress = request.getRemoteAddr();
//        }  
//        
//    //    String userId = request.getParameter("userId").trim();
//    //    String password = request.getParameter("password").trim();
// 
//     //   PrintWriter out = response.getWriter();
//    //    response.setContentType("text/html");
//        response.setHeader("Cache-control", "no-cache, no-store");
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Expires", "-1");
//        
//        int myIndex = incomingURLs.indexOf(clientOrigin);
//        //if the client origin is found in our list then give access
//        //if you don't want to check for origin and want to allow access 
//        //to all incoming request then change the line to this
//        //response.setHeader("Access-Control-Allow-Origin", "*");
//        if(myIndex != -1){



//
//          //  response.setHeader("Access-Control-Allow-Origin", "*");
//          
//            
//            response.setHeader("Access-Control-Allow-Origin", clientOrigin);
//            response.setHeader("Access-Control-Allow-Methods", "POST");
//            response.setHeader("Access-Control-Allow-Headers", "Content-Type");
//            response.setHeader("Access-Control-Max-Age", "86400");
//            
//            
     //   }
 
     
     
     
     
     
//        response.addHeader("Access-Control-Allow-Origin", "*");
// response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
// response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
// response.addHeader("Access-Control-Max-Age", "1728000");     
//            
            
            
            
            
            
            
            
            
            
            
            
            
            
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("contact");
            String address = request.getParameter("address");
            String cart = request.getParameter("carts");

            String gson1 = cart.replaceFirst("\"", ""); //replaces first "
            String gson2 = gson1.replaceAll("\"[^\"]*$", ""); //replaces last "
            String gson3 = gson2.replaceAll("\\\\", ""); // replaces all /
            String gson45 = gson3.replaceAll("\"", "'"); // replaces all "
        
        //    System.out.println("********START******ggogle*******JSON********");

            OrderTemp[] oo = OrderTemp.getOrderTemp(gson45);

      //      System.out.println(oo.length);

            HashMap<Integer, Integer> maps = new HashMap<>();

               double OrderPrice = 0.0;
               int OrderQt = 0;
        //     String pNames=oo[0].getProductName();
               String pNames="";
               
               
            ProductDao pDao=new ProductDao(FactoryProvider.getFactory());
            
            for (int i = 0; i < oo.length; i++) {

                maps.put(oo[i].getProductId(), oo[i].getProductQuantity());

                int pid=oo[i].getProductId();
                
                Product product=pDao.getProductById(pid);
                
                    OrderPrice =OrderPrice+ (( oo[i].getProductQuantity()) * (product.getPriceAfterApplyingDiscount()));
                
                     OrderQt = OrderQt + oo[i].getProductQuantity();
                     
    //     pNames=     pNames+",  "+oo[i].getProductName(); 
           pNames=     pNames+oo[i].getProductName()+" , ";
                
                System.out.println(oo[i]);
//                System.out.println(oo[i].getProductId());
//                System.out.println(oo[i].getProductName());
//                System.out.println(oo[i].getProductPrice());
//                System.out.println(oo[i].getProductQuantity());

            }
            String newPNames=pNames.trim();
newPNames=            newPNames.substring(0,newPNames.length()-1);
            
            System.out.println("**********************************************"+newPNames+"**************"+OrderPrice+"********************"+OrderQt+"*******************************************");
            
            
           
            
            HashMap<String,String> mapForPaymentData=new HashMap<>();
            mapForPaymentData.put("OrderNames", newPNames);
            mapForPaymentData.put("OrderQT",  Integer.toString(OrderQt));
            mapForPaymentData.put("OrderPrice", Double.toString(OrderPrice));
            
            
            HttpSession ss=request.getSession();
            ss.setAttribute("paymentDetails",mapForPaymentData);

//            System.out.println("********end*********google*******JSON********");
//
//            System.out.println("************traversal of hashmap**********************");
//            for (Map.Entry<Integer, Integer> e : maps.entrySet()) {
//
//                System.out.println(e.getKey() + "----" + e.getValue());
//
//            }
//
//            System.out.println("********START***prrrrnt********");
//            System.out.println(name + "<br>");
//            System.out.println(email + "<br>");
//            System.out.println(phone + "<br>");
//            System.out.println(address + "<br>");
//            System.out.println(cart);
//            System.out.println("*********END***printt*******");




               int temp=0;

            //updating user data   
            UserDao udao = new UserDao(FactoryProvider.getFactory());
            User user = udao.getUserByEmail(email);
         //   System.out.println(user);

            int userID = user.getUserId();

            if ((user.getUserName().equals(name)) && (user.getUserPhone().equals(phone)) && (user.getUserAddress().equals(address))) {
                  temp++;
                System.out.println("***************user havnt upadted the data***********");
            } else {

                boolean f = udao.updateUser(name, phone, address, userID);

                if (f) {
                    temp++;
                    System.out.println("***user details upadated***");
                } else {
                    System.out.println("***user details not updated*****");
                }

            }
            
            
            

            // inserting order data to OrderTableBuy 
            //unique order id
            String key = "OID" + (String.valueOf(System.currentTimeMillis())).substring(0);

            try {
 
                UserProductDao upDao = null;

                for (Map.Entry<Integer, Integer> e : maps.entrySet()) {

                    //System.out.println(e.getKey()+"----"+e.getValue());
                    System.out.println("***************saving to UserProduct*********************");

                    
                    upDao = new UserProductDao(FactoryProvider.getFactory());

                    upDao.saveUserProduct(userID, e.getKey(), e.getValue());
                    System.out.println("***************succesfully  saved to UserProduct*********************");

                }
                
                temp++;

                boolean f = upDao.saveInOrderTableBuy(userID, user);

                if (f) {
                    temp++;
                    System.out.println("*********************Saved in OrderTAbleBuy**************");
                } else {
                    System.out.println("**************not  Saved in OrderTAbleBuy**************");
                }
                
                
                
                
                
                
                if(temp==3){
                    
                     out.println("ss");
                 //   response.sendRedirect("pgRedirect.jsp");
                    
                //    RequestDispatcher rd=request.getRequestDispatcher("pgRedirect.jsp");  
                //         rd.forward(request, response);  
                    
                
                //AuthorizePaymentServle
                
                
//                
//                
//            
//            
//             String product = "myProduct";//                       request.getParameter("product");
//        String subtotal =       "100";//                 request.getParameter("subtotal");
//        String shipping =      "100";//                    request.getParameter("shipping");
//        String tax =           "100";//                       request.getParameter("tax");
//        String total =        "300";//                     request.getParameter("total");
//         
//        OrderDetail orderDetail = new OrderDetail(product, subtotal, shipping, tax, total);
// 
//        try {
//            PaymentServices paymentServices = new PaymentServices();
//            String approvalLink = paymentServices.authorizePayment(orderDetail);
// 
//            response.sendRedirect(approvalLink);
//             
//        } catch (PayPalRESTException ex) {
//            request.setAttribute("errorMessage", ex.getMessage());
//            ex.printStackTrace();
//            request.getRequestDispatcher("error.jsp").forward(request, response);
//        }
//            
//            
//            
//          //  response.addHeader("Access-Control-Allow-Origin", "*");
//                
//                
//                
//                
//                
//                
//                
                    
                }
                
                
                

            } catch (Exception e) {
                e.printStackTrace();
            }

//            out.println("</body>");
//            out.println("</html>");
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
