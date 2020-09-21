/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learn.ecommerce.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author ashut
 */
@Entity
public class OrderTableBuy {
      @Id
      @Column(length = 25, name = "order_id")
       @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oId;
     
      @Column(length = 100, name = "order_quantity")
    private int oQuantity;
    
     @Column( name = "order_price")
    private int oPrice;

    
    @Temporal(javax.persistence.TemporalType.DATE)
     @Column( name = "order_date")
    private Date addedDate;
      
    
      @Column(length = 15, name = "order_status")
    private String oStatus;
     
    
    
    
      @ManyToOne 
      private User userD;
      
      
     

      
      
      
// @OneToMany(mappedBy ="orderD")
//    private List<Product>  OrderProducts=new ArrayList<>(); 

    public OrderTableBuy(int oId, int oQuantity, int oPrice, Date addedDate, String oStatus, User userD) {
        this.oId = oId;
        this.oQuantity = oQuantity;
        this.oPrice = oPrice;
        this.addedDate = addedDate;
        this.oStatus = oStatus;
        this.userD = userD;
    }
      
      
     

 

    public OrderTableBuy() {
    }

    public OrderTableBuy(int oQuantity, int oPrice, Date addedDate, String oStatus, User userD) {
        this.oQuantity = oQuantity;
        this.oPrice = oPrice;
        this.addedDate = addedDate;
        this.oStatus = oStatus;
        this.userD = userD;
    }

    public int getoQuantity() {
        return oQuantity;
    }

    public void setoQuantity(int oQuantity) {
        this.oQuantity = oQuantity;
    }

    public int getoPrice() {
        return oPrice;
    }

    public void setoPrice(int oPrice) {
        this.oPrice = oPrice;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getoStatus() {
        return oStatus;
    }

    public void setoStatus(String oStatus) {
        this.oStatus = oStatus;
    }

    public User getUserD() {
        return userD;
    }

    public void setUserD(User userD) {
        this.userD = userD;
    }

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }
      
    
      
    
    
      
      
}
