
<%@page import="java.sql.SQLException"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date"%>
<%@page import="com.learn.ecommerce.entities.OrderTableBuy"%>
<%@page import="com.learn.ecommerce.helper.FactoryProvider"%>
<%@page import="com.learn.ecommerce.dao.OrderTableDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.learn.ecommerce.helper.UserProductsCount"%>
<%@page import="java.util.Map"%>
<%@ page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="SHORTCUT ICON" href="favicon.ico" type="image/x-icon" />


        <%--<%@include file="components/common_css_js.jsp" %>--%> 

        <title>Admin Dashboard</title>

                <%@include file="components/common_css_js.jsp" %>


        <link href="css/styles.css" rel="stylesheet"/>

        <link href="css/styles.css"/> 

 <%@include  file="components/Common_modals.jsp" %>

      <%--<%@include file="components/navbar.jsp" %>--%> 

      <style>
          .prd{background-color: #5e35b1;}
          .cat{ background-color: #ff7043;}
  .warning {background-color: #f0ad4e}
.danger {background-color: #d9534f}
.success {background-color: #5cb85c}
.inf {background-color: #5bc0de}

.statis {
  color: #EEE;
  margin-top: 15px;
}
.statis .box {
  position: relative;
  padding: 15px;
  overflow: hidden;
  border-radius: 3px;
  margin-bottom: 25px;
}
.statis .box h3:after {
  content: "";
  height: 2px;
  width: 70%;
  margin: auto;
  background-color: rgba(255, 255, 255, 0.12);
  display: block;
  margin-top: 10px;
}
.statis .box i {
  position: absolute;
  height: 70px;
  width: 70px;
  font-size: 22px;
  padding: 15px;
  top: -25px;
  left: -25px;
  background-color: rgba(255, 255, 255, 0.15);
  line-height: 60px;
  text-align: right;
  border-radius: 50%;
}

* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
  font-family: 'Droid Sans', sans-serif;
  outline: none;
}
::-webkit-scrollbar {
  background: transparent;
  width: 5px;
  height: 5px;
}
::-webkit-scrollbar-thumb {
  background-color: #888;
}
::-webkit-scrollbar-thumb:hover {
  background-color: rgba(0, 0, 0, 0.3);
}
body {background-color: #2a2b3d}
      </style>
      
      
      
   
    </head>
    <body style="background-color: #2a2b3d!important;"> 
        <%@include file="nav-Back.jsp" %>

     
        
        
        
        
        <!--to get dynamic user and product count-->
        <%  
            
           Map<String,Long> map=UserProductsCount.getCounts();


                        %>
                        
                        

        <!--admin validations-->
        <c:set var="userSS" scope="session" value="${sessionScope['current-user']}"   />
        <!-- <%--       <c:out value="${userSS}"/><br>
                <c:out value="${userSS.userType}"/><br>     --%>-->

        <c:choose>
            <c:when test="${userSS==null}">

                <c:set var="message" scope="session" value="You are not logged in !! Login first"   />

                <c:redirect url="login.jsp"/>

            </c:when>

            <c:otherwise>       

                <c:set var="String" value="${userSS.userType}"/> 

                <c:if test="${fn:contains(String, 'normal')}">

                    <c:set var="message" scope="session" value="You are not Admin !! Acess Denied !!"   />

                    <c:redirect url="login.jsp"/>

                </c:if>
            </c:otherwise>
        </c:choose> 
        <!--admin validations ends -->


       
                        <!--cards-->

        <div id="admin" class="container"  style="margin-top: 120px!important;">

            <div class="container-fluid mt-3"> 
                <%@include file="components/Message.jsp" %>   
            </div> 

            <div class="row mt-3">

                <div class="statis col-md-3 text-center">

                    <!--<div class="card text-center">-->
                        <!--<div class="card-body text-center">-->

<!--                            <img class="img-fluid rounded-circle"  style="max-width: 85px" src="https://image.flaticon.com/icons/svg/878/878719.svg"/>
                            <h1></h1>
                            <h1 class="text-uppercase text-muted">users</h1>-->
<!--
                                 <div class="box bg-primary">
              <i class="fa fa-eye"></i>
              <h3></h3>
              <p class="lead">Users</p>
            </div>-->
                        <!--</div>-->
                    <!--</div>-->

                       <div class="box danger">
              <i class="fa fa-user-o"></i>
              <h3><%=map.get("userCount")  %></h3>
              <p class="lead">User registered</p>
            </div>      
               

                </div>

                            
                             <div class="statis text-center   col-md-3">

<!--                    <div class="card">
                        <div class="card-body text-center">

                            <img class="img-fluid rounded-circle"  style="max-width: 85px" src="https://image.flaticon.com/icons/svg/878/878719.svg"/>
                            <h1></h1>
                            <h1 class="text-uppercase text-muted">Orders</h1>
                        </div>
                    </div>-->

 
                             <div class="box success">
                                 
                                 <a href="Orders.jsp"  class="stretched-link" ></a>
              <i class="fa fa-handshake-o"></i>
              <h3><%=map.get("orderCount")  %></h3>
              <p class="lead">Transactions</p>
            </div>
                            
                            
                </div>
                            
                            
                            
                            
                            
                <div class="statis text-center col-md-3">
                    
                    
                    <div class="box bg-primary">
                            <a href="Categories.jsp"  class="stretched-link" ></a>
              <i class="fa fa-eye"></i>
              <h3><c:out value="${fn:length( sessionScope['categoryList'] )}         "/></h3>
              <p class="lead">Categories</p>
            </div>
                    
<!--                    <div class="card">
                        <div class="card-body text-center">
                            
                             <a href="Categories.jsp"  class="stretched-link" ></a>
                            
                            <img class="img-fluid rounded-circle"  style="max-width: 85px" src="https://image.flaticon.com/icons/svg/878/878719.svg"/>
                           
                            <h1></h1>
                            <h1 class="text-uppercase text-muted">Categories</h1>
                        </div>
                    </div>-->


                </div>

                <div class="statis text-center  col-md-3">

<!--                    <div class="card">
                        <div class="card-body text-center">
                            
                               <a href="Products.jsp"  class="stretched-link" ></a>
                            
                            <img class="img-fluid rounded-circle"  style="max-width: 85px" src="https://image.flaticon.com/icons/svg/878/878719.svg"/>

                            <h1></h1>
                            <h1 class="text-uppercase text-muted">Products</h1>
                        </div>
                    </div>-->

      <div class="box warning">
                                   <a href="Products.jsp"  class="stretched-link" ></a>
              <i class="fa fa-shopping-cart"></i>
              <h3><%=map.get("productCount")  %></h3>
              <p class="lead">Products</p>
            </div>
                </div>

            </div>




            <!--second row-->

            <div class="row mt-3">

                <div class="statis text-center  col-md-6">

                    
                    <div style="cursor: pointer" class="box prd"  data-toggle="modal" data-target="#add-product-modal"  >
              <i class="fa fa-shopping-cart"></i>
                                          <img class="img-fluid"  style="max-width: 85px" src="img/Box-Add-198.png"/>

              <h3>Add new products</h3>
              <p class="lead">click here to add new product</p>
            </div>
                    
                    
                    
<!--                    <div class="card">>
                        <div class="card-body text-center"  data-toggle="modal" data-target="#add-product-modal" >

                            <img class="img-fluid rounded-circle"  style="max-width: 85px" src="https://image.flaticon.com/icons/svg/878/878719.svg"/>
                            <p class="mt-3">click here to add new product </p>
                            <h1 class="text-uppercase text-muted">Add products</h1>
                        </div>
                    </div>-->


                </div>

                <div class="statis text-center   col-md-6">
                    
                    
                    <div style="cursor: pointer;" class="box cat" data-toggle="modal" data-target="#add-category-modal">
                            <!--<a href="Categories.jsp"  class="stretched-link" ></a>-->
              <i class="fa fa-eye"></i>
                                          <img class="img-fluid "  style="max-width: 85px" src="img/Documents-Add-01-200.png"/>
                                         
              <h3>Add new Categories</h3>
              <!--<img src="img/Documents-Add-01-200.png" alt=""/>-->
              <p class="lead">click here to add new category</p>
            </div>
                    
                    
<!--                    <div class="card" data-toggle="modal" data-target="#add-category-modal" >
                        <div class="card-body text-center">
                            <img class="img-fluid rounded-circle"  style="max-width: 85px" src="https://image.flaticon.com/icons/svg/878/878719.svg"/>

                            <p class="mt-3">click here to add new category </p>
                            <h1 class="text-uppercase text-muted"> Add Categories</h1>
                        </div>
                    </div>-->


                </div>



            </div>














        </div>    





        <!--add category modal-->



        <!-- Modal -->
        <div class="modal fade" id="add-category-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Fill category details</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="AddCategoryServlet" method="post">

                            <input type="hidden" name="operation" value="addcategory"/>

                            <div class="form-group">
                                <input type="text" class="form-control" name="catTitle" required="true" placeholder="Enter Category title"/>
                            </div>
                            <div class="form-group">
                                <textarea style="height:252px;" class="form-control" name="catDescription" required="true" placeholder="Enter Category description"></textarea>
                            </div>
                            <div class="container text-center">
                                <button type="submit" class="btn btn-outline-success">Add Category</button>
                            </div>




                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <!--end add category modal-->


        <!--add product modal-->



        <!-- Modal -->
        <div class="modal fade" id="add-product-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Product details</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="AddProductServlet" method="post" enctype="multipart/form-data">

                            <!--<input type="hidden" name="operation" value="addproduct"/>-->

                            <div class="form-group">
                                <input type="text" class="form-control" name="pName" required="true" placeholder="Enter Product title"/>
                            </div>

                            <div class="form-group">
                                <textarea style="height:152px;" class="form-control" name="pDesc" required="true" placeholder="Enter Product description"></textarea>
                            </div>

                            <div class="form-group">
                                <input type="number" class="form-control" name="pPrice" required="true" placeholder="Enter Product price"/>
                            </div>
                            <div class="form-group">
                                <input type="number" class="form-control" name="pDiscount" required="true" placeholder="Enter Product discount"/>
                            </div>
                            <div class="form-group">
                                <input type="number" class="form-control" name="pQuantity" required="true" placeholder="Enter Product quantity"/>
                            </div>

    <%--                        <c:out value="${sessionScope['categoryList']}"/><br> 
                             <c:out value="${sessionScope}"/><br>     --%>

                            <div class="form-group">

                                <label for="exampleFormControlSelect1"> select category</label>
                                <select name="catId" class="form-control" id="exampleFormControlSelect1">

                                    <c:out value="${sessionScope['categoryList']}"/><br> 
                                    <c:forEach items="${sessionScope['categoryList']}" var="categoryL">

                                        <option value="${categoryL.categoryId}" >${categoryL.categoryTitle}</option>

                                    </c:forEach> 
                                        

                                </select>



                            </div>



                            <div class="form-group">
                                <label for="pPic"> select a picture</label>

                                <br>
                                <input type="file" class="form-control" name="pPic" id="pPic" required="true" />
                            </div>



                            <div class="container text-center">
                                <button type="submit" class="btn btn-outline-success">Add Product</button>
                                   <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>




                        </form>
                    </div>
                 
                </div>
            </div>
        </div>







        <!--end add product modal-->

        
        
        
         <div id="About" class="container-fluid" style=" position: relative;margin-top: 50px!important;padding:0px!important">
            <%@include file="footer.jsp" %> 

        </div>  
    </body>
</html>
