layui.use(['table', 'treetable', 'element', 'form', 'layer', 'laydate'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var treetable = layui.treetable;
    var layer = layui.layer;
    var form = layui.form;
    // var formSelects = layui.formSelects;
    // var laydate = layui.laydate;

    //新增父类提交
    form.on('submit(addsubmitparent)', function (data) {
        alert("coming")
        var formData = $('#addformparent').serialize();
        $.post("../Stock/insertStock", formData, function (msg) {
            if (msg > 0) {
                treetable.render({
                    treeColIndex: 1,
                    treeSpid: '000',
                    treeIdName: 'stockId',
                    treePidName: 'stockParentId',
                    elem: '#stock-table',
                    toolbar: '#stockToolBar',//显示在表头的工具条
                    url: '../Stock/selectStock',
                    page: false,
                    cols: [
                        [
                            {type: 'numbers'},
                            {field: 'stockId', width: 200, title: '股票板块编号'},
                            {field: 'stockName', title: '股票板块名称'},
                            {field: 'stockDesc', width: 300, align: 'center', title: '备注'},
                            {templet: '#barDemo', width: 300, align: 'center', title: '操作'}
                        ]
                    ],
                    done: function () {
                        // layer.closeAll('loading');
                    }
                });
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

    //新增子类提交
    form.on('submit(editsubmit)', function (data) {
        var formData = $('#editform2').serialize();
        $.post("../Stock/insertStock", formData, function (msg) {
            if (msg > 0) {
                treetable.render({
                    treeColIndex: 1,
                    treeSpid: '000',
                    treeIdName: 'stockId',
                    treePidName: 'stockParentId',
                    elem: '#stock-table',
                    toolbar: '#stockToolBar',//显示在表头的工具条
                    url: '../Stock/selectStock',
                    page: false,
                    cols: [
                        [
                            {type: 'numbers'},
                            {field: 'stockId', width: 200, title: '股票板块编号'},
                            {field: 'stockName', title: '股票板块名称'},
                            {field: 'stockDesc', width: 300, align: 'center', title: '备注'},
                            {templet: '#barDemo', width: 300, align: 'center', title: '操作'}
                        ]
                    ],
                    done: function () {
                        // layer.closeAll('loading');
                    }
                });
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
    form.on('submit(updatesubmit)', function (data) {
        var formData = $('#updateform').serialize();
        $.post("../Stock/updateStock", formData, function (msg) {
            if (msg > 0) {
                treetable.render({
                    treeColIndex: 1,
                    treeSpid: '000',
                    treeIdName: 'stockId',
                    treePidName: 'stockParentId',
                    elem: '#stock-table',
                    toolbar: '#stockToolBar',//显示在表头的工具条
                    url: '../Stock/selectStock',
                    page: false,
                    cols: [
                        [
                            {type: 'numbers'},
                            {field: 'stockId', width: 200, title: '股票板块编号'},
                            {field: 'stockName', title: '股票板块名称'},
                            {field: 'stockDesc', width: 300, align: 'center', title: '备注'},
                            {templet: '#barDemo', width: 300, align: 'center', title: '操作'}
                        ]
                    ],
                    done: function () {
                        // layer.closeAll('loading');
                    }
                });
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

    // 渲染表格
    treetable.render({
        treeColIndex: 1,
        treeSpid: '000',
        treeIdName: 'stockId',
        treePidName: 'stockParentId',
        elem: '#stock-table',
        toolbar: '#stockToolBar',//显示在表头的工具条
        url: '../Stock/selectStock',
        page: false,
        cols: [
            [
                {type: 'numbers'},
                {field: 'stockId', width: 200, title: '股票板块编号'},
                {field: 'stockName', title: '股票板块名称'},
                {field: 'stockDesc', width: 300, align: 'center', title: '备注'},
                {templet: '#barDemo', width: 300, align: 'center', title: '操作'}
            ]
        ],
        done: function () {
            // layer.closeAll('loading');
        }
    });

    //监听增加的工具栏
    table.on('toolbar(stock-table)', function (obj) {
        //获取选中复选框的对象，
        var checkStatus = table.checkStatus(obj.config.id);//得到表格选中行的ID
        switch (obj.event) {
            case 'addParent':
                var index = layer.open({
                    type: 1,
                    title: '添加股票父板块',
                    closeBtn: 1,
                    move: false,
                    content: $("#addParentContent"),
                    area:['700px','400px'],
                    btn: []
                });
                form.render();
                //全屏
                //layer.full(index);
                break;
            case 'addChild':
                var index = layer.open({
                    type: 1,
                    title: '添加股票子板块',
                    closeBtn: 1,
                    move: false,
                    content: $("#addSonContent"),
                    area:['700px','400px'],
                    btn: []
                });
                form.render();
                //全屏
                //layer.full(index);
                break;
            <!--展开按钮-->
            case 'btn-expand':
                treetable.expandAll('#stock-table');
                break;
            case 'btn-fold':
                <!--折叠按钮-->
                treetable.foldAll('#stock-table');
                break;
        }
    });

    //给表格编辑，删除按钮添加点击事件
    table.on('tool(stock-table)', function (obj) {
        var data = obj.data;//得到删除行整行的数据
        alert(data.stockId);
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', {icon: 2}, function (index) {
                layer.close(index);
                $.post("../Stock/deleteStock", {stockId: data.stockId}, function (msg) {
                    treetable.render({
                        treeColIndex: 1,
                        treeSpid: '000',
                        treeIdName: 'stockId',
                        treePidName: 'stockParentId',
                        elem: '#stock-table',
                        toolbar: '#stockToolBar',//显示在表头的工具条
                        url: '../Stock/selectStock',
                        page: false,
                        cols: [
                            [
                                {type: 'numbers'},
                                {field: 'stockId', width: 200, title: '股票板块编号'},
                                {field: 'stockName', title: '股票板块名称'},
                                {field: 'stockDesc', width: 300, align: 'center', title: '备注'},
                                {templet: '#barDemo', width: 300, align: 'center', title: '操作'}
                            ]
                        ],
                        done: function () {
                            // layer.closeAll('loading');
                        }
                    });
                });

            });
        } else if (obj.event === 'edit') {
            alert(JSON.stringify(data));
            form.val('updateform', $.parseJSON(JSON.stringify(data)));
            var index = layer.open({
                type: 1,
                title: '修改股票信息',
                closeBtn: 1,
                move: false,
                area:['700px','500px'],
                content: $('#updatContent')
            });
            form.render();
            //layer.full(index);
        };
    })
});

function myclose() {
    layer.closeAll();
}

layui.use(['tableSelect'], function () {
    var $ = layui.jquery,
        tableSelect = layui.tableSelect;
    //增加得下拉表格
    tableSelect.render({
        elem: '#addParentStock',
        checkedKey: 'stockName',
        table: {
            url: '../Stock/selectParentStock',
            cols: [
                [{type: 'radio'},
                    {field: 'stockName', title: '股票名称'},
                    {field: 'stockId', title: '股票Id'}
                ]
            ]
        },
        done: function (elem, data) {
            //elem:返回之前input对象；data:表格返回的选中的数据 []
            var newJson = [];
            //遍历选中的数据
            $.each(data.data, function (index, item) {
                newJson.push(item.stockId);
                console.log(item.stockId)
                $("#addAccountId").val(item.stockId);//给隐藏域中的val赋值
            });
            elem.val(newJson.join(","));//给输入框里显示的值赋值
        }
    })
})