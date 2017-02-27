document.getElementById("search_student_info").style.display = "none";
document.getElementById("search_staff_info").style.display = "none";
document.getElementById("search_subject_info").style.display = "none";
document.getElementById("search_teacher_info").style.display = "none";
document.getElementById("search_score_info").style.display = "none";
document.getElementById("search_avg_info").style.display = "none";
document.getElementById("search_overall_avg_info").style.display = "none";

function open_search_info(id) {
	if (id == "search_student_info") {
		$("#search_student").submit();
	} else if (id == "search_staff_info") {
		$("#search_staff").submit();
	} else if (id == "search_subject_info") {
		$("#search_subject").submit();
	} else if (id == "search_teacher_info") {
		$("#search_teacher").submit();
	} else if (id == "search_score_info") {
		$("#search_score").submit();
	} else if (id == "search_avg_info") {
		$("#search_avg_score").submit();
	} else if (id == "search_overall_avg_info") {
		$("#search_overall_avg_score").submit();
	}
}

function modify_student(some) {
	var modify_student_id = $(some).parent().parent().find("td:eq(0)").html();
	$("input[name='modify_id']").val(modify_student_id);
	document.getElementById('modify_student_form').style.display = 'block';
}
function delete_student(some) {
	var delete_id = $(some).parent().parent().find("td:eq(0)").html();
	console.log($(some).parent().parent().find("td:eq(0)").html());
	$("#delete_student_id").val(delete_id);
	$("#delete_student_form").submit();
}

function modify_staff(some) {
	var modify_staff_id = $(some).parent().parent().find("td:eq(0)").html();
	$("input[name='modify_id']").val(modify_staff_id);
	document.getElementById('modify_staff_form').style.display = 'block';
}
function delete_staff(some) {
	var delete_id = $(some).parent().parent().find("td:eq(0)").html();
	console.log($(some).parent().parent().find("td:eq(0)").html());
	$("#delete_staff_id").val(delete_id);
	$("#delete_staff_form").submit();
}

function modify_subject(some) {
	var modify_subject_id = $(some).parent().parent().find("td:eq(0)").html();
	$("input[name='modify_id']").val(modify_subject_id);
	document.getElementById('modify_subject_form').style.display = 'block';
}
function delete_subject(some) {
	var delete_id = $(some).parent().parent().find("td:eq(0)").html();
	console.log($(some).parent().parent().find("td:eq(0)").html());
	$("#delete_subject_id").val(delete_id);
	$("#delete_subject_form").submit();
}

function modify_teacher(some) {
	var modify_teacher_id = $(some).parent().parent().find("td:eq(0)").html();
	$("input[name='modify_id']").val(modify_teacher_id);
	document.getElementById('modify_teacher_form').style.display = 'block';
}
function delete_teacher(some) {
	var delete_id = $(some).parent().parent().find("td:eq(0)").html();
	console.log($(some).parent().parent().find("td:eq(0)").html());
	$("#delete_teacher_id").val(delete_id);
	$("#delete_teacher_form").submit();
}

function modify_score(some) {
	var modify_student_id = $(some).parent().parent().find("td:eq(0)").html();
	$("input[name='modify_id']").val(modify_student_id);
	var modify_subject_id = $(some).parent().parent().find("td:eq(2)").html();
	$("input[name='modify_subjectId']").val(modify_subject_id);
	document.getElementById('modify_score_form').style.display = 'block';
}
function delete_score(some) {
	var delete_id = $(some).parent().parent().find("td:eq(0)").html();
	var delete_subjectId = $(some).parent().parent().find("td:eq(2)").html();
	$("#delete_score_id").val(delete_id);
	$("#delete_score_subjectId").val(delete_subjectId);
	$("#delete_score_form").submit();
}