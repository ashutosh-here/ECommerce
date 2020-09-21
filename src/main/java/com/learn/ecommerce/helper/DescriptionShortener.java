/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learn.ecommerce.helper;

/**
 *
 * @author ashut
 */
public class DescriptionShortener {
    
   public static String get10Words(String Desc){
       String[] strs=Desc.split(" ");
       
       if (strs.length>10) {
        String result="";
        
         for(int i=0;i<10;i++){
             result=result+strs[i]+" ";
         }  
           
         return (result+"...");
           
           
           
           
       } else {
           
           return (Desc+"...");
       }
      
   }
    
}
