layui.use(['element', 'form', 'table', 'layer', 'laydate','laypage'], function () {
    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;
    var form = layui.form;
    var laydate = layui.laydate;
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

    //期初数据选中
    form.on('checkbox(initialSigns)', function(data){
        var addbtn = document.getElementById('addbtn');
        if(data.elem.checked){
            addbtn.classList.remove("layui-btn-disabled")
            addbtn.setAttribute("lay-event","add")

        }else{
            addbtn.classList.add("layui-btn-disabled")
            addbtn.setAttribute("lay-event","")
        }
    });


    //新增提交
    form.on('submit(addsubmit)', function (data) {
        var formData = $('#addform').serialize();
        $.post("../securitiesInventory/insertSecuritiesInventory", formData, function (msg) {
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
        $.post("../securitiesInventory/updateSecuritiesInventory", formData, function (msg) {
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
        , url: '../securitiesInventory/selectSecuritiesInventoryInfo' //数据接口
        , title: '证券库存表'
        , page: true //开启分页
        , toolbar: '#toolbar1' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        , totalRow: true //开启合计行
        , height: 'full-50'
        , cellMinWidth: 60
        , cols: [
            [ //表头
                {type: 'checkbox'}
                , {field: 'securitiesInventoryId', title: '证券库存Id',hide:true, fixed: 'left', totalRowText: '合计：'}
                , {field: 'dateTime', title: '日期'}
                , {field: 'securitiesId', title: '证券代码'}
                , {field: 'securitiesName', title: '证券名称', sort: true}
                , {field: 'fundId', title: '基金代码',hide:true}
                , {field: 'securitiesNum', title: '数量'}
                , {field: 'price', title: '单位成本'}
                , {field: 'total', title: '总金额'}
                , {field: 'securitiesInventoryDesc', title: '备注'}
                , {fixed: 'right',title:'操作', minWidth: 165, align: 'center', toolbar: '#barDemo'}
            ]
        ]
    });
    //给工具条的按钮添加事件
    table.on('toolbar(userTable)', function (obj) {
        //获取选中复选框的对象，
        var checkStatus = table.checkStatus(obj.config.id);//得到表格选中行的ID
        var fundId=$("#fundId").val();
        switch (obj.event) {
            case 'add':
                var checked = $("#addCheck").prop("checked");
                if(checked == false) {
                    layer.msg("只能添加期初数据");
                    return false;
                }else{
                var index = layer.open({
                    type: 1,
                    title: '添加证券库存',
                    closeBtn: 1,
                    move: false,
                    content: $("#addContent"),
                    area:['800px','600px'],
                    btn: []
                });
                }
                form.render();

                break;
            case 'search':
                var dateTime = $("#dateTime").val();
                var securitiesName = $("#securitiesName").val();
                //表格的重新加载事件
                table.reload('userTable', {
                        where: {
                             'dateTime': dateTime,
                             'securitiesName':securitiesName
                    },
                        page: {
                              curr: 1
                    }
                });
                $("#dateTime").val(dateTime);
                $("#securitiesName").val(securitiesName);
                break;
            case 'deleteAll':
                var data = checkStatus.data;
                //    layer.alert(JSON.stringify(data));
                if (data.length == 0) {
                    layer.msg("请至少选择一条数据",)
                } else {
                    var ids = [];
                    for (var i = 0; i < data.length; i++) {
                        ids.push(data[i].securitiesInventoryId);
                    }
                    layer.confirm('真的删除行么', {icon: 2}, function (index) {
                        layer.close(index);
                        $.post("../securitiesInventory/deleteSecuritiesInventory", {securitiesInventoryId: ids.join(',')}, function (msg) {
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
                layer.full(index);
                break;
        }
    });
    //给表格编辑，删除按钮添加点击事件
    table.on('tool(userTable)', function (obj) {
        var data = obj.data;//得到删除行整行的数据
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', {icon: 2}, function (index) {
                layer.close(index);
                $.post("../securitiesInventory/deleteSecuritiesInventory", {securitiesInventoryId: data.securitiesInventoryId + ""}, function (msg) {
                    table.reload('userTable');
                });
            });
        } else if (obj.event === 'edit') {
            form.val('editform', $.parseJSON(JSON.stringify(data)));
            var index = layer.open({
                type: 1,
                title: '修改证券库存数据',
                closeBtn: 1,
                move: false,
                area:['800px','600px'],
                content: $('#editContent')
            });
            form.render();
        }
    });
});
function myclose() {
    layer.closeAll();
}