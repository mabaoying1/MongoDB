<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>接口日志管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $("#inputForm").submit();
			  return true;
		  }
	
		  return false;
		}
		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
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
			
		});
	</script>
</head>
<body>
		<form:form id="inputForm" modelAttribute="hpIfaceLog" action="${ctx}/iface/hpIfaceLog/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		   <tr>
			   <td class="width-15 active"><label class="pull-right">商户号：</label></td>
			   <td class="width-35">
				   <form:input path="merId" htmlEscape="false" maxlength="100" class="form-control required"/>
			   </td>
			   <td class="width-15 active"><label class="pull-right">功能码：</label></td>
			   <td class="width-35">
				   <form:input path="funcId" htmlEscape="false" maxlength="100" class="form-control "/>
			   </td>
		   </tr>
		   <tr>
			   <td class="width-15 active"><label class="pull-right">接受时间：</label></td>
			   <td class="width-35"><input id="validDate" name="validDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm" value="<fmt:formatDate value="${hpIfaceLog.createTime}" pattern="yyyy-MM-dd"/>"/></td>
			   <td class="width-15 active"><label class="pull-right">返回错误消息：</label></td>
			   <td class="width-35" >
				   <form:input path="errmsg" htmlEscape="false" maxlength="50" class="form-control "/>
			   </td>
		   </tr>
		   <tr>
			   <td class="width-15 active"><label class="pull-right">发送原文报告：</label></td>
			   <td class="width-35" colspan="3">
				   <c:out value="${hpIfaceLog.senddata}" escapeXml="true"/>
			   </td>
		   </tr>
		   <tr>
			   <td class="width-15 active"><label class="pull-right">返回报文：</label></td>
			   <td class="width-35" colspan="3">
				   <c:out value="${hpIfaceLog.retdata}" escapeXml="true"/>
			   </td>
		   </tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>