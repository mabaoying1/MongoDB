<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<title>业务表管理</title>
<meta content="default" name="decorator">
<script src="${ctxStatic}/jquery-combox/jquery.combox.js"
	type="text/javascript"></script>
<link type="text/css"
	href="${ctxStatic}/jquery-combox/styles/style.css" rel="stylesheet">
<script type="text/javascript">
	function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		if ($("#inputForm").valid()) {
			$("#inputForm").submit();
			return true;
		}

		return false;
	}
</script>

</head>
<body style="" class="  pace-done" id="">
	<div class="pace  pace-inactive">
		<div class="pace-progress" style="width: 100%;"
			data-progress-text="100%" data-progress="99">
			<div class="pace-progress-inner"></div>
		</div>
		<div class="pace-activity"></div>
	</div>


	<div class="wrapper wrapper-content">


		<!-- create table from db -->

		<!-- 业务表导入 -->
		<form:form id="inputForm" modelAttribute="genTable" action="${ctx}/gen/genTable/importTableFromDB" method="post" class="form-horizontal">
		<form:hidden path="id"/>
			<div class="control-group">
				<label class="control-label">表名:</label>
				<div class="controls">
					<form:select path="name" class="form-control">
						<form:options items="${tableList}" itemLabel="nameAndComments"  itemValue="name" htmlEscape="false"/>
					</form:select>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>