<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<style>
    .ui-jqgrid .ui-userdata {
        padding: 13px 94px;
        overflow: hidden;
        min-height: 32px;
    }

    .modal-body {
        position: relative;
        padding: 21px 130px;
    }

    #myH22 {
        margin-top: -42px;
        margin-bottom: 10px;
    }
</style>
<script>
    $(function () {
        $("#albumList").jqGrid({
            url: "${app}/album/queryAlbumByPager",
            styleUI: "Bootstrap",
            datatype: "json",
            autowidth: true,
            records: true,
            rowNum: 3,
            rowList: [3, 6, 9],
            height: 350,
            caption: "专辑",
            pager: "#albumPager",
            colNames: [
                "编号", "标题", "分数", "作者", "播音员", "数量", "简介", "日期", "状态", "图片"
            ],
            colModel: [
                {name: "id"},
                {name: "title"},
                {name: "score"},
                {name: "author"},
                {name: "broadcaster"},
                {name: "count"},
                {name: "brief"},
                {name: "createDate"},
                {name: "status"},
                {
                    name: "img",
                    formatter: function (cellValue) {
                        return "<img style=\"height: 60px;width: 100%\" src='${app}/img/" + cellValue + "'/>";
                    }
                },
            ],
            subGrid: true,         //开启子表格
            subGridRowExpanded: function (subGridId, albumId) {
                //添加子表格的方法
                addSubGrid(subGridId, albumId);

            }
        })
    })

    //添加子表格
    function addSubGrid(subGridId, albumId) {
        //动态table  id
        var subGridTableId = subGridId + "table";
        //动态div id
        var subGridDivId = subGridId + "div";
        //动态添加子表格
        $("#" + subGridId).html("<table id='" + subGridTableId + "'></table>" +
            "<div id='" + subGridDivId + "' style='height: 50px'></div>"
        )
        $("#" + subGridTableId).jqGrid({
            url: "${app}/chapter/queryChapterByPager?albumId=" + albumId,
            editurl: "${app}/chapter/edit?albumId=" + albumId,
            styleUI: "Bootstrap",
            datatype: "json",
            autowidth: true,
            records: true,
            rowNum: 3,
            caption: "章节",
            toolbar: [true, "top"],
            pager: "#" + subGridDivId,
            multiselect: true,
            rowList: [3, 6, 9],
            colNames: [
                "编号", "标题", "大小", "时长", "音频", "状态"
            ],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "size"},
                {name: "duration"},
                {name: "src", editable: true, edittype: 'file'},
                {
                    name: "status", editable: true, edittype: 'select',
                    editoptions: {value: "展示:展示;不展示:不展示"}
                },
            ]
        }).jqGrid("navGrid", "#" + subGridDivId, {},
            {
                //修改
                closeAfterEdit: true,
                afterSubmit: function (response) {
                    var chapterId = response.responseJSON.chapterId;
                    if (chapterId != null) {
                        $.ajaxFileUpload({
                            url: "${app}/chapter/uploadChapter",
                            fileElementId: "src",
                            type: "post",
                            data: {chapterId: chapterId},
                            success: function () {
                                $("#" + subGridTableId).trigger("reloadGrid");
                                alert("success")
                            }
                        })
                    }
                    return response;
                }
            },
            {
                //添加
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var chapterId = response.responseJSON.chapterId;
                    $.ajaxFileUpload({
                        url: "${app}/chapter/uploadChapter",
                        fileElementId: "src",
                        type: "post",
                        data: {chapterId: chapterId},
                        success: function () {
                            $("#" + subGridTableId).trigger("reloadGrid");
                            alert("success")
                        }
                    })
                    return response;
                }
            },
            {}
        )

        //添加按钮
        $("#t_" + subGridTableId).html("<button class='btn btn-danger' onclick=\"play('" + subGridTableId + "')\">播放 <span class='glyphicon glyphicon-play'></span></button>" +
            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
            "<button class='btn btn-danger' onclick=\"downLoad('" + subGridTableId + "')\">下载 <span class='glyphicon glyphicon-arrow-down'></span></button>"
        )
    }

    //播放
    function play(subGridTableId) {
        // 判断 用户是否选中一行  未选中->null         选中->被选中行的id
        var gr = $("#" + subGridTableId).jqGrid('getGridParam', 'selrow');
        if (gr == null) {
            alert("请选中要播放的音频");
        } else {
            //1.请求后台
            //2.jqgrid 提供的方法 根据id拿到对应的值
            var data = $("#" + subGridTableId).jqGrid('getRowData', gr);
            $('#myModal').modal('show');
            $("#myAudio").attr("src", "${app}/upload/audio/" + data.src);
        }
    }

    //下载  下载不能用 ajax
    function downLoad(subGridTableId) {
        // 判断 用户是否选中一行  未选中->null         选中->被选中行的id
        var gr = $("#" + subGridTableId).jqGrid('getGridParam', 'selrow');
        if (gr == null) {
            alert("请选中要下载的音频");
        } else {
            var data = $("#" + subGridTableId).jqGrid('getRowData', gr);
            var src = data.src;
            location.href = '${app}/chapter/downLoad?src=' + src;
        }
    }
</script>
<div class="page-header">
    <h2 id="myH22">专辑管理</h2>
</div>
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active">
        <a href="#home" aria-controls="home" role="tab" data-toggle="tab">专辑信息</a>
    </li>
</ul>
<div class="tab-content">
    <table id="albumList"></table>
</div>
<div id="albumPager" style="height: 50px"></div>

<div class="modal fade" tabindex="-1" role="dialog" id="myModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">播放器</h4>
            </div>
            <div class="modal-body">
                <audio autoplay controls src="" id="myAudio"></audio>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

