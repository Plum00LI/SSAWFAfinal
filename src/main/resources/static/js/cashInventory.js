
layui.use(['element', 'form', 'table', 'layer', 'laydate'], function () {
        var layer = layui.layer;
        var $ = layui.$;
        var table = layui.table;
        var form = layui.form;
        var formSelects = layui.formSelects;
        var laydate = layui.laydate;
        var fundId=$("#tableFundId").val();
        //执行一个laydate实例
        //时间下拉表
        laydate.render({
            elem: '#dateTime' //指定元素
        });
        laydate.render({
            elem: '#dateTime1' //指定元素
        });
        laydate.render({
            elem: '#dateTime2' //指定元素
        });
        //数据表格
        table.render({
            elem: '#cashInventoryTable',
            url: '../cashInventory/select',
            count:0,
            page: true,
            height: 'full-55',
            where:{ 'fundId':fundId},
            toolbar: '#cashInventoryToolBar',//显示在表头的工具条
            cols: [
                [ //表头
                    {type: 'checkbox', fixed: 'left'}
                    ,{field: 'dateTime', title: '统计时间'}
                    ,{field: 'cashInventoryId', title: '现金库存ID'}
                    ,{field: 'fundId', title: '基金ID'}
                    ,{field: 'accountId', title: '账户ID'}
                    ,{field: 'accountName', title: '账户名'}
                    ,{field: 'cashBlance', title: '现金余额'}
                    ,{field: 'right', title: '操作', toolbar:'#barDemo', fixed: 'right'}
                ]
            ]
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
        form.on('submit(addsubmit)', function(data){
            var formData=$('#addform').serialize();
            $.post("../cashInventory/insert",formData,function(msg){
                if(msg>0){
                    table.reload('cashInventoryTable');
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
            $.post("../cashInventory/update",formData,function(msg){
                if(msg>0){
                    table.reload('cashInventoryTable');
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
        table.on('toolbar(cashInventoryTable)',function (obj) {
            //获取选中复选框的对象，
            var checkStatus=table.checkStatus(obj.config.id);//得到表格选中行的ID
            switch (obj.event) {
                case 'add':
                    var index=layer.open({
                        type: 1,
                        title: '添加现金库存信息',
                        closeBtn: 1,
                        move:false,
                        area:['800px','600px'],
                        content:$("#addContent"),
                        btn:[]
                    });
                    form.render();
                    break;
                case 'search':
                    var accountId5= $("#searchAccountId").val();
                    var dateTime5= $("#dateTime").val();
                    // var fundId=$("#searchFundId").val();
                    //表格的重新加载事件
                    table.reload('cashInventoryTable',{
                        method: 'post'
                        , where: {
                            'accountId': accountId5,
                            'dateTime': dateTime5
                            // 'fundId':fundId
                        }
                        , page: {
                            curr: 1
                        }
                    });
                    laydate.render({
                        elem: '#dateTime' //指定元素
                    });
                    $("#searchAccountId").val(accountId5);
                    $("#dateTime").val(dateTime5);
                    break;
                case 'deleteAll':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    if(data.length==0){
                        layer.msg("请至少选择一条数据",)
                    }else
                    {
                        var ids=[];
                        for (var i = 0; i <data.length; i++) {
                            ids.push(data[i].cashInventoryId);
                        }
                        layer.confirm('真的删除行么',{icon: 2}, function(index){
                            layer.close(index);
                            $.post("../cashInventory/delete", {cashInventoryId:ids.join(',')},function(msg){
                                table.reload('cashInventoryTable');
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
        table.on('tool(cashInventoryTable)', function(obj) {
            var data = obj.data;//得到删除行整行的数据
            if (obj.event === 'del') {
                layer.confirm('真的删除行么',{icon: 2}, function(index){
                    layer.close(index);
                    $.post("../cashInventory/delete", {cashInventoryId:data.cashInventoryId+""},function(msg){
                        table.reload('cashInventoryTable');
                    });

                });
            } else if (obj.event === 'edit') {
                form.val('editform',$.parseJSON(JSON.stringify(data)));
                var index = layer.open({
                    type: 1,
                    title: '修改现金库存信息',
                    closeBtn: 1,
                    move:false,
                    area: ['800px', '600px'],
                    content:$('#editContent')
                });
                form.render();

            };
        })
    });
//取消按钮
function myclose() {
    layer.closeAll();
}


