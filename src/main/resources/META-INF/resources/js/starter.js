// check for IE and fix reloading and caching bug
var isIE = (navigator.userAgent.match('MSIE') != null || navigator.userAgent.match('Trident') != null)
if (isIE) {
	// https caching problem in IE10+ but edge is working
	//$.ajaxSetup({cache : false});
}