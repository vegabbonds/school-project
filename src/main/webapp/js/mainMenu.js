function w3_open() {
	document.getElementById("mySidenav").style.display = "block";
	document.getElementById("myOverlay").style.display = "block";
}
function w3_close() {
	document.getElementById("mySidenav").style.display = "none";
	document.getElementById("myOverlay").style.display = "none";
}

openNav("nav01");
function openNav(id) {
	document.getElementById("nav01").style.display = "none";
	document.getElementById("nav02").style.display = "none";
	document.getElementById("nav03").style.display = "none";
	document.getElementById(id).style.display = "block";
}

$(document).ready(function() {
	$(".menu>a").click(function() {
		var submenu = $(this).next("ul");
		if (submenu.is(":visible")) {
			submenu.slideUp();
		} else {
			submenu.slideDown();
		}
	});
});