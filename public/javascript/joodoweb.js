$(document).ready(function(){
	
	// $('.description').hide();
	// $('.toggle_source').hide();
	$('.documentation').hide();
	$('.source').hide();
	
	$('.toggle_documentation').click(function() {
		$(this).siblings('.documentation').toggle('normal');
  });

	$('.toggle_source').click(function() {
		$(this).siblings('.source').toggle('normal');
	})
	
});