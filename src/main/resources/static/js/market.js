function myclose() {
    layer.closeAll();
}

layui.use(['element', 'form', 'table', 'layer', 'laydate','laypage','upload'], function () {
    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;
    var form = layui.form;
    var upload = layui.upload;
    var laydate = layui.laydate;
   // var tableSelect = layui.tableSelect;
    var laypage = layui.laypage;

    //执行一个laydate实例

    laydate.render({
        elem: '#endTime1'
    });

    laydate.render({
        elem: '#endTime2'
    });

    laydate.render({
        elem: '#endTime3'
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#dateTime' //指定元素
    });



    //新增提交
    form.on('submit(addsubmit)', function (data) {
            var formData = $('#addform').serialize();
            $.post("../market/insertMarket", formData, function (msg) {
                if (msg > 0) {
                    table.reload('userTable');
                    layer.closeAll();
                    layer.msg('添加成功', {
                        title: '提示',
                        area: ['200px',
                            '140px'],
                        time: 0,
                        btn: ['知道了']
                    });
                } else {
                    layer.closeAll();
                    layer.msg('添加失败', {
                        title: '提示',
                        area: ['200px',
                            '140px'],
                        time: 0,
                        btn: ['知道了']
                    });
                }
            });
        $("#addform")[0].reset();
            return false;
        });
    //修改提交
    form.on('submit(editsubmit)', function (data) {
        var formData = $('#editform').serialize();
        $.post("../market/updateMarket", formData, function (msg) {
            if (msg > 0) {
                table.reload('userTable');
                layer.closeAll();
                layer.msg('修改成功', {
                    title: '提示',
                    area: ['200px',
                        '140px'],
                    time: 0,
                    btn: ['知道了']
                });
            } else {
                layer.closeAll();
                layer.msg('修改失败', {
                    title: '提示',
                    area: ['200px',
                        '140px'],
                    time: 0,
                    btn: ['知道了']
                });
            }
        });
        return false;
    });
    //执行一个 table 实例
    table.render({
        elem: '#userTable'
        , url: '../market/selectMarketInfo' //数据接口
        , title: '行情数据表'
        , page: true //开启分页
        , toolbar: '#toolbar1' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        , height: 'full-50'
        , cellMinWidth: 60
        , cols: [
            [ //表头
                {type: 'checkbox',field:'left'}
                , {field: 'marketId', title: '行情ID', sort: true}
                , {field: 'securitiesId', title: '证券编号'}
                , {field: 'securitiesName', title: '证券名称'}
                , {field: 'dateTime', title: '日期', sort: true}
                , {field: 'openPrice', title: '开盘价格', sort: true, totalRow: true}
                , {field: 'closingPrice', title: '闭市价格'}
                , {field: 'marketDesc', title: '备注'}
                , {fixed: 'right', title:'操作',width: 187, align: 'center', toolbar: '#barDemo'}
            ]
        ]
    });
    //上传
    upload.render({
        elem: '#uploadDeepMarket'
        , url: 'https://httpbin.org/post' //改成您自己的上传接口
        , done: function (res) {
            layer.msg('上传成功');
            layui.$('#test9').removeClass('layui-hide').find('img').attr('src', res.files.file);
            console.log(res)
        }
    });
    //给工具条的按钮添加事件
    table.on('toolbar(userTable)', function (obj) {
        //获取选中复选框的对象，
        var checkStatus = table.checkStatus(obj.config.id);//得到表格选中行的ID
        switch (obj.event) {
            case 'add':
                var index = layer.open({
                    type: 1,
                    title: '添加行情数据',
                    closeBtn: 1,
                    move: false,
                    content: $("#addContent"),
                    area:['400px','500px'],
                    btn: []
                });
                form.render();
                /*//全屏
                layer.full(index);*/
                break;
            case 'search':
                var securitiesId = $("#securitiesId").val();
                var dateTime = $("#dateTime").val();
                //表格的重新加载事件
                table.reload('userTable', {
                    method:'post'
                    ,page: {
                        curr: 1
                    },
                    where: {
                        'securitiesId': securitiesId
                        ,'dateTime':dateTime
                    }
                });
                //执行一个laydate实例
                laydate.render({
                    elem: '#dateTime' //指定元素
                });
                $("#securitiesId").val(securitiesId);
                $("#dateTime").val(dateTime);
                break;
            case 'deleteAll':
                var data = checkStatus.data;
                //    layer.alert(JSON.stringify(data));
                if (data.length == 0) {
                   layer.msg("请至少选择一条数据",)
                } else {
                    var ids = [];
                    for (var i = 0; i < data.length; i++) {
                        ids.push(data[i].marketId);
                    }
                    layer.confirm('真的删除行么', {icon: 2}, function (index) {
                        layer.close(index);
                        $.post("../market/deleteMarket", {marketId: ids.join(',')}, function (msg) {
                            table.reload('userTable');
                            layer.msg('删除' + checkStatus.data.length + '条记录', {
                                title: '提示',
                                area: ['200px', '140px'],
                                time: 0,
                                btn: ['知道了']
                            });
                        });
                    });
                }
                break;
            case 'shangHai':
                var index = layer.open({
                    type: 1,
                    title: '导入上海行情',
                    closeBtn: 1,
                    move:false,
                    content:$('#uploadOnSeaMarket'),
                    area:['800px','600px'],
                    btn:[]
                });
               /* layer.full(index);*/
                break;
            case 'shenZhen':
                var index = layer.open({
                    type: 1,
                    title: '导入深圳行情',
                    closeBtn: 1,
                    move:false,
                    content:$('#uploadOnSeaMarket'),
                    area:['800px','600px'],
                    btn:[]
                });
               /* layer.full(index);*/
                break;
        }
    });
    //给表格编辑，删除按钮添加点击事件
    table.on('tool(userTable)', function (obj) {
        var data = obj.data;//得到删除行整行的数据
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', {icon: 2}, function (index) {
                layer.close(index);
                $.post("../market/deleteMarket", {marketId: data.marketId + ""}, function (msg) {
                    table.reload('userTable');
                });

            });
        } else if (obj.event == 'edit') {
            form.val('editform', $.parseJSON(JSON.stringify(data)));
            var index = layer.open({
                type: 1,
                title: '修改行情数据',
                closeBtn: 1,
                move: false,
                content: $('#editContent'),
                area:['400px','500px']
            });
            form.render();
           /* layer.full(index);*/
        }
    });
});

