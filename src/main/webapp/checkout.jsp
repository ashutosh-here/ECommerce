<%  
//checking for login

//
// response.addHeader("Access-Control-Allow-Origin", "*");
// response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
// response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
// response.addHeader("Access-Control-Max-Age", "1728000");     
//


    
    
    %>
    
        
        
        
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout Page</title>
        <link rel="SHORTCUT ICON" href="favicon.ico" type="image/x-icon" />

        <%@include file="components/common_css_js.jsp" %> 
        <link href="css/styles.css" rel="stylesheet"/>

        <link href="css/styles.css"/> 
<!--sweetlaert-->
 <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

 <style>

.loader svg {
  position: absolute;
  top: 40%;
  left: 45%;
  height: 10em;
  transform: rotate(-60deg);
  color: black;
  animation: 1s linear 0s infinite rotate;
}

#shadow {
  animation: 1s linear 0s infinite reverse rotate;
  color: darkgrey;
  opacity: 90%;
  z-index: 1;
  opacity: 70%;
}

@keyframes rotate {
  from {transform: rotate(-60deg)}
  to {transform: rotate(300deg)}
}
 </style>
 
 
 
    </head>
    <body>
        
        
        
        
        
<!--    //checking for login-->
        <!--admin validations-->
        <c:set var="userSS" scope="session" value="${sessionScope['current-user']}"   />
        <!-- <%--       <c:out value="${userSS}"/><br>
                <c:out value="${userSS.userType}"/><br>     --%>-->

        <c:choose>
            <c:when test="${userSS==null}">

                <c:set var="message" scope="session" value="You are not logged in !! Login first to confirm order"   />

                <c:redirect url="login.jsp"/>

            </c:when>

          
        </c:choose> 
        <!--admin validations ends -->

        
        
        <%@include file="components/Common_modals.jsp" %> 
        <%--<%@include file="components/navbar.jsp" %>--%> 
 <%@include file="nav-Back.jsp" %>


        <div  style="margin-top: 120px!important;"    class="container bS">
            <div class="row mt-5">

                <div class="col-md-8 ">
                    <!--cart-->
                    <div   class="card">
                        <div class="card-body">

                            <h3 class="text-center mb-5">Your selected items</h3> 

                            <div class="cart-body">
                            </div>


                        </div>
                    </div>

                </div>
              
                <div class="col-md-4 ">
                    <!--form details-->

                    <div class="card">
                        <div class="card-body">

                            <h3 class="text-center mb-5">Your Details for Order</h3> 

                            <form  id="OrderForm"  action="OrderServlet" method="post"     >
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Email address</label>
                                    <input readonly name="email" value="${userSS.userEmail}"  type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                                </div>
                                <div class="form-group">
                                    <label for="name">Your Name</label>
                                    <input name="name"  value="${userSS.userName}" type="text" class="form-control" id="name" placeholder="Enter name" />
                                </div>                                
                                <div class="form-group">
                                    <label for="name">Your Contact</label>
                                    <input name="contact"  value="${userSS.userPhone}" type="text" class="form-control" id="name" placeholder="Enter phone number" />
                                </div>                                
                                <div class="form-group">
                                    <label for="exampleFormControlTextarea1">Shipping Address</label>
                                    <textarea  name="address"   class="form-control" id="exampleFormControlTextarea1" rows="3"  placeholder="Enter the shipping address"  >${userSS.userAddress}</textarea>
                                </div>


                                <div class="container text-center">
                                 <!--<a type="submit" href="#!" class="btn btn-outline-success">Order Now</a>-->
                                 <button type="submit" class="btn btn-outline-success">Order Now</button>
                                <!--<a type="submit" href="index.jsp" class="btn btn-outline-primary">Continue shopping</a>-->
                                </div>
                               
                            </form>

                        </div>
                    </div>



                </div>







            </div>
        </div>

          <div class="loader" id="load"  style="display: none;">
<svg aria-hidden="true" focusable="false" data-prefix="far" data-icon="spinner-third" class="svg-inline--fa fa-spinner-third fa-w-16" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path fill="currentColor" d="M460.116 373.846l-20.823-12.022c-5.541-3.199-7.54-10.159-4.663-15.874 30.137-59.886 28.343-131.652-5.386-189.946-33.641-58.394-94.896-95.833-161.827-99.676C261.028 55.961 256 50.751 256 44.352V20.309c0-6.904 5.808-12.337 12.703-11.982 83.556 4.306 160.163 50.864 202.11 123.677 42.063 72.696 44.079 162.316 6.031 236.832-3.14 6.148-10.75 8.461-16.728 5.01z"></path></svg>

<svg id="shadow" aria-hidden="true" focusable="false" data-prefix="far" data-icon="spinner-third" class="svg-inline--fa fa-spinner-third fa-w-16" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path fill="currentColor" d="M460.116 373.846l-20.823-12.022c-5.541-3.199-7.54-10.159-4.663-15.874 30.137-59.886 28.343-131.652-5.386-189.946-33.641-58.394-94.896-95.833-161.827-99.676C261.028 55.961 256 50.751 256 44.352V20.309c0-6.904 5.808-12.337 12.703-11.982 83.556 4.306 160.163 50.864 202.11 123.677 42.063 72.696 44.079 162.316 6.031 236.832-3.14 6.148-10.75 8.461-16.728 5.01z"></path></svg>
</div>                        
                                
                                
          <div id="About" class="container-fluid" style=" position: relative;margin-top: 50px!important;padding:0px!important">
            <%@include file="footer.jsp" %> 

        </div>                       
                                
<script>
 
 function backToShopping(){
     window.location="index.jsp"
 }
    
    
    
</script>                                
                                

    </body>
</html>
