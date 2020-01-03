<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<c:set value="${pageContext.request.contextPath}" var="app"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="${app}/boot/js/jquery-2.2.1.min.js"></script>
    <script src="${app}/echarts/echarts.min.js"></script>
    <script src="${app}/echarts/china.js"></script>
    <script>
        var goEast = new GoEasy({
            host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "my_appkey", //替换为您的应用appkey
        });
        $(function () {

            /* 每月注册人数 */
            var month = echarts.init($("#month").get(0));
            var option = {
                title: {
                    text: '每月注册人数'
                },
                tooltip: {
                    trigger: 'axis'
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"]
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: '人数',
                        type: 'line',
                        stack: '人数',
                        data: [1, 3, 43]
                    }
                ]
            };
            month.setOption(option);
            $.ajax({
                url: "${app}/echarts/monthAll",
                type: "POST",
                datatype: "json",
                success: function (result) {
                    month.setOption({
                        series: [
                            {
                                data: result
                            }
                        ]
                    })
                }
            });
            goEast.subscribe({
                channel: "month",
                onMessage: function (message) {
                    console.log(message.content)
                    JSON.parse(message.content)
                    month.setOption({
                        series: [
                            {
                                data: JSON.parse(message.content)
                            }
                        ]
                    })
                }
            });


            /* 每月注册人数 */


        })
    </script>
</head>
<body>

<div id="month" style="width: 1000px; height: 400px"></div>

</body>
</html>