<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vn.edu.vinaenter.utils.StringUtil"%>   
<%@include file="/WEB-INF/templates/taglib.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<section id="header_area">
			<div class="wrapper header">
				<div class="clearfix header_top">
					<div class="clearfix logo floatleft">
						<a href="${pageContext.request.contextPath }"><h1><span>C</span>Land</h1></a>
					</div>
					<div class="clearfix search floatright">
						<form>
							<input type="text" placeholder="Search"/>
							<input type="submit" />
						</form>
					</div>
				</div>
				<div class="header_bottom">
					<nav>
						<ul id="nav">
							<li><a href="${pageContext.request.contextPath }">Trang chủ</a></li>
							<li id="dropdown"><a href="">Bất động sản</a>
								<ul>
									<c:forEach items="${listCat }" var="objCat">
										<c:url var="urlCat" value="/danh-muc/${StringUtil.makeSlug(objCat.cname)}-${objCat.cid }.html" ></c:url>										
										<li><a href="${urlCat}">${objCat.cname }</a></li>	
									</c:forEach>
																	
								</ul>
							</li>
							<li><a href="single.html">Giới thiệu</a></li>
							<li><a href="${contextPath }/lien-he">Liên hệ</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</section>