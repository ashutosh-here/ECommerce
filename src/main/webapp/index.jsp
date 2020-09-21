<%-- 
    Document   : index
    Created on : 25 Aug, 2020, 2:57:50 PM
    Author     : ashut
--%>
   
<%@page import="com.learn.ecommerce.helper.DescriptionShortener"%>
<%@page import="com.learn.ecommerce.entities.Category"%>
<%@page import="com.learn.ecommerce.dao.CategoryDao"%>
<%@page import="com.learn.ecommerce.entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.learn.ecommerce.dao.ProductDao"%>
<%@page import="com.learn.ecommerce.helper.FactoryProvider"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyCompany : Home</title>

        <%@include file="components/common_css_js.jsp" %>
    </head>
    <body>
         <%--<%@include file="nav-Back.jsp" %>--%>
        <%@include file="components/navbar.jsp" %>  
         <%@include  file="components/Common_modals.jsp" %>

              <% //   FactoryProvider.getFactory();  %>   


        <%    
            
           String catID=request.getParameter("category");
         //  out.print(catID);
            ProductDao dao = new ProductDao(FactoryProvider.getFactory());
         List<Product> list=null;
       
         if(catID==null || (catID.trim().equals("all"))){
         
            list = dao.getAllProducts();
         } else{
            
             int cid=Integer.parseInt(catID.trim());
             list=dao.getAllProductsById(cid);
             
         }
         
         
            CategoryDao cdao = new CategoryDao(FactoryProvider.getFactory());
            List<Category> clist = cdao.getCategories();


        %>







        <div class="row mt-3 mx-2">

            <!--show categories-->

            <div class="col-md-2">


                <div class="list-group mt-4">

                    <a href="index.jsp?category=all" class="list-group-item list-group-item-action active"> All Products </a>



                    <%                  for (Category category : clist) {

                    %> 

                    <a href="index.jsp?category=<%=category.getCategoryId() %>" class="list-group-item list-group-item-action"><%=category.getCategoryTitle()%></a>


                    <%
                            // out.println(category.getCategoryTitle()+"<br>");
                        }


                    %>
                </div> 


            </div>

            <!--show products-->

            <div class="col-md-10">

                <div class="row mt-4">
                    <div class="col-md-12">
                        <div class="card-columns">
                       
                              <%  for (Product products : list) {
                             
                                %>
                                

                            <div class="card product-card">
                                
                                <img class="card-img-top m-2" style="max-height: 200px; max-width: 95%;width: auto;" src="img/products/<%=products.getpPhoto()%>" alt="Card image cap">
                                
                                
                                <div class="card-body">
                                    <h5 class="card-title"><%=products.getpName() %></h5>
                                    <p class="card-text"> <%=  DescriptionShortener.get10Words(products.getpDesc()) %></p>
                                    <a href="#" class="btn btn-primary">Read more</a>
                                </div>
                                    
                                    <div class="card-footer text-center">
                                        <button class="btn custom-bg text-white" onclick="addToCart(<%=products.getpId()%>,'<%=products.getpName()%>',<%=products.getPriceAfterApplyingDiscount()%>)"  >Add to Cart</button>
                                        <button class="btn btn-outline-success"> &#8377;<%=products.getPriceAfterApplyingDiscount() %>/-<span class="text-secondary discount-label">  &#8377; <%=products.getpPrice() %>  <%=products.getpDiscount() %>% Off</span></button>
                                    </div>   
                                    
                                    
                                    
                                    
                            </div>
                          
                                <%
                                   // out.println(products.getpName() + "<br>");
                                }
                                
                           if(list.size()==0){
                                     out.println("<h1>No items </h1>");
                                 }
                           
                       
                            %>   




                        </div> 

                    </div>
                </div>


            </div>






        </div>


                           

    </body>
</html>
