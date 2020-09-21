/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learn.ecommerce.helper;

import java.util.HashMap;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.query.Query;


/**
 *
 * @author ashut
 */
public class UserProductsCount {
    
    
    public static Map<String,Long> getCounts(){
        
        Session ss=FactoryProvider.getFactory().openSession();
        String q1="Select count(*) from User";
        String q2="Select count(*) from Product";
        String q3="Select count(*) from OrderTableBuy";
        
        Query query1=ss.createQuery(q1);
        Query query2=ss.createQuery(q2);
        Query query3=ss.createQuery(q3);
        
        Long userCount=(Long)query1.list().get(0);
        Long productCount=(Long)query2.list().get(0);
        Long orderCount=(Long)query3.list().get(0);
        
        Map<String , Long> map = new HashMap<>();
        map.put("userCount", userCount);
        map.put("productCount", productCount);
        map.put("orderCount", orderCount);
        
        return map;
        
    }
    
    
    
}
