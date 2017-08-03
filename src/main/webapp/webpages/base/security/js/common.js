/**********************************************
****************cookie设置与获取***************
**********************************************/
/***
* @param {string} cookieName Cookie名称
* @param {string} cookieValue Cookie值
* @param {number} nDays Cookie过期天数
*/
function SetCookie(cookieName,cookieValue,nDays) {
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

/***
*读取指定的Cookie值
*@param {string} cookieName Cookie名称
*/
function ReadCookie(cookieName) {
    var theCookie = "" + document.cookie;
    var ind = theCookie.indexOf(cookieName);
    if(ind==-1 || cookieName=="") return "";
    var ind1 = theCookie.indexOf(';',ind);
    if(ind1==-1) ind1 = theCookie.length;
    /*读取Cookie值*/
    return unescape(theCookie.substring(ind+cookieName.length+1,ind1));
}

/**********************************************
****************URL参数解读***************
**********************************************/
UrlParm = function() { // url参数    
  var data, index;    
  (function init() {    
    data = [];    
    index = {};    
    var u = window.location.search.substr(1);    
    if (u != '') {    
      var parms = decodeURIComponent(u).split('&');    
      for (var i = 0, len = parms.length; i < len; i++) {    
        if (parms[i] != '') {    
          var p = parms[i].split("=");    
          if (p.length == 1 || (p.length == 2 && p[1] == '')) {// p | p=    
            data.push(['']);    
            index[p[0]] = data.length - 1;    
          } else if (typeof(p[0]) == 'undefined' || p[0] == '') { // =c | =    
            data[0] = [p[1]];    
          } else if (typeof(index[p[0]]) == 'undefined') { // c=aaa    
            data.push([p[1]]);    
            index[p[0]] = data.length - 1;    
          } else {// c=aaa    
            data[index[p[0]]].push(p[1]);    
          }    
        }    
      }    
    }    
  })();    
  return {    
    // 获得参数,类似request.getParameter()    
    parm : function(o) { // o: 参数名或者参数次序    
      try {    
        return (typeof(o) == 'number' ? data[o][0] : data[index[o]][0]);    
      } catch (e) {    
      }    
    },    
    //获得参数组, 类似request.getParameterValues()    
    parmValues : function(o) { //  o: 参数名或者参数次序    
      try {    
        return (typeof(o) == 'number' ? data[o] : data[index[o]]);    
      } catch (e) {}    
    },    
    //是否含有parmName参数    
    hasParm : function(parmName) {    
      return typeof(parmName) == 'string' ? typeof(index[parmName]) != 'undefined' : false;    
    },    
    // 获得参数Map ,类似request.getParameterMap()    
    parmMap : function() {    
      var map = {};    
      try {    
        for (var p in index) {  map[p] = data[index[p]];  }    
      } catch (e) {}    
      return map;    
    }    
  }    
}(); 

