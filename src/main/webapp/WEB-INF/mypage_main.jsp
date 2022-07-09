<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
				<main>
					<div class="container">
						<br>
						<br>
						<h1 class="text-center">[마이페이지]</h1>
						<br>
						<br>
							<div class="form-group">
	                            <label>아이디</label>
	                            <input type="text" class="form-control " value="${uid}" disabled/>
	                        </div>
	                        <div class="form-group">
	                            <label>이메일</label>
	                            <input type="text" class="form-control" value="${email}" disabled/>
	                        </div>
						
						<c:set value="${phone}" var="phone"/>
						<c:if test="${phone!=null}">
							<div class="pwd">
								<a style="text-align:right;" href="/update">비밀번호 변경</a>
							</div>
						</c:if>
					</div>
						
						<nav class="nav nav-tabs" id="navtab">
						  <a class="nav-link" aria-current="page" href="/mypage/bbs_list/1">작성글</a>
						  <a class="nav-link" href="/mypage/bbs_cmt/1">작성댓글</a>
						  <a class="nav-link" href="/mypage/bbs_reply/1">댓글단 글</a>
						  <a class="nav-link" href="/mypage/bbs_like/1">좋아요 글</a>
						  <a class="nav-link" href="/mypage/bbs_delete/1">삭제한 글</a>
						</nav>
					
				</main>