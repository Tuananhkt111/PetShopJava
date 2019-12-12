<%@include file="../guest/header.jsp" %>
<!-- Page info -->
<div class="page-top-info">
    <div class="container">
        <h4>Your cart</h4>
        <div class="site-pagination">
            <a href="../guest/home.jsp">Home</a> /
            <a href="#">Your cart</a>
        </div>
    </div>
</div>
<!-- Page info end -->


<!-- cart section end -->
<section class="cart-section spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <div class="cart-table">
                    <h3>Your Cart</h3>
                    <div class="cart-table-warp" id="cart-table">
                        <table>
                            <thead>
                                <tr>
                                    <th class="product-th">Accessory</th>
                                    <th class="quy-th">Quantity</th>
                                    <th class="size-th">Price</th>
                                    <th class="total-th">Delete</th>
                                </tr>
                            </thead>
                            <tbody id="tbodyCart">
                            </tbody>
                        </table>
                    </div>
                    <div class="total-cost">
                        <h6>Total <span id="total-price">$0.00</span></h6>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 card-right">
                <a class="site-btn" onclick="checkOut()">Proceed to checkout</a>
                <a href="../guest/findAllAccAction.action" class="site-btn sb-dark">Continue shopping</a>
            </div>
        </div>
    </div>
</section>
<!-- cart section end -->
<!-- Related product section end -->
<%@include file="../guest/footer.jsp" %>
<script src="../js/cart.js"></script>
</body>
</html>
