layui.use(['element', 'form', 'table', 'layer', 'laydate','tableSelect'], function () {
    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;
    var form = layui.form;
    var formSelects = layui.formSelects;
    var tableSelect = layui.tableSelect;
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#start' //指定元素
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#start1' //指定元素
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#start2' //指定元素
    });

    //新增提交
    form.on('submit(addsubmit)', function(data){
        var formData=$('#addform').serialize();
        $.post("../insertTatTransaction",formData,function(msg){
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
    //修改提交
    form.on('submit(editsubmit)', function(data){
        var formData=$('#editform').serialize();
        $.post("../updateTaTransaction",formData,function(msg){
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
        url: '../selectTaTransaction',
        page: true,
        height: 'full-30',
        toolbar: '#userToolBar',//显示在表头的工具条
        minLength:80,
        cellMinWidth:60,
        cols: [
            [ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'taTransactionId', title: '交易数据编号', align:'center'}
                ,{field: 'fundId', title: '基金',  align:'center'}
                ,{field: 'accountId', title: '现金账户',  align:'center'}
                ,{field: 'dateTime', title: '交易日期',  align:'center'}
                ,{field: 'fundNum', title: '数量',  align:'center'}
                ,{field: 'balanceDate', title: '结算日期',  align:'center'}
                ,{field: 'totalMoney', title: '总金额',  align:'center'}
                ,{field: 'actualMoney', title: '实际金额',  align:'center'}
                ,{field: 'price', title: '单价',  align:'center'}
                ,{field: 'cost', title: '费用',  align:'center'}
                ,{field: 'agencies', title: '代销机构' ,  align:'center' ,templet:function (item) {
                    if (item.agencies=='1'){
                        return '建设银行';
                    }else if (item.agencies=='2'){
                        return '工商银行';
                    }else if (item.agencies=='3'){
                        return '农业银行';
                    }
                }}
                ,{field: 'transactionType',title:'类型' ,align :'center',templet:function (item) {
                    if (item.transactionType=='1'){
                        return '认购';
                    }else if (item.transactionType=='2'){
                        return '申购';
                    }else if (item.transactionType=='3'){
                        return '赎回';
                    }
                }}
                ,{field: 'transactionStatus',title:'状态' ,align :'center',templet:function (item) {
                    if(item.transactionStatus=='1'){
                        return "<span style='color: green'>已结算</span>";
                    }else  if(item.transactionStatus=='0'){
                        return "<span style='color: red'>未结算</span>";
                    }
                }}
                ,{fixed: 'right',field: 'right', title: '操作',width: 150, align:'center', toolbar: '#barDemo'}
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
                    title: '添加',
                    closeBtn: 1,
                    move:false,
                    content:$("#addContent"),
                    btn:[],
                    area:['800px','500px']
                });
                form.render();
                break;
            case 'search':
                var dateTime= $("#start").val();
                var transactionStatus= $("#transactionStatus").val();
                var transactionType=$("#transactionType").val();
                //表格的重新加载事件
                table.reload('userTable', {
                    method: 'post'
                    , where: {
                        'dateTime': dateTime,
                        'transactionStatus':transactionStatus,
                        'transactionType':transactionType
                    }
                    , page: {
                        curr: 1
                    }
                });

                break;
            case 'deleteAll':
                var data = checkStatus.data;

                if(data.length==0){
                    layer.msg("请至少选择一条数据",)
                }else
                {
                    var ids=[];
                    for (var i = 0; i <data.length; i++) {
                        ids.push(data[i].taTransactionId);
                    }
                    layer.confirm('真的删除行么',{icon: 2}, function(index){
                        layer.close(index);
                        $.post("../deleteTaTransaction", {taTransactionId:ids.join(',')},function(msg){
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
        if (obj.event === 'del') {

            layer.confirm('真的删除行么',{icon: 2}, function(index){
                layer.close(index);
                $.post("../deleteTaTransaction", {taTransactionId:data.taTransactionId+""},function(msg){
                    table.reload('userTable');
                });

            });
        } else if (obj.event === 'edit') {
            form.val('editform',$.parseJSON(JSON.stringify(data)));
            var index = layer.open({
                type: 1,
                title: '修改',
                closeBtn: 1,
                move:false,
                area:['800px','500px'],
                content:$('#editContent')
            });

            form.render();
        };
    })

    //给修改券商的下拉表格
    tableSelect.render({
        elem:'#updateAccount',
        checkedKey:'accountId',
        table:{
            url:'../account/selectAccount',
            cellMinWidth: 60,
            cols:[
                [   {type:'radio'},
                    {field:'accountId',title:'现金账户Id'},
                    {field: 'accountName',title:'现金账户名称'},
                ]
            ]
        },
        done:function (elem,data) {
            //elem:返回之前input对象；data:表格返回的选中的数据 []
            var newJson=[];
            //遍历选中的数据
            $.each(data.data,function (index,item) {
                newJson.push(item.accountId);
                $("#brokersId2").val(item.accountId);
            });
            elem.val(newJson.join(","));//给输入框里显示的值赋值

        }
    })
    //新增券商的下拉表格
    tableSelect.render({
        elem:'#insertAccount',
        checkedKey:'accountId',
        table:{
            url:'../account/selectAccount',
            cellMinWidth: 60,
            cols:[
                [   {type:'radio'},
                    {field:'accountId',title:'现金账户Id'},
                    {field: 'accountName',title:'现金账户名称'},
                ]
            ]
        },
        done:function (elem,data) {
            //elem:返回之前input对象；data:表格返回的选中的数据 []
            var newJson=[];
            //遍历选中的数据
            $.each(data.data,function (index,item) {
                newJson.push(item.accountId);
                $("#brokersId1").val(item.accountId);
            });
            elem.val(newJson.join(","));//给输入框里显示的值赋值
        }
    })
});

function quxiao() {
    layer.closeAll();
}

function autoEx() {
    layui.use(['jquery'],function () {
        var $=layui.jquery;
        var taNum = $("#taNum").val();
        var cost = $("#cost").val();
        var price=$("#price").val();

        $("#sum").val(taNum*price);
    })
}