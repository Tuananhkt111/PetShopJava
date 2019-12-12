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
    <body>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>
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
                        <div class="ml-auto">
                            <div class="user-panel m-auto">
                                <div class="up-item">
                                    <i class="flaticon-profile"></i>
                                    <a href="<s:if test="%{#session.ROLE == 'admin'}">../admin/admin.jsp</s:if>" 
                                       style="cursor:pointer;"> <s:property value="%{#session.USER}"/></a>,
                                    <a href="logOutAction" style="cursor:pointer;"> Log out</a>
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
                        <li><a href="../admin/accessoryManagement.jsp">Accessory</a></li>
                        <li><a href="../admin/categoryManagement.jsp">Category</a></li>
                        <li><a href="../admin/accessoryTransactionManagement.jsp">Transaction</a></li>
                    </ul>
                </div>
            </nav>
        </header>
        <!-- Header section end -->