<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Sprout Factory</title>
    <!-- load stylesheets -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400">  <!-- Google web font "Open Sans" -->
    <link rel="stylesheet" href="css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <link rel="stylesheet" href="css/magnific-popup.css">                                     <!-- Magnific pop up style, http://dimsemenov.com/plugins/magnific-popup/ -->
    <link rel="stylesheet" href="css/main-style.css">
    <link rel="stylesheet" href="css/sticky_header.css">

    <!-- js -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/classie.js"></script>
    <script>
        function init() {
            window.addEventListener('scroll', function(e) {
                var distanceY = window.pageYOffset || document.documentElement.scrollTop;
                var shrinkOn = 300;
                var header = document.querySelector("header");
                var headerLogo = document.querySelector(".header_logo");
                var headerLogoImg = document.querySelector("#header_logo_img");

                if (distanceY > shrinkOn) {
                    classie.add(header,"smaller");
                    classie.add(headerLogo,"header_logo_smaller");
                    classie.add(headerLogoImg,"header_logo_smaller");
                    classie.remove(headerLogoImg,"header_logo_larger");
                } else {
                    if (classie.has(header,"smaller")) {
                        classie.remove(header,"smaller");
                        classie.remove(headerLogo,"header_logo_smaller");
                        classie.remove(headerLogoImg,"header_logo_smaller");
                        classie.add(headerLogoImg,"header_logo_larger");
                    }
                }
            });
        }
        window.onload = init();
    </script>

    <style>
        input[type=text], select {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        select {
            height: 4em;
        }

        input[type=submit] {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        div#customerRegistration {
            border-radius: 5px;
            padding: 20px;
            width: 700px;

        }

        form {
            background-color: #f2f2f2;
            padding: 20px;
            width: 100%;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            background-color: #f2f2f2;
            min-width: 740px;
        }

        th {
            font-weight: bold;
            /*background-color: #CCC;*/
            border-bottom: 2px solid #cef;
        }

        th, td {
            text-align: left;
            padding: 16px;
        }

        tr:nth-child(even){background-color: #cef}

    </style>
</head>
<body>
<div id="wrapper">
<header id="nav">
    <div class="container clearfix">
        <div class="header_logo">
            <img id="header_logo_img" class="header_logo_larger" src="img/sprouts-icon.png" alt="Logo">
            <span class="tm-site-name header_text">Sprout Factory</span>
        </div>
        <div style="padding-right: 30px;">
            <nav>
                <a href="index.jsp?page=welcome" title="Home"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></a>
                <a href="index.jsp?page=customer" title="Customers"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
                <a href="index.jsp?page=suppliers" title="Suppliers"><span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span></a>
                <a href="index.jsp?page=recipes" title="Recipes"><span class="glyphicon glyphicon-cutlery" aria-hidden="true"></span></a>
                <a href="index.jsp?page=product" title="Product"><span class="glyphicon glyphicon-tree-conifer" aria-hidden="true"></span></a>
                <a href="index.jsp?page=employees" title="Employees"><span class="glyphicon glyphicon-pawn" aria-hidden="true"></span></a>
            </nav>
        </div>
    </div>
</header><!-- /header -->

    <c:if test="${not param.page}">
        <c:set var="page" value="welcome" scope="request"/>
    </c:if>
    <jsp:include page="${param.page}.jsp" />
    <%--<c:import url="${param.page}.jsp" />--%>
</div>

<!-- load JS files -->
<script src="js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
<script src="js/jquery.magnific-popup.min.js"></script>     <!-- Magnific pop-up (http://dimsemenov.com/plugins/magnific-popup/) -->
<script src="js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
<script>

    $(document).ready(function(){

        // Single page nav
        $('.tm-main-nav').singlePageNav({
            'currentClass' : "active",
            offset : 20
        });

        // Magnific pop up
        $('.tm-gallery-1').magnificPopup({
            delegate: 'a', // child items selector, by clicking on it popup will open
            type: 'image',
            gallery: {enabled:true}
            // other options
        });

        $('.tm-gallery-2').magnificPopup({
            delegate: 'a', // child items selector, by clicking on it popup will open
            type: 'image',
            gallery: {enabled:true}
            // other options
        });

        $('.tm-gallery-3').magnificPopup({
            delegate: 'a', // child items selector, by clicking on it popup will open
            type: 'image',
            gallery: {enabled:true}
            // other options
        });

        $('.tm-current-year').text(new Date().getFullYear());
    });
</script>
</body>
</html>