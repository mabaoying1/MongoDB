<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>接口地址管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()) {
			//  confirmx('确认要修改该接口地址吗？', function(){$("#inputForm").submit();window.close();return true;});
			if (confirm('确认要修改该接口地址吗？')) {

				$("#inputForm").submit();
				window.close();
				return true;
			}
		  }
	
		  return false;
		}
		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
				rules:{
					merId:{remote: {type:"post",url:"${ctx}/iface/hpIfaceAddress/checkAddress",data:{oldFuncId:function(){return encodeURIComponent('${hpIfaceAddress.funcId}')},
                    oldMerId:function(){return encodeURIComponent('${hpIfaceAddress.merId}')},
                    merId:function(){return $("#merId").val()},
                    funcId:function(){return $("#funcId").val()}
                    }}
				    },
                    funcId:{remote: {type:"post",url:"${ctx}/iface/hpIfaceAddress/checkAddress",data:{oldFuncId:function(){return encodeURIComponent('${hpIfaceAddress.funcId}')},
                        oldMerId:function(){return encodeURIComponent('${hpIfaceAddress.merId}')},
                        merId:function(){return $("#merId").val()},
                        funcId:function(){return $("#funcId").val()}
                    }}}
                },
                messages: {
                    merId: {remote: "商户号与功能码的对应已存在"},
                    funcId: {remote: "商户号与功能码的对应已存在"}
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
			
		});
	</script>
</head>
<body>
		<form:form id="inputForm" modelAttribute="hpIfaceAddress" action="${ctx}/iface/hpIfaceAddress/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">商户号：</label></td>
					<td class="width-35">
						<form:input path="merId" htmlEscape="false" maxlength="5" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">功能码：</label></td>
					<td class="width-35">
						<form:input path="funcId" htmlEscape="false" maxlength="5" class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">接口名字：</label></td>
					<td class="width-35">
						<form:input path="funcName" htmlEscape="false" maxlength="10" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">通信方式：</label></td>
					<td class="width-35">
						<form:select path="funcType" class="form-control ">
							<form:options items="${fns:getDictList('communication_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">接口地址：</label></td>
					<td class="width-35">
						<form:input path="funcAddress" htmlEscape="false" maxlength="50" class="form-control required"/>
					</td>
					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>