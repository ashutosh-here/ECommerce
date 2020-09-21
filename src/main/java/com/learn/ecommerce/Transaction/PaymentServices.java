package com.learn.ecommerce.Transaction;
 
import java.util.*;
 
import com.paypal.api.payments.*;
import com.paypal.base.rest.*;
 
public class PaymentServices {
    private static final String CLIENT_ID = "AdsJQxJYl4uQDSbAttsUx97NKkzumPNtMtUUO-MPUi7lezqUd0KJmnmiAK7F594A2eCtTcPSGxbmxies";
    private static final String CLIENT_SECRET = "EHj68wmZXnhTWrUCW-PsfOr-mRUrBlXCCfPAQeHQCrm_XnyFq-a_QARo3PJV1aM9cWsFMkgn0KdqOuE5";
    private static final String MODE = "sandbox";
 
    public String authorizePayment(OrderDetail orderDetail)        
            throws PayPalRESTException {       
 
        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(orderDetail);
         
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");
 
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
 
        Payment approvedPayment = requestPayment.create(apiContext);
 
        return getApprovalLink(approvedPayment);
 
    }
     
   private Payer getPayerInformation() {
    Payer payer = new Payer();
    payer.setPaymentMethod("paypal");
     
    PayerInfo payerInfo = new PayerInfo();
    payerInfo.setFirstName("Test")
             .setLastName("Testing")
             .setEmail("test@Tesing.com");
     
    payer.setPayerInfo(payerInfo);
     
    return payer;
}
     
   private RedirectUrls getRedirectURLs() {
    RedirectUrls redirectUrls = new RedirectUrls();
    redirectUrls.setCancelUrl("http://localhost:5050/ECommerce/cancel.html");
    redirectUrls.setReturnUrl("http://localhost:5050/ECommerce/review_payment");
     
    return redirectUrls;
}
     
   private List<Transaction> getTransactionInformation(OrderDetail orderDetail) {
    Details details = new Details();
    details.setShipping(orderDetail.getShipping());
    details.setSubtotal(orderDetail.getSubtotal());
    details.setTax(orderDetail.getTax());
 
    Amount amount = new Amount();
                                                          amount.setCurrency("INR");
    amount.setTotal(orderDetail.getTotal());
    amount.setDetails(details);
 
    Transaction transaction = new Transaction();
    transaction.setAmount(amount);
    transaction.setDescription(orderDetail.getProductName());
     
    ItemList itemList = new ItemList();
    List<Item> items = new ArrayList<>();
     
    Item item = new Item();
                                                                                            item.setCurrency("INR");
    item.setName(orderDetail.getProductName());
    item.setPrice(orderDetail.getSubtotal());
    item.setTax(orderDetail.getTax());
    item.setQuantity("1");
     
    items.add(item);
    itemList.setItems(items);
    transaction.setItemList(itemList);
 
    List<Transaction> listTransaction = new ArrayList<>();
    listTransaction.add(transaction);  
     
    return listTransaction;
}
     
   private String getApprovalLink(Payment approvedPayment) {
    List<Links> links = approvedPayment.getLinks();
    String approvalLink = null;
     
    for (Links link : links) {
        if (link.getRel().equalsIgnoreCase("approval_url")) {
            approvalLink = link.getHref();
            break;
        }
    }      
     
    return approvalLink;
}
   public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
    APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
    return Payment.get(apiContext, paymentId);
}
   
   
   
   
   
   
   public Payment executePayment(String paymentId, String payerId)
        throws PayPalRESTException {
    PaymentExecution paymentExecution = new PaymentExecution();
    paymentExecution.setPayerId(payerId);
 
    Payment payment = new Payment().setId(paymentId);
 
    APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
 
    return payment.execute(apiContext, paymentExecution);
}
   
   
   
   
   
   
   
   
   
}