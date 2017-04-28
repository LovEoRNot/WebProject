/**
 * 
 */
 	$(function() {
 		var reg = document.getElementById('reg');
		var log = document.getElementById('log');
		/*清空表单数据*/
		function ClearForm(id) {
			var objId = document.getElementById(id);
		    if (objId == undefined) {
		      return;
		    }
			for(var i=0; i<objId.elements.length; i++){
				objId.elements[i].value = "";
			}
		}
		/*登录按钮*/
		log.onclick = function() {
			document.getElementById('modal2').style.display = 'block';
		}
		/*注册按钮*/
		reg.onclick = function() {	
			document.getElementById('modal1').style.display = 'block';		
		}
		/*关闭按钮*/
		document.getElementById('close_1').onclick = function() {
			ClearForm('form1');
			document.getElementById('modal1').style.display = 'none';
		}
		document.getElementById('close_2').onclick = function() {
			ClearForm('form2');
			document.getElementsByTagName('input').value = '';
			document.getElementById('modal2').style.display = 'none';
		}
 	});

	//登录表单验证
	$(function() {
		$('#submit').click(function() {
			$.post("LoginServlet", $('#form2').serialize(), function(data, status) {				
				if(data == "unvalid") {
					$('#tip').css('display','inline');
				}
				else {
					location.href = "shopping.jsp";
				}
			});
		});	
		$("#form2 button[type='reset']").click(function(){
			$('#tip').css('display','none');
		});	
	});
	//注册页面验证
	$(function() {
		$("#ok").click(function(){
			//这里将序列化的注册表单先解码然后再编码，是为了解决中文乱码问题
			var param = $("#form1").serialize();
			param = decodeURIComponent(param,true);
			param = encodeURI(encodeURI(param));
			$.post("RegisterServlet", param, function(data, status) {
				/*注册成功时的跳转页面
				*这部分代码是后来加的，所以引用了之前的js代码，没换成jQuery格式的*/
				 if(data == "sucess") {
					/*隐藏注册窗口，并打开成功注册窗口*/
					document.getElementById('hide1').style.display = 'none';
					document.getElementById('sucess').style.display = 'block';
					
					document.getElementById('time').innerHTML = 3;
					/*这里需要把time变成全局变量，不然在使用clearInterval来清除计时的时候回不起作用*/
					window.time = setInterval(function() {				
						var time = document.getElementById('time').innerHTML; 
						console.log(time);
						time--;
						if(time>-1) {
							document.getElementById('time').innerHTML = time;		
						}else{
							clearInterval(window.time);
							document.getElementById('modal1').style.display = 'none';
							document.getElementById('hide1').style.display = 'block';
							document.getElementById('sucess').style.display = 'none';
						}			
					},1000);					 
				 }
				 else {
					 $('#r_tip').css('display','inline');
				 }
			});
			$("#form1 button[type='reset']").click(function(){
				$('#r_tip').css('display','none');
			});
		});		
	});