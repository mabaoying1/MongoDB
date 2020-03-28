<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>实体卡管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>实体卡列表 </h5>
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
	<form:form id="searchForm" modelAttribute="hpRealCard" action="${ctx}/hc/hpRealCard/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>实体卡卡号：</span>
				<form:input path="iccardid" htmlEscape="false" maxlength="30"  class=" form-control input-sm"/>
			<span>健康e卡卡号：</span>
				<form:input path="cardPkid" htmlEscape="false" maxlength="30"  class=" form-control input-sm"/>
			<span>卡状态：</span>
				<form:select path="status"  class="form-control m-b">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('realcard_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			<span>卡类型：</span>
				<form:select path="type"  class="form-control m-b">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('card_type')}"  itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select></br></br>
			<span>持卡人姓名：</span>
			<form:input path="hpHealthcard.hpCardholder.name" htmlEscape="false" maxlength="30"  class=" form-control input-sm"/>
			<span>证件类别：</span>
			<form:select path="hpHealthcard.hpCardholder.docuType" class="form-control required">
				<form:option value="" label="请选择"/>
				<form:options items="${fns:getDictList('std_id_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			<span>证件号：</span>
			<form:input path="hpHealthcard.hpCardholder.docuId" htmlEscape="false" maxlength="30"  class=" form-control input-sm"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="hc:hpRealCard:add">
				<table:addRow url="${ctx}/hc/hpRealCard/form" title="实体卡"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="hc:hpRealCard:edit">
			    <table:editRow url="${ctx}/hc/hpRealCard/form" title="实体卡" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<%--
			<shiro:hasPermission name="hc:hpRealCard:del">
				<table:delRow url="${ctx}/hc/hpRealCard/deleteAll" id="contentTable" label="注销" msg="您确定要注销实体卡吗"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="hc:hpRealCard:import">
				<table:importExcel url="${ctx}/hc/hpRealCard/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="hc:hpRealCard:export">
	       		<table:exportExcel url="${ctx}/hc/hpRealCard/export"></table:exportExcel><!-- 导出按钮 -->
	       	</shiro:hasPermission>
	       	--%>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
		</div>
	</div>
	</div>
	
	<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>

				<th> <input type="checkbox" class="i-checks"></th>
				<th>持卡人姓名</th>
				<th  class="sort-column card_pkid">健康e卡卡号</th>
				<th  class="sort-column iccardid">实体卡卡号</th>
				<th  class="sort-column status">卡状态</th>
				<th  class="sort-column type">卡类型</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hpRealCard">
			<tr>
				<td><c:if test="${ hpRealCard.status ==1}">
				 <input type="checkbox" id="${hpRealCard.pkid}" class="i-checks">
				</c:if></td>
				<td>${hpRealCard.hpHealthcard.hpCardholder.name}</td>
				<td ><a  href="#" onclick="openDialogView('查看实体卡', '${ctx}/hc/hpRealCard/form?id=${hpRealCard.pkid}','800px', '500px')">
					${hpRealCard.cardPkid}
				</a></td>
				<td >
					${hpRealCard.iccardid}
				</td>
				<td>
				   ${fns:getDictLabel(hpRealCard.status , 'realcard_status', '正常')}
				</td>
				<td >
					${fns:getDictLabel(hpRealCard.type , 'card_type', '正常')}
				</td>
				<td>
					<shiro:hasPermission name="hc:hpRealCard:view">
						<a href="#" onclick="openDialogView('查看实体卡', '${ctx}/hc/hpRealCard/form?id=${hpRealCard.pkid}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<c:if test="${hpRealCard.status == 1}">
					<shiro:hasPermission name="hc:hpRealCard:edit">
    					<a href="#" onclick="openDialog('修改实体卡', '${ctx}/hc/hpRealCard/form?id=${hpRealCard.pkid}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
						<c:if test="${hpRealCard.type != 1}">
							<shiro:hasPermission name="hc:hpRealCard:del">
								<a href="${ctx}/hc/hpRealCard/delete?id=${hpRealCard.pkid}" onclick="return confirmx('确认要注销该实体卡吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i>注销</a>
							</shiro:hasPermission>
						</c:if>
					</c:if>

					<%--<shiro:hasPermission name="">
						
					</shiro:hasPermission>--%>
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