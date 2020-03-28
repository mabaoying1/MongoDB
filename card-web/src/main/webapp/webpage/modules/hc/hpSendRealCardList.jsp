<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>医院实时发卡情况</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			laydate({
				elem: '#startDate',
				event: 'focus',
				max: laydate.now()
			});
			laydate({
				elem: '#endDate',
				event: 'focus',
				max: laydate.now()
			});
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>医院实时发卡情况</h5>
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
	<form:form id="searchForm" modelAttribute="hpRealCardStock" action="${ctx}/hc/realCard/stock/sendlist" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>发卡机构号：</span>
			<form:input path="sendOrganiCode" htmlEscape="false" maxlength="50"  class="form-control input-sm"/>
			<span>发卡机构名称：</span>
			<form:input path="sendOrganiName" htmlEscape="false" maxlength="50"  class="form-control input-sm"/>
				<span>发卡时间：</span>
				<input id="startDate" name="startDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					   value="${startDate}"/><label>&nbsp;--&nbsp;</label>
				<input type="text" id="endDate" name="endDate" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					   value="${endDate}"/>
			</div>
		<div class="pull-right">
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
		</div>
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<%--<shiro:hasPermission name="hc:hpRealCard:add">
				<table:addRow url="${ctx}/hc/hpRealCard/form" title="实体卡"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="hc:hpRealCard:edit">
			    <table:editRow url="${ctx}/hc/hpRealCard/form" title="实体卡" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>--%>

	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>

			</div>
		<div style="display: inline-block" ><p style="font-size: 8px;padding-bottom:0px;position: relative;bottom: 0px;left: 0px;">（入库最大卡号，出库最大卡号，领取数量，剩余数量都是实时数据）</p></div>
		<div class="pull-right">
			注销总卡数：${requestScope.cancel}|正常总卡数：${requestScope.notCancel}|总卡数:${requestScope.total}
		</div>


	</div>
	</div>
	
	<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th> <input type="checkbox" class="i-checks"></th>
				<th>发卡机构代码</th>
				<th  >发卡机构名字</th>
				<th >入库最大卡号</th>
				<th  >出库最大卡号</th>
				<th  >领取数量</th>
				<th  >发卡数量</th>
				<th>注销卡数</th>
				<th>正常卡数</th>
				<th  >剩余数量</th>
				<th >库存修改时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hpRealCard">
			<tr>
				<td>
					<input type="checkbox" id="${hpRealCard.pkid}" class="i-checks">
				</td>
				<td>
					${hpRealCard.sendOrganiCode}
				</td>
				<td>
					${hpRealCard.sendOrganiName}
				</td>
				<td>
					${hpRealCard.cardNOMaxInStock}
				</td>
				<td>
					${hpRealCard.cardNOMaxOutStock}
				</td>
				<td>
					<c:if test="${hpRealCard.receiveTotalMount!=null && hpRealCard.receiveTotalMount!=0}">
						${hpRealCard.receiveTotalMount}
					</c:if>
				</td>
				<td>
					${hpRealCard.realSendCardCount}
				</td>
				<td>
					<c:if test="${hpRealCard.cancel!=null && hpRealCard.cancel!=0}">
						${hpRealCard.cancel}
					</c:if>
				</td>
				<td>
					<c:if test="${hpRealCard.notcancel!=null && hpRealCard.notcancel!=0}">
						${hpRealCard.notcancel}
					</c:if>
				</td>
				<td>
					<c:if test="${hpRealCard.receiveTotalMount!=null && hpRealCard.receiveTotalMount!=0}">
						${hpRealCard.remainingMount}
					</c:if>
				</td>
				<td>
						<c:if test="${hpRealCard.gmtModifiend!=null}">
							<fmt:formatDate value="${hpRealCard.gmtModifiend}"
											pattern="yyyy-MM-dd HH:mm:ss" />
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