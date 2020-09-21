

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
        <title> Vocal4Local : Home</title>
        <script src="js/script.js" type="text/javascript"></script>
        <link rel="SHORTCUT ICON" href="favicon.ico" type="image/x-icon" />
        
    </head>
    <body class="hero-anime" style="background-color: #fcfcfc!important;">
        
        <div id="Preloader" >
            <%@include file="Preloader.html" %>
        </div>
         
        <div id="mainC" style="display: none;">
        
        <link href="https://fonts.googleapis.com/css?family=Kanit:200,400" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">


  <%@include file="components/common_css_js.jsp" %> 

<link href="css/homeCSS.css" rel="stylesheet" type="text/css"/>

               <%@include  file="components/Common_modals.jsp" %>
    <script src="https://kit.fontawesome.com/e057dff8c2.js" crossorigin="anonymous"></script>
        
        <%@include file="nav-Back.jsp" %>
         
        
        
        
        
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
        
        
        <div style="margin-top:130px!important;" class="row m-5">
          
                
            <!--show categories-->

            <div id="cats" style="margin-left:0px!important;" class="col-md-2">
            <!--<div style="margin-left:0px!important;" class="col-md-2">-->


                <div class="list-group">

                    <a href="Home.jsp?category=all" class="list-group-item list-group-item-action active"> All Products </a>



                    <%                  for (Category category : clist) {

                    %> 

                    <a href="Home.jsp?category=<%=category.getCategoryId() %>" class="list-group-item list-group-item-action"><%=category.getCategoryTitle()%></a>


                    <%
                            // out.println(category.getCategoryTitle()+"<br>");
                        }


                    %>
                </div> 

            <!--</div>-->
            </div>
              
        
   
                <!--show products-->
                <div class="col-md-10">

                <div class="row">
                    
                <%  for (Product products : list) {     %>        
             
          
               <div class="col-md-3 m-0 p-0 float-left">
  
                   <!--style="margin-top: 100px!important;"-->
<div  class='CCcontainer'>
      
      <!--style="margin: 20px!important;"-->
    <div  class='CCcard m-2'>
      <div class='card-content'>
        <div class='top-bar'>
          <span>
            &#8377;<%=products.getPriceAfterApplyingDiscount() %>
          </span>
                    <span style="color : #388e3c!important;position:relative;right: -30px!important;" class="m-0 p-0 float-right"><%=products.getpDiscount() %>% Off</span>

            <div class="m-1 wish">
                
                
             <a onclick="addToHeart(<%=products.getpId()%>,'<%=products.getpName()%>',<%=products.getPriceAfterApplyingDiscount()%>);"
                style="cursor: pointer;" > 
                 <svg class="bi bi-heart-fill wish-light" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor"
                                                    xmlns="http://www.w3.org/2000/svg">
                                                    <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z" />
                </svg>
             </a>

                                                
<!--                <a id="btnHousing"  style="cursor: pointer;" ><span onclick="heart();"  class="far fa-heart"></span></a>-->
            
            </div>
        </div>
        <div class='img'>
   <img  class=" m-2 p-2" style="max-height: 120px; max-width: 95%;width: auto;" src="img/products/<%=products.getpPhoto()%>" alt="Card image cap">

          <!--<img src='https://tctechcrunch2011.files.wordpress.com/2014/11/solo2-wireless-red-quarter.jpg?w=738'>-->
        </div>
      </div>
                 
      <div class='card-description'>
          <div style="color: #212121" class='title'>
         <%=products.getpName() %>
        </div>
        <div style="font-size:20px!important;" class='cart'>
     <a  onclick="addToCart(<%=products.getpId()%>,'<%=products.getpName()%>',<%=products.getPriceAfterApplyingDiscount()%>)"  ><span class='lnr lnr-cart'></span></a>

          <!--<span class='lnr lnr-cart'></span>-->
        </div>
      </div>
        
  
      <div class= 'card-myfooter'>
<!--          <div style="font-size: 15px!important;" class="my-2">
            <span  class="span ">  &#8377; 21</span>
          <span style="color : #388e3c!important;" class="pl-4">15% Off</span>
              
              
          </div>-->
     
          <div><p><%=  DescriptionShortener.get10Words(products.getpDesc()) %>
                  <a  style="text-decoration:none;color:black;" id="pLink" href="Product.jsp?pid=<%=products.getpId() %>">Check this..</a></p></div>
                  

          
          
          
<!--        <div class='span'>
          RED
        </div>
        <div class='span'>
          BEATS
        </div>
        <div class='span'>
          HEADPHONE
        </div>-->
      </div>
          
          
    </div>
      
      




  </div>

     
                  </div>           
                       <%
                                }
                                
                           if(list.size()==0){
                                     out.println("<h1>No items </h1>");
                                 }
                            %>  
                            
                            
                            
        </div>    
        </div>
        </div>
        
        
       
              
                            
        
        
        
        
        
           <div id="About" class="container-fluid" style=" position: relative;margin: 0px!important;padding:0px!important">
             <%@include file="footer.jsp" %> 
            
        </div>
        
        
       </div>
    </body>
</html>
