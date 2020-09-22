layui.use(['element', 'form', 'table', 'layer', 'laydate'], function () {
	var layer = layui.layer;
	var $ = layui.$;
	var table = layui.table;
	var form = layui.form;
	var formSelects = layui.formSelects;
	var laydate = layui.laydate;

	//执行一个laydate实例
	laydate.render({
		elem: '#start',//指定元素
	});
	//执行一个laydate实例
	laydate.render({
		elem: '#updatetime'//指定元素
	});
	//执行一个laydate实例
	laydate.render({
		elem: '#editopen',//指定元素
	});
	//执行一个laydate实例
	laydate.render({
		elem: '#editend'//指定元素
	});

	//新增提交
	form.on('submit(addsubmit)', function (data) {
		var formData = $('#addform').serialize();
		$.post("/Securities/insertSecurities", formData, function (msg) {
			if (msg > 0) {
				table.reload('userTable');
				layer.closeAll();
				layer.msg('添加成功', {
					title: '提示',
					area: ['200px',
						'140px'],
					time: 0,
					btn: ['知道了']
				});
			} else {
				layer.closeAll();
				layer.msg('添加失败', {
					title: '提示',
					area: ['200px',
						'140px'],
					time: 0,
					btn: ['知道了']
				});
			}
		});
		return false;
	});

	//修改提交
	form.on('submit(editsubmit)', function (data) {
		var formData = $('#editform').serialize();
		$.post("/Securities/updateSecurities", formData, function (msg) {
			if (msg > 0) {
				table.reload('userTable');
				layer.closeAll();
				layer.msg('修改成功', {
					title: '提示',
					area: ['200px',
						'140px'],
					time: 0,
					btn: ['知道了']
				});
			} else {
				layer.closeAll();
				layer.msg('修改失败', {
					title: '提示',
					area: ['200px',
						'140px'],
					time: 0,
					btn: ['知道了']
				});
			}
		});
		return false;
	});

	//查询表格渲染
	table.render({
		elem: '#userTable',
		url: '../Securities/selectSecurities',
		toolbar: '#userToolBar',
		defaultToolbar: ['filter', 'exports', 'print'],
		height:'full-20',
		cellMinWidth:60,
		cols: [
			[
				{type: "checkbox", width: 50},
				{field: 'securitiesId', title: '证券编号', sort: true},
				{field: 'securitiesName', title: '证券名称'},
				{field: 'securitiesType', title: '证券类型', sort: true,
					templet:function (item) {
						if (item.securitiesType=="1"){
							return '债券';
						}else if (item.securitiesType=="2"){
							return '股票';
						}
					}
				},
				{field: 'issueDate', title: '发行日期'},
				{field: 'delayDate', title: '延迟日期',
					templet:function (item) {
						if (item.delayDate==="1"){
							return "T+1";
						}else if (item.delayDate==="2"){
							return "T+2";
						}
						else if (item.delayDate==="3"){
							return "T+3";
						}
						else if (item.delayDate==="4"){
							return "T+4";
						}
					}
				},
				{field: 'stockName', title: '股票板块', sort: true,
					hide:function (item) {
						if (item.stockParentId==="000"){
							return false;
						}else{
							return true;
						}
					}
				},
				{field: 'exchange', title: '交易所', sort: true,
					templet:function (item) {
						if (item.exchange=="1"){
							return '上交所';
						}else if (item.exchange=="2"){
							return '深交所';
						}
					}
				},
				{field: 'securitiesDesc', title: '备注', sort: true},
				{fixed:'right',title: '操作', minWidth: 100, toolbar: '#barDemo', align: "center"}
			]
		],
		limits: [10, 15, 20, 25, 50, 100],
		limit: 15,
		page: true,
		skin: 'line'
	});

	//给工具条的按钮添加事件
	table.on('toolbar(userTable)', function (obj) {
		//获取选中复选框的对象，
		var checkStatus = table.checkStatus(obj.config.id);//得到表格选中行的ID
		switch (obj.event) {
			case 'add':
				var index = layer.open({
					type: 1,
					title: '添加证券数据信息',
					closeBtn: 1,
					move: false,
					content: $("#addContent"),
					btn: [],
					area:['800px','400px']
				});
				form.render();
				break;
			case 'search':
				var securitiesId = $("#securitiesId_3").val();
				var exchange = $("#exchange_3").val();
				var securitiesType =  $("#securitiesType_3").val();
				//表格的重新加载事件
				table.reload('userTable', {
					method: 'post'
					, where: {
						'securitiesId': securitiesId,
						'exchange': exchange,
						'securitiesType': securitiesType
					}
					, page: {
						curr: 1
					}
				});
				$("#securitiesId_3").val(securitiesId);
				$("#exchange_3").val(exchange);
				$("#securitiesType_3").val(securitiesType);
				break;
			case 'deleteAll':
				var data = checkStatus.data;
				if (data.length == 0) {
					layer.msg("请至少选择一条数据",)
				} else {
					var securitiesIds = [];
					for (var i = 0; i < data.length; i++) {
						securitiesIds.push(data[i].securitiesId);
					}
					layer.confirm('真的删除行么', {icon: 2}, function (index) {
						layer.close(index);
						$.post("/Securities/deleteSecurities", {securitiesId: securitiesIds.join(',')}, function (msg) {
							table.reload('userTable');
							layer.msg('删除' + checkStatus.data.length + '条记录', {
								title: '提示',
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
	table.on('tool(userTable)', function (obj) {
		var data = obj.data;//得到删除行整行的数据
		if (obj.event === 'del') {
			layer.confirm('真的删除行么', {icon: 2}, function (index) {
				layer.close(index);
				$.post("../Securities/deleteSecurities",{securitiesId: data.securitiesId}, function (msg) {
					table.reload('userTable');
				});

			});
		} else if (obj.event === 'edit') {
			form.val('editform', $.parseJSON(JSON.stringify(data)));
			var index = layer.open({
				type: 1,
				title: '修改证券信息',
				closeBtn: 1,
				move: false,
				area: ['800px', '400px'],
				content: $('#editContent')
			});
			form.render();
		};
	})
});
function myclose() {
	layer.closeAll();
}

layui.use([ 'tableSelect'], function () {
	var $=layui.jquery,
		tableSelect=layui.tableSelect;
	//增加得下拉表格
	tableSelect.render({
		elem:'#addStockName',
		checkedKey:'stockName',
		table:{
			url:'../Stock/selectSonStock',
			cols:[
				[   {type:'radio'},
					{field:'stockName',title:'股票名称'},
					{field: 'stockId',title: '股票Id'}
				]
			]
		},
		done:function (elem,data) {
			//elem:返回之前input对象；data:表格返回的选中的数据 []
			var newJson=[];
			//遍历选中的数据
			$.each(data.data,function (index,item) {
				newJson.push(item.stockName);
				console.log(item.stockId)
				$("#addStockId").val(item.stockId);//给隐藏域中的val赋值
			});
			elem.val(newJson.join(","));//给输入框里显示的值赋值
		}
	})
})

layui.use([ 'tableSelect'], function () {
	var $=layui.jquery,
		tableSelect=layui.tableSelect;
	//增加得下拉表格
	tableSelect.render({
		elem:'#addStockName2',
		checkedKey:'stockName',
		table:{
			url:'../Stock/selectSonStock',
			cols:[
				[   {type:'radio'},
					{field:'stockName',title:'股票名称'},
					{field: 'stockId',title: '股票Id'}
				]
			]
		},
		done:function (elem,data) {
			//elem:返回之前input对象；data:表格返回的选中的数据 []
			var newJson=[];
			//遍历选中的数据
			$.each(data.data,function (index,item) {
				newJson.push(item.stockName);
				console.log(item.stockId)
				$("#addStockId2").val(item.stockId);//给隐藏域中的val赋值
			});
			elem.val(newJson.join(","));//给输入框里显示的值赋值
		}
	})
})