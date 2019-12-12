<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Pet Shop | Paradise for Pets</title>
        <meta charset="UTF-8">
        <meta name="description" content="Pet Shop | Paradise for Pets">
        <meta name="keywords" content="Pet Shop, eCommerce, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Favicon -->
        <link href="img/favicon.ico" rel="shortcut icon"/>
        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Josefin+Sans:300,300i,400,400i,700,700i" rel="stylesheet">
        <!-- Stylesheets -->
        <link rel="stylesheet" href="../css/bootstrap.min.css"/>
        <link rel="stylesheet" href="../css/font-awesome.min.css"/>
        <link rel="stylesheet" href="../css/flaticon.css"/>
        <link rel="stylesheet" href="../css/slicknav.min.css"/>
        <link rel="stylesheet" href="../css/jquery-ui.min.css"/>
        <link rel="stylesheet" href="../css/owl.carousel.min.css"/>
        <link rel="stylesheet" href="../css/animate.css"/>
        <link rel="stylesheet" href="../css/style.css"/>
        <style>
            .error {
                color: red;
                margin: 5px 0;
            }
        </style>
    </head>
    <body <s:if test="%{#session.ROLE == 'user'}">onload="countCart();"</s:if>>
            <!-- Page Preloder -->
            <div id="preloder">
                <div class="loader"></div>
            </div>
        <s:if test="#context['struts.actionMapping'].name=='registerAction'">
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog modal-dialog-centered">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <i class="fa fa-check-circle-o" style="color: greenyellow; font-size: 30px;"></i>
                            <h4 class="modal-title ml-2">Register successful</h4>
                            <button type="button" id="myBtn" class="close" data-dismiss="modal"><i class="fa fa-close"></i></button>
                        </div>
                        <div class="modal-body">
                            <p>System automatically log in your account.</p>
                        </div>
                    </div>
                </div>
            </div>
        </s:if>
        <!-- Header section -->
        <header class="header-section">
            <div class="header-top">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-2 text-center text-lg-left">
                            <!-- logo -->
                            <a href="../guest/home.jsp" class="site-logo">
                                <img src="../img/logonew.png" alt="">
                            </a>
                        </div>
                        <div class="col-xl-6 col-lg-5">
                            <form class="header-search-form" method="GET" action="findByLikeNameAction">
                                <input type="text" name="accSearch" placeholder="Search accessories...">
                                <button type="submit"><i class="flaticon-search"></i></button>
                            </form>
                        </div>
                        <div class="col-xl-4 col-lg-5">
                            <div class="user-panel">
                                <div class="up-item">
                                    <s:if test="%{#session.USER == null}">
                                        <i class="flaticon-profile"></i>
                                        <a data-toggle="modal" data-target="#modalLRForm"  style="cursor:pointer;">Sign In/Sign Up</a>
                                        <div class="modal fade" id="modalLRForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                            <div class="modal-dialog cascading-modal" role="document">
                                                <!--Content-->
                                                <div class="modal-content">
                                                    <!--Modal cascading tabs-->
                                                    <div class="modal-c-tabs">
                                                        <!-- Nav tabs -->
                                                        <ul class="nav nav-tabs tabs-2" role="tablist">
                                                            <li class="nav-item">
                                                                <a class="nav-link active" data-toggle="tab" href="#panel7" role="tab" style="font-size: 20px;"><i class="fa fa-sign-in"></i>Login</a>
                                                            </li>
                                                            <li class="nav-item">
                                                                <a class="nav-link" data-toggle="tab" href="#panel8" role="tab"  style="font-size: 20px;"><i class="fa fa-user-plus"></i>Register</a>
                                                            </li>
                                                        </ul>
                                                        <!-- Tab panels -->
                                                        <div class="tab-content">
                                                            <!--Panel 7-->
                                                            <div class="tab-pane fade in show active" id="panel7" role="tabpanel">
                                                                <!--Body-->
                                                                <form class="modal-body" id="form-login">
                                                                    <div class="md-form form-sm mb-3">
                                                                        <p class="error" id="login-result"></p>
                                                                        <i class="fa fa-user"></i>
                                                                        <label for="txtUsernameLg">Username</label>
                                                                        <input type="text" name="txtUsernameLg" id="txtUsernameLg" class="form-control form-control-sm" pattern="[A-Za-z0-9]{1,30}">
                                                                    </div>
                                                                    <div class="md-form form-sm mb-3">
                                                                        <i class="fa fa-lock"></i>
                                                                        <label for="txtPasswordLg">Password</label>
                                                                        <input type="password" name="txtPasswordLg" id="txtPasswordLg" class="form-control form-control-sm" pattern="(?=.*[A-Z])(?=.*[a-z])[A-Za-z0-9]{8,16}">
                                                                    </div>
                                                                    <div class="text-center mt-2">
                                                                        <button class="btn btn-success"> Log in </button>
                                                                    </div>
                                                                </form>
                                                            </div>
                                                            <!--/.Panel 7-->
                                                            <!--Panel 8-->
                                                            <div class="tab-pane fade" id="panel8" role="tabpanel">

                                                                <!--Body-->
                                                                <form class="modal-body" id="form-register" method="POST" action="registerAction">
                                                                    <div  class="md-form form-sm mb-3">
                                                                        <i class="fa fa-user"></i>
                                                                        <label for="txtUsernameRg">Username</label>
                                                                        <input type="text" name="txtUsernameRg" id="txtUsernameRg" class="form-control form-control-sm" 
                                                                               pattern="(\d|\w){1,30}" onkeyup="checkUsernameExisted()"/>
                                                                        <p class="error" id="txtUsernameRg-exist"></p>
                                                                    </div>
                                                                    <div  class="md-form form-sm mb-3">
                                                                        <i class="fa fa-lock"></i>
                                                                        <label for="txtPasswordRg">Password</label>
                                                                        <input type="password" name="txtPasswordRg" id="txtPasswordRg" class="form-control form-control-sm"
                                                                               pattern="(?=.*[A-Z])(?=.*[a-z])[A-Za-z0-9]{8,16}">
                                                                    </div>
                                                                    <div class="md-form form-sm mb-3">
                                                                        <i class="fa fa-lock"></i>
                                                                        <label for="txtRepPasswordRg">Repeat password</label>
                                                                        <input type="password" id="txtRepPasswordRg" name="txtRepPasswordRg" class="form-control form-control-sm"
                                                                               pattern="(?=.*[A-Z])(?=.*[a-z])[A-Za-z0-9]{8,16}">
                                                                    </div>
                                                                    <div class="md-form form-sm mb-3">
                                                                        <i class="fa fa-user-circle"></i>
                                                                        <label for="txtFullnameRg">Fullname</label>
                                                                        <input type="text" id="txtFullnameRg" name="txtFullnameRg" class="form-control form-control-sm"
                                                                               pattern=".{1,40}">
                                                                    </div>
                                                                    <div class="md-form form-sm mb-3">
                                                                        <i class="fa fa-phone"></i>
                                                                        <label for="txtPhoneRg">Phone</label>
                                                                        <input type="text" id="txtPhoneRg" name="txtPhoneRg" class="form-control form-control-sm"
                                                                               pattern="[0-9]{10,16}">
                                                                    </div>
                                                                    <div class="md-form form-sm mb-3">
                                                                        <i class="fa fa-address-book"></i>
                                                                        <label for="txtAddressRg">Address</label>
                                                                        <input type="text" id="txtAddressRg" name="txtAddressRg" class="form-control form-control-sm"
                                                                               pattern=".{1,70}">
                                                                    </div>
                                                                    <div class="text-center form-sm mt-2">
                                                                        <button type="submit" class="btn btn-success"> Sign up </button>
                                                                    </div>
                                                                </form>
                                                            </div>
                                                            <!--/.Panel 8-->
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--/.Content-->
                                            </div>
                                        </div>
                                        <!--Modal: Login / Register Form-->
                                    </s:if>
                                    <s:elseif test="%{#session.USER != null}">
                                        <i class="flaticon-profile"></i>
                                        <a href="<s:if test="%{#session.ROLE == 'user'}">../user/user.jsp</s:if>
                                           <s:elseif test="%{#session.ROLE == 'admin'}">../admin/admin.jsp</s:elseif>" 
                                           style="cursor:pointer;"> <s:property value="%{#session.USER}"/></a>,
                                        <a href="logOutAction" style="cursor:pointer;"> Log out</a>
                                    </s:elseif>
                                </div>
                                <div class="up-item">
                                    <div class="shopping-card">
                                        <i class="flaticon-bag"></i>
                                        <span id="cartCount">0</span>
                                    </div>
                                    <a href="#" id="cartLink" onclick="checkCartLink()">Shopping Cart</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <nav class="main-navbar">
                <div class="container">
                    <!-- menu -->
                    <ul class="main-menu">
                        <li><a href="../guest/home.jsp">Home</a></li>
                        <li><a href="../guest/findAllAccAction.action">Accessories</a>
                            <ul class="sub-menu" id="accessories-dropdown"></ul>
                        </li>
                        <li><a href="../guest/services.jsp">Services<span class="new">New</span></a></li>
                        <li><a href="../guest/about.jsp">About</a></li>
                        <li><a href="../guest/blogs.jsp">Blogs</a></li>
                        <li><a href="../guest/contact.jsp">Contact</a></li>
                    </ul>
                </div>
            </nav>
        </header>
        <!-- Header section end -->