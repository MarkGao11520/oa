var url = 'table_data.json'
var design_id = 1;

$('#table').bootstrapTable({
	dataType: 'json',
	cache: false,
	striped: true, //是否显示行间隔色
	sidePagination: "client", //分页方式：client客户端分页，server服务端分页（*）
	url: url,
	toolbar: '#query',
	//				height: $(window).height() - 110,
	//				width: $(window).width(),
	showColumns: true,
	pagination: true,
	//      queryParams : queryParams,
	minimumCountColumns: 2,
	async: false,
	pageNumber: 1, //初始化加载第一页，默认第一页
	pageSize: 7, //每页的记录行数（*）
	pageList: [7, 10, 15, 20], //可供选择的每页的行数（*）
	uniqueId: "id", //每一行的唯一标识，一般为主键列
	//      responseHandler: responseHandler,
	columns: [{
			field: '',
			title: '编号',
			formatter: function(value, row, index) {
				return index + 1;
			}
		},
		{
			field: 'PRODEFID',
			title: '流程定义ID',
			align: 'center',
			valign: 'middle',
			sortable: true
		}, {
			field: 'NAME',
			title: '模型名称',
			align: 'center',
			valign: 'middle',
			sortable: true
		}, {
			field: 'DEPLOYIMENTID',
			title: '部署ID',
			align: 'center',
			valign: 'middle',
			sortable: true

		}, {
			field: '',
			title: '流程图片',
			align: 'center',
			valign: 'middle',
			sortable: true,
			formatter: function(value, row, index) {
				return '<img  onclick="bigImg(this)" src = "' + row.IMAGE + '" style= "height:50px;width:50px" >点击查看大图</img>'
			}
		}, {
			field: '',
			title: '操作',
			align: 'center',
			valign: 'middle',
			sortable: true,
			formatter: function(value, row, index) {
				return '<button class="btn btn-warning" onclick="openDesignPanel()">设计表单</button>&nbsp;&nbsp;<button class="btn btn-warning">启用</button>'
			}
		}
	]
});

function bigImg(obj) {
	$('.winright img').attr("src", obj.src);
	var windowW = $(window).width();
	var windowH = $(window).height();
	var rheight = (obj.height * 750) / obj.width;
	var w = (windowW - 750) / 2;
	if(rheight > windowH) {
		var h = 10;
	} else {
		var h = (windowH - rheight) / 2 - 30;
	}

	var myAlert = document.getElementById("imgBig");
	myAlert.style.display = "block";
	myAlert.style.position = "fixed";
	myAlert.style.top = h + "px";
	myAlert.style.left = w + "px";
	var bgObj = document.getElementById("bgDiv");
	bgObj.style.display = "block";
	bgObj.style.position = "fixed";
	bgObj.style.top = "0";
	bgObj.style.left = "0";
	bgObj.style.background = "#777";
	bgObj.style.filter = "alpha(opacity:40)";
	bgObj.style.zoom = "1";
	bgObj.style.opacity = "0.6";
	bgObj.style.width = "100%";
	bgObj.style.height = "100%";
}

function addNewTr() {
	var str = "";
	str += '<tr id="designid_' + design_id + '" >' +
		'<td><input class="form-control" type="text" placeholder="重要！必须和流程中的任务同名"></td>' +
		'<td><input class="form-control" type="text" placeholder="请输入栏位名"></td>' +
		'<td><select class="form-control"><option>文本输入框</option><option>多文本输入框</option><option>单选框</option><option>多选框</option></select></td>' +
		'<td><select class="form-control"><option>手机号</option><option>邮箱</option><option>qq号</option><option>微信号</option><option>部门选择器</option><option>人员选择器</option><option>地区选择器</option></select></td>' +
		'<td><button class="btn btn-warning" onclick="removeTr(' + design_id + ')">删除<span class="glyphicon glyphicon-remove"></span></button></td></tr>';
	$('#designtbody').append(str);
	design_id++;
}

function removeTr(id) {
	$('#designid_' + id).remove()
}

function closeDesignPanel() {
	$('#desginPanel').hide();
	$('#designtbody').html("");
}

function openDesignPanel() {
	$('#desginPanel').show();
}

function designSubmit() {
	if(document.getElementById("designtbody").childNodes.length < 2) {
		alert("您没有添加任何数据");
	}
}