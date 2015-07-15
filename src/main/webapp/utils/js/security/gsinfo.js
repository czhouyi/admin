/** 通用js函数
 *  2013年6月5日 14:28:28 */

var GSINFO = {
	isIE : window.ActiveXObject ? true : false,
	isWin8 : /Windows NT 6.2/.test(navigator.userAgent.toLowerCase()),
	decode : function() {
	    return function(json, k) {
	    	var orig = json;
	        if(json.startWith('{')) {
	        	return eval("(" + json + ")");
	        }
	        var findError = true;
	        var result;
	        var json = CryptUtils.decrypt(json, k);
	        while(findError) {
	            try {
	            	result = eval("(" + json + ")");
	            	findError = false;
	            } catch(e) {
	            	json = json.substring(0, json.length - 1);
	            }
	        }
//	        if(!result) {
//	        	var params = {};
//	        		params.info = orig;
//	        		params.uid = uid || '';
//	        		params.key = key || '';
//	        		params.errorType = 'CryptUtils.decrypt';
//					params.action = 'logFrontError';
//				var arg = serial(params);
//	        	$.post(base + '/front/index.do?' + arg , {}, function(msg) {}, 'html');
//	        }
	        return result;
	    };
	}(),
	data : function(n, v) {
		var t = window.top, c = top['_CA'] || {};
		t['_CA'] = c;
		return v !== undefined ? c[n] = v : c[n];
	},
	remove: function(n) {
		var c = window.top['_CA'];
		if(c && c[n]) delete c[n];
	}
};

/** 序列化对象 */
function serial(o) {
    var ret = '';
    if(o) {
        for(var i in o) {
        	var v = o[i] || '';
            ret += i + '=' + v + '&';
        }
    }
    return ret;
}
String.prototype.startWith=function(str){     
  var reg=new RegExp("^"+str);     
  return reg.test(this);        
}  

String.prototype.endWith=function(str){     
  var reg=new RegExp(str+"$");     
  return reg.test(this);        
}

String.prototype.replaceAll  = function(s1,s2){   
    return this.replace(new RegExp(s1,"gm"),s2);   
} 
function urlencode(url) {
	return escape(url).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g, '%27').replace(/\//g,'%2F');
}
/** 查看dom元素属性 */
function dump(o) {
	var arr = [];
	if(o != null) {
		for(var i in o) {
			arr.push(i);
		}
		arr.sort();
		var table = "<table>";
		for(var i=0; i<arr.length; i++) {
			table += "<tr><td>" + arr[i] + "</td><td>" + o[arr[i]] + "</td></tr>";
		}
		var win = window.open('','dump','');
		win.document.body.innerHTML = table;
	}
}

/** 将对象序列化为查询字符串 */
function getQueryStr(o) {
	var queryStr = "";
	for(var i in o) {
		queryStr += i + "=" + o[i] + "&";
	}
	return queryStr;
}

/** 传入formId/divId,返回标识表单值的对象, clean是否重置表单各类输入选择项 */
function formData(formId, clean, callback) {
	var o = {};
	$("#"+formId + " input[type!='button'][type!='submit'][type!='reset']").each(function() {
		var ref = this.name||this.id;
		var type = this.type;
		if(type == 'hidden' || type == 'text' || type == 'password') {
			o[ref] = $.trim(this.value);
			if(clean) {
				this.value = '';
			}
		} else if(type == 'radio') {
			o[ref] = $("[name=" + ref + "]:checked").val();
			if(clean) {
				$("[name=" + ref + "]:first").attr("checked", true);
			}
		}
	});
	$("#"+formId + " textarea").each(function() {
		var ref = this.name||this.id;
		var self = $("[name=" + ref + "]");
		if(self.size() == 0) {
			self = $("#" + ref);
		}
		o[ref] = $.trim(self.val());
		if(clean) {
			self.val('');
		}
	});
	$("#"+formId + " select").each(function() {
		var ref = this.name||this.id;
		var self = $("[name=" + ref + "] option:selected");
		if(self.size() == 0) {
			self = $("#" + ref + " option:selected");
		}
		o[ref] = self.val();
		if(clean) {
			self.first().attr("selected", true);
		}
	});

	if(callback) {
		callback(o);
	}
	
	if(clean) {//重置表单
		formClean(formId);
	}
	//alert(JSON2.stringify(o));
	return o;
}

