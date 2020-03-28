<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>接口日志管理</title>
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
		<h5>接口日志列表 </h5>
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
	<form:form id="searchForm" modelAttribute="hpIfaceLog" action="${ctx}/iface/hpIfaceLog/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>商户号：</span>
				<form:input path="merId" htmlEscape="false" maxlength="5"  class=" form-control input-sm"/>
			<span>功能码：</span>
				<form:input path="funcId" htmlEscape="false" maxlength="30"  class=" form-control input-sm"/>
			<span>接收时间：</span>
				<form:input path="createTime" htmlEscape="false"  class=" form-control input-sm"/>
			<span>结果：</span>
				<form:input path="retcode" htmlEscape="false" maxlength="30"  class=" form-control input-sm"/>
		 </div>
	</form:form>
	<br/>
	</div>
	</div>

	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="iface:hpIfaceLog:add">
				<table:addRow url="${ctx}/iface/hpIfaceLog/form" title="接口日志"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="iface:hpIfaceLog:edit">
			    <table:editRow url="${ctx}/iface/hpIfaceLog/form" title="接口日志" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="iface:hpIfaceLog:del">
				<table:delRow url="${ctx}/iface/hpIfaceLog/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="iface:hpIfaceLog:import">
				<table:importExcel url="${ctx}/iface/hpIfaceLog/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="iface:hpIfaceLog:export">
	       		<table:exportExcel url="${ctx}/iface/hpIfaceLog/export"></table:exportExcel><!-- 导出按钮 -->
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
	<div style="overflow: auto; width:100%;">
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th> <input type="checkbox" class="i-checks"></th>
				<th>商户号</th>
				<th>功能码</th>
				<th>接收时间</th>
				<th>发送报文原文</th>
				<th>返回错误消息</th>
				<th>结果</th>
				<th>返回报文</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hpIfaceLog">
			<tr>
				<td> <input type="checkbox" id="${hpIfaceLog.pkid}" class="i-checks"></td>
				<td ><a  href="#" onclick="openDialogView('查看接口日志', '${ctx}/iface/hpIfaceLog/form?id=${hpIfaceLog.pkid}','800px', '500px')">
					${hpIfaceLog.merId}
				</a></td>
				<td >
					${hpIfaceLog.funcId}
				</td>
				<td >
					<fmt:formatDate value="${hpIfaceLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td title="<c:out value="${hpIfaceLog.senddata}" escapeXml="true"/>">
					<c:out value="${hpIfaceLog.ref1}" escapeXml="true" default="${hpIfaceLog.senddata}"/>
				</td>
				<td >
					${hpIfaceLog.errmsg}
				</td>
				<td >
					${hpIfaceLog.retcode}
				</td>
				<td title="<c:out value="${hpIfaceLog.retdata}" escapeXml="true"/>">
					<c:out value="${hpIfaceLog.ref0}" escapeXml="true" default="${hpIfaceLog.retdata}"/>
				</td>
				<td>
					<shiro:hasPermission name="iface:hpIfaceLog:view">
						<a href="#" onclick="openDialogView('查看接口日志', '${ctx}/iface/hpIfaceLog/form?id=${hpIfaceLog.pkid}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="iface:hpIfaceLog:edit">
    					<a href="#" onclick="openDialog('修改接口日志', '${ctx}/iface/hpIfaceLog/form?id=${hpIfaceLog.pkid}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="iface:hpIfaceLog:del">
						<a href="${ctx}/iface/hpIfaceLog/delete?id=${hpIfaceLog.pkid}" onclick="return confirmx('确认要删除该接口日志吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
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
</div>
</body>
</html>