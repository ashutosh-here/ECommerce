/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learn.ecommerce.dao;

import com.learn.ecommerce.entities.OrderTableBuy;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ashut
 */
public class OrderTableDao {
   
     private SessionFactory factory ;

    public OrderTableDao(SessionFactory factory) {
        this.factory = factory;
    }
    
    public int saveOrder(OrderTableBuy order){
     
      Session ss=this.factory.openSession();  
      Transaction tx=ss.beginTransaction();
      
      int oId=(int) ss.save(order);
    //  String oId=(String) ss.save(order);
      tx.commit();
      
        ss.close();
        
        return oId;
       
    }
    
//    getting order date and order price to display on chart in admin.jsp
    
        public List<OrderTableBuy> getOrders(){
     
      Session ss=this.factory.openSession();  
      Transaction tx=ss.beginTransaction();
     Query q=ss.createQuery("from OrderTableBuy");
        List<OrderTableBuy> list=q.list();
        
        
        return list;
       
    }
    
    
    
    
    
    
    
}
