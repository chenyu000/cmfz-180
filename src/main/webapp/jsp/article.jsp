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
            url: "${pageContext.request.contextPath}/article/queryArticleByPager",
            //editurl:"${pageContext.request.contextPath}/banner/bannerEdit",
            datatype: "json",
            styleUI: "Bootstrap",
            pager: "#bannerPager",               //分页
            rowNum: 5,                            //每页显示多少条
            rowList: [2, 4, 8],
            viewrecords: true,                   //是否显示总记录数
            autowidth: true,                     //自适应父容器
            multiselect: true,                   //是否多选
            height: 200,
            colNames: ["id", "title", "createDate", "status", "guruId", "content", "aaaa"],
            colModel: [
                {name: "id"},
                {name: "title"},
                {name: "createDate"},
                {
                    name: "status", editable: true, edittype: "select",
                    editoptions: {value: "展示:展示;不展示:不展示"}
                },
                {name: "guruId"},
                {name: "content", hidden: true},
                {
                    name: "aaaa", formatter: function (cellValue, options, rowObject) {

                        return "<button class='btn btn-success' onclick=\"showArticle('" + rowObject.id + "')\"><span class='glyphicon glyphicon-list'></span></button>";
                    }
                }
            ]
        }).jqGrid("navGrid", "#bannerPager", {search: false, add: false, edit: false, deltext: "删除"});
        /*初始化kindeditor*/
        editor = KindEditor.create("#addTextarea", {
            afterBlur: function () {
                this.sync()
            },
            afterChange: function () {
                this.sync()
            },
            width: '100%',
            height: '300px',
            resizeType: 1,
            uploadJson: '${pageContext.request.contextPath}/article/uploadImg',
            filePostName: 'img',
            allowFileManager: true,    //展示图片空间
            fileManagerJson: '${pageContext.request.contextPath}/article/getAllImgs'          //图片空间访问的路径

        });
    })

    /*
    *  添加莫泰黄
    * */
    function showAddMoadl() {
        /* 展示模态框*/
        $("#myaddModal").modal("show");
        $("#myFotter").empty();

        $("#myFotter").append("<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>" +
            "<button type=\"button\" class=\"btn btn-primary\" onclick=\"addSubmit();\">Save changes</button>")
    }

    function addSubmit() {
        $.ajax({
            url: "aaa",
            data: $("#addForm").serialize(),

        })
    }

    /*
    * 修改
    * */
    function showEditMoadl() {
        var id = jQuery("#bannerList").jqGrid('getGridParam', 'selrow');
        if (id != null) {
            $("#myaddModal").modal("show");
            var data = jQuery("#bannerList").jqGrid('getRowData', id);
            $("#title").val(data.title);
            $("#status").val(data.status);
            editor.html(data.content)
            $("#myFotter").empty();
            $("#myFotter").append("<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>" +
                "<button type=\"button\" class=\"btn btn-primary\" onclick=\"editSubmit();\">Save changes</button>")
        } else {
            alert("请选择要修改的数据")
        }
    }

    function editSubmit() {
        $.ajax({
            url: "bbb",
            data: $("#addForm").serialize(),
        })
    }

    /*
    *   查看详情
    * */
    function showArticle(id) {
        $("#showArticleModal").modal("show")
        var data = jQuery("#bannerList").jqGrid('getRowData', id);
        $("#myArticleContent").html(data.content);
    }
</script>
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active">
        <a href="#home" aria-controls="home" role="tab" data-toggle="tab">文章信息</a>
    </li>
    <li><a href="javascript:void(0)" onclick="showAddMoadl();">文章添加</a></li>
    <li><a href="javascript:void(0)" onclick="showEditMoadl();">文章修改</a></li>
</ul>
<div class="tab-content">
    <table id="bannerList"></table>
</div>
<div id="bannerPager" style="height: 50px"></div>

<!-- 模态框 -->
<div class="modal fade" id="myaddModal">
    <div class="modal-dialog">
        <%-- 内容 --%>
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">文章添加</h4>
            </div>
            <div class="modal-body">
                <form action="" class="form-horizontal" id="addForm">
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">
                            标题
                        </label>
                        <div class="col-sm-6">
                            <input type="text" id="title" name="title" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">
                            状态
                        </label>
                        <div class="col-sm-6">
                            <select name="status" class="form-control" id="status">
                                <option value="展示">展示</option>
                                <option value="不展示">不展示</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <textarea id="addTextarea" name="content">

                        </textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer" id="myFotter">

            </div>
        </div>
    </div>
</div>

<%--查看详情--%>
<div class="modal fade" id="showArticleModal">
    <div class="modal-dialog">
        <%-- 内容 --%>
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title">文章详情</h4>
            </div>
            <div class="modal-body">
                <div id="myArticleContent">

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
