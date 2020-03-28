<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
		<meta name="description" content="User login page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<script src="${ctxStatic}/jquery/jquery-2.1.1.min.js" type="text/javascript"></script>
		<script src="${ctxStatic}/jquery/jquery-migrate-1.1.1.min.js" type="text/javascript"></script>
		<script src="${ctxStatic}/jquery-validation/1.14.0/jquery.validate.js" type="text/javascript"></script>
		<script src="${ctxStatic}/jquery-validation/1.14.0/localization/messages_zh.min.js" type="text/javascript"></script>
		<link href="${ctxStatic}/bootstrap/3.3.4/css_default/bootstrap.min.css" type="text/css" rel="stylesheet" />
		<script src="${ctxStatic}/bootstrap/3.3.4/js/bootstrap.min.js"  type="text/javascript"></script>
		<link href="${ctxStatic}/awesome/4.4/css/font-awesome.min.css" rel="stylesheet" />
		<!-- jeeplus -->
		<link href="${ctxStatic}/common/jeeplus.css" type="text/css" rel="stylesheet" />
		<script src="${ctxStatic}/common/jeeplus.js" type="text/javascript"></script>
		<link rel="shortcut icon" href="images/favicon.png" type="image/png">
		<!-- text fonts -->
		<link rel="stylesheet" href="${ctxStatic }/common/login/ace-fonts.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="${ctxStatic }/common/login/ace.css" />

		<!-- 引入layer插件 -->
		<script src="${ctxStatic}/layer-v2.3/layer/layer.js"></script>
		<script src="${ctxStatic}/layer-v2.3/layer/laydate/laydate.js"></script>

		<script type="text/javascript" src="${ctxStatic}/encrpt/des.js"></script>
		<!--[if lte IE 9]>
			<link rel="stylesheet" href="../assets/css/ace-part2.css" />
		<![endif]-->
		<link rel="stylesheet" href="${ctxStatic }/common/login/ace-rtl.css" />
		
		<link href="${ctxStatic }/common/login/login.css" rel="stylesheet" type="text/css">
		<title>${fns:getConfig('productName')} 登录</title>
		<style type="text/css">

		.bound{
			background-color: white;
			background-color: rgba(255, 255, 255, 0.95);
			border-radius: 40px;
		}
		</style>
		<script>
			if (window.top !== window.self) {
				window.top.location = window.location;
			}
		</script>
		<script type="text/javascript">
				$(document).ready(function() {
					$("#loginForm").validate({
						rules: {
							validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
						},
						messages: {
							username: {required: "请填写用户名."},password: {required: "请填写密码."},
							validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
						},
						submitHandler: function(form){
							var  enResult = strEnc($("#password").val(),"012C2C9BA925FAF8045B2FD9B02A2664","","");
							$("#password").val(enResult);
							form.submit();
						},
						errorLabelContainer: "#messageBox",
						errorPlacement: function(error, element) {
							error.appendTo($("#loginError").parent());
						} 
					});
				});
//				function formSubmit()
//				{
//					$("#password").val(hex_md5($("#password").val()));
//					document.getElementById("loginForm").submit()
//				}
//				$("#loginForm").submit(function()  {
//					var tmp = hex_md5($("#password").val());
//					$("#txt_passwd").val(tmp);
//					$(this).ajaxSubmit({
//						success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
//							var obj = eval("("+data+")");
//							if(obj.result=="ok")  {
//								window.location.assign(url);
//							}
//							else {
//								$.noty.consumeAlert({layout: 'top', type: 'error', dismissQueue: true,timeout:2000});
//								alert("登录失败,请重试!");
//							}
//						}
//					});
//					return false;
//				});
				// 如果在框架或在对话框中，则弹出提示并跳转到首页
				if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
					alert('未登录或登录超时。请重新登录，谢谢！');
					top.location = "${ctx}";
				}
		</script>
		<script type="text/javascript">
		$(document).ready(function() {
			
		});

	
		
		

		
	</script>

</head>

<body class="login">

<div class="login_m">
	<div class="login_logo"><img src="${ctxStatic }/common/login/images/login/logo.png" width="289" height="32"></div>
	<sys:message content="${message}"/>
	<div class="login_boder">
	<form id="loginForm" class="form-signin" action="${ctx}/sso/ssoRegisterSend" method="post">
		<div class="login_padding">
			<h2>用户名</h2>
			<label>
				<input type="text" id="username" name="username" class="txt_input txt_input2 required" placeholder="用户名">
			</label>
			<h2>密码</h2>
			<label>
				<input type="password" id="password" name="password" class="txt_input required" placeholder="密码" />
			</label>
			<h2>身份证</h2>
			<label>
				<input type="text" id="idNum" name="idNum" class="txt_input required" placeholder="身份证" />
			</label>
			<div class="rem_sub">
				<input type="submit" class="sub_button" name="button" id="button" value="登录" style="opacity: 0.7;">
			</div>
		</div>
	</form>
	</div><!--login_boder end-->
</div><!--login_m end-->

<br />
<br />

<%--<p align="center">  <a href="#" target="_blank" title="居民健康通管理平台">居民健康通管理平台</a></p>--%>
<p align="center" style="font-size: 10px">推荐使用IE10及以上,Chrome41及以上,Firefox43及以上</p>

<script type="text/javascript">
			window.jQuery || document.write("<script src='../assets/js/jquery.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='../assets/js/jquery1x.js'>"+"<"+"/script>");
</script>
<![endif]-->

		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='../assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>
		<style type="text/css">
		/* Validation */

			label.error {
			    color: #cc5965;
			    display: inline-block;
			    margin-left: 5px;
			}
			
			.form-control.error {
			    border: 1px dotted #cc5965;
			}
		</style>
</body>
</html>