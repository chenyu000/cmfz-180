<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<style>
    #myH2 {
        margin-top: -42px;
        margin-bottom: 10px;
    }
</style>
<script>
    $(function () {
        $("#bannerList").jqGrid({
            url: "${pageContext.request.contextPath}/banner/queryBannerByPager",
            editurl: "${pageContext.request.contextPath}/banner/bannerEdit",
            datatype: "json",
            styleUI: "Bootstrap",
            colNames: ["id", "title", "createDate", "status", "img"],
            pager: "#bannerPager",               //分页
            rowNum: 5,                            //每页显示多少条
            rowList: [2, 4, 8],
            viewrecords: true,                   //是否显示总记录数
            autowidth: true,                     //自适应父容器
            multiselect: true,                   //是否多选
            height: '400px',
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "createDate", editable: true, edittype: "date"},
                {
                    name: "status", editable: true, edittype: "select",
                    editoptions: {value: "展示:展示;不展示:不展示"}
                },
                {
                    name: "img", editable: true, edittype: "file",
                    formatter: function (cellValue) {
                        return "<img style='width:100%;height:100px' src='${pageContext.request.contextPath}/upload/img/" + cellValue + "'/>"
                    }
                }
            ]
        }).jqGrid("navGrid", "#bannerPager", {search: false, addtext: "添加", edittext: "修改", deltext: "删除"},
            {
                /*
                * 修改
                * */
                closeAfterEdit: true,
                afterSubmit: function (response) {
                    var msg = response.responseJSON.msg;
                    var bannerId = response.responseJSON.bannerId;
                    if (bannerId != null) {
                        $.ajaxFileUpload({
                            url: "${pageContext.request.contextPath}/banner/bannerUpload",
                            type: "post",
                            fileElementId: "img",
                            data: {bannerId: bannerId},
                            success: function () {
                                $("#bannerList").trigger("reloadGrid");
                                $("#msgDiv").html(msg).show();
                                setTimeout(function () {
                                    $("#msgDiv").hide();
                                }, 4000)
                            }
                        })
                    }
                    return response;
                }

            },
            {
                /*
                * 添加
                * */
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var msg = response.responseJSON.msg;
                    var bannerId = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/banner/bannerUpload",
                        type: "post",
                        fileElementId: "img",
                        data: {bannerId: bannerId},
                        success: function () {
                            $("#bannerList").trigger("reloadGrid");
                            $("#msgDiv").html(msg).show();
                            setTimeout(function () {
                                $("#msgDiv").hide();
                            }, 4000)
                        }
                    })
                    return response;
                }
            },
            {
                /*
                * 删除
                * */
                afterComplete: function (response) {
                    var msg = response.responseJSON.msg;
                    $("#bannerList").trigger("reloadGrid");
                    $("#msgDiv").html(msg).show();
                    setTimeout(function () {
                        $("#msgDiv").hide();
                    }, 4000)
                }
            }
        );
    })
</script>

<div class="page-header">
    <h2 id="myH2">轮播图管理</h2>
</div>

<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active">
        <a href="#home" aria-controls="home" role="tab" data-toggle="tab">轮播图信息</a>
    </li>
</ul>
<div class="tab-content">
    <table id="bannerList"></table>
</div>
<div id="bannerPager" style="height: 50px"></div>
<div class="alert alert-success" style="display:none" id="msgDiv">
    添加成功
</div>