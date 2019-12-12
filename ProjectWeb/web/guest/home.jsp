<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../guest/header.jsp" %>
<!-- Hero section -->
<section class="hero-section">
    <div class="hero-slider owl-carousel">
        <div class="hs-item set-bg" data-setbg="../img/bgnew2.png">
            <div class="container">
                <div class="row">
                    <div class="col-xl-6 col-lg-7 text-white">
                        <span>Pet</span>
                        <h2>Accessories</h2>
                        <p> Good stuff that goes fast. Simple, functional, and thoughtfully designed for you and your pet(s). </p>
                        <a href="../guest/findAllAccAction.action" class="site-btn sb-line">DISCOVER</a>
                    </div>
                </div>
                <div class="offer-card text-white">
                    <span>on</span>
                    <h2 style="font-size: 55px;">SALE</h2>
                    <p>SHOP NOW</p>
                </div>
            </div>
        </div>
        <div class="hs-item set-bg" data-setbg="../img/bgnew.png">
            <div class="container">
                <div class="row">
                    <div class="col-xl-6 col-lg-7 text-white">
                        <span>PET</span>
                        <h2> Care Services</h2>
                        <p>Our trusted Pet care people can assure you the safety and happiness of your pets
                            whiles you take care of other things.</p>
                        <a href="../guest/services.jsp" class="site-btn sb-line">DISCOVER</a>
                    </div>
                </div>
                <div class="offer-card text-white">
                    <span>on</span>
                    <h2 style="font-size: 55px;">SALE</h2>
                    <p>BOOK NOW</p>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="slide-num-holder" id="snh-1"></div>
    </div>
</section>
<!-- Hero section end -->
<!-- Features section -->
<section class="features-section">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4 p-0 feature">
                <div class="feature-inner">
                    <div class="feature-icon">
                        <img src="../img/icons/1.png" alt="#">
                    </div>
                    <h2>Simple Payments</h2>
                </div>
            </div>
            <div class="col-md-4 p-0 feature">
                <div class="feature-inner">
                    <div class="feature-icon">
                        <img src="../img/icons/2.png" alt="#">
                    </div>
                    <h2>Premium Products</h2>
                </div>
            </div>
            <div class="col-md-4 p-0 feature">
                <div class="feature-inner">
                    <div class="feature-icon">
                        <img src="../img/icons/3.png" alt="#">
                    </div>
                    <h2>Free & fast Delivery</h2>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Features section end -->
<!-- latest product section -->
<section class="top-letest-product-section">
    <div class="container">
        <div class="section-title">
            <h2>LATEST ACCESSORIES</h2>
        </div>
        <div class="product-slider owl-carousel" id="list-latest"></div>
    </div>
</section>
<!-- latest product section end -->
<!-- Product filter section -->
<section class="product-filter-section">
    <div class="container">
        <div class="section-title">
            <h2>BROWSE TOP FAVORITE ACCESSORIES</h2>
        </div>
        <div class="row" id="most-favorite-list"></div>
    </div>
</section>
<!-- Product filter section end -->
<!-- Banner section -->
<section class="banner-section">
    <div class="container">
        <div class="banner set-bg" data-setbg="../img/banner-bg.jpg">
            <div class="tag-new">NEW</div>
            <div class="text-white">
                <span>PET</span>
                <h2 class="text-white">CARE SERVICES</h2>
            </div>
            <a href="../guest/services.jsp" class="site-btn">BOOK NOW</a>
        </div>
    </div>
</section>
<!-- Banner section end  -->
<%@include file="../guest/footer.jsp" %>
<script src="../js/home.js"></script>
</body>
</html>