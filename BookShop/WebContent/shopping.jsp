<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>shopping</title>
<link href="css/shopping.css" rel="stylesheet">
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/shopping.js"></script>
</head>

<body onload="loadBook()">
	<div class="fix"><!--只是为了好看一点-->
		<nav><!--导航栏-->
			<div class="title"><a href="shopping.jsp">图书购物</a></div>
			<c:if test="${user != null }">
				<div class="welcome">欢迎您：${user }</div>
			</c:if>
			<ul class="nav">
				<c:choose>
					<c:when test="${status == '1' }">
						<li><a href="ExitServlet">退出</a></li>
						<li id="mycart"><a href="cart.jsp">我的购物车</a></li>							
					</c:when>
					<c:when test="${status == '0'|| status == NULL }">
						<li id="reg"><a>注册</a></li>
						<li id="log"><a>登录</a></li>
					</c:when>
				</c:choose>						
				<li></li>
			</ul>
		</nav>
	</div>
			
	<div class="container"><!--是所有内容的容器-->
		<div class="header"></div><!--顶部图片显示-->	
		<div>
			<div class="choose">
				<ul>
					<li id="all" class="active"><a>全部</a></li>
					<li id="java"><a href="#">java</a></li>
					<li id="c"><a href="#">C</a></li>
				</ul>
			</div>
			<div class="book">
				<div class="book-title">
						<div class="sm">编号</div>
						<div class="md">商品名称</div>
						<div class="sm">作者</div>
						<div class="md">出版社</div>
						<div class="sm">单价</div>
						<div class="sm">购买数量</div>
						<div class="sm">库存</div>
						<div class="sm">操作</div>
				</div>
				<%--从session中获取书本信息 --%>
				<c:if test="${bookList == null}">
					<center>没有书本上架啊！！！</center>
				</c:if>
				<c:forEach items="${bookList }" var="book">
					<c:if test="${book.getType() == 0 }">
						<div class="java-book">
							<div class="block">
								<div class="sm">${book.id }</div>
								<div class="md">${book.name}</div>
								<div class="sm">${book.author }</div>
								<div class="md">${book.press }</div>
								<div class="sm">${book.price }</div>
								<div class="sm"><input  class="input_num" type="number" min="0" max="${book.inventory }" value="0">本</div>
								<div class="sm">${book.inventory }</div>
								<div class="sm toCart">加入购物车</div>							
							</div>
						</div>						
					</c:if>
					<c:if test="${book.getType() == 1 }">
						<div class="c-book">
							<div class="block">
								<div class="sm">${book.id }</div>
								<div class="md">${book.name}</div>
								<div class="sm">${book.author }</div>
								<div class="md">${book.press }</div>
								<div class="sm">${book.price }</div>
								<div class="sm"><input  class="input_num" type="number" min="0" max="${book.inventory }" value="0">本</div>
								<div class="sm">${book.inventory }</div>
								<div class="sm toCart">加入购物车</div>							
							</div>				
						</div>					
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>

	<!--注册窗口-->
	<div class="modal-cover fade-in" id="modal1">
		<div class="modal" id="hide1">
			<div class="window">
				<h1>Sign up</h1><span class="close" id="close_1">×</span>
				<form class="form" id="form1" action="" method="post">
						<label class="form-font form-control ">USERNAME</label>
						<input type="text" class="form-control form-input" name="name" id="name" maxlength="10"><br>
						<label class="form-font form-control" for="password">PASSWORD</label>
						<input type="password" class="form-control form-input" name="password" id="password" maxlength="16"><br>
						<button type="reset" class="form-button one">重置</button>
						<button type="button" class="form-button two" id="ok">确定</button>
				</form>	
			</div>
			<span id="r_tip" style="display: none; color: red">用户名已被注册！</span>
		</div>
		<div class="sucess" id="sucess">注册成功,<span id="time"></span>s后可以进行登录....</div>
	</div>
	<!--登录窗口 -->
	<div class="modal-cover fade-in" id="modal2">
		<div class="modal" id="hide2">
			<div class="window">
				<h1>Login in</h1><span class="close" id="close_2">×</span>
				<form class="form" id="form2" action="" method="post">
						<label class="form-font form-control ">USERNAME</label>
						<input type="text" class="form-control form-input" name="username" id="username" maxlength="10"><br>
						<label class="form-font form-control" for="password">PASSWORD</label>
						<input type="password" class="form-control form-input" name="userpass" id="userpass" maxlength="16"><br>
						<button type="reset" class="form-button one">重置</button>
						<button type="button" class="form-button two" id="submit">登录</button>
				</form>	
				<span id="tip" style="display: none; color: red">用户名或密码错误</span>
			</div>
		</div>
	</div>
	<footer>@Xiaolei 2017</footer>
<script>
	function loadBook() {
		$.post("LoadBooks",{status : "sucess"}, function(data, status) {
			console.log("书本被成功加载");
		});	
	}

	/*全选按钮，Java按钮，c按钮*/
	$(function() {
		$('#all').click(function() {
			$(this).addClass('active');
			$('#java').removeClass('active');
			$('#c').removeClass('active');
			$('.java-book').css('display','block');
			$('.c-book').css('display','block');
		});
		$('#java').click(function() {
			$(this).addClass('active');
			$('#all').removeClass('active');
			$('#c').removeClass('active');
			$('.c-book').css('display','none');
			$('.java-book').css('display','block');
		});
		$('#c').click(function() {
			$(this).addClass('active');
			$('#all').removeClass('active');
			$('#java').removeClass('active');
			$('.c-book').css('display','block');
			$('.java-book').css('display','none');
		});
	});	
	//添加入购物车
	$(function() {
		var $cart = $(".toCart");
		$cart.each(function() {
			$(this).bind("click", function(){
				if(${status == 1}) {
					var $parent = $(this).parent();
					var id = $parent.children(":eq(0)").text();
					var name = $parent.children(":eq(1)").text();
					var author = $parent.children(":eq(2)").text();
					var press = $parent.children(":eq(3)").text();
					var price = parseFloat($parent.children(":eq(4)").text()).toFixed(2);
					var num = parseInt($parent.children(":eq(5)").children().val());
					if(num == 0){
						alert("你还没有选择数量！");
						return;
					}
					var param = "id="+id + "&name="+name + "&author="+author 
								+ "&press="+press +"&price="+price + "&num="+num;
					param = encodeURI(encodeURI(param));
					$.post("BookCart", param, function(data, status) {
						if(data == "sucess") {
							alert("添加成功！");
						}
					});
				} else {
					$("#modal2").css("display","block");
				}				
			});
		});
	});
</script>
</body>
</html>
