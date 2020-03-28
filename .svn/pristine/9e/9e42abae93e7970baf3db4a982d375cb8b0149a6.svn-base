\<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>首页</title>
	<meta name="decorator" content="default"/>
    <script src="${ctxStatic}/ace/assets/js/flot/jquery.flot.js"></script>
    <script src="${ctxStatic}/ace/assets/js/flot/jquery.flot.resize.js"></script>
    <script src="${ctxStatic}/ace/assets/js/flot/jquery.flot.pie.js"></script>
    <script type="text/javascript">
		$(document).ready(function() {
		     WinMove();

            var barOptions = {
                series: {
                    lines: {
                        show: true,
                        lineWidth: 2,
                        fill: true,
                        fillColor: {
                            colors: [{
                                opacity: 0.0
                            }, {
                                opacity: 0.0
                            }]
                        }
                    }
                },
                xaxis: {
                    tickDecimals: 0
                },
                yaxis: {
                    tickSize:2
                },
                colors: ["#1ab394"],
                grid: {
                    color: "#999999",
                    hoverable: true,
                    clickable: true,
                    tickColor: "#D4D4D4",
                    borderWidth:0
                },
                legend: {
                    show: true
                },
                tooltip: true,
                tooltipOpts: {
                    content: "x: %x, y: %y"
                }
            };

            var data = [];
            <c:forEach items="${data}" var="item">
                data.push([${item.key},${item.value}]);
            </c:forEach>
            var barData = {
                label: "bar",
                data:data
            };
            $.plot($("#flot-line-chart"), [barData], barOptions);
		});

        function doClick(click_obj) {
            self.$ = parent.$;
            $(".J_menuItem").each(function(){
                if($(this).attr("href") == $(click_obj).attr("data-url")){
                    $(this,parent.document).trigger("click");
                }
            });
        }
	</script>
</head>
<body class="gray-bg">
<%--
   <div class="row  border-bottom white-bg dashboard-header">
        <div class="col-sm-12">

            <blockquote class="text-info" style="font-size:14px">

            JeePlus是一款基于代码生成器的智能快速开发平台，引领新开发模式(智能开发\在线开发\插件开发)， 可以帮助解决Java项目80%的重复工作，让开发更多关注业务逻辑。既能快速提高开发效率，帮助公司节省人力成本，同时又不失灵活性。 
    JeePlus快速开发宗旨是：简单功能由代码生成器生成使用; 复杂业务采用表单自定义，业务流程使用工作流来实现、扩展出任务接口，供开发编写业务逻辑。 实现了流程任务节点和任务接口的灵活配置，既保证了公司流程的保密性，又减少了开发人员的工作量
    			<br/>
               <br>JeePlus采用了目前极为流行的扁平化响应式的设计风格，可以完美的兼容电脑，pad，手机等多个平台。前端UI采用了<a target="_blank" href="http://wrapbootstrap.com/preview/WB0R5L90S">INSPINIA</a>(点击访问）为原型开发，JeePlus v2.0发布时作者将bootstrap升级到了最新的3.3.4。
                <br>…………
            </blockquote>
            <hr>
        </div>
    </div>
       --%>
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-4">

                <div class="ibox float-e-margins">
                     <div class="ibox-title">
                        <h5>基本信息</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <%--
                            <a class="dropdown-toggle" data-toggle="dropdown" href="index.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="index.html#">选项1</a>
                                </li>
                                <li><a href="index.html#">选项2</a>
                                </li>
                            </ul>
                            --%>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    
                    <div class="ibox-content">
                        <%--
                        <p> JeePlus开发平台采用 SpringMVC + MyBatis + BootStrap + Apache Shiro + Ehcache 开发组件 的基础架构,采用面向声明的开发模式， 基于泛型编写极少代码即可实现复杂的数据展示、数据编辑、
表单处理等功能，再配合代码生成器的使用,将J2EE的开发效率提高5倍以上，可以将代码减少50%以上。--%>
                        <div class="row">
                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <tbody>
                                        <tr>
                                            <td><strong>姓名</strong></td>
                                            <td>${user.name}</td>
                                        </tr>
                                        <tr>
                                            <td><strong>手机</strong></td>
                                            <td>${user.mobile}</td>
                                        </tr>
                                        <tr>
                                            <td><strong>机构</strong></td>
                                            <td><font color="red">${user.company.name}</font></td>
                                        </tr>
                                        <tr>
                                            <td><strong>区域</strong></td>
                                            <td>${user.area.name}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <strong>上次登录</strong>
                                    IP: ${user.oldLoginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${user.oldLoginDate}" type="both" dateStyle="full"/>
                                </div>
                        </div>
                    </div>
                </div>
              
            </div>
            <div class="col-sm-8">
                <div class="ibox float-e-margins">
                     <div class="ibox-title">
                        <h5><a data-url="${fns:getAdminPath()}/hc/hpApplycard?operate=audit" onclick="doClick(this);">待审核信息（${count}）</a></h5>
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
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                    <th>姓名</th>
                                    <th>证件号码</th>
                                    <th>手机号码</th>
                                    <th>生日</th>
                                    <th>性别</th>
                                    <th>名族</th>
                                    <th>居住地</th>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.list}" var="hpApplycard">
                                  <tr>
                                    <td>${hpApplycard.name}</td>
                                    <td>${hpApplycard.docuId}</td>
                                    <td>${hpApplycard.phone}</td>
                                    <td><fmt:formatDate value="${hpApplycard.birth}" type="date"/></td>
                                    <td>${fns:getDictLabel(hpApplycard.sex, 'sex', '未知')}</td>
                                    <td>${fns:getDictLabel(hpApplycard.nation, 'std_nation', '未知')}</td>
                                    <td>${hpApplycard.address2}</td>
                                  </tr>
                                </c:forEach>
                                <c:forEach begin="${fn:length(page.list)}" end="3">
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
            </div>
            </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>开卡统计(${year}年)</h5>
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

                        <div class="flot-chart">
                            <div class="flot-chart-content" id="flot-line-chart">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>