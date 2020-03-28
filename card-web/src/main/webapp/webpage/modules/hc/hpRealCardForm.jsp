<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>实体卡管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        var validateForm;
        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            if (validateForm.form()) {
                $("#inputForm").submit();
                return true;
            }

            return false;
        }
        $(document).ready(function () {
            validateForm = $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });



        });
    </script>
</head>
<body>
<form:form id="inputForm" modelAttribute="hpRealCard" action="${ctx}/hc/hpRealCard/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right">持卡人姓名：</label></td>
            <td class="width-35">
                &nbsp;&nbsp;&nbsp;${hpRealCard.hpHealthcard.hpCardholder.name}
            </td>
            <td class="width-15 active"><label class="pull-right"></label></td>
            <td class="width-35">
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right"><font color="red">*</font>健康e卡卡号：</label></td>

                <td class="width-35">
                    <form:input path="cardPkid" htmlEscape="false" maxlength="30" class="form-control required"/>
                </td>

            <td class="width-15 active"><label class="pull-right">实体卡卡号：</label></td>
            <td class="width-35">
                <form:input path="iccardid" htmlEscape="false" maxlength="30" class="form-control required"/>
            </td>

        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">实体卡类型：</label></td>
            <td class="width-35">
                <form:select path="type" class="form-control">
                    <form:options items="${fns:getDictList('card_type')}" itemLabel="label" itemValue="value"
                                  htmlEscape="false"/>
                </form:select>
            </td>
            <td class="width-15 active"><label class="pull-right">卡状态：</label></td>
            <td class="width-35">
                <span>&nbsp;&nbsp;&nbsp;${fns:getDictLabel(hpRealCard.status , 'realcard_status','正常')}</span></td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">实体卡创建/绑定医院记录：</label></td>
            <td class="width-35" id="merNameList" colspan="3">
                <c:forEach items="${merNameList}" var="merNameMap">
                    <c:if test="${merNameMap.FLAG0 == '1'}">
                        <p>【医院名称】${merNameMap.MERCHANTNAME}【创建时间】${merNameMap.CREATEDATE}</p><br>
                    </c:if>
                    <c:if test="${merNameMap.FLAG0 == '2'}">
                        <p>【医院名称】${merNameMap.MERCHANTNAME}【绑定时间】${merNameMap.CREATEDATE}</p><br>
                    </c:if>
                </c:forEach>
            </td>
        </tr>
        </tbody>
    </table>
</form:form>
</body>
</html>