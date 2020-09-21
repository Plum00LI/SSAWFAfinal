layui.config({
    base: 'exts/',
})
layui.use(['form', 'table', 'layer','laydate','jquery'], function() {
    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;
    var form = layui.form;
    var laydate = layui.laydate;
    table =  $.extend(table, {config: {checkName: 'checked'}});
    $('.cancel').click(function(){
        layer.closeAll();
    });

    table.render({
        elem : '#mytable',
        url : '../assetValuationController/selectAssetValuationData',
        page : true,
        limit : 8,
        height: 'full-50',
        toolbar : '#toolbarDemo',
        cols : [
            [
                {
                    field:'left',
                    checkbox:true,
                },
                {field:'assetValuationId',
                    hide:true
                }
                , {
                field : 'assetValuationType',
                align: 'center',
                title : '业务类型'
            }, {
                field : 'state',
                align: 'center',
                title : '状态'
            }
            ]
        ]
    });

    laydate.render({
        elem:'#valuation1',
        type:'date',
        value:new Date(),
        done:function(value){
            var checked = table.checkStatus('mytable').data;
            var strAppraisement = [];
            for(var i=0;i<checked.length;i++){
                strAppraisement.push(checked[i].assetValuationId);
            }
            var toDay = value;

            date1(value);
        }
    })
    function date1(val){
        $("#valuation1").remove();
        $("#valuation2").before('<input type="text" name="toDay" style="height: 32px;" class="layui-input" id="valuation1" />');
        laydate.render({
            elem: '#valuation1',//指定元素
            value:val,
            done:function(value){
                var checked = table.checkStatus('mytable').data;
                var strAppraisement = [];
                for(var i=0;i<checked.length;i++){
                    strAppraisement.push(checked[i].assetValuationId);
                }

                date2(value);
            }
        });
    }
    function date2(val){
        $("#valuation1").remove();
        $("#valuation2").before('<input type="text" name="toDay" style="height: 32px;" class="layui-input" id="valuation1" />');
        laydate.render({
            elem: '#valuation1',//指定元素
            value:val,
            done:function(value){
                var checked = table.checkStatus('mytable').data;
                var strAppraisement = [];
                for(var i=0;i<checked.length;i++){
                    strAppraisement.push(checked[i].assetValuationId);
                }

                date1(value);
            }
        });
    }

    table.on('toolbar(mytable)', function(obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        var data=checkStatus.data;
        switch (obj.event) {
            case 'valuation':
                if(data.length === 0){
                    layer.msg("请选择估值项")
                }else{
                    var toDay= $("#valuation1").val();
                    var fundId = $("#fundId").val();
                    var accountId = $("#accountId").val();
                    if(toDay == null || toDay == ''){
                        layer.msg("请输入日期")
                    }else{
                        var strJson = [];
                        for (var i = 0; i < data.length; i++) {
                            strJson.push(data[i])
                        }
                        var arrJson = JSON.stringify(strJson);
                        layer.confirm('是否确认估值？', {
                            btn: ['确认','取消'] ,//按钮
                            icon:1
                        }, function(){
                            layer.closeAll('dialog');


                            $.post('../assetValuationController/startValuation',{toDay:toDay,fundId:fundId,accountId:accountId,arrJson:arrJson},function(res){
                                if(res.stockSecuritiesJoinMarketList == ''){
                                    //边缘弹出
                                    layer.open({
                                        type: 1
                                        ,offset: 'rb' //具体配置参考：offse t参数项
                                        ,content: '<div style="padding: 15px 80px;">估值失败，请检查当天的证券库存</div>'
                                        ,skin: 'layui-layer-molv'
                                        ,btn: '关闭'
                                        ,time: 4000
                                        ,btnAlign: 'c' //按钮居中
                                        ,shade: 0 //不显示遮罩
                                        ,yes: function(){
                                            layer.closeAll();
                                        }
                                    });
                                }else if(res.assetValuationDataList != '' || res.transactionDataList != ''){
                                    table.reload('mytable', {
                                        page: {curr: 1},
                                        where:{
                                            toDay:toDay,
                                            strAppraisement:strJson.join(",")
                                        }
                                    });
                                    //边缘弹出
                                    layer.open({
                                        type: 1
                                        ,offset: 'rb' //具体配置参考：offset参数项
                                        ,content: '<div style="padding: 15px 80px;">估值完毕！</div>'
                                        ,skin: 'layui-layer-molv'
                                        ,btn: '关闭'
                                        ,time: 4000
                                        ,btnAlign: 'c' //按钮居中
                                        ,shade: 0 //不显示遮罩
                                        ,yes: function(){
                                            layer.closeAll();
                                        }
                                    });
                                    date1(toDay)
                                }
                            })
                            /*	setTimeout(function(){

                                },100)
                            */
                        });


                        /* $("#valuation1").val(toDay);
                        $.post('assetValuationController/startValuation',{toDay:toDay},function(res){
                            console.log(res)
                            if(res == null || res ==''){
                                for(var i=0;i<data.length;i++){
                                    if(data[i].assetValuationId == 1){
                                        dataz[0].state = '未估值'
                                    }
                                    if(data[i].assetValuationId == 2){
                                        dataz[1].state = '未结算'
                                    }
                                }
                                table.reload('mytable');
                            }else{
                                $.each(res,function(item,value){
                                    for(var i=0;i<data.length;i++){
                                        if(value == 1 && value == data[i].assetValuationId){
                                            dataz[0].state = '已估值'
                                        }
                                        if(value == 2  && value == data[i].assetValuationId){
                                            dataz[1].state = '已结算'
                                        }
                                    }
                                })
                            }

     })*/

                    }
                }
                break;
            default:
                break;
        }
    });
});
