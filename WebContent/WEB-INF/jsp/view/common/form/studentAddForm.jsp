<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="student_add_layer" class="w3-modal">
	<div class="w3-modal-content w3-card-8 w3-animate-zoom"
		style="max-width: 600px">
		<div class="w3-center">
			<br> <span
				onclick="document.getElementById('student_add_layer').style.display='none'"
				class="w3-closebtn w3-hover-red w3-container w3-padding-8 w3-display-topright"
				title="Close Modal">×</span>
			<h1 class="w3-xxxlarge w3-padding-16">Student Information</h1>
		</div>

		<form class="w3-container" id="student_addForm"
			action="AddStudentServlet">
			<div class="w3-section">
				<label><b>StudentId</b></label> <input
					class="w3-input w3-border w3-margin-bottom" type="text"
					placeholder="Enter StudentId ex)12345678" name="id" id="id" > <label><b>Name</b></label>
				<input class="w3-input w3-border w3-margin-bottom" type="text"
					placeholder="Enter Name" id="name" name="name"> <label><b>BirthDate</b></label>
				<input class="w3-input w3-border w3-margin-bottom" type="date"
					placeholder="Enter BirthDate ex)1990-03-02" id="birthDate" name="birthDate">
				<button class="w3-btn-block w3-green w3-section w3-padding"
					type="submit">submit</button>
			</div>
		</form>

		<div class="w3-container w3-border-top w3-padding-16 w3-light-grey">
			<button
				onclick="document.getElementById('student_add_layer').style.display='none'"
				type="button" class="w3-btn w3-red">Cancel</button>
		</div>

	</div>
</div>
<script>
	
</script>
