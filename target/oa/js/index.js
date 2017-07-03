//changeNav1(2);
tablist();

$(function(){
    $(".panel-heading").click(function(e){
		/*切换折叠指示图标*/
        $(this).find("span").toggleClass("glyphicon-chevron-down");
        $(this).find("span").toggleClass("glyphicon-chevron-up");
    });
});

function changeNav1(id) {
	var str = '';
	for(var i = 1; i <= 3; i++) {
		if(i == id) {
			if(id == 2) {
				str += '<li id="nt1" class="active">' +
					'<a href="javascript:changeNav2(\'modellist.html\',1)">模型管理</a></li>' +
					'<li id="nt2">' +
					'<a href="3w4">流程管理</a>' +
					'</li>' +
					'<li id="nt3">' +
					'<a href="javascript:changeNav2(\'modellist.html\',3)">条件管理</a>' +
					'</li>';
			}
			$('#nn' + i).addClass('active');
		} else {
			$('#nn' + i).removeClass('active');
		}
	}
	$('#nt').html(str);
}

function changeNav2(url) {
	// for(var i = 1; i <= 3; i++) {
	// 	if(i == id) {
	// 		$('#nt' + i).addClass('active');
	// 	} else {
	// 		$('#nt' + i).removeClass('active');
	// 	}
	// }
	$('#leftMenu').attr('src', url);
}

function tablist() {
	var str = '';
	var array = ['系统','流程'];
	var array1 = [['部门管理','人员管理'],['模型管理','流程管理','条件管理']];
	var array2 = [['#','#'],['javascript:changeNav2(\'modellist.html\')','javascript:changeNav2(\'activiti_process_define.html\')','#'],['#','#']]
	for(var i=0;i<array.length;i++){
        str+= '<div style="background-color: #34352c" class="panel  leftMenu">';
        str+='<div class="panel-heading"  id="collapseListGroupHeading'+i+'" data-toggle="collapse" data-target="#collapseListGroup'+i+ '"role="tab" >'+
            '<h4 class="panel-title">'+array[i]+
            '<span class="glyphicon glyphicon-chevron-up right"></span>'+
            '</h4>'+
            '</div>';
		str +='<div id="collapseListGroup'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="collapseListGroupHeading'+i+'">';
		str +='<ul class="list-group">';
		for(var j = 0;j<array1[i].length;j++){
			str+='<li  class="list-group-item">'+
                '<a class="menu-item-text"  href="'+array2[i][j]+'"><button class="menu-item-left">'+
                '<span >'+array1[i][j]+
                '</span></button></a>'+
                '</li>';
		}
		str+='</ul></div></div>';
	}


	$('#tablist').html(str);
}

function reinitIframe() {
	var iframe = document.getElementById("iframe");
	var leftMenu = document.getElementById("leftMenu");
	try {
        var navHight = document.getElementById("nav").offsetHeight;
        var bodyHight = document.documentElement.clientHeight;
        var bottomHight = document.getElementById("bnav").offsetHeight;
        iframe.style.marginTop = navHight+'px';
        iframe.style.marginBottom = bottomHight+'px';
        // var bHeight = iframe.contentWindow.document.body.scrollHeight;
        // var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
        // var height = Math.max(bHeight, dHeight);
        iframe.height = bodyHight-navHight-bottomHight;
        leftMenu.height = bodyHight-navHight-bottomHight;
		console.log(height);
	} catch(ex) {}
}
window.setInterval("reinitIframe()", 200);