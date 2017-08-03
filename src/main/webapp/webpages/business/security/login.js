
$("#signin_submit").click(function() {
	var client_id = $("#client_id").val();
	var client_secret = $("#client_secret").val();
	
	var auth = make_base_auth(client_id, client_secret);
	alert(auth);
//	$("#loginform").attr("action", "api/login");
//	$("#loginform").attr("method", "post");
//	$("#loginform").submit();

//	var loginObj = new Object();
//	loginObj.userName = $("#username").val();
//	loginObj.passWord = $("#password").val();
//	loginObj.grant_type = $("#grant_type").val();
//	var loginJson = JSON.stringify(loginObj); 
	$.ajax({
		url : "api/login",
//		headers : {
//			"X-Auth-Token" : "open-sesame",
//			"Content-Type" : "application/json"
//			"Content-Type" : "application/x-www-form-urlencoded"
//		},
		beforeSend :function(xhr){  
			   xhr.setRequestHeader('Authorization',auth);//设置消息头
			   xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');//设置消息头
			 } ,
		type : "post",
		data : $("#loginform").serialize(),
		dataType : "json",
		success : function(data) {
//			$("#showDiv").empty();
			getMenu(data);
			alert(JSON.stringify(data));
			$("#space").append(
					"<p> 名称：" + data.userName + "，密码："
							+ data.passWord+ "，机构" + data.orgCode
							+ "，用户ID:" + data.userId + "</p>");
			$("#showDiv").append(JSON.stringify(data));
//			alert(JSON.stringify(data));
			
			//单个值 Read.htm?username=baobao;
			//多全值 Read.htm?username=baobao&sex=male;
			url = "main.html?loginvo="+escape(JSON.stringify(data));
			location.href=url;
		},
		error: function(data) {  //#3这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
//            alert(XMLHttpRequest.status);
//            alert(XMLHttpRequest.readyState);
//            alert(textStatus); // paser error;
			alert(JSON.stringify(data));
        }
	})
});

function make_base_auth(user, password) {
	var tok = user + ':' + password;
	var hash = $.base64.atob(tok, true);
	return "Basic " + hash;
}

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