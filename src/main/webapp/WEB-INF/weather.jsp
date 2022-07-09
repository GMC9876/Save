<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
		<jsp:include page="/WEB-INF/header.jsp"></jsp:include>

        <title>날씨 정보</title>

    </head>
    <body>
		<jsp:include page="/WEB-INF/topnav.jsp"></jsp:include>
		<div id="layoutSidenav">
		
			<jsp:include page="/WEB-INF/sidenav.jsp"></jsp:include>
		 	<div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="display-4 fw-bold lh-1 mt-4">날씨</h1>
						<div class="card mb-3" style="max-width: 700px;">
						  <div class="row g-0">
						    <div class="col-md-4 my-5 py-3">
						    	<div class="mx-5 my-4 ps-4">
						      	<c:choose>
									<c:when test="${weather eq '맑음'}"><img src="/assets/img/icons8-sun-64.png" class="img-responsive" width="60px"/></c:when>
									<c:when test="${weather eq '구름 많음'}"><img src="/assets/img/icons8-clouds-64.png" class="img-responsive" width="60px"/></c:when>
									<c:when test="${weather eq '흐림'}"><img src="/assets/img/icons8-cloud-64.png" class="img-responsive" width="60px"/></c:when>
								</c:choose>
								</div>
							    <h5 class="card-title mt-4 ms-5 ps-3">현재시간</h5>
							    <h5 class="card-title ms-5 ps-3">${hour }시 ${minute }분</h5>
							    
							    <h5 class="card-title mt-4 ms-5 ps-3">${weather } ${temperature }°C</h5>
						    </div>
							<div class="col-md-8">
							  <div class="card-body">
							  	
							  <ul class="list-group list-group-flush">
							    <li class="list-group-item">강수 확률 : ${precipitation_probability }%</li>
							    <li class="list-group-item">강수 형태 : ${precipitation_form }</li>
							    <li class="list-group-item">습도 : ${humidity }%</li>
							    <li class="list-group-item">풍향 : ${wind_direction }m/s</li>
							    <li class="list-group-item">풍속 : ${wind_speed }</li>
							    <li class="list-group-item">풍속(남북) : ${wind_speed_SN }</li>
							    <li class="list-group-item">풍향(동서) : ${wind_speed_EW }</li>
							    <li class="list-group-item">적설 : ${snow }</li>
							    <li class="list-group-item">파고 : ${wave_height }</li>
							    
							  </ul>

							</div>
		
                        </div>
                      </div>
                      </div>
                      </div>
                </main>
				<jsp:include page="/WEB-INF/footer.jsp"></jsp:include>
				
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
