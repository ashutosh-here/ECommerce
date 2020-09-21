/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learn.ecommerce.helper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.text.StringEscapeUtils;

/**
 *
 * @author ashut
 */
public class OrderTemp {
    
    
   private int productId;
    private String productName;
    private int productQuantity;
    private double productPrice;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "OrderTemp{" + "productId=" + productId + ", productName=" + productName + ", productQuantity=" + productQuantity + ", productPrice=" + productPrice + '}';
    }
    
    
    
    
    
    
            
            
            
            
            
    
    
    
    
    
    public static OrderTemp[] getOrderTemp(String str){
        
        
//         String gson4="{'productId':26,'productName':'bhaaukaaaaal','productQuantity':2,'productPrice':54271.36}";
//            Gson gson=new Gson();
//         
//           OrderTemp orrder=null;
//            try {
//
//            // Convert JSON File to Java Object
//             orrder = gson.fromJson(gson4, OrderTemp.class);
//
//			// print staff object
//          //  System.out.println(orrder);
//
//        } catch (IllegalStateException | JsonSyntaxException  e) {
//            e.printStackTrace();
//        }
//       return orrder;





    OrderTemp[] orrder=null;

 String gson4=  "[{'productId':26,'productName':'bhaaukaaaaal','productQuantity':2,'productPrice':54271.36},"+ "{'productId':6,'productName':'bhal','productQuantity':22,'productPrice':512.36}]";

 String gsss="[{'productId':26,'productName':'bhaaukaaaaal','productQuantity':2,'productPrice':54271.36},\"+ \"{'productId':22,'productName':'halka2','productQuantity':2,'productPrice':-10.11}]";

  String gson3=gsss.replaceAll("\\\\", ""); // replaces all /
 String ss3=gson3.replace("\\", "");
 
 //    System.out.println("ttteeeeeeessstiing string");
 //       System.out.println(gson4);
  //      System.out.println(gsss);
      //  System.out.println(gson3);
     //   System.out.println(ss3);
 
 
    String out = StringEscapeUtils.unescapeJava(gsss);

 //   System.out.println(out);
 
 
 ////       System.out.println("coming string");
 //       System.out.println(str);
 
 
 
            Gson gson=new Gson();
         
            
            try {

            // Convert JSON File to Java Object
            orrder = gson.fromJson(str, OrderTemp[].class);

			// print staff object
          //  System.out.println(orrder);
            
            for(OrderTemp user : orrder) {
  //  System.out.println(user);
}

        } catch (IllegalStateException | JsonSyntaxException  e) {
            e.printStackTrace();
        }
       return orrder;
            








        
    }
    
    
}
