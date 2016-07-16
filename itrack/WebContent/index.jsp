<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content ="width=device-width, initial-scale=1">

<%@ include file="/jsp/common/JQMStyle.jsp"%>
<script>
$(document).ready(function(){
	$("#pinyin").focus();

})
function clearClientPY(){
	$("#pinyin").focus();
	$("#pinyin").attr("value","");
	
    $("#clients").hide();
    
    $('#clientsBody tr').each(function () {                
        $(this).remove();
    });

}
function checkSearch(){
	if ($.trim($("#pinyin").val()).length >= 2)
		searchClients();
}
function searchClients(){
	if (validateSearch()){
		var params = "pinyin=" + $("#pinyin").val();

		$.mobile.loading("show",{ theme: "b", text: "正在加载数据", textonly: false});
		
		$.post('<%=request.getContextPath()%>/clientController/SearchJSClientP/mobile', params, 
		function(result) {
			
			if (result.success) {
			    $('#clientsBody tr').each(function () {                
			        $(this).remove();
			    });
			    
			    var cops = result.obj;
			    if (cops != null && cops.length != 0){
				    for (var i = 0; i < cops.length; i++){
				    	var bg = "";
				    	if ((i % 2) == 0)
				    		bg = "style='background-color: #E0E0E0;'";
				    		
				    	var j = i +1;
				        if (cops[i] != "")  {
					          $("<tr "+bg+"><td style='vertical-align:middle;'>"+
					        		  cops[i].clientName +"</td><td style='vertical-align:middle;'>"+
					        		  cops[i].regionName +"</td><td style='vertical-align:middle;'>"+
					        		  cops[i].phoneNum+"</td>"+
							          "</tr>").appendTo("#clientsBody");
				        }
				    }
			    } else {
			    	$("<tr><td colspan=3><font color='red'>没有查询到客户信息</font> </td></tr>").appendTo("#clientsBody");
			    }
			    
			    $("#clients").show();

			    $.mobile.loading("hide");
			} else {
				$.mobile.loading("hide");
				renderPopup("系统错误",result.msg)
			}
		}, 'JSON');
	}
}
function validateSearch(){

	if ($.trim($("#pinyin").val()).length < 2){
		renderPopup("查询错误","请输入至少两位拼音作为查询条件");
		$("#pinyin").focus();
		return false;
	} else 
		return true;
}


</script>
</head>
<body>
	<div id="mainPage" data-role="page">

		<jsp:include  page="/jsp/common/MobileHeader.jsp"/>

		<div data-role="content" class="content">
				<table>
				    <tr>
						<td><label for="productCode">拼音 : </label></td> 
						<td><input type="text" id="pinyin" name="pinyin"  placeholder="输入至少两位拼音,自动查找" /></td>
					</tr>

				</table>
				<div class="ui-grid-a ui-responsive">
    				<div class="ui-block-a"><input type="button" id="clearBnt" data-theme="a" onclick ="clearClientPY();" value="清空查询条件"/></div>
    				<div class="ui-block-b"><input type="button" id="searchBnt" data-theme="b" onclick ="searchClients();" value="查找客户信息"/></div>
				</div>				
				<div id="clients" style="display:none">
					<table data-role="table" id="table-column-toggle" class="ui-responsive table-stroke">
						<thead>
					       <tr>
					         <th width="28%" data-priority="1">客户名字</th>
					         <th width="45%" data-priority="2">地区</th>
					         <th width="27%" data-priority="3">电话</th>
					       </tr>
					     </thead>
					     <tbody id="clientsBody">
					     </tbody>
				    </table>	
				</div>
				
		</div>
		
		<jsp:include  page="jsp/common/Popup.jsp"/>
	</div>

</body>
</html>