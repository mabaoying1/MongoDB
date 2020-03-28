<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html style="overflow-x:auto;overflow-y:auto;"><head>
	<title>菜单管理</title>
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
	
	<form:form method="post" modelAttribute="menu" action="${ctx}/gen/genScheme/createMenu" class="form-horizontal" id="inputForm">
		<input type="hidden" value="${menu.id }" name="id" id="id">
		<input type="hidden" value="${gen_table_id}" name="gen_table_id">	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		      <tr>
		         <td class="width-15 active"><label class="pull-right">上级菜单:</label></td>
		         <td class="width-35">
					<sys:treeselect id="menu" name="parent.id" value="${menu.parent.id}" labelName="parent.name" labelValue="${menu.parent.name}"
					title="菜单" url="/sys/menu/treeData" extId="${menu.id}" cssClass="form-control required"/></td>
				</td>
		         <td class="width-15 active"><label class="pull-right"><font color="red">*</font> 名称:</label></td>
		         <td class="width-35"><input type="text" maxlength="50" value="${menu.name }" class="required form-control valid" name="name" id="name"></td>
		      </tr>
		      <tr>
		         <td class="width-15 active"><label class="pull-right">图标:</label></td>
		         <td class="width-35">
		         	<sys:iconselect id="icon" name="icon" value="${menu.icon}"/>
				 </td>
		         <td class="width-15 active"><label class="pull-right">排序:</label></td>
		         <td class="width-35"><form:input path="sort" htmlEscape="false" maxlength="50" class="required digits form-control "/>
					<span class="help-inline">排列顺序，升序。</span></td>
		      </tr>
		    </tbody>
		  </table>
	</form:form>
</body></html>