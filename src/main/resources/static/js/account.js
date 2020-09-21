layui.use(['element', 'form', 'table', 'layer', 'laydate'], function () {
    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;
    var form = layui.form;
    var formSelects = layui.formSelects;
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#addopen',//指定元素
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#addend'//指定元素
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#editopen',//指定元素
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#editend'//指定元素
    });
    <!--加载表格的内容-->
    table.render({
        elem: '#userTable',
        url: '../account/selectAccount',
        page: true,
        toolbar: '#userToolBar',//显示在表头的工具条
        cellMinWidth:60,
        height:'full-20',
        cols: [
            [ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'accountId', title: '现金账户Id',align:'center',hide:true}
                ,{field: 'fundId', title: '基金Id',align:'center',hide:true}
                ,{field: 'blankCardCode', title: '银行卡号',align:'center'}
                ,{field: 'accountName', title: '账户名称',align:'center'}
                ,{field: 'blankName', title: '银行名称',align:'center'}
                ,{field: 'deposit', title: '存款类型',align:'center',
                templet:function (item) {
                    if (item.deposit===1){
                        return '活期';
                    }
                    return  '定期';
                }
            }
                ,{field: 'cardRate', title: '卡号利率%',align:'center'}
                ,{field: 'procisionDays', title: '计息期间',align:'center',
                templet:function (item) {
                    if (item.procisionDays===1){
                        return '360天';
                    }else if (item.procisionDays===2){
                        return '365天';
                    }
                    return '366天'
                }
            }
                ,{field: 'openTime', title: '开户时间',align:'center'}
                ,{field: 'endTime', title: '结束时间',align:'center'}
                ,{field: 'accountDesc', title: '备注',align:'center'}
                ,{title: '操作',width: 187, align:'center', toolbar: '#barDemo',fixed: 'right'}
            ]
        ]
    });
    //新增提交
    form.on('submit(addsubmit)', function(data){
        var formData=$('#addform').serialize();
        $.post("../account/insertAccount",formData,function(msg){
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
        $.post("../account/updateAccount",formData,function(msg){
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
    //给工具条的按钮添加事件，增加删除功能
    table.on('toolbar(userTable)',function (obj) {
        //获取选中复选框的对象，
        var checkStatus=table.checkStatus(obj.config.id);//得到表格选中行的ID
        switch (obj.event) {
            case 'add':
                var index=layer.open({
                    type: 1,
                    title: '添加现金账户信息',
                    closeBtn: 1,
                    move:false,
                    content:$("#addContent"),
                    area:['800px','500px'],
                    btn:[]
                });
                form.render();
                //全屏
                //layer.full(index);
                break;
            case 'search':
                //alert("搜索");
                var accountName= $("#accountName").val();
                var blankName= $("#blankName").val();
                //表格的重新加载事件
                table.reload('userTable', {
                    method: 'post'
                    , where: {
                        'accountName': accountName,
                        'blankName': blankName
                    }
                    , page: {
                        curr: 1
                    }
                });
                $("#accountName").val(accountName);
                $("#blankName").val(blankName);
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
                        ids.push(data[i].accountId);
                    }
                    layer.confirm('真的删除行么',{icon: 2}, function(index){
                        layer.close(index);
                        $.post("../account/deleteAccount", {accountId:ids.join(',')},function(msg){
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
        //alert(data.accountId);
        if (obj.event === 'del') {
            layer.confirm('真的删除行么',{icon: 2}, function(index){
                layer.close(index);
                $.post("../account/deleteAccount", {accountId:data.accountId},function(msg){
                    table.reload('userTable');
                });

            });
        } else if (obj.event === 'edit') {
            //alert(JSON.stringify(data));

            form.val('editform',$.parseJSON(JSON.stringify(data)));
            var index = layer.open({
                type: 1,
                title: '修改账户信息',
                closeBtn: 1,
                move:false,
                area:['800px','500px'],
                content:$('#editContent')
            });
            form.render();
            //layer.full(index);
        };
    })
});
function myclose() {
    layer.closeAll();
}