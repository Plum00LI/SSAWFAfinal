layui.use(['element', 'form', 'table', 'layer', 'laydate'], function () {
    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;
    var form = layui.form;
    var formSelects = layui.formSelects;
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#end' //指定元素
    });

    //新增提交
    form.on('submit(addsubmit)', function (data) {
        var formData = $('#addform').serialize();
        var datas=table.cache["userTable"];
        //获取表格所有最新数据
        var add1 = document.getElementById("exchangeName_1").value;
        var add2 = document.getElementById("rateType_1").value;
        for (var i = 0; i < datas.length; i++) {
            if (datas[i].exchangeName == add1 && datas[i].rateType == add2) {
                layer.msg('该交易所品种费率已存在！')
                return false;
            }
        }
        $.post("/varietiesRate/insertVarietiesRate", formData, function (msg) {
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
        form.reset();
        return false;
    });
    //修改提交
    form.on('submit(editsubmit)', function (data) {
        var formData = $('#editform').serialize();
        $.post("/varietiesRate/updateVarietiesRate", formData, function (msg) {
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

    //查询
    table.render({
        elem: '#userTable',
        url: '/varietiesRate/selectVarietiesRate',
        page: true,
        height: 'full-20',
        toolbar: '#userToolBar',//显示在表头的工具条
        minLength: 80,
        cellMinWidth:60,
        cols: [
            [ //表头
                {type: 'checkbox', field: 'left'}
                , {field: 'exchangeName', title: '交易所名称', width: 175, align: 'center'
                ,templet: function(item) {
                    if (item.exchangeName == '2') {
                        return '深交所';
                    } else if (item.exchangeName == '1') {
                        return '上交所';
                    }
                }
            }
                , {field: 'rateType', title: '费率类型', width: 175, align: 'center'
                ,templet: function(item) {
                    if (item.rateType == '2') {
                        return '债券';
                    } else if (item.rateType == '1') {
                        return '股票';
                    }
                }}
                , {field: 'stampDuty', title: '印花税',  align: 'center'}
                , {field: 'transferFee', title: '过户费', align: 'center'}
                , {field: 'collectionRate', title: '征管费',  align: 'center'}
                , {field: 'brokerage', title: '经手费',  align: 'center'}
                , {fixed: 'right',title: '操作', minWidth: 100, toolbar: '#barDemo', align: "center"}
            ]
        ],
        limits: [10, 15, 20, 25, 50, 100],
        limit: 15,

    });

    //给工具条的按钮添加事件
    table.on('toolbar(userTable)', function (obj) {
        //获取选中复选框的对象，
        var checkStatus = table.checkStatus(obj.config.id);//得到表格选中行的ID
        switch (obj.event) {
            case 'add':
                var index = layer.open({
                    type: 1,
                    title: '添加交易所品种费率',
                    closeBtn: 1,
                    move: false,
                    content: $("#addContent"),
                    btn: [],
                    area:['400px','500px']
                });
                form.render();
                break;
            case 'search':
                var exchangeNameIds = $("#exchangeName_3").val();
                var rateTypeIds = $("#rateType_3").val();
                //表格的重新加载事件
                table.reload('userTable', {
                    method: 'post'
                    , where: {
                        'exchangeNameIds': exchangeNameIds,
                        'rateTypeIds':rateTypeIds
                    }
                    , page: {
                        curr: 1
                    }
                });
                $("#exchangeName_3").val(exchangeNameIds);
                $("#rateType_3").val(rateTypeIds);
                break;
            case 'deleteAll':
                var data = checkStatus.data;
                //    layer.alert(JSON.stringify(data));
                if (data.length == 0) {
                    layer.msg("请至少选择一条数据",)
                } else {
                    var exchangeNames = [];
                    for (var i = 0; i < data.length; i++) {
                        exchangeNames.push(data[i].exchangeName);
                    }
                    var rateTypes=[];
                    for (var i = 0; i < data.length; i++) {
                        rateTypes.push(data[i].rateType);
                    }
                    layer.confirm('真的删除行么', {icon: 2}, function (index) {
                        layer.close(index);
                        $.post("/varietiesRate/deleteVarietiesRate2", {exchangeName: exchangeNames.join(','),rateType: rateTypes.join(',')}, function (msg) {
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
        }
    });
    //给表格编辑，删除按钮添加点击事件
    table.on('tool(userTable)', function (obj) {
        var data = obj.data;//得到删除行整行的数据
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', {icon: 2}, function (index) {
                layer.close(index);
                $.post("../varietiesRate/deleteVarietiesRate",{exchangeName: data.exchangeName ,rateType:data.rateType}, function (msg) {
                    table.reload('userTable');
                });

            });
        } else if (obj.event === 'edit') {
            form.val('editform', $.parseJSON(JSON.stringify(data)));
            var index = layer.open({
                type: 1,
                title: '修改品种费率',
                closeBtn: 1,
                move: false,
                area: ['400px', '500px'],
                content: $('#editContent')
            });
            form.render();
        };
    })
});
function myclose() {
    layer.closeAll();
}