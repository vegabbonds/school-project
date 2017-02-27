<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>Error Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet"
	href="http://www.w3schools.com/lib/w3-theme-teal.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js"></script>
</head>
<body>
	<%@include file="WEB-INF/jsp/view/common/menu/mainMenu.jsp"%>
	<%@include file="WEB-INF/jsp/view/common/search/search.jsp"%>
	<%@include file="WEB-INF/jsp/view/common/form/studentAddForm.jsp"%>
	<%@include file="WEB-INF/jsp/view/common/form/staffAddForm.jsp"%>
	<%@include file="WEB-INF/jsp/view/common/form/subjectAddForm.jsp"%>
	<%@include file="WEB-INF/jsp/view/common/form/teacherAddForm.jsp"%>
	<%@include file="WEB-INF/jsp/view/common/form/scoreAddForm.jsp"%>
	<script type="text/javascript">
		alert("에러가 발생하였습니다. 초기화면으로 되돌아 갔습니다.");
	</script>
</body>
<script src="js/mainMenu.js"></script>
<script src="js/search.js"></script>
<script src="js/checkFormValidation.js"></script>
</html>
