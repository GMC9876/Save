<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<jsp:include page="/WEB-INF/header.jsp"></jsp:include>
		<style>
			.list a { text-decoration:none }
			.list a:link { color : black; }
			.list a:visited { color : #bc3cbc; }
			.list a:hover { color : red; }
		</style>
		<title>프로젝트 게시판</title>
		<script>
			window.onload = function(){
				if("${category}"=="all"){
					$("#ctgr")[0].innerHTML = "[전체게시판]"
				} else if("${category}"=="meetingLog"){
					$("#ctgr")[0].innerHTML = "[회의록]"
				} else if("${category}"=="FreeBoard"){
					$("#ctgr")[0].innerHTML = "[자유게시판]"
				} else{
					$("#ctgr")[0].innerHTML = "[something]"
				}
			}

			function bbs_add(uid){
				if (uid == "") {
					alert("로그인 후 이용가능합니다.")
					location.href="/login";
				} else{
				location.href="/bbs_add";
				}
			}
			
		</script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/topnav.jsp"></jsp:include>
		<div id="layoutSidenav">
		
			<jsp:include page="/WEB-INF/sidenav.jsp"></jsp:include>
			<div id="layoutSidenav_content">
				<main>
					<div class="container">
						<br>
						<br>
						<h1 class="text-center">Project Board!</h1>
						<h2 class="text-center" id="ctgr"></h2>
						<br>
						<br>
						<c:choose>
							<c:when test="${category=='all'}">
								<table class="table table-hover table-striped text-center" style="border: 1px solid;">
									<thead>
										<tr>
											<th class="col-1">번호</th>
											<th class="col-2">분류</th>
											<th class="col-3">제목</th>
											<th class="col-2">글쓴이</th>
											<th class="col-2">작성일</th>
											<th class="col-1">조회</th>
											<th class="col-1">좋아요</th>
										</tr>
									</thead>
									<tbody class="list">
										<c:set value="${category}" var="category"/>
										<c:forEach items="${pageInfo.list}" var="list">
												<tr>
													<td>${list.num}</td>
													<td>
														<c:if test="${list.category =='FreeBoard'}">
															자유게시판
														</c:if>
														<c:if test="${list.category =='meetingLog'}">
															회의록
														</c:if>
														<c:if test="${list.category =='something'}">
															something
														</c:if>
													</td>
													<td><a href="/bbs_detail/${list.num}/${category}">${list.title} [${list.reply}]</a></td>
													<td>${list.uid}</td>
													<td>${list.date}</td>
													<td>${list.cnt}</td>
													<td>${list.liked}</td>
												</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:when>
							
							<c:otherwise>
								<table class="table table-hover table-striped text-center" style="border: 1px solid;">
									<thead>
										<tr>
											<th class="col-2">번호</th>
											<th class="col-4">제목</th>
											<th class="col-2">글쓴이</th>
											<th class="col-2">작성일</th>
											<th class="col-1">조회</th>
											<th class="col-1">좋아요</th>
										</tr>
									</thead>
									<tbody class="list">
										<c:set value="${category}" var="category"/>
										<c:forEach items="${pageInfo.list}" var="list">
											<c:if test="${list.category==category}">
												<tr>
													<td>${list.num}</td>
													<td><a href="/bbs_detail/${list.num}/${category}">${list.title} [${list.reply}]</a></td>
													<td>${list.uid}</td>
													<td>${list.date}</td>
													<td>${list.cnt}</td>
													<td>${list.liked}</td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
							</c:otherwise>
						</c:choose>
						<button type="button" class="btn btn-outline-primary" onclick="bbs_add('${uid}');">글쓰기</button>
					</div>
					<nav aria-label="Page navigation example">
							<ul class="pagination justify-content-center">
							   	<c:forEach var="i" items="${pageInfo.navigatepageNums}">
							      	<c:choose>
							         	<c:when test="${i==pageInfo.pageNum}">	<!-- (c:when = if) 현재 보는 페이지는 링크를 안걸고 -->
											<li class="page-item disabled"><a class="page-link" href="#">${i}</a></li>
							         	</c:when>
							         	<c:otherwise>							<!-- (c:otherwise = else) 다른 페이지는 링크 걸음 -->
							          	  	<li class="page-item"><a class="page-link" href="/bbs_list/${i}/${category}">${i}</a></li>
							         	</c:otherwise>
							      	</c:choose> 
							   	</c:forEach>
						   	</ul>
						</nav>
				</main>
				<jsp:include page="/WEB-INF/footer.jsp"></jsp:include>
			</div>
		</div>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
	</body>
</html>