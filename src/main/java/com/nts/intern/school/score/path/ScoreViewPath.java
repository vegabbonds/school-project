package com.nts.intern.school.score.path;

public enum ScoreViewPath {	
	SCORE_PAGE("view/score/searchScorePage"),
	SCORE_AVG_PAGE("view/score/searchAvgScorePage"),
	SCORE_OVERALL_AVG_PAGE("view/score/searchOverallAvgScorePage");	
	
	private String viewPath;

	private ScoreViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	public String getPath() {
		return this.viewPath;
	}	
}
