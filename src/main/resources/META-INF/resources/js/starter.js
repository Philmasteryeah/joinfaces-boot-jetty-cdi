// check for IE and fix reloading and caching bug
var isIE = (navigator.userAgent.match('MSIE') != null || navigator.userAgent.match('Trident') != null)
if (isIE) {


}


///** add active class and stay opened when selected */
//var url = window.location;
//
//// for sidebar menu entirely but not cover treeview
//$('ul.sidebar-menu a').filter(function() {
//    return this.href == url;
//}).parent().siblings().removeClass('active').end().addClass('active');
//
//// for treeview
//$('ul.treeview-menu a').filter(function() {
//    return this.href == url;
//}).parentsUntil(".sidebar-menu > .treeview-menu").siblings().removeClass('active').end().addClass('active');