layui.config({
	base: '../exts/',
})
function fmoney(s, n) {
	n = n > 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
	t = "";
	for (i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	return t.split("").reverse().join("") + "." + r;
}
var data = [{"projectName":"证券","parentId":" ","projectId":" ","quantity":"10,00","market":"","cost":"100,00","marketValue":"","valuation":""},
	{"projectName":"现金","parentId":" ","projectId":"","quantity":"","market":"","cost":"","marketValue":"","valuation":""},
	{"projectName":"合计","parentId":" ","projectId":"","quantity":"","market":"","cost":"","marketValue":"","valuation":""},
	{"projectName":"股票","parentId":"证券","projectId":"","quantity":"","market":"","cost":"","marketValue":"","valuation":""},
	{"projectName":"债券","parentId":"证券","projectId":"","quantity":"10,00","market":"","cost":"","marketValue":"","valuation":""},
	{"projectName":"腾讯","parentId":"股票","projectId":"","quantity":"10,00","market":"","cost":"","marketValue":"","valuation":""}]
layui.use([ 'element', 'form', 'table', 'layer','dynamicCondition','treeTable','laydate'], function() {
	var layer = layui.layer;
	var dynamicCondition=layui.dynamicCondition;
	var $ = layui.$;
	var table = layui.table;
	var form = layui.form;
	var formSelects = layui.formSelects;
	var treeTable=layui.treeTable;
	var dates = layui.laydate;
	$('.cancel').click(function(){
		layer.closeAll();
	});

	dates.render({
		elem: '#test12',
		type: 'date' ,//默认，可不填
		value: new Date()//获取当前时间
	});

	function zx(time){
		alert("1")
		alert(time)
		var re = treeTable.render({

			elem : '#tree-table',// 必须
			dataType:'json',
			method : "post",
			data:data,// （url和data参数必须设置一个）
			// url : '../../ValueStatistics?datetime='+time,
			icon_key : 'projectName',// 必须
			top_value : '1',// 主菜单的父id值
			primary_key : 'projectName',// 菜单id
			treeDefaultClose: false ,
			treeLinkage : false,
			parent_key : 'parentId',// 父编号
			is_checkbox: true,
			hide_class : 'layui-hide',
			icon : {
				open : 'layui-icon layui-icon-triangle-d',
				close : 'layui-icon layui-icon-triangle-r',
				left : 16,
			},
			is_checkbox : true,
			end : function(e) {
				form.render();
			},
			cols : [
				{
					key : 'projectName',
					title : '项目名称',
					width : '120px',
					template : function(item) {
						if (item.level == 0) {
							return '<span style="color:red;">'
							+ item.projectName
							+ '</span>';
						} else if (item.level == 1) {
							return '<span style=" color:green;">'
							+ item.projectName
							+ '</span>';
						} else if (item.level == 2) {
							 return item.projectName;

						}
					}
				},
				{
					key : 'projectId',
					title : '项目代码',
					width : '100px',
					align : 'center',
					template : function(item){
						if(item.projectId == null){
							return "";
						}else{
							if((item.level == 1&&item.parentId=='现金')||(item.level == 2&&(item.parentId=='股票'||item.parentId=='债券'))){
								return item.projectId;
							}
							if(item.projectId<0){

								return "<span style='color:red'>"+fmoney(item.projectId*-1,2)+"</span>";
							}else{
								return fmoney(item.projectId,2);
							}
						}
					}
				},
				{
					key : 'quantity',
					title : '票面值/股数',
					width : '100px',
					align : 'center',
					template : function(item){
						if(item.quantity == null){
							return " ";
						}else{
							return fmoney(item.quantity,2);
						}
					}
				},
				{
					key : 'price',
					title : '行情',
					width : '100px',
					align : 'center',
					template : function(item){
						if(item.price == null){
							return " ";
						}else{
							return item.price;
						}
					}
				},
				{
					key : 'cost',
					title : '成本',
					width : '100px',
					align : 'center',
					template : function(item){
						console.log(item)
			 			if(item.cost == null){
							return " ";
						}else{
							if(Number(item.cost)<0){
								return "<span style='color:#ff0000'>"+fmoney(item.cost*-1,2)+"</span>"
							}else{
								return fmoney(item.cost,2);
							}
						}
					}
				},
				{
					key : 'marketValue',
					title : '市值',
					width : '100px',
					align : 'center',
					template : function(item){
						if(item.marketValue == null){
							return " ";
						}else{
							return fmoney(item.marketValue,2);
						}
					}
				},
				{
					key : 'valuation',
					title : '估值增值',
					width : '100px',
					align : 'center',
					template : function(item){
						if(item.valuation == null){
							return " ";
						}else{
							return fmoney(item.valuation,2);
						}
					}
				}]
		});
	}
	zx($('#test12').val());
	// 全部展开
	$('.search').click(function(){
		var a =$("#test12").val();
		$.post("ValueStatistics/insertValueStatistics",{datetime:a},function(data){
			console.log(data);
			if(data>0){
				//treeTable.render(re);//重新加载表格
				zx(a);//
				layer.closeAll();//关闭窗口
				layer.msg('统计成功成功',{
					title : '提示',
					area : [ '200px',
						'140px' ],
						time : 0,
						btn : [ '知道了' ]
				});
				$('#test12').val(a);
			}
		})
	})
});