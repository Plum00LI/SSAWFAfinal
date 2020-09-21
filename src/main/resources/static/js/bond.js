
layui.use(['element', 'form', 'table', 'layer', 'laydate'], function () {
	var layer = layui.layer;
	var $ = layui.$;
	var table = layui.table;
	var form = layui.form;
	var formSelects = layui.formSelects;
	var laydate = layui.laydate;

	//执行一个laydate实例
	laydate.render({
		elem: '#start' //指定元素
	});
	//执行一个laydate实例
	laydate.render({
		elem: '#start1' //指定元素
	});
	//执行一个laydate实例
	laydate.render({
		elem: '#start2' //指定元素
	});
	laydate.render({
		elem: '#start3' //指定元素
	});
	laydate.render({
		elem: '#start4' //指定元素
	});
	//新增提交
	form.on('submit(addsubmit)', function(data){
		var formData=$('#addform').serialize();
		alert("表单数据：" + formData);

		$.post("../insertBond",formData,function(msg){
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
		$.post("../updateBond",formData,function(msg){
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
		url: '../selectBond',
		page: true,
		height: 'full-20',
		toolbar: '#userToolBar',//显示在表头的工具条
		cellMinWidth:60,
		cols: [
			[ //表头
				{type: 'checkbox', fixed: 'left'}
				,{field: 'securitiesId', title: '债券编号',  align:'center'}
				,{field: 'bondName', title: '债券名称',  align:'center'}
				,{field: 'drawStartDate', title: '计息起始日',  align:'center'}
				,{field: 'drawEndDate', title: '计息结束日',  align:'center'}
				,{field: 'bondType', title: '债券类型',  align:'center',templet:function (item){
					if (item.bondType=='1'){
						return '银行间';
					}else {
						return '非银行间';
					}
				}}
				,{field: 'parRate', title: '票面利率(%)',  align:'center'}
				,{field: 'bondRate', title: '债券利息(%)',  align:'center'}
				,{field: 'bondRateAmount', title: '票面金额',  align:'center'}
				,{field: 'payInterestNum', title: '付息次数',  align:'center',templet:function (item) {
					if (item.payInterestNum=='1'){
						return '一年一次';
					}else if (item.payInterestNum=='2'){
						return '一年两次';
					}else if (item.payInterestNum=='3'){
						return '一年四次';
					}
				}}
				,{field: 'bondDesc', title: '备用字段',  align:'center'}

				,{fixed: 'right', title: '操作',width: 150, align:'center', toolbar: '#barDemo'}
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
					title: '添加',
					closeBtn: 1,
					move:false,
					content:$("#addContent"),
					btn:[],
					area:['800px','550px']
				});

				form.render();
				break;
			case 'search':

				var bondName= $("#bondName").val();
				var drawStartDate= $("#start").val();
				//表格的重新加载事件
				table.reload('userTable', {
					method: 'post'
					, where: {
						'bondName': bondName,
						'drawStartDate': drawStartDate,
					}
					, page: {
						curr: 1
					}
				});

				break;
			case 'deleteAll':
				var data = checkStatus.data;
				//    layer.alert(JSON.stringify(data));
				if(data.length==0){
					layer.msg("请至少选择一条数据",)
				}else
				{
					var ids=[];
					for (var i = 0; i <data.length; i++) {
						ids.push(data[i].securitiesId);
					}
					layer.confirm('真的删除行么',{icon: 2}, function(index){
						layer.close(index);
						$.post("../deleteBond", {securitiesId:ids.join(',')},function(msg){
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
				$.post("../deleteBond", {securitiesId:data.securitiesId+""},function(msg){
					table.reload('userTable');
				});

			});
		} else if (obj.event === 'edit') {
			//alert(JSON.stringify(data));

			form.val('editform',$.parseJSON(JSON.stringify(data)));
			var index = layer.open({
				type: 1,
				title: '修改',
				closeBtn: 1,
				move:false,
				area: ['800px', '550px'],
				content:$('#editContent')
			});

			form.render();
		};
	})
});

function quxiao() {
	layer.closeAll();
}

layui.use([ 'tableSelect'], function () {
	var $=layui.jquery,
		tableSelect=layui.tableSelect;
	//增加得下拉表格
	tableSelect.render({
		elem:'#insertSecurities',
		checkedKey:'securitiesId',
		table:{
			url:'../Securities/selectSecurities',
			cellMinWidth:60,

			cols:[
				[   {type:'radio'},
					{field:'securitiesId',title:'证券编号',width:250},
					{field: 'securitiesName',title: '证券名称',width:250}
				]
			]
		},
		done:function (elem,data) {
			//elem:返回之前input对象；data:表格返回的选中的数据 []
			var newJson=[];
			//遍历选中的数据
			$.each(data.data,function (index,item) {
				newJson.push(item.securitiesId);
			});
			elem.val(newJson.join(","));//给输入框里显示的值赋值

		}
	})

})