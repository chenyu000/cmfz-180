<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%--1.bootstrap 核心 css--%>
    <link rel="stylesheet" href="${app}/boot/css/bootstrap.min.css">
    <%--2.jqgrid相关css--%>
    <link rel="stylesheet" href="${app}/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <%--3.jquery核心js--%>
    <script src="${app}/boot/js/jquery-2.2.1.min.js"></script>
    <%--4.bootstrap相关js--%>
    <script src="${app}/boot/js/bootstrap.min.js"></script>
    <%--5.jqgrid相关js--%>
    <script src="${app}/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${app}/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <%--上传--%>
    <script src="${app}/boot/js/ajaxfileupload.js"></script>
    <%--kindeditor相关--%>
    <script src="${app}/kindeditor/kindeditor-all-min.js"></script>
    <script src="${app}/kindeditor/lang/zh-CN.js"></script>
</head>
<body>
<%--导航条--%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><b>持明法洲后台管理系统</b></a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎:<b>小黑</b></a></li>
                <li><a href="#">退出登录 <span class="glyphicon glyphicon-log-out"></span></a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<%--布局 菜单栏->3 巨幕,轮播图->9--%>
<div class="container-fluid">
    <div class="row">
        <%--菜单栏--%>
        <div class="col-sm-3">
            <div class="panel-group" id="accordion">
                <%--用户相关--%>
                <div class="panel panel-default text-center">
                    <div class="panel-heading" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                <h4><b>用户管理</b></h4>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse">
                        <div class="panel-body">
                            <button class="btn btn-default btn-lg">
                                用户列表
                            </button>
                        </div>
                    </div>
                </div>
                <%--上师相关--%>
                <div class="panel panel-default text-center">
                    <div class="panel-heading" id="headingTwo">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                <h4><b>上师管理</b></h4>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <button class="btn btn-default btn-lg">
                                上师列表
                            </button>
                        </div>
                    </div>
                </div>
                <%--文章相关--%>
                <div class="panel panel-default text-center">
                    <div class="panel-heading" id="headingThree">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                                <h4><b>文章管理</b></h4>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <button class="btn btn-default btn-lg">
                                <a href="javascript:$('#content').load('article.jsp')">文章列表</a>
                            </button>
                        </div>
                    </div>
                </div>
                <%--专辑-章节--%>
                <div class="panel panel-default text-center">
                    <div class="panel-heading" id="headingT">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseT">
                                <h4><b>专辑管理</b></h4>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseT" class="panel-collapse collapse">
                        <div class="panel-body">
                            <button class="btn btn-default btn-lg">
                                <a href="javascript:$('#content').load('album.jsp')">专辑列表</a>
                            </button>
                        </div>
                    </div>
                </div>
                <%--轮播图--%>
                <div class="panel panel-default text-center">
                    <div class="panel-heading" id="headingTh">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTh">
                                <h4><b>轮播图管理</b></h4>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTh" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a class="btn btn-default btn-lg" href="javascript:$('#content').load('banner.jsp')">
                                轮播图列表
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--巨幕,轮播图--%>
        <div class="col-sm-9" id="content">
            <div class="jumbotron">
                <h3>欢迎来到持明法洲后台管理!</h3>
            </div>
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="${app}/img/shouye.jpg">
                    </div>
                    <div class="item">
                        <img src="${app}/img/A2.jpg">
                    </div>
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>
            </div>
        </div>
    </div>
</div>
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container text-center" style="padding-top: 10px">
        <h4>百知教育@180班</h4>
    </div>
</nav>
</body>
</html>