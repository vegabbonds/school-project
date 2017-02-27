<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${message != null}">
	<script>
		alert("${message }");
	</script>
</c:if>
<nav class="w3-sidenav w3-collapse w3-white  w3-large"
	style="z-index: 3; width: 300px;" id="mySidenav">
	<ul class="w3-navbar w3-black w3-center">
		<li class="navimg"><a href="javascript:void(0)"
			onclick="openNav('nav01')">Person</a></li>
		<li class="navimg"><a href="javascript:void(0)"
			onclick="openNav('nav02')">Subject</a></li>
		<li class="navimg"><a href="javascript:void(0)"
			onclick="openNav('nav03')">Score</a></li>
	</ul>

	<div id="nav01">
		<div class="w3-container w3-border-bottom">
			<h1 class="w3-text-theme">Person Management</h1>
		</div>
		<div>
			<li class="menu"><a href="#">Student</a>
				<ul class="hide">
					<li><a href="#"
						onclick="document.getElementById('student_add_layer').style.display='block'">Add</a></li>
					<li><a href="#"
						onclick="open_search_info('search_student_info')">Search</a></li>
				</ul></li>
			<li class="menu"><a href="#">Teacher</a>
				<ul class="hide">
					<li><a href="#"
						onclick="document.getElementById('teacher_add_layer').style.display='block'">Add</a></li>
					<li><a href="#"
						onclick="open_search_info('search_teacher_info')">Search</a></li>
				</ul></li>
			<li class="menu"><a href="#">Staff</a>
				<ul class="hide">
					<li><a href="#"
						onclick="document.getElementById('staff_add_layer').style.display='block'">Add</a></li>
					<li><a href="#"
						onclick="open_search_info('search_staff_info')">Search</a></li>
				</ul></li>
		</div>
	</div>

	<div id="nav02">
		<div class="w3-container w3-border-bottom">
			<h1 class="w3-text-theme">Subject Management</h1>
		</div>
		<a href="#"
			onclick="document.getElementById('subject_add_layer').style.display='block'">Add</a>
		<a href="#" onclick="open_search_info('search_subject_info')">Search</a>
	</div>

	<div id="nav03">
		<div class="w3-container w3-border-bottom">
			<h1 class="w3-text-theme">Score Management</h1>
		</div>
		<a href="#" onclick="document.getElementById('score_add_layer').style.display='block'">Add</a>
		<li class="menu"><a href="#">Search List</a>
			<ul class="hide">
				<li><a href="#" onclick="open_search_info('search_score_info')">Search</a></li>
				<li><a href="#" onclick="open_search_info('search_overall_avg_info')">Overall Average</a></li>
				<li><a href="#" onclick="open_search_info('search_avg_info')">Each Person Average</a></li>
			</ul></li>
	</div>
</nav>

<div class="w3-overlay w3-hide-large" onclick="w3_close()"
	style="cursor: pointer" id="myOverlay"></div>

<header class="w3-container w3-theme w3-padding-64 w3-center">
	<h1 class="w3-xxxlarge w3-padding-16">School Management</h1>
</header>