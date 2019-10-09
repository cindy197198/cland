<%@page import="java.util.List"%>
<%@page import="vn.edu.vinaenter.utils.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/templates/taglib.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<div class="clearfix sidebar">
						<div class="clearfix single_sidebar category_items">
							<h2>Danh mục bất động sản</h2>							
							<ul>
							<% 	
							List<Integer> listCount=  (List<Integer>) request.getAttribute("listCount");
							int i=0;
							%>
							<c:forEach items="${listCat }" var="objCat">
								<c:url var="urlCat" value="/danh-muc/${StringUtil.makeSlug(objCat.cname)}-${objCat.cid }.html" ></c:url>																		
								<li class="cat-item"><a href="${urlCat}">${objCat.cname}</a>(<%=listCount.get(i) %>)</li>
								<%i++; %>
							</c:forEach>								
							</ul>
						</div>

						<div class="clearfix single_sidebar">
							<div class="popular_post">
								<div class="sidebar_title"><h2>Xem nhiều</h2></div>
								<ul>
									<c:forEach items="${listMostView}" var="objLand">
										<c:url var="urlDetail" value="/chi-tiet/${StringUtil.makeSlug(objLand.lname)}-${objLand.lid }.html" ></c:url>										
										<li><a href="${urlDetail}">${objLand.lname } </a></li>
									</c:forEach>																	
								</ul>
							</div>
							</div>
						
						<div class="clearfix single_sidebar">
							<h2>Danh mục hot</h2>
							<ul>
								<c:forEach items="${listHot}" var="objCat">
									<c:url var="urlCat" value="/danh-muc/${StringUtil.makeSlug(objCat.cname)}-${objCat.cid }.html" ></c:url>																											
									<li><a href="${urlCat}">${objCat.cname } <span></span></a></li>
								</c:forEach>
							</ul>
						</div>
					</div>