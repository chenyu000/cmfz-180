<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="assets/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>Login Page - Now UI Kit by Creative Tim</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
          name='viewport'/>
    <!--     Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"/>
    <!-- CSS Files -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="assets/css/now-ui-kit.css?v=1.1.0" rel="stylesheet"/>
    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link href="assets/css/demo.css" rel="stylesheet"/>
    <!-- Canonical SEO -->
    <link rel="canonical" href=""/>
    <!--  Social tags      -->
    <meta name="keywords" content="">
    <meta name="description" content="">
</head>

<body class="login-page sidebar-collapse">
<!-- Navbar -->
<nav class="navbar navbar-expand-lg bg-primary fixed-top navbar-transparent " color-on-scroll="400">
    <div class="container">
        <div class="collapse navbar-collapse justify-content-end" data-nav-image="assets/img/blurred-image-1.jpg">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="#/issues" id="time"></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" rel="tooltip" title="Follow us on Twitter" data-placement="bottom"
                       href="#CreativeTim" target="_blank">
                        <i class="fa fa-twitter"></i>
                        <p class="d-lg-none d-xl-none">Twitter</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" rel="tooltip" title="Like us on Facebook" data-placement="bottom"
                       href="#CreativeTim" target="_blank">
                        <i class="fa fa-facebook-square"></i>
                        <p class="d-lg-none d-xl-none">Facebook</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" rel="tooltip" title="Follow us on Instagram" data-placement="bottom" href="#"
                       target="_blank">
                        <i class="fa fa-instagram"></i>
                        <p class="d-lg-none d-xl-none">Instagram</p>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!-- End Navbar -->
<div class="page-header" filter-color="orange">
    <div class="page-header-image" style="background-image:url(assets/img/login.jpg)"></div>
    <div class="container">
        <div class="col-md-4 content-center">
            <div class="card card-login card-plain">
                <form class="form" method="" action="" id="myForm">
                    <div class="header header-primary text-center">
                        <div class="logo-container">
                            <img src="assets/img/now-logo.png" alt="">
                        </div>
                    </div>
                    <div class="content">
                        <div class="input-group form-group-no-border input-lg">
                                <span class="input-group-addon">
                                    <i class="now-ui-icons users_circle-08"></i>
                                </span>
                            <input minlength="4" name="username" type="text" class="form-control" placeholder="用户名"
                                   required>
                        </div>
                        <div class="input-group form-group-no-border input-lg">
                                <span class="input-group-addon">
                                    <i class="now-ui-icons text_caps-small"></i>
                                </span>
                            <input type="password" minlength="6" name="password" placeholder="密码" class="form-control"
                                   required/>
                        </div>
                        <div class="input-group form-group-no-border input-lg">
                                <span class="input-group-addon">
                                    <img src="${pageContext.request.contextPath}/code/getCode" style="height: 50px"/>
                                </span>
                            <input name="code" type="text" placeholder="请输入验证码" class="form-control" required
                                   minlength="4"/>
                        </div>
                        <div id="myMessage">
                            <p style="color: red"></p>
                        </div>
                    </div>
                    <div class="footer text-center">
                        <a href="#pablo" id="submitButton" class="btn btn-primary btn-round btn-lg btn-block">Get
                            Started</a>
                    </div>
                    <div class="pull-left">
                        <h6>
                            <a href="#pablo" class="link">Create Account</a>
                        </h6>
                    </div>
                    <div class="pull-right">
                        <h6>
                            <a href="#pablo" class="link">Need Help?</a>
                        </h6>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <footer class="footer">
        <div class="container">
            <nav>
                <ul>
                    <li>
                        <a href="#">
                            Creative Tim
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            About Us
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            Blog
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            MIT License
                        </a>
                    </li>
                </ul>
            </nav>
            <div class="copyright">
                &copy;
                <script>
                    document.write(new Date().getFullYear())
                </script>
                , 百知教育 java180
            </div>
        </div>
    </footer>
</div>
</body>
<!--   Core JS Files   -->
<script src="assets/js/core/jquery.3.2.1.min.js" type="text/javascript"></script>
<script src="assets/js/core/popper.min.js" type="text/javascript"></script>
<script src="assets/js/core/bootstrap.min.js" type="text/javascript"></script>
<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
<script src="assets/js/plugins/bootstrap-switch.js"></script>
<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
<script src="assets/js/plugins/nouislider.min.js" type="text/javascript"></script>
<!--  Plugin for the DatePicker, full documentation here: https://github.com/uxsolutions/bootstrap-datepicker -->
<script src="assets/js/plugins/bootstrap-datepicker.js" type="text/javascript"></script>
<!-- Share Library etc -->
<script src="assets/js/plugins/jquery.sharrre.js" type="text/javascript"></script>
<!-- Control Center for Now Ui Kit: parallax effects, scripts for the example pages etc -->
<script src="assets/js/now-ui-kit.js?v=1.1.0" type="text/javascript"></script>
<%--
    jquery-validate.js
    支持中文
--%>
<script src="${pageContext.request.contextPath}/jquery-validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery-validate/messages_zh.min.js"></script>

<script>
    getTime = function () {
        var date = new Date();
        /* 年*/
        var year = date.getFullYear();
        /* 月 */
        var month = date.getMonth() + 1;
        /* 日 */
        var day = date.getDate();
        /* 时 */
        var hour = date.getHours();
        /* 分 */
        var minute = date.getMinutes();
        /* 秒 */
        var second = date.getSeconds();
        var time = year + "年" + month + "月" + day + " " + hour + ":" + minute + ":" + second;
        $("#time").html(time);
    }
    /* 1000毫秒调用一次getTime()方法*/
    setInterval("getTime()", 1000);

    $(function () {
        /* 自定义 验证*/
        $("#myForm").validate({
            /*自定义 输出的错误信息*/
            messages: {
                username: {
                    required: "请输入用户名",
                    minlength: "用户名最少4位"
                },
                password: {
                    required: "请输入密码",
                    minlength: "密码最少6位"
                },
                code: {
                    required: "请输入验证码",
                    minlength: "验证最少4位"
                }
            },
            /* 自定义错误信息输出位置*/
            errorPlacement: function (error, element) {
                /*error[0] 错误信息*/
                $("#myMessage p").html(error[0])
            }
        });
        $("#submitButton").click(function () {
            var valid = $("#myForm").valid();
            if (valid) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/admin/login",
                    datatype: "json",
                    data: $("#myForm").serialize(),
                    success: function (data) {
                        if (data.msg == null) {
                            location.href = "${pageContext.request.contextPath}/jsp/main.jsp"
                        } else {
                            $("#myMessage p").html(data.msg)
                        }
                    }
                })

            }
        })
    })


</script>

</html>