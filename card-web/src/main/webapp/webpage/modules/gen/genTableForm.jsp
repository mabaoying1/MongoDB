<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>表单管理</title>
	<meta content="default" name="decorator">
	<script src="${ctxStatic}/jquery-combox/jquery.combox.js" type="text/javascript"></script>
	<link type="text/css" href="${ctxStatic}/jquery-combox/styles/style.css" rel="stylesheet">
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $("#inputForm").submit();
			  return true;
		  }
	
		  return false;
		}
		$(document).ready(function() {
		//	$("#comments").focus();
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					$("input[type=checkbox]").each(function(){
						$(this).after("<input type=\"hidden\" name=\""+$(this).attr("name")+"\" value=\""
								+($(this).attr("checked")?"1":"0")+"\"/>");
						$(this).attr("name", "_"+$(this).attr("name"));
					});
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			resetColumnNo();

			//判断是否是树表
			$("#tableType").change(function(){
				if($("#tableType").val() == "3"){
					addForTreeTable();
				}else{
					removeForTreeTable();
				}

			});

			var fromIndex, toIndex;
			$("#contentTable1").tableDnD({//支持拖拽
				
			    onDragClass: "myDragClass",
			    onDrop: function(table, row) {
			    	toIndex = $(row).index();//移动后的位置

			    	//同步页面属性
			 		var targetTR2 = $("#tab-2 #contentTable2 tbody tr:eq("+toIndex+")");//同步页面属性,目标元素位置（移动到该元素后面）
			  		var objTR2 = $("#tab-2 #contentTable2 tbody tr:eq("+fromIndex+")");//同步页面属性, 需要移动的元素
			  		if(fromIndex < toIndex){
			  			objTR2.insertAfter(targetTR2); 
			  		}else{
			  			objTR2.insertBefore(targetTR2);
			  		}

			  		//同步自定义java对象
			 		var targetTR3 = $("#tab-3 #contentTable3 tbody tr:eq("+toIndex+")");//同步页面属性,目标元素位置（移动到该元素后面）
			  		var objTR3 = $("#tab-3 #contentTable3 tbody tr:eq("+fromIndex+")");//同步页面属性, 需要移动的元素
			  		if(fromIndex < toIndex){
			  			objTR3.insertAfter(targetTR3); 
			  		}else{
			  			objTR3.insertBefore(targetTR3);
			  		}
			  		
					resetColumnNo();
					
			    },
				onDragStart: function(table, row) {
			    	fromIndex = $(row).index();//移动前的位置
				}
			});
		});

		function resetColumnNo(){
			$("#tab-3 #contentTable3 tbody tr").each(function(index,element){
				 $(this).find("span[name*=columnList],select[name*=columnList],input[name*=columnList]").each(function(){
					 var name = $(this).attr("name");
					 var attr_name = name.split(".")[1];
					 var newName = "columnList["+index+"]."+attr_name;
					 $(this).attr("name", newName);
					 if(name.indexOf(".sort")>=0){
							
						 $(this).val(index);
						 $(this).next().text(index);
					 }
				 });
				 
				 $(this).find("label[id*=columnList]").each(function(){
					 var id = $(this).attr("id");
					 var attr_id = id.split(".")[1];
					 var newId = "columnList["+index+"]."+attr_id;
					 $(this).attr("id", newId);
					 $(this).attr("for", "columnList["+index+"].jdbcType");
				 });

				 $(this).find("input[name*=name]").each(function(){
					 var name = $(this).attr("name");
					 var attr_name = name.split(".")[1];
					 var newName = "page-columnList["+index+"]."+attr_name;
					 $(this).attr("name", newName);
				 });
				 
			  });
			$("#tab-2 #contentTable2 tbody tr").each(function(index,element){
				 $(this).find("span[name*=columnList],select[name*=columnList],input[name*=columnList]").each(function(){
					 var name = $(this).attr("name");
					 var attr_name = name.split(".")[1];
					 var newName = "columnList["+index+"]."+attr_name;
					 $(this).attr("name", newName);
					 if(name.indexOf(".sort")>=0){
							
						 $(this).val(index);
						 $(this).next().text(index);
					 }
				 });
				 
				 $(this).find("label[id*=columnList]").each(function(){
					 var id = $(this).attr("id");
					 var attr_id = id.split(".")[1];
					 var newId = "columnList["+index+"]."+attr_id;
					 $(this).attr("id", newId);
					 $(this).attr("for", "columnList["+index+"].jdbcType");
				 });

				 $(this).find("input[name*=name]").each(function(){
					 var name = $(this).attr("name");
					 var attr_name = name.split(".")[1];
					 var newName = "page-columnList["+index+"]."+attr_name;
					 $(this).attr("name", newName);
				 });
				 
			  });
			$("#tab-1 #contentTable1 tbody tr").each(function(index,element){
				 $(this).find("span[name*=columnList],select[name*=columnList],input[name*=columnList]").each(function(){
					 var name = $(this).attr("name");
					 var attr_name = name.split(".")[1];
					 var newName = "columnList["+index+"]."+attr_name;
					 $(this).attr("name", newName);//重新对name排序
					 if(name.indexOf(".sort")>=0){
							
						 $(this).val(index);
						 $(this).next().text(index);
					 }
				 });
				 
				 $(this).find("label[id*=columnList]").each(function(){
					 var id = $(this).attr("id");
					 var attr_id = id.split(".")[1];
					 var newId = "columnList["+index+"]."+attr_id;
					 $(this).attr("id", newId);
					 $(this).attr("for", "columnList["+index+"].jdbcType");
				 });

				 $(this).find("input[name*=name]").change(function(){//修改数据库列名，同时同步页面列名
					 var name = $(this).attr("name");
					 var newName = "page-"+name;
					 $("#tab-2 #contentTable2 tbody tr input[name='"+newName+"']").val($(this).val());

				 });

				 $(this).find("input[name*=name]").change(function(){//修改数据库列名，同时同步自定义对象列名
					 var name = $(this).attr("name");
					 var newName = "page-"+name;
					 $("#tab-3 #contentTable3 tbody tr input[name='"+newName+"']").val($(this).val());

				 });
				 
			  });

			//预定义数据库字段类型
			$('#contentTable1 tbody tr span[name*=jdbcType]').combox({datas:['varchar(64)','nvarchar(64)','integer','double','datetime','longblob','longtext']});

			$('#contentTable2 tbody tr select[name*=javaType]').change(function(){ //自定义JAVA类型
				var selectedValue = $(this).children('option:selected').val();
				var _this = $(this);
				if(selectedValue == 'Custom' || $(this).children('option:selected').attr("class") == 'newadd'){
					 top.layer.open({
					        type: 1,
					        title:'输入自定义java对象',
					        area: ['600px', '360px'],
					        shadeClose: true, //点击遮罩关闭
					        content: '<div class="wrapper wrapper-content"><div class="col-md-12">'+
					            '<div class="form-group">'+
					       ' <label class="col-sm-3 control-label">包名：</label>'+
					       ' <div class="col-sm-9">'+
					           ' <input type="text" id="packagePath" name="" class="form-control required" placeholder="请输入自定义对象所在的包路径"> <span class="help-block m-b-none">必须是存在的package</span>'+

					       ' </div>'+
					   ' </div>'+
					   ' <div class="form-group">'+
					       ' <label class="col-sm-3 control-label">类名：</label>'+
					       ' <div class="col-sm-9">'+
					           ' <input type="text" id="className" name="" class="form-control required" placeholder="请输入自定义对象的类名"> <span class="help-block m-b-none">必须是存在的class对象</span>'+

					       ' </div>'+
					   ' </div>'+
					'</div></div>',
					        btn: ['确定', '关闭'],
						    yes: function(index, layero){
						    	 var packagePath = top.$("#packagePath").val();
						    	 var className = top.$("#className").val();
						    	 var package_class = packagePath+"."+className;
						    	 var option = top.$("<option>").val(package_class).text(className);
						    	
						    	// _this.append(option);
						    	if(className.trim() == '' || packagePath.trim() == ''){
						    		top.layer.alert('包名和类名都不允许为空!', {icon: 0});
						    		return;
		
						    	}
						    	 _this.children('option:selected').text(className);
						    	 _this.children('option:selected').val(package_class);
						    	 _this.children('option:selected').attr("class","newadd");
						    	// _this.eq(1).attr("selected",true);
						    	// _this.find("option[text='"+className+"']").attr("selected",true);
						        	 top.layer.close(index);//关闭对话框。
								
							  },
							  cancel: function(index){ 
						       }
					    });

					    if(selectedValue != 'Custom' && $(this).children('option:selected').attr("class") == 'newadd'){
							 top.$("#packagePath").val($(this).children('option:selected').val().substring(0, $(this).children('option:selected').val().lastIndexOf('.')));
							 top.$("#className").val($(this).children('option:selected').text())

					    }
				}
				//var p1=$(this).children('option:selected').val();//这就是selected的值 
				//var p2=$('#param2').val();//获取本页面其他标签的值 
			
				}) 
			//$('#contentTable2 tbody tr select[name*=javaType]').combox({datas:['varchar(64)','nvarchar(64)','integer','double','datetime','longblob','longtext']});
			//select name="columnList[0].javaType"
		}
		function addColumn(){
			  var column1 = $("#template1").clone();
			  column1.removeAttr("style");
			  column1.removeAttr("id");
			  var column2 = $("#template2").clone();
			  column2.removeAttr("style");
			  column2.removeAttr("id");
			  var column3 = $("#template3").clone();
			  column3.removeAttr("style");
			  column3.removeAttr("id");
			  $("#tab-1 #contentTable1 tbody").append(column1);
			  $("#tab-2 #contentTable2 tbody").append(column2);
			  $("#tab-3 #contentTable3 tbody").append(column3);
			  column1.find('input:checkbox').iCheck({ checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-blue', increaseArea: '20%' });
			  column2.find('input:checkbox').iCheck({ checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-blue', increaseArea: '20%' });
			  column3.find('input:checkbox').iCheck({ checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-blue', increaseArea: '20%' });
			  resetColumnNo();
				$("#contentTable1").tableDnD({//支持拖拽
					
				    onDragClass: "myDragClass",
				    onDrop: function(table, row) {
				    	toIndex = $(row).index();//移动后的位置

				    	//同步页面属性
				 		var targetTR2 = $("#tab-2 #contentTable2 tbody tr:eq("+toIndex+")");//同步页面属性,目标元素位置（移动到该元素后面）
				  		var objTR2 = $("#tab-2 #contentTable2 tbody tr:eq("+fromIndex+")");//同步页面属性, 需要移动的元素
				  		if(fromIndex < toIndex){
				  			objTR2.insertAfter(targetTR2); 
				  		}else{
				  			objTR2.insertBefore(targetTR2);
				  		}

				  		//同步自定义java对象
				 		var targetTR3 = $("#tab-3 #contentTable3 tbody tr:eq("+toIndex+")");//同步页面属性,目标元素位置（移动到该元素后面）
				  		var objTR3 = $("#tab-3 #contentTable3 tbody tr:eq("+fromIndex+")");//同步页面属性, 需要移动的元素
				  		if(fromIndex < toIndex){
				  			objTR3.insertAfter(targetTR3); 
				  		}else{
				  			objTR3.insertBefore(targetTR3);
				  		}

				  		
						resetColumnNo();
						
				    },
					onDragStart: function(table, row) {
				    	fromIndex = $(row).index();//移动前的位置
					}
				});
			return false;
		}

		function removeForTreeTable(){
			$("#tab-1 #contentTable1 tbody").find("#tree_11,#tree_12,#tree_13,#tree_14").remove();
			$("#tab-2 #contentTable2 tbody").find("#tree_21,#tree_22,#tree_23,#tree_24").remove();
			$("#tab-3 #contentTable3 tbody").find("#tree_31,#tree_32,#tree_33,#tree_34").remove();
			  resetColumnNo();
				return false;
		}
		function addForTreeTable(){
			// 如果是树表，不存在parent_id，则添加， 下面都是雷同。
			 if(!$("#tab-1 #contentTable1 tbody").find("input[name*=name][value=parent_id]").val()){
			  var column11 = $("#template1").clone();
			  column11.removeAttr("style");
			  column11.attr("id","tree_11");
			  column11.find("input[name*=name]").val("parent_id");
			  column11.find("input[name*=comments]").val("父级编号");
			  column11.find("span[name*=jdbcType]").val("varchar(64)");
			  column11.find("input[name*=isNull]").removeAttr("checked");
			  
			  var column21 = $("#template2").clone();
			  column21.removeAttr("style");
			  column21.attr("id","tree_21");
			  column21.find("input[name*=name]").val("parent_id");
			  column21.find("select[name*=javaType]").val("This");
			  column21.find("input[name*=javaField]").val("parent.id|name");
			  column21.find("input[name*=isList]").removeAttr("checked");
			  column21.find("select[name*=showType]").val("treeselect");

			  var column31 = $("#template3").clone();
			  column31.removeAttr("style");
			  column31.attr("id","tree_31");
			  column31.find("input[name*=name]").val("parent_id");
			  
			  $("#tab-1 #contentTable1 tbody").append(column11);
			  $("#tab-2 #contentTable2 tbody").append(column21);
			  $("#tab-3 #contentTable3 tbody").append(column31);
			  column11.find('input:checkbox').iCheck({ checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-blue', increaseArea: '20%' });
			  column21.find('input:checkbox').iCheck({ checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-blue', increaseArea: '20%' });
			  column31.find('input:checkbox').iCheck({ checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-blue', increaseArea: '20%' });
			 }

			 if(!$("#tab-1 #contentTable1 tbody").find("input[name*=name][value=parent_ids]").val()){
			  var column12 = $("#template1").clone();
			  column12.removeAttr("style");
			  column12.attr("id","tree_12");
			  column12.find("input[name*=name]").val("parent_ids");
			  column12.find("input[name*=comments]").val("所有父级编号");
			  column12.find("span[name*=jdbcType]").val("varchar(2000)");
			  column12.find("input[name*=isNull]").removeAttr("checked");
			  
			  var column22 = $("#template2").clone();
			  column22.removeAttr("style");
			  column22.attr("id","tree_22");
			  column22.find("input[name*=name]").val("parent_ids");
			  column22.find("select[name*=javaType]").val("String");
			  column22.find("input[name*=javaField]").val("parentIds");
			  column22.find("select[name*=queryType]").val("like");
			  column22.find("input[name*=isList]").removeAttr("checked");

			  var column32 = $("#template3").clone();
			  column32.removeAttr("style");
			  column32.attr("id","tree_32");
			  column32.find("input[name*=name]").val("parent_ids");
			  
			  $("#tab-1 #contentTable1 tbody").append(column12);
			  $("#tab-2 #contentTable2 tbody").append(column22);
			  $("#tab-3 #contentTable3 tbody").append(column32);
			  column12.find('input:checkbox').iCheck({ checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-blue', increaseArea: '20%' });
			  column22.find('input:checkbox').iCheck({ checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-blue', increaseArea: '20%' });
			  column32.find('input:checkbox').iCheck({ checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-blue', increaseArea: '20%' });
			 }

			 if(!$("#tab-1 #contentTable1 tbody").find("input[name*=name][value=name]").val()){
			  var column13 = $("#template1").clone();
			  column13.removeAttr("style");
			  column13.attr("id","tree_13");
			  column13.find("input[name*=name]").val("name");
			  column13.find("input[name*=comments]").val("名称");
			  column13.find("span[name*=jdbcType]").val("varchar(100)");
			  column13.find("input[name*=isNull]").removeAttr("checked");
			  
			  var column23 = $("#template2").clone();
			  column23.removeAttr("style");
			  column23.attr("id","tree_23");
			  column23.find("input[name*=name]").val("name");
			  column23.find("select[name*=javaType]").val("String");
			  column23.find("input[name*=javaField]").val("name");
			  column23.find("input[name*=isQuery]").attr("checked","checked");
			  column23.find("select[name*=queryType]").val("like");

			  var column33 = $("#template3").clone();
			  column33.removeAttr("style");
			  column33.attr("id","tree_33");
			  column33.find("input[name*=name]").val("name");
			  
			  $("#tab-1 #contentTable1 tbody").append(column13);
			  $("#tab-2 #contentTable2 tbody").append(column23);
			  $("#tab-3 #contentTable3 tbody").append(column33);
			  column13.find('input:checkbox').iCheck({ checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-blue', increaseArea: '20%' });
			  column23.find('input:checkbox').iCheck({ checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-blue', increaseArea: '20%' });
			  column33.find('input:checkbox').iCheck({ checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-blue', increaseArea: '20%' });
			 }

			 if(!$("#tab-1 #contentTable1 tbody").find("input[name*=name][value=sort]").val()){
			  var column14 = $("#template1").clone();
			  column14.removeAttr("style");
			  column14.attr("id","tree_14");
			  column14.find("input[name*=name]").val("sort");
			  column14.find("input[name*=comments]").val("排序");
			  column14.find("span[name*=jdbcType]").val("decimal(10,0)");
			  column14.find("input[name*=isNull]").removeAttr("checked");
			  
			  var column24 = $("#template2").clone();
			  column24.removeAttr("style");
			  column24.attr("id","tree_24");
			  column24.find("input[name*=name]").val("sort");
			  column24.find("select[name*=javaType]").val("Integer");
			  column24.find("input[name*=javaField]").val("sort");
			  column24.find("input[name*=isList]").removeAttr("checked");

			  var column34 = $("#template3").clone();
			  column34.removeAttr("style");
			  column34.attr("id","tree_34");
			  column34.find("input[name*=name]").val("sort");
			  
			  $("#tab-1 #contentTable1 tbody").append(column14);
			  $("#tab-2 #contentTable2 tbody").append(column24);
			  $("#tab-3 #contentTable3 tbody").append(column34);
			  column14.find('input:checkbox').iCheck({ checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-blue', increaseArea: '20%' });
			  column24.find('input:checkbox').iCheck({ checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-blue', increaseArea: '20%' });
			  column34.find('input:checkbox').iCheck({ checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-blue', increaseArea: '20%' });
			 }
			  resetColumnNo();
			return false;
		}
		function delColumn(){
			$("input[name='ck']:checked").closest("tr").each(function(){

				var name = $(this).find("input[name*=name]").attr("name");
				$(this).remove();
				$("#tab-2 #contentTable2 tbody tr input[name='page-"+name+"']").closest("tr").remove();
				$("#tab-3 #contentTable3 tbody tr input[name='page-"+name+"']").closest("tr").remove();
			})
			resetColumnNo();
			return false;
		}
		
		
		function up(obj) { 
			var objParentTR = $(obj).parent().parent(); 
			var index = objParentTR.index();

			var prevTR = objParentTR.prev(); 
			if (prevTR.length > 0) { 
				prevTR.insertAfter(objParentTR); 
			} 


			var objParentTR2 = $("#tab-2 #contentTable2 tbody tr:eq("+index+")");
			var prevTR2 = objParentTR2.prev(); 
			if (prevTR2.length > 0) { 
				prevTR2.insertAfter(objParentTR2); 
			} 

			var objParentTR3 = $("#tab-3 #contentTable3 tbody tr:eq("+index+")");
			var prevTR3 = objParentTR3.prev(); 
			if (prevTR3.length > 0) { 
				prevTR3.insertAfter(objParentTR3); 
			} 
			
			resetColumnNo();
		} 
		function down(obj) { 
			var objParentTR = $(obj).parent().parent();
			var index = objParentTR.index(); 
			var nextTR = objParentTR.next(); 
			if (nextTR.length > 0) { 
				nextTR.insertBefore(objParentTR); 
			} 

			var objParentTR2 = $("#tab-2 #contentTable2 tbody tr:eq("+index+")");
			var nextTR2 = objParentTR2.next(); 
			if (nextTR2.length > 0) { 
				nextTR2.insertBefore(objParentTR2); 
			} 

			var objParentTR3 = $("#tab-3 #contentTable3 tbody tr:eq("+index+")");
			var nextTR3 = objParentTR3.next(); 
			if (nextTR3.length > 0) { 
				nextTR3.insertBefore(objParentTR3); 
			} 
			
			resetColumnNo();
		} 
	</script>

</head>
<body style="" class=" pace-running" id=""><div class="pace pace-active"><div class="pace-progress" style="width: 99.9766%;" data-progress-text="99%" data-progress="99">
  <div class="pace-progress-inner"></div>
</div>
<div class="pace-activity"></div></div>
	
	<div class="wrapper wrapper-content">
	
	<table style="display:none">
		<tbody><tr style="display:none" id="template1">
				<td>
					<input type="hidden" class="form-control required   digits" maxlength="200" value="0" name="columnList[0].sort">
					<label>0</label>
				</td>
				<td>
					<input type="checkbox" value="1" name="ck" class="form-control  ">
				</td>
				<td>
					<input type="text" value="" name="columnList[0].name" class="form-control required">
				</td>
				<td>
					<input type="text" maxlength="200" value="" name="columnList[0].comments" class="form-control required">
				</td>
				<td>
					<span value="varchar(64)" class="required" name="template_columnList[0].jdbcType"/>
				</td>
				<td>
					<input type="checkbox" value="1" name="columnList[0].isPk" class="form-control">
				</td>
				<td>
					<input type="checkbox" checked="" value="1" name="columnList[0].isNull" class="form-control ">
					<input type="hidden" value="1" name="columnList[0].isInsert" class="form-control">
					<input type="hidden" value="1" name="columnList[0].isEdit" class="form-control">
				</td>
					
			</tr>
			<tr style="display:none" id="template2">
				<td>
					<input type="text" value="" name="page-columnList[0].name" readonly="readonly" class="form-control">
				</td>
				<td>
					<select class="form-control required m-b" name="columnList[0].javaType" >
						<c:forEach items="${config.javaTypeList}" var="dict" varStatus="status">
							<option title="${dict.description }" value="${dict.value }">${dict.label}</option>
						</c:forEach>
						<option class="newadd" value="Custom">自定义输入</option>
					</select>
				</td>
				<td>
					<input type="text" class="form-control required " maxlength="200" value="" name="columnList[0].javaField">
				</td>
				<td>
					<input type="checkbox" checked="" value="1" name="columnList[0].isForm" class="form-control  ">
				</td>
				<td>
					<input type="checkbox" checked="" value="1" name="columnList[0].isList" class="form-control  ">
				</td>
				<td>
					<input type="checkbox" value="1" name="columnList[0].isQuery" class="form-control  ">
				</td>
				<td>
				
					<select class="form-control required  m-b" name="columnList[0].queryType">
						<c:forEach items="${config.queryTypeList}" var="config" varStatus="status">
							<option title="${config.description }" value="${config.value }">${config.label}</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<select class="form-control required  m-b" name="columnList[0].showType" >
						<c:forEach items="${config.showTypeList}" var="config" varStatus="status">
							<option title="${config.description }" value="${config.value }">${config.label}</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<input type="text" class="form-control   " maxlength="200" value="" name="columnList[0].dictType">
				</td>
			</tr>
			
			<tr style="display:none" id="template3">
				<td>
					<input type="text" value="" name="page-columnList[0].name" readonly="readonly" class="form-control">
				</td>
				<td>
					<input type="text" class="form-control  " maxlength="200" value="" name="columnList[0].tableName">
				</td>
				<td>
					<input type="text" class="form-control  " maxlength="200" value="" name="columnList[0].fieldLabels">
				</td>
				<td>
					<input type="text" class="form-control  " maxlength="200" value="" name="columnList[0].fieldKeys">
				</td>
				<td>
					<input type="text" class="form-control  " maxlength="200" value="" name="columnList[0].searchLabel">
				</td>
				<td>
					<input type="text" class="form-control  " maxlength="200" value="" name="columnList[0].searchKey">
				</td>
				
			</tr>
	
	
	</tbody></table>
		
			<!-- 业务表添加 -->
			<form:form id="inputForm" modelAttribute="genTable" action="${ctx}/gen/genTable/save" method="post" class="form-horizontal">
				<form:hidden path="id"/>
				<form:hidden path="isSync"/>
				<sys:message content="${message}"/>
				<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
				   <tbody>
						<tr>
							<td class="width-15 active"><label class="pull-right"><font color="red">*</font>表名:</label></td>
							<td class="width-35">
							<input type="text" maxlength="200" value="${genTable.name}" class="form-control required" name="name" id="name" >
							</td>
							<td class="width-15 active"><label class="pull-right"><font color="red">*</font>说明:</label></td>
							<td class="width-35">
								<input type="text" maxlength="200" value="${genTable.comments}" class="form-control required" name="comments" id="comments" >
							</td>
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">表类型</label></td>
							<td class="width-35">
								<form:select path="tableType" class="form-control">
									<form:options items="${fns:getDictList('table_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
								<span class="help-inline">如果是附表，请指定主表表名和当前表的外键</span>
							</td>
							<td class="width-15 active"><label class="pull-right"><font color="red">*</font>类名:</label></td>
							<td class="width-35">
								<input type="text" maxlength="200" value="${genTable.className}" class="form-control required" name="className" id="className" >
							</td>
							
						</tr>
						<tr>
							<td class="width-15 active"><label class="pull-right">主表表名:</label></td>
							<td class="width-35">
								<select class="form-control" name="parentTable">
									<option value="">无</option>
									<c:forEach items="${tableList}" var="table" varStatus="status">
										<option <c:if test="${table.name eq genTable.parentTable  }">selected</c:if> value="${table.name }">${table.nameAndComments}</option>
									</c:forEach>
								</select>
							</td>
							<td class="width-15 active"><label class="pull-right">当前表外键：</label></td>
							<td class="width-35">
								<input type="text" maxlength="200" value="${genTable.parentTableFk}" class="form-control" name="parentTableFk" id="parentTableFk">
							</td>
						</tr>
						
					</tbody>
				</table>
					<button onclick="return addColumn()" class="btn btn-white btn-sm"><i class="fa fa-plus"> 增加</i></button>
					<button onclick="return delColumn()" class="btn btn-white btn-sm"><i class="fa fa-minus"> 删除</i> </button>
					<br>
						
				<div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li class="active"><a aria-expanded="true" href="#tab-1" data-toggle="tab"> 数据库属性</a>
                        </li>
                        <li class=""><a aria-expanded="false" href="#tab-2" data-toggle="tab">页面属性</a>
                        </li>
                         <li class=""><a aria-expanded="false" href="#tab-3" data-toggle="tab">grid选择框（自定义java对象）</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab-1">
                            <div class="panel-body">
                                <table class="table table-striped table-bordered table-hover  dataTables-example dataTable" id="contentTable1">
                                <thead>
									<tr >
										<th width="40px">序号</th>
										<th>操作</th>
										<th title="数据库字段名">列名</th>
										<th title="默认读取数据库字段备注">说明</th>
										<th title="数据库中设置的字段类型及长度">物理类型</th>
										<th title="是否是数据库主键">主键</th>
										<th title="字段是否可为空值，不可为空字段自动进行空值验证">可空</th>
										<!--<th title="选中后该字段被加入到insert语句里">插入</th>
										<th title="选中后该字段被加入到update语句里">编辑</th>  -->
									</tr>
								</thead>
								<tbody>
							<c:forEach items="${genTable.columnList}" var="column" varStatus="status">
								<!-- ${column.name} -->
								<tr >
									<td>
										<input type="hidden" name="columnList[${status.index }].id" value="${column.id }"/>
										<input type="hidden" name="columnList[${status.index }].sort" value="${status.index }"/>
										<label>${status.index }</label>
									</td>
									<td>
										<input type="checkbox" class="i-checks" name="ck" value="1" />
									</td>
									<td>
										<input type="text" value="${column.name }" name="columnList[${status.index }].name" class="form-control">
									</td>
									<td>
										<input type="text" maxlength="200" value="${column.comments }" name="columnList[${status.index }].comments" class="form-control">
									</td>
									<td>
										<span  name="columnList[${status.index }].jdbcType" class="required " value="${column.jdbcType }"/>
									</td>
									<td>
										<input type="checkbox" class="i-checks" name="columnList[${status.index }].isPk" value="${column.isPk }" <c:if test="${column.isPk eq 1}">checked</c:if> />
									</td>
									<td>
										<input type="checkbox" class="i-checks" name="columnList[${status.index }].isNull" value="${column.isNull }" <c:if test="${column.isNull eq 1}">checked</c:if> />
										<input type="hidden" value="${column.isInsert }" name="columnList[${status.index }].isInsert">
										<input type="hidden" value="${column.isEdit }" name="columnList[${status.index }].isEdit">
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
                            </div>
                        </div>
                        <div class="tab-pane" id="tab-2">
                            <div class="panel-body">
                                 <table class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable" id="contentTable2">
                              <thead>
							<tr>
								<th width="15%" title="数据库字段名">列名</th>
								<th width="15%" title="实体对象的属性字段类型">Java类型</th>
								<th title="实体对象的属性字段（对象名.属性名|属性名2|属性名3，例如：用户user.id|name|loginName，属性名2和属性名3为Join时关联查询的字段）">Java属性名称 <i class="icon-question-sign"></i></th>
								<th title="选中后该字段被加入到查询列表里">表单</th>
								<th title="选中后该字段被加入到查询列表里">列表</th>
								<th title="选中后该字段被加入到查询条件里">查询</th>
								<th width="15%" title="该字段为查询字段时的查询匹配放松">查询匹配方式</th>
								<th width="15%" title="字段在表单中显示的类型">显示表单类型</th>
								<th title="显示表单类型设置为“下拉框、复选框、点选框”时，需设置字典的类型">字典类型</th>
							</tr>
							</thead>
							<tbody>
								<c:forEach items="${genTable.columnList}" var="column" varStatus="status">
									<!-- ${column.name} -->
									<tr>
										<td>
											<input type="text" value="${column.name}" name="page-columnList[${status.index }].name" readonly="readonly" class="form-control">
										</td>
										<td>
											
											<select class="form-control required m-b" name="columnList[${status.index }].javaType" >
												<c:forEach items="${config.javaTypeList}" var="dict" varStatus="status">
													<option title="${dict.description }" <c:if test="${dict.value eq column.javaType  }">selected</c:if> value="${dict.value }">${dict.label}</option>
												</c:forEach>
												<option class="newadd" value="Custom">自定义输入</option>
											</select>
										</td>
										<td>
											<input type="text" class="form-control required " maxlength="200" value="${column.javaField}" name="columnList[${status.index }].javaField" >
										</td>
										<td>
											<input type="checkbox" class="i-checks" name="columnList[${status.index }].isForm" value="${column.isForm}" <c:if test="${column.isForm eq 1  }">checked</c:if> />
										</td>
										<td>
											<input type="checkbox" class="i-checks" name="columnList[${status.index }].isList" value="${column.isList}" <c:if test="${column.isList eq 1  }">checked</c:if> />
										</td>
										<td>
											<input type="checkbox" class="i-checks" name="columnList[${status.index }].isQuery" value="${column.isQuery}" <c:if test="${column.isQuery eq 1  }">checked</c:if> />
										</td>
										<td>
											<select class="form-control required  m-b" name="columnList[${status.index }].queryType" >
												<c:forEach items="${config.queryTypeList}" var="dict" varStatus="status">
													<option title="${dict.description }" <c:if test="${dict.value eq column.queryType  }">selected</c:if> value="${dict.value }">${dict.label}</option>
												</c:forEach>
											</select>
										</td>
										<td>
											<select class="form-control required  m-b" name="columnList[${status.index }].showType" >
												<c:forEach items="${config.showTypeList}" var="dict" varStatus="status">
													<option title="${dict.description }" <c:if test="${dict.value eq column.showType  }">selected</c:if> value="${dict.value }">${dict.label}</option>
												</c:forEach>
											</select>
										</td>
										<td>
											<input type="text" class="form-control" maxlength="200" value="${column.dictType}" name="columnList[${status.index }].dictType">
										</td>
										
									</tr>
								</c:forEach>
							</tbody>
						</table>
                            </div>
                        </div>
                        
                         <div class="tab-pane" id="tab-3">
                            <div class="panel-body">
                                 <table class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable" id="contentTable3">
                              <thead>
							<tr>
								<th width="15%" title="数据库字段名">列名</th>
								<th width="15%" title="实体对象的属性字段类型">table表名</th>
								<th title="实体对象的属性字段说明（label1|label2|label3，用户名|登录名|角色）">JAVA属性说明<i class="icon-question-sign"></i></th>
								<th title="选中后该字段被加入到查询列表里">JAVA属性名称</th>
								<th title="选中后该字段被加入到查询列表里">检索标签</th>
								<th title="选中后该字段被加入到查询条件里">检索key</th>

							</tr>
							</thead>
							<tbody>
								<c:forEach items="${genTable.columnList}" var="column" varStatus="status">
									<!-- ${column.name} -->
									<tr>
										<td>
											<input type="text" value="${column.name }" name="page-columnList[${status.index }].name" readonly="readonly" class="form-control">
										</td>
										<td>
											<input type="text" class="form-control  " maxlength="200" value="" name="columnList[${status.index }].tableName">
										</td>
										<td>
											<input type="text" class="form-control  " maxlength="200" value="" name="columnList[${status.index }].fieldLabels">
										</td>
										<td>
											<input type="text" class="form-control  " maxlength="200" value="" name="columnList[${status.index }].fieldKeys">
										</td>
										<td>
											<input type="text" class="form-control  " maxlength="200" value="" name="columnList[${status.index }].searchLabel">
										</td>
										<td>
											<input type="text" class="form-control  " maxlength="200" value="" name="columnList${status.index }].searchKey">
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
                            </div>
                        </div>
                        
                    </div>


                </div>
					
			</form:form>
		
	</div>
</body>
</html>