
layui.use(['element', 'form', 'table', 'layer', 'laydate'], function () {
    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;
    var form = layui.form;
    var formSelects = layui.formSelects;
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#start',//指定元素
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#end'//指定元素
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#up'//指定元素
    });
    //表格加载
    table.render({
        elem: '#userTable',
        url: '../transferMoney/selectTransferMoney',
        page: true,
        toolbar: '#userToolBar',//显示在表头的工具条
        cellMinWidth:60,
        height:'full-55',
        cols: [
            [ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'transferMoneyId', title: '划款指令Id',align:'center',hide:true}
                ,{field: 'foundId', title: '基金Id',align:'center',hide:true}
                ,{field: 'outAccount', title: '划款账户Id',align:'center',hide:true}
                ,{field: 'outBlankName', title: '划款账户的银行名称',align:'center',hide:true}
                ,{field: 'inAccountId', title: '划款到的账户Id',align:'center',hide:true}
                ,{field: 'inBlankName', title: '划款到的账户的银行名称',align:'center',hide:true}
                ,{field: 'outAccountName', title: '划款账户',align:'center'}
                ,{field: 'inAccountName', title: '接收账户',align:'center'}
                ,{field: 'money', title: '划款金额',align:'center'}
                ,{field: 'crossSectionDate', title: '划款日期',align:'center'}
                ,{field: 'accountingDate', title: '到账日期',align:'center'}
                ,{field: 'purpose', title: '划款的用途',align:'center'}
                ,{field: 'right', title: '操作',width: 187, align:'center', toolbar: '#barDemo',fixed: 'right'}
            ]
        ]
    });
    //新增提交
    form.on('submit(addsubmit)', function(data){
        var formData=$('#addform').serialize();
        $.post("../transferMoney/insertTransferMoney",formData,function(msg){
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
    //给工具条的按钮添加事件
    table.on('toolbar(userTable)',function (obj) {
        //获取选中复选框的对象，
        var checkStatus=table.checkStatus(obj.config.id);//得到表格选中行的ID
        switch (obj.event) {
            case 'add':
                var index=layer.open({
                    type: 1,
                    title: '添加划款指令',
                    closeBtn: 1,
                    move:false,
                    content:$("#addContent"),
                    area:['700px','450px'],
                    btn:[]
                });
                form.render();
                //全屏
                //layer.full(index);
                break;
            case 'search':
                //alert("搜索");
                var crossSectionDate= $("#start").val();
                //表格的重新加载事件
                table.reload('userTable', {
                    method: 'post'
                    , where: {
                        'crossSectionDate': crossSectionDate
                    }
                    , page: {
                        curr: 1
                    }
                });
                laydate.render({
                    elem: '#start',//指定元素
                });
                $("#start").val(crossSectionDate);
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
                        ids.push(data[i].transferMoneyId);
                    }
                    layer.confirm('真的删除行么',{icon: 2}, function(index){
                        layer.close(index);
                        $.post("../transferMoney/deleteTransferMoney", {transferMoneyId:ids.join(',')},function(msg){
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
        //alert(data.transferMoneyId);
        if (obj.event === 'del') {
            layer.confirm('真的删除行么',{icon: 2}, function(index){
                layer.close(index);
                $.post("../transferMoney/deleteTransferMoney", {transferMoneyId:data.transferMoneyId+""},function(msg){
                    table.reload('userTable');
                });

            });
        } else if (obj.event === 'edit') {
            //alert(JSON.stringify(data));
            form.val('editform',$.parseJSON(JSON.stringify(data)));
            $('#BTitle').val('中国银行股份有限公司托管及投资则服务部:');
            $('#HTitle').val('敬请贵部根据以下提供的付/收款人名称、开户行、账号、到账日期、币种、和划款金额划款。');
            var index = layer.open({
                type: 1,
                title: '指令设置',
                closeBtn: 1,
                move:false,
                area:['700px','400px'],
                content:$('#editContent')
            });
            form.render();
        };
    })
    //生成指令excel
    form.on('submit(editsubmit)', function(data){
        //$('#outBlankName').val(data.outBlankName);
        var transferMoney=$('#editform').serialize();
        //alert(transferMoney);
        window.location.href="../transferOrderController/export?"+transferMoney;
        $('#editform')[0].reset();  //清空原有数据
        layer.closeAll();
        removeDate();
        return false;
    });

    form.on('select(title)', function(data){
        if($('#orderCheque').val()==1){
            $("#BTitleDiv").css("display","block");
            $("#HTitleDiv").css("display","block");
        }else{
            $("#BTitleDiv").css("display","none");
            $("#HTitleDiv").css("display","none");
        }
    });

});
function myclose() {
    layer.closeAll();
}


layui.use([ 'tableSelect'], function () {
    var $=layui.jquery,
        tableSelect=layui.tableSelect;
    //增加得下拉表格
    tableSelect.render({
        elem:'#insertAccount',
        checkedKey:'inAccountName',
        table:{
            url:'../account/selectAccount',
            cellMinWidth:60,
            cols:[
                [   {type:'radio'},
                    {field: 'accountId',title: '账户Id'},
                    {field:'accountName',title:'账户名称'},
                    {field: 'blankCardCode',title: '银行卡号'},
                    {field:'blankName',title:'银行名称'}
                ]
            ]
        },
        done:function (elem,data) {
            //elem:返回之前input对象；data:表格返回的选中的数据 []
            var newJson=[];
            //遍历选中的数据
            $.each(data.data,function (index,item) {
                newJson.push(item.accountName);
                $("#inAccountId").val(item.accountId);//给隐藏域中的val赋值
                $("#inBlankName").val(item.blankName);
            });
            elem.val(newJson.join(","));//给输入框里显示的值赋值

        }
    })
    //增加的第二个
    tableSelect.render({
        elem:'#insertAccount2',
        checkedKey:'outAccountName',
        table:{
            url:'../account/selectAccount',
            cellMinWidth:60,
            cols:[
                [   {type:'radio'},
                    {field: 'accountId',title: '账户Id'},
                    {field:'accountName',title:'账户名称'},
                    {field: 'blankCardCode',title: '银行卡号'},
                    {field:'blankName',title:'银行名称'}
                ]
            ]
        },
        done:function (elem,data) {
            //elem:返回之前input对象；data:表格返回的选中的数据 []
            var newJson=[];
            //遍历选中的数据
            $.each(data.data,function (index,item) {
                newJson.push(item.accountName);
                $("#outAccount").val(item.accountId);//给隐藏域中的val赋值
                $("#outBlankName").val(item.blankName);
            });
            elem.val(newJson.join(","));//给输入框里显示的值赋值

        }
    })
});