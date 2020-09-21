layui.use(['element', 'form', 'table', 'layer', 'laydate'], function () {
    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;
    var form = layui.form;
    var formSelects = layui.formSelects;
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#businessDate' //指定元素
    });

    laydate.render({
        elem: '#start2'
    })

    laydate.render({
        elem: '#start3'
    })

    //新增提交
    form.on('submit(addsubmit)', function(data){
        var formData=$('#addform').serialize();
        $.post("../insertCashClosedPayInventory",formData,function(msg){
            if(msg>0){
                table.reload('userTable');
                layer.closeAll();
                layer.msg('添加成功',{
                    title : '提示',
                    area : [ '200px',
                        '140px' ],
                    time : 0,
                    btn : [ '知道了' ]
                });
            }else{
                layer.closeAll();
                layer.msg('添加失败',{
                    title : '提示',
                    area : [ '200px',
                        '140px' ],
                    time : 0,
                    btn : [ '知道了' ]
                });
            }
        });
        return false;
    });


    //期初数据选中事件
    form.on('checkbox(initialSigns)', function(data){
        var addbtn = document.getElementById('addbtn');
        if(data.elem.checked){
            addbtn.classList.remove("layui-btn-disabled");
            addbtn.setAttribute("lay-event","add");
        }else{
            addbtn.classList.add("layui-btn-disabled");
            addbtn.setAttribute("lay-event","");
        }
    });

    //修改提交
    form.on('submit(editsubmit)', function(data){
        var formData=$('#editform').serialize();
        $.post("../updateCashClosedPayInventory",formData,function(msg){
            if(msg>0){
                table.reload('userTable');
                layer.closeAll();
                layer.msg('修改成功',{
                    title : '提示',
                    area : [ '200px',
                        '140px' ],
                    time : 0,
                    btn : [ '知道了' ]
                });
            }else{
                layer.closeAll();
                layer.msg('修改失败',{
                    title : '提示',
                    area : [ '200px',
                        '140px' ],
                    time : 0,
                    btn : [ '知道了' ]
                });
            }
        });
        return false;
    });
    table.render({
        elem: '#userTable',
        url: '../selectCashClosedPayInventory',
        page: true,
        toolbar: '#userToolBar',//显示在表头的工具条
        cellMinWidth: 50,
        height:'full-55',
        cols: [
            [ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'businessDate', title: '业务日期', align:'center'}
                ,{field: 'cashClosedPayInventoryId', title: '现金应收应付库存ID', align:'center', hide:true}
                ,{field: 'accountName', title: '现金账户名称', align:'center'}
                ,{field: 'fundId', title: '基金编号', align:'center', hide:true}
                ,{field: 'fundName', title: '基金名称', align:'center', hide:true}
                ,{field: 'businessType', title: '业务类型', align:'center',
                templet:function (item) {
                    if (item.businessType==1){
                        return '管理费';
                    }else if (item.businessType==2){
                        return '托管费';
                    }else if (item.businessType==3){
                        return '存款利息';
                    }else if (item.businessType==4){
                        return '申购赎回费';
                    }

                }
            }
                ,{field: 'businessStatus', title: '业务状态', align:'center',
                templet:function (item) {
                    if (item.businessStatus==1){
                        return '流入';
                    }
                    return '流出';
                }
            }
                ,{field: 'initialSigns', title: '期初标志', align:'center',
                templet:function (item) {
                    if (item.initialSigns==1){
                        return "<span style='color: green'>是</span>";
                    }
                    return "<span style='color: red'>否</span>";
                }
            }
                ,{field: 'totalMoney', title: '总金额', align:'center'}
                ,{title: 'operation', title: '操作', toolbar:'#barDemo', align:'center'}
            ]
        ]
    });
    //给工具条的按钮添加事件
    table.on('toolbar(userTable)',function (obj) {
        //获取选中复选框的对象，
        var checkStatus=table.checkStatus(obj.config.id);//得到表格选中行的ID
        switch (obj.event) {
            case 'add':
                var index=layer.open({
                    type: 1,
                    title: '添加现金应收应付库存信息',
                    closeBtn: 1,
                    move:false,
                    content:$("#addContent"),
                    area: ['800px', '600px'],
                    btn:[]
                });
                form.render();
                //全屏
                //layer.full(index);
                break;

            case 'search':
                var businessType= $("#businessType").val();
                var businessDate= $("#businessDate").val();
                //表格的重新加载事件
                table.reload('userTable', {
                    method: 'post'
                    , where: {
                        'businessType': businessType,
                        'businessDate': businessDate,
                    }
                    , page: {
                        curr: 1
                    }
                });
                laydate.render({
                    elem: '#businessDate' //指定元素
                });
                $("#businessType").val(businessType);
                $("#businessDate").val(businessDate);
                break;


            case 'deleteAll':
                var data = checkStatus.data;
                if(data.length==0){
                    layer.msg("请至少选择一条数据",)
                }else
                {
                    var ids=[];
                    for (var i = 0; i <data.length; i++) {
                        ids.push(data[i].cashClosedPayInventoryId);
                    }
                    layer.confirm('真的删除行么',{icon: 2}, function(index){
                        layer.close(index);
                        $.post("../deleteCashClosedPayInventory", {cashClosedPayInventoryId:ids.join(',')},function(msg){
                            table.reload('userTable');
                            layer.msg('删除'+checkStatus.data.length+'条记录', {
                                title:'提示',
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
    table.on('tool(userTable)', function(obj) {
        var data = obj.data;//得到删除行整行的数据
        alert(data.cashClosedPayInventoryId);
        if (obj.event === 'del') {
            layer.confirm('真的删除行么',{icon: 2}, function(index){
                layer.close(index);
                $.post("../deleteCashClosedPayInventory", {cashClosedPayInventoryId:data.cashClosedPayInventoryId+""},function(msg){
                    table.reload('userTable');
                });

            });
        } else if (obj.event === 'edit') {
            form.val('editform',$.parseJSON(JSON.stringify(data)));
            var index = layer.open({
                type: 1,
                title: '修改权益数据',
                closeBtn: 1,
                move:false,
                area: ['800px', '600px'],
                content:$('#editContent')
            });
            form.render();
            //layer.full(index);
        };
    })
});

//取消按钮关闭
function myclose() {
    layer.closeAll();
}


//下拉表格
layui.use(['tableSelect'], function () {
    var tableSelect = layui.tableSelect;
    var $ = layui.$;
    //新增账户名称下拉选择器
    tableSelect.render({
        elem: '#accountName',
        checkedKey: 'accountId',
        table: {
            url: '../account/selectAccount',
            cols: [
                [
                    {type: 'radio'},
                    {field: 'accountId', title: '账户编号', width:250},
                    {field: 'accountName', title: '账户名称', width:250}
                ]
            ]
        },
        done: function (elem, data) {
            var NEWJSON=[]
            layui.each(data.data, function (index, item) {
                NEWJSON.push(item.accountName)
                console.log(item.accountId)
                $("#accountId").val(item.accountId);
            })
            elem.val(NEWJSON.join(","))
        }
    })

    //修改账户名称下拉选择器
    tableSelect.render({
        elem: '#accountName2',
        checkedKey: 'accountId',
        table: {
            url: '../account/selectAccount',
            cols: [
                [
                    {type: 'radio'},
                    {field: 'accountId', title: '账户编号', width:250},
                    {field: 'accountName', title: '账户名称', width:250}
                ]
            ]
        },
        done: function (elem, data) {
            var NEWJSON=[]
            layui.each(data.data, function (index, item) {
                NEWJSON.push(item.accountName)
                console.log(item.accountId)
                $("#accountId2").val(item.accountId);
            })
            elem.val(NEWJSON.join(","))
        }
    })
})
