<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>外部平台管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/webpage/include/treeview.jsp" %>
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
			var ids2 = "${hpIfacePlatform.areaIds}".split(",");
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					var ids2 = [], nodes2 = tree2.getCheckedNodes(true);
					for(var i=0; i<nodes2.length; i++) {
						ids2.push(nodes2[i].id);
					}
					$("#areaIds").val(ids2);
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

			var setting = {check:{enable:true,nocheckInherit:true},view:{selectedMulti:false},
				async:{enable:true,url:"${ctx}/sys/area/treeData",autoParam:["id"]},
				data:{simpleData:{enable:true}},callback:{beforeClick:function(treeId, treeNode, clickFlag){
					//var treeObj = $.fn.zTree.getZTreeObj(id);
					//treeObj.checkNode(node, !node.checked, false, true);
					//return false;
				},onAsyncSuccess:function(event,treeid,treeNode,msg){
					var nodes = treeNode.children;
					for(var i=0;i<nodes.length;i++){
						if($.inArray(nodes[i].id,ids2) != -1){
							try{tree2.checkNode(nodes[i], true, false);}catch(e){}
						}
					}

				}}};
			var tree2;
			$.post("${ctx}/sys/area/treeData",function(data){
				var zNodes2 = data;
				// 初始化树结构
				tree2 = $.fn.zTree.init($("#areaTree"), setting, zNodes2);
				// 不选择父节点
				tree2.setting.check.chkboxType = { "Y" : "", "N" : "" };
				var nodes = tree2.getNodesByParam("level", 0);
				for(var i=0; i<nodes.length; i++) {
					tree2.expandNode(nodes[i], true, false, false);
				}
				// 默认展开全部节点
				//tree2.expandAll(false);
				// 默认选择节点
				for(var i=0; i<ids2.length; i++) {
					var node = tree2.getNodeByParam("id", ids2[i]);
					try{tree2.checkNode(node, true, false);}catch(e){}
				}
			});
		});
	</script>
</head>
<body>
		<form:form id="inputForm" modelAttribute="hpIfacePlatform" action="${ctx}/iface/hpIfacePlatform/save" method="post" class="form-horizontal">
			<input type="hidden" name="id" value="${hpIfacePlatform.pkid}"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">平台名称：</label></td>
					<td class="width-35">
						<form:input path="name" htmlEscape="false" maxlength="30" class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">功能代码：</label></td>
					<td class="width-35">
						<form:input path="funcCode" htmlEscape="false" maxlength="30" class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">功能密钥：</label></td>
					<td class="width-35">
						<form:input path="scretkey" htmlEscape="false" maxlength="30" class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">传送地址：</label></td>
					<td class="width-35">
						<form:input path="posturl" htmlEscape="false" maxlength="200" class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">报文类型：</label></td>
					<td class="width-35">
						<form:select path="datatype" class="form-control ">
							<form:options items="${fns:getDictList('data_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">地址类型：</label></td>
					<td class="width-35">
						<form:select path="urltype" class="form-control ">
							<form:options items="${fns:getDictList('iface_url_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">平台级别：</label></td>
					<td class="width-35">
						<form:select path="platformtype" class="form-control ">
							<form:options items="${fns:getDictList('platform_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
		        <tr>
					<td class="width-15 active"><label class="pull-right">管辖范围：</label></td>
					<td class="width-35" colspan="3">
						<div id="areaTree" class="ztree" style="margin-top:3px;"></div>
						<form:hidden path="areaIds"/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>