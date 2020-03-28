<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>健康e卡历史记录管理</title>
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
		<h5>健康e卡历史记录列表 </h5>
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
	<form:form id="searchForm" modelAttribute="hpApplycardHis" action="${ctx}/hc/hpApplycardHis/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>姓名：</span>
				<form:input path="name" htmlEscape="false" maxlength="30"  class=" form-control input-sm"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="hc:hpApplycardHis:add">
				<table:addRow url="${ctx}/hc/hpApplycardHis/form" title="健康卡历史记录"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="hc:hpApplycardHis:edit">
			    <table:editRow url="${ctx}/hc/hpApplycardHis/form" title="健康卡历史记录" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="hc:hpApplycardHis:del">
				<table:delRow url="${ctx}/hc/hpApplycardHis/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="hc:hpApplycardHis:import">
				<table:importExcel url="${ctx}/hc/hpApplycardHis/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="hc:hpApplycardHis:export">
	       		<table:exportExcel url="${ctx}/hc/hpApplycardHis/export"></table:exportExcel><!-- 导出按钮 -->
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
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable" style="width: 1300px;">
		<thead>
			<tr>
				<th ></th>
			    <th>姓名</th>
				<th>证件类型</th>
				<th  class="sort-column identity_id">证件号码</th>
				<th>手机号</th>
				<th  class="sort-column sex">性别</th>
				<th>年龄</th>
				<th>发卡机构</th>
				<th>居住地</th>
				<th>申请时间</th>
				<th>审核时间</th>
				<th>状态</th>
				<th>拒绝理由</th>
				<th>创建人</th>
				<th>审核人</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hpApplycardHis">
			<tr>
				<td> <input type="checkbox" id="${hpApplycardHis.id}" class="i-checks"></td>
				<td ><a  href="${ctx}/hc/hpApplycardHis/form?pkid=${hpApplycardHis.pkid}">
					${hpApplycardHis.name}
				</a></td>
				<td>${fns:getDictLabel(hpApplycardHis.docuType , 'std_id_type', '居民身份证')}</td>
				<td>${hpApplycardHis.docuId}</td>
				<td>${hpApplycardHis.phone}</td>
				<td>${fns:getDictLabel(hpApplycardHis.sex, 'sex', '未知')}</td>
				<td>${hpApplycardHis.age}</td>
				<td>${hpApplycardHis.organizationName}</td>
				<td>${hpApplycardHis.countryname2}${hpApplycardHis.provname2}${hpApplycardHis.cityname2}${hpApplycardHis.countyname2}${hpApplycardHis.townname2}${hpApplycardHis.villagename2}</td>
				<td><fmt:formatDate value="${hpApplycardHis.createDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${hpApplycardHis.auditDate}" pattern="yyyy-MM-dd"/></td>
				<td>${fns:getDictLabel(hpApplycardHis.status, 'card_apply_status', '待审核')}</td>
				<td>${hpApplycardHis.reason}</td>
				<td>${hpApplycardHis.createByName}</td>
				<td>${hpApplycardHis.auditByName}</td>
				<td>
					<shiro:hasPermission name="hc:hpApplycardHis:view">
						<a href="${ctx}/hc/hpApplycardHis/form?pkid=${hpApplycardHis.pkid}" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
		<!-- 分页代码 -->
	<table:page page="${page}"></table:page>
	<br/>
	<br/>
	</div>
	</div>
</div>
</body>
</html>