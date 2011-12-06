$(document).ready(function(){
	
	$('.description').hide();
	$('.toggle_source').hide();
	$('.source').hide();
	
	$('.toggle_description').click(function() {
		$(this).children('.description').toggle('normal');
		$(this).siblings('.toggle_source').toggle('normal');
  });

	$('.toggle_source').click(function() {
		$(this).children('.source').toggle('normal');
	})
	
});