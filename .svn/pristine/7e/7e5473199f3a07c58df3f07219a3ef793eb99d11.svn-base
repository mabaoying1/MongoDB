<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>健康e卡管理</title>
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
			$("#isOpenpay").change(function(){
                if($(this).val() == 1) {
                    $("#payLimitLabel").show();
                    $("#paylimit").show();
                }else{
                    $("#payLimitLabel").hide();
                    $("#paylimit").hide();
                }
            });
			laydate({
	            elem: '#validDate', 
	            event: 'focus',
	            min: laydate.now(), 
	            max: '2099-06-16 23:59:59'
	        });
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				rules: {
					paylimit: {number:true,min:0},//设置了远程验证，在初始化时必须预先调用一次。
					cardId:{digits:true}
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
		            elem: '#validDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
		        });
		});
	</script>
</head>
<body>
		<form:form id="inputForm" modelAttribute="hpHealthcard" action="${ctx}/hc/hpHealthcard/save" method="post" class="form-horizontal">
		<%--<form:hidden path="pkid"/>--%>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		   		<tr>
		   			 <td  class="width-15 active"><label class="pull-right">健康e卡卡号:</label></td>
					 <%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
		   			<td  class="width-35" ><form:input path="pkid" htmlEscape="false" maxlength="30" class="form-control required" readonly="true"/></td>

		   			 <td  class="width-15 active"><label class="pull-right">发卡机构:</label></td>
		   			 <td><span  class="width-35">${hpHealthcard.office.name}</span><%--<sys:treeselect id="office" name="office.id" value="${hpHealthcard.office.id}" labelName="office.name" labelValue="${hpHealthcard.office.name}"
						title="发卡机构" url="/sys/office/treeData" cssClass="form-control required"/>--%>

					 </td>
		   		</tr>
		   		<tr>
		   			<td class="width-15 active"><label class="pull-right">有效期:</label></td>
		   			<td class="width-35"><input id="validDate" name="validDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
				value="<fmt:formatDate value="${hpHealthcard.validDate}" pattern="yyyy-MM-dd"/>"/></td>
				    <td class="width-15 active"><label class="pull-right">归属地:</label></td>
				    <td class="width-35"><sys:treeselect id="area" name="area.id" value="${hpHealthcard.area.id}" labelName="area.name" labelValue="${hpHealthcard.countryname2}${hpHealthcard.provname2}${hpHealthcard.cityname2}${hpHealthcard.countyname2}${hpHealthcard.townname2}${hpHealthcard.villagename2}"
					title="区域" url="/sys/area/treeData?type=3" cssClass="form-control required" notAllowSelectParent="false"/></td>
		   		</tr>
		   		<tr>
		   			<td class="width-15 active"><label class="pull-right">是否开通支付:</label></td>
		   			<td class="width-35"><%-- <form:select path="isOpenpay"  class="form-control">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> --%><span>${fns:getDictLabel(hpHealthcard.isOpenpay , 'yes_no','是')}</span></td>
				    <td class="width-15 active"><label class="pull-right" id="payLimitLabel" >支付限额:</label></td>
					<td class="width-35"></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">支付密码:</label></td>
					<td class="width-35"></td>
					<td class="width-15 active"><label class="pull-right" id="payLimitLabel" >小额免密标志:</label></td>
					<td class="width-35">
				</tr>
				<tr>
					<td  class="width-15 active"><label class="pull-right">预留手机:</label></td>
						<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
					<td  class="width-35" ><span  class="width-35">${hpHealthcard.phone}</span></td>
				</tr>
		 	</tbody>
		</table>
			<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
				<thead>
				<tr>
					<th>实体卡号</th>
					<th>实体卡状态</th>
					<th>实体卡类型</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${realCardList}" var="realCardList">
					<tr>
						<td class="width-13">${realCardList.iccardid}</td>
						<td class="width-13">${fns:getDictLabel(realCardList.status,'realcard_status','错误')}</td>
						<td class="width-13">${fns:getDictLabel(realCardList.type,'card_type','错误')}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
	</form:form>

</body>
</html>