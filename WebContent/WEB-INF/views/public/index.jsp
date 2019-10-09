<%@page import="vn.edu.vinaenter.utils.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/templates/taglib.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<div class="clearfix slider">
	<%@include file="/WEB-INF/templates/public/slide.jsp"%>
</div>
<div class="clearfix content">
	<div class="content_title">
		<h2>Bài viết mới</h2>
	</div>
	<c:forEach items="${listLand }" var="objLand">
		<c:url var="urlDetail" value="/chi-tiet/${StringUtil.makeSlug(objLand.lname)}-${objLand.lid }.html" ></c:url>
		<fmt:formatDate pattern="dd" value="${objLand.date_create}" var="date"/>
		<fmt:formatDate pattern="MM" value="${objLand.date_create}" var="month" />
		<div class="clearfix single_content">
			<div class="clearfix post_date floatleft">
				<div class="date">
					<h3>${date}</h3>
					<p>Tháng ${month}</p>
				</div>
			</div>
			<div class="clearfix post_detail">
				<h2>
					<a href="${urlDetail}">${objLand.lname }</a>
				</h2>
				<div class="clearfix post-meta">
					<p>
						<span><i class="fa fa-clock-o"></i> Địa chỉ:
							${objLand.address }</span> <span><i class="fa fa-folder"></i>
							Diện tích: ${objLand.area }m2</span>
					</p>
				</div>
				<div class="clearfix post_excerpt">
					<a href="${urlDetail}"><img src="${contextPath}/fileUpload/${objLand.picture}" alt="" /></a>
					<p>
						<c:choose>
							<c:when test="${objLand.description.length() >150 }">${objLand.description.substring(0,150) } ...</c:when>
							<c:otherwise>${objLand.description}</c:otherwise>
						 </c:choose>
						
					</p>
				</div>
				<a href="${urlDetail}">Đọc thêm</a>
			</div>
		</div>
	</c:forEach>


</div>

<div class="pagination">
	<nav>
		<ul>
			<c:choose>
				<c:when test="${page==1}">
					<c:set var="previous" value="disabled"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="previous" value=""></c:set>
				</c:otherwise>
			</c:choose>
			<li class="${previous}"><a
				href="${contextPath}/public/${page-1}"> << </a></li>
			<c:forEach begin="1" end="${sumPage }" var="i">
				<c:choose>
					<c:when test="${i== page}">
						<c:set var="active" value="active"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="active" value=""></c:set>
					</c:otherwise>
				</c:choose>
				<li class="${active }"><a href="${contextPath}/${i}">${i}</a></li>
			</c:forEach>
			<c:choose>
				<c:when test="${page==sumPage}">
					<c:set var="next" value="disabled"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="next" value=""></c:set>
				</c:otherwise>
			</c:choose>
			<li class="${next }"><a
				href="${contextPath}/${page+1}"> >> </a></li>
		</ul>
	</nav>
</div>