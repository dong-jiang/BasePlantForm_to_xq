
$("#signin_submit").click(function() {
	var client_id = $("#client_id").val();
	var client_secret = $("#client_secret").val();
	
	var auth = make_base_auth(client_id, client_secret);
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
//			getMenu(data);
			//获取到data.access_token  "oauth-token"
			setCookie("oauth-token",data.access_token,1);
			document.location = "main.html";  
			//单个值 Read.htm?username=baobao;
			//多全值 Read.htm?username=baobao&sex=male;
//			url = "main.html?loginvo="+escape(JSON.stringify(data));
//			location.href=url;
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



/***
* @param {string} cookieName Cookie名称
* @param {string} cookieValue Cookie值
* @param {number} nDays Cookie过期天数
*/
function setCookie(cookieName,cookieValue,nDays) {
    /*当前日期*/
    var today = new Date();
    /*Cookie过期时间*/
    var expire = new Date();
    /*如果未设置nDays参数或者nDays为0，取默认值1*/
    if(nDays == null || nDays == 0) nDays = 1;
    /*计算Cookie过期时间*/
    expire.setTime(today.getTime() + 3600000 * 24 * nDays);
    /*设置Cookie值*/
    document.cookie = cookieName + "=" + escape(cookieValue)
        + ";expires=" + expire.toGMTString();
}