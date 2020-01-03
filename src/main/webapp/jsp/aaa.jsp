<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <!-- 引入 echarts.js -->
    <script src="../echarts/echarts.min.js"></script>
    <script src="../echarts/china.js"></script>
    <script>
        $(function () {
            // 基于准备好的dom，初始化echarts实例
            // 参数必须为 js
            var myChart = echarts.init($("#main")[0]);

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: 'ECharts 入门示例'
                },
                tooltip: {},
                legend: {
                    data: ['销量']
                },
                xAxis: {
                    data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
                },
                yAxis: {},
                series: [{
                    name: '销量',
                    type: 'bar'
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            //动态数据   必须连后台(ajax)
            $.ajax({
                url: "${pageContext.request.contextPath}/egetDatacharts/",
                datatype: "json",
                success: function (data) {
                    console.log(data);
                    myChart.setOption({
                        series: [{
                            data: data  // [1, 2, 3, 4, ...]
                        }]
                    })
                }
            })
        })
    </script>

</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>