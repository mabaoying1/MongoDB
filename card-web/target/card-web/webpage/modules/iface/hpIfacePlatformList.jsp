<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>外部平台管理</title>
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
		<h5>外部平台列表 </h5>
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
	<form:form id="searchForm" modelAttribute="hpIfacePlatform" action="${ctx}/iface/hpIfacePlatform/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>平台名称：</span>
				<form:input path="name" htmlEscape="false" maxlength="30"  class=" form-control input-sm"/>
			<span>功能代码：</span>
				<form:input path="funcCode" htmlEscape="false" maxlength="30"  class=" form-control input-sm"/>
			<span>功能密钥：</span>
				<form:input path="scretkey" htmlEscape="false" maxlength="30"  class=" form-control input-sm"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="iface:hpIfacePlatform:add">
				<table:addRow url="${ctx}/iface/hpIfacePlatform/form" title="外部平台"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="iface:hpIfacePlatform:edit">
			    <table:editRow url="${ctx}/iface/hpIfacePlatform/form" title="外部平台" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="iface:hpIfacePlatform:del">
				<table:delRow url="${ctx}/iface/hpIfacePlatform/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="iface:hpIfacePlatform:import">
				<table:importExcel url="${ctx}/iface/hpIfacePlatform/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="iface:hpIfacePlatform:export">
	       		<table:exportExcel url="${ctx}/iface/hpIfacePlatform/export"></table:exportExcel><!-- 导出按钮 -->
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
				<th  class="sort-column name">平台名称</th>
				<th  class="sort-column func_code">功能代码</th>
				<th  class="sort-column scretkey">功能密钥</th>
				<th  class="sort-column posturl">传送地址</th>
				<th  class="sort-column datatype">报文类型</th>
				<th  class="sort-column urltype">地址类型</th>
				<th  class="sort-column platformtype">平台级别</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hpIfacePlatform">
			<tr>
				<td> <input type="checkbox" id="${hpIfacePlatform.pkid}" class="i-checks"></td>
				<td ><a  href="#" onclick="openDialogView('查看外部平台', '${ctx}/iface/hpIfacePlatform/form?id=${hpIfacePlatform.pkid}','800px', '500px')">
					${hpIfacePlatform.name}
				</a></td>
				<td >
					${hpIfacePlatform.funcCode}
				</td>
				<td >
					${hpIfacePlatform.scretkey}
				</td>
				<td >
					${hpIfacePlatform.posturl}
				</td>
				<td >
					${hpIfacePlatform.datatype}
				</td>
				<td >
					${hpIfacePlatform.urltype}
				</td>
				<td >
					${fns:getDictLabel(hpIfacePlatform.platformtype,'platform_type','市级')}
				</td>
				<td>
					<shiro:hasPermission name="iface:hpIfacePlatform:view">
						<a href="#" onclick="openDialogView('查看外部平台', '${ctx}/iface/hpIfacePlatform/form?id=${hpIfacePlatform.pkid}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="iface:hpIfacePlatform:edit">
    					<a href="#" onclick="openDialog('修改外部平台', '${ctx}/iface/hpIfacePlatform/form?id=${hpIfacePlatform.pkid}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="iface:hpIfacePlatform:del">
						<a href="${ctx}/iface/hpIfacePlatform/delete?id=${hpIfacePlatform.pkid}" onclick="return confirmx('确认要删除该外部平台吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="iface:hpIfacePlatform:assign">
						<a href="#" onclick="openDialogView('指定区域范围', '${ctx}/iface/hpIfacePlatform/assign?id=${hpIfacePlatform.pkid}','800px', '600px')"  class="btn  btn-warning btn-xs" ><i class="glyphicon glyphicon-plus"></i>指定区域范围</a>
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