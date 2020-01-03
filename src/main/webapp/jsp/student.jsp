<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="${pageContext.request.contextPath}/boot/js/jquery-2.2.1.min.js"></script>
    <script>
        $.ajax({
            url: "${pageContext.request.contextPath}/poi/showAllStudents",
            datatype: "json",
            success: function (data) {
                $.each(data, function (index, val) {

                    $("#myDiv").append(val.id + "--" + val.name + "--" + val.age + "<br/>")

                })
            }
        })
    </script>
</head>
<body>
<div id="myDiv"></div>
<form action="${pageContext.request.contextPath}/poi/poiOut">
    <input type="submit" value="poi导出">
</form>
<br/><br/><br/>
<form action="${pageContext.request.contextPath}/poi/poiIn" enctype="multipart/form-data" method="post">
    <div style="border: 1px solid rebeccapurple">
        <input type="file" name="myExcle">
        <input type="submit" value="确定">
    </div>
</form>
</body>
</html>