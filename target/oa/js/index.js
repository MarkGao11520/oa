changeNav1(2);

function changeNav1(id) {
	var str = '';
	for(var i = 1; i <= 3; i++) {
		if(i == id) {
			if(id == 2) {
				str += '<li id="nt1" class="active">' +
					'<a href="javascript:changeNav2(\'modellist.html\',1)">模型管理</a></li>' +
					'<li id="nt2">' +
					'<a href="javascript:changeNav2(\'activiti_process_define.html\',2)">流程管理</a>' +
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

function changeNav2(url, id) {
	for(var i = 1; i <= 3; i++) {
		if(i == id) {
			$('#nt' + i).addClass('active');
		} else {
			$('#nt' + i).removeClass('active');
		}
	}
	$('#iframe').attr('src', url);
}

function reinitIframe() {
	var iframe = document.getElementById("iframe");
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
		console.log(height);
	} catch(ex) {}
}
window.setInterval("reinitIframe()", 200);