<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title></title>
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
			$("#type").change(function () {
				if($(this).val() == 1){
					$("#inOutStockMountLabel").html("入库数量");
					$("#cardNOOriInOutStockLabel").html("入库起始卡号");
					$("#cardNOAbortInOutStockLabel").html("入库截止卡号");
					$("#outOrderTr").show();
					$("#makeCard").show();
					$("#supplier1").show();
					$("#supplier2").show();
					$("#reciveCard").hide();
					$("#hospital1").hide();
				}else{
					$("#inOutStockMountLabel").html("出库数量");
					$("#cardNOOriInOutStockLabel").html("出库起始卡号");
					$("#cardNOAbortInOutStockLabel").html("出库库截止卡号");
					$("#outOrderTr").hide();
					$("#makeCard").hide();
					$("#supplier1").hide();
					$("#supplier2").hide();
					$("#reciveCard").show();
					$("#hospital1").show();
				}
			});

			if($("#type").val()==1){
				$("#inOutStockMountLabel").html("入库数量");
				$("#cardNOOriInOutStockLabel").html("入库起始卡号");
				$("#cardNOAbortInOutStockLabel").html("入库截止卡号");
				$("#outOrderTr").show();
				$("#makeCard").show();
				$("#supplier1").show();
				$("#supplier2").show();
				$("#reciveCard").hide();
				$("#hospital1").hide();
			}else if($("#type").val()==2){
				$("#inOutStockMountLabel").html("出库数量");
				$("#cardNOOriInOutStockLabel").html("出库起始卡号");
				$("#cardNOAbortInOutStockLabel").html("出库库截止卡号");
				$("#outOrderTr").hide();
				$("#makeCard").hide();
				$("#supplier1").hide();
				$("#supplier2").hide();
				$("#reciveCard").show();
				$("#hospital1").show();
			}
			$("#stockInOutMount").change(function(){

			})

			validateForm = $("#inputForm").validate({
				rules:{
					stockInOutMount:{isIntGtZero:true},
					hospitalPhone:{isTel:true},
					supplierPhone:{isTel:true},
					paylimit: {number:true,min:0},//设置了远程验证，在初始化时必须预先调用一次。
					cardId:{digits:true}
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
		   			 <td  class="width-15 active"><label class="pull-right"><font color="red">*</font>发卡机构号:</label></td>
					 <%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
		   			 <td  class="width-35" >
						 <sys:treeselect id="officeId" name="officeId" value="${hpRealCardStockBill.sendOrganiCode}" labelName="officeId" labelValue="${hpRealCardStockBill.sendOrganiName}"
										 title="用户" url="/sys/office/treeData" cssClass="form-control input-sm required" allowClear="true" notAllowSelectParent="true"/>
						 <%--<form:input path="sendOrganiCode" value="${hpRealCardStockBill.sendOrganiCode}" htmlEscape="false" maxlength="50" class="form-control required" />--%>
					 </td>

					<td  class="width-15 active"><label class="pull-right"><font color="red">*</font>单位:</label></td>
						<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
					<td  class="width-35" >
						<form:select path="unit" id="unit" name="unit"   class="form-control">
							<form:option value="张">张</form:option>
						</form:select>
					</td>
		   		</tr>
				<tr>
					<td  class="width-15 active"><label class="pull-right"><font color="red">*</font>类型:</label></td>
						<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
					<td  class="width-35" >
						<%--<c:if test="${hpRealCardStockBill.type!=null}">
							<form:select path="type" id="type" name="type"  disabled="true" class="form-control required">
								<form:option value="1">入库</form:option>
								<c:if test="${hpRealCardStockBill.type==2}">
									<form:option value="2" select="select">出库</form:option>
								</c:if>
							</form:select>
						</c:if>
						<c:if test="${hpRealCardStockBill.pkid==null}">
							<form:select path="type" id="type" name="type"  class="form-control required">
								<form:option value="1">入库</form:option>
								<form:option value="2">出库</form:option>
							</form:select>
						</c:if>--%>
							<form:select path="type" id="type" name="type"  class="form-control required">
								<form:option value="1">入库</form:option>
								<form:option value="2">出库</form:option>
							</form:select>
					</td>
					<td  class="width-15 active"><font color="red">*</font><label class="pull-right" id="inOutStockMountLabel">入库数量:${hpRealCardStockBill.stockInOutMount}:</label></td>
					<td><span  class="width-35">
							<form:input path="stockInOutMount" id="stockInOutMount" value="${hpRealCardStockBill.stockInOutMount}" htmlEscape="false" maxlength="50" class="form-control required" />
						</span>
					</td>
				</tr>

				<tr id="outOrderTr">
					<td  class="width-15 active"><label class="pull-right"><font color="red">*</font>外部订单号:</label></td>
						<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
					<td  class="width-35" >
						<form:input path="outOrderNo" value="${hpRealCardStockBill.outOrderNo}" htmlEscape="false" maxlength="50" class="form-control required" />
					</td>

					<td  class="width-15 active"><label class="pull-right"></label></td>
					<td>
					</td>
				</tr>
				<tr id="inCardNo">
					<td  class="width-15 active"><font color="red">*</font><label class="pull-right" id="cardNOOriInOutStockLabel">入库起始卡号:</label></td>
						<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
					<td  class="width-35" >
						<form:input path="cardNOOriInOutStock" id="startNo" value="${hpRealCardStockBill.cardNOOriInOutStock}" htmlEscape="false" maxlength="50" class="form-control required" />
					</td>

					<td  class="width-15 active"><font color="red">*</font><label class="pull-right" id="cardNOAbortInOutStockLabel">入库截止卡号:</label></td>
					<td><span  class="width-35">
							<form:input path="cardNOAbortInOutStock" id="endNo" value="${hpRealCardStockBill.cardNOAbortInOutStock}" htmlEscape="false" maxlength="30" class="form-control required" />
						</span>
					</td>
				</tr>

				<tr id="hospital1" style="display: none">
					<td  class="width-15 active"><label class="pull-right"><font color="red">*</font>医院联系人:</label></td>
					<td><span  class="width-35">
						<form:input path="hospitalContacts" value="${hpRealCardStockBill.hospitalContacts}" htmlEscape="false" maxlength="30" class="form-control required" />
						</span>
					</td>
					<td  class="width-15 active"><label class="pull-right"><font color="red">*</font>医院联系电话:</label></td>
						<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
					<td  class="width-35" >
						<form:input path="hospitalPhone" htmlEscape="false" maxlength="20" class="form-control required" />
					</td>
				</tr>

				<tr  id="supplier1">
					<td  class="width-15 active"><label class="pull-right"><font color="red">*</font>供应商联系人:</label></td>
						<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
					<td  class="width-35" >
						<form:input path="supplier" value="${hpRealCardStockBill.supplier}" htmlEscape="false" maxlength="50" class="form-control required" />
					</td>
					<td  class="width-15 active"><label class="pull-right"><font color="red">*</font>供应商联系电话:</label></td>
					<td>
						<span  class="width-35">
							<form:input path="supplierPhone" value="${hpRealCardStockBill.supplierPhone}" htmlEscape="false" maxlength="20" class="form-control required" />
						</span>
					</td>
				</tr>
				<tr id="supplier2">
					<td  class="width-15 active"><label class="pull-right"><font color="red">*</font>制卡供应商:</label></td>
					<td><span  class="width-35">
							<form:input path="cardSupplier" value="${hpRealCardStockBill.cardSupplier}" htmlEscape="false" maxlength="30" class="form-control required" />
						</span>
					</td>
					<td  class="width-15 active"><label class="pull-right"></label></td>
						<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
					<td  class="width-35" ></td>

				</tr>
				<tr id="makeCard" >
					<td  class="width-15 active"><label class="pull-right"><font color="red">*</font>制卡经办人:</label></td>
						<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
					<td  class="width-35" >
						<form:input path="makeCardByPerson" value="${hpRealCardStockBill.makeCardByPerson}" htmlEscape="false" maxlength="50" class="form-control required"  />
					</td>
					<td  class="width-15 active"><label class="pull-right"><font color="red">*</font>制卡时间:</label></td>
					<td>
						<span  class="width-35">
							<input id="cardDate" name="cardDate" type="text" maxlength="20" class="laydate-icon form-control layer-date required"
								   value="<fmt:formatDate value="${hpRealCardStockBill.cardDate}" pattern="yyyy-MM-dd"/>"/>
						</span>

					</td>
				</tr>
				<tr id="reciveCard" style="display: none">
					<td  class="width-15 active"><label class="pull-right"><font color="red">*</font>领卡经办人:</label></td>
						<%--<td><span  class="width-35">${hpHealthcard.pkid}</span></td>--%>
					<td  class="width-35" >
						<form:input path="receiveCardByPerson" value="${hpRealCardStockBill.receiveCardByPerson}" htmlEscape="false" maxlength="50" class="form-control required" />
					</td>
					<td  class="width-15 active"><label class="pull-right"><font color="red">*</font>领卡时间:</label></td>
					<td>
						<span  class="width-35">
							<input id="receiveDate" name="receiveDate" type="text" maxlength="20" class="laydate-icon form-control layer-date required"
								   value="<fmt:formatDate value="${hpRealCardStockBill.receiveDate}" pattern="yyyy-MM-dd"/>"/>
						</span>
					</td>
				<tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>