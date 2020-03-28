<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>实体卡库存台帐</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			laydate({
				elem: '#startDateYear',
				format:'YYYY'
			});
			laydate({
				elem: '#endDateYear',
				format:'YYYY'
			});

			laydate({
				elem: '#startDateMonth',
				format:'YYYY-MM'
			});
			laydate({
				elem: '#endDateMonth',
				format:'YYYY-MM'
			});

			laydate({
				elem: '#startDateDay',
				format:'YYYY-MM-DD'
			});
			laydate({
				elem: '#endDateDay',
				format:'YYYY-MM-DD'
			});
			var ss = '${hpRealCardStatistics.type}';
			if(ss == 1){
				$("#endDateYear").hide();
				$("#startDateYear").hide();
				$("#endDateYear").val("");
				$("#startDateYear").val("");

				$("#endDateMonth").hide();
				$("#startDateMonth").hide();
				$("#endDateMonth").val("");
				$("#startDateMonth").val("");

				$("#endDateDay").show();
				$("#startDateDay").show();
			}else if(ss == 2){
				$("#endDateYear").hide();
				$("#startDateYear").hide();
				$("#endDateYear").val("");
				$("#startDateYear").val("");

				$("#endDateMonth").show();
				$("#startDateMonth").show();

				$("#endDateDay").hide();
				$("#startDateDay").hide();
				$("#endDateDay").val("");
				$("#startDateDay").val("");
			}else if(ss == 3){
				$("#endDateYear").show();
				$("#startDateYear").show();


				$("#endDateMonth").hide();
				$("#startDateMonth").hide();
				$("#endDateMonth").val("");
				$("#startDateMonth").val("");

				$("#endDateDay").hide();
				$("#startDateDay").hide();
				$("#endDateDay").val("");
				$("#startDateDay").val("");
			}else{
				$("#endDateYear").show();
				$("#startDateYear").show();
				$("#endDateYear").val("");
				$("#startDateYear").val("");

				$("#endDateMonth").hide();
				$("#startDateMonth").hide();
				$("#endDateMonth").val("");
				$("#startDateMonth").val("");

				$("#endDateDay").hide();
				$("#startDateDay").hide();
				$("#endDateDay").val("");
				$("#startDateDay").val("");
			}

			$("#type").change(function () {
				if($(this).val() == 1){//日
					$("#endDateYear").hide();
					$("#startDateYear").hide();
					$("#endDateYear").val("");
					$("#startDateYear").val("");

					$("#endDateMonth").hide();
					$("#startDateMonth").hide();
					$("#endDateMonth").val("");
					$("#startDateMonth").val("");

					$("#endDateDay").show();
					$("#startDateDay").show();
				}else if($(this).val() == 2){//月
					$("#endDateYear").hide();
					$("#startDateYear").hide();
					$("#endDateYear").val("");
					$("#startDateYear").val("");

					$("#endDateMonth").show();
					$("#startDateMonth").show();

					$("#endDateDay").hide();
					$("#startDateDay").hide();
					$("#endDateDay").val("");
					$("#startDateDay").val("");
				}else if($(this).val() == 3){
					$("#endDateYear").show();
					$("#startDateYear").show();


					$("#endDateMonth").hide();
					$("#startDateMonth").hide();
					$("#endDateMonth").val("");
					$("#startDateMonth").val("");

					$("#endDateDay").hide();
					$("#startDateDay").hide();
					$("#endDateDay").val("");
					$("#startDateDay").val("");
				}
			});
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>机构发卡情况统计表</h5>
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
	<form:form id="searchForm" modelAttribute="hpRealCardStatistics" action="${ctx}/hc/hpRealCard/statistics/" method="post" class="form-inline">

		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">

			<span>日期：</span>
			<form:select path="type"  class="form-control m-b">
				<form:option value="3" label="年"/>
				<form:option value="2" label="月"/>
				<form:option value="1" label="日"/>
			</form:select>
		<%--	<input class="laydate-icon" type="text" id="startDate" name="startDate"  value="${hpRealCardStatistics.startDate}" />
			<input class="laydate-icon" type="text" id="endDate" name="endDate"  value="${hpRealCardStatistics.startDate}" />--%>

			<input id="startDateYear" name="startDateTmp" type="text" maxlength="20"  class="laydate-icon"
				   value="${hpRealCardStatistics.startDate}"/>
			<input type="text" id="endDateYear" name="endDateTmp" maxlength="20" class="laydate-icon"
				   value="${hpRealCardStatistics.endDate}"/>

			<input id="startDateDay" name="startDateTmp" type="text" maxlength="20"  class="laydate-icon"
				   value="${hpRealCardStatistics.startDate}"/>
			<input type="text" id="endDateDay" name="endDateTmp" maxlength="20" class="laydate-icon"
				   value="${hpRealCardStatistics.endDate}"/>

			<input id="startDateMonth" name="startDateTmp" type="text" maxlength="20"  class="laydate-icon"
				   value="${hpRealCardStatistics.startDate}"/>
			<input type="text" id="endDateMonth" name="endDateTmp" maxlength="20" class="laydate-icon"
				   value="${hpRealCardStatistics.endDate}"/>
			<span>区域：</span>
			<sys:treeselect id="area" name="areaId" value="${hpRealCardStatistics.areaId}" labelName="areaName" labelValue="${hpRealCardStatistics.areaName}" title="区域" url="/sys/area/treeData?type=3" cssClass=" form-control input-sm" allowClear="true" notAllowSelectParent="false"/>
		</div>
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">

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
			<%--<th> <input type="checkbox" class="i-checks"></th>--%>
			<th  class="sort-column iccardid">商户名</th>
			<th  class="sort-column type">开卡日期</th>
			<th  class="sort-column type">开卡地区</th>
				<th  class="sort-column type">开卡数量</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hpRealCardStatistics">
			<tr>
					<%--<td><c:if test="${ hpRealCard.status ==1}">
                     <input type="checkbox" id="${hpRealCard.pkid}" class="i-checks">
                    </c:if></td>--%>
				<td >${hpRealCardStatistics.merName}</td>
				<td>
						${hpRealCardStatistics.createDates}
				</td>
				<td>
						${hpRealCardStatistics.areaName}
				</td>
				<td>
						${hpRealCardStatistics.count}
				</td>
			</tr>
		</c:forEach>
			<tr>
				<td>合计</td>
				<td colspan="2"></td>
				<td>${totalC}</td>
			</tr>
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