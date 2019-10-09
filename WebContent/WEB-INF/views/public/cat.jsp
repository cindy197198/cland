<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vn.edu.vinaenter.utils.StringUtil"%>
<%@include file="/WEB-INF/templates/taglib.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<div class="clearfix content">
						<div class="content_title"><h2>${listLand.get(0).cat.cname }</h2></div>
						
						<div class="clearfix single_work_container">
							<c:forEach items="${listLand }" var="objLand">
								<c:url var="urlDetail" value="/chi-tiet/${StringUtil.makeSlug(objLand.lname)}-${objLand.lid }.html" ></c:url>								
								<c:set var="cid" value="${objLand.cat.cid }"></c:set>
								<c:set var="cname" value="${objLand.cat.cname }"></c:set>
								<div class="clearfix single_work">
									<a href="${urlDetail}"><img class="img_bottom" src="${contextPath}/fileUpload/${objLand.picture}" alt=""/></a>
									<a href="${urlDetail}"><h2>${objLand.lname }</h2></a>
									<a href="${urlDetail}">
									<p class="caption" style="position: absolute;bottom:0;color:#fff;padding:5px;background-color: #737475" >
									<c:choose>
										<c:when test="${objLand.description.length() >50 }">${objLand.description.substring(0,50) } ...</c:when>
										<c:otherwise>${objLand.description}</c:otherwise>
									 </c:choose>
									</p>
									</a>
								</div>
							</c:forEach>

							<div class="clearfix work_pagination">
								<nav>
									<c:url var="urlCat" value="/danh-muc/${StringUtil.makeSlug(cname)}-${cid }/page" ></c:url>																																				
									<a class="newer floatleft" href="${urlCat}-${page-1}.html"> < -- Trang trước</a>
									<a class="older floatright" href="${urlCat }-${page+1}.html">Trang sau -- ></a>
								</nav>
							</div>
						</div>
					</div>