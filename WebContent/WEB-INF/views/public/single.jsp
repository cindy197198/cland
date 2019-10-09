<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vn.edu.vinaenter.utils.StringUtil"%>
<%@include file="/WEB-INF/templates/taglib.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>  
					<div class="clearfix content">
						
						<h1>${objLand.lname } </h1>
						<div class="clearfix post-meta">
							<p><span><i class="fa fa-clock-o"></i> ${objLand.address }</span> <span><i class="fa fa-folder"></i> Diện tích: 100m2</span></p>
						</div>
						
						<div class="vnecontent">
							<p>${objLand.description }</p>
						</div>
						
						<!-- <a class="btn" href="">Bài trước</a>
						<a class="btn" href="">Bài sau</a> -->
					
					</div>
					
						<div class="more_themes">
							<h2>Bất động sản liên quan <i class="fa fa-thumbs-o-up"></i></h2>
							<div class="more_themes_container">
								<c:forEach items="${relatedLands }" var="itemLand">
									<c:url var="urlDetail" value="/chi-tiet/${StringUtil.makeSlug(itemLand.lname)}-${itemLand.lid }.html" ></c:url>
									<div class="single_more_themes floatleft">
										<a href="${urlDetail}"><img src="${contextPath}/fileUpload/${itemLand.picture}" alt=""/></a>
										<a href="${urlDetail}"><h2>${itemLand.lname }</h2></a>
									</div>
								</c:forEach>								
							</div>
						</div>