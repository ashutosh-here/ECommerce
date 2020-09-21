/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learn.ecommerce.entities;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author ashut
 */
@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pId;
    private String pName;
    @Column(length=3000)
    private String pDesc;
    private String pPhoto;
    private int pPrice;
    private int pDiscount;
    private int pQuantity;
    
   @ManyToOne 
   private Category category;

//   
//   @ManyToOne 
//      private OrderTableBuy orderD ;
//   
//   
//   
   
   
   
   
//   @OneToMany(mappedBy = "primaryKey.product",cascade = CascadeType.ALL)
 //   private List<UserProduct> userProducts = new ArrayList<>();
 
   
   @OneToMany(mappedBy = "product" )
   private List<UserProduct> userProducts = new ArrayList<>();
    
public void addUserProduct(UserProduct product) {
		this.userProducts.add(product);
	}

//@OneToMany(mappedBy = "primaryKey.group", 
//			cascade = CascadeType.ALL)
    public List<UserProduct> getUserProducts() {
        return userProducts;
    }

    public void setUserProducts(List<UserProduct> userProducts) {
        this.userProducts = userProducts;
    }

 

   
   
   
   
   
   
   
   
    public Product() {
    }

    public Product(String pName, String pDesc, String pPhoto, int pPrice, int pDiscount, int pQuantity,Category category) {
        this.pName = pName;
        this.pDesc = pDesc;
        this.pPhoto = pPhoto;
        this.pPrice = pPrice;
        this.pDiscount = pDiscount;
        this.pQuantity = pQuantity;
        this.category=category;
    }

    public Product(String pName, String pDesc, String pPhoto, int pPrice, int pDiscount, int pQuantity) {
        this.pName = pName;
        this.pDesc = pDesc;
        this.pPhoto = pPhoto;
        this.pPrice = pPrice;
        this.pDiscount = pDiscount;
        this.pQuantity = pQuantity;
    }

    
    
    
    
    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public String getpPhoto() {
        return pPhoto;
    }

    public void setpPhoto(String pPhoto) {
        this.pPhoto = pPhoto;
    }

    public int getpPrice() {
        return pPrice;
    }

    public void setpPrice(int pPrice) {
        this.pPrice = pPrice;
    }

    public int getpDiscount() {
        return pDiscount;
    }

    public void setpDiscount(int pDiscount) {
        this.pDiscount = pDiscount;
    }

    public int getpQuantity() {
        return pQuantity;
    }

    public void setpQuantity(int pQuantity) {
        this.pQuantity = pQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    

    @Override
    public String toString() {
        return "Product{" + "pId=" + pId + ", pName=" + pName + ", pDesc=" + pDesc + ", pPhoto=" + pPhoto + ", pPrice=" + pPrice + ", pDiscount=" + pDiscount + ", pQuantity=" + pQuantity + '}';
    }
    
    //calculate price after discount
    public double getPriceAfterApplyingDiscount(){
        
        double d= (this.getpDiscount() / 100.0)*this.getpPrice();
        
        d=Double.parseDouble(String.format("%.2f",d));      
        
        return this.getpPrice()-d;
        
    }
    
}
