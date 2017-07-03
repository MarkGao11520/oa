var url = './userController/getList';
initTable();
getDepartKVList();

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
        queryParams: queryParams,
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
            field: 'login.username',
            title: '用户名',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: 'realname',
            title: '真实姓名',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: 'nikename',
            title: '昵称',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: 'mobile',
            title: '手机号',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: 'email',
            title: 'email',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: 'weixinid',
            title: '微信',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: 'login.depart.name',
            title: '部门名称',
            align: 'center',
            valign: 'middle',
            sortable: true
        },{
            field: '',
            title: '操作',
            align: 'center',
            valign: 'middle',
            sortable: true,
            formatter: function (value, row, index) {
                var str = '';
                str += '<button class="btn btn-success" onclick="openDesignPanel(' + false + ',' + row.uid + ',\'' + row.login.username + '\'' + ',\'' + row.realname + '\'' + ',\'' + row.mobile + '\'' + ')">编辑</button>&nbsp;&nbsp;';
                return str;
            }
        }
        ]
    });
}

function getDepartKVList() {
    $.ajax({
        type:'get',
        url:'./departController/getList',
        success:function(result){
            if(result.code==200){
                var str = '<option value="0">全部</option>';
                var list = result.data;
                for(var i=0;i<list.length;i++){
                    str+='<option value="'+list[i].id+'">'+list[i].name+'</option>'
                }
                $('#depart').html(str);
            }else {
                alert("获取部门列表失败")
            }
        }
    })
}

function queryParams(params) {
    var param = {
        did:$('#depart').val()
    }
    return param;
}

function doQuery() {
    $('#table').bootstrapTable('refresh');    //刷新表格
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

function openDesignPanel(isAdd, id, name, rname, phone) {
    if (isAdd) {
        $('#desigenSumbit').attr('onclick', 'add()');
    }
    else {
        $('#userName').val(name);
        $('#userName').attr('disabled', 'disabled');
        $('#realName').val(rname);
        $('#phone').val(phone==null?'-':phone == ''?'-':phone=='undefined'?'-':phone);

        $('#desigenSumbit').attr('onclick', 'update(' + id + ')');
    }
    $('#desginPanel').show();
}

function check() {
    if ($('#userName').val() == null || $('#userName').val() == "") {
        bootbox.alert("用户名不能为空");
        return false;
    }
    if ($('#realName').val() == null || $('#realName').val() == "") {
        bootbox.alert("真实姓名不能为空");
        return false;
    }
    if ($('#phone').val() == null || $('#phone').val() == "") {
        bootbox.alert("手机号不能为空");
        return false;
    }
    if (isNaN($('#phone').val())) {
        bootbox.alert("手机号必须为11位数字");
        return false;
    }
    if($('#depart').val()==0||$('#depart').val()==null){
        bootbox.alert("请选择一个具体的部门");
        return false;
    }else {
        return true;
    }
}


function add() {
    if (check()) {
        $.ajax({
            type: 'post',
            url: './userController/addUser',
            data: {
                username: $('#userName').val(),
                realname: $('#realName').val(),
                mobile: $('#phone').val(),
                did:$('#depart').val()
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
            url: './userController/updateUser',
            data: {
                uid: id,
                realname: $('#realName').val(),
                mobile: $('#phone').val(),
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
