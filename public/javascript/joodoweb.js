$(document).ready(function(){
	
	$('.hideable').hide();
	
	$('.toggle').click(function() {
		$(this).children('p').toggle('normal');
  });
	
});