


var ordertrack = {};


ordertrack.init = function() {
	ordertrack.startTime = ordertrack.get('startTime');
	ordertrack.endTime = ordertrack.get('endTime');
	ordertrack.orderNo = ordertrack.get('orderNo');
	ordertrack.receiver = ordertrack.get('receiver');
	ordertrack.prodArea = ordertrack.get('prodArea');
	ordertrack.lotno = ordertrack.get('lotno');
	ordertrack.goodsName = ordertrack.get('goodsName');
	ordertrack.goodsType = ordertrack.get('goodsType');
	ordertrack.oid = ordertrack.get('oid');
	$('.btn-primary').click(ordertrack.query);
}

ordertrack.get = function(id) {
	return document.getElementById(id);
}


// 获取符合条件的订单信息
ordertrack.query = function() {
  var url = 'order.do?ope=queryOrder&startTime=' + ordertrack.startTime.value +
  		'&endTime=' + ordertrack.endTime.value + '&orderNo=' + ordertrack.orderNo.value +
  		'&receiver=' + ordertrack.receiver.value + '&prodArea=' + ordertrack.prodArea.value +
  		'&lotno=' + ordertrack.lotno.value + '&goodsName=' + ordertrack.goodsName.value +
  		'&goodsType=' + ordertrack.goodsType.value + '&oid=' + ordertrack.oid.value + 
  		'&query=1';
  window.location.href = url;
}

// 获取订单的药品的详细信息
ordertrack.getOrderDetail = function(orderId) {
  var url = 'order.do?ope=queryOrderDetail&startTime=' + ordertrack.startTime.value +
  	'&endTime=' + ordertrack.endTime.value + '&orderNo=' + ordertrack.orderNo.value +
  	'&receiver=' + ordertrack.receiver.value + '&prodArea=' + ordertrack.prodArea.value +
	'&lotno=' + ordertrack.lotno.value + '&goodsName=' + ordertrack.goodsName.value +
  	'&goodsType=' + ordertrack.goodsType.value + '&oid=' + ordertrack.oid.value + 
  	'&orderId=' + orderId;
  window.location.href = url;  
}