<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>健康e卡审核表</title>
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
		<h5>健康e卡审核表</h5>
		<div class="ibox-tools">
			<a class="collapse-link">
				<i class="fa fa-chevron-up"></i>
			</a>
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
	<form:form id="searchForm" modelAttribute="hpApplycard" action="${ctx}/hc/hpApplycard/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>姓名：</span><form:input path="name" htmlEscape="false" maxlength="30"  class=" form-control input-sm"/>
			<span>身份证号码：</span><form:input path="identityId" htmlEscape="false" maxlength="30"  class=" form-control input-sm"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
				<shiro:hasPermission name="hc:hpApplycard:audit">
				    <table:auditRow url="${ctx}/hc/hpApplycard/auditForm" title="健康卡申请记录" id="contentTable" key="pkid"></table:auditRow><!-- 审核按钮 -->
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
				<th>姓名</th>
				<th  class="sort-column docu_id">证件号码</th>
				<th  class="sort-column docu_type">证件类型</th>
				<th>手机号码</th>
				<th  class="sort-column sex">性别</th>
				<th  class="sort-column birth">生日</th>
				<th>发卡机构</th>
				<th>居住地</th>
				<c:if test="${operate == 'audit' }">
				<th>数据来源</th>
				</c:if>
				<c:if test="${operate != 'audit' }">
				<th>数据来源</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hpApplycard">
			<tr>
				<td> <input type="checkbox" id="${hpApplycard.pkid}" class="i-checks"></td>
				<td ><a  href="${ctx}/hc/hpApplycard/form?operate=view&pkid=${hpApplycard.pkid}" >
					${hpApplycard.name}
				</a></td>
				<td ><a  href="${ctx}/hc/hpApplycard/form?operate=view&pkid=${hpApplycard.pkid}">
					${hpApplycard.docuId}
				</a></td>
				<td>${fns:getDictLabel(hpApplycard.docuType, 'std_id_type', '未知')}</td>
				<td>${hpApplycard.phone}</td>
				<td>${fns:getDictLabel(hpApplycard.sex, 'sex', '未知')}</td>
				<td><fmt:formatDate value="${hpApplycard.birth}" type="date"/></td>
				<td>${hpApplycard.office.name}</td>
				<td>${hpApplycard.provname2}${hpApplycard.cityname2}${hpApplycard.countyname2}${hpApplycard.townname2}${hpApplycard.address2}</td>
				<c:if test="${operate == 'audit' }">
				<td>${fns:getDictLabel(hpApplycard.source, 'data_source', '未知')}</td>
				</c:if>
				<c:if test="${operate != 'audit' }">
				<td>${fns:getDictLabel(hpApplycard.source, 'data_source', '未知')}</td>
				</c:if>
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