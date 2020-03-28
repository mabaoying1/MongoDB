<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>实体卡库存台帐</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			laydate({
				elem: '#registerStartDate',
				event: 'focus',
				max: laydate.now()
			});
			laydate({
				elem: '#registerEndDate',
				event: 'focus',
				max: laydate.now()
			});
			if($("#statusCondition").val()==2){
					$("#contentAuditTable").show();
					$("#contentEditTable").show();
				}else{
					$("#contentAuditTable").hide();
					$("#contentEditTable").hide();
				}
			if($("#statusCondition").val()==2||$("#statusCondition").val()==3){
					$("#contentDeleteTable").show();
				}else{
					$("#contentDeleteTable").hide();
				}
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>实体卡库存台帐</h5>
		<div class="ibox-tools">
			<a class="collapse-link">
				<i class="fa fa-chevron-up"></i>
			</a>
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
				<i class="fa fa-wrench"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="#">选项1</a>
				</li>
				<li><a href="#">选项2</a>
				</li>
			</ul>
			<a class="close-link">
				<i class="fa fa-times"></i>
			</a>
		</div>
	</div>
    
    <div class="ibox-content">
	<sys:message content="${message}"/>
	
	<!--查询条件-->
	<div class="row">
	<div class="col-sm-12">
	<form:form id="searchForm" modelAttribute="hpRealCardStockBill" action="${ctx}/hc/realCard/stockbill" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>发卡机构号：</span>
			<form:input path="sendOrganiCode" htmlEscape="false" maxlength="50"  class="form-control input-sm"/>
			<span>发卡机构名称：</span>
			<form:input path="sendOrganiName" htmlEscape="false" maxlength="50"  class="form-control input-sm"/>
			<span>外部订单号：</span>
			<form:input path="outOrderNo" htmlEscape="false" maxlength="50"  class="form-control input-sm"/></br></br>
			<span>制卡供应商：</span>
			<form:input path="cardSupplier" htmlEscape="false" maxlength="50"  class="form-control input-sm"/>
			<span>&nbsp;&nbsp;制卡经办人：</span>
			<form:input path="makeCardByPerson" htmlEscape="false" maxlength="50"  class="form-control input-sm"/>
			<span>领卡经办人：</span>
			<form:input path="receiveCardByPerson" htmlEscape="false" maxlength="50"  class="form-control input-sm"/>
			<span>登记人：</span>
			<form:input path="registerPersonName" htmlEscape="false" maxlength="50"  class="form-control input-sm"/></br></br>
			<span>入/出库：</span>
			<%--<form:select path="type" id="type" name="type"  class="form-control required">
				<form:option value="1">入库</form:option>
				<form:option value="2">出库</form:option>
			</form:select>--%>
			<form:select path="type"   class="form-control m-b" >
				<form:option value="1" htmlEscape="false">入库</form:option>
				<form:option value="2">出库</form:option>
			</form:select>&nbsp;&nbsp;&nbsp;
			<span>流水状态：</span>
			<form:select path="status" id="statusCondition"  htmlEscape="false"  class="form-control m-b">
				<form:option value="1">成功</form:option>
				<form:option value="2">待审核</form:option>
				<form:option value="3">审核失败</form:option>
			</form:select>
			<div class="form-group">
				<span>登记时间：</span>
				<input id="registerStartDate" name="registerStartDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					   value="<fmt:formatDate value="${hpRealCardStockBillCon.registerStartDate}" pattern="yyyy-MM-dd"/>"/><label>&nbsp;--&nbsp;</label>
				<input type="text" id="registerEndDate" name="registerEndDate" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					   value="<fmt:formatDate value="${hpRealCardStockBillCon.registerEndDate}" pattern="yyyy-MM-dd"/>"/>
			</div>
			<div class="pull-right">
				<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
				<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
			</div>
			</table>
		 </div>
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="hc:realCard:stockbill:add">
				<table:addRow url="${ctx}/hc/realCard/stockbill/form" title="健康卡库存清单"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="hc:realCard:stockbill:edit">
				<span id="contentEditTable"><table:editRow url="${ctx}/hc/realCard/stockbill/form" title="健康卡库存清单" id="contentTable" key="pkid"></table:editRow></span><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="hc:realCard:stockbill:del">
				<span id="contentDeleteTable"><table:delRow url="${ctx}/hc/realCard/stockbill/del" id="contentTable" ></table:delRow></span><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="hc:realCard:stockbill:audit">
				<span id="contentAuditTable"><table:auditRow url="${ctx}/hc/realCard/stockbill/formAudit" title="健康卡库存清单" id="contentTable" key="pkid"></table:auditRow></span><!-- 審核按钮 -->
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			<shiro:hasPermission name="hc:realCard:stockbill:export">
				<table:exportExcel url="${ctx}/hc/realCard/stockbill/export"></table:exportExcel><!-- 导出按钮 -->
			</shiro:hasPermission>
			</div>
		<div class="pull-right">
			<p style="font-size: 15px;"><b>当前页总数量：${stockSum}</b></p>
		</div>
	</div>
	</div>
	
	<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th> <input type="checkbox" class="i-checks"></th>
				<th>发卡机构代码</th>
				<th  class="">发卡机构名称</th>
				<c:if test="${hpRealCardStockBillCon.type == null || hpRealCardStockBillCon.type==1}">
					<th  >入库起始卡号</th>
					<th  >入库截止卡号</th>
					<th >外部订单号</th>
					<th >制卡供应商</th>
					<th >供应商联系人</th>
					<th >供应商联系电话</th>
					<th >制卡经办人</th>
					<th >制卡时间</th>
					<th  >本次入库数量</th>
				</c:if>
				<c:if test="${hpRealCardStockBillCon.type != null && hpRealCardStockBillCon.type==2}">
					<th  >出库起始卡号</th>
					<th  >出库截止卡号</th>
					<%--<th>发卡机构号</th>
					<th  >发卡机构名字</th>--%>
					<%--<th  >医院名字</th>--%>
					<th >领卡经办人</th>
					<th >领卡时间</th>
					<th  >本次出库数量</th>
				</c:if>
				<th >登记人</th>
				<th >登记日期</th>
				<th >类型</th>
				<th >状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hpRealCardStockBill">
			<tr>
				<td>
					<input type="checkbox" id="${hpRealCardStockBill.pkid}" class="i-checks">
				</td>
				<td>
						${hpRealCardStockBill.sendOrganiCode}
				</td>
				<td>
						${hpRealCardStockBill.sendOrganiName}
				</td>
				<td> ${hpRealCardStockBill.cardNOOriInOutStock} </td>
				<td> ${hpRealCardStockBill.cardNOAbortInOutStock} </td>
				<c:if test="${hpRealCardStockBillCon.type == null || hpRealCardStockBillCon.type==1}">
					<td> ${hpRealCardStockBill.outOrderNo} </td>
					<td> ${hpRealCardStockBill.cardSupplier} </td>
					<td> ${hpRealCardStockBill.supplier} </td>
					<td> ${hpRealCardStockBill.supplierPhone} </td>
					<td> ${hpRealCardStockBill.makeCardByPerson} </td>
					<td>
						<c:if test="${hpRealCardStockBill.cardDate!=null}">
							<fmt:formatDate value="${hpRealCardStockBill.cardDate}"
											pattern="yyyy-MM-dd" />
						</c:if>
					</td>
					<td> ${hpRealCardStockBill.stockInOutMount} </td>
				</c:if>
				<c:if test="${hpRealCardStockBillCon.type != null && hpRealCardStockBillCon.type==2}">
					<%--<td> ${hpRealCardStockBill.sendOrganiCode} </td>
					<td> ${hpRealCardStockBill.sendOrganiName} </td>--%>
					<%--<td> ${hpRealCardStockBill.hospitalName} </td>--%>
					<td> ${hpRealCardStockBill.receiveCardByPerson} </td>
					<td>
						<c:if test="${hpRealCardStockBill.receiveDate!=null}">
							<fmt:formatDate value="${hpRealCardStockBill.receiveDate}"
											pattern="yyyy-MM-dd" />
						</c:if>
					</td>
					<td> ${hpRealCardStockBill.stockInOutMount} </td>
				</c:if>
				<td> ${hpRealCardStockBill.registerPersonName} </td>
				<td>
					<c:if test="${hpRealCardStockBill.registerDate!=null}">
						<fmt:formatDate value="${hpRealCardStockBill.registerDate}"
										pattern="yyyy-MM-dd HH:mm:ss" />
					</c:if>
				</td>
				<td>
					<c:if test="${hpRealCardStockBill.type==1}">
						入库
					</c:if>
					<c:if test="${hpRealCardStockBill.type==2}">
						出库
					</c:if>
				</td>
				<td>
					<c:if test="${hpRealCardStockBill.status==1}">
						成功
					</c:if>
					<c:if test="${hpRealCardStockBill.status==2}">
						待审核
					</c:if>
					<c:if test="${hpRealCardStockBill.status==3}">
						失败
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

		<!-- 分页代码 -->
	<table:page page="${page}"></table:page>
	<br/>
	<br/>
	</div>
	</div>
</div>
</body>
</html>