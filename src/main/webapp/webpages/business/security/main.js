$("#signin_submit").click(function() {
	$("#showDiv").append(
			"<p>测试返回结果区域</p>");
	var loginObj = new Object();
	loginObj.userName = "test";
	loginObj.passWord = "test";
	var loginJson = JSON.stringify(loginObj); // 将JSON对象转化为JSON字符
	$.ajax({
		url : "api/login/",
		headers : {
			"X-Auth-Token" : "open-sesame",
			"Content-Type" : "application/json"
		},
		contentType : 'text/html; charset=UTF-8',
		type : "post",
		data : loginJson,
		dataType : "json",
		success : function(data) {
//			$("#showDiv").empty();
			getMenu(data);
			alert(JSON.stringify(data));
			$("#showDiv").append(
					"<p> 名称：" + data.userName + "，密码："
							+ data.passWord+ "，机构" + data.orgCode
							+ "，用户ID:" + data.userId + "</p>");
			$("#showDiv").append(JSON.stringify(data));
//			alert(JSON.stringify(data));
			
			//单个值 Read.htm?username=baobao;
			//多全值 Read.htm?username=baobao&sex=male;
			url = "main.htm?loginvo="+escape(JSON.stringify(data));
			location.href=url;
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {  //#3这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus); // paser error;
        }
	})
});

function getMenu(data){
	alert(data);
	$.ajax({
		url : "api/menu/",
		headers : {
			"X-Auth-Token" : "open-sesame",
			"Content-Type" : "application/json"
		},
		contentType : 'text/html; charset=UTF-8',
		type : "post",
		data : JSON.stringify(data),
		success : function(data) {
//			$("#showDiv").empty();
			for (var t = 0; t < data.length; t++) {
				$("#showDiv").append(
						"<p> id：" + data[t].id + "，名称："
								+ data[t].name + ",url" + data[t].url
								+ "，排序：" + data[t].orderNum + "，是否默认："
								+ data[t].isDefault + "，父ID：" + data[t].parentId
								+ "，标题：" + data[t].title + "</p>");
			}
		},
		dataType : "json",
		error : function(data) {
			alert("出错了");
		}
	});
}