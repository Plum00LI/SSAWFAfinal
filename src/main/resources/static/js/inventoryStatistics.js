layui.use(['element', 'form', 'table', 'layer', 'laydate'], function(){
    var layer = layui.layer;

    var $ = layui.$;
    var table = layui.table;
    var form = layui.form;
    var formSelects = layui.formSelects;
    var laydate = layui.laydate;

    laydate.render({
        elem:'#dateTime'
    });

    // //执行一个laydate实例
    // laydate.render({
    //     elem: '#dateTime' //指定日期
    // });

    //执行一个laydate实例
    laydate.render({
        elem: '#end' //指定元素
    });


    //表头工具条和标题
    table.render({
        elem: '#userTable',
        url: '../selectInventory',
        page: true,
        count:0,
        toolbar: '#userToolBar',//显示在表头的工具条
        cellMinWidth: 80,
        height: "full-55",
        cols: [
            [
                {type:'checkbox'},
                {
                    field: 'inventoryName',
                    align: 'center',
                    title: '库存名称',
                }, {
                field: 'fundId',
                title: '基金编号',
                align: 'center',
                sort: true

            }, {
                field: 'inventoryOperator',
                title: '操作员',
                align: 'center',

            },{
                field : 'inventoryDate',
                title : '统计日期',
                align: 'center',
                sort: true
            },{
                field: 'inventoryData',
                title: '已统计数据',
                align: 'center',
                sort: true
            },{
                field: 'inventoryStatis',
                title: '统计状态',
                align: 'center'}
            ]
        ]
    });


    //给工具条的按钮添加事件
    table.on('toolbar(userTable)',function (obj) {
        //获取选中复选框的对象，
        var checkStatus = table.checkStatus(obj.config.id);//得到表格选中行的ID
        switch (obj.event) {
            case 'search':
                //获得统计的库存id
                var data = checkStatus.data;
                //获得日期
                var dateTime = $("#dateTime").val();
                // //    layer.alert(JSON.stringify(data));
                var fundId=$("#fundId").val();
                if (data.length == 0) {
                    layer.msg("请至少选择一个库存进行统计",)
                } else {
                    var ids = [];
                    for (var i = 0; i < data.length; i++) {
                        ids.push(data[i].invId);
                    }
                }

                //执行一个laydate实例
                laydate.render({
                    elem: '#dateTime' //指定日期
                });
                table.reload('userTable', {
                        method: 'post'
                        , where: {
                            'dateTime': dateTime,
                            'invId': ids.join(','),
                            'fundId':fundId
                        }
                        , page: {
                            curr: 1
                        }
                    }
                );
                //执行一个laydate实例
                laydate.render({
                    elem: '#dateTime' //指定日期
                });
                break;
        }


    });
});
