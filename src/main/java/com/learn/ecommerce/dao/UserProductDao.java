/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learn.ecommerce.dao;

import com.learn.ecommerce.entities.OrderTableBuy;
import com.learn.ecommerce.entities.Product;
import com.learn.ecommerce.entities.User;
import com.learn.ecommerce.entities.UserProduct;
import com.learn.ecommerce.helper.FactoryProvider;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class UserProductDao {

    private SessionFactory factory;

    public UserProductDao(SessionFactory factory) {
        this.factory = factory;
    }

    public boolean saveUserProduct(int uid, int pid, int pQuantity) {
        boolean f = false;

        Session ss = this.factory.openSession();
        Transaction tx = ss.beginTransaction();

        //to save existing user and product 
        User Euser = ss.get(User.class, uid);

        Product Eproduct = ss.get(Product.class, pid);

        UserProduct userProduct = new UserProduct();

        userProduct.setProduct(Eproduct);
        userProduct.setUser(Euser);
        userProduct.setProductQuantity(pQuantity);
        
          
 long time=System.currentTimeMillis();
        
        Timestamp tp=new java.sql.Timestamp(time);
    tp.setNanos(0);
    
  //  String ts=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(tp);

        
        //  Date d=new Date();
        //   String s=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(d);
        userProduct.setOrderTimestamp((tp));
        //???no  //Euser.addUserProduct(userProduct);
        //   UserProductId s=   (UserProductId) ss.save(userProduct);
        ss.save(userProduct);

        f = true;

        //   System.out.println(s);
        tx.commit();
        ss.close();

//    
//   if(s!=null){
//       f=true;
//   } 
        return f;
    }

    public boolean saveInOrderTableBuy(int userID, User user) {
        boolean f = false;

        Session ss = this.factory.openSession();
        Transaction tx = ss.beginTransaction();

        UserProduct upd = null;

        try {

            String query = "from UserProduct  as up where up.user.userId =:e AND up.orderTimestamp >:t";

            Query q = ss.createQuery(query);
            q.setInteger("e", userID);
            
            long time=System.currentTimeMillis()-5000;
        
        Timestamp tp=new java.sql.Timestamp(time);
            tp.setNanos(0);
            
            
            q.setParameter("t", tp);
            
            
            List<UserProduct> list = q.list();

            int OrderQt = 0;
            double OrderPrice = 0.0;

            //   System.out.println("*******starttttt***for**looop*********");
               System.out.println("***************************list of user--Products****************************");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));

                
                list.get(i).getProduct().getPriceAfterApplyingDiscount();
                list.get(i).getProductQuantity();
                OrderPrice =OrderPrice+ ((list.get(i).getProductQuantity()) * (list.get(i).getProduct().getPriceAfterApplyingDiscount()));

                OrderQt = OrderQt + list.get(i).getProductQuantity();

            }
            //     System.out.println("OrderPrice"+OrderPrice);
            //   System.out.println("OrderQt"+OrderQt);

            OrderTableBuy order = new OrderTableBuy(OrderQt, (int) OrderPrice, new Date(), "f", user);

            OrderTableDao oDao = new OrderTableDao(FactoryProvider.getFactory());

            int oid = oDao.saveOrder(order);

            System.out.println("order saved in ORDERTABLEBUY:-" + oid);

            
            OrderQt=0;
            
            
      
            
//            System.out.println("******end***for*****loop***********");
//
//            System.out.println("********whole***list*****");
//            System.out.println(list);
//
//            System.out.println("********first***row**of***list*****");
//            System.out.println(list.get(0));
//
//            upd = list.get(0);
//
//            // upd.getProductQuantity();
//            System.out.println("********product**quantity*****");
//            System.out.println(upd.getProductQuantity());
//
//            System.out.println("********product**of**first**row**of***list*****");
//            Product prd = upd.getProduct();
//
//            System.out.println(prd);
//
//            //  prd.getpPrice();
//            System.out.println("***price*****product**of**first**row**of***list*****");
//            System.out.println(prd.getpPrice());
//
//            System.out.println("****discounted**price*****product**of**first**row**of***list*****");
//            System.out.println(prd.getPriceAfterApplyingDiscount());

            f = true;

        } catch (HibernateException e) {
            e.printStackTrace();
        }

        tx.commit();
        ss.close();

        return f;
    }

    
    public List<UserProduct> getOrderedProductsByUser(int uid){
        
        
           Session ss = this.factory.openSession();
        Transaction tx = ss.beginTransaction();

          List<UserProduct> list =null;
        UserProduct upd = null;

        try {

            String query = "from UserProduct  as up where up.user.userId =:e";

            Query q = ss.createQuery(query);
            q.setInteger("e", uid);
            
                 list = q.list();

        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        return list;
    }
    
}
