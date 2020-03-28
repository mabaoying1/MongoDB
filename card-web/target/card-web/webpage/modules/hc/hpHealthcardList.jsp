<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>健康e卡管理</title>
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
		<h5>健康e卡列表 </h5>
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
	<form:form id="searchForm" modelAttribute="hpHealthcard" action="${ctx}/hc/hpHealthcard/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
				<span>卡属地：</span>
				<sys:treeselect id="area" name="area.id" value="${hpHealthcard.area.id}" labelName="area.name" labelValue="${hpHealthcard.area.name}"
							title="卡属地" url="/sys/area/treeData?type=3" cssClass=" form-control input-sm" allowClear="true" notAllowSelectParent="false"/>
				<span>健康e卡卡号：</span>
				<form:input path="pkid" htmlEscape="false" maxlength="50" class=" form-control input-sm"/>
					<span>发卡机构：</span>
				<sys:treeselect id="office" name="office.id" value="${hpHealthcard.office.id}" labelName="office.name" labelValue="${hpHealthcard.office.name}" 
				title="发卡机构" url="/sys/office/treeData?type=1" cssClass=" form-control input-sm" allowClear="true"/></br></br>
				<span>身份证号：</span>
				<form:input path="cardId" htmlEscape="false" maxlength="18" class=" form-control input-sm"/>
				<span>手机号：</span>
				<form:input path="phone" htmlEscape="false" maxlength="50" class=" form-control input-sm"/>
				<span>姓名：</span>
				<form:input path="hpCardholder.name" htmlEscape="false" maxlength="40" class=" form-control input-sm"/>
		 </div>
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="hc:hpHealthcard:add">
				<table:addRow url="${ctx}/hc/hpHealthcard/form" title="健康e卡"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="hc:hpHealthcard:edit">
			    <table:editRow url="${ctx}/hc/hpHealthcard/form" title="健康e卡" id="contentTable" key="pkid"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<%--<shiro:hasPermission name="hc:hpHealthcard:del">
				<table:delRow url="${ctx}/hc/hpHealthcard/deleteAll" id="contentTable" label="注销" msg="确认要注销吗？"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>--%>
			<shiro:hasPermission name="hc:hpHealthcard:import">
				<table:importExcel url="${ctx}/hc/hpHealthcard/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="hc:hpHealthcard:export">
	       		<table:exportExcel url="${ctx}/hc/hpHealthcard/export"></table:exportExcel><!-- 导出按钮 -->
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
				<th><input type="checkbox" ></th>
				<th class="width-13">姓名</th>
				<th class="sort-column pkid">健康e卡卡号</th>
				<th>发卡机构</th>
				<th>发卡时间</th>
				<th>有效时间</th>
				<th class="width-20">卡属地</th>
				<th>状态</th>
				<th>健康钱包</th>
				<th>关联手机号</th>
				<th>来源</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hpHealthcard">
			<tr>
				<td><c:if test="${hpHealthcard.status == 2}"><input type="checkbox" id="${hpHealthcard.pkid}" class="i-checks"/></c:if></td>
				<td class="width-13">${hpHealthcard.hpCardholder.name}</td>
				<td><a  href="#" onclick="openDialogView('查看健康e卡', '${ctx}/hc/hpHealthcard/form?pkid=${hpHealthcard.pkid}','800px', '500px')">
					${hpHealthcard.pkid}
				</a></td>
				<td class="width-13">${hpHealthcard.office.name}</td>
				<td class="width-13"><fmt:formatDate value="${hpHealthcard.startDate }" pattern="yyyy-MM-dd"/></td>
				<td class="width-13"><fmt:formatDate value="${hpHealthcard.validDate }" pattern="yyyy-MM-dd"/></td>
				<td class="width-20">${hpHealthcard.countryname2}${hpHealthcard.provname2}${hpHealthcard.cityname2}${hpHealthcard.countyname2}${hpHealthcard.townname2}${hpHealthcard.villagename2}${hpHealthcard.address}</td>
				<td>${fns:getDictLabel(hpHealthcard.status,'card_status','注销')}</td>
				<td>${fns:getDictLabel(hpHealthcard.isOpenpay,'is_open_papy','未开通')}</td>
				<td>${hpHealthcard.phone }</td>
				<td>${fns:getDictLabel(hpHealthcard.source,'data_source','手机app')}</td>
				<td>
					<shiro:hasPermission name="hc:hpHealthcard:view">
						<a href="#" onclick="openDialogView('查看健康e卡', '${ctx}/hc/hpHealthcard/form?pkid=${hpHealthcard.pkid}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<%-- 状态为待激活的情况 --%>
					<c:if test="${hpHealthcard.status == 1}">
						<shiro:hasPermission name="hc:hpHealthcard:activate"> 
						<a href="${ctx}/hc/hpHealthcard/activate?pkid=${hpHealthcard.pkid}"  onclick="return confirmx('确认要激活该健康e卡吗？', this.href)" class="btn btn-primary btn-xs" ><i class="fa fa-edit"></i>激活</a>
						</shiro:hasPermission>
					</c:if>
					<c:if test="${hpHealthcard.status == 2}">
						<shiro:hasPermission name="hc:hpHealthcard:edit">
	    					<a href="#" onclick="openDialog('修改健康e卡', '${ctx}/hc/hpHealthcard/form?pkid=${hpHealthcard.pkid}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
	    				</shiro:hasPermission>
	    				<%--<shiro:hasPermission name="hc:hpHealthcard:del">
							<a href="${ctx}/hc/hpHealthcard/delete?pkid=${hpHealthcard.pkid}" onclick="return confirmx('确认要注销该健康卡吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 注销</a>
						</shiro:hasPermission>--%>
					</c:if>
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