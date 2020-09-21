layui.use(['element', 'form', 'table', 'layer', 'laydate'], function () {
    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;
    var form = layui.form;
    var formSelects = layui.formSelects;
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#selectDate',//指定元素
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#end'//指定元素
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#up'//指定元素
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#date1'//指定元素
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#date2'//指定元素
    });
    //表格加载
    table.render({
        elem: '#userTable',
        url: '../bankTreasurer/selectBankTreasurer',
        page: true,
        toolbar: '#userToolBar',//显示在表头的工具条
        cellMinWidth:60,
        height:'full-55',
        cols: [
            [ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'bankTreasurerId', title: '资金调拨Id',align:'center'}
                ,{field: 'fundId', title: '基金Id',align:'center',hide:true}
                ,{field: 'accountId', title: '现金账户Id',align:'center',hide:true}
                ,{field: 'accountName', title: '现金账户名称',align:'center'}
                ,{field: 'allocatingType', title: '调拨类型',align:'center',
                templet:function (item) {
                    if (item.allocatingType==1){
                        return '存款利息';
                    }else if (item.allocatingType==2){
                        return '申购赎回清算款';
                    }else if (item.allocatingType==3){
                        return '买卖交易清算款';
                    }else if (item.allocatingType==4){
                        return '债券利息'
                    }else if (item.allocatingType==5){
                        return '存款业务'
                    }
                    return '两费'
                }
            }
                ,{field: 'flag', title: '调拨方向' ,align:'center',
                templet:function (item) {
                    if (item.flag==1){
                        return '流入';
                    }
                    return  '流出';
                }
            }
                ,{field: 'totalPrice', title: '调拨总数额',align:'center'}
                ,{field: 'dateTime', title: '业务日期',align:'center'}
                ,{field: 'dbTime', title: '调拨日期',align:'center'}
                ,{field: 'businessId', title: '业务标号',align:'center',hide:true}
                ,{field: 'bankTreasurerDesc', title: '备注',align:'center'}
                ,{field: 'right', title: '操作',width: 187, align:'center', toolbar: '#barDemo',fixed: 'right'}
            ]
        ]
    });
    //新增提交
    form.on('submit(addsubmit)', function(data){
        var formData=$('#addform').serialize();
        $.post("../bankTreasurer/insertBankTreasurer",formData,function(msg){
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
        $("#addform")[0].reset();
        return false;
    });
    //修改提交
    form.on('submit(editsubmit)', function(data){
        var formData=$('#editform').serialize();
        $.post("../bankTreasurer/updateBankTreasurer",formData,function(msg){
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
    //给工具条的按钮添加事件
    table.on('toolbar(userTable)',function (obj) {
        //获取选中复选框的对象，
        var checkStatus=table.checkStatus(obj.config.id);//得到表格选中行的ID
        switch (obj.event) {
            case 'add':
                var index=layer.open({
                    type: 1,
                    title: '添加资金调拨',
                    closeBtn: 1,
                    move:false,
                    content:$("#addContent"),
                    area:['700px','500px'],
                    btn:[]
                });
                form.render();
                //全屏
                //layer.full(index);
                break;
            case 'search':
                //alert("搜索");
                var allocatingType= $("#allocatingType").val();
                var flag= $("#flag").val();
                var dbTime= $("#selectDate").val();
                //表格的重新加载事件
                table.reload('userTable', {
                    method: 'post'
                    , where: {
                        'allocatingType': allocatingType,
                        'flag': flag,
                        'dbTime': dbTime
                    }
                    , page: {
                        curr: 1
                    }
                });
                laydate.render({
                    elem: '#selectDate',//指定元素
                });
                $("#allocatingType").val(allocatingType);
                $("#flag").val(flag);
                $("#selectDate").val(dbTime);
                break;
            case 'deleteAll':
                var data = checkStatus.data;
                //    layer.alert(JSON.stringify(data));
                if(data.length==0){
                    layer.msg("请至少选择一条数据",)
                }else
                {
                    var ids=[];
                    for (var i = 0; i <data.length; i++) {
                        ids.push(data[i].bankTreasurerId);
                    }
                    layer.confirm('真的删除行么',{icon: 2}, function(index){
                        layer.close(index);
                        $.post("../bankTreasurer/deleteBankTreasurer", {bankTreasurerId:ids.join(',')},function(msg){
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
        //alert(data.bankTreasurerId);
        if (obj.event === 'del') {
            layer.confirm('真的删除行么',{icon: 2}, function(index){
                layer.close(index);
                $.post("../bankTreasurer/deleteBankTreasurer", {bankTreasurerId:data.bankTreasurerId+""},function(msg){
                    table.reload('userTable');
                });

            });
        } else if (obj.event === 'edit') {
            //alert(JSON.stringify(data));

            form.val('editform',$.parseJSON(JSON.stringify(data)));
            var index = layer.open({
                type: 1,
                title: '修改资金调拨',
                closeBtn: 1,
                move:false,
                area:['700px','500px'],
                content:$('#editContent')
            });
            form.render();
            //layer.full(index);
        };
    })
    //修改得下拉表格

});
function myclose() {
    layer.closeAll();
}