layui.use('tableSelect',function(){
    var tableSelect=layui.tableSelect;
    var $ = layui.$;
    tableSelect.render({
        elem: '#securities',
        checkedKey: 'securitiesId',
        table: {
            url: '../Securities/selectSecurities',
            cols: [
                [
                    { type: 'radio' },
                    { field: 'securitiesId', title: '证券编号', },
                    { field: 'securitiesName', title: '证券名称', },
                    { field: 'dateTime', title: '发行日期', }
                ]
            ]
        },
        done: function (elem, data) {
            var NEWJSON = []
            layui.each(data.data, function (index, item) {
                NEWJSON.push(item.securitiesName)
                console.log(item.securitiesId)
                $("#ss").val(item.securitiesId);
            })
            elem.val(NEWJSON.join(","))
        }
    });
    tableSelect.render({
        elem: '#securities2',
        checkedKey: 'securitiesId',
        table: {
            url: '../Securities/selectSecurities',
            cols: [
                [
                    { type: 'radio' },
                    { field: 'securitiesId', title: '证券编号', },
                    { field: 'securitiesName', title: '证券名称', },
                    { field: 'dateTime', title: '发行日期', }
                ]
            ]
        },
        done: function (elem, data) {
            var NEWJSON = []
            layui.each(data.data, function (index, item) {
                NEWJSON.push(item.securitiesId)
                console.log(item.securitiesId)
                $("#ss2").val(item.securitiesId);
            })
            elem.val(NEWJSON.join(","))
        }
    });
});
