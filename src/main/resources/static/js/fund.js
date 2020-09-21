layui.use([ 'element', 'form', 'table', 'layer','laydate'], function () {
	var layer = layui.layer;
	var $ = layui.$;
	var table = layui.table;
	var form = layui.form;
	var laydate = layui.laydate;
	// var dynamicCondition=layui.dynamicCondition;
	//执行一个laydate实例
	laydate.render({
		elem: '#start' //指定元素
	});

	laydate.render({
		elem: '#start2'
	});

	laydate.render({
		elem: '#equitiesRecord'
	});

	laydate.render({
		elem: '#equitiesExright'
	});

	laydate.render({
		elem: '#receivedDate'
	});

	//新增提交
	form.on('submit(addsubmit)', function(data){
		var formData=$('#addform').serialize();
		$.post("../insertFund",formData,function(msg){
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
		$.post("../updateFund",formData,function(msg){
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
		url: '../selectFund',
		page: true,
		height: 'full-55',
		toolbar: '#userToolBar',//显示在表头的工具条
		minLength:80,
		cellMinWidth:60,
		cols: [
			[ //表头
				{type: 'checkbox', field: 'left'}
				,{field: 'fundId', title: '基金代码',  align:'center'}
				,{field: 'fundName', title: '基金名称',  align:'center'}
				,{field: 'managerCompany', title: '基金管理公司',  align:'center'}
				,{field: 'trusteeCompany', title: '基金托管银行',  align:'center'}
				,{field: 'fundType', title: '基金类型',  align:'center',templet: function(item){
					if(item.fundType=='2') {return '封闭式';
					} else if(item.fundType=='1'){ return '开放式';
					}
				}},
				{
					field : 'provisionDays',
					title:'计提天数',
					hide:true,
					align: 'center',
					templet :function(items){
						if(items.provisionDays==1){
							return '360';
						}
						if(items.provisionDays==2){
							return '365';
						}
						return '366';
					}
				},
				{fixed: 'right',field: 'operation', title: '操作' , width: 215, align:'center',toolbar:'#barDemo'}

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
					title: '添加基金信息',
					closeBtn: 1,
					move:false,
					content:$("#addContent"),
					btn:[],
					area:['800px','600px']
				});
				form.render();
				break;
			case 'search':
				var fundId= $("#fundId").val();
				var fundType= $("#fundType").val();
				//表格的重新加载事件
				table.reload('userTable', {
					method: 'post'
					, where: {
						'fundId': fundId,
						'fundType':fundType
					}
					, page: {
						curr: 1
					}
				});
				$("#fundId").val(fundId);
				$("#fundType").val(fundType);
				break;
			case 'deleteAll':
				var data = checkStatus.data;
				if(data.length==0){
					layer.msg("请至少选择一条数据",)
				}else
				{
					var ids=[];
					for (var i = 0; i <data.length; i++) {
						ids.push(data[i].fundId);
					}
					layer.confirm('真的删除行么',{icon: 2}, function(index){
						layer.close(index);
						layer.alert(JSON.stringify(data));
						$.post("../deleteFund", {fundId:ids.join(',')},function(msg){
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
				$.post("../deleteFund", {fundId:data.fundId+""},function(msg){
					table.reload('userTable');
				});
			});
		} else if (obj.event === 'edit') {
			form.val('editform',$.parseJSON(JSON.stringify(data)));
			var index = layer.open({
				type: 1,
				title: '修改基金信息',
				closeBtn: 1,
				move:false,
				area: ['800px', '600px'],
				content:$('#editContent')
			});
			form.render();
		}
	});

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
	//新增托管人下拉表格
	tableSelect.render({
		elem: '#trusteeCompany',
		checkedKey: 'trusteeCompany',
		table: {
			url: '../selectTrustee',
			cols: [
				[
					{type: 'radio'},
					{field: 'trusteeId', align: 'center', title: '托管人ID'},
					{field: 'trusteeCompany', align: 'center', title: '托管公司'},
					{field: 'trusteeDesc', align: 'center', title: '托管费率'}
				]]
		},
		done: function (elem, data) {
			var NEWJSON = [];
			layui.each(data.data, function (index, item) {
				NEWJSON.push(item.trusteeCompany)
				$("#trusteeId").val(item.trusteeId)
			});
			elem.val(NEWJSON.join(","))
		}
	});
	//新增管理人下拉表格
	// managerId
	// 管理人id
	// managerCompany
	// 管理人名称
	// managerFee
	// 管理费率
	tableSelect.render({
		elem: '#managerCompany',
		checkedKey: 'managerCompany',
		table: {
			url: '../selectManager',
			cols: [
				[
					{type: 'radio'},
					{field: 'managerId', align: 'center', title: '管理人id'},
					{field: 'managerCompany', align: 'center', title: '管理人名称'},
					{field: 'managerFee', align: 'center', title: '管理费率'}
				]]
		},
		done: function (elem, data) {
			var NEWJSON = [];
			layui.each(data.data, function (index, item) {
				NEWJSON.push(item.managerCompany)
				$("#managerId").val(item.managerId)
			});
			elem.val(NEWJSON.join(","))
		}
	});
	//修改托管人下拉表格
	tableSelect.render({
		elem: '#trusteeCompany2',
		checkedKey: 'trusteeId',
		table: {
			url: '../selectTrustee',
			cols: [
				[
					{type: 'radio'},
					{field: 'trusteeId', align: 'center', title: '托管人ID'},
					{field: 'trusteeCompany', align: 'center', title: '托管公司'},
					{field: 'trusteeDesc', align: 'center', title: '托管费率'}
				]]
		},
		done: function (elem, data) {
			var NEWJSON = [];
			layui.each(data.data, function (index, item) {
				NEWJSON.push(item.trusteeCompany)
				$("#trusteeId2").val(item.trusteeId)
			});
			elem.val(NEWJSON.join(","))
		}
	});
	//修改管理人下拉表格
	// managerId
	// 管理人id
	// managerCompany
	// 管理人名称
	// managerFee
	// 管理费率
	tableSelect.render({
		elem: '#managerCompany2',
		checkedKey: 'managerId',
		table: {
			url: '../selectManager',
			cols: [
				[
					{type: 'radio'},
					{field: 'managerId', align: 'center', title: '管理人id'},
					{field: 'managerCompany', align: 'center', title: '管理人名称'},
					{field: 'managerFee', align: 'center', title: '管理费率'}
				]]
		},
		done: function (elem, data) {
			var NEWJSON = [];
			layui.each(data.data, function (index, item) {
				NEWJSON.push(item.managerCompany)
				$("#managerId2").val(item.managerId)
			});
			elem.val(NEWJSON.join(","))
		}
	});

});
