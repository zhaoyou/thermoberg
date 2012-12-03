
var login = {};
login.init = function() {
	login.clientName = $('#clientName');
	login.userName = $('#userName');
	login.password = $('#password');
	$('.btn-primary').click(login.submit);
}


login.submit = function() {
  if (login.clientName.val() == '' || login.userName.val() == '' ||
  		login.password.val() == '') {
  	window.alert('请输入帐号，用户名，密码！');	
  	return;
  }
  $('#myform').submit();
}