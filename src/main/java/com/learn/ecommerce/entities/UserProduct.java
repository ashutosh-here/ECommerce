/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learn.ecommerce.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

/**
 *
 * @author ashut
 */


//@Entity
//@Table(name = "USERS_PRODUCTS")
//@AssociationOverrides({
//	@AssociationOverride(name = "primaryKey.user", 
//		joinColumns = @JoinColumn(name = "USER_ID")),
//	@AssociationOverride(name = "primaryKey.product", 
//		joinColumns = @JoinColumn(name = "PRODUCT_ID")) 



@Entity
@Table(name = "USERS_PRODUCTS")
public class UserProduct  {
   
    
        @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "USER_PRODUCT_ID")
      private int id;
    
    
    
    
   @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
      private User user;
      
   
    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_ID")
      private Product product;

    private int productQuantity;

  
//    @Temporal(TemporalType.TIME)
//    private Date orderTimestamp;
    private Timestamp orderTimestamp;
    
    
    

//    public Date getOrderTimestamp() {
//        return orderTimestamp;
//    }
//
//    public void setOrderTimestamp(Date orderTimestamp) {
//        this.orderTimestamp = orderTimestamp;
//    }

    public Timestamp getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp(Timestamp orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }
    
    
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    
//    @ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    
    
    
//    @ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "PRODUCT_ID")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

   
    
    
    
    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    
    
    
    
    
    
    
    
    
    
    
    //    
//     private UserProductId primaryKey = new UserProductId();
//    
//    @EmbeddedId
//    public UserProductId getPrimaryKey() {
//        return primaryKey;
//    }
//
//    public void setPrimaryKey(UserProductId primaryKey) {
//        this.primaryKey = primaryKey;
//    }
//
//    @Transient
//    public User getUser() {
//        return getPrimaryKey().getUser();
//    }
//
//    public void setUser(User user) {
//        getPrimaryKey().setUser(user);
//    }
//
//    @Transient
//    public Product getProduct() {
//        return getPrimaryKey().getProduct();
//    }
//
//    public void setProduct(Product product) {
//        getPrimaryKey().setProduct(product);
//    }
//
//    
//    
// 
}
