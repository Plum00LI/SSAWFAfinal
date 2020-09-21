layui.use(['element', 'form', 'table', 'layer', 'laydate'], function () {
    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;
    var form = layui.form;
    var formSelects = layui.formSelects;
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#selectEnd',//指定元素
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
        url: '../deposit/selectDeposit',
        page: true,
        toolbar: '#userToolBar',//显示在表头的工具条
        cellMinWidth:60,
        height:'full-55',
        cols: [
            [ //表头
                ,{field: 'depositId', title: '存款业务Id',align:'center',hide:true}
                ,{field: 'fundId', title: '基金Id',align:'center',hide:true}
                ,{field: 'outAccountId', title: '流出账户Id',align:'center',hide:true}
                ,{field: 'outAccountName', title: '流出账户名称',align:'center'}
                ,{field: 'inAccountId', title: '流入账户Id',align:'center',hide:true}
                ,{field: 'inAccountName', title: '流入账户名称',align:'center'}
                ,{field: 'money', title: '存款金额',align:'center'}
                ,{field: 'interest', title: '所含利息',align:'center',hide:true}
                ,{field: 'businessType', title: '业务类型',align:'center',
                templet:function (item) {
                    if (item.businessType==1){
                        return '定期三天';
                    }else if (item.businessType==2){
                        return '定期七天';
                    }
                    return  '活期存款';
                }

            }
                ,{field: 'directionOfMoney', title: '调拨方向',align:'center',hide:true,
                templet:function (item) {
                    if (item.directionOfMoney==1){
                        return '流入';
                    }
                    return  '流出';
                }
            }
                ,{field: 'businessDate', title: '业务时间',align:'center',hide:true}
                ,{field: 'endDate', title: '到期日期',align:'center'}
                ,{field: 'flag', title: '是否处理',align:'center',
                templet:function (item) {
                    if (item.flag==0){
                        return '未办理';
                    }
                    return  '已办理';
                }

            }
                ,{field: 'right', title: '操作',width: 180, align:'center', toolbar: '#barDemo',fixed: 'right'}
            ]
        ]
    });
    //新增提交
    form.on('submit(addsubmit)', function(data){
        var formData=$('#addform').serialize();
        $.post("../deposit/insertDeposit",formData,function(msg){
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
                    title: '添加存款',
                    closeBtn: 1,
                    move:false,
                    content:$("#addContent"),
                    area:['700px','530px'],
                    btn:[]
                });
                form.render();
                //全屏
                //layer.full(index);
                break;
            case 'search':
                //alert("搜索");
                var businessType= $("#businessType").val();
                var endDate= $("#selectEnd").val();
                //表格的重新加载事件
                table.reload('userTable', {
                    method: 'post'
                    , where: {
                        'businessType': businessType,
                        'endDate': endDate,
                    }
                    , page: {
                        curr: 1
                    }
                });
                laydate.render({
                    elem: '#selectEnd',//指定元素
                });
                $("#businessType").val(businessType);
                $("#selectEnd").val(endDate);
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
                        ids.push(data[i].depositId);
                    }
                    layer.confirm('真的删除行么',{icon: 2}, function(index){
                        layer.close(index);
                        $.post("../deposit/deleteDeposit", {depositId:ids.join(',')},function(msg){
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
        var endDateHaoMiao = new Date(data.endDate).getTime();//获得到期日期的时间
        var nowTimeHaoMiao = new Date().getTime();//获得当前日期的时间
        if (obj.event === 'del') {
  /*          layer.confirm('真的删除行么',{icon: 2}, function(index){
                layer.close(index);
                $.post("../deposit/deleteDeposit", {depositId:data.depositId+""},function(msg){
                    if(msg>0){
                        layer.msg('删除成功');
                    }else{
                        layer.msg('出现一个错误删除失败');
                    }
                    table.reload('userTable');
                });
                removeDate();
            });*/
            if (data.flag==1){
                layer.msg('已处理不可删除');
            }else if (data.flag==0){
                layer.confirm('真的删除行么',{icon: 2}, function(index){
                    layer.close(index);
                    $.post("../deposit/deleteDeposit", {depositId:data.depositId+""},function(msg){
                        if(msg>0){
                            layer.msg('删除成功');
                        }else{
                            layer.msg('出现一个错误删除失败');
                        }
                        table.reload('userTable');
                    });
                    removeDate();
                });
            }
        } else if (obj.event === 'edit') {
            if (data.flag==1){
                layer.msg('已处理不可重复操作');
            }else if (endDateHaoMiao>nowTimeHaoMiao && data.businessType!=3){
                layer.msg('存款未到期无法处理');
            }else{
                layer.confirm('确认对此存款进行到期处理?',{icon: 1}, function(index){
                    layer.close(index);
                    $.post("../deposit/updateDeposit",data
                        ,function(msg){
                            if(msg){
                                layer.msg('已处理');
                            }else{
                                layer.msg('出现一个错误处理失败');
                            }
                        });
                    table.reload('userTable');
                    removeDate();
                });
            }
        };
    })
    form.on('select(type)', function(data){
            if ($('#date1').val()!=""){
                if ($('#businessType2').val() == 1) {
                    day = 3;
                    setTimeout(function () {
                        var date1 = new Date($('#date1').val()).getTime();
                        date2 = date1 + 1000 * 60 * 60 * 24 * day;
                        var endDate = new Date(date2);
                        var endYear = endDate.getFullYear();
                        var endMonth = endDate.getMonth() + 1;
                        var endday = endDate.getDate();
                        var endTime = endYear+"-"+buling(endMonth)+"-"+buling(endday);
                        $('#date2').val(endTime);
                    }, 300);
                } else if ($('#businessType2').val() == 2) {
                    day = 7;
                    setTimeout(function () {
                        var date1 = new Date($('#date1').val()).getTime();
                        date2 = date1 + 1000 * 60 * 60 * 24 * day;
                        var endDate = new Date(date2);
                        var endYear = endDate.getFullYear();
                        var endMonth = endDate.getMonth() + 1;
                        var endday = endDate.getDate();
                        var endTime = endYear+"-"+buling(endMonth)+"-"+buling(endday);
                        $('#date2').val(endTime);
                    }, 300);
                }
            }
    });
});
function myclose() {
    layer.closeAll();
}
function myDate() {
    if ($('#businessType2').val() == 1) {
        day = 3;
        setTimeout(function () {
            var date1 = new Date($('#date1').val()).getTime();
            date2 = date1 + 1000 * 60 * 60 * 24 * day;
            var endDate = new Date(date2);
            var endYear = endDate.getFullYear();
            var endMonth = endDate.getMonth() + 1;
            var endday = endDate.getDate();
            var endTime = endYear+"-"+buling(endMonth)+"-"+buling(endday);
            $('#date2').val(endTime);
        }, 300);
    } else if ($('#businessType2').val() == 2) {
        day = 7;
        setTimeout(function () {
            var date1 = new Date($('#date1').val()).getTime();
            date2 = date1 + 1000 * 60 * 60 * 24 * day;
            var endDate = new Date(date2);
            var endYear = endDate.getFullYear();
            var endMonth = endDate.getMonth() + 1;
            var endday = endDate.getDate();
            var endTime = endYear+"-"+buling(endMonth)+"-"+buling(endday);
            $('#date2').val(endTime);
        }, 300);
    }
};
function buling(data) {
    if (data < 10) {
        return data = "0" + data;
    } else {
        return data;
    }
};

layui.use([ 'tableSelect', 'layer'], function () {
    var $ = layui.$;
    tableSelect=layui.tableSelect;
    //增加得下拉表格
    tableSelect.render({
        elem:'#insertAccount',
        checkedKey:'blankCardCode',
        table:{
            url:'../account/selectAccount',
            cellMinWidth:60,

            cols:[
                [   {type:'radio'},
                    {field: 'blankCardCode',title: '银行卡号',width:250},
                    {field:'accountName',title:'账户名称',width:250},
                    {field:'blankName',title:'银行名称',width:250}
                ]
            ]
        },
        done:function (elem,data) {
            //elem:返回之前input对象；data:表格返回的选中的数据 []
            var newJson=[];
            //遍历选中的数据
            $.each(data.data,function (index,item) {
                newJson.push(item.blankCardCode);
                $("#outAccountId").val(item.accountId);//给隐藏域中的val赋值
                $("#outAccountName").val(item.accountName);
                $("#outBlankName").val(item.blankName);
            });
            elem.val(newJson.join(","));//给输入框里显示的值赋值

        }
    })
    //增加的第二个
    tableSelect.render({
        elem:'#insertAccount2',
        checkedKey:'blankCardCode',
        table:{
            url:'../account/selectAccount',
            cellMinWidth:60,

            cols:[
                [   {type:'radio'},
                    {field: 'blankCardCode',title: '银行卡号',width:250},
                    {field:'accountName',title:'账户名称',width:250},
                    {field:'blankName',title:'银行名称',width:250}
                ]
            ]
        },
        done:function (elem,data) {
            //elem:返回之前input对象；data:表格返回的选中的数据 []
            var newJson=[];
            //遍历选中的数据
            $.each(data.data,function (index,item) {
                newJson.push(item.blankCardCode);
                $("#inAccountId").val(item.accountId);//给隐藏域中的val赋值
                $("#inAccountName").val(item.accountName);
                $("#inBlankName").val(item.blankName);
            });
            elem.val(newJson.join(","));//给输入框里显示的值赋值

        }
    })
});