function formClean(formId) {
	$("#"+formId + " input").each(function() {
		var ref = this.name||this.id;
		var type = this.type;
		if(type == 'hidden' || type == 'text' || type == 'password') {
			this.value = '';
			var self = $(this);
			if(self.hasClass('right-style')) {// 对formValid验证组件添加的样式还原
				self.removeClass('right-style');
				self.css('border', '1px solid #B5B8C8');
			}
			if(self.hasClass('error-style')) {
				self.removeClass('error-style');				
			}
		} else if(type == 'radio') {
			$("[name=" + ref + "]:first").attr("checked", true);
		}
	});
	$("#"+formId + " textarea").each(function() {
		this.value = '';
	});
	$("#"+formId + " select").each(function() {
		var ref = this.name||this.id;
		$("[name=" + ref + "] option:first").attr("selected", true);
	});
}

/** 给ligerUI的grid设置查询参数 */
function setGridParams(grid, params) {
	if(grid && params) {
		for(var i in params) {
			grid.setParm(i, params[i]);
		}
	}
}

/** ligerui对话框 */
function info(type, content, callback) {
	$.ligerDialog.open({
		type : type,
		content : content,
		isDrag : false,
		isResize : false,
		allowClose : false,
		modal : true,
		callback : callback,
		buttons : [{
			text : '确定',
			onclick : function(item, dialog) {
				dialog.close();
				if(callback){
					callback();
				}
			}
		}]
	});
}
/** artDialog对话框
 * 	succeed
 *  warning
 *  error */
function artInfo(icon, content, closeCallback) {
	art.dialog({
		icon: icon,//plugins\artDialog4\skins\icons目录下定义
//		time: 2,
		fixed: true,
		lock: true,
		resize: false,
		content: content,
		close: closeCallback,
		ok: true
	});
}
/** artDialog确认框 */
function artConfirm(content, okCallback, cancelCallback) {
	art.dialog({
		lock: true,
		icon: 'question',
		content: content,
		ok: okCallback,
		cancel: cancelCallback || true
	});
}
/** artDialog远程Iframe式对话框 */
function artPage(url, title, width, height, closeCallback) {
	return art.dialog.open(url, {
		title: title,
		width: width,
		height: height,
		lock: true,
		resize: false,
		close: closeCallback
	});
}

/** 图片本地预览&无刷新图片上传 (依赖：imagePreview.css、CJL.0.1.min.js、QuickUpload.js、ImagePreviewd.js)
 *  url 上传地址, 
 *  callback 上传完成的回调方法, 
 *  validate 是否校验不能为空, 
 *  w 预览图片的宽度, 
 *  h 预览图片的高度
 *  modify 预览按钮显示文字是否为：修改图片 */
function uploadPreview(url, callback, validate, w, h, modify) {
// 图片预览
// 	var ip = new ImagePreview( $$("idPicFile"), $$("idImg"), {
// 		maxWidth: 200, maxHeight: 200, action: "viewImg.jsp"
// 	});
// 	ip.img.src = ImagePreview.TRANSPARENT;
// 	ip.file.onchange = function(){ ip.preview(); };
	if($$ && $$("idPicFile")) {// 添加预览按钮
		var file = document.createElement("input"),
		ip = new ImagePreview( file, $$("idImg"), {
			maxWidth:	128,
			maxHeight:	128,
// 			action:		"${base}/admin/user/add",
			onErr:		function(){ alert("载入预览出错！"); resetFile(file); },
			onCheck:	function() {//检测
				var img_exts = "jpg|jpeg|gif|bmp|png", paths = "|";
				var value = this.file.value, check = true;
				if ( !value ) {
					check = false; alert("请先选择文件！");
				} else if ( !RegExp( "\.(?:" + img_exts + ")$$", "i" ).test(value) ) {
					check = false; alert("只能上传以下类型：" + img_exts);
				} else if ( paths.indexOf( "|" + value + "|" ) >= 0 ) {
					check = false; alert("已经有相同文件！");
				}
				check || resetFile(this.file);
				return check;
			},
			onShow: function() {
				if(validate) {//是否验证不能为空
				}
				var file = $$("img"), fu = new QuickUpload(file, {
					action: url,
					onFinish: function(iframe){
						callback(iframe);
					}
				});
				fu.upload();
				if(w && h) {//再次更改预览的图片显示大小
					var img = $("#idImg").get(0);
					if(img.width >122 || img.height > 122) {
						img.style.width = w + "px";
						img.style.height = h + "px";
						img.style.marginTop = '0';
					}
				}
			}
		});
		file.type = "file"; file.name = "img";
		file.id = "img";
		if(validate) {//是否验证不能为空,是的话,增加formValid验证表达式
		 	file.setAttribute("valid", "required,image[idImg]");
		}
		file.onchange = function(){ ip.preview(); };
		$$("idPicFile").appendChild(file);
		if(w && h) {//更改预览的图片显示大小
			var img = $("#idImg").get(0);
			if(img.width > w) {
				img.style.width = w + "px";				
			}
			if(img.height > h) {
				img.style.height = h + "px";
			}
		}
		if(modify) {
			$("#idPicFile").css('background', 'url('+base+'/framework/plugins/imagePreview/o_modifyfile.png) center no-repeat');
		}

		function resetFile(file){
			file.value = "";//ff chrome safari
			if ( file.value ) {
				if ( $$B.ie ) {//ie
					with(file.parentNode.insertBefore(document.createElement('form'), file)){
						appendChild(file); reset(); removeNode(false);
					}
				} else {//opera
					file.type = "text"; file.type = "file";
				}
			}
		}
	}
}

