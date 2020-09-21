layui.use(['element','table','layer'],function () {
    var layer = layui.layer,
        $ = layui.$,
        table = layui.table;

    table.render({
        elem:'#logTable',
        url:'../sysLog/selectSysLog',
        page:true,
        toolbar:true,
        cellMinWidth:200,
        height:'full-20',
        cols:[
            [
                {fixed:'left',field: 'ssawLogId', title: 'Id',align:'center',width:50},
                {field: 'logByTime', title: '访问时间',align:'center'},
                {field: 'logByUser', title: '用户',align:'center'},
                {field: 'logByIp', title: 'IP',align:'center'},
                {field: 'logByUrl', title: '访问URL',align:'center'},
                {field: 'logByRunName', title: '执行方法',align:'center'},
                {fixed:'right',field: 'logByRunTime', title: '执行时间',align:'center'},
                {field: 'logByClass', title: '访问方法路径',align:'center',hide:true}
            ]
        ]
    })
});