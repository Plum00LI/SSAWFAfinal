layui.use(['form', 'table', 'layer', 'laydate'], function () {
    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;
    var laydate = layui.laydate;

    //执行一个实例
    table.render({
        elem: '#userTable',
        url: '../ClosingDateController/selectClosingDate',
        title: '成交清算数据报表',
        page: true, //开启分页
        toolbar: '#toolbarDemo',
        height: 'full-50',
        cellMinWidth: 60,
        cols: [
            [
                 {field: 'securitiesId', title: '证券代码', align: 'center'}
                , {field: 'securitiesName', title: '证券名称', align: 'center'}
                , {field: 'num', title: '交易数量', align: 'center',}
                , {field: 'netReceipts', title: '交易金额', align: 'center'}
                , {field: 'commission', title: '佣金费用', align: 'center'}
                , {field: 'stamp', title: '印花税', align: 'center'}
                , {field: 'brokerage', title: '经手费', align: 'center'}
                , {field: 'transfer', title: '过户费', align: 'center'}
                , {field: 'management', title: '征管费', align: 'center'}
                , {field: 'security', title: '国债利息', align: 'center'}
                , {field: 'flag', title: '交易标识', align: 'center', hide: true}
                , {field: 'securitiesType', title: '证券类型', align: 'center', hide: true}
                , {
                field: 'totalSum', title: '实际清算金额', align: 'center',
                templet: function (items) {
                    if (items.totalSum != null) {
                        if (items.flag == -1) {
                            return "<span style='color: red'>" + items.totalSum + "</span>"
                        }
                        if (items.securitiesId == '流出合计') {
                            return "<span style='color: red'>" + items.totalSum + "</span>"
                        }
                        if (items.totalSum < 0) {
                            return "<span style='color: red'>" + (-Number(items.totalSum)) + "</span>"
                        }
                        return items.totalSum
                    } else {
                        return 0;
                    }
                }
            }
                , {
                field: 'transactionDataMode', title: '业务类型', align: 'center',
                templet: function (item) {
                    if (item.transactionDataMode == 1 && item.securitiesType == 1) {
                        return '买入债券'
                    } else if (item.transactionDataMode == 2 && item.securitiesType == 1) {
                        return '卖出债券'
                    } else if (item.transactionDataMode == 3) {
                        return '分红'
                    } else if (item.transactionDataMode == 4) {
                        return '送股'
                    }
                    if (item.transactionDataMode == 0) {
                        return ''
                    }
                    if (item.transactionDataMode == 1 && item.securitiesType == 2) {
                        return '买入股票'
                    } else if (item.transactionDataMode == 2 && item.securitiesType == 2) {
                        return '卖出股票'
                    }
                }
            }
            ]
        ]

    });

    laydate.render({
        elem: '#dateTime', //指定元素
    });

    table.on('toolbar(userTable)', function (obj) {
        switch (obj.event) {
            case 'search':
                var dateTime = $("#dateTime").val();
                table.reload('userTable', {
                    method:'post',
                    page: {
                        curr: 1
                    },
                    where: {
                        dateTime: dateTime,
                    }
                });
                laydate.render({
                    elem: '#dateTime', //指定元素
                });
                $("#dateTime").val(dateTime);
                break;
        }
    })
});