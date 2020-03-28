<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>持卡人管理</title>
<meta name="decorator" content="default" />
	<script src="/static/base64/jquery.base64.js" type="application/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {

	});

	//跳转健康档案页面
	function mpi(ID){
		//base64位编码
		var IDCARD = window.btoa(ID);
		//organizationcode是“CardManager”的base64编码
		var organizationcode = "Q2FyZE1hbmFnZXI=";
		var url = "http://55.0.2.6:8211/ehrview/redirect?idCard="+IDCARD+"&organizationcode="+organizationcode+"&user=c3lzdGVt&password=MTIz";
		window.open(url);
	}
</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="ibox">
			<div class="ibox-title">
				<h5>持卡人列表</h5>
				<div class="ibox-tools">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="dropdown-toggle" data-toggle="dropdown" href="#"> <i
						class="fa fa-wrench"></i>
					</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="#">选项1</a></li>
						<li><a href="#">选项2</a></li>
					</ul>
					<a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
			</div>

			<div class="ibox-content">
				<sys:message content="${message}" />

				<!--查询条件-->
				<div class="row">
					<div class="col-sm-12">
						<form:form id="searchForm" modelAttribute="hpCardholder"
							action="${ctx}/hc/hpCardholder/" method="post"
							class="form-inline">
							<input id="pageNo" name="pageNo" type="hidden"
								value="${page.pageNo}" />
							<input id="pageSize" name="pageSize" type="hidden"
								value="${page.pageSize}" />
							<table:sortColumn id="orderBy" name="orderBy"
								value="${page.orderBy}" callback="sortOrRefresh();" />
							<!-- 支持排序 -->
							<div class="form-group">
								<span>健康档案号：</span>
								<form:input path="healthId" htmlEscape="false" maxlength="30"
											class=" form-control input-sm" />
								<span>手机号：</span>
								<form:input path="phone" htmlEscape="false" maxlength="30"
											class=" form-control input-sm" />
								<span>姓名：</span>
								<form:input path="name" htmlEscape="false" maxlength="30"
									class=" form-control input-sm" />
								<span>证件号码：</span>
								<form:input path="docuId" htmlEscape="false" maxlength="30"
											class=" form-control input-sm" /></br></br>
								<span>新农合卡号：</span>
								<form:input path="newFarmid" htmlEscape="false" maxlength="30"
											class=" form-control input-sm" />
							</div>
						</form:form>
						<br />
					</div>
				</div>

				<!-- 工具栏 -->
				<div class="row">
					<div class="col-sm-12">
						<div class="pull-left">
							<shiro:hasPermission name="hc:hpCardholder:add">
								<table:addRow url="${ctx}/hc/hpCardholder/form" title="持卡人档案"></table:addRow>
								<!-- 增加按钮 -->
							</shiro:hasPermission>
							<shiro:hasPermission name="hc:hpCardholder:edit">
								<table:editRow url="${ctx}/hc/hpCardholder/form" title="持卡人档案"
									id="contentTable" key="identityId"></table:editRow>
								<!-- 编辑按钮 -->
							</shiro:hasPermission>
							<%--
							<shiro:hasPermission name="hc:hpCardholder:del">
								<table:delRow url="${ctx}/hc/hpCardholder/deleteAll"
									id="contentTable" label="销户" msg="您确定要对该人进行销户处理吗？"></table:delRow>
								<!-- 删除按钮 -->
							</shiro:hasPermission>
							--%>
							<shiro:hasPermission name="hc:hpCardholder:import">
								<table:importExcel url="${ctx}/hc/hpCardholder/import"></table:importExcel>
								<!-- 导入按钮 -->
							</shiro:hasPermission>
							<shiro:hasPermission name="hc:hpCardholder:export">
								<table:exportExcel url="${ctx}/hc/hpCardholder/export"></table:exportExcel>
								<!-- 导出按钮 -->
							</shiro:hasPermission>
							<button class="btn btn-white btn-sm " data-toggle="tooltip"
								data-placement="left" onclick="sortOrRefresh()" title="刷新">
								<i class="glyphicon glyphicon-repeat"></i> 刷新
							</button>

						</div>
						<div class="pull-right">
							<button class="btn btn-primary btn-rounded btn-outline btn-sm "
								onclick="search()">
								<i class="fa fa-search"></i> 查询
							</button>
							<button class="btn btn-primary btn-rounded btn-outline btn-sm "
								onclick="reset()">
								<i class="fa fa-refresh"></i> 重置
							</button>
						</div>
					</div>
				</div>

				<!-- 表格 -->
				<div style="overflow: auto; width:100%;">
				<table id="contentTable"
					class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable" style="width: 1300px;">
					<thead>
						<tr>
							<th><input type="checkbox" class="i-checks"></th>
							<th width="70">姓名</th>
							<th width="80">证件类型</th>
							<th>证件号码</th>
							<th>手机号</th>
							<th>性别</th>
							<th>年龄</th>
							<th>文化程度</th>
							<th>户籍地址</th>
							<th>居住地</th>
							<th width="80">登记时间</th>
							<th>状态</th>
							<th>来源</th>
							<th style="width: 160px;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list}" var="hpCardholder">
							<tr>
								<td><c:if test="${ hpCardholder.status ==1}">
										<input type="checkbox" id="${hpCardholder.identityId}"
											class="i-checks" />
									</c:if></td>
								<td>${hpCardholder.name}</td>
								<td>${fns:getDictLabel(hpCardholder.docuType , 'std_id_type', '居民身份证')}</td>
								<td>${hpCardholder.docuId}</td>
								<td>${hpCardholder.phone}</td>
								<td>${fns:getDictLabel(hpCardholder.sex , 'sex', '男')}</td>
								<td>${hpCardholder.age}</td>
								<td>${fns:getDictLabel(hpCardholder.educationLevel,'std_education','大学')}</td>
								<td>${hpCardholder.countryname}${hpCardholder.provname}${hpCardholder.cityname}${hpCardholder.countyname}${hpCardholder.townname}${hpCardholder.villagename}</td>
								<td>${hpCardholder.countryname2}${hpCardholder.provname2}${hpCardholder.cityname2}${hpCardholder.countyname2}${hpCardholder.townname2}${hpCardholder.villagename2}${hpCardholder.address2 }</td>
								<td><fmt:formatDate value="${hpCardholder.createDate}"
													pattern="yyyy-MM-dd" /></td>
								<td>${fns:getDictLabel(hpCardholder.status,'cardholder_status','正常')}</td>
								<td>${fns:getDictLabel(hpCardholder.source,'data_source','窗口发卡')}</td>
								<td><shiro:hasPermission name="hc:hpCardholder:view">
										<a href="#"
											onclick="openDialogView('查看持卡人档案', '${ctx}/hc/hpCardholder/form?identityId=${hpCardholder.identityId}','800px', '500px')"
											class="btn btn-info btn-xs"><i class="fa fa-search-plus"></i>
											查看</a>
									</shiro:hasPermission>
									<shiro:hasPermission name="hc:hpCardholder:edit">
										<a href="#"
										   onclick="mpi('${hpCardholder.docuId}')"
										   class="btn btn-info btn-xs"><i class="fa fa-search-plus"></i>
											健康档案</a>
									</shiro:hasPermission>
									<c:if test="${hpCardholder.status == 1}">
										<shiro:hasPermission name="hc:hpCardholder:edit">
											<a href="#"
												onclick="openDialog('修改持卡人档案', '${ctx}/hc/hpCardholder/form?identityId=${hpCardholder.identityId}','800px', '500px')"
												class="btn btn-success btn-xs"><i class="fa fa-edit"></i>
												修改</a>
										</shiro:hasPermission>
										<shiro:hasPermission name="hc:hpCardholder:del">
										<a href="${ctx}/hc/hpCardholder/delete?identityId=${hpCardholder.identityId}" onclick="return confirmx('您确定要对该人进行销户处理吗？', this.href)" class="btn btn-danger btn-xs"><i class="fa fa-trash"></i>销户</a>
										</shiro:hasPermission>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
				<!-- 分页代码 -->
				<table:page page="${page}"></table:page>
				<br /> <br />
			</div>
		</div>
	</div>
</body>
</html>