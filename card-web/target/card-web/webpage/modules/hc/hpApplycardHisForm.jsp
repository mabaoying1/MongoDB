<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>健康e卡历史记录管理</title>
	<meta name="decorator" content="default"/>
<style type="text/css">
.btn-center{text-align: center;padding-top: 12px;}
.ibox-title{border-width: 0;}
</style>
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
			
			var listurl = "${ctx}/hc/hpApplycardHis/";
			$("#btn-back").click(function(event) {
				window.location.href = listurl ;
			});
			
		});
	</script>
</head>
<body>
   <div class="wrapper wrapper-content">
	  <div class="ibox">
		<div class="ibox-title">
		<h5>开卡申请历史详情</h5>
	    </div>
		<form:form id="inputForm" modelAttribute="hpApplycardHis" action="${ctx}/hc/hpApplycardHis/save" method="post" class="form-horizontal">
		<form:hidden path="pkid"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		      <tr>
		         <td  class="width-13 active">	<label class="pull-right"><font color="red">*</font>姓名:</label></td>
		         <td class="width-21" ><form:input path="name" htmlEscape="false" maxlength="30" class="form-control required"/></td>
		         <td  class="width-13 active">	<label class="pull-right"><font color="red">*</font>性别:</label></td>
		         <td class="width-20" >
					<form:select path="sex" class="form-control required">
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				 </td>
				<td  class="width-13 active">	<label class="pull-right"><font color="red">*</font>生日:</label></td>
		         <td  class="width-20" >
		         <input id="birth" name="birth" type="text" maxlength="20" class="laydate-icon form-control layer-date required"
				 value="<fmt:formatDate value="${hpApplycardHis.birth}" pattern="yyyy-MM-dd"/>"/>
				</td>
		      </tr>
		      <tr>
		         <td  class="width-13 active">	<label class="pull-right"><font color="red">*</font>身份证号码:</label></td>
		         <td class="width-21" ><form:input path="docuId" htmlEscape="false" maxlength="30" class="form-control required"/></td>
		         <td  class="width-13 active">	<label class="pull-right"><font color="red">*</font>其他证件类别:</label></td>
		         <td class="width-20" >
					<form:select path="docuType" class="form-control required">
					<form:options items="${fns:getDictList('std_id_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				 </td>
		         <td  class="width-13 active">	<label class="pull-right"><font color="red">*</font>证件号码:</label></td>
		         <td class="width-20" ><form:input path="docuId" htmlEscape="false" maxlength="30" class="form-control required"/></td>
		      </tr>
		      <tr>
		      	 <td  class="width-13 active">	<label class="pull-right"><font color="red">*</font>手机号码:</label></td>
		         <td class="width-21" ><form:input path="phone" htmlEscape="false" maxlength="20" class="form-control required"/></td>
		         <td  class="width-13 active">	<label class="pull-right">新农合证（卡）号:</label></td>
		         <td class="width-20" ><form:input path="newFarmid" htmlEscape="false" maxlength="30" class="form-control"/></td>
		         <td  class="width-13 active">	<label class="pull-right">健康档案编号:</label></td>
		         <td class="width-20" ><form:input path="healthId" htmlEscape="false" maxlength="30" class="form-control"/></td>
		      </tr>
		       <tr>
		         <td  class="width-13 active">	<label class="pull-right"><font color="red">*</font>民族:</label></td>
		         <td class="width-21" >
					<form:select path="nation" class="form-control required">
					<form:options items="${fns:getDictList('std_nation')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				 </td>
		         <td  class="width-13 active">	<label class="pull-right">婚姻:</label></td>
		         <td class="width-20" >
					<form:select path="maritalStatus" class="form-control">
					<form:options items="${fns:getDictList('std_marital')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				 </td>
				 <td  class="width-13 active">	<label class="pull-right">职业:</label></td>
		         <td class="width-20" >
					<form:select path="profession" class="form-control">
					<form:options items="${fns:getDictList('std_profession')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				 </td>
		      </tr>
		      <tr>
				 <td class="width-13 active"><label class="pull-right">发卡机构:</label></td>
		      	 <td class="width-21"><sys:treeselect id="office" name="office.id" value="${hpApplycardHis.office.id}" labelName="office.name" labelValue="${hpApplycardHis.organizationName}"
						title="发卡机构" url="/sys/office/treeData" cssClass="form-control required"/></td>
				 <td  class="width-13 active">	<label class="pull-right">医疗支付方式:</label></td>
		         <td class="width-20" >
					<form:select path="paytype" class="form-control">
					<form:options items="${fns:getDictList('std_paytype')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				 </td>
				 <td  class="width-13 active">	<label class="pull-right">文化程度:</label></td>
		         <td class="width-20" >
					<form:select path="educationLevel" class="form-control">
					<form:options items="${fns:getDictList('std_education')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				 </td>
		      </tr>
		      <tr>
		         <td  class="width-13 active">	<label class="pull-right">联系人姓名:</label></td>
		         <td class="width-21" ><form:input path="attnName" htmlEscape="false" maxlength="30" class="form-control required"/></td>
		         <td class="width-13 active">	<label class="pull-right">联系人手机:</label></td>
		         <td class="width-20" ><form:input path="attnPhone" htmlEscape="false" maxlength="20" class="form-control required"/></td>
				 <td  class="width-13 active">	<label class="pull-right">联系人关系:</label></td>
		         <td class="width-20" >
					<form:select path="attnRela" class="form-control">
					<form:options items="${fns:getDictList('std_rela')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				 </td>
		      </tr>
		       <tr>
		         <td class="width-13 active"><label class="pull-right">户籍地址:</label></td>
		         <td class="width-21"><sys:treeselect id="area" name="area.id" value="${hpApplycardHis.area.id}" labelName="area.name" labelValue="${hpApplycardHis.countryname}${hpApplycardHis.provname}${hpApplycardHis.cityname}${hpApplycardHis.countyname}${hpApplycardHis.townname}${hpApplycardHis.villagename}"
					title="区域" url="/sys/area/treeData" cssClass="form-control" notAllowSelectParent="false"/></td>
		         <td class="width-13 active"><label class="pull-right">居住地址:</label></td>
		         <td class="width-20"><sys:treeselect id="area1" name="area1.id" value="${hpApplycardHis.area.id}" labelName="area.name" labelValue="${hpApplycardHis.countryname2}${hpApplycardHis.provname2}${hpApplycardHis.cityname2}${hpApplycardHis.countyname2}${hpApplycardHis.townname2}${hpApplycardHis.villagename2}"
					title="区域" url="/sys/area/treeData" cssClass="form-control" notAllowSelectParent="false"/></td>
				   <td class="width-13 active"><label class="pull-right">详细地址:</label></td>
		         <td colspan="2"><form:input path="address2" htmlEscape="false" maxlength="50" class="form-control"/></td>
		      </tr>
		       <tr>
		         <td class="width-13 active">	<label class="pull-right"><font color="red">*</font>身份证（正面）：</label></td>
		         <td class="width-21"><form:hidden id="nameImage" path="url0" htmlEscape="false" maxlength="255" class="input-xlarge"/>
						<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/></td>
		          <td class="width-13 active">	<label class="pull-right"><font color="red">*</font>身份证（反面）：</label></td>
		         <td class="width-20"><form:hidden id="nameImage1" path="url1" htmlEscape="false" maxlength="255" class="input-xlarge"/>
						<sys:ckfinder input="nameImage1" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/></td>
		      </tr>
		      <tr>
		        
		      </tr>
		      <tr>
		         <td class="width-13 active">	<label class="pull-right">拒绝原因：</label></td>
		         <td colspan="5"><form:textarea path="reason" htmlEscape="false" rows="3" maxlength="300" class="form-control"/></td>
		      </tr>
		 	</tbody>
		</table>
	</form:form>
	<div class="layui-layer-btn btn-center">
		<a id="btn-back" class="layui-layer-btn1">返回列表</a>
	</div>
  </div>
</div>
</body>
</html>