var aWeek = ['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
/**
 * 日期转换 
 * @param dateStr 日期格式字符 2013-06-12 12:11:20
 * @param format 字符串格式 Y-m-d h:i:s
 * @returns 今天、昨天、星期几、上周、两周前、本月、上月、更早
 */
function dateFormatToString(dateStr,format){
	try {
		var datedangqian = new Date();
		var datetmp = Date.parseDate(dateStr, format);
		
		// 年
		var yeartmp = datedangqian.getFullYear()- datetmp.getFullYear();
		if(yeartmp == 0){
			// 天
			var daytmp = datedangqian.getDayOfYear() - datetmp.getDayOfYear();
			if(daytmp==0){
				return '今天';
			}else if(daytmp == 1){
				return '昨天';
			}else{
				// 周
				var wtmp = datedangqian.getWeekOfYear() - datetmp.getWeekOfYear();
				if(wtmp == 0){
					return aWeek[datetmp.getDay()]; 
				}else if(wtmp == 1){
					return '上周'; 
				}else if(wtmp == 2){
					return '两周前'; 
				}else{
					// 月
					var mtmp = datedangqian.getMonth() - datetmp.getMonth();
					if(mtmp == 0){
						return '本月';
					}else if(mtmp==1){
						return '上月';
					}else{
						return '更早';
					}
				}
			}
		}else{
			return '更早';
		}
		return datetmp; 
	} catch (e) {}
	return '';
}

/**
 * 转换成日期对象
 * @param dateStr 日期字符串 如2013-12-22
 * @param format  格式符 如Y-m-d
 * @returns 日期对象  否则返回 null
 */
function formatToDate(dateStr,format){
	try {
		if(dateStr){
			if(format){
				return Date.parseDate(dateStr, format);
			}else{
				return Date.parseDate(dateStr, 'Y-m-d');
			}
		}
	} catch (e) {
	}
	return null;
}

/**
 * 转换成日期字符串
 * @param date 日期对象
 * @param format 格式符 如Y-m-d
 * @returns 返回日期字符串 否则返回 ''
 */
function formatToString(date,format){
	try {
		if(date){
			if(format){
				return date.format(format);
			}else{
				return date.format('Y-m-d');
			}
		}
	} catch (e) {
	}
	return '';
}

/**
 * 比较两个日期大小
 * @param startDate 开始时间(可以是日期对象或y-m-d格式日期字符串)
 * @param endDate 结束时间(可以是日期对象或y-m-d格式日期字符串)
 * @returns 1结束时间在开始时间之后，0结束时间和开始时间相同，-1结束时间在开始时间之前, null出错
 */
function compareDate(startDate, endDate){
	try {
		var date1 = null;
		var date2 = null;
		if((typeof startDate=='object')&&startDate.constructor == Date){
			date1 = formatToDate(formatToString(startDate, 'Y-m-d'), 'Y-m-d');
		}
		if((typeof endDate=='object')&&endDate.constructor == Date){
			date2 = formatToDate(formatToString(endDate, 'Y-m-d'), 'Y-m-d');
		}
		if((typeof startDate=='string')&&startDate.constructor == String){
			date1 = formatToDate(startDate, 'Y-m-d');
		}
		if((typeof endDate=='string')&&endDate.constructor == String){
			date2 = formatToDate(endDate, 'Y-m-d');
		}
		var result = date2 - date1;
		return result;
	} catch (e) {
	}
	return null;
}

/** 插入html */
function insertHtml(where, el, html){
    where = where.toLowerCase();
    if(el.insertAdjacentHTML){

        switch(where){
            case "beforebegin":
                el.insertAdjacentHTML('BeforeBegin', html);
                return el.previousSibling;
            case "afterbegin":
                el.insertAdjacentHTML('AfterBegin', html);
                return el.firstChild;
            case "beforeend":
                el.insertAdjacentHTML('BeforeEnd', html);
                return el.lastChild;
            case "afterend":
                el.insertAdjacentHTML('AfterEnd', html);
                return el.nextSibling;
        }
        throw 'Illegal insertion point -> "' + where + '"';
    }

    var range = el.ownerDocument.createRange();
    var frag;
    switch(where){
         case "beforebegin":
            range.setStartBefore(el);
            frag = range.createContextualFragment(html);
            el.parentNode.insertBefore(frag, el);
            return el.previousSibling;
         case "afterbegin":
            if(el.firstChild){
                range.setStartBefore(el.firstChild);
                frag = range.createContextualFragment(html);
                el.insertBefore(frag, el.firstChild);
                return el.firstChild;
             }else{
                el.innerHTML = html;
                return el.firstChild;
             }
        case "beforeend":
            if(el.lastChild){
                range.setStartAfter(el.lastChild);
                frag = range.createContextualFragment(html);
                el.appendChild(frag);
                return el.lastChild;
            }else{
                el.innerHTML = html;
                return el.lastChild;
            }
        case "afterend":
            range.setStartAfter(el);
            frag = range.createContextualFragment(html);
            el.parentNode.insertBefore(frag, el.nextSibling);
            return el.nextSibling;
    }
    throw 'Illegal insertion point -> "' + where + '"';
}

jQuery.extend({
	//添加验证身份证号方法
	//返回 boolean
	isIdCard: function(value){
		var idCard = removeAllSpace(value);
		
		if(idCard==""){  
			return false;   
		};
		
		if(validId15(idCard) || validId18(idCard)){
			return true; 
		}else{
			return false;
		}
		function removeAllSpace(str){
			var localString = '';
			for(var index = 0; index<str.length; index++)     
				if(str.charCodeAt(index)!= 32){     
					localString += str.charAt(index);     
				};     
			return localString;     
		};
		//18
		function validId18(str){
			if(str.length != 18) return false;
			iW = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);
			iSum = 0;
			for( i=0;i<17;i++){
				iC = str.charAt(i) ;
				iVal = parseInt(iC);
				iSum += iVal * iW[i];
			}
			iJYM = iSum % 11;
			var sJYM = '';
			if(iJYM == 0) sJYM = "1";
			else if(iJYM == 1 ) sJYM = "0";
			else if(iJYM == 2 ) sJYM = "x";
			else if(iJYM == 3 ) sJYM = "9";
			else if(iJYM == 4 ) sJYM = "8";
			else if(iJYM == 5 ) sJYM = "7";
			else if(iJYM == 6 ) sJYM = "6";
			else if(iJYM == 7 ) sJYM = "5";
			else if(iJYM == 8 ) sJYM = "4";
			else if(iJYM == 9) sJYM = "3";
			else if(iJYM == 10) sJYM = "2";
			var cCheck = str.charAt(17).toLowerCase();
			if( cCheck != sJYM ){
				return false; 
			}
			return true;
		}
		//15  
		function validId15(str){     
			if(str.length != 15) return false;
			str=str+"";     
			for(var i=0;i<str.length;i++){     
				if(str.charAt(i)<'0'||str.charAt(i)>'9'){     
					return false;     
					break;     
				}     
			}     
			var year = str.substr(6,2);     
			var month = str.substr(8,2);     
			var day = str.substr(10,2);     
			var sexBit = str.substr(14);     
		
			if(year<'01'||year >'90')return false;     
			if(month<'01'||month >'12')return false;     
			if(day<'01'||day >'31')return false;
			return true;     
		};
	},
	isIdCardDate15: function(str) {
		var year = str.substr(6,2);
		var month = str.substr(8,2);
		var day = str.substr(10,2);
		if(month<'01'||month >'12')return false;     
		if(day<'01'||day >'31')return false;
		return true;
	},
	isIdCardDate18: function(str) {
		var year = str.substr(8,2);
		var month = str.substr(10,2);
		var day = str.substr(12,2);
		if(month<'01'||month >'12')return false;     
		if(day<'01'||day >'31')return false;
		return true;
	},
	//添加检测密码方法
	//返回 boolean
	isPw: function(str){
		var a = /[a-z]/i,
			b = /[0-9]/;
		//if(a.test(str) && b.test(str) && str.length>=6 && str.length<=32){
		if(a.test(str) && b.test(str)){
			return true;
		}else{
			return false;
		};
	},
	isLoginPw: function(str){
		return /[0-9a-zA-Z\~\!\@\#\$\%\^\&\*\(\)\_\-\+\=\{\}\[\]\|\:\;\<\,\>\.\?]+/.test(str);
	},
	//添加检测手机号码方法
	//返回 boolean
	isMob: function(str){
		return /^1\d{10}$/.test(str)
	},
	//添加检测中文名字方法
	isCName: function(str){
		return /^[\u4e00-\u9fa5]+[\u00b7\.]?[\u4e00-\u9fa5]+$/.test(str)
	},
	isEmail: function(str){
		//return /^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$/.test(str)
		//return /^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{3})?$/.test(str)
		return /^([a-zA-Z0-9]+[-_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,4}$/.test(str)
	}
}); 