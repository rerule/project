<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>orderList</title>
<style type="text/css">
	html,body {
    margin: 0;
    padding: 0;
    width: 100%;

	}
	.openItems{
		background-color: #e6e1bc;
	}
	a{
		text-decoration: none;
	}
	a:hover{
		cursor:pointer;
		text-decoration:underline;
	}
	a.orderInfo{
		color:blue;
	}
	
	a.delete{
		color:red;
	}
	
	
	.cover{
		width: 100%;
		height: 100%;
		background-color: #292935;
		position: fixed;
		top:0;
		left: 0;
		opacity: .6;
	
	}
	
	.window{
		height: 60%;
		width: 50%;
		position: fixed;
		margin: auto;
		background-color: #ced0cb;
		left: 0;
		right: 0;
		top: 0;
		bottom: 0;
		overflow-y:scroll;
	}

	div.wrap{
	
		margin-top: 50px;
	}
	table.user{
		margin: 0 auto;
	}
	table,tr{
		border-collapse:collapse;
		border: 1px solid #8c8e0a;
		
	}
	tbody tr td{
		border-collapse:collapse;
		border: 1px solid #8c8e0a;
	}
	tbody tr{
		background-color: #a5e0db;
	}
	td{
		text-align: center;
	}
	td.tablehead{
		background-color: #deb56d;
		
	}
	
	
	
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/commons/jquery-1.10.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/commons/json2.js"></script>
</head>
<body>
<h3>欢迎您：${sessionScope.loginname }</h3>
<button><a href="${pageContext.request.contextPath}/order/exit">安全退出</a></button>
	<div class="wrap">
		
<table class="user">
<thead>
	<tr>
		<td class="tablehead" colspan="5">用户订单信息</td>
		<td ><span ><a id="openItems">新增订单</a></span></td>
	</tr>
</thead>
<tbody>
	<tr>
		<td>ID</td>
		<td>订单号</td>
		<td>总价</td>
		<td>日期</td>
		<td>删除</td>
		<td>详细信息</td>
	</tr>
<c:forEach items="${requestScope.orderList }" var="order" varStatus="status">
		<tr>
			<td>${order.id }</td>
			<td>${order.code}</td>
			<td>${order.total}</td>
			<td>
				<fmt:timeZone value="GMT+8:00">
					<fmt:formatDate value="${order.order_date }" type="both" pattern="yyyy/MM/dd HH:mm:ss"/>
				</fmt:timeZone>
			</td>
			<td><a class="delete" href="javascript:void(0)">删除</a></td>
			<td><a class="info" href="javascript:void(0)">查看</a></td>
		</tr>
