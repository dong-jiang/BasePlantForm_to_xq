
/** ********************************************************* */

$(document).ready(function() {
			var token = ReadCookie("oauth-token");
			getMenu(token);

		});

function getMenu(token) {
	$.ajax({
				url : "api/menu/",
				headers : {
					"X-Auth-Token" : "open-sesame",
					"Content-Type" : "application/json",
					"oauth-token" : token
				},
				contentType : 'text/html; charset=UTF-8',
				type : "post",
				success : function(data) {
					var li = createTree(data);
//					 alert(JSON.stringify(data));
//					 alert(li);
					$(".nav-list").append(li);
//					$.get("main.html", function(data, status) {
//								$('#divframe').html(data);
//							}, "html");
					$(".active").click(function(event) {
						var current = $(this), href = current.find('a')
								.attr('innerHref'); // 找到链接a中的innerHref的值
						if (href != '') {
							$.get(href, function(data, status) {
										$('#divframe').html(data);
									}, "html");
						}
					});
					$(".submenu li").click(function(event) {
						var current = $(this), href = current.find('a')
								.attr('innerHref'); // 找到链接a中的innerHref的值
						if (href != '') {
							$.get(href, function(data, status) {
										$('#divframe').html(data);
									}, "html");
						}
					});
				},
				dataType : "json",
				error : function(data) {
					alert("出错了");
				}
			});
}

function createTree(jsonsData){
        	var li='';
					if(jsonsData != null){
						var level='1';
            for(var i=0;i<jsonsData.length;i++){
            	if(level>jsonsData[i].level){
            			li += '</ul></li>';
            	}
            	li += '<li';
            	if(jsonsData[i].id=='1'){
            		li += ' class="active"';
            	}
            	li += '><a href="#" innerHref="' + jsonsData[i].url + '"';
            	if(!jsonsData[i].isleaf){
            	  li += ' class="dropdown-toggle"';
            	}
            	li +='>';
            	li += '<i class="' + jsonsData[i].icon + '"></i>';
            	if(!jsonsData[i].isleaf){
            		li += '<span class="menu-text"> ' + jsonsData[i].title + ' </span>';
            		li += '<b class="arrow icon-angle-down"></b>';
            	}else{
            		li += jsonsData[i].title;
            	}
              li += '</a>';
              if(jsonsData[i].isleaf){
              	li += '</li>';
              }
            	if(!jsonsData[i].isleaf){
            		li += '<ul class="submenu">';
            	}
            	level=jsonsData[i].level;
            }
          }
          return li;
        }

/** ************************************************************************** */

