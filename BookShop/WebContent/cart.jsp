<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的购物车</title>
<link href="css/shopping.css" rel="stylesheet" type="text/css">
<script src="js/jquery-3.1.1.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
</head>

<body onload="load()">
	<div class="fix"><!--只是为了好看一点-->
		<nav><!--导航栏-->
			<div class="title"><a href="shopping.jsp">图书购物</a></div>
			<c:if test="${user != null }">
				<div class="welcome">欢迎您：${user }</div>
			</c:if>			
			<ul class="nav">
				<li><a href="ExitServlet">退出</a></li>
				<li id="mycart"><a href="#">我的购物车</a></li>
			</ul>
		</nav>
	</div>
	<div class="container"><!--是所有内容的容器-->
		<div class="header"></div><!--顶部图片显示-->
		<div>
			<div style="height: 80px;"></div>
			<div class="book"><!--以下部分就是购物车的内容了-->
				<div id="full">
					<div id="allbuy"><p>全部商品</p></div>
					<table class="table">
						<colgroup><!--用于设置表格内每一列的宽度-->
							<col style="width: 5%;">
							<col style="width: 5%">
							<col style="width: 20%;">
							<col style="width: 10%;">
							<col style="width: 20%;">
							<col style="width: 10%;">
							<col style="width: 10%;">
							<col style="width: 10%;">
							<col style="width: 10%;">
						</colgroup>
						<thead>
							<tr>
								<th><input type="checkbox" id="checkAll" name="check">全选</th>
								<th>编号</th>
								<th>商品名称</th>
								<th>作者</th>
								<th>出版社</th>
								<th>单价</th>
								<th>购买数量</th>
								<th>金额</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>		
							<c:forEach items="${cartBooks }" var="cbook">
								<tr>
									<td><input type="checkbox" name="check"></td>
									<td>${cbook.id}</td>
									<td>${cbook.name}</td>
									<td>${cbook.author}</td>
									<td>${cbook.press}</td>
									<td>¥<span>${cbook.price}</span></td>
									<td><input type="number"value="${cbook.buyCount}" class="input_num" disabled="disabled"></td>
									<td>
										<!-- 这里我也不想这么长啊，但是要是加了换行在获取内容时长度会出问题 -->
										<span class="num_color totalPrice" style="font-size: 16px">¥<fmt:formatNumber type="number" value="${cbook.price * cbook.buyCount} " maxFractionDigits="2"/>
										</span>
									</td>
									<td><a href="#" class="delete">删除</a></td>								
								</tr>
							</c:forEach>								
						</tbody>
					</table>
					<div class="account">
						<div id="butt"><button type="button" class="n-button" id="sum">结算</button></div>					
						<div id="total">合计：<span class="num_color">¥0</span></div>
						<div id="choose_num">已选商品 <span class="num_color">0</span> 件</div>
					</div>
				</div>
				<div id="empty">
					<img src="images/cart.png">
					<p>你的购物车是空的...快去加点什么吧</p>
					<p><a href="shopping.jsp" style="color: blue;">点击这里返回购物界面</a></p>
				</div>
			</div>		
		</div>
	</div>
	<footer>@Xiaolei 2017</footer>
<script>
function load() {
	if(${cartBooks == null}){
		$("#full").css("display","none");
		$("#empty").css("display","block");
	}
}
var booknum = 0,total = 0;
/*全选按钮*/
$(function() {
	var $checkAll = $("#checkAll");
	var $check = $('input[name=check]:not(#checkAll)');	
	var $getNum = $("input[type=number");
	var $getTotalPrice = $("tbody .totalPrice");
	$checkAll.click(function() {
		var that = this;
		if((that).checked) {
   			$check.each(function() { 
    			this.checked = true; 
    		}); 
    		for(var i=0; i<$getNum.length; i++) {
    			booknum += parseInt($getNum.eq(i).val());
    		}
			for(var j=0; j<$getTotalPrice.length; j++) {
				var price = $getTotalPrice.eq(j).text();
				total += parseFloat(price.slice(1));				
			}
			$('#choose_num').children().text(booknum);
			$('#total').children().text('¥'+total);
			
			$("#sum").removeClass("n-button").addClass("m-button");
		}else{
			$check.each(function() { 
    			this.checked = false; 
    		}); 
			$('#choose_num').children().text(0);
			$('#total').children().text('¥'+0);
			booknum = 0.0;
			total = 0;
			
			$("#sum").removeClass("m-button").addClass("n-button");
		}
	});		
});
//复选框中单个按钮按下
$(function() {
	var $check = $('input[name=check]:not(#checkAll)');			
	$check.each(function() {
		$(this).bind("click", function(event) {
			if(this.checked) {
				var $parent = $(this).parent().parent();
				var $child1 = $parent.children(":nth-child(7)");
				var $child2 = $parent.children(":nth-child(8)");
				booknum += parseInt($child1.children().val());
				total += parseFloat($child2.children().text().slice(1));
				$('#choose_num').children().text(booknum);
				$('#total').children().text('¥'+total);
				
				$("#sum").removeClass("n-button").addClass("m-button");
				var $ch = $('input[name=check]:not(#checkAll)');
				var select = 0;
				for(var i=0; i<$ch.length; i++) {
					if($ch.eq(i)[0].checked){							
						select ++;
					}
				}
				if(select == $ch.length) {
					$("#checkAll")[0].checked = true;
				}
			} else {
				var $parent = $(this).parent().parent();
				var $child1 = $parent.children(":nth-child(7)");
				var $child2 = $parent.children(":nth-child(8)");
				booknum -= parseInt($child1.children().val());
				total -= parseFloat($child2.children().text().slice(1));
				$('#choose_num').children().text(booknum);
				$('#total').children().text('¥'+total);
				
				var $ch = $('input[name=check]:not(#checkAll)');
				var select = $ch.length;
				for(var i=0; i<$ch.length; i++) {
					if(!$ch.eq(i)[0].checked){							
						select --;
					}
				}
				if(select == 0) {
					$("#checkAll")[0].checked = false;
					$("#sum").removeClass("m-button").addClass("n-button");
				}
			}
		})
	});	
});
//修改购买数量时显示金额变化
$(function() {
	var $num = $('.input_num');
	$num.change(function() {			
		var num = $(this).val();
		var price = parseFloat($(this).parent().prev().children().text());
		$(this).parent().next().children().text( '¥'+num*price );
	})
});
//删除按钮
$(function() {
	var $delete = $(".delete");
	$delete.each(function() {
		$(this).bind('click', function(event) {
			var $parent = $(this).parent().parent();
			var $tbody = $parent.parent();
			var id = $parent.children(":eq(1)").text();
			var param = "id="+id;
			$.post("DeleteBook",param,function(data, status) {
				location.href="cart.jsp";
			});
			$parent.remove();
			if($tbody.children("tr").length == 0) {
				$("#full").css("display","none");
				$("#empty").css("display","block");
			}
		});
	});
	
});
//结算按钮
$(function() {
	var $count = $("#butt");
	$count.bind("click", function(event) {
		$.post("AggregateServlet", {status: "sucess"}, function(data, status){
			alert("结算成功");
			location.href = "cart.jsp";
		})	
	})	
})

</script>
</body>
</html>
