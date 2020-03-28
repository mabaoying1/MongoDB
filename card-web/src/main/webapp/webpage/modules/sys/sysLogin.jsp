<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
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
		<title>${fns:getConfig('productName')}</title>
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


					$("#issso").prop("checked",false);
//					$("#idNum").hide();
//					$("#idNumTitle").hide();
					var div = document.getElementById("login_boder");
					$("#issso").change(function () {
						if ($("#issso").is(':checked')) {
							$("#idNum").show();
							$("#idNumTitle").show();
							div.style.cssText = "background-size:403px 350px;"
						}
						else {
							$("#idNum").hide();
							$("#idNumTitle").hide();
							div.style.cssText = 'background-size:403px 302px;'
						}
					})
				});

				$("#ischange").change(function() {
					alert("checked");
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
			$("#inputForm").validate({
				rules: {
				loginName: {remote: "${ctx}/sys/user/validateLoginName"},
				mobile: {remote: "${ctx}/sys/user/validateMobile"},
				randomCode: {

					  remote:{

						   url:"${ctx}/sys/register/validateMobileCode", 
	
						  data:{
					       mobile:function(){
					          return $("#tel").val();
					          }
			          		} 

						}


					}
			},
				messages: {
					confirmNewPassword: {equalTo: "输入与上面相同的密码"},
					ck1: {required: "必须接受用户协议."},
					loginName: {remote: "此用户名已经被注册!", required: "用户名不能为空."},
					mobile:{remote: "此手机号已经被注册!", required: "手机号不能为空."},
					randomCode:{remote: "验证码不正确!", required: "验证码不能为空."}
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});

			$("#resetForm").validate({
				rules: {
				mobile: {remote: "${ctx}/sys/user/validateMobileExist"}
			},
				messages: {
					mobile:{remote: "此手机号未注册!", required: "手机号不能为空."}
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			// 手机号码验证
			jQuery.validator.addMethod("isMobile", function(value, element) {
			    var length = value.length;
			    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
			    return (length == 11 && mobile.test(value));
			}, "请正确填写您的手机号码");



			$('#sendPassBtn').click(function () { 
				if($("#tel_resetpass").val()=="" || $("#tel_resetpass-error").text()!=""){
					top.layer.alert("请输入有效的注册手机号码！", {icon: 0});//讨厌的白色字体问题
					return;

				}
				$("#sendPassBtn").attr("disabled", true); 
				$.get("${ctx}/sys/user/resetPassword?mobile="+$("#tel_resetpass").val(),function(data){
						if(data.success == false){
							top.layer.alert(data.msg, {icon: 0});//讨厌的白色字体问题
							//alert(data.msg);
							$("#sendPassBtn").html("重新发送").removeAttr("disabled"); 
							clearInterval(countdown); 

						}

					});
				var count = 60; 
				var countdown = setInterval(CountDown, 1000); 
				function CountDown() { 
					$("#sendPassBtn").attr("disabled", true); 
					$("#sendPassBtn").html("等待 " + count + "秒!"); 
					if (count == 0) { 
						$("#sendPassBtn").html("重新发送").removeAttr("disabled"); 
						clearInterval(countdown); 
					} 
					count--; 
				}

				
			}) ;
			

			$('#sendCodeBtn').click(function () { 
				if($("#tel").val()=="" || $("#tel-error").text()!=""){
					top.layer.alert("请输入有效的注册手机号码！", {icon: 0});//讨厌的白色字体问题
					return;

				}
				$("#sendCodeBtn").attr("disabled", true); 
				$.get("${ctx}/sys/register/getRegisterCode?mobile="+$("#tel").val(),function(data){
						if(data.success == false){
							//top.layer.alert(data.msg, {icon: 0});讨厌的白色字体问题
							alert(data.msg);
							$("#sendCodeBtn").html("重新发送").removeAttr("disabled"); 
							clearInterval(countdown); 

						}

					});
				var count = 60; 
				var countdown = setInterval(CountDown, 1000); 
				function CountDown() { 
					$("#sendCodeBtn").attr("disabled", true); 
					$("#sendCodeBtn").html("等待 " + count + "秒!"); 
					if (count == 0) { 
						$("#sendCodeBtn").html("重新发送").removeAttr("disabled"); 
						clearInterval(countdown); 
					} 
					count--; 
				}

				
			}) ;
			
		});

	
		
		

		
	</script>

</head>

<body class="login">
<img src="${ctxStatic}/images/background.png" style="position:absolute; width:100%; height:100%; left:0; top:0; z-index:-1;"/>
<div class="login_center">
	<div style="width:390px;float:left;position:relative;bottom:0;"><img src="${ctxStatic}/images/people.png" style="width:100%; height:100%; left:0; top:0;margin-top:70px"/> </div>
	<div class="login_m" style="float:right">
		<div>
			<div class="login_logo"><img src="${ctxStatic }/common/login/images/login/logo.png" width="230" height="50"></div>
			<sys:message content="${message}"/>
			<div id="login_boder" class="login_boder">
				<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
					<div class="login_padding">
						<h2>用户名</h2>
						<label>
							<input type="text" id="username" name="username" class="txt_input txt_input2 required" placeholder="用户名" value="${username}">
						</label>
						<h2>密码</h2>
						<label>
							<input type="password" id="password" name="password" class="txt_input required" placeholder="密码" />
						</label>
						<h2 id="idNumTitle" hidden>身份证</h2>
						<label>
							<input type="text" id="idNum" name="idNum" class="txt_input txt_input2" placeholder="身份证" hidden >
						</label>
						<c:if test="${isValidateCodeLogin}">
							<div class="input-group m-b text-muted validateCode">
								<label class="input-label mid" for="validateCode">验证码:</label>
								<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:5px;"/>
							</div>
						</c:if>
						<p class="forgot"><!--<a href="#" data-target="#forgot-box" class="pull-left">忘记密码</a>--></p>
						<div class="rem_sub">
							<div class="rem_sub_l">
								<input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''} />
								<label for="checkbox">记住</label>
								<input type="checkbox" id="issso" name="issso" checked="false" />
								<label for="checkbox">注册统一门户系统</label>
							</div>
							<input type="submit" class="sub_button" name="button" id="button" value="登录" style="opacity: 0.7;">
						</div>
					</div>
				</form>
			</div><!--login_boder end-->
		</div>
		<div style="background: white;position:absolute;left:0;bottom:0;width:100%" >
			<p align="center" style="font-size: 10px">推荐使用IE10及以上,Chrome41及以上,Firefox43及以上</p>
			<p align="center">中山市卫生和计划生育局版权所有  创业软件股份有限公司承建 </p>
		</div>
	</div><!--login_m end-->
</div>
<br />
<br />

<%--<p align="center">  <a href="#" target="_blank" title="居民健康通管理平台">居民健康通管理平台</a></p>--%>


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
	<!-- inline scripts related to this page -->
		<script type="text/javascript">
		$(document).ready(function() {
			 $(document).on('click', '.form-options a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			 });
			});
			
			
			
		</script>
</body>
</html>