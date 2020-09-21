
<!-- cart  Modal -->
<div class="modal fade" id="cart" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Your Cart</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <div class="cart-body">
              
              
              
              
              
              
          </div>    
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary checkout-btn" onclick="gotoCheckout()">Checkout</button>
      </div>
    </div>
  </div>
</div>

<!-- cart  Modal ends -->




<!-- heart  Modal -->
<div class="modal fade" id="heart" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Your Wishlist</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <div class="heart-body">
              
              
              
              
              
              
          </div>    
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <!--<button type="button" class="btn btn-primary checkout-btn" onclick="gotoCheckout()">Checkout</button>-->
      </div>
    </div>
  </div>
</div>

<!-- heart  Modal ends -->



















<!--toast message-->
<div id='toast'></div>



<script>
 
 function gotoCheckout(){
     window.location="checkout.jsp"
 }
    
    
    
</script>
