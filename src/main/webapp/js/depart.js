var url = './departController/getList';
initTable();

function initTable() {
    $('#table').bootstrapTable({
        dataType: 'json',
        cache: false,
        striped: true, //是否显示行间隔色
        sidePagination: "client", //分页方式：client客户端分页，server服务端分页（*）
        url: url,
        toolbar: '#query',
        height: $(window).height() - 110,
        //				width: $(window).width(),
        showColumns: true,
        pagination: true,
              queryParams : queryParams,
        minimumCountColumns: 2,
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 7, //每页的记录行数（*）
        pageList: [7, 10, 15, 20], //可供选择的每页的行数（*）
        // uniqueId: "id", //每一行的唯一标识，一般为主键列
        exportDataType: 'all',
        // responseHandler: responseHandler,
        columns: [{
            field: '',
            title: '编号',
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {
            field: 'name',
            title: '部门名称',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: '',
            title: '操作',
            align: 'center',
            valign: 'middle',
            sortable: true,
            formatter: function (value, row, index) {
                var str = '';
                str += '<button class="btn btn-success" onclick="openDesignPanel(' + false + ',' + row.id + ',\'' + row.name + '\''  + ')">编辑</button>&nbsp;&nbsp;';
                return str;
            }
        }
        ]
    });
}

function queryParams(params) {
    var param = {
        page: this.pageNumber,
        rows: this.pageSize,
    }
    return param;
}

// 用于server 分页，表格数据量太大的话 不想一次查询所有数据，可以使用server分页查询，数据量小的话可以直接把sidePagination: "server"  改为 sidePagination: "client" ，同时去掉responseHandler: responseHandler就可以了，
function responseHandler(res) {
    if (res) {
        return {
            "rows": res.list,
            "total": res.total
        };
    } else {
        return {
            "rows": [],
            "total": 0
        };
    }
}

function closeDesignPanel() {
    $('#desginPanel').hide();
    $('#departName').val("");
}

function openDesignPanel(isAdd, id, name) {
    if (isAdd) {
        $('#desigenSumbit').attr('onclick', 'add()');
    }
    else {
        $('#departName').val(name);
        $('#desigenSumbit').attr('onclick', 'update(' + id + ')');
    }
    $('#desginPanel').show();
}

function check() {
    if ($('#departName').val() == null || $('#departName').val() == "") {
        bootbox.alert("部门名称不能为空");
        return false;
    } else {
        return true;
    }
}



function add() {
    if (check()) {
            $.ajax({
                type: 'post',
                url: './departController/addDepart',
                data: {
                    name: $('#departName').val(),
                },
                success: function (result) {
                    if (result.code == 200) {
                        bootbox.alert("添加成功");
                        $('#table').bootstrapTable('refresh');    //刷新表格
                        closeDesignPanel();
                    } else {
                        bootbox.alert(result.message);
                    }
                },
                error: function (error) {
                    bootbox.alert("访问服务器失败")
                }
            })
        }
}

function update(id) {
    if (check()) {
            $.ajax({
                type: 'post',
                url: './departController/updateDepart',
                data: {
                    id:id,
                    name: $('#departName').val(),
                },
                success: function (result) {
                    if (result.code == 200) {
                        bootbox.alert("编辑成功");
                        $('#table').bootstrapTable('refresh');    //刷新表格
                        closeDesignPanel();
                    } else {
                        bootbox.alert(result.message);
                    }
                },
                error: function (error) {
                    alert("访问服务器失败")
                }
            })
        }
}
