 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <a class="nav-link" href="/index">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Dashboard
                            </a>
                            <div class="sb-sidenav-menu-heading">Interface</div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#boardCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                Board
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                             <div class="collapse" id="boardCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="/bbs_list/1/all">전체게시판</a>
                                    <a class="nav-link" href="/bbs_list/1/FreeBoard">자유게시판</a>
                                    <a class="nav-link" href="/bbs_list/1/meetingLog">회의록</a>
                                    <a class="nav-link" href="/bbs_list/1/something">something</a>
                                </nav>
                            </div>
                            
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                Pages
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                                        개인정보
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                   
                                    <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                        <nav class="sb-sidenav-menu-nested nav">
                                         
											<c:set value="${phone}" var="phone"/>
											<c:if test="${uid!=null}">
												<a class="nav-link" href="/mypage/bbs_list/1">마이페이지</a>
											</c:if>
                                        </nav>
                                    </div>
                                    
                                   
                                </nav>
                            </div>
                            <div class="sb-sidenav-menu-heading">Addons</div>
                             <a class="nav-link" href="/weather">
                                <div class="sb-nav-link-icon"><i class="fa-solid fa-cloud-sun"></i></div>
                                Weather
                            </a>
                          <!-- <a class="nav-link" href="/calendar">
                                <div class="sb-nav-link-icon"><i class="fas fa-calendar"></i></div>
                                Calendar
                            </a> -->
                            <a class="nav-link" href="/calendar">
                                <div class="sb-nav-link-icon"><i class="fas fa-calendar"></i></div>
                                Calendar
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                        Start Bootstrap
                    </div>
                </nav>
            </div>