</c:forEach>



	
</tbody>
</table>
		
	</div>
	<script type="text/javascript">
		$(function(){
			//删除
			$(".delete").on("click",function(){
				var confirm = window.confirm("确定要删除吗");
				if(confirm==true){
					/* var $order_id=$(this).parent().prevAll(":eq(0)").text();//prevAll不能获取第一个,它是倒着数的 */
					var $order_id=$(this).parent().parent().children(":eq(0)").text();
					var jsonObject = {
							"order_id":$order_id
					};
					var jsonStr= JSON.stringify(jsonObject);
					$.post("${pageContext.request.contextPath}/order/delete",{jsonString:jsonStr},function(data){
						alert(data.status);
						window.location.href="${pageContext.request.contextPath}/order/orderList";
					}); 
					 /*  var jsonString= JSON.stringify(jsonObject);
					$.ajax({
						url:"${pageContext.request.contextPath}/order/delete",
						type:"post",
						contentType:"application/json",
						dataType:"json",  
						data:{"jsonString":jsonString}, 
						success:function(data){
							alert("success");
						}	
					}); *///jsonlib不能使用$.ajax只能用post
				}
			});
			//打开article列表
			$("#openItems").on("click",function(){
				$("body").css({"position":"fixed"});
				$("body").append('<div class="cover"></div>');
				$("body").append('<div class="window"><div style="height:20px;"><img id="closeCover" height="20px" src="${pageContext.request.contextPath}/images/close.jpg"/></div> <table style="width:100%;"><tbody id="tableItem"><tr><td>id</td><td>name</td><td>price</td><td>remark</td><td>amount</td><td></td></tr></tbody></table></div>');
				$.ajax({
					async:false,
					type:"post",
					url:"${pageContext.request.contextPath}/article/articleItems",
					contentType:"application/json",
					dataType:"json",
					data:null,
					success:function(data){
						$.each(data,function(){
							var item = '<tr class="item">'
								+'<td d-id="'+this.id+'">'+this.id+'</td>'
								+'<td d-name="'+this.name+'">'+this.name+'</td>'
								+'<td d-price="'+this.price+'">'+this.price+'</td>'
								+'<td d-remark="'+this.remark+'">'+this.remark+'</td>'
								+'<td d-amount="'+this.amount+'">'+this.amount+'</td>'
								+'<td>'+'<a href="javascript:void(0)"><b>-</b></a>'+'<span class="many">0</span>'+'<a href="javascript:void(0)"><b>+</b></a>'+'</td>'
							+'</tr>';
							$("#tableItem").append(item);
							
						});
					}
				});
				//关闭遮罩层
				$("#closeCover").on("click",function(){
					$(".cover").remove();
					$(".window").remove();
				});
				
				$('.window').append('<button type="button"><a class="charge" style="float:right;" href="javascript:void(0)">购买</a></button>');
				
				$('tr.item a b:contains("-")').on('click',function(){
					var a=$(this).parent();
					var number=a.siblings('.many').text();
					if (number==0) {
						a.removeProp('href');
					}else{
						number=number-1;
						a.siblings('.many').text(number);
						if(a.prop('href')==null){
							a.prop('href','javascript:void(0)');
						}
					}
				});
				
				$('tr.item a b:contains("+")').on('click',function(){
					var a=$(this).parent();
					var number=a.siblings('.many').text();
					var amount = a.parent().siblings('[d-amount]');
					if (number==amount) {
						a.removeProp('href');
					}else{
						number=number-0+1;
						a.siblings('.many').text(number);
					}
				});
				
				$('button').on('click','a.charge',function(){
					var incharge = $('td span.many:not(":contains(0)")');
					var arr = new Array();
					
					incharge.each(function(i){
						var item = new Object();
						var number = $(this).text();
						var id = $(this).parent().siblings('[d-id]').attr('d-id');
						item.amount=number;
						item.article_id=id;
						arr.push(item);
					});
					$.ajax({
						url:'${pageContext.request.contextPath}/order/addNewOrder',
						async:false,
						type:'post',
						dataType:'json',
						contentType:'application/json;charset=UTF-8',
						data:JSON.stringify(arr),
						success:function(data){
							alert(data.status);

							$(".cover").remove();
							$(".window").remove();
							window.location.href="${pageContext.request.contextPath}/order/orderList";
						}
					});
				});
				
			});
			
			//查看
			$('.info').on('click',function(){
				var $order_id=$(this).parent().parent().children(":eq(0)").text();
				var obj = new Object();
				console.log($order_id);
				obj.order_id=$order_id;
				$("body").css({"position":"fixed"});
				$("body").append('<div class="cover"></div>');
				$("body").append('<div class="window"><div style="height:20px;"><img id="closeCover" height="20px" src="${pageContext.request.contextPath}/images/close.jpg"/></div></div>');
				$.post('${pageContext.request.contextPath}/order/info',{jsonObj:JSON.stringify(obj)},function(data){
					$('.window').append(data);
				});
				$("#closeCover").on("click",function(){
					$(".cover").remove();
					$(".window").remove();
				});
			});
			
		});
	</script>
</body>

</html>