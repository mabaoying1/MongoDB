<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>生成代码</title>
	<meta content="default" name="decorator">
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
			$("#name").focus();
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
<body style="" class="  pace-done" id=""><div class="pace  pace-inactive"><div class="pace-progress" style="width: 100%;" data-progress-text="100%" data-progress="99">
  <div class="pace-progress-inner"></div>
</div>
<div class="pace-activity"></div></div>
	
	<form method="post" modelAttribute="genScheme" action="${ctx}/gen/genTable/genCode" class="form-horizontal" id="inputForm" novalidate="novalidate">
		<input type="hidden" value="${genScheme.id }" name="id" id="id">
		<input type="hidden" value="${genScheme.genTable.id}" name="genTable.id">
		<div class="control-group">
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>代码风格:</label>
			<div class="controls">
				<select class="form-control required" name="category" id="category">
					<c:forEach items="${config.categoryList}" var="config" varStatus="status">
						<option title="${config.description }" <c:if test="${config.value eq genScheme.category}">selected</c:if> value="${config.value }">${config.label}</option>
					</c:forEach>
				</select>
				<span class="help-inline">
					生成结构：{包名}/{模块名}/{分层(dao,entity,service,web)}/{子模块名}/{java类}
				</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>生成包路径:</label>
			<div class="controls">
				<input type="text" maxlength="500" value="com.healthpay.modules" class="required form-control" name="packageName" id="packageName">
				<span class="help-inline">建议模块包：com.healthpay.modules</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>生成模块名:</label>
			<div class="controls">
				<input type="text" maxlength="500" value="" class="required form-control" name="moduleName" id="moduleName">
				<span class="help-inline">可理解为子系统名，例如 sys</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生成子模块名:</label>
			<div class="controls">
				<input type="text" maxlength="500" value="" class="form-control valid" name="subModuleName" id="subModuleName">
				<span class="help-inline">可选，分层下的文件夹，例如 </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>生成功能描述:</label>
			<div class="controls">
				<input type="text" maxlength="500" value="" class="required form-control" name="functionName" id="functionName" >
				<span class="help-inline">将设置到类描述</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>生成功能名:</label>
			<div class="controls">
				<input type="text" maxlength="500" value="" class="required form-control" name="functionNameSimple" id="functionNameSimple">
				<span class="help-inline">用作功能提示，如：保存“某某”成功</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*</font>生成功能作者:</label>
			<div class="controls">
				<input type="text" maxlength="500" value="" class="required form-control" name="functionAuthor" id="functionAuthor">
				<span class="help-inline">功能开发者</span>
			</div>
		</div>
	</form>
</body>
</html>