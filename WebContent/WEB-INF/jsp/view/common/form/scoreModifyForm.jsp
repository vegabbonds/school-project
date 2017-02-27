<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="modify_score_form" class="w3-modal">
	<div class="w3-modal-content w3-card-8 w3-animate-zoom"
		style="max-width: 600px">
		<div class="w3-center">
			<br> <span
				onclick="document.getElementById('modify_score_form').style.display='none'"
				class="w3-closebtn w3-hover-red w3-container w3-padding-8 w3-display-topright"
				title="Close Modal">×</span>
			<h1 class="w3-xxxlarge w3-padding-16">Score Information</h1>
		</div>

		<form class="w3-container" id="score_modifyForm"
			action="ModifyScoreServlet">
			<div class="w3-section">
				<input class="w3-input w3-border w3-margin-bottom" type="hidden"
					placeholder="Enter StudentId" name="modify_id" id="modify_id"> <input
					class="w3-input w3-border w3-margin-bottom" type="hidden"
					placeholder="Enter SubjectId" name="modify_subjectId" id="modify_subjectId"> <label><b>Score</b></label>
				<input class="w3-input w3-border w3-margin-bottom" type="text"
					placeholder="Enter Score" name="score" id="score">
				<button class="w3-btn-block w3-green w3-section w3-padding"
					type="submit">submit</button>
			</div>
		</form>

		<div class="w3-container w3-border-top w3-padding-16 w3-light-grey">
			<button
				onclick="document.getElementById('modify_score_form').style.display='none'"
				type="button" class="w3-btn w3-red">Cancel</button>
		</div>

	</div>
</div>