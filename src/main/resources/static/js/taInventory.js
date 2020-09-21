
layui.use(['element', 'form', 'table', 'layer', 'laydate'], function () {
    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;
    var form = layui.form;
    var formSelects = layui.formSelects;
    var laydate = layui.laydate;
    var fundId=$("#tableFundId").val();
    //执行一个laydate实例
    laydate.render({
        elem: '#dateTime' //指定元素
    });
    laydate.render({
        elem: '#dateTime1' //指定元素
    });
    laydate.render({
        elem: '#dateTime3' //指定元素
    });
    // 期初数据选中
    form.on('checkbox(initialSigns)', function(data){
        var addbtn = document.getElementById('addBtn');
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
        // alert("formData" + formData);
        $.post("../taInventory/insert",formData,function(msg){
            if(msg>0){
                table.reload('taInventoryTable');
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
        // alert("formData" + formData);
        $.post("../taInventory/update",formData,function(msg){
            if(msg>0){
                table.reload('taInventoryTable');
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
        elem: '#taInventoryTable',
        url: '../taInventory/select',
        page: true,
        height: 'full-55',
        where:{ 'fundId':fundId},
        toolbar: '#taInventoryToolBar',//显示在表头的工具条
        minLength:60,
        cols: [
            [ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'dateTime', title: '统计日期'}
                ,{field: 'taInventoryId', title: 'TA库存ID'}
                ,{field: 'taNum', title: '数量'}
                ,{field: 'taTotal', title: '金额'}
                ,{field: 'taInventoryDesc', title: '备注'}
                ,{field: 'classify', title: '操作', toolbar:'#barDemo',fixed: 'right'}
            ]
        ]
    });
    //给工具条的按钮添加事件
    table.on('toolbar(taInventoryTable)',function (obj) {
        //获取选中复选框的对象，
        var checkStatus=table.checkStatus(obj.config.id);//得到表格选中行的ID
        switch (obj.event) {
            case 'add':
                var index=layer.open({
                    type: 1,
                    title: '添加TA库存信息',
                    closeBtn: 1,
                    move:false,
                    area:['800px','600px'],
                    content:$("#addContent"),
                    btn:[]
                });

                form.render();
                //全屏
                // layer.full(index);
                break;
            case 'search':
                // alert("搜索");
                var dateTime= $("#dateTime").val();
                // alert(dateTime);
                //表格的重新加载事件
                table.reload('taInventoryTable', {
                    method: 'post'
                    , where: {
                        'dateTime': dateTime
                    }
                    , page: {
                        curr: 1
                    }
                });
                laydate.render({
                    elem: '#dateTime' //指定元素
                });
                $("#dateTime").val(dateTime);
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
                        ids.push(data[i].taInventoryId);
                    }
                    layer.confirm('真的删除行么',{icon: 2}, function(index){
                        layer.close(index);
                        $.post("../taInventory/delete", {taInventoryId:ids.join(',')},function(msg){
                            table.reload('taInventoryTable');
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
    table.on('tool(taInventoryTable)', function(obj) {
        var data = obj.data;//得到删除行整行的数据

        if (obj.event === 'del') {
            layer.confirm('真的删除行么',{icon: 2}, function(index){
                layer.close(index);
                $.post("../taInventory/delete", {taInventoryId:data.taInventoryId+""},function(msg){
                    table.reload('taInventoryTable');
                });

            });
        } else if (obj.event === 'edit') {
            // alert(JSON.stringify(data));

            form.val('editform',$.parseJSON(JSON.stringify(data)));
            var index = layer.open({
                type: 1,
                title: '修改TA库存信息',
                closeBtn: 1,
                move:false,
                area: ['800px', '600px'],
                content:$('#editContent')
            });
            form.render();
        };
    })


});
function myclose() {
    layer.closeAll();
}