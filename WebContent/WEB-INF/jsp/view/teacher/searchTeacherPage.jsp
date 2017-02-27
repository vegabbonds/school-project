<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- contextpath 가져와서 변수 정의 -->
<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Teacher</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet"
	href="http://www.w3schools.com/lib/w3-theme-teal.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="./css/common.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js"></script>
</head>
<body>
	<%@include file="../common/menu/mainMenu.jsp"%>
	<%@include file="../common/search/search.jsp"%>
	<%@include file="../common/form/studentAddForm.jsp"%>
	<%@include file="../common/form/studentModifyForm.jsp"%>
	<%@include file="../common/form/staffAddForm.jsp"%>
	<%@include file="../common/form/staffModifyForm.jsp"%>
	<%@include file="../common/form/subjectAddForm.jsp"%>
	<%@include file="../common/form/subjectModifyForm.jsp"%>
	<%@include file="../common/form/teacherAddForm.jsp"%>
	<%@include file="../common/form/teacherModifyForm.jsp"%>
	<%@include file="../common/form/scoreAddForm.jsp"%>
	<%@include file="../common/form/scoreModifyForm.jsp"%>
</body>
<script src="js/mainMenu.js"></script>
<script src="js/search.js"></script>
<script src="js/searchTeacher.js"></script>
<script src="js/checkFormValidation.js"></script>
</html>