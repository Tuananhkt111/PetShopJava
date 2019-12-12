<%@include file="../guest/header.jsp" %>
<!-- Page info -->
<div class="page-top-info">
    <div class="container">
        <h4>Check out</h4>
        <div class="site-pagination">
            <a href="../guest/home.jsp">Home</a> /
            <a href="#">Check out</a>
        </div>
    </div>
</div>
<!-- Page info end -->


<!-- checkout section  -->
<section class="checkout-section spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 order-2 order-lg-1">
                <form class="checkout-form" action="checkOutAction" method="POST" id="checkout-form">
                    <div class="cf-title">Billing Address</div>
                    <div class="row">
                        <div class="col-md-3">
                            <p>*Billing Information:</p>
                        </div>
                    </div>
                    <div class="row address-inputs">
                        <div class="col-md-12">
                            Address: <input type="text" name="deliveryAddress" placeholder="Address" value="<s:property value="%{deliveryAddress}"/>" pattern=".{1,70}">
                            Phone: <input type="text" name="deliveryPhone" placeholder="Phone no." value="<s:property value="%{deliveryPhone}"/>" pattern="[0-9]{10,16}">
                        </div>
                    </div>
                    <div class="cf-title">Delievery Info</div>
                    <div class="mb-5 text-center">
                        <h4>Free Shipping</h4>
                    </div>
                    <div class="cf-title">Payment</div>
                    <ul class="payment-list text-center">
                        <li>Pay when you get the package</li>
                    </ul>
                    <button type="submit" class="site-btn submit-order-btn">Place Order</button>
                </form>
            </div>
            <div class="col-lg-4 order-1 order-lg-2">
                <div class="checkout-cart">
                    <h3>Your Cart</h3>
                    <ul class="product-list">
                        <s:iterator value="cart">     
                            <li>
                                <div class="pl-thumb"><img src="../img/product/<s:property value="value.image"/>" onerror="errorImage()"></div>
                                <h6><s:property value="value.accName"/></h6>
                                <p class="current-price">$<s:property value="%{getText('{0,number,##0.00}',{value.price*(1 - value.salePercent)})}"/> x <s:property value="%{value.quantity}"/></p> 
                            </li>   
                        </s:iterator>
                    </ul>
                    <ul class="price-list">
                        <li>Total<span class="total-price"></span></li>
                        <li>Shipping<span>free</span></li>
                        <li class="total">Total<span class="total-price"></span></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- checkout section end -->
<%@include file="../guest/footer.jsp" %>
<script src="../js/checkout.js"></script>
</body>
</html>
