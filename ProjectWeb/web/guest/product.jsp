<%@include file="../guest/header.jsp" %>
<!-- Page info -->
<div class="page-top-info">
    <div class="container">
        <h4>Product Page</h4>
        <div class="site-pagination">
            <a href="../guest/home.jsp">Home</a> /
            <a href="../guest/accessories.jsp">Accessories</a> / 
            <a href="../guest/product.jsp">Product</a>
        </div>
    </div>
</div>
<!-- Page info end -->
<!-- product section -->
<section class="product-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <img class="product-big-img" src="../img/product/<s:property value="%{dto.image}"/>" alt="">
            </div>
            <div class="col-lg-6 product-details">
                <h2 class="p-title"><s:property value="%{dto.accName}"/></h2>
                <h3 class="p-price">
                    <s:if test="%{dto.salePercent == 0}">
                        $<s:property value="%{dto.price}"/>
                    </s:if>
                    <s:else>
                        <del style="color:gray"; class="ml-2">$<s:property value="%{dto.price}"/></del>
                        $<s:property value="%{getText('{0,number,##0.00}',{dto.price*(1 - dto.salePercent)})}"/>
                    </s:else>
                </h3>
                <h4 class="p-stock">Available: <span>
                        <s:if test="%{dto.isDelete == true}">
                            <div style="color:red">Stop selling</div>
                        </s:if>
                        <s:elseif test="%{dto.availableQuantity == 0}">
                            <div style="color:red">Out of Stock</div>
                        </s:elseif>
                        <s:else>
                            In Stock
                        </s:else>
                    </span></h4>
                <div class="p-rating">
                    Favorite: <span style="color:#ff0066"><s:property value="%{countFavorite}"/></span>
                </div>
                <div class="p-rating">
                    Category: <span style="color:#0066ff; font-weight: bold"><s:property value="%{accCategory}"/></span>
                </div>
                <div class="p-rating">
                    Brand: <span style="color:#0066ff; font-weight: bold"><s:property value="%{dto.brand}"/></span>
                </div>
                <div class="quantity">
                    <p>Quantity</p>
                    <s:if test="%{dto.isDelete == true || dto.availableQuantity == 0}">
                        <input type="number" id="inputQuantity" name="quantity" value="0" class="form-control quantity-acc" disabled>
                    </s:if>
                    <s:else>                        
                        <input type="number" id="inputQuantity" name="quantity" min="1" max="<s:property value="%{dto.availableQuantity}"/>" class="form-control quantity-acc" value="1">
                    </s:else>   
                </div>
                <s:if test="%{dto.isDelete == false && dto.availableQuantity != 0}">
                    <div class="p-rating">
                        Available Quantity: <span style="color:#0066ff"><s:property value="%{dto.availableQuantity}"/></span>
                    </div>
                </s:if>
                <button onclick="addCart('<s:property value="%{accIdSearch}"/>', '<s:property value="%{dto.availableQuantity}"/>')" class="site-btn">SHOP NOW</button>
                <div id="accordion" class="accordion-area">
                    <div class="panel">
                        <div class="panel-header" id="headingOne">
                            <button class="panel-link active" data-toggle="collapse" data-target="#collapse1" aria-expanded="true" aria-controls="collapse1">information</button>
                        </div>
                        <div id="collapse1" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                            <div class="panel-body">
                                <p><s:property value="%{dto.description}"/></p>
                            </div>
                        </div>
                    </div>
                    <div class="panel">
                        <div class="panel-header" id="headingTwo">
                            <button class="panel-link" data-toggle="collapse" data-target="#collapse2" aria-expanded="false" aria-controls="collapse2">care details </button>
                        </div>
                        <div id="collapse2" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                            <div class="panel-body">
                                <p>Always answer any questions from customer</p>
                            </div>
                        </div>
                    </div>
                    <div class="panel">
                        <div class="panel-header" id="headingThree">
                            <button class="panel-link" data-toggle="collapse" data-target="#collapse3" aria-expanded="false" aria-controls="collapse3">shipping & Returns</button>
                        </div>
                        <div id="collapse3" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                            <div class="panel-body">
                                <h4>7 Days Returns</h4>
                                <p>Freeship in HCM City<br>Home Delivery <span>3 - 4 days</span></p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="social-sharing">
                    <a href="https://www.google.com/"><i class="fa fa-google-plus"></i></a>
                    <a href="https://www.pinterest.com/"><i class="fa fa-pinterest"></i></a>
                    <a href="https://www.facebook.com/"><i class="fa fa-facebook"></i></a>
                    <a href="https://www.twitter.com/"><i class="fa fa-twitter"></i></a>
                    <a href="https://www.youtube.com/"><i class="fa fa-youtube"></i></a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- product section end -->


<!-- RELATED PRODUCTS section -->
<section class="related-product-section">
    <div class="container">
        <div class="section-title">
            <h2>RELATED PRODUCTS</h2>
        </div>
        <div id="related-acc" class="row">

        </div>
    </div>
</section>
<!-- RELATED PRODUCTS section end -->
<%@include file="../guest/footer.jsp" %>
<script src="../js/product.js"></script>
<script>
                    $(function () {
                        getRelatedAccessory('<s:property value="%{dto.accCatID}"/>', '<s:property value="%{dto.accID}"/>');
                    });
</script>
</body>
</html>
