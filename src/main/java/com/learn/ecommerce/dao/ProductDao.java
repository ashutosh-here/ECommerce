/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learn.ecommerce.dao;

import com.learn.ecommerce.entities.Product;
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
public class ProductDao {
    
    private SessionFactory factory;

    public ProductDao(SessionFactory factory) {
        this.factory = factory;
    }
    
    public boolean saveProduct(Product product){
        
        boolean f=false;
        try {
            
            Session ses=this.factory.openSession();
            Transaction tx=ses.beginTransaction();
            
            ses.save(product);
            
            tx.commit();
            ses.close();
            f=true;
            
        } catch (HibernateException e) {
            e.printStackTrace();
            f=false;
        }
        
        
        
        
      return f;  
        
        
    }
    
    public  List<Product> getAllProducts(){
        Session s=this.factory.openSession();
        Query q=s.createQuery("from Product");
        List<Product> list=q.list();
        
        
        return list;
        
    }
    
    
    
    // get all products of given category
     public  List<Product> getAllProductsById( int cid ){
        Session s=this.factory.openSession();
        Query q=s.createQuery("from Product as p where p.category.categoryId =:id");
        q.setParameter("id", cid);
        List<Product> list=q.list();
        
        
        return list;
        
    }
   
     
     
     
     
     
     
     public Product getProductById(int pid){

          Product product=null;

      Session s=this.factory.openSession();
        Query q=s.createQuery("from Product as p where p.pId =:id");
        q.setParameter("id", pid);
       product=(Product) q.uniqueResult();
       
        
s.close();

return product;

}
     
     
     
     
     
        public boolean updateProduct(Product product){
        
        boolean f=false;
        try {
            
            Session ses=this.factory.openSession();
            Transaction tx=ses.beginTransaction();
            
            ses.saveOrUpdate(product);
            
            tx.commit();
            ses.close();
            f=true;
            
        } catch (HibernateException e) {
            e.printStackTrace();
            f=false;
        }
        
        
        
        
      return f;  
        
        
    }
     
     
     
      public boolean deleteProduct(Product prd){
       boolean f=false;
       
       
       Session ss=this.factory.openSession();
      Transaction tx=ss.beginTransaction();
      
      
//      prd.getCategory().getProducts().remove(prd);
      
      
         ss.delete(prd);
     
   
          
          
          f=true;
     
      
      
      tx.commit();
      
        ss.close();
//        ss.flush();
       
       return f;
   }
    
     
     
     
}

