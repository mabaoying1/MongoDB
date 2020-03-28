<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@include file="/webpage/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>密码过期</title>
    <%@include file="/webpage/include/head.jsp" %>
    <meta name="decorator" content="default"/>
    <script src="${ctxStatic}/jquery-validation/1.14.0/jquery.validate.js" type="text/javascript"></script>
    <script src="${ctxStatic}/jquery-validation/1.14.0/localization/messages_zh.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/common/jquery-validate-method.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#oldPassword").focus();

            $("#inputForm").validate({
                rules: {
                    newPassword:{isPwdNumAndAlpha:true},
                    confirmNewPassword:{isPwdNumAndAlpha:true}
                },
                messages: {
                    confirmNewPassword: {equalTo: "输入与上面相同的密码"}
                },
                submitHandler: function(form){
                    $("#btnSubmit").click();
                }
            });

            $("#btnSubmit").click(function(){
                $.ajax({
                    type:"GET",
                    url:"${ctx}/sys/user/modifyExpiredPwd",
                    data:{oldPassword:$("#oldPassword").val(),newPassword:$("#newPassword").val()},
                    dataType:"json",
                    success:function(data){
                        alert(data.msg);
                        if(data.code =="ok"){
                            window.location.href = "${ctx}/login";
                        }
                    }

                })
            })
        });



    </script>
</head>
<body>
<div class="container-fluid">
    <div class="page-header"><h1>密码已过期，请修改密码</h1></div>
    <jsp:useBean id="user" class="com.healthpay.modules.sys.entity.User" scope="request" ></jsp:useBean>
        <%--<form:hidden path="id"/>--%>
        <div class="control-group">
        </div>
        <form id="inputForm" >
        <div class="control-group">
            <label class="col-sm-3 control-label"><font color="red">*</font>旧密码:</label>
            <div class="controls">
                <input id="oldPassword" name="oldPassword" type="password" value="" maxlength="50" minlength="3"  class="form-control  max-width-250 required"/>
            </div>
        </div>
        <div class="control-group">
            <label class="col-sm-3 control-label"><font color="red">*</font>新密码:</label>
            <div class="controls">
                <input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="8" class="form-control  max-width-250 required"/>
            </div>
        </div>
        <div class="control-group">
            <label class="col-sm-3 control-label"><font color="red">*</font>确认新密码:</label>
            <div class="controls">
                <input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="8" class="form-control  max-width-250 required" equalTo="#newPassword"/>
            </div>
        </div>
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="submit"  value="保 存"/>
        </div>
        </form>
</div>
</body>
</html>