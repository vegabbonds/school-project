$(function() {
	$("#student_addForm").validate({
		rules : {
			id : {
				required : true,
				minlength : 8,
				maxlength : 8,
				range : [10000000, 99999999],
				digits : true	
			},
			name : {
				required : true,
				maxlength : 10
			},
			birthDate : {
				required : true,
			}
		},
		messages : {
			id : {
				required : "아이디를 입력하세요.",
				maxlength : "id는 8자리 입니다.",
				minlength : "id는 8자리 입니다.",
				digits : "양의 정수만 입렵하십시오."
			},
			name : {
				required : "이름을 입력하세요",
				maxlength : "10자 이내로 입력하십시오"
			},
			birthDate : {
				required : "생일을 입력하세요.",
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})

	$("#student_modifyForm").validate({
		rules : {
			name : {
				required : true,
				maxlength : 10
			},
			birthDate : {
				required : true,
			}
		},
		messages : {
			name : {
				required : "이름을 입력하세요",
				maxlength : "10자 이내로 입력하십시오"
			},
			birthDate : {
				required : "생일을 입력하세요.",
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})

	$("#staff_addForm").validate({
		rules : {
			id : {
				required : true,
				minlength : 7,
				maxlength : 7,
				range : [1000000, 9999999],
				digits : true
			},
			name : {
				required : true,
				maxlength : 10
			},
			birthDate : {
				required : true,
			}
		},
		messages : {
			id : {
				required : "아이디를 입력하세요.",
				maxlength : "id는 7자리 입니다.",
				minlength : "id는 7자리 입니다.",
				digits : "양의 정수만 입렵하십시오."
			},
			name : {
				required : "이름을 입력하세요",
				maxlength : "10자 이내로 입력하십시오"
			},
			birthDate : {
				required : "생일을 입력하세요.",
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})

	$("#staff_modifyForm").validate({
		rules : {
			name : {
				required : true,
				maxlength : 10
			},
			birthDate : {
				required : true,
			}
		},
		messages : {
			name : {
				required : "이름을 입력하세요",
				maxlength : "10자 이내로 입력하십시오"
			},
			birthDate : {
				required : "생일을 입력하세요.",
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})

	$("#teacher_addForm").validate({
		rules : {
			id : {
				required : true,
				minlength : 6,
				maxlength : 6,
				range : [100000, 999999],
				digits : true	
			},
			name : {
				required : true,
				maxlength : 10
			},
			birthDate : {
				required : true,
			},
			subjectId : {
				required : true,
				minlength : 5,
				maxlength : 5,
				digits : true	
			}
		},
		messages : {
			id : {
				required : "아이디를 입력하세요.",
				maxlength : "id는 6자리 입니다.",
				minlength : "id는 6자리 입니다.",
				digits : "양의 정수만 입렵하십시오."
			},
			name : {
				required : "이름을 입력하세요",
				maxlength : 10
			},
			birthDate : {
				required : "생일을 입력하세요.",
			},
			subjectId : {
				required : "과목id를 입력하세요.",
				maxlength : "과목id는 5자리 입니다.",
				minlength : "과목id는 5자리 입니다.",
				digits : "양의 정수만 입렵하십시오."
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})

	$("#teacher_modifyForm").validate({
		rules : {
			name : {
				required : true,
				maxlength : 10
			},
			birthDate : {
				required : true,
			},
			subjectId : {
				required : true,
				minlength : 5,
				maxlength : 5,
				digits : true	
			}
		},
		messages : {
			name : {
				required : "이름을 입력하세요",
				maxlength : "10자 이내로 입력하십시오"
			},
			birthDate : {
				required : "생일을 입력하세요.",
			},
			subjectId : {
				required : "과목id를 입력하세요.",
				maxlength : "과목id는 5자리 입니다.",
				minlength : "과목id는 5자리 입니다.",
				digits : "양의 정수만 입렵하십시오."
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})

	$("#subject_addForm").validate({
		rules : {
			id : {
				required : true,
				minlength : 5,
				maxlength : 5,
				range : [10000, 99999],
				digits : true				
			},
			name : {
				required : true,
				maxlength : 10
			}
		},
		messages : {
			id : {
				required : "아이디를 입력하세요.",
				maxlength : "id는 5자리 입니다.",
				minlength : "id는 5자리 입니다.",
				digits : "양의 정수만 입렵하십시오."
			},
			name : {
				required : "이름을 입력하세요",
				maxlength : "10자 이내로 입력하십시오"
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})

	$("#subject_modifyForm").validate({
		rules : {
			name : {
				required : true,
				maxlength : 10
			}
		},
		messages : {
			name : {
				required : "이름을 입력하세요",
				maxlength : "10자 이내로 입력하십시오"
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})

	$("#score_addForm").validate({
		rules : {
			id : {
				required : true,
				minlength : 8,
				maxlength : 8,
				range : [10000000, 99999999],
				digits : true	
			},
			subjectId : {
				required : true,
				minlength : 5,
				maxlength : 5,
				range : [10000, 99999],
				digits : true	
			},
			score : {
				required : true,
				maxlength : 3,
				digits : true,
				range : [ 0, 100 ]
			}
		},
		messages : {
			id : {
				required : "아이디를 입력하세요.",
				maxlength : "id는 8자리 입니다.",
				minlength : "id는 8자리 입니다.",
				digits : "양의 정수만 입렵하십시오."
			},
			subjectId : {
				required : "과목id를 입력하세요.",
				maxlength : "과목id는 5자리 입니다.",
				minlength : "과목id는 5자리 입니다.",
				digits : "양의 정수만 입렵하십시오."
			},
			score : {
				required : "성적을 입력하세요.",
				maxlength : "0~100범위의 값을 입력하십시오.",
				digits : "양의 정수만 입렵하십시오.",
				range : "0~100범위의 값을 입력하십시오."
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})

	$("#score_modifyForm").validate({
		rules : {
			score : {
				required : true,
				maxlength : 3,
				digits : true,
				range : [ 0, 100 ]
			}
		},
		messages : {
			score : {
				required : "성적을 입력하세요.",
				maxlength : "0~100범위의 값을 입력하십시오.",
				digits : "숫자만 입력하십시오.",
				range : "0~100범위의 값을 입력하십시오."
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})
})

jQuery.validator.setDefaults({

	onkeyup : false,
	onclick : false,
	onfocusout : false,
	showErrors : function(errorMap, errorList) {
		if (this.numberOfInvalids()) {
			alert(errorList[0].message);
		}
	}
});

$.validator.addMethod("myDateFormat", function(value, element) {

	var re = /^\d{4}-\d{1,2}-\d{1,2}$/;

	return (this.optional(element) && value == "") || re.test(value);
});
