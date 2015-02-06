/**
 * 
 * 捕获url参数插件
 * 
 * 创建时间：2014年9月29日 10:03:24
 * 
 * 创建作者：borenbanner
 * 
 * 
 */

(function($) {
	$.getUrlParam = function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	};
})(jQuery);