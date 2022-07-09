<style>
	.list a { text-decoration:none;
			  color:black;
			  }
	.list a:link { color : black; }
	.list a:hover { color : red; }
	
	#navtab > a{color:black;}
	.pwd {text-align:right;}
</style>
<script>
	var pageUrl = window.location.href;
			$(window).on('load', function(){
				$('#navtab > a').siblings('a').removeClass('active'); 
				
				if (pageUrl.indexOf('list') > -1) { 
			        $('#navtab > a').eq(0).addClass('active');
			    } else if (pageUrl.indexOf('cmt') > -1) { 
			        $('#navtab > a').eq(1).addClass('active');
			    } else if (pageUrl.indexOf('reply') > -1) { 
			        $('#navtab > a').eq(2).addClass('active');
			    } else if (pageUrl.indexOf('like') > -1) { 
			        $('#navtab > a').eq(3).addClass('active');
			    } else { 
			        $('#navtab > a').eq(4).addClass('active');
			    }
				
			});
</script>