layui.use(['element', 'form', 'table', 'layer', 'laydate'], function () {
    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;
    var form = layui.form;
    var formSelects = layui.formSelects;
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#end' //指定元素
    });

    laydate.render({
        elem: '#dateTime'
    })

    laydate.render({
        elem: '#dateTime'
    })

    laydate.render({
        elem: '#dateTime1'
    })

    laydate.render({
        elem: '#settlementDate'
    })
    laydate.render({
        elem: '#settlementDate1'
    })


    //新增提交
    form.on('submit(addsubmit)', function(data){
        var formData=$('#addform').serialize();
        $.post("../insertTransactionData",formData,function(msg){
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
        $.post("../updateTransactionData",formData,function(msg){
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
        url: '../selectTransactionData',
        page: true,
        height: 'full-55',
        toolbar: '#userToolBar',//显示在表头的工具条
        minLength: 80,
        cellMinWidth:60,
        cols: [
            [ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'transactionDataId', title: '交易单号',width:130,align:'center',hide:true}
                ,{field: 'dateTime', title: '交易日期',  width:130, align:'center'}
                ,{field: 'num', title: '交易数量',  width:130, align:'center'}
                ,{field: 'price', title: '交易单价',  width:130,align:'center'}
                ,{field: 'totalSum', title: '结算金额',  width:130, align:'center'}
                ,{field: 'netReceipts', title: '交易金额',  width:130,align:'center'}
                ,{field: 'settlementDate', title: '结算日期', width:130, align:'center'}
                ,{field: 'accountName', title: ' 现金账户', width:200, align:'center'}
                ,{field: 'securitiesName', title: ' 证券名称', width:200, align:'center'}
                ,{field: 'brokersName', title: '券商名称', width:130,  align:'center'}
                ,{field: 'fundId', title: '基金代码', width:130,  align:'center',hide:true}
                ,{field: 'fundName', title: '基金名称',  width:130, align:'center',hide:true}
                ,{field: 'securitiesId', title: '证券ID',  width:130, align:'center',hide:true}
                ,{field: 'brokersId', title: '券商ID',  width:130, align:'center',hide:true}
                ,{field: 'brokersName', title: '券商名称',  width:130, align:'center',hide:true}
                ,{field: 'seateId', title: '席位ID',  width:130, align:'center',hide:true}
                ,{field: 'seateName', title: '席位名称',  width:130, align:'center',hide:true}
                ,{field: 'accountId', title: '现金账户ID',  width:130, align:'center',hide:true}
                ,{field: 'blankName', title: '银行名称',  width:130, align:'center',hide:true}
                ,{field: 'flag', title: '交易方向 1流入,-1流出',  width:130, align:'center',hide:true}
                ,{field: 'commission', title: '佣金费用',  width:130,align:'center',hide:true}
                ,{field: 'transfer', title: '过户费',  width:130, align:'center',hide:true}
                ,{field: 'brokerage', title: '经手费',  width:130, align:'center',hide:true}
                ,{field: 'stamp', title: '印花税',  width:130, align:'center',hide:true}
                ,{field: 'management', title: '征管费', width:130, align:'center',hide:true}
                ,{field: 'security', title: '证券利息',  width:130,align:'center',hide:true}
                ,{field: 'transactionDataDesc', title: '备注',  width:130,align:'center',hide:true}
                ,{field: 'transactionDataMode', title: '交易方式', width:130, align:'center', templet: function(item){
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
                ,{field: 'status', title: '交易状态', width:130, align:'center', templet: function(item){
                    if(item.status=='0') {
                        return '未结算';
                    } else if(item.status=='1'){
                        return '已结算';
                    }
                }
            }
                ,{fixed: 'right',field: 'operation', title: '操作' ,  width:160, align:'center',toolbar:'#barDemo'}
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
                    title: '添加交易数据信息',
                    closeBtn: 1,
                    move:false,
                    area: ['800px','720px'],
                    content:$("#addContent"),
                    btn:[]
                });
                $("#addform")[0].reset();
                form.render();
                //全屏弹窗 layer.full(index);
                break;

            case 'search':
                var dateTime= $("#end").val();
                var securitiesName= $("#equityId").val();
                //表格的重新加载事件
                table.reload('userTable', {
                    method: 'post'
                    , where: {
                        'dateTime': dateTime,
                        'securitiesName': securitiesName
                    }
                    , page: {
                        curr: 1
                    }
                });
                laydate.render({
                    elem: '#end' //指定元素
                });
                $("#end").val(dateTime);
                $("#equityId").val(securitiesName);
                break;
            case 'deleteAll':
                var data = checkStatus.data;
                if(data.length==0){
                    layer.msg("请至少选择一条数据",)
                }else {
                    var ids=[];
                    for (var i = 0; i <data.length; i++) {
                        ids.push(data[i].transactionDataId+'-'+data[i].status);
                    }
                        layer.confirm('真的删除行么',{icon: 2}, function(index){
                            layer.close(index);
                            $.post("../deleteTransactionData", {transactionData:ids.join(',')},function(obj){
                                table.reload('userTable');
                                if (obj.code==0){
                                    layer.msg('删除'+checkStatus.data.length+'条记录', {
                                        title:'提示',
                                        area: ['200px', '140px'],
                                        time: 0,
                                        btn: ['知道了']
                                    });
                                }else {
                                    layer.msg(obj.msg);
                                }
                            });
                        });
                }
                break;
        }
    });
    //给表格编辑，删除按钮添加点击事件
    table.on('tool(userTable)', function(obj) {
        var data = obj.data;//得到行整行的数据
        // alert(data.transactionDataId);
        if (obj.event === 'del') {
           var status= data.status;
           // alert(status);
           if(status==0){
               layer.confirm('真的删除行么',{icon: 2}, function(index){
                   layer.close(index);
                   $.post("../deleteTransactionData", {transactionDataId:data.transactionDataId+""},function(obj){
                       table.reload('userTable');
                       if (obj.code==0){
                           layer.msg('删除成功', {
                               title:'提示',
                               area: ['200px', '140px'],
                               time: 0,
                               btn: ['知道了']
                           });
                       }else {
                           layer.msg(obj.msg);
                       }
                   });

               });
           }else {
               layer.confirm('不能删除已结算数据');
           }

        } else if (obj.event === 'edit') {
            // alert(JSON.stringify(data));
            form.val('editform',$.parseJSON(JSON.stringify(data)));
            var index = layer.open({
                type: 1,
                title: '修改交易数据',
                closeBtn: 1,
                move:false,
                area: ['800px','700px'],
                content:$('#editContent')
            });
            form.render();
            //全屏弹窗 layer.full(index);
        }
    })
});

//取消事件
function myclose() {
    layer.closeAll();
}


layui.use(['table', 'form', 'tableSelect'], function () {
    var $ = layui.jquery,
        table = layui.table,
        form = layui.form,
        tableSelect = layui.tableSelect;

    //新增证券下拉表单
    tableSelect.render({
        elem: '#securitiesName'
        , checkedKey: 'securitiesName'
        , table: {
            url: '../Securities/selectSecurities'
            , cellMinWidth: 60
            , cols: [
                [
                    {type: 'checkbox'},
                    {field: 'securitiesId', title: '证券id'},
                    {field: 'securitiesName', title: '证券名称'},
                    {
                        field: 'securitiesType', title: '证券类型', templet: function (item) {
                            if (item.securitiesType == '1') {
                                return '债券';
                            } else if (item.securitiesType == '2') {
                                return '股票';
                            }
                        }
                    },
                    {
                        field: 'securitiesType', title: '证券地址', templet: function (item) {
                            if (item.securitiesType == '1') {
                                return '上交所';
                            } else if (item.securitiesType == '2') {
                                return '深交所';
                            }
                        }
                    }
                ]
            ]
        }
        , done: function (elem, data) {
            var NEWJSON = [];
            layui.each(data.data, function (index, item) {
                NEWJSON.push(item.securitiesName);
                $("#securitiesId").val(item.securitiesId);
            })
            elem.val(NEWJSON.join(","));

        }
    });

    //新增现金账户下拉表单
    tableSelect.render({
        elem: '#accountName'
        , checkedKey: 'accountName'
        , table: {
            url: '../account/selectAccount'
            , cellMinWidth: 60
            , cols: [
                [
                    {type: 'checkbox'},
                    {field: 'accountId', title: '账户编号'},
                    {field: 'accountName', title: '账户名称'}
                ]
            ]
        }
        , done: function (elem, data) {
            var NEWJSON = [];
            layui.each(data.data, function (index, item) {
                NEWJSON.push(item.accountName);
                $("#accountId").val(item.accountId);
            })
            elem.val(NEWJSON.join(","));

        }
    });
    //新增券商下拉表单
    tableSelect.render({
        elem:'#brokersName'
        ,checkedKey:'brokersName'
        ,table:{
            url:'../selectBrokers'
            ,cellMinWidth: 60
            ,cols:[
                [
                    {type:'checkbox'},
                    {field:'brokersId',title:'券商编号'},
                    {field: 'brokersName',title:'券商名称'},
                    {field: 'brokersInstructions',title:'券商描述'}
                ]
            ]
        }
        ,done:function (elem,data) {
            var NEWJSON = [];
            layui.each(data.data, function (index, item) {
                NEWJSON.push(item.brokersName);
                $("#brokersId").val(item.brokersId);
            });
            elem.val(NEWJSON.join(","));
            //新增席位名称下拉表单
            tableSelect.render({
                elem: '#seateName'
                , checkedKey:'seateName'
                , table: {
                    url: '../seateSelect?brokersId=' + $("#brokersId").val()
                    , cellMinWidth: 60
                    , cols: [
                        [
                            {type: 'checkbox'},
                            {field: 'seateId', title: '席位编号'},
                            {field: 'seateName', title: '席位名称'},
                            {field: 'seateRate', title: '佣金利率'},
                            {field: 'brokersId', title: '券商名称'},
                            {
                                field: 'seateAddress', title: '地址', templet: function (item) {
                                    if (item.seateAddress == '1') {
                                        return '上交所';
                                    } else if (item.seateAddress == '2') {
                                        return '深交所';
                                    }
                                }
                            }

                        ]
                    ]
                }
                , done: function (elem, data) {
                    var NEWJSON = [];
                    layui.each(data.data, function (index, item) {
                        NEWJSON.push(item.seateName);
                        $("#seateRate1").val(item.seateRate);
                    })
                    elem.val(NEWJSON.join(","));

                }
            });
        }
    })
    //修改证券下拉表单
    tableSelect.render({
        elem: '#securitiesName2'
        , checkedKey:'securitiesId'
        , table: {
            url: '../Securities/selectSecurities'
            , cellMinWidth: 60
            , cols: [
                [
                    {type: 'checkbox'},
                    {field: 'securitiesId', title: '证券id'},
                    {field: 'securitiesName', title: '证券名称'},
                    {
                        field: 'securitiesType', title: '证券类型', templet: function (item) {
                            if (item.securitiesType == '1') {
                                return '债券';
                            } else if (item.securitiesType == '2') {
                                return '股票';
                            }
                        }
                    },
                    {
                        field: 'securitiesType', title: '证券地址', templet: function (item) {
                            if (item.securitiesType == '1') {
                                return '上交所';
                            } else if (item.securitiesType == '2') {
                                return '深交所';
                            }
                        }
                    }
                ]
            ]
        }
        , done: function (elem, data) {
            var NEWJSON = [];
            layui.each(data.data, function (index, item) {
                NEWJSON.push(item.securitiesName);
                $("#securitiesId2").val(item.securitiesId);
            })
            elem.val(NEWJSON.join(","));

        }
    });

    //修改现金账户下拉表单
    tableSelect.render({
        elem: '#accountName2'
        , checkedKey: 'accountId'
        , table: {
            url: '../account/selectAccount'
            , cellMinWidth: 60
            , cols: [
                [
                    {type: 'checkbox'},
                    {field: 'accountId', title: '账户编号'},
                    {field: 'accountName', title: '账户名称'}
                ]
            ]
        }
        , done: function (elem, data) {
            var NEWJSON = [];
            layui.each(data.data, function (index, item) {
                NEWJSON.push(item.accountName);
                $("#accountId2").val(item.accountId);
            })
            elem.val(NEWJSON.join(","));

        }
    });
    //修改券商下拉表单
    tableSelect.render({
        elem:'#brokersName2'
        ,checkedKey:'brokersName'
        ,table:{
            url:'../selectBrokers'
            ,cellMinWidth: 60
            ,cols:[
                [
                    {type:'checkbox'},
                    {field:'brokersId',title:'券商编号'},
                    {field: 'brokersName',title:'券商名称'},
                    {field: 'brokersInstructions',title:'券商描述'}
                ]
            ]
        }
        ,done:function (elem,data) {
            // alert("选中的数据：" + data);
            var NEWJSON = [];
            layui.each(data.data, function (index, item) {
                NEWJSON.push(item.brokersName);
                $("#brokersId2").val(item.brokersId);
            });
            elem.val(NEWJSON.join(","));
            //修改席位名称下拉表单
            tableSelect.render({
                elem: '#seateName2'
                , checkedKey: 'seateName'
                , table: {
                    url: '../seateSelect?brokersId=' + $("#brokersId").val()
                    , cellMinWidth: 60
                    , cols: [
                        [
                            {type: 'checkbox'},
                            {field: 'seateId', title: '席位编号'},
                            {field: 'seateName', title: '席位名称'},
                            {field: 'seateRate', title: '佣金利率'},
                            {field: 'brokersId', title: '券商名称'},
                            {
                                field: 'seateAddress', title: '地址', templet: function (item) {
                                    if (item.seateAddress == '1') {
                                        return '上交所';
                                    } else if (item.seateAddress == '2') {
                                        return '深交所';
                                    }
                                }
                            }

                        ]
                    ]
                }
                , done: function (elem, data) {
                    var NEWJSON = [];
                    layui.each(data.data, function (index, item) {
                        NEWJSON.push(item.seateName);
                        $("#seateId2").val(item.seateId);
                    })
                    elem.val(NEWJSON.join(","));

                }
            });
        }
    })

});
//添加

function myNum() {
    var lixidays=0;
    layui.use(['layer'], function () {
        var $ = layui.$;
        //交易金额 = 数量*价格
        var num = document.getElementById("num").value;
        var price = document.getElementById("price").value;
        var netReceipts = Number(num) * Number(price);
        $("#netReceipts").val(netReceipts);

        //佣金费用 = 交易金额*席位佣金利率
        var seateRate1=document.getElementById("seateRate1").value;
        var netReceipts1=document.getElementById("netReceipts").value;
        var commission=Number(seateRate1)*Number(netReceipts1);
        $("#commission").val(commission/100);
        //securitiesId
        var securitiesId =document.getElementById("securitiesId").value;
        var securitiesType1=document.getElementById("securitiesType1").value;
        var exchange1=document.getElementById("exchange1").value;

        $.post("../varietiesRate/selectVarietiesRate", {exchangeName: exchange1,rateType: securitiesType1,page:1,limit:1},function(obj){
            $.each(obj.data,function (index,item) {
                $('#stampDuty1').val(item.stampDuty);
                $('#transferFee1').val(item.transferFee);
                $('#collectionRate1').val(item.collectionRate);
                $('#brokerage1').val(item.brokerage);
                //证券利息：股票和债券都属于证券
                //股票没有利息，债券利息就是债券


                $.post("../selectBond", {securitiesId: securitiesId,drawStartDate: '',page:1,limit:1},function(obj2) {
                    $.each(obj2.data,function (index2,item2){
                        //  票面利率/365*票面金额*数量*1
                        //票面利率
                        var lixiday = Number(item2.parRate)/365*Number(item2.bondRateAmount)*Number(num)*1;
                        lixidays = lixiday.toFixed(2);
                        $('#security1').val(lixidays);
                    })
                })
                var vals=0;
                if (item.rateType==1){
                    vals = $('#security1').val();
                }else if (item.rateType==2){
                    $('#security1').val(0);
                }
                // alert(vals)
                var totalSum=0;
                //买入
                if ($('#transactionDataMode').val()==1){
                    totalSum=Number(netReceipts)+Number(commission)/100+Number(item.stampDuty)
                        +Number(item.transferFee)+Number(item.collectionRate)+Number(item.brokerage)+Number(vals);
                    //var floatSum=parseFloat(totalSum);
                    var ser=totalSum.toFixed(2);
                    $('#totalSum').val(ser);
                    //卖出
                }else if($('#transactionDataMode').val()==2){
                    $('#totalSum').val(netReceipts-(commission/100+item.stampDuty
                        +item.transferFee+item.collectionRate+item.brokerage)+vals);
                }
            })

        });
    })
}

//修改
function myNum2() {
    layui.use(['layer'], function () {
        var $ = layui.$;
        //交易金额 = 数量*价格
        var num2 = document.getElementById("num2").value;
        var price2 = document.getElementById("price2").value;
        var netReceipts2 = Number(num2) * Number(price2);
        $("#netReceipts2").val(netReceipts2);

        //佣金费用 = 交易金额*席位佣金利率
        var seateRate2=document.getElementById("seateRate2").value;
        var netReceipts2=document.getElementById("netReceipts2").value;
        var commission2=Number(seateRate2)*Number(netReceipts2);
        $("#commission2").val(commission2/100);
        //securitiesId
        var securitiesId2 =document.getElementById("securitiesId2").value;
        var securitiesType2=document.getElementById("securitiesType2").value;
        var exchange2=document.getElementById("exchange2").value;

        $.post("../varietiesRate/selectVarietiesRate", {exchangeName: exchange2,rateType: securitiesType2,page:1,limit:1},function(obj){
            $.each(obj.data,function (index,item) {
                $('#stampDuty2').val(item.stampDuty);
                $('#transferFee2').val(item.transferFee);
                $('#collectionRate2').val(item.collectionRate);
                $('#brokerage2').val(item.brokerage);
                //证券利息：股票和债券都属于证券
                //股票没有利息，债券利息就是债券
                var parRate2=0;
                var bondRateAmount2=0;
                var lixiday=0;
                $.post("../selectBond", {securitiesId: securitiesId2,drawStartDate: '',page:1,limit:1},function(obj2) {
                    $.each(obj2.data,function (index2,item2){
                        //  票面利率/365*票面金额*数量*1
                        //票面利率
                        parRate2 = item2.parRate;
                        //票面金额
                        bondRateAmount2 = item2.bondRateAmount;
                    })
                })
                //当天应收债券利息
                lixiday = parRate2/365*bondRateAmount2*num2*1;

                //股票没有利息
                if (item.rateType==2){
                    lixiday=0;
                    $('#security2').val(lixiday);
                }else if (item.rateType==1){
                    $('#security2').val(lixiday);
                }
                //买入   结算金额
                if ($('#transactionDataMode2').val()==1){
                    var totalSum=Number(netReceipts2)+Number(commission2)/100+Number(item.stampDuty)
                        +Number(item.transferFee)+Number(item.collectionRate)+Number(item.brokerage)+Number(lixiday);
                    var ser=totalSum.toFixed(2);
                    $('#totalSum2').val(ser);
                    //卖出
                }else if($('#transactionDataMode2').val()==2){
                    $('#totalSum2').val(netReceipts2-(commission2/100+item.stampDuty
                        +item.transferFee+item.collectionRate+item.brokerage)+lixiday);
                }
            })

        });
    })
}
