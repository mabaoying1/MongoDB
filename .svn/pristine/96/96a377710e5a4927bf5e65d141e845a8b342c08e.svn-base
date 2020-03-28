<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>开卡居民统计表</title>
    <meta name="decorator" content="default" />
    <script type="text/javascript">
        $(document).ready(function() {
            laydate({
                elem: '#beginDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
                event: 'focus', //响应事件。如果没有传入event，则按照默认的click
                format: 'YYYY-MM-DD'
            });
            laydate({
                elem: '#endDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
                event: 'focus', //响应事件。如果没有传入event，则按照默认的click
                format: 'YYYY-MM-DD'
            });
        });
    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="ibox">
        <div class="ibox-title">
            <h5>开卡居民统计表</h5>
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
                               action="${ctx}/analysis/resistat/" method="post"
                               class="form-inline">
                        <input id="pageNo" name="pageNo" type="hidden"
                               value="${page.pageNo}" />
                        <input id="pageSize" name="pageSize" type="hidden"
                               value="${page.pageSize}" />
                        <table:sortColumn id="orderBy" name="orderBy"
                                          value="${page.orderBy}" callback="sortOrRefresh();" />
                        <!-- 支持排序 -->
                        <div class="form-group">
                            <span>开卡时间：</span>
                            <input id="beginDate" name="beginDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
                                   value="${hpCardholder.beginDate}"/><label>&nbsp;--&nbsp;</label>
                            <input type="text" id="endDate" name="endDate" maxlength="20" class="laydate-icon form-control layer-date input-sm" value="${hpCardholder.endDate}"/>
                            <span>户籍：</span>
                            <sys:treeselect id="area" name="area.id" value="${hpCardholder.area.id}" labelName="area.name" labelValue="${hpCardholder.area.name}" title="区域" url="/sys/area/treeData?type=3" cssClass=" form-control input-sm" allowClear="true" notAllowSelectParent="false"/>
                        </div>
                    </form:form>
                    <br />
                </div>
            </div>

            <!-- 工具栏 -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="pull-left">
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
            <table id="contentTable"
                   class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
                <thead>
                <tr>
                    <th>开卡时间</th>
                    <th>居民户籍</th>
                    <th>开卡人数</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.list}" var="hpCardholder">
                    <tr>
                        <td>${hpCardholder.createDateStr}</td>
                        <td>${hpCardholder.countryname}${hpCardholder.provname}${hpCardholder.cityname}${hpCardholder.countyname}</td>
                        <td>${hpCardholder.count}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- 分页代码 -->
            <table:page page="${page}"></table:page>
            <br /> <br />
        </div>
    </div>
</div>
</body>
</html>
