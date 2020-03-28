<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>商户管理</title>
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
			laydate({
	            elem: '#validtime', 
	            event: 'focus',
			    min: laydate.now()
	        });
		});
	</script>
</head>
<body>
<%-- 		<form:form id="inputForm" modelAttribute="hpIfaceMerchant" action="${ctx}/interface/hpIfaceMerchant/saveHpIfaceMerchant" method="post" class="form-horizontal">
		<form:hidden path="merId"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>中文全称：</label></td>
					<td class="width-35">
						<form:input path="merName" htmlEscape="false" maxlength="100" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">英文全称：</label></td>
					<td class="width-35">
						<form:input path="merEname" htmlEscape="false" maxlength="100" class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">中文简称：</label></td>
					<td class="width-35">
						<form:input path="merAbbrName" htmlEscape="false" maxlength="50" class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">英文简称：</label></td>
					<td class="width-35">
						<form:input path="merAbbrEname" htmlEscape="false" maxlength="50" class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>商户号：</label></td>
					<td class="width-35">
						<form:input path="merId" htmlEscape="false" maxlength="50" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>数字证书代码：</label></td>
					<td class="width-35">
						<form:input path="digitalKey" htmlEscape="false" maxlength="50" class="form-control required"/>
					</td>
				</tr>
		        <tr>
					<td class="width-15 active"><label class="pull-right">商户类型：</label></td>
					<td class="width-35">
						<form:select path="source"  class="form-control">
							<form:options items="${fns:getDictList('mer_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">是否自动审核：</label></td>
					<td class="width-35">
						<form:select path="isAutoAudit"  class="form-control">
							<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form> --%>
	
	<form:form id="inputForm" modelAttribute="hpIfaceMerchant" action="${ctx}/interface/hpIfaceMerchant/saveHpIfaceMerchant" method="post" class="form-horizontal">
<%--		<form:hidden path="merId"/>--%>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>机构名称：</label></td>
					<td class="width-35">
						<form:input path="merName" htmlEscape="false" maxlength="100" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">英文名称：</label></td>
					<td class="width-35">
						<form:input path="merEname" htmlEscape="false" maxlength="100" class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>机构编码：</label></td>
					<td class="width-35">
						<form:input path="orgCode" htmlEscape="false" maxlength="50" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>机构性质：</label></td>
					<td class="width-35">
						<form:select path="orgProperty"  class="form-control required">
							<form:options items="${fns:getDictList('orgproperty')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">接入ip地址:</label></td>
					<td class="width-35">
						<form:input path="orgIp" htmlEscape="false" maxlength="50" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>接入方式:</label></td>
					<td class="width-35">
						<form:select path="accessMethod"  class="form-control required">
							<form:options items="${fns:getDictList('accessmethod')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>有效时间:</label></td>
					<td class="width-35">
					<input id="validtime" name="validTime" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm required"
					   value="${validtime}"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>机构行政区划:</label></td>
					 <td class="width-35"><sys:treeselect id="orgAddr" name="orgAddr.id" value="${hpIfaceMerchant.orgAddr.id}" labelName="orgAddr.name" labelValue="${hpIfaceMerchant.orgAddr.name}"
					title="区域" url="/sys/area/treeData?type=3" cssClass="form-control required" notAllowSelectParent="false" allShowParent="true"  /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>机构等级:</label></td>
					<td class="width-35">
						<form:select path="orgLevel"  class="form-control required">
							<form:options items="${fns:getDictList('sys_office_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>机构等次:</label></td>
					<td class="width-35">
						<form:select path="orgHierarchy"  class="form-control required">
							<form:options items="${fns:getDictList('orghierarchy')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				 <tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>商户类型：</label></td>
					<td class="width-35">
						<form:select path="source"  class="form-control required">
							<form:options items="${fns:getDictList('mer_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>是否自动审核：</label></td>
					<td class="width-35">
						<form:select path="isAutoAudit"  class="form-control required">
							<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<%-- <tr>
					<td class="width-15 active"><label class="pull-right">登记注册类型:</label></td>
					<td class="width-35">
						<form:select path="source"  class="form-control">
							<form:options items="${fns:getDictList('djzclx')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">机构类别:</label></td>
					<td class="width-35">
						<form:select path="source"  class="form-control">
							<form:options items="${fns:getDictList('jglb')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">分类管理类型:</label></td>
					<td class="width-35">
						<form:select path="source"  class="form-control">
							<form:options items="${fns:getDictList('flgllx')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">政府办隶属机构:</label></td>
					<td class="width-35">
						<form:input path="digitalKey" htmlEscape="false" maxlength="50" class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">机构级别:</label></td>
					<td class="width-35">
						<form:select path="source"  class="form-control">
							<form:options items="${fns:getDictList('jgjb')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">许可范围:</label></td>
					<td class="width-35">
						<form:input path="digitalKey" htmlEscape="false" maxlength="50" class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">机构具体地址:</label></td>
					<td class="width-35">
						<form:input path="merId" htmlEscape="false" maxlength="50" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">联系地址:</label></td>
					<td class="width-35">
						<form:input path="digitalKey" htmlEscape="false" maxlength="50" class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">负责人联系电话:</label></td>
					<td class="width-35">
						<form:input path="merId" htmlEscape="false" maxlength="50" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">邮政编码:</label></td>
					<td class="width-35">
						<form:input path="digitalKey" htmlEscape="false" maxlength="50" class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">电子邮箱:</label></td>
					<td class="width-35">
						<form:input path="merId" htmlEscape="false" maxlength="50" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">网址域名:</label></td>
					<td class="width-35">
						<form:input path="digitalKey" htmlEscape="false" maxlength="50" class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">成立时间:</label></td>
					<td class="width-35">
						<input id="clsj" name="clsj" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					   value="${clsj}"/>
					</td>
					<td class="width-15 active"><label class="pull-right">单位负责人:</label></td>
					<td class="width-35">
						<form:input path="digitalKey" htmlEscape="false" maxlength="50" class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">管理类别:</label></td>
					<td class="width-35">
						<form:select path="source"  class="form-control">
							<form:options items="${fns:getDictList('gllb')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">发证机关:</label></td>
					<td class="width-35">
						<form:input path="digitalKey" htmlEscape="false" maxlength="50" class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">批准日期:</label></td>
					<td class="width-35">
						<input id="pzrq" name="pzrq" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					   value="${pzrq}"/>
					</td>
					<td class="width-15 active"><label class="pull-right">批准文号:</label></td>
					<td class="width-35">
						<form:input path="digitalKey" htmlEscape="false" maxlength="50" class="form-control required"/>
					</td>
				</tr> --%>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>