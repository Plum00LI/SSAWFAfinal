layui.use(['element', 'form', 'table', 'layer'], function () {
    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;
    var form = layui.form;
    var formSelects = layui.formSelects;

    //新增提交
    form.on('submit(addsubmit)', function(data){
        var formData=$('#addform').serialize();
        $.post("../insertBrokers",formData,function(msg){
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
        alert(formData)
        $.post("../updateBrokers",formData,function(msg){
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

    //表格渲染
    table.render({
        elem: '#userTable',
        url: '../selectBrokers',
        page: true,
        toolbar: '#userToolBar',//显示在表头的工具条
        cellMinWidth: 50,
        height:'full-20',
        cols: [
            [ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'brokersId', title: '券商编号', align:'center'}
                ,{field: 'brokersName', title: '券商名称', align:'center'}
                ,{field: 'brokersInstructions', title: '券商说明', align:'center'}
                ,{field: 'brokersDesc', title: '券商备注', align:'center'}
                ,{fixed: 'right',title: 'operation', title: '操作' , toolbar:'#barDemo', align:'center'}
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
                    title: '添加券商信息',
                    closeBtn: 1,
                    move:false,
                    content:$("#addContent"),
                    area: ['400px', '450px'],
                    btn:[]
                });
                form.render();
                //全屏
                //layer.full(index);
                break;


            //搜索按钮的条件查询
            case 'search':
                var brokersName= $("#brokersName").val();
                //表格的重新加载事件
                table.reload('userTable', {
                    method: 'post'
                    , where: {
                        'brokersName': brokersName,
                    }
                    , page: {
                        curr: 1
                    }
                });
                $("#brokersName").val(brokersName);
                break;


            //批量删除
            case 'deleteAll':
                var data = checkStatus.data;
                if(data.length==0){
                    layer.msg("请至少选择一条数据",)
                }else
                {
                    var ids=[];
                    for (var i = 0; i <data.length; i++) {
                        ids.push(data[i].brokersId);
                    }
                    layer.confirm('真的删除行么',{icon: 2}, function(index){
                        layer.close(index);
                        $.post("../deleteBrokers", {brokersId:ids.join(',')},function(msg){
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
                $.post("../deleteBrokers", {brokersId:data.brokersId+""},function(msg){
                    table.reload('userTable');
                });

            });
        } else if (obj.event === 'edit') {
            form.val('editform',$.parseJSON(JSON.stringify(data)));
            var index = layer.open({
                type: 1,
                title: '修改券商信息',
                closeBtn: 1,
                move:false,
                area: ['400px', '450px'],
                content:$('#editContent')
            });
            form.render();
            //layer.full(index);
        };
    })
});


//取消按钮点击事件
function myclose() {
    layer.closeAll();
}


