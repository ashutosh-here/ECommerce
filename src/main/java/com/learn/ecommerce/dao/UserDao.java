/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learn.ecommerce.dao;

import com.learn.ecommerce.entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ashut
 */
public class UserDao {
    
    private SessionFactory factory;

    public UserDao(SessionFactory factory) {
        this.factory = factory;
    }
    
    //get user by email and passowrd
    public  User getUserByEmailAndPassword(String email, String password){
      
       User user=null;
        
        try {
            
           String query="from User where userEmail =:e and userPassword =:p"; 
            
            Session session=this.factory.openSession();
            Query q=session.createQuery(query);
            q.setParameter("e", email);
            q.setParameter("p", password);
            user=(User)q.uniqueResult();
            session.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        
        
        
        
        return user;
       
        
    }
    
    
    
    
    
    
    
    
    public User getUserByEmail(String email){
       
        User user=null;
        
        
         try {
            
           String query="from User where userEmail =:e"; 
            
            Session session=this.factory.openSession();
            Query q=session.createQuery(query);
            q.setParameter("e", email);
          
            user=(User)q.uniqueResult();
            session.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        
        
     return user;
    }
    
    
   public boolean updateUser(String name,String phone,String address,int id){
    
       boolean f=false;
   int  temp = 0;
       
       try {
            
          Session ss=this.factory.openSession();
      Transaction tx=ss.beginTransaction();
      
      User updatedUser=ss.get(User.class, id);
      
      updatedUser.setUserName(name);
      
      updatedUser.setUserPhone(phone);
      
      updatedUser.setUserAddress(address);
      
       temp  =    (int) ss.save(updatedUser);
      
      
      tx.commit();
      
        ss.close();
        
            
        if(temp>0){
            f=true;
        }
        
        
        
        
        } catch (HibernateException e) {
             e.printStackTrace();

        }
        
       
       
       
       
       
       
       
       return f;
   }
    
    
    
    
    
}
