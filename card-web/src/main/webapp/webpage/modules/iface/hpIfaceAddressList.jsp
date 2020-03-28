<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>接口地址管理</title>
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
		<h5>接口地址列表 </h5>
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
	<form:form id="searchForm" modelAttribute="hpIfaceAddress" action="${ctx}/iface/hpIfaceAddress/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>商户号：</span>
				<form:input path="merId" htmlEscape="false" maxlength="5"  class=" form-control input-sm"/>
			<span>功能码：</span>
				<form:input path="funcId" htmlEscape="false" maxlength="5"  class=" form-control input-sm"/>
			<span>接口名字：</span>
				<form:input path="funcName" htmlEscape="false" maxlength="10"  class=" form-control input-sm"/>
			<span>通信方式：</span>
				<form:select path="funcType"  class="form-control m-b">
					<form:options items="${fns:getDictList('communication_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="iface:hpIfaceAddress:add">
				<table:addRow url="${ctx}/iface/hpIfaceAddress/form" title="接口地址"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="iface:hpIfaceAddress:edit">
			    <table:editRow url="${ctx}/iface/hpIfaceAddress/form" title="接口地址" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="iface:hpIfaceAddress:del">
				<table:delRow url="${ctx}/iface/hpIfaceAddress/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="iface:hpIfaceAddress:import">
				<table:importExcel url="${ctx}/iface/hpIfaceAddress/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="iface:hpIfaceAddress:export">
	       		<table:exportExcel url="${ctx}/iface/hpIfaceAddress/export"></table:exportExcel><!-- 导出按钮 -->
	       	</shiro:hasPermission>
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
				<th  class="sort-column mer_id">商户号</th>
				<th  class="sort-column mer_name">中文全称</th>
				<th  class="sort-column func_id">功能码</th>
				<th  class="sort-column func_name">接口名字</th>
				<th  class="sort-column func_type">通信方式</th>
				<th  class="sort-column func_address">接口地址</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hpIfaceAddress">
			<tr>
				<td> <input type="checkbox" id="${hpIfaceAddress.id}" class="i-checks"></td>
				<td ><a  href="#" onclick="openDialogView('查看接口地址', '${ctx}/iface/hpIfaceAddress/form?id=${hpIfaceAddress.id}','800px', '500px')">
					${hpIfaceAddress.merId}
				</a></td>
				<td >
					${hpIfaceAddress.hpIfaceMerchant.merName}
				</td>
				<td >
					${hpIfaceAddress.funcId}
				</td>
				<td >
					${hpIfaceAddress.funcName}
				</td>
				<td >
					${fns:getDictLabel(hpIfaceAddress.funcType, 'communication_type', 'HTTP')}
				</td>
				<td >
					${hpIfaceAddress.funcAddress}
				</td>
				<td>
					<shiro:hasPermission name="iface:hpIfaceAddress:view">
						<a href="#" onclick="openDialogView('查看接口地址', '${ctx}/iface/hpIfaceAddress/form?id=${hpIfaceAddress.pkid}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="iface:hpIfaceAddress:edit">
    					<a href="#" onclick="openDialog('修改接口地址', '${ctx}/iface/hpIfaceAddress/form?id=${hpIfaceAddress.pkid}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="iface:hpIfaceAddress:del">
						<a href="${ctx}/iface/hpIfaceAddress/delete?id=${hpIfaceAddress.pkid}" onclick="return confirmx('确认要删除该接口地址吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
					</shiro:hasPermission>
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