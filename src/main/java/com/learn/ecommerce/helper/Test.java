/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learn.ecommerce.helper;

import com.learn.ecommerce.entities.Product;
import com.learn.ecommerce.entities.UserProduct;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ashut
 */
public class Test {
    
    public static void main(String[] args) {
        
   
    
    Session ss= FactoryProvider.getFactory().openSession();
   Transaction tx= ss.beginTransaction();
    
   
   
   
   //to save new user and product 
//   User user=new User("userName", "userEmail", "userPassword", "8888", "userPic", "userAddress", "normal");
//   
//   Product product=new Product("pName", "pDesc", "pPhoto", 10, 10, 10);
//   ss.save(product);
//   
//   UserProduct userProduct=new UserProduct();
//     
//   userProduct.setProduct(product);
//   userProduct.setUser(user);
//   userProduct.setProductQuantity(145);
//  
//   user.addUserProduct(userProduct);
//   
//   ss.save(user);
   
   
//   
//   
//   //to save existing user and product 
//   User Euser=ss.get(User.class, 13);
//   
//   Product Eproduct=ss.get(Product.class, 1);
//   
//   
//    UserProduct userProduct=new UserProduct();
//     
//   userProduct.setProduct(Eproduct);
//   userProduct.setUser(Euser);
//   userProduct.setProductQuantity(1445);
//  
//   //Euser.addUserProduct(userProduct);
//   
//UserProductId s   =   (UserProductId) ss.save(userProduct);
//   
//
    UserProduct upd =null;

 try {
            
         //  String query="from UserProduct as up where up."+upd.getUser()+".userId =:e"; 
          // String query="from UserProduct"; 
           String query="from UserProduct  as up where up.user.userId =:e"; 
            
           
            Query q=ss.createQuery(query);
         //   q.setParameter("e", 10);
          q.setInteger("e", 10);
         //   upd    =(UserProduct) q.uniqueResult();
         
         
         
         
         
         
           List<UserProduct> list=q.list();
         
       
           
//           System.out.println("*******starttttt*************");
//           
//           for(UserProduct listsData : list){
//               System.out.println(listsData);
//           }
           
            
        int OrderQt=0;
        double OrderPrice=0.0;
           
           System.out.println("*******starttttt***for**looo*********");
           
            for(int i=0;i<list.size();i++){
               System.out.println(list.get(i));
               
              
              list.get(i).getProduct().getPriceAfterApplyingDiscount(); 
               list.get(i).getProductQuantity();
             OrderPrice=((list.get(i).getProductQuantity())  *  (list.get(i).getProduct().getPriceAfterApplyingDiscount())     );  
               
               
               
            OrderQt=OrderQt+list.get(i).getProductQuantity();   
               
               
               
           }
            
            System.out.println("OrderPrice"+OrderPrice);
            System.out.println("OrderQt"+OrderQt);
            
           System.out.println("*****end***for**looop*********");
           
            
              System.out.println("********whole***list*****");
              System.out.println(list);
              
              
              
              System.out.println("********first***row**of***list*****");
           System.out.println(list.get(0));   
              
              
         upd=list.get(0);
         
      // upd.getProductQuantity();
       System.out.println("********product**quantity*****");
       System.out.println(upd.getProductQuantity());
       
       
     System.out.println("********product**of**first**row**of***list*****");
       Product prd   =     upd.getProduct();
       
       
           System.out.println(prd);     
              
      //  prd.getpPrice();
      
        System.out.println("***price*****product**of**first**row**of***list*****");
           System.out.println( prd.getpPrice());   
              
        System.out.println("****discounted**price*****product**of**first**row**of***list*****");
           System.out.println( prd.getPriceAfterApplyingDiscount());   
              
              
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        


    












   //     System.out.println(upd);
      
    
    tx.commit();
    ss.close();
    
    
    
  }   
}
