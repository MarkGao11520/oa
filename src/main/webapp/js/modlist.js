/**
 * Created by gaowenfeng on 2017/6/30.
 */
$(function () {
    Date.prototype.toLocaleString = function() {
        return this.getFullYear() + "年" + (this.getMonth() + 1) + "月" + this.getDate() + "日 " + this.getHours() + "点" + this.getMinutes() + "分" + this.getSeconds() + "秒";
    };
    initTable();
    initDate();
});
function doQuery(params) {
    $('#table').bootstrapTable('refresh');    //刷新表格
}
function initTable() {
    var url = 'list'
    $('#table').bootstrapTable({
        method: 'GET',
        dataType: 'json',
        toolbar: '#query',
        //contentType: "application/x-www-form-urlencoded",
        cache: false,
        striped: true,                      //是否显示行间隔色
        clickToSelect: true,                //是否启用点击选中行
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        sortable: true,
//            sortOrder: "sname",
        height: $(window).height(),
        url: url,
        showColumns: true,
        pagination: true,                 	//是否启用分页
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                        //每页的记录行数（*）
        pageList: [10, 15, 20, 30],           //可供选择的每页的行数（*）
        queryParams: queryParams,			//传递参数
        minimumCountColumns: 2,
        uniqueId: "id",                    //每一行的唯一标识，一般为主键列
//            showExport: true,
        exportDataType: 'all',
        //   responseHandler: responseHandler,
        columns: [
            {
                field: 'id',
                title: 'ID',
                align: 'center',
                valign: 'middle',
            },
            {
                field: 'key',
                title: 'KEY',
                align: 'center',
                valign: 'middle',
//                            sortable : true
            }, {
                field: 'name',
                title: '名称',
                align: 'center',
                valign: 'middle',
//                            sortable : true
            }, {
                field: 'version',
                title: '版本',
                align: 'center',
                valign: 'middle',
//                            sortable : true
            }, {
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                valign: 'middle',
                formatter:function (value, row, index) {
                    var unixTimestamp = new Date( row.createTime ) ;
                    return unixTimestamp.toLocaleString();
                }
//                            sortable : true
            }, {
                field: 'lastUpdateTime',
                title: '更新时间',
                align: 'center',
                valign: 'middle',
                formatter:function (value, row, index) {
                    var unixTimestamp = new Date( row.lastUpdateTime ) ;
                    return unixTimestamp.toLocaleString();
                }
//                            sortable : true
            }, {
                field: '',
                title: '操作',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return '<button class="btn btn-success" onclick="exportXml(' + row.id + ')">导出</button>&nbsp;&nbsp;<button class="btn btn-success" onclick="model(' + row.id + ')">流程图</button>&nbsp;&nbsp;<button class="btn btn-success" onclick="depoly(' + row.id + ')">部署</button>';
                }
            }]
    });
}


function initDate() {
    var start = {
        elem: '#startDate',
        format: 'YYYY-MM-DD',
        min: laydate.now(-7),
        max: laydate.now(),
        istime: true,
        istoday: false,
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#endDate',
        format: 'YYYY-MM-DD',
        min: laydate.now(-7),
        max: laydate.now(),
        istime: true, //是否开启时间选择
        isclear: true, //是否显示清空
        istoday: true, //是否显示今天
        issure: true, //是否显示确认
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);
}


function queryParams(params) {
    var param = {
        orgCode: $("#orgCode").val(),
        userName: $("#userName").val(),
        startDate: $("#startDate").val(),
        endDate: $("#endDate").val(),
        limit: this.limit, // 页面大小
        offsex: this.offset, // 页码
        pageindex: this.pageNumber,
        pageSize: this.pageSize
    }
    return param;
}


function exportXml(id) {
    if (id != null) {
        window.open("export.do?modelId=" + id);
    } else {
        alert('提示', '请选择一条记录进行修改');
    }
}

function cloasPanal() {
    $('#modelPanel').hide();
}

function openPanal() {
    $('#modelPanel').show();
}

function createModel() {
    window.open("./model/create?name=" + $('#name').val()+"&key="+$('#key').val()+"&description="+$('#description').val());
}

function model(id) {
    if (id != null) {
        window.open("modeler.html?modelId=" + id);
    } else {
        alert('提示', '请选择一条记录进行修改');
    }
}



function depoly(id) {
    $.ajax({
        type:'get',
        url:'./deploy',
        data:{
            modelId:id
        },
        success:function(result) {
            alert(result.msg);
        },
        error:function(error) {
            alert("访问服务器失败");
        }
    })
}