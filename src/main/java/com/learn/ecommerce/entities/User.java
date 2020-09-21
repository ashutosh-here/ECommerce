package com.learn.ecommerce.entities;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ashut
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(length = 100, name = "user_name")
    private String userName;
    @Column(length = 100, name = "user_email",unique = true )
    private String userEmail;
    @Column(length = 100, name = "user_password")
    private String userPassword;
    @Column(length = 12, name = "user_phone")
    private String userPhone;
    @Column(length = 20, name = "user_gender")
    private String userGender;
//    @Column(length = 1500, name = "user_pic")
//    private String userPic;
    @Column(length = 1500, name = "user_address")
    private String userAddress;
    
    @Column(length = 25, name = "user_type")
    private String userType;
    
    
    
    
 @OneToMany(mappedBy ="userD")
    private List<OrderTableBuy> orders=new ArrayList<>(); 
    
 
 
 
 
// @OneToMany(mappedBy = "primaryKey.user",cascade = CascadeType.ALL)
 //private List<UserProduct> userProducts = new ArrayList<>();
 
 @OneToMany(mappedBy = "user")
 private List<UserProduct> userProducts = new ArrayList<>();
    
public void addUserProduct(UserProduct product) {
		this.userProducts.add(product);
	}

//@OneToMany(mappedBy = "primaryKey.user", 
//			cascade = CascadeType.ALL)
    public List<UserProduct> getUserProducts() {
        return userProducts;
    }

    public void setUserProducts(List<UserProduct> userProducts) {
        this.userProducts = userProducts;
    }

 
 
 
 
    public User(int userId, String userName, String userEmail, String userPassword, String userPhone, String userGender, String userAddress,String userType) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userGender = userGender;
        this.userAddress = userAddress;
        this.userType=userType;
    }

    public User(String userName, String userEmail, String userPassword, String userPhone, String userGender, String userAddress,String userType) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userGender = userGender;
        this.userAddress = userAddress;
         this.userType=userType;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

   

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword=" + userPassword + ", userPhone=" + userPhone + ", userGender=" + userGender + ", userAddress=" + userAddress + ", userType=" + userType + ", orders=" + orders + ", userProducts=" + userProducts + '}';
    }
    
    

   
    
    
    
    
    
    
    
    

}
