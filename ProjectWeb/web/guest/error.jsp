<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pet Shop | Error</title>
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
    </head>
    <body>
        <div class="page-top-info">
            <div class="container">
                <h4>Home</h4>
                <div class="site-pagination">
                    <a href="../guest/home.jsp">Home</a> /
                    <a href="#">Error</a>
                </div>
            </div>
        </div>
        <h2 class="text-center mt-4" style="color: red;"><s:property value="%{#request.ERROR}"/><s:property value="%{#parameters.txtError}"/></h2>
    </body>
</html>
