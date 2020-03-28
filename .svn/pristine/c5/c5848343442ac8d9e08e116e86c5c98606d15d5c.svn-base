<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>审核</title>
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
			laydate({
				elem: '#receiveDate',
				event: 'focus',
				max: laydate.now()
			});
			laydate({
				elem: '#cardDate',
				event: 'focus',
				max: laydate.now()
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
		});
		/*$("#btn-audit").click(function() {
			alert("审核通过");
		});
		$("#btn-refuse").click(function() {
			alert("审核拒绝")
		});*/

		function audit(type) {
			if(type == 1){
				window.location.href  = "${ctx}/hc/realCard/stockbill/audit?status=1&pkid="+$("[name='pkid']").val();
			}else if(type==2){
				window.location.href= "${ctx}/hc/realCard/stockbill/audit?status=3&pkid="+$("[name='pkid']").val();
			}else{
				window.location.href = "${ctx}/hc/realCard/stockbill/list?status=2";
			}
		}

	</script>
</head>
<body>
		<form:form id="inputForm" modelAttribute="hpRealCardStockBill" action="${ctx}/hc/realCard/stockbill/save" method="post" class="form-horizontal">
			<input type="hidden" name="pkid" value="${hpRealCardStockBill.pkid}"/>
			<input type="hidden" id="opeType" value="${opeType}" />
		<%--<form:hidden path="pkid"/>--%>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		   		<tr>
		   			 <td  class="width-15 active"><label class="pull-right">发卡机构号:</label></td>
					 <%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
		   			 <td  class="width-35" >
							 ${hpRealCardStockBill.sendOrganiCode}
					 </td>

		   			 <td  class="width-15 active"><label class="pull-right">发卡机构名字:</label></td>
		   			 <td><span  class="width-35">
							 ${hpRealCardStockBill.sendOrganiName}
					 </td>
		   		</tr>
				<tr>
					<td  class="width-15 active"><label class="pull-right">类型:</label></td>
						<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
					<td  class="width-35" >
						<c:if test="${hpRealCardStockBill.type==2}">
							出库
						</c:if>
						<c:if test="${hpRealCardStockBill.type==1}">
							入库
						</c:if>
					</td>
					<td  class="width-15 active">
						<label class="pull-right" id="inOutStockMountLabel">
							<c:if test="${hpRealCardStockBill.type==2}">
								出库数量:
							</c:if>
							<c:if test="${hpRealCardStockBill.type==1}">
								入库数量:
							</c:if>
						</label>
					</td>
					<td><span  class="width-35">
							${hpRealCardStockBill.stockInOutMount}
						</span>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active"><label class="pull-right" id="cardNOOriInOutStockLabel"><c:if test="${hpRealCardStockBill.type==1}">入库起始卡号</c:if><c:if test="${hpRealCardStockBill.type==2}">出库起始卡号</c:if>:</label></td>
						<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
					<td  class="width-35" >
							${hpRealCardStockBill.cardNOOriInOutStock}
					</td>

					<td  class="width-15 active"><label class="pull-right" id="cardNOAbortInOutStockLabel"><c:if test="${hpRealCardStockBill.type==1}">入库截止卡号</c:if><c:if test="${hpRealCardStockBill.type==2}">出库截止卡号</c:if>:</label></td>
					<td>
							<span  class="width-35">
									${hpRealCardStockBill.cardNOAbortInOutStock}
							</span>
					</td>
				</tr>
				<c:if test="${hpRealCardStockBill.type==1}">
					<tr >
						<td  class="width-15 active"><label class="pull-right">外部订单号:</label></td>
							<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
						<td  class="width-35" >
								${hpRealCardStockBill.outOrderNo}
						</td>
						<td  class="width-15 active"><label class="pull-right"></label></td>
						<td>
						</td>
					</tr>
					<tr  >
						<td  class="width-15 active"><label class="pull-right">供应商联系人:</label></td>
							<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
						<td  class="width-35" >
								${hpRealCardStockBill.supplier}
						</td>
						<td  class="width-15 active"><label class="pull-right">供应商联系电话:</label></td>
						<td><span  class="width-35">
								${hpRealCardStockBill.supplierPhone}
						</span>
						</td>
					</tr>
					<tr >
						<td  class="width-15 active"><label class="pull-right">制卡供应商:</label></td>
						<td><span  class="width-35">
								${hpRealCardStockBill.cardSupplier}
						</span>
						</td>
						<td  class="width-15 active"><label class="pull-right"></label></td>
							<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
						<td  class="width-35" ></td>

					</tr>
					<tr id="makeCard" >
						<td  class="width-15 active"><label class="pull-right">制卡经办人:</label></td>
							<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
						<td  class="width-35" >
								${hpRealCardStockBill.makeCardByPerson}
						</td>
						<td  class="width-15 active"><label class="pull-right">制卡时间:</label></td>
						<td>
						<span  class="width-35">
							<fmt:formatDate value="${hpRealCardStockBill.cardDate}" pattern="yyyy-MM-dd"/>
						</span>

						</td>
					</tr>
				</c:if>
				<c:if test="${hpRealCardStockBill.type==2}">

					<tr id="hospital1" >
						<td  class="width-15 active"><label class="pull-right">医院联系人:</label></td>
						<td>
						<span  class="width-35">
								${hpRealCardStockBill.hospitalContacts}
						</span>
						</td>
						<td  class="width-15 active"><label class="pull-right">医院联系电话:</label></td>
							<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
						<td  class="width-35" >
								${hpRealCardStockBill.hospitalPhone}
						</td>
					</tr>
					<tr id="reciveCard">
						<td  class="width-15 active"><label class="pull-right">领卡经办人:</label></td>
							<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
						<td  class="width-35" >
								${hpRealCardStockBill.receiveCardByPerson}
						</td>
						<td  class="width-15 active"><label class="pull-right">领卡时间:</label></td>
						<td>
							<span  class="width-35">
								<fmt:formatDate value="${hpRealCardStockBill.receiveDate}" pattern="yyyy-MM-dd"/>
							</span>
						</td>
					<tr>
				</c:if>
				</tr>
					<td  class="width-15 active"><label class="pull-right">单位:</label></td>
						<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
					<td  class="width-35" >
							${hpRealCardStockBill.unit}
					</td>
					<td  class="width-15 active"><label class="pull-right"></label></td>
					<td><span  class="width-35"></span>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
				<div id="auditButtons" class="layui-layer-btn btn-center center" >
					<a id="btn-audit" onclick="audit(1)" class="layui-layer-btn0">审核通过</a>
					<a id="btn-refuse" onclick="audit(2)" class="layui-layer-btn0">审核拒绝</a>
					<a id="btn-back" onclick="audit(3)" class="layui-layer-btn0">返回列表</a>
				</div>
</body>
</html>