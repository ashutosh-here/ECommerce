/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learn.ecommerce.dao;

import com.learn.ecommerce.entities.Category;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author ashut
 */
public class CategoryDao {
    
    private SessionFactory factory;

    public CategoryDao(SessionFactory factory) {
        this.factory = factory;
    }
    
    public int saveCategory(Category Cat){
     
      Session ss=this.factory.openSession();
      Transaction tx=ss.beginTransaction();
      
      int catId=(int) ss.save(Cat);
      tx.commit();
      
        ss.close();
        
        return catId;
       
    }
    
    
  public List<Category> getCategories(){
   
      Session s=this.factory.openSession();
      Query query=s.createQuery("from Category");
      List<Category> list=query.list();
      
      s.close();
      
      return list;
      
  }  
    
   public Category getCategoryById(int cid){
       
       Category cat=null;
       try {
           
             Session ss=this.factory.openSession();
              cat=ss.get(Category.class, cid);
              ss.close();
       } catch (HibernateException e) {
           e.printStackTrace();
       }
     
    return cat;
   } 
    
   
   
   public boolean updateCategory(Category newCat){
      boolean f=false;
 
        Session ss=this.factory.openSession();
      Transaction tx=ss.beginTransaction();
      
       ss.saveOrUpdate(newCat);
      tx.commit();
      
        ss.close();      
      f=true;
      
       
       return f;
   }
   
   public boolean deleteCategoryById(int catId){
       boolean f=false;
       
       
       Session ss=this.factory.openSession();
      Transaction tx=ss.beginTransaction();
      Object cObj =ss.load(Category.class, catId);
     
      if(cObj!=null){
          
          try {
                     ss.delete(cObj);
          } catch (Exception e) {
          }
     
   
          
          
          f=true;
      }
      
      
      tx.commit();
      
        ss.close();
       
       return f;
   }
    
}
