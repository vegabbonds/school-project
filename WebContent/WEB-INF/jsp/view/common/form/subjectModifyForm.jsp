<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="modify_subject_form" class="w3-modal">
	<div class="w3-modal-content w3-card-8 w3-animate-zoom"
		style="max-width: 600px">
		<div class="w3-center">
			<br> <span
				onclick="document.getElementById('modify_subject_form').style.display='none'"
				class="w3-closebtn w3-hover-red w3-container w3-padding-8 w3-display-topright"
				title="Close Modal">×</span>
			<h1 class="w3-xxxlarge w3-padding-16">Subject Information</h1>
		</div>

		<form class="w3-container" id="subject_modifyForm"
			action="ModifySubjectServlet">
			<div class="w3-section">
				<input type="hidden" name="modify_id"> <label><b>Name</b></label>
				<input class="w3-input w3-border w3-margin-bottom" type="text"
					placeholder="Enter Name" name="name" id="name">
				<button class="w3-btn-block w3-green w3-section w3-padding"
					type="submit">submit</button>
			</div>
		</form>

		<div class="w3-container w3-border-top w3-padding-16 w3-light-grey">
			<button
				onclick="document.getElementById('modify_subject_form').style.display='none'"
				type="button" class="w3-btn w3-red">Cancel</button>
		</div>

	</div>
</div>
