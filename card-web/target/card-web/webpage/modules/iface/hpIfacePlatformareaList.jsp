<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>外部平台管辖范围管理</title>
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
		<h5>外部平台管辖范围列表 </h5>
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
	<form:form id="searchForm" modelAttribute="hpIfacePlatformarea" action="${ctx}/iface/hpIfacePlatformarea/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>区域代码：</span>
				<form:input path="code" htmlEscape="false" maxlength="20"  class=" form-control input-sm"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="iface:hpIfacePlatformarea:add">
				<table:addRow url="${ctx}/iface/hpIfacePlatformarea/form" title="外部平台管辖范围"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="iface:hpIfacePlatformarea:edit">
			    <table:editRow url="${ctx}/iface/hpIfacePlatformarea/form" title="外部平台管辖范围" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="iface:hpIfacePlatformarea:del">
				<table:delRow url="${ctx}/iface/hpIfacePlatformarea/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="iface:hpIfacePlatformarea:import">
				<table:importExcel url="${ctx}/iface/hpIfacePlatformarea/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="iface:hpIfacePlatformarea:export">
	       		<table:exportExcel url="${ctx}/iface/hpIfacePlatformarea/export"></table:exportExcel><!-- 导出按钮 -->
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
				<th  class="sort-column code">区域代码</th>
				<th  class="sort-column platformid">平台ID</th>
				<th  class="sort-column platformtype">平台级别:1 市级 2 县级</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hpIfacePlatformarea">
			<tr>
				<td> <input type="checkbox" id="${hpIfacePlatformarea.id}" class="i-checks"></td>
				<td ><a  href="#" onclick="openDialogView('查看外部平台管辖范围', '${ctx}/iface/hpIfacePlatformarea/form?id=${hpIfacePlatformarea.id}','800px', '500px')">
					${hpIfacePlatformarea.code}
				</a></td>
				<td >
					${hpIfacePlatformarea.platformid}
				</td>
				<td >
					${hpIfacePlatformarea.platformtype}
				</td>
				<td>
					<shiro:hasPermission name="iface:hpIfacePlatformarea:view">
						<a href="#" onclick="openDialogView('查看外部平台管辖范围', '${ctx}/iface/hpIfacePlatformarea/form?id=${hpIfacePlatformarea.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="iface:hpIfacePlatformarea:edit">
    					<a href="#" onclick="openDialog('修改外部平台管辖范围', '${ctx}/iface/hpIfacePlatformarea/form?id=${hpIfacePlatformarea.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="iface:hpIfacePlatformarea:del">
						<a href="${ctx}/iface/hpIfacePlatformarea/delete?id=${hpIfacePlatformarea.id}" onclick="return confirmx('确认要删除该外部平台管辖范围吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
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