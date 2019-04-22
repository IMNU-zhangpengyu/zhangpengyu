/**
 * 
 */
function changeVerifyCode(img){
	img.src="../Kaptcha?" + Math.floor(Math.random() * 100);
}

function getQueryString(name){
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!= null){
		//我见过最坑的方法名
		return decodeURIComponent(r[2]);
	}
	return '';
}