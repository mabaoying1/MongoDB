<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>持卡人档案管理</title>
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
		<form:form id="inputForm" modelAttribute="hpCardholder" action="${ctx}/hc/hpCardholder/save" method="post" class="form-horizontal">
		<form:hidden path="identityId"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		   		<tr>
		   			 <td  class="width-15 active"><label class="pull-right">姓名:</label></td>
		   			 <td  class="width-35" ><form:input path="name" htmlEscape="false" maxlength="30" class="form-control required"/></td>
		   			 <td  class="width-15 active"><label class="pull-right">${fns:getDictLabel(hpCardholder.docuType , 'std_id_type', '居民身份证')}:</label></td>
		   			 <td  class="width-35"><span class="lbl">${hpCardholder.docuId }</span></td>
		   		</tr>
		   		<tr>
		   			 <td  class="width-15 active"><label class="pull-right">性别:</label></td>
		   			 <td  class="width-35" ><form:radiobuttons path="sex" items="${fns:getDictList('sex')}"  cssClass="i-checks required" itemLabel="label" itemValue="value" htmlEscape="false" /></td>
		   			 <td  class="width-15 active"><label class="pull-right">出生日期:</label></td>
		   			 <td  class="width-35"><span class="lbl"><fmt:formatDate value="${hpCardholder.birth }" pattern="yyyy-MM-dd"/></span></td>
		   		</tr>
		   		<tr>
		   			 <td  class="width-15 active"><label class="pull-right">健康档案号:</label></td>
		   			 <td  class="width-35" ><form:input path="healthId" htmlEscape="false" maxlength="30" class="form-control"/></td>
		   			 <td  class="width-15 active"><label class="pull-right">新农合卡号:</label></td>
		   			 <td  class="width-35"><form:input path="newFarmid" htmlEscape="false" maxlength="30" class="form-control"/></td>
		   		</tr>
		   		<tr>
		   			 <td  class="width-15 active"><label class="pull-right">婚姻状况:</label></td>
		   			 <td  class="width-35" ><form:select path="maritalStatus" class="form-control">
					<form:options items="${fns:getDictList('std_marital')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select></td>
		   			 <td  class="width-15 active"><label class="pull-right">民族:</label></td>
		   			 <td  class="width-35"><form:select path="nation" class="form-control">
						 <form:options items="${fns:getDictList('std_nation')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					 </form:select></td>
		   		</tr>
		   		<tr>
		   			 <td  class="width-15 active"><label class="pull-right">职业:</label></td>
		   			 <td  class="width-35" ><form:select path="profession" class="form-control">
					<form:options items="${fns:getDictList('std_profession')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select></td>
		   			 <td  class="width-15 active"><label class="pull-right">文化:</label></td>
		   			 <td  class="width-35"><form:select path="educationLevel" class="form-control">
					<form:options items="${fns:getDictList('std_education')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select></td>
		   		</tr>
		   		<tr>
		   			 <td  class="width-15 active"><label class="pull-right">户籍地址:</label></td>
		   			 <td  class="width-35" ><sys:treeselect id="area" name="area.id" value="${hpCardholder.area.id}" labelName="area.name" labelValue="${hpCardholder.countryname}${hpCardholder.provname}${hpCardholder.cityname}${hpCardholder.countyname}${hpCardholder.townname}${hpCardholder.villagename}"
					title="户籍地址" url="/sys/area/treeData?type=3" cssClass="form-control" notAllowSelectParent="false" isAll="true"/></td>
					<td  class="width-15 active"><label class="pull-right">手机号码:</label></td>
		   			 <td  class="width-35"><form:input path="phone" htmlEscape="false" maxlength="30" class="form-control required"/></td>
		   		</tr>
		   			<tr>
		   			 <td  class="width-15 active"><label class="pull-right">常住地址:</label></td>
		   			 <td  class="width-35" ><sys:treeselect id="area2" name="area2.id" value="${hpCardholder.area2.id}" labelName="area2.name" labelValue="${hpCardholder.countryname2}${hpCardholder.provname2}${hpCardholder.cityname2}${hpCardholder.countyname2}${hpCardholder.townname2}${hpCardholder.villagename2}"
					title="常住地址" url="/sys/area/treeData?type=3" cssClass="form-control required" notAllowSelectParent="false" isAll="true"/></td>
		   			 <td  class="width-15 active"><label class="pull-right">详细地址:</label></td>
		   			 <td  class="width-35"><form:input path="address2" htmlEscape="false" maxlength="150" class="form-control required"/></td>
		   		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>