layui.use(['element', 'form', 'table', 'layer', 'laydate'], function () {
    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;
    var form = layui.form;
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#start' //指定元素
    });
    laydate.render({
        elem: '#start1' //指定元素
    });
    //未结算表格数据
    table.render({
        elem: '#userTable',
        url: '../selectSettlement?status=0',
        page: true,
        height: 'full-50',
        minLength:80,
        toolbar:'#userToolBar',
        cols: [
            [ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'dateTime', title: '交易日期', width:150, align:'center'}
                ,{field: 'num', title: '交易数量', width: 150, align:'center'}
                ,{field: 'price', title: '交易单价', width:150, align:'center'}
                ,{field: 'totalSum', title: '结算金额', width: 150, align:'center'}
                ,{field: 'netReceipts', title: '交易金额', width:150, align:'center'}
                ,{field: 'settlementDate', title: '结算日期', width: 150, align:'center'}
                ,{field: 'accountName', title: ' 现金账户', width: 210, align:'center'}
                ,{field: 'securitiesName', title: ' 证券名称', width: 150, align:'center'}
                ,{field: 'brokersName', title: '券商名称', width: 150, align:'center'}
                ,{field: 'transactionDataMode', title: '交易方式', width: 150, align:'center', templet: function(item){
                    if(item.transactionDataMode=='1') {
                        return '买入';
                    } else if(item.transactionDataMode=='2'){
                        return '卖出';
                    } else if(item.transactionDataMode=='3'){
                        return '分红';
                    } else if(item.transactionDataMode=='4'){
                        return '送股';
                    }
                }
            }
                ,{fixed: 'right',field: 'status', title: '交易状态', width: 150, align:'center', templet: function(item){
                    if(item.status=='0') {
                        return '未结算';
                    } else if(item.status=='1'){
                        return '已结算';
                    }
                }
            }
            ]
        ]
    });


    //已结算表格数据
    table.render({
        elem: '#userTable2',
        url: '../selectSettlement?status=1',
        page: true,
        height: 'full-100',
        minLength:80,
        toolbar:'#userToolBar2',
        cols: [
            [ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'dateTime', title: '交易日期', width:150, align:'center'}
                ,{field: 'num', title: '交易数量', width: 150, align:'center'}
                ,{field: 'price', title: '交易单价', width:150, align:'center'}
                ,{field: 'totalSum', title: '结算金额', width: 150, align:'center'}
                ,{field: 'netReceipts', title: '交易金额', width:150, align:'center'}
                ,{field: 'settlementDate', title: '结算日期', width: 150, align:'center'}
                ,{field: 'accountName', title: ' 现金账户', width: 210, align:'center'}
                ,{field: 'securitiesName', title: ' 证券名称', width: 150, align:'center'}
                ,{field: 'brokersName', title: '券商名称', width: 150, align:'center'}
                ,{field: 'transactionDataMode', title: '交易方式', width: 150, align:'center', templet: function(item){
                    if(item.transactionDataMode=='1') {
                        return '买入';
                    } else if(item.transactionDataMode=='2'){
                        return '卖出';
                    } else if(item.transactionDataMode=='3'){
                        return '分红';
                    } else if(item.transactionDataMode=='4'){
                        return '送股';
                    }
                }
            }
                ,{fixed: 'right',field: 'status', title: '交易状态', width: 150, align:'center', templet: function(item){
                    if(item.status=='0') {
                        return '未结算';
                    } else if(item.status=='1'){
                        return '已结算';
                    }
                }
            }
            ]
        ]
    });
    //给工具条的结算添加事件
    table.on('toolbar(userTable)', function (obj) {

        switch (obj.event) {
            case "search":
                //获得选中的对象
                var checkStatus = table.checkStatus("userTable"); //idTest 即为基础参数 id 对应的值
                // 其中 ID 为基础参数 id 对应的值（见：设定容器唯一ID）

                var dataArr = checkStatus.data; //获取选中行的数据
                var settlement=JSON.stringify(dataArr);
                if (dataArr.length == 0) {
                    layer.msg("请先选中数据");
                }
                else {
                    layer.confirm('真的要结算嘛', function () {
                        // alert(settlement)
                        //向服务端发送结算指令
                        $.post("../updateSettlement","settlement="+settlement,function(msg){
                            if(msg>0){
                                table.reload('userTable');
                                table.reload('userTable2');  /!*渲染刷新*!/
                                layer.closeAll();
                                layer.msg('结算成功',{
                                    title : '提示',
                                    area : [ '200px',
                                        '140px' ],
                                    btn : [ '知道了' ]
                                });
                            }
                            else{
                                layer.closeAll();
                                layer.msg('结算失败',{
                                    title : '提示',
                                    area : [ '200px',
                                        '140px' ],
                                    btn : [ '知道了' ]
                                });
                            }
                        });
                        return false;
                    });
                }
                break;
            case 'select':
                // alert("搜索");
                var dateTime= $("#start").val();
                var transactionDataMode= $("#cateId").val();
                //表格的重新加载事件
                table.reload('userTable', {
                    method: 'post'
                    , where: {
                        'dateTime': dateTime,
                        'transactionDataMode': transactionDataMode
                    }
                    , page: {
                        curr: 1
                    }
                });
                laydate.render({
                    elem: '#start' //指定元素
                });
                break;
        }
    });
    //给工具条的反结算添加事件
    table.on('toolbar(userTable2)', function (obj) {
        switch (obj.event) {
            case "search2":
                //获得选中的对象
                var checkStatus = table.checkStatus("userTable2"); //idTest 即为基础参数 id 对应的值
                // 其中 ID 为基础参数 id 对应的值（见：设定容器唯一ID）
                var dataArr = checkStatus.data; //获取选中行的数据
                var settlement=JSON.stringify(dataArr);
                if (dataArr.length == 0) {
                    layer.msg("请先选中数据");
                }
                else {
                    layer.confirm('真的要反结算嘛', function () {
                        layer.close();
                        //向服务端发送删除指令
                        $.post("../updateSettlementTwo","settlement="+settlement,function(msg){
                            if(msg>0){
                                table.reload('userTable2');
                                table.reload('userTable');  /*渲染刷新*/
                                layer.closeAll();
                                layer.msg('反结算成功',{
                                    title : '提示',
                                    area : [ '200px',
                                        '140px' ],
                                    time : 0,
                                    btn : [ '知道了' ]
                                });
                            }
                            else{
                                layer.closeAll();
                                layer.msg('反结算失败',{
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
                }
                break;
            case 'select':
                var dateTime= $("#start1").val();
                var transactionDataMode= $("#cateId1").val();
                //表格的重新加载事件
                table.reload('userTable2', {
                    method: 'post'
                    , where: {
                        'dateTime': dateTime,
                        'transactionDataMode': transactionDataMode
                    }
                    , page: {
                        curr: 1
                    }
                });
                laydate.render({
                    elem: '#start1' //指定元素
                });
                $("#start1").val(dateTime);
                $("#cateId1").val(transactionDataMode);
                break;
        }
    });
});