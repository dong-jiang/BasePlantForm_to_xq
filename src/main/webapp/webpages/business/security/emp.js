//post restful
$(addBut).on("click", function() {
	var post_data = {
		empno : 110,
		ename : "RESTful",
		sal : 1.1
	};
	$.ajax({
		url : "api/emp/",
		headers : {
			"X-Auth-Token" : "open-sesame",
			"Content-Type" : "application/json"
		},
		contentType : 'text/html; charset=UTF-8',
		type : "post",
		data : JSON.stringify(post_data),
		success : function(data) {
			$(showDiv).empty();
			$(showDiv).append("<p> 数据增加成功：" + data + "</p>");
		},
		dataType : "json",
		error : function(data) {
			$(showDiv).empty();
			$(showDiv).append("<p>出错了</p>");
			$(showDiv).append("<p>" + data + "</p>");
		}
	})
});

$(editBut).on("click", function() {
	var post_data = {
		empno : 3,
		ename : "PUT_REST",
		sal : 3.1
	};

	$.ajax({
		url : "api/emp/3",
		headers : {
			"X-Auth-Token" : "open-sesame",
			"Content-Type" : "application/json"
		},
		contentType : 'text/html; charset=UTF-8',
		type : "put",
		data : JSON.stringify(post_data),
		success : function(data) {
			$(showDiv).empty();
			$(showDiv).append("<p> 数据更新成功：" + data + "</p>");
		},
		dataType : "json",
		error : function(data) {
			$(showDiv).empty();
			$(showDiv).append("<p>出错了</p><p> 错误信息：" + data + "</p>");
		}
	})
});

$(deleteBut).on("click", function() {

	$.ajax({
		url : "api/emp/110",
		headers : {
			"X-Auth-Token" : "open-sesame",
			"Content-Type" : "application/json"
		},
		contentType : 'text/html; charset=UTF-8',
		method : "delete",
		data : {},
		dataType : "json",
		success : function(data) {
			$(showDiv).empty();
			$(showDiv).append("<p> 数据删除成功：" + data + "</p>");
		},
		error : function(data) {
			$(showDiv).empty();
			$(showDiv).append("<p>出错了</p><p> 错误信息：" + data + "</p>");
		}
	})
});

$(getBut).on(
		"click",
		function() {
			$.ajax({
				url : "api/emp/7900",
				method : "get",
				data : {},
				dataType : "json",
				success : function(data) {

					$(showDiv).empty();
					$(showDiv).append(
							"<p> 编号：" + data.empno + "，名称：" + data.ename
									+ ",工资" + data.sal + ",日期:" + data.hiredate
									+ "</p>");
					console.log(1);
				},
				error : function(data) {
					$(showDiv).empty();
					$(showDiv).append("<p>出错了</p><p> 错误信息：" + data + "</p>");
				}
			})
		});

$(listBut).on(
		"click",
		function() {
			$.ajax({
				url : "api/emp/",
				method : "get",
				dataType : "json",
				success : function(data) {
					$(showDiv).empty();
					for (var t = 0; t < data.length; t++) {
						$(showDiv).append(
								"<p> 编号：" + data[t].empno + "，名称："
										+ data[t].ename + ",工资" + data[t].sal
										+ ",日期:" + data[t].hiredate + "</p>");
					}
				},
				error : function(data) {
					$(showDiv).empty();
					$(showDiv).append("<p>出错了</p><p> 错误信息：" + data + "</p>");
				}
			})
		});

$("#loginBut").click(function() {
	$("#showDiv").append(
			"<p>测试返回结果区域</p>");
	var loginObj = new Object();
	loginObj.userName = "test";
	loginObj.passWord = "test";
	var loginJson = JSON.stringify(loginObj); // 将JSON对象转化为JSON字符
	$.ajax({
		url : "api/login/",
		headers : {
//			"X-Auth-Token" : "open-sesame",
			"Content-Type" : "application/json"
		},
		contentType : 'text/html; charset=UTF-8',
		type : "post",
		data : loginJson,
		dataType : "json",
		success : function(data) {
//			$("#showDiv").empty();
			getMenu(data);
			$("#showDiv").append(
					"<p> 名称：" + data.userName + "，密码："
							+ data.passWord+ "，机构" + data.orgCode
							+ "，用户ID:" + data.userId + "</p>");
			$("#showDiv").append(JSON.stringify(data));
//			alert(JSON.stringify(data));
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {  //#3这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus); // paser error;
        }
	})
});

$("#signin_submit").click(function() {
	var loginObj = new Object();
	loginObj.userName = $("#signin_account").val() ;
	loginObj.passWord =  $("#signin_password").val();
	var loginJson = JSON.stringify(loginObj); // 将JSON对象转化为JSON字符
	$.ajax({
		url : "api/login/",
		headers : {
			"X-Auth-Token" : "open-sesame",
			"Content-Type" : "application/json"
		},
		contentType : 'text/html; charset=UTF-8',
		type : "post",
		async: false,
		data : loginJson,
		dataType : "json",
		success : function(data) {
//			$("#showDiv").empty();
//			getMenu(data);
			$("#signin_account").val(JSON.stringify(data)) ;
//			alert(JSON.stringify(data));
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {  //#3这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus); // paser error;
        }
	})
});

function getMenu(data){
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
			$("#showDiv").append(JSON.stringify(data));
		},
		dataType : "json",
		error : function(data) {
			alert("出错了");
		}
	});
}