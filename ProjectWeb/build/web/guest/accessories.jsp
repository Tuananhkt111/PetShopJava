<%@include file="../guest/header.jsp" %>
<!-- Page info -->
<div class="page-top-info">
    <div class="container">
        <h4>Accessories Page</h4>
        <div class="site-pagination">
            <a href="../guest/home.jsp">Home</a> /
            <a href="../guest/findAllAccAction.action">Accessories</a>
            <s:if test="%{accCatIDSearch != null}">
                / <a href="#">Accessory Category</a>
            </s:if>
            <s:if test="%{petCatSearch != null}">
                / <a href="#">Pet Category</a>
            </s:if>
        </div>
    </div>
</div>
<!-- Page info end -->
<!-- Category section -->
<section class="category-section spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 order-2 order-lg-1">
                <div class="filter-widget">
                    <img src="../img/18395.jpg"/>
                </div>
            </div>
            <div class="col-lg-9  order-1 order-lg-2 mb-5 mb-lg-0">
                <div class="row">
                    <s:iterator value="list">
                        <div class="col-lg-4 col-sm-6">
                            <div class="product-item animated fadeIn" style="margin: 0px 20px;">
                                <div class="pi-pic">
                                    <img class="imgSrc" src="../img/product/<s:if test="%{image == null}"><s:property value="noimage.jpg"/></s:if><s:else><s:property value="%{image}"/></s:else>" alt="Image not found">
                                        <div class="pi-links">
                                                <a class="add-card" style="cursor: pointer;" onclick="addToCart('<s:property value="%{accID}"/>')"><i class="flaticon-bag"></i><span>ADD TO CART</span></a>
                                        <a id="<s:property value="%{accID}"/>" class="wishlist-btn" style="cursor: pointer;" onclick="addFavorite('<s:property value="%{accID}"/>', this)"><i class="flaticon-heart"></i></a>
                                    </div>
                                </div>
                                <div class="pi-text">
                                    <h6>
                                        <s:if test="%{salePercent > 0}">
                                            <del style="color: gray;">$<s:property value="%{getText('{0,number,##0.00}',{price})}"/></del><br>$<s:property value="%{getText('{0,number,##0.00}',{price*(1 - salePercent)})}"/>
                                        </s:if>
                                        <s:else>
                                            $<s:property value="%{getText('{0,number,##0.00}',{price})}"/>
                                        </s:else>       
                                    </h6>
                                    <a href="findAccByIdAction.action?accIdSearch=<s:property value="%{accID}"/>"><s:property value="%{accName}"/></a>
                                </div>
                                <s:if test="%{salePercent > 0}">
                                    <div class="tag-sale" style="font-size: 20px;"><s:property value="%{getText('{0,number,##0}',{salePercent * 100})}"/>%</div>
                                </s:if>
                            </div>
                        </div>
                    </s:iterator>
                </div>    
            </div>
        </div>
    </div>
</section>
<!-- Category section end -->
<%@include file="../guest/footer.jsp" %>
<script>
    function addFavorite(accID, element) {
        if (roleValue === "user") {
            if (element.classList[0] === "wishlist-btn") {
                insertFavorite(accID, usernameValue, element);
            } else {
                deleteFavorite(accID, usernameValue, element);
            }
        } else if (roleValue === "admin") {
            alert("Your role ADMIN is not authorized");
        } else {
            $('#modalLRForm').modal('show');
        }
    }
    function addToCart(accID) {
        if (roleValue === "user") {
            addCart(accID);
        } else if (roleValue === "admin") {
            alert("Your role ADMIN is not authorized");
        } else {
            $('#modalLRForm').modal('show');
        }
    }

    $(function () {
        $('.imgSrc').on("error", function () {
            this.onerror = null;
            this.src = "../img/product/noimage.jpg";
        });
        $('.wishlist-btn').each(function () {
            if (checkFavoriteExisted(this.id)) {
                this.classList.add("wishlist-btn1");
                this.classList.remove("wishlist-btn");
            } else {
                this.classList.add("wishlist-btn");
                this.classList.remove("wishlist-btn1");
            }
        });
    });
</script>
</body>
</html>
