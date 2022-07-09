<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/WEB-INF/header.jsp"></jsp:include>
		<meta charset="EUC-KR">
		<title>Insert title here</title>
		<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
		<script src="https:/p/unpkg.com/aos@2.3.1/dist/aos.js"></script>
		<style>
			img {
			 width: 100%;
			 height: 100%;
			}	
		</style>

		<script>
			
			window.addEventListener("wheel", function(e){
				e.preventDefault();
			},{passive : false});
			
			
			var mHtml = $("html");
			var page = 1;
			var lastPage = $(".content").length;
			
			mHtml.animate({scrollTop:0},10);
			
			$(window).on("wheel", function(e) {
					
				if($("html").is(":animated")) return;
				 
				if(e.originalEvent.deltaY > 0){
					if(page== lastPage) return;
			 
					page++;
				}else if(e.originalEvent.deltaY < 0){
					if(page == 1) return;
			 
					page--;
				}
				var posTop = (page-1) * $('#content').height()+130;
				
				mHtml.animate({scrollTop : posTop});
			});
			
		</script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/topnav.jsp"></jsp:include>
		<div class="content" id="content"><img src="/img/ppt/1.png"></div>
   		<div class="content" id="content"><img src="/img/ppt/2.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/3.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/4.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/5.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/6.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/7.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/8.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/9.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/10.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/11.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/12.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/13.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/14.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/15.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/16.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/17.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/18.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/19.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/20.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/21.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/22.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/23.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/24.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/25.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/26.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/27.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/28.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/29.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/30.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/31.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/32.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/33.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/34.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/35.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/36.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/37.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/38.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/39.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/40.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/41.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/42.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/43.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/44.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/45.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/46.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/47.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/48.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/49.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/50.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/51.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/52.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/53.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/54.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/55.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/56.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/57.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/58.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/59.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/60.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/61.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/62.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/63.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/64.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/65.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/66.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/67.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/68.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/69.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/70.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/71.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/72.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/73.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/74.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/75.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/76.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/77.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/78.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/79.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/80.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/81.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/82.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/83.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/84.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/85.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/86.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/87.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/88.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/89.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/90.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/91.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/92.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/93.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/94.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/95.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/96.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/97.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/98.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/99.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/100.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/101.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/102.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/103.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/104.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/105.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/106.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/107.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/108.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/109.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/110.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/111.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/112.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/113.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/114.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/115.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/116.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/117.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/118.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/119.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/120.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/121.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/122.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/123.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/124.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/125.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/126.png"></div>
      	<div class="content" id="content"><img src="/img/ppt/127.png"></div>
	</body>
</html>