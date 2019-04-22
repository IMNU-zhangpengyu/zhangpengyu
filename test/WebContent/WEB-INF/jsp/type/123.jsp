<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<meta name="keywords" content="passport,SSO">
<meta name="description" content="">
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link type="text/css" rel="stylesheet" href="../css/login.min.css">
</head>
<body>

	<div class="main">
		<div id="app_sdo_login" class="app_sdo_login index">
			<div class="login_wrap">
				<div class="nav">
					<ul id="nav">
						<li class="btn_index cur"><a href="javascript:void(0)"
							id="nav_btn_index" hidefocus="true" name="nav_btn_index">帐号登录</a>
						</li>
						<li class="btn_code2d"><a href="javascript:void(0)"
							id="nav_btn_code2d" hidefocus="true" class="btn_custom"
							name="nav_btn_code2d">二维码登录</a></li>
					</ul>
				</div>
				<!-- login_box -->
				<div class="login_box">
					<div id="login_frame" class="login_frame">
						<form action="${pageContext.request.contextPath}/type/regist.action" method="post" id="form"
							class="login_form" name="form">
							<div class="login_table">
								<!-- 用户信息填写表单 -->
								<div id="tbody_user" class="tbody">
									<!-- 用户名 -->
									<div id="user_name" class="tr">
										<div id="user_name_input" class="cell_input with_icon">
											<span class="td"> <label class="cell_input_notice"
												for="username">账号</label> <input type="text"
												id="username" name="username" tabindex="1"
												class="text username" maxlength="50" autocomplete="off">
											</span>
										</div>
									</div>
									<!--用户名 -->

									<!-- 密码 -->
									<div id="user_pwd" class="tr">
										<div id="user_pwd_input" class="cell_input with_icon">
											<span class="td"> <label class="cell_input_notice"
												for="password">密码</label> <input type="password"
												tabindex="2" id="password" name="password"
												class="text password" maxlength="40">
											</span>
										</div>
									</div>
									<!--密码 -->

									<!-- 邮箱 -->
									<div id="user_name" class="tr">
										<div id="user_name_input" class="cell_input with_icon">
											<span class="td"> <label class="cell_input_notice"
												for="username">邮箱账号</label> <input type="text"
												id="email" name="email" tabindex="1"
												class="text email" maxlength="50" autocomplete="off">
											</span>
										</div>
									</div>
									<!-- 邮箱 -->


								</div>
								<!--用户名 -->
								<!-- 二维码 -->
								<div id="tbody_code2" class="tbody">
									<div id="code2" class="code2">
										<div class="code2d_notice">
											扫描二维码安全登录[暂不开放]
											<div class="code2d_tip">
												<div class="code2d_tip_bg"></div>
											</div>
										</div>
										<div class="code_bg">
											<img src="/themes/simplebootx/Public/img/login/code2d.png"
												width="145" height="145" onerror="errorcodeBg(this);">
										</div>
									</div>
								</div>
								<!--二维码 -->
								<!-- 密码提示 -->
								<span class="form_tips_outer"> <span id="form_tips"
									class="form_tips"></span>
								</span>
								<!--密码提示 -->
								<!-- 表单提交按钮 -->
								<div id="tbody_btn" class="tbody">
									<div id="login_btn" class="tr">
										<div class="login_btn">
											<span class="td"> <input type="submit"
												class="submit_btn" id="submit" tabindex="40" value="登 录">
												<div style="clear: both"></div>
											</span>
										</div>
									</div>
								</div>
								<!--表单提交按钮 -->


								<div class="no_reg">
									<a href="#" class="link"></a>
									<div class="ui_btn ui_btn_gray">
										没有账号<a target="_blank" href="/index.php/user/register/index">立即注册</a>
										| <a target="_blank"
											href="/index.php/user/login/forgot_password">忘记密码</a>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
				<!-- login_box -->
			</div>
		</div>
	</div>
	<!-- footer -->
	<script src="../js/jquery.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			$(".btn_index").click(function() {
				$("#nav li").removeClass("cur");
				$(this).addClass("cur");
				$("#tbody_user").show();
				$("#tbody_code2").hide();
				$("#tbody_btn").show();
			});

			$(".btn_code2d").click(function() {
				$("#nav li").removeClass("cur");
				$(this).addClass("cur");
				$("#tbody_user").hide();
				$("#tbody_code2").show();
				$("#tbody_btn").hide();
			});

			showtip1();
			showtip2();
			showtip3();

			$("#account").on('input', function(e) {
				showtip1();
				return false;
			});

			$("#password").on('input', function(e) {
				showtip2();
				return false;
			});

			$("#verify").on('input', function(e) {
				showtip3();
				return false;
			});

			$("#submit").click(function() {
				var isverify = $('.geetest_success_radar_tip_content').html();
				if (isverify != '验证成功') {
					return false;
				}
				ajaxsubmit();
				return false;
			});

			$(".cell_input_notice").click(function() {
				$(this).next().focus();
			});
		});

		function showtip1() {
			if ($("#account").val() != "") {
				$(".cell_input_notice[for=username]").hide();
			} else {
				$(".cell_input_notice[for=username]").show();
			}
			return false;
		}

		function showtip2() {
			if ($("#password").val() != "") {
				$(".cell_input_notice[for=password]").hide();
			} else {
				$(".cell_input_notice[for=password]").show();
			}
		}

		function showtip3() {
			if ($("#verify").val() != "") {
				$(".cell_input_notice[for=verify]").hide();
			} else {
				$(".cell_input_notice[for=verify]").show();
			}
		}

		function ajaxsubmit() {
			var s1 = $('#verify').val();
			var s2 = $('#account').val();
			var s3 = $('#password').val();
			if (s1 == '' || s2 == '' || s3 == '') {
				alert('输入完整信息');
				return false;
			}
			$.ajax({
				type : "GET",
				url : "/index.php/user/login/dologin",
				dataType : "jsonp",
				jsonp : "callback",
				jsonpCallback : "handler",
				data : $("Form").serialize(),
				success : function(data) {
					if (data.status == 'n') {
						geetResetAjax();
						alert(data.info);
					} else {
						window.location.reload();
					}
				}
			});
		}
	</script>
</body>